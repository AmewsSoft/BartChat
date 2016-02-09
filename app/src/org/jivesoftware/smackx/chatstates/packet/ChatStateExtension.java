// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.chatstates.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.chatstates.ChatState;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ChatStateExtension
    implements ExtensionElement
{
    public static class Provider extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public ChatStateExtension parse(XmlPullParser xmlpullparser, int i)
        {
            try
            {
                xmlpullparser = ChatState.valueOf(xmlpullparser.getName());
            }
            // Misplaced declaration of an exception variable
            catch (XmlPullParser xmlpullparser)
            {
                xmlpullparser = ChatState.active;
            }
            return new ChatStateExtension(xmlpullparser);
        }

        public Provider()
        {
        }
    }


    public static final String NAMESPACE = "http://jabber.org/protocol/chatstates";
    private final ChatState state;

    public ChatStateExtension(ChatState chatstate)
    {
        state = chatstate;
    }

    public ChatState getChatState()
    {
        return state;
    }

    public String getElementName()
    {
        return state.name();
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/chatstates";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }
}
