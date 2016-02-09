// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import org.jivesoftware.smack.SmackException;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            Socks5Client

class this._cls0
    implements Callable
{

    final Socks5Client this$0;

    public volatile Object call()
        throws Exception
    {
        return call();
    }

    public Socket call()
        throws IOException, SmackException
    {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(streamHost.getAddress(), streamHost.getPort()));
        boolean flag;
        try
        {
            flag = establish(socket);
        }
        catch (SmackException smackexception)
        {
            socket.close();
            throw smackexception;
        }
        if (flag)
        {
            return socket;
        } else
        {
            socket.close();
            throw new SmackException("SOCKS5 negotiation failed");
        }
    }

    am.StreamHost()
    {
        this$0 = Socks5Client.this;
        super();
    }
}
