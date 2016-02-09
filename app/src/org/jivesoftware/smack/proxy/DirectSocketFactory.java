// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.proxy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;

class DirectSocketFactory extends SocketFactory
{

    public DirectSocketFactory()
    {
    }

    public Socket createSocket(String s, int i)
        throws IOException, UnknownHostException
    {
        Socket socket = new Socket(Proxy.NO_PROXY);
        socket.connect(new InetSocketAddress(s, i));
        return socket;
    }

    public Socket createSocket(String s, int i, InetAddress inetaddress, int j)
        throws IOException, UnknownHostException
    {
        return new Socket(s, i, inetaddress, j);
    }

    public Socket createSocket(InetAddress inetaddress, int i)
        throws IOException
    {
        Socket socket = new Socket(Proxy.NO_PROXY);
        socket.connect(new InetSocketAddress(inetaddress, i));
        return socket;
    }

    public Socket createSocket(InetAddress inetaddress, int i, InetAddress inetaddress1, int j)
        throws IOException
    {
        return new Socket(inetaddress, i, inetaddress1, j);
    }
}
