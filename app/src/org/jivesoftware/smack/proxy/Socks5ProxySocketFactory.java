// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;

// Referenced classes of package org.jivesoftware.smack.proxy:
//            ProxyException, ProxyInfo

public class Socks5ProxySocketFactory extends SocketFactory
{

    private ProxyInfo proxy;

    public Socks5ProxySocketFactory(ProxyInfo proxyinfo)
    {
        proxy = proxyinfo;
    }

    private void fill(InputStream inputstream, byte abyte0[], int i)
        throws IOException
    {
        int k;
        for (int j = 0; j < i; j += k)
        {
            k = inputstream.read(abyte0, j, i - j);
            if (k <= 0)
            {
                throw new ProxyException(ProxyInfo.ProxyType.SOCKS5, "stream is closed");
            }
        }

    }

    private Socket socks5ProxifiedSocket(String s, int i)
        throws IOException
    {
        Object obj;
        byte abyte0[];
        String s1;
        String s2;
        int j;
        abyte0 = null;
        obj = proxy.getProxyAddress();
        j = proxy.getProxyPort();
        s1 = proxy.getProxyUsername();
        s2 = proxy.getProxyPassword();
        obj = new Socket(((String) (obj)), j);
        InputStream inputstream;
        OutputStream outputstream;
        inputstream = ((Socket) (obj)).getInputStream();
        outputstream = ((Socket) (obj)).getOutputStream();
        ((Socket) (obj)).setTcpNoDelay(true);
        abyte0 = new byte[1024];
        int k;
        k = 0 + 1;
        abyte0[0] = 5;
        j = k + 1;
        abyte0[k] = 2;
        k = j + 1;
        abyte0[j] = 0;
        abyte0[k] = 2;
        outputstream.write(abyte0, 0, k + 1);
        fill(inputstream, abyte0, 2);
        byte byte0;
        k = 0;
        byte0 = abyte0[1];
        j = k;
        byte0 & 0xff;
        JVM INSTR tableswitch 0 2: default 180
    //                   0 209
    //                   1 184
    //                   2 215;
           goto _L1 _L2 _L3 _L4
_L1:
        j = k;
_L3:
        if (j != 0) goto _L6; else goto _L5
_L5:
        int l;
        try
        {
            ((Socket) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        try
        {
            throw new ProxyException(ProxyInfo.ProxyType.SOCKS5, "fail in SOCKS5 proxy");
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        break; /* Loop/switch isn't completed */
        s;
_L14:
        throw s;
_L2:
        j = 1;
        continue; /* Loop/switch isn't completed */
_L4:
        j = k;
        if (s1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = k;
        if (s2 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = 0 + 1;
        abyte0[0] = 1;
        abyte0[j] = (byte)s1.length();
        System.arraycopy(s1.getBytes(), 0, abyte0, j + 1, s1.length());
        j = s1.length() + 2;
        l = j + 1;
        abyte0[j] = (byte)s2.length();
        System.arraycopy(s2.getBytes(), 0, abyte0, l, s2.length());
        outputstream.write(abyte0, 0, l + s2.length());
        fill(inputstream, abyte0, 2);
        j = k;
        if (abyte0[1] == 0)
        {
            j = 1;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        j = 0 + 1;
        abyte0[0] = 5;
        k = j + 1;
        abyte0[j] = 1;
        j = k + 1;
        abyte0[k] = 0;
        s = s.getBytes();
        k = s.length;
        l = j + 1;
        abyte0[j] = 3;
        abyte0[l] = (byte)k;
        System.arraycopy(s, 0, abyte0, l + 1, k);
        j = k + 5;
        k = j + 1;
        abyte0[j] = (byte)(i >>> 8);
        abyte0[k] = (byte)(i & 0xff);
        outputstream.write(abyte0, 0, k + 1);
        fill(inputstream, abyte0, 4);
        i = abyte0[1];
        if (i == 0) goto _L8; else goto _L7
_L7:
        try
        {
            ((Socket) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        throw new ProxyException(ProxyInfo.ProxyType.SOCKS5, (new StringBuilder()).append("server returns ").append(abyte0[1]).toString());
_L13:
        if (obj != null)
        {
            try
            {
                ((Socket) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        obj = (new StringBuilder()).append("ProxySOCKS5: ").append(s.toString()).toString();
        if (s instanceof Throwable)
        {
            throw new ProxyException(ProxyInfo.ProxyType.SOCKS5, ((String) (obj)), s);
        } else
        {
            throw new IOException(((String) (obj)));
        }
_L8:
        abyte0[3] & 0xff;
        JVM INSTR tableswitch 1 4: default 713
    //                   1 628
    //                   2 713
    //                   3 640
    //                   4 669;
           goto _L9 _L10 _L9 _L11 _L12
_L10:
        fill(inputstream, abyte0, 6);
        return ((Socket) (obj));
_L11:
        fill(inputstream, abyte0, 1);
        fill(inputstream, abyte0, (abyte0[0] & 0xff) + 2);
        return ((Socket) (obj));
_L12:
        fill(inputstream, abyte0, 18);
        return ((Socket) (obj));
        s;
        obj = abyte0;
          goto _L13
        s;
          goto _L14
_L9:
        return ((Socket) (obj));
        if (true) goto _L3; else goto _L15
_L15:
    }

    public Socket createSocket(String s, int i)
        throws IOException, UnknownHostException
    {
        return socks5ProxifiedSocket(s, i);
    }

    public Socket createSocket(String s, int i, InetAddress inetaddress, int j)
        throws IOException, UnknownHostException
    {
        return socks5ProxifiedSocket(s, i);
    }

    public Socket createSocket(InetAddress inetaddress, int i)
        throws IOException
    {
        return socks5ProxifiedSocket(inetaddress.getHostAddress(), i);
    }

    public Socket createSocket(InetAddress inetaddress, int i, InetAddress inetaddress1, int j)
        throws IOException
    {
        return socks5ProxifiedSocket(inetaddress.getHostAddress(), i);
    }
}
