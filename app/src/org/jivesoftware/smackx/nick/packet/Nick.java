// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.nick.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class Nick
    implements ExtensionElement
{
    public static class Provider extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public Nick parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException
        {
            xmlpullparser.next();
            String s = xmlpullparser.getText();
            for (; xmlpullparser.getEventType() != 3; xmlpullparser.next()) { }
            return new Nick(s);
        }

        public Provider()
        {
        }
    }


    public static final String ELEMENT_NAME = "nick";
    public static final String NAMESPACE = "http://jabber.org/protocol/nick";
    private String name;

    public Nick(String s)
    {
        name = null;
        name = s;
    }

    public String getElementName()
    {
        return "nick";
    }

    public String getName()
    {
        return name;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/nick";
    }

    public void setName(String s)
    {
        name = s;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<").append("nick").append(" xmlns=\"").append("http://jabber.org/protocol/nick").append("\">");
        stringbuilder.append(getName());
        stringbuilder.append("</").append("nick").append('>');
        return stringbuilder.toString();
    }
}
