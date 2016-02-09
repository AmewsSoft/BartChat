// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.pubsub.packet.PubSub;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PubSubProvider extends IQProvider
{

    public PubSubProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public PubSub parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        PubSub pubsub = new PubSub(PubSubNamespace.valueOfFromXmlns(xmlpullparser.getNamespace()));
        do
        {
label0:
            do
            {
                switch (xmlpullparser.next())
                {
                default:
                    break;

                case 3: // '\003'
                    break label0;

                case 2: // '\002'
                    PacketParserUtils.addExtensionElement(pubsub, xmlpullparser);
                    break;
                }
            } while (true);
        } while (xmlpullparser.getDepth() != i);
        return pubsub;
    }
}
