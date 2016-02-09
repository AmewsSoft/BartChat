// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.privacy.provider;

import java.io.IOException;
import java.util.ArrayList;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.ParserUtils;
import org.jivesoftware.smackx.privacy.packet.Privacy;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PrivacyProvider extends IQProvider
{

    public PrivacyProvider()
    {
    }

    private static PrivacyItem parseItem(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        Object obj;
        String s;
        byte byte0;
        long l;
        s = xmlpullparser.getAttributeValue("", "action");
        l = ParserUtils.getLongAttribute(xmlpullparser, "order").longValue();
        obj = xmlpullparser.getAttributeValue("", "type");
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 2: default 64
    //                   3079692: 134
    //                   92906313: 120;
           goto _L1 _L2 _L3
_L1:
        byte0;
        JVM INSTR tableswitch 0 1: default 88
    //                   0 148
    //                   1 190;
           goto _L4 _L5 _L6
_L4:
        throw new SmackException((new StringBuilder()).append("Unkown action value '").append(s).append("'").toString());
_L3:
        if (s.equals("allow"))
        {
            byte0 = 0;
        }
          goto _L1
_L2:
        if (s.equals("deny"))
        {
            byte0 = 1;
        }
          goto _L1
_L5:
        boolean flag = true;
_L7:
        if (obj != null)
        {
            String s1 = xmlpullparser.getAttributeValue("", "value");
            obj = new PrivacyItem(org.jivesoftware.smackx.privacy.packet.PrivacyItem.Type.valueOf(((String) (obj))), s1, flag, l);
        } else
        {
            obj = new PrivacyItem(flag, l);
        }
        parseItemChildElements(xmlpullparser, ((PrivacyItem) (obj)));
        return ((PrivacyItem) (obj));
_L6:
        flag = false;
          goto _L7
    }

    private static void parseItemChildElements(XmlPullParser xmlpullparser, PrivacyItem privacyitem)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
_L10:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 36
    //                   2 39
    //                   3 219;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 4: default 96
    //                   -1240091849: 167
    //                   3368: 139
    //                   211864444: 181
    //                   954925063: 153;
           goto _L4 _L5 _L6 _L7 _L8
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            privacyitem.setFilterIQ(true);
            break;

        case 1: // '\001'
            privacyitem.setFilterMessage(true);
            break;

        case 2: // '\002'
            privacyitem.setFilterPresenceIn(true);
            break;

        case 3: // '\003'
            privacyitem.setFilterPresenceOut(true);
            break;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (s.equals("iq"))
        {
            byte0 = 0;
        }
          goto _L4
_L8:
        if (s.equals("message"))
        {
            byte0 = 1;
        }
          goto _L4
_L5:
        if (s.equals("presence-in"))
        {
            byte0 = 2;
        }
          goto _L4
_L7:
        if (s.equals("presence-out"))
        {
            byte0 = 3;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L10; else goto _L9
_L9:
    }

    private static void parseList(XmlPullParser xmlpullparser, Privacy privacy)
        throws XmlPullParserException, IOException, SmackException
    {
        boolean flag = false;
        String s = xmlpullparser.getAttributeValue("", "name");
        ArrayList arraylist = new ArrayList();
        do
        {
            if (flag)
            {
                break;
            }
            int i = xmlpullparser.next();
            if (i == 2)
            {
                if (xmlpullparser.getName().equals("item"))
                {
                    arraylist.add(parseItem(xmlpullparser));
                }
            } else
            if (i == 3 && xmlpullparser.getName().equals("list"))
            {
                flag = true;
            }
        } while (true);
        privacy.setPrivacyList(s, arraylist);
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public Privacy parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        Privacy privacy = new Privacy();
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
                if (xmlpullparser.getName().equals("active"))
                {
                    String s = xmlpullparser.getAttributeValue("", "name");
                    if (s == null)
                    {
                        privacy.setDeclineActiveList(true);
                    } else
                    {
                        privacy.setActiveName(s);
                    }
                } else
                if (xmlpullparser.getName().equals("default"))
                {
                    String s1 = xmlpullparser.getAttributeValue("", "name");
                    if (s1 == null)
                    {
                        privacy.setDeclineDefaultList(true);
                    } else
                    {
                        privacy.setDefaultName(s1);
                    }
                } else
                if (xmlpullparser.getName().equals("list"))
                {
                    parseList(xmlpullparser, privacy);
                }
            } else
            if (j == 3 && xmlpullparser.getName().equals("query"))
            {
                i = 1;
            }
        } while (true);
        return privacy;
    }
}
