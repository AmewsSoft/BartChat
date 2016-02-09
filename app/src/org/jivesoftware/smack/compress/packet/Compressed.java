// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.compress.packet;

import org.jivesoftware.smack.packet.FullStreamElement;

public class Compressed extends FullStreamElement
{

    public static final String ELEMENT = "compressed";
    public static final Compressed INSTANCE = new Compressed();
    public static final String NAMESPACE = "http://jabber.org/protocol/compress";

    private Compressed()
    {
    }

    public String getElementName()
    {
        return "compressed";
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/compress";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        return "<compressed xmlns='http://jabber.org/protocol/compress'/>";
    }

}
