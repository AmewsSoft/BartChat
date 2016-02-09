// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            SimpleIQ, ExtensionElement

public class Session extends SimpleIQ
{
    public static class Feature
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

        public Feature(boolean flag)
        {
            optional = flag;
        }
    }


    public static final String ELEMENT = "session";
    public static final String NAMESPACE = "urn:ietf:params:xml:ns:xmpp-session";

    public Session()
    {
        super("session", "urn:ietf:params:xml:ns:xmpp-session");
        setType(IQ.Type.set);
    }
}
