// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl.packet;

import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.sasl.packet:
//            SaslStreamElements

public static class 
    implements PlainStreamElement
{

    public static final String ELEMENT = "response";
    private final String authenticationText;

    public String getAuthenticationText()
    {
        return authenticationText;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("response").xmlnsAttribute("urn:ietf:params:xml:ns:xmpp-sasl").rightAngleBracket();
        xmlstringbuilder.optAppend(authenticationText);
        xmlstringbuilder.closeElement("response");
        return xmlstringbuilder;
    }

    public ()
    {
        authenticationText = null;
    }

    public authenticationText(String s)
    {
        authenticationText = StringUtils.returnIfNotEmptyTrimmed(s);
    }
}
