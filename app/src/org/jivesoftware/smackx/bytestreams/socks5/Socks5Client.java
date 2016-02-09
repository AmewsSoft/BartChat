// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            Socks5Utils

class Socks5Client
{

    protected String digest;
    protected org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost streamHost;

    public Socks5Client(org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost streamhost, String s)
    {
        streamHost = streamhost;
        digest = s;
    }

    private byte[] createSocks5ConnectRequest()
    {
        byte abyte0[] = digest.getBytes();
        byte abyte1[] = new byte[abyte0.length + 7];
        abyte1[0] = 5;
        abyte1[1] = 1;
        abyte1[2] = 0;
        abyte1[3] = 3;
        abyte1[4] = (byte)abyte0.length;
        System.arraycopy(abyte0, 0, abyte1, 5, abyte0.length);
        abyte1[abyte1.length - 2] = 0;
        abyte1[abyte1.length - 1] = 0;
        return abyte1;
    }

    protected boolean establish(Socket socket)
        throws SmackException, IOException
    {
        DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
        socket = new DataOutputStream(socket.getOutputStream());
        socket.write(new byte[] {
            5, 1, 0
        });
        socket.flush();
        byte abyte0[] = new byte[2];
        datainputstream.readFully(abyte0);
        if (abyte0[0] != 5 || abyte0[1] != 0)
        {
            return false;
        } else
        {
            byte abyte1[] = createSocks5ConnectRequest();
            socket.write(abyte1);
            socket.flush();
            socket = Socks5Utils.receiveSocks5Message(datainputstream);
            abyte1[1] = 0;
            return Arrays.equals(abyte1, socket);
        }
    }

    public Socket getSocket(int i)
        throws IOException, org.jivesoftware.smack.XMPPException.XMPPErrorException, InterruptedException, TimeoutException, SmackException, XMPPException
    {
        Object obj = new FutureTask(new Callable() {

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

            
            {
                this$0 = Socks5Client.this;
                super();
            }
        });
        (new Thread(((Runnable) (obj)))).start();
        long l = i;
        try
        {
            obj = (Socket)((FutureTask) (obj)).get(l, TimeUnit.MILLISECONDS);
        }
        catch (ExecutionException executionexception)
        {
            Throwable throwable = executionexception.getCause();
            if (throwable != null)
            {
                if (throwable instanceof IOException)
                {
                    throw (IOException)throwable;
                }
                if (throwable instanceof SmackException)
                {
                    throw (SmackException)throwable;
                }
            }
            throw new IOException("Error while connection to SOCKS5 proxy");
        }
        return ((Socket) (obj));
    }
}
