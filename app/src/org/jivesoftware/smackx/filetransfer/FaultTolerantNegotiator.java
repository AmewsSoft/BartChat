// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.io.InputStream;
import java.io.OutputStream;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.si.packet.StreamInitiation;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            StreamNegotiator

public class FaultTolerantNegotiator extends StreamNegotiator
{

    private final XMPPConnection connection;
    private final StreamNegotiator primaryNegotiator;
    private final StreamNegotiator secondaryNegotiator;

    public FaultTolerantNegotiator(XMPPConnection xmppconnection, StreamNegotiator streamnegotiator, StreamNegotiator streamnegotiator1)
    {
        primaryNegotiator = streamnegotiator;
        secondaryNegotiator = streamnegotiator1;
        connection = xmppconnection;
    }

    private StreamNegotiator determineNegotiator(Stanza stanza)
    {
        if (stanza instanceof Bytestream)
        {
            return primaryNegotiator;
        }
        if (stanza instanceof Open)
        {
            return secondaryNegotiator;
        } else
        {
            throw new IllegalStateException("Unknown stream initation type");
        }
    }

    public InputStream createIncomingStream(StreamInitiation streaminitiation)
        throws SmackException, org.jivesoftware.smack.XMPPException.XMPPErrorException
    {
        streaminitiation = initiateIncomingStream(connection, streaminitiation);
        StreamNegotiator streamnegotiator = determineNegotiator(streaminitiation);
        try
        {
            streaminitiation = streamnegotiator.negotiateIncomingStream(streaminitiation);
        }
        // Misplaced declaration of an exception variable
        catch (StreamInitiation streaminitiation)
        {
            throw new IllegalStateException(streaminitiation);
        }
        return streaminitiation;
    }

    public OutputStream createOutgoingStream(String s, String s1, String s2)
        throws SmackException, XMPPException
    {
        OutputStream outputstream;
        try
        {
            outputstream = primaryNegotiator.createOutgoingStream(s, s1, s2);
        }
        catch (Exception exception)
        {
            return secondaryNegotiator.createOutgoingStream(s, s1, s2);
        }
        return outputstream;
    }

    public String[] getNamespaces()
    {
        String as[] = primaryNegotiator.getNamespaces();
        String as1[] = secondaryNegotiator.getNamespaces();
        String as2[] = new String[as.length + as1.length];
        System.arraycopy(as, 0, as2, 0, as.length);
        System.arraycopy(as1, 0, as2, as.length, as1.length);
        return as2;
    }

    InputStream negotiateIncomingStream(Stanza stanza)
    {
        throw new UnsupportedOperationException("Negotiation only handled by create incoming stream method.");
    }

    public void newStreamInitiation(String s, String s1)
    {
        primaryNegotiator.newStreamInitiation(s, s1);
        secondaryNegotiator.newStreamInitiation(s, s1);
    }
}
