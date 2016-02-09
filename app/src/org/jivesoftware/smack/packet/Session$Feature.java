// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            ExtensionElement, Session

public static class optional
    implements ExtensionElement
{

    public static final String OPTIONAL_ELEMENT = "optional";
    private final boolean optional;

    public String getElementName()
    {
        return "session";
    }

    public String getNamespace()
    {
        return "urn:ietf:params:xml:ns:xmpp-session";
    }

    public boolean isOptional()
    {
        return optional;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        if (optional)
        {
            xmlstringbuilder.rightAngleBracket();
            xmlstringbuilder.emptyElement("optional");
            xmlstringbuilder.closeElement(this);
            return xmlstringbuilder;
        } else
        {
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }
    }

    public (boolean flag)
    {
        optional = flag;
    }
}
