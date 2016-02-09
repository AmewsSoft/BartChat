// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl.packet;

import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.sasl.packet:
//            SaslStreamElements

public static class authenticationText
    implements PlainStreamElement
{

    public static final String ELEMENT = "auth";
    private final String authenticationText;
    private final String mechanism;

    public String getAuthenticationText()
    {
        return authenticationText;
    }

    public String getMechanism()
    {
        return mechanism;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("auth").xmlnsAttribute("urn:ietf:params:xml:ns:xmpp-sasl").attribute("mechanism", mechanism).rightAngleBracket();
        xmlstringbuilder.optAppend(authenticationText);
        xmlstringbuilder.closeElement("auth");
        return xmlstringbuilder;
    }

    public I(String s, String s1)
    {
        mechanism = (String)Objects.requireNonNull(s, "SASL mechanism shouldn't be null.");
        authenticationText = (String)StringUtils.requireNotNullOrEmpty(s1, "SASL authenticationText must not be null or empty (RFC6120 6.4.2)");
    }
}
