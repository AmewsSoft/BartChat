// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.offline.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OfflineMessageInfo
    implements ExtensionElement
{
    public static class Provider extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public OfflineMessageInfo parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException
        {
            OfflineMessageInfo offlinemessageinfo = new OfflineMessageInfo();
            i = 0;
            do
            {
                if (i != 0)
                {
                    break;
                }
                int j = xmlpullparser.next();
                if (j == 2)
                {
                    if (xmlpullparser.getName().equals("item"))
                    {
                        offlinemessageinfo.setNode(xmlpullparser.getAttributeValue("", "node"));
                    }
                } else
                if (j == 3 && xmlpullparser.getName().equals("offline"))
                {
                    i = 1;
                }
            } while (true);
            return offlinemessageinfo;
        }

        public Provider()
        {
        }
    }


    private String node;

    public OfflineMessageInfo()
    {
        node = null;
    }

    public String getElementName()
    {
        return "offline";
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/offline";
    }

    public String getNode()
    {
        return node;
    }

    public void setNode(String s)
    {
        node = s;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace()).append("\">");
        if (getNode() != null)
        {
            stringbuilder.append("<item node=\"").append(getNode()).append("\"/>");
        }
        stringbuilder.append("</").append(getElementName()).append(">");
        return stringbuilder.toString();
    }
}
