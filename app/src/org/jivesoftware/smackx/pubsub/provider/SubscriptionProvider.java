// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.pubsub.Subscription;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SubscriptionProvider extends ExtensionElementProvider
{

    public SubscriptionProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public Subscription parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        Object obj = null;
        String s = xmlpullparser.getAttributeValue(null, "jid");
        String s1 = xmlpullparser.getAttributeValue(null, "node");
        String s2 = xmlpullparser.getAttributeValue(null, "subid");
        String s3 = xmlpullparser.getAttributeValue(null, "subscription");
        boolean flag = false;
        boolean flag3 = false;
        boolean flag2 = flag;
        if (xmlpullparser.next() == 2)
        {
            flag2 = flag;
            if (xmlpullparser.getName().equals("subscribe-options"))
            {
                int j = xmlpullparser.next();
                boolean flag1 = flag3;
                i = j;
                if (j == 2)
                {
                    flag1 = flag3;
                    i = j;
                    if (xmlpullparser.getName().equals("required"))
                    {
                        flag1 = true;
                        i = j;
                    }
                }
                do
                {
                    flag2 = flag1;
                    if (i == 3)
                    {
                        break;
                    }
                    flag2 = flag1;
                    if (xmlpullparser.getName().equals("subscribe-options"))
                    {
                        break;
                    }
                    i = xmlpullparser.next();
                } while (true);
            }
        }
        for (; xmlpullparser.getEventType() != 3; xmlpullparser.next()) { }
        if (s3 == null)
        {
            xmlpullparser = obj;
        } else
        {
            xmlpullparser = org.jivesoftware.smackx.pubsub.Subscription.State.valueOf(s3);
        }
        return new Subscription(s, s1, s2, xmlpullparser, flag2);
    }
}
