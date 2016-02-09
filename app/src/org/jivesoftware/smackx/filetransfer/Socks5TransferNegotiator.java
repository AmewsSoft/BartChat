// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager;
import org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamRequest;
import org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamSession;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.si.packet.StreamInitiation;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            StreamNegotiator

public class Socks5TransferNegotiator extends StreamNegotiator
{
    private static class ByteStreamRequest extends Socks5BytestreamRequest
    {

        private ByteStreamRequest(Socks5BytestreamManager socks5bytestreammanager, Bytestream bytestream)
        {
            super(socks5bytestreammanager, bytestream);
        }

    }


    private XMPPConnection connection;
    private Socks5BytestreamManager manager;

    Socks5TransferNegotiator(XMPPConnection xmppconnection)
    {
        connection = xmppconnection;
        manager = Socks5BytestreamManager.getBytestreamManager(connection);
    }

    public InputStream createIncomingStream(StreamInitiation streaminitiation)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, InterruptedException, SmackException
    {
        manager.ignoreBytestreamRequestOnce(streaminitiation.getSessionID());
        return negotiateIncomingStream(initiateIncomingStream(connection, streaminitiation));
    }

    public OutputStream createOutgoingStream(String s, String s1, String s2)
        throws org.jivesoftware.smack.SmackException.NoResponseException, SmackException, XMPPException
    {
        try
        {
            s = manager.establishSession(s2, s).getOutputStream();
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new SmackException("error establishing SOCKS5 Bytestream", s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new SmackException("error establishing SOCKS5 Bytestream", s);
        }
        return s;
    }

    public String[] getNamespaces()
    {
        return (new String[] {
            "http://jabber.org/protocol/bytestreams"
        });
    }

    InputStream negotiateIncomingStream(Stanza stanza)
        throws InterruptedException, SmackException, org.jivesoftware.smack.XMPPException.XMPPErrorException
    {
        stanza = (new ByteStreamRequest(manager, (Bytestream)stanza)).accept();
        try
        {
            stanza = new PushbackInputStream(stanza.getInputStream());
            stanza.unread(stanza.read());
        }
        // Misplaced declaration of an exception variable
        catch (Stanza stanza)
        {
            throw new SmackException("Error establishing input stream", stanza);
        }
        return stanza;
    }

    public void newStreamInitiation(String s, String s1)
    {
        manager.ignoreBytestreamRequestOnce(s1);
    }
}
