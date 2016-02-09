// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.tcp;

import org.jivesoftware.smack.ConnectionConfiguration;

// Referenced classes of package org.jivesoftware.smack.tcp:
//            XMPPTCPConnectionConfiguration

public static class <init> extends org.jivesoftware.smack.der
{

    private boolean compressionEnabled;
    private int connectTimeout;

    public volatile ConnectionConfiguration build()
    {
        return build();
    }

    public XMPPTCPConnectionConfiguration build()
    {
        return new XMPPTCPConnectionConfiguration(this, null);
    }

    protected volatile org.jivesoftware.smack.der getThis()
    {
        return getThis();
    }

    protected getThis getThis()
    {
        return this;
    }

    public getThis setCompressionEnabled(boolean flag)
    {
        compressionEnabled = flag;
        return this;
    }

    public compressionEnabled setConnectTimeout(int i)
    {
        connectTimeout = i;
        return this;
    }



    private ()
    {
        compressionEnabled = false;
        connectTimeout = XMPPTCPConnectionConfiguration.DEFAULT_CONNECT_TIMEOUT;
    }

    CONNECT_TIMEOUT(CONNECT_TIMEOUT connect_timeout)
    {
        this();
    }
}
