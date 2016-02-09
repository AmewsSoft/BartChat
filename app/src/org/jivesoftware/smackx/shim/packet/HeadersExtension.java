// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.shim.packet;

import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class HeadersExtension
    implements ExtensionElement
{

    public static final String ELEMENT = "headers";
    public static final String NAMESPACE = "http://jabber.org/protocol/shim";
    private final List headers;

    public HeadersExtension(List list)
    {
        if (list != null)
        {
            headers = Collections.unmodifiableList(list);
            return;
        } else
        {
            headers = Collections.emptyList();
            return;
        }
    }

    public static HeadersExtension from(Stanza stanza)
    {
        return (HeadersExtension)stanza.getExtension("headers", "http://jabber.org/protocol/shim");
    }

    public String getElementName()
    {
        return "headers";
    }

    public List getHeaders()
    {
        return headers;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/shim";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.append(headers);
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
