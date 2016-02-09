// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.tcp;

import org.jivesoftware.smack.ConnectionConfiguration;

public class XMPPTCPConnectionConfiguration extends ConnectionConfiguration
{
    public static class Builder extends org.jivesoftware.smack.ConnectionConfiguration.Builder
    {

        private boolean compressionEnabled;
        private int connectTimeout;

        public volatile ConnectionConfiguration build()
        {
            return build();
        }

        public XMPPTCPConnectionConfiguration build()
        {
            return new XMPPTCPConnectionConfiguration(this);
        }

        protected volatile org.jivesoftware.smack.ConnectionConfiguration.Builder getThis()
        {
            return getThis();
        }

        protected Builder getThis()
        {
            return this;
        }

        public Builder setCompressionEnabled(boolean flag)
        {
            compressionEnabled = flag;
            return this;
        }

        public Builder setConnectTimeout(int i)
        {
            connectTimeout = i;
            return this;
        }



        private Builder()
        {
            compressionEnabled = false;
            connectTimeout = XMPPTCPConnectionConfiguration.DEFAULT_CONNECT_TIMEOUT;
        }

    }


    public static int DEFAULT_CONNECT_TIMEOUT = 30000;
    private final boolean compressionEnabled;
    private final int connectTimeout;

    private XMPPTCPConnectionConfiguration(Builder builder1)
    {
        super(builder1);
        compressionEnabled = builder1.compressionEnabled;
        connectTimeout = builder1.connectTimeout;
    }


    public static Builder builder()
    {
        return new Builder();
    }

    public int getConnectTimeout()
    {
        return connectTimeout;
    }

    public boolean isCompressionEnabled()
    {
        return compressionEnabled;
    }

}
