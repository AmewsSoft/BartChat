// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.extensions;

import org.jivesoftware.smack.initializer.UrlInitializer;

public class ExtensionsInitializer extends UrlInitializer
{

    public ExtensionsInitializer()
    {
    }

    protected String getConfigUrl()
    {
        return "classpath:org.jivesoftware.smack.extensions/extensions.xml";
    }

    protected String getProvidersUrl()
    {
        return "classpath:org.jivesoftware.smack.extensions/extensions.providers";
    }
}
