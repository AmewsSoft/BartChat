// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.pubsub.PubSubElementType;

// Referenced classes of package org.jivesoftware.smackx.pubsub.packet:
//            PubSubNamespace

public class PubSub extends IQ
{

    public static final String ELEMENT = "pubsub";
    public static final String NAMESPACE = "http://jabber.org/protocol/pubsub";

    public PubSub()
    {
        super("pubsub", "http://jabber.org/protocol/pubsub");
    }

    public PubSub(String s, org.jivesoftware.smack.packet.IQ.Type type, PubSubNamespace pubsubnamespace)
    {
        PubSubNamespace pubsubnamespace1 = pubsubnamespace;
        if (pubsubnamespace == null)
        {
            pubsubnamespace1 = PubSubNamespace.BASIC;
        }
        super("pubsub", pubsubnamespace1.getXmlns());
        setTo(s);
        setType(type);
    }

    public PubSub(PubSubNamespace pubsubnamespace)
    {
        super("pubsub", pubsubnamespace.getXmlns());
    }

    public static PubSub createPubsubPacket(String s, org.jivesoftware.smack.packet.IQ.Type type, ExtensionElement extensionelement, PubSubNamespace pubsubnamespace)
    {
        s = new PubSub(s, type, pubsubnamespace);
        s.addExtension(extensionelement);
        return s;
    }

    public String getElementName()
    {
        return "pubsub";
    }

    public ExtensionElement getExtension(PubSubElementType pubsubelementtype)
    {
        return getExtension(pubsubelementtype.getElementName(), pubsubelementtype.getNamespace().getXmlns());
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        return iqchildelementxmlstringbuilder;
    }
}
