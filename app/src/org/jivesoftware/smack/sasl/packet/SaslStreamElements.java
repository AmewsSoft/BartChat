// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl.packet;

import java.util.Map;
import org.jivesoftware.smack.packet.AbstractError;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.sasl.SASLError;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class SaslStreamElements
{
    public static class AuthMechanism
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

        public AuthMechanism(String s, String s1)
        {
            mechanism = (String)Objects.requireNonNull(s, "SASL mechanism shouldn't be null.");
            authenticationText = (String)StringUtils.requireNotNullOrEmpty(s1, "SASL authenticationText must not be null or empty (RFC6120 6.4.2)");
        }
    }

    public static class Challenge
        implements PlainStreamElement
    {

        public static final String ELEMENT = "challenge";
        private final String data;

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = (new XmlStringBuilder()).halfOpenElement("challenge").xmlnsAttribute("urn:ietf:params:xml:ns:xmpp-sasl").rightAngleBracket();
            xmlstringbuilder.optAppend(data);
            xmlstringbuilder.closeElement("challenge");
            return xmlstringbuilder;
        }

        public Challenge(String s)
        {
            data = StringUtils.returnIfNotEmptyTrimmed(s);
        }
    }

    public static class Response
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

        public Response()
        {
            authenticationText = null;
        }

        public Response(String s)
        {
            authenticationText = StringUtils.returnIfNotEmptyTrimmed(s);
        }
    }

    public static class SASLFailure extends AbstractError
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

        public SASLFailure(String s)
        {
            this(s, null);
        }

        public SASLFailure(String s, Map map)
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

    public static class Success
        implements PlainStreamElement
    {

        public static final String ELEMENT = "success";
        private final String data;

        public String getData()
        {
            return data;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
            xmlstringbuilder.halfOpenElement("success").xmlnsAttribute("urn:ietf:params:xml:ns:xmpp-sasl").rightAngleBracket();
            xmlstringbuilder.optAppend(data);
            xmlstringbuilder.closeElement("success");
            return xmlstringbuilder;
        }

        public Success(String s)
        {
            data = StringUtils.returnIfNotEmptyTrimmed(s);
        }
    }


    public static final String NAMESPACE = "urn:ietf:params:xml:ns:xmpp-sasl";

    public SaslStreamElements()
    {
    }
}
