// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.packet.ExtensionElement;

public class SimplePayload
    implements ExtensionElement
{

    private final String elemName;
    private final String ns;
    private final CharSequence payload;

    public SimplePayload(String s, String s1, CharSequence charsequence)
    {
        elemName = s;
        payload = charsequence;
        ns = s1;
    }

    public String getElementName()
    {
        return elemName;
    }

    public String getNamespace()
    {
        return ns;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append("payload [").append(toXML()).append("]").toString();
    }

    public CharSequence toXML()
    {
        return payload;
    }
}
