// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pep.packet;

import org.jivesoftware.smack.packet.ExtensionElement;

public abstract class PEPItem
    implements ExtensionElement
{

    String id;

    public PEPItem(String s)
    {
        id = s;
    }

    public String getElementName()
    {
        return "item";
    }

    abstract String getItemDetailsXML();

    public String getNamespace()
    {
        return "http://jabber.org/protocol/pubsub";
    }

    abstract String getNode();

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<").append(getElementName()).append(" id=\"").append(id).append("\">");
        stringbuilder.append(getItemDetailsXML());
        stringbuilder.append("</").append(getElementName()).append(">");
        return stringbuilder.toString();
    }
}
