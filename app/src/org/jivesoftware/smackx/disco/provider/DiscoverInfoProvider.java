// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DiscoverInfoProvider extends IQProvider
{

    static final boolean $assertionsDisabled;

    public DiscoverInfoProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public DiscoverInfo parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        DiscoverInfo discoverinfo = new DiscoverInfo();
        i = 0;
        String s1 = "";
        String s3 = "";
        String s4 = "";
        String s = "";
        String s2 = "";
        discoverinfo.setNode(xmlpullparser.getAttributeValue("", "node"));
        do
        {
            if (i != 0)
            {
                break;
            }
            int j = xmlpullparser.next();
            if (j == 2)
            {
                if (xmlpullparser.getName().equals("identity"))
                {
                    s1 = xmlpullparser.getAttributeValue("", "category");
                    s3 = xmlpullparser.getAttributeValue("", "name");
                    s4 = xmlpullparser.getAttributeValue("", "type");
                    s2 = xmlpullparser.getAttributeValue(xmlpullparser.getNamespace("xml"), "lang");
                } else
                if (xmlpullparser.getName().equals("feature"))
                {
                    s = xmlpullparser.getAttributeValue("", "var");
                } else
                {
                    PacketParserUtils.addExtensionElement(discoverinfo, xmlpullparser);
                }
            } else
            if (j == 3)
            {
                if (xmlpullparser.getName().equals("identity"))
                {
                    discoverinfo.addIdentity(new org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity(s1, s4, s3, s2));
                }
                if (xmlpullparser.getName().equals("feature"))
                {
                    boolean flag = discoverinfo.addFeature(s);
                    if (!$assertionsDisabled && !flag)
                    {
                        throw new AssertionError();
                    }
                }
                if (xmlpullparser.getName().equals("query"))
                {
                    i = 1;
                }
            }
        } while (true);
        return discoverinfo;
    }

    static 
    {
        boolean flag;
        if (!org/jivesoftware/smackx/disco/provider/DiscoverInfoProvider.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }
}
