// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class RosterVer
    implements ExtensionElement
{

    public static final String ELEMENT = "ver";
    public static final RosterVer INSTANCE = new RosterVer();
    public static final String NAMESPACE = "urn:xmpp:features:rosterver";

    private RosterVer()
    {
    }

    public String getElementName()
    {
        return "ver";
    }

    public String getNamespace()
    {
        return "urn:xmpp:features:rosterver";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }

}
