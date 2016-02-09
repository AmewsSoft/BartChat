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
//            ProxyInfo, ProxyException

public class Socks4ProxySocketFactory extends SocketFactory
{

    private ProxyInfo proxy;

    public Socks4ProxySocketFactory(ProxyInfo proxyinfo)
    {
        proxy = proxyinfo;
    }

    private Socket socks4ProxifiedSocket(String s, int i)
        throws IOException
    {
        Object obj;
        byte abyte0[];
        String s1;
        int j;
        abyte0 = null;
        obj = proxy.getProxyAddress();
        j = proxy.getProxyPort();
        s1 = proxy.getProxyUsername();
        obj = new Socket(((String) (obj)), j);
        InputStream inputstream;
        OutputStream outputstream;
        inputstream = ((Socket) (obj)).getInputStream();
        outputstream = ((Socket) (obj)).getOutputStream();
        ((Socket) (obj)).setTcpNoDelay(true);
        abyte0 = new byte[1024];
        int k;
        k = 0 + 1;
        abyte0[0] = 4;
        j = k + 1;
        abyte0[k] = 1;
        k = j + 1;
        abyte0[j] = (byte)(i >>> 8);
        abyte0[k] = (byte)(i & 0xff);
        s = InetAddress.getByName(s).getAddress();
        j = 0;
        i = k + 1;
_L2:
        k = s.length;
        if (j >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        abyte0[i] = s[j];
        j++;
        i++;
        if (true) goto _L2; else goto _L1
        s;
_L12:
        throw new ProxyException(ProxyInfo.ProxyType.SOCKS4, s.toString(), s);
        s;
_L10:
        throw s;
_L1:
        j = i;
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_215;
        }
        System.arraycopy(s1.getBytes(), 0, abyte0, i, s1.length());
        j = i + s1.length();
        abyte0[j] = 0;
        outputstream.write(abyte0, 0, j + 1);
        i = 0;
_L7:
        if (i >= 6) goto _L4; else goto _L3
_L3:
        j = inputstream.read(abyte0, i, 6 - i);
        if (j > 0) goto _L6; else goto _L5
_L5:
        throw new ProxyException(ProxyInfo.ProxyType.SOCKS4, "stream is closed");
        s;
_L8:
        if (obj != null)
        {
            try
            {
                ((Socket) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        throw new ProxyException(ProxyInfo.ProxyType.SOCKS4, s.toString());
_L6:
        i += j;
          goto _L7
_L4:
        if (abyte0[0] == 0)
        {
            break MISSING_BLOCK_LABEL_345;
        }
        throw new ProxyException(ProxyInfo.ProxyType.SOCKS4, (new StringBuilder()).append("server returns VN ").append(abyte0[0]).toString());
        i = abyte0[1];
        if (i == 90)
        {
            break MISSING_BLOCK_LABEL_395;
        }
        try
        {
            ((Socket) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        s = (new StringBuilder()).append("ProxySOCKS4: server returns CD ").append(abyte0[1]).toString();
        throw new ProxyException(ProxyInfo.ProxyType.SOCKS4, s);
        inputstream.read(new byte[2], 0, 2);
        return ((Socket) (obj));
        s;
        obj = abyte0;
          goto _L8
        s;
        if (true) goto _L10; else goto _L9
_L9:
        s;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public Socket createSocket(String s, int i)
        throws IOException, UnknownHostException
    {
        return socks4ProxifiedSocket(s, i);
    }

    public Socket createSocket(String s, int i, InetAddress inetaddress, int j)
        throws IOException, UnknownHostException
    {
        return socks4ProxifiedSocket(s, i);
    }

    public Socket createSocket(InetAddress inetaddress, int i)
        throws IOException
    {
        return socks4ProxifiedSocket(inetaddress.getHostAddress(), i);
    }

    public Socket createSocket(InetAddress inetaddress, int i, InetAddress inetaddress1, int j)
        throws IOException
    {
        return socks4ProxifiedSocket(inetaddress.getHostAddress(), i);
    }
}
