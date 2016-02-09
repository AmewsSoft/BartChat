// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ItemProvider extends ExtensionElementProvider
{

    public ItemProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public Item parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        String s = xmlpullparser.getAttributeValue(null, "id");
        String s1 = xmlpullparser.getAttributeValue(null, "node");
        if (xmlpullparser.next() == 3)
        {
            return new Item(s, s1);
        }
        String s2 = xmlpullparser.getName();
        String s3 = xmlpullparser.getNamespace();
        ExtensionElementProvider extensionelementprovider = ProviderManager.getExtensionProvider(s2, s3);
        if (extensionelementprovider == null)
        {
            return new PayloadItem(s, s1, new SimplePayload(s2, s3, PacketParserUtils.parseElement(xmlpullparser, true)));
        } else
        {
            return new PayloadItem(s, s1, (ExtensionElement)extensionelementprovider.parse(xmlpullparser));
        }
    }
}
