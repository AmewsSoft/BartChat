// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl.packet;

import java.util.Map;
import org.jivesoftware.smack.packet.AbstractError;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.sasl.SASLError;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.sasl.packet:
//            SaslStreamElements

public static class saslErrorString extends AbstractError
    implements PlainStreamElement
{

    public static final String ELEMENT = "failure";
    private final SASLError saslError;
    private final String saslErrorString;

    public SASLError getSASLError()
    {
        return saslError;
    }

    public String getSASLErrorString()
    {
        return saslErrorString;
    }

    public String toString()
    {
        return toXML().toString();
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("failure").xmlnsAttribute("urn:ietf:params:xml:ns:xmpp-sasl").rightAngleBracket();
        xmlstringbuilder.emptyElement(saslErrorString);
        addDescriptiveTextsAndExtensions(xmlstringbuilder);
        xmlstringbuilder.closeElement("failure");
        return xmlstringbuilder;
    }

    public (String s)
    {
        this(s, null);
    }

    public <init>(String s, Map map)
    {
        super(map);
        map = SASLError.fromString(s);
        if (map == null)
        {
            saslError = SASLError.not_authorized;
        } else
        {
            saslError = map;
        }
        saslErrorString = s;
    }
}
