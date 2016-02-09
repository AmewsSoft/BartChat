// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            Socks5Client, Socks5Proxy

class Socks5ClientForInitiator extends Socks5Client
{

    private XMPPConnection connection;
    private String sessionID;
    private String target;

    public Socks5ClientForInitiator(org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost streamhost, String s, XMPPConnection xmppconnection, String s1, String s2)
    {
        super(streamhost, s);
        connection = xmppconnection;
        sessionID = s1;
        target = s2;
    }

    private void activate()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Bytestream bytestream = createStreamHostActivation();
        connection.createPacketCollectorAndSend(bytestream).nextResultOrThrow();
    }

    private Bytestream createStreamHostActivation()
    {
        Bytestream bytestream = new Bytestream(sessionID);
        bytestream.setMode(null);
        bytestream.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        bytestream.setTo(streamHost.getJID());
        bytestream.setToActivate(target);
        return bytestream;
    }

    public Socket getSocket(int i)
        throws IOException, InterruptedException, TimeoutException, XMPPException, SmackException
    {
        Socket socket;
        if (streamHost.getJID().equals(connection.getUser()))
        {
            Socket socket1 = Socks5Proxy.getSocks5Proxy().getSocket(digest);
            socket = socket1;
            if (socket1 == null)
            {
                throw new SmackException("target is not connected to SOCKS5 proxy");
            }
        } else
        {
            socket = super.getSocket(i);
            try
            {
                activate();
            }
            catch (XMPPException xmppexception)
            {
                socket.close();
                throw xmppexception;
            }
            catch (org.jivesoftware.smack.SmackException.NoResponseException noresponseexception)
            {
                socket.close();
                throw noresponseexception;
            }
        }
        return socket;
    }
}
