// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            FullStreamElement

public class StartTls extends FullStreamElement
{

    public static final String ELEMENT = "starttls";
    public static final String NAMESPACE = "urn:ietf:params:xml:ns:xmpp-tls";
    private final boolean required;

    public StartTls()
    {
        this(false);
    }

    public StartTls(boolean flag)
    {
        required = flag;
    }

    public String getElementName()
    {
        return "starttls";
    }

    public String getNamespace()
    {
        return "urn:ietf:params:xml:ns:xmpp-tls";
    }

    public boolean required()
    {
        return required;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.condEmptyElement(required, "required");
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
