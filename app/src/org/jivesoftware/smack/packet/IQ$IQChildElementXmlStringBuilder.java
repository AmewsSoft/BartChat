// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            IQ, ExtensionElement

public static class <init> extends XmlStringBuilder
{

    private final String element;
    private boolean isEmptyElement;

    public void setEmptyElement()
    {
        isEmptyElement = true;
    }



    private (String s, String s1)
    {
        prelude(s, s1);
        element = s;
    }

    public element(ExtensionElement extensionelement)
    {
        this(extensionelement.getElementName(), extensionelement.getNamespace());
    }

    private <init>(IQ iq)
    {
        this(iq.getChildElementName(), iq.getChildElementNamespace());
    }

    <init>(IQ iq, <init> <init>1)
    {
        this(iq);
    }
}
