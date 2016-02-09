// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.shim.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class Header
    implements ExtensionElement
{

    public static final String ELEMENT = "header";
    private final String name;
    private final String value;

    public Header(String s, String s1)
    {
        name = s;
        value = s1;
    }

    public String getElementName()
    {
        return "header";
    }

    public String getName()
    {
        return name;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/shim";
    }

    public String getValue()
    {
        return value;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("name", name);
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.escape(value);
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
