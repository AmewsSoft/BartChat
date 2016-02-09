// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;


// Referenced classes of package org.jivesoftware.smack.packet:
//            IQ, ExtensionElement

public class Bind extends IQ
{
    public static class Feature
        implements ExtensionElement
    {

        public static final Feature INSTANCE = new Feature();

        public String getElementName()
        {
            return "bind";
        }

        public String getNamespace()
        {
            return "urn:ietf:params:xml:ns:xmpp-bind";
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public String toXML()
        {
            return "<bind xmlns='urn:ietf:params:xml:ns:xmpp-bind'/>";
        }


        private Feature()
        {
        }
    }


    public static final String ELEMENT = "bind";
    public static final String NAMESPACE = "urn:ietf:params:xml:ns:xmpp-bind";
    private final String jid;
    private final String resource;

    public Bind(String s, String s1)
    {
        super("bind", "urn:ietf:params:xml:ns:xmpp-bind");
        resource = s;
        jid = s1;
    }

    public static Bind newResult(String s)
    {
        return new Bind(null, s);
    }

    public static Bind newSet(String s)
    {
        s = new Bind(s, null);
        s.setType(IQ.Type.set);
        return s;
    }

    protected IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        iqchildelementxmlstringbuilder.optElement("resource", resource);
        iqchildelementxmlstringbuilder.optElement("jid", jid);
        return iqchildelementxmlstringbuilder;
    }

    public String getJid()
    {
        return jid;
    }

    public String getResource()
    {
        return resource;
    }
}
