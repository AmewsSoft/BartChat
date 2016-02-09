// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.provider;


abstract class AbstractProviderInfo
{

    private String element;
    private String ns;
    private Object provider;

    AbstractProviderInfo(String s, String s1, Object obj)
    {
        element = s;
        ns = s1;
        provider = obj;
    }

    public String getElementName()
    {
        return element;
    }

    public String getNamespace()
    {
        return ns;
    }

    Object getProvider()
    {
        return provider;
    }
}
