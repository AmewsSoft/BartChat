// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.sharedgroups.packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SharedGroupsInfo extends IQ
{
    public static class Provider extends IQProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public SharedGroupsInfo parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException
        {
            SharedGroupsInfo sharedgroupsinfo = new SharedGroupsInfo();
            i = 0;
            do
            {
                if (i != 0)
                {
                    break;
                }
                int j = xmlpullparser.next();
                if (j == 2 && xmlpullparser.getName().equals("group"))
                {
                    sharedgroupsinfo.getGroups().add(xmlpullparser.nextText());
                } else
                if (j == 3 && xmlpullparser.getName().equals("sharedgroup"))
                {
                    i = 1;
                }
            } while (true);
            return sharedgroupsinfo;
        }

        public Provider()
        {
        }
    }


    public static final String ELEMENT = "sharedgroup";
    public static final String NAMESPACE = "http://www.jivesoftware.org/protocol/sharedgroup";
    private List groups;

    public SharedGroupsInfo()
    {
        super("sharedgroup", "http://www.jivesoftware.org/protocol/sharedgroup");
        groups = new ArrayList();
    }

    public List getGroups()
    {
        return groups;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        for (Iterator iterator = groups.iterator(); iterator.hasNext(); iqchildelementxmlstringbuilder.element("group", (String)iterator.next())) { }
        return iqchildelementxmlstringbuilder;
    }
}
