// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.SocketFactory;
import org.jivesoftware.smack.util.stringencoder.Base64;

// Referenced classes of package org.jivesoftware.smack.proxy:
//            ProxyInfo, ProxyException

class HTTPProxySocketFactory extends SocketFactory
{

    private static final Pattern RESPONSE_PATTERN = Pattern.compile("HTTP/\\S+\\s(\\d+)\\s(.*)\\s*");
    private ProxyInfo proxy;

    public HTTPProxySocketFactory(ProxyInfo proxyinfo)
    {
        proxy = proxyinfo;
    }

    private Socket httpProxifiedSocket(String s, int i)
        throws IOException
    {
        String s1 = proxy.getProxyAddress();
        Socket socket = new Socket(s1, proxy.getProxyPort());
        Object obj = (new StringBuilder()).append("CONNECT ").append(s).append(":").append(i).toString();
        s = proxy.getProxyUsername();
        int j;
        if (s == null)
        {
            s = "";
        } else
        {
            String s2 = proxy.getProxyPassword();
            s = (new StringBuilder()).append("\r\nProxy-Authorization: Basic ").append(Base64.encode((new StringBuilder()).append(s).append(":").append(s2).toString())).toString();
        }
        socket.getOutputStream().write((new StringBuilder()).append(((String) (obj))).append(" HTTP/1.1\r\nHost: ").append(((String) (obj))).append(s).append("\r\n\r\n").toString().getBytes("UTF-8"));
        s = socket.getInputStream();
        obj = new StringBuilder(100);
        j = 0;
        do
        {
            char c = (char)s.read();
            ((StringBuilder) (obj)).append(c);
            if (((StringBuilder) (obj)).length() > 1024)
            {
                throw new ProxyException(ProxyInfo.ProxyType.HTTP, (new StringBuilder()).append("Recieved header of >1024 characters from ").append(s1).append(", cancelling connection").toString());
            }
            if (c == '\uFFFF')
            {
                throw new ProxyException(ProxyInfo.ProxyType.HTTP);
            }
            if ((j == 0 || j == 2) && c == '\r')
            {
                i = j + 1;
            } else
            if ((j == 1 || j == 3) && c == '\n')
            {
                i = j + 1;
            } else
            {
                i = 0;
            }
            j = i;
        } while (i != 4);
        if (i != 4)
        {
            throw new ProxyException(ProxyInfo.ProxyType.HTTP, (new StringBuilder()).append("Never received blank line from ").append(s1).append(", cancelling connection").toString());
        }
        s = (new BufferedReader(new StringReader(((StringBuilder) (obj)).toString()))).readLine();
        if (s == null)
        {
            throw new ProxyException(ProxyInfo.ProxyType.HTTP, (new StringBuilder()).append("Empty proxy response from ").append(s1).append(", cancelling").toString());
        }
        obj = RESPONSE_PATTERN.matcher(s);
        if (!((Matcher) (obj)).matches())
        {
            throw new ProxyException(ProxyInfo.ProxyType.HTTP, (new StringBuilder()).append("Unexpected proxy response from ").append(s1).append(": ").append(s).toString());
        }
        if (Integer.parseInt(((Matcher) (obj)).group(1)) != 200)
        {
            throw new ProxyException(ProxyInfo.ProxyType.HTTP);
        } else
        {
            return socket;
        }
    }

    public Socket createSocket(String s, int i)
        throws IOException, UnknownHostException
    {
        return httpProxifiedSocket(s, i);
    }

    public Socket createSocket(String s, int i, InetAddress inetaddress, int j)
        throws IOException, UnknownHostException
    {
        return httpProxifiedSocket(s, i);
    }

    public Socket createSocket(InetAddress inetaddress, int i)
        throws IOException
    {
        return httpProxifiedSocket(inetaddress.getHostAddress(), i);
    }

    public Socket createSocket(InetAddress inetaddress, int i, InetAddress inetaddress1, int j)
        throws IOException
    {
        return httpProxifiedSocket(inetaddress.getHostAddress(), i);
    }

}
