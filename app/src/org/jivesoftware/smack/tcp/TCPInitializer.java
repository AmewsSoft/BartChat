// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.tcp;

import org.jivesoftware.smack.initializer.UrlInitializer;

public class TCPInitializer extends UrlInitializer
{

    public TCPInitializer()
    {
    }

    protected String getProvidersUrl()
    {
        return "classpath:org.jivesoftware.smack.tcp/smacktcp.providers";
    }
}
