// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.offline.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.offline.packet:
//            OfflineMessageRequest

public static class  extends IQProvider
{

    private  parseItem(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        boolean flag = false;
          = new t>(xmlpullparser.getAttributeValue("", "node"));
        .ction(xmlpullparser.getAttributeValue("", "action"));
        .id(xmlpullparser.getAttributeValue("", "jid"));
        do
        {
            if (flag)
            {
                break;
            }
            if (xmlpullparser.next() == 3 && xmlpullparser.getName().equals("item"))
            {
                flag = true;
            }
        } while (true);
        return ;
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public OfflineMessageRequest parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        OfflineMessageRequest offlinemessagerequest = new OfflineMessageRequest();
        i = 0;
        do
        {
            if (i != 0)
            {
                break;
            }
            int j = xmlpullparser.next();
            if (j == 2)
            {
                if (xmlpullparser.getName().equals("item"))
                {
                    offlinemessagerequest.addItem(parseItem(xmlpullparser));
                } else
                if (xmlpullparser.getName().equals("purge"))
                {
                    offlinemessagerequest.setPurge(true);
                } else
                if (xmlpullparser.getName().equals("fetch"))
                {
                    offlinemessagerequest.setFetch(true);
                }
            } else
            if (j == 3 && xmlpullparser.getName().equals("offline"))
            {
                i = 1;
            }
        } while (true);
        return offlinemessagerequest;
    }

    public ()
    {
    }
}
