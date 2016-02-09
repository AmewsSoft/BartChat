// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.io.InputStream;
import java.io.OutputStream;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamRequest;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.jivesoftware.smackx.si.packet.StreamInitiation;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            StreamNegotiator

public class IBBTransferNegotiator extends StreamNegotiator
{
    private static class ByteStreamRequest extends InBandBytestreamRequest
    {

        private ByteStreamRequest(InBandBytestreamManager inbandbytestreammanager, Open open)
        {
            super(inbandbytestreammanager, open);
        }

    }


    private XMPPConnection connection;
    private InBandBytestreamManager manager;

    protected IBBTransferNegotiator(XMPPConnection xmppconnection)
    {
        connection = xmppconnection;
        manager = InBandBytestreamManager.getByteStreamManager(xmppconnection);
    }

    public InputStream createIncomingStream(StreamInitiation streaminitiation)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        manager.ignoreBytestreamRequestOnce(streaminitiation.getSessionID());
        return negotiateIncomingStream(initiateIncomingStream(connection, streaminitiation));
    }

    public OutputStream createOutgoingStream(String s, String s1, String s2)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        s = manager.establishSession(s2, s);
        s.setCloseBothStreamsEnabled(true);
        return s.getOutputStream();
    }

    public String[] getNamespaces()
    {
        return (new String[] {
            "http://jabber.org/protocol/ibb"
        });
    }

    InputStream negotiateIncomingStream(Stanza stanza)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        stanza = (new ByteStreamRequest(manager, (Open)stanza)).accept();
        stanza.setCloseBothStreamsEnabled(true);
        return stanza.getInputStream();
    }

    public void newStreamInitiation(String s, String s1)
    {
        manager.ignoreBytestreamRequestOnce(s1);
    }
}
