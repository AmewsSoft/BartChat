// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands.packet;

import org.jivesoftware.smack.packet.ExtensionElement;

// Referenced classes of package org.jivesoftware.smackx.commands.packet:
//            AdHocCommandData

public static class condition
    implements ExtensionElement
{

    public static final String namespace = "http://jabber.org/protocol/commands";
    public org.jivesoftware.smackx.commands.tion condition;

    public org.jivesoftware.smackx.commands.tion getCondition()
    {
        return condition;
    }

    public String getElementName()
    {
        return condition.toString();
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/commands";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<").append(getElementName());
        stringbuilder.append(" xmlns=\"").append(getNamespace()).append("\"/>");
        return stringbuilder.toString();
    }

    public (org.jivesoftware.smackx.commands.tion tion)
    {
        condition = tion;
    }
}
