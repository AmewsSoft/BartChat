// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.SmackException;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            Socks5Proxy, Socks5Utils

private class <init>
    implements Runnable
{

    final Socks5Proxy this$0;

    private void establishConnection(Socket socket)
        throws SmackException, IOException
    {
        DataOutputStream dataoutputstream = new DataOutputStream(socket.getOutputStream());
        DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
        if (datainputstream.read() != 5)
        {
            throw new SmackException("Only SOCKS5 supported");
        }
        byte abyte1[] = new byte[datainputstream.read()];
        datainputstream.readFully(abyte1);
        byte abyte2[] = new byte[2];
        abyte2[0] = 5;
        boolean flag1 = false;
        int i = 0;
label0:
        do
        {
label1:
            {
                boolean flag = flag1;
                if (i < abyte1.length)
                {
                    if (abyte1[i] != 0)
                    {
                        break label1;
                    }
                    flag = true;
                }
                if (!flag)
                {
                    abyte2[1] = -1;
                    dataoutputstream.write(abyte2);
                    dataoutputstream.flush();
                    throw new SmackException("Authentication method not supported");
                }
                break label0;
            }
            i++;
        } while (true);
        abyte2[1] = 0;
        dataoutputstream.write(abyte2);
        dataoutputstream.flush();
        byte abyte0[] = Socks5Utils.receiveSocks5Message(datainputstream);
        String s = new String(abyte0, 5, abyte0[4]);
        if (!Socks5Proxy.access$200(Socks5Proxy.this).contains(s))
        {
            abyte0[1] = 5;
            dataoutputstream.write(abyte0);
            dataoutputstream.flush();
            throw new SmackException("Connection is not allowed");
        } else
        {
            abyte0[1] = 0;
            dataoutputstream.write(abyte0);
            dataoutputstream.flush();
            Socks5Proxy.access$300(Socks5Proxy.this).put(s, socket);
            return;
        }
    }

    public void run()
    {
_L2:
        Object obj;
        Socket socket;
        socket = null;
        obj = socket;
        if (Socks5Proxy.access$100(Socks5Proxy.this).isClosed())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = socket;
        if (Thread.currentThread().isInterrupted())
        {
            return;
        }
        obj = socket;
        socket = Socks5Proxy.access$100(Socks5Proxy.this).accept();
        obj = socket;
        try
        {
            establishConnection(socket);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        catch (Exception exception)
        {
            if (obj != null)
            {
                try
                {
                    ((Socket) (obj)).close();
                }
                catch (IOException ioexception) { }
            }
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    private ()
    {
        this$0 = Socks5Proxy.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
