// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import org.jivesoftware.smackx.bytestreams.BytestreamSession;

public class Socks5BytestreamSession
    implements BytestreamSession
{

    private final boolean isDirect;
    private final Socket socket;

    protected Socks5BytestreamSession(Socket socket1, boolean flag)
    {
        socket = socket1;
        isDirect = flag;
    }

    public void close()
        throws IOException
    {
        socket.close();
    }

    public InputStream getInputStream()
        throws IOException
    {
        return socket.getInputStream();
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        return socket.getOutputStream();
    }

    public int getReadTimeout()
        throws IOException
    {
        int i;
        try
        {
            i = socket.getSoTimeout();
        }
        catch (SocketException socketexception)
        {
            throw new IOException("Error on underlying Socket");
        }
        return i;
    }

    public boolean isDirect()
    {
        return isDirect;
    }

    public boolean isMediated()
    {
        return !isDirect;
    }

    public void setReadTimeout(int i)
        throws IOException
    {
        try
        {
            socket.setSoTimeout(i);
            return;
        }
        catch (SocketException socketexception)
        {
            throw new IOException("Error on underlying Socket");
        }
    }
}
