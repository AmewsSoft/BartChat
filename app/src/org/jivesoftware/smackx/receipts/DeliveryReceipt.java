// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.receipts;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class DeliveryReceipt
    implements ExtensionElement
{
    public static class Provider extends EmbeddedExtensionProvider
    {

        protected volatile ExtensionElement createReturnExtension(String s, String s1, Map map, List list)
        {
            return createReturnExtension(s, s1, map, list);
        }

        protected DeliveryReceipt createReturnExtension(String s, String s1, Map map, List list)
        {
            return new DeliveryReceipt((String)map.get("id"));
        }

        public Provider()
        {
        }
    }


    public static final String ELEMENT = "received";
    public static final String NAMESPACE = "urn:xmpp:receipts";
    private final String id;

    public DeliveryReceipt(String s)
    {
        id = s;
    }

    public static DeliveryReceipt from(Message message)
    {
        return (DeliveryReceipt)message.getExtension("received", "urn:xmpp:receipts");
    }

    public static DeliveryReceipt getFrom(Message message)
    {
        return from(message);
    }

    public String getElementName()
    {
        return "received";
    }

    public String getId()
    {
        return id;
    }

    public String getNamespace()
    {
        return "urn:xmpp:receipts";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("id", id);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }
}
