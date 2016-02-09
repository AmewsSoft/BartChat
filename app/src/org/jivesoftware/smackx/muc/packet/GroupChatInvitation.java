// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class GroupChatInvitation
    implements ExtensionElement
{
    public static class Provider extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public GroupChatInvitation parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException
        {
            String s = xmlpullparser.getAttributeValue("", "jid");
            xmlpullparser.next();
            return new GroupChatInvitation(s);
        }

        public Provider()
        {
        }
    }


    public static final String ELEMENT = "x";
    public static final String NAMESPACE = "jabber:x:conference";
    private final String roomAddress;

    public GroupChatInvitation(String s)
    {
        roomAddress = s;
    }

    public static GroupChatInvitation from(Stanza stanza)
    {
        return (GroupChatInvitation)stanza.getExtension("x", "jabber:x:conference");
    }

    public static GroupChatInvitation getFrom(Stanza stanza)
    {
        return from(stanza);
    }

    public String getElementName()
    {
        return "x";
    }

    public String getNamespace()
    {
        return "jabber:x:conference";
    }

    public String getRoomAddress()
    {
        return roomAddress;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("jid", getRoomAddress());
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }
}
