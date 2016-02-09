// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.receipts;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.id.StanzaIdUtil;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DeliveryReceiptRequest
    implements ExtensionElement
{
    public static class Provider extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public DeliveryReceiptRequest parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException
        {
            return new DeliveryReceiptRequest();
        }

        public Provider()
        {
        }
    }


    public static final String ELEMENT = "request";

    public DeliveryReceiptRequest()
    {
    }

    public static String addTo(Message message)
    {
        if (message.getStanzaId() == null)
        {
            message.setStanzaId(StanzaIdUtil.newStanzaId());
        }
        message.addExtension(new DeliveryReceiptRequest());
        return message.getStanzaId();
    }

    public static DeliveryReceiptRequest from(Stanza stanza)
    {
        return (DeliveryReceiptRequest)stanza.getExtension("request", "urn:xmpp:receipts");
    }

    public static DeliveryReceiptRequest getFrom(Stanza stanza)
    {
        return from(stanza);
    }

    public String getElementName()
    {
        return "request";
    }

    public String getNamespace()
    {
        return "urn:xmpp:receipts";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        return "<request xmlns='urn:xmpp:receipts'/>";
    }
}
