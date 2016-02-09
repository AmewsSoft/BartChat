// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class CapsExtension
    implements ExtensionElement
{

    public static final String ELEMENT = "c";
    public static final String NAMESPACE = "http://jabber.org/protocol/caps";
    private final String hash;
    private final String node;
    private final String ver;

    public CapsExtension(String s, String s1, String s2)
    {
        node = s;
        ver = s1;
        hash = s2;
    }

    public static CapsExtension from(Stanza stanza)
    {
        return (CapsExtension)stanza.getExtension("c", "http://jabber.org/protocol/caps");
    }

    public String getElementName()
    {
        return "c";
    }

    public String getHash()
    {
        return hash;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/caps";
    }

    public String getNode()
    {
        return node;
    }

    public String getVer()
    {
        return ver;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("hash", hash).attribute("node", node).attribute("ver", ver);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }
}
