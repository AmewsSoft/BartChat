// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.proxy;

import java.io.IOException;

public class ProxyException extends IOException
{

    private static final long serialVersionUID = 1L;

    public ProxyException(ProxyInfo.ProxyType proxytype)
    {
        super((new StringBuilder()).append("Proxy Exception ").append(proxytype.toString()).append(" : ").append("Unknown Error").toString());
    }

    public ProxyException(ProxyInfo.ProxyType proxytype, String s)
    {
        super((new StringBuilder()).append("Proxy Exception ").append(proxytype.toString()).append(" : ").append(s).toString());
    }

    public ProxyException(ProxyInfo.ProxyType proxytype, String s, Throwable throwable)
    {
        super((new StringBuilder()).append("Proxy Exception ").append(proxytype.toString()).append(" : ").append(s).append(", ").append(throwable).toString());
    }
}
