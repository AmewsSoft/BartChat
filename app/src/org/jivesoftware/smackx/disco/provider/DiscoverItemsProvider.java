// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DiscoverItemsProvider extends IQProvider
{

    public DiscoverItemsProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public DiscoverItems parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        DiscoverItems discoveritems = new DiscoverItems();
        i = 0;
        String s1 = "";
        String s2 = "";
        String s = "";
        String s3 = "";
        discoveritems.setNode(xmlpullparser.getAttributeValue("", "node"));
        do
        {
            if (i != 0)
            {
                break;
            }
            int j = xmlpullparser.next();
            if (j == 2 && "item".equals(xmlpullparser.getName()))
            {
                s1 = xmlpullparser.getAttributeValue("", "jid");
                s2 = xmlpullparser.getAttributeValue("", "name");
                s3 = xmlpullparser.getAttributeValue("", "node");
                s = xmlpullparser.getAttributeValue("", "action");
            } else
            if (j == 3 && "item".equals(xmlpullparser.getName()))
            {
                org.jivesoftware.smackx.disco.packet.DiscoverItems.Item item = new org.jivesoftware.smackx.disco.packet.DiscoverItems.Item(s1);
                item.setName(s2);
                item.setNode(s3);
                item.setAction(s);
                discoveritems.addItem(item);
            } else
            if (j == 3 && "query".equals(xmlpullparser.getName()))
            {
                i = 1;
            }
        } while (true);
        return discoveritems;
    }
}
