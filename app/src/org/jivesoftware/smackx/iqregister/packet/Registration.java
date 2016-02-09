// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqregister.packet;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;

public class Registration extends IQ
{
    public static class Feature
        implements ExtensionElement
    {

        public static final String ELEMENT = "register";
        public static final Feature INSTANCE = new Feature();
        public static final String NAMESPACE = "http://jabber.org/features/iq-register";

        public String getElementName()
        {
            return "register";
        }

        public String getNamespace()
        {
            return "http://jabber.org/features/iq-register";
        }

        public CharSequence toXML()
        {
            return "<register xmlns='http://jabber.org/features/iq-register'/>";
        }


        private Feature()
        {
        }
    }


    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:register";
    private final Map attributes;
    private final String instructions;

    public Registration()
    {
        this(null);
    }

    public Registration(String s, Map map)
    {
        super("query", "jabber:iq:register");
        instructions = s;
        attributes = map;
    }

    public Registration(Map map)
    {
        this(null, map);
    }

    public Map getAttributes()
    {
        return attributes;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        iqchildelementxmlstringbuilder.optElement("instructions", instructions);
        if (attributes != null && attributes.size() > 0)
        {
            String s;
            for (Iterator iterator = attributes.keySet().iterator(); iterator.hasNext(); iqchildelementxmlstringbuilder.element(s, (String)attributes.get(s)))
            {
                s = (String)iterator.next();
            }

        }
        return iqchildelementxmlstringbuilder;
    }

    public String getInstructions()
    {
        return instructions;
    }
}
