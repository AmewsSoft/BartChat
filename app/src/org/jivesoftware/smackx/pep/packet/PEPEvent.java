// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pep.packet;

import org.jivesoftware.smack.packet.ExtensionElement;

// Referenced classes of package org.jivesoftware.smackx.pep.packet:
//            PEPItem

public class PEPEvent
    implements ExtensionElement
{

    PEPItem item;

    public PEPEvent()
    {
    }

    public PEPEvent(PEPItem pepitem)
    {
        item = pepitem;
    }

    public void addPEPItem(PEPItem pepitem)
    {
        item = pepitem;
    }

    public String getElementName()
    {
        return "event";
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/pubsub";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace()).append("\">");
        stringbuilder.append(item.toXML());
        stringbuilder.append("</").append(getElementName()).append(">");
        return stringbuilder.toString();
    }
}
