// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.attention.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AttentionExtension
    implements ExtensionElement
{
    public static class Provider extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public AttentionExtension parse(XmlPullParser xmlpullparser, int i)
        {
            return new AttentionExtension();
        }

        public Provider()
        {
        }
    }


    public static final String ELEMENT_NAME = "attention";
    public static final String NAMESPACE = "urn:xmpp:attention:0";

    public AttentionExtension()
    {
    }

    public String getElementName()
    {
        return "attention";
    }

    public String getNamespace()
    {
        return "urn:xmpp:attention:0";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace()).append("\"/>");
        return stringbuilder.toString();
    }
}
