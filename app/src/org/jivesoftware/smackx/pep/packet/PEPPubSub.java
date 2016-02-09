// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pep.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.pep.packet:
//            PEPItem

public class PEPPubSub extends IQ
{

    public static final String ELEMENT = "pubsub";
    public static final String NAMESPACE = "http://jabber.org/protocol/pubsub";
    private final PEPItem item;

    public PEPPubSub(PEPItem pepitem)
    {
        super("pubsub", "http://jabber.org/protocol/pubsub");
        item = pepitem;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        iqchildelementxmlstringbuilder.openElement("publish").attribute("node", item.getNode()).rightAngleBracket();
        iqchildelementxmlstringbuilder.append(item.toXML());
        iqchildelementxmlstringbuilder.closeElement("publish");
        return iqchildelementxmlstringbuilder;
    }
}
