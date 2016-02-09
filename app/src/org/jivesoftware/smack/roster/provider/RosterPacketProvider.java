// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RosterPacketProvider extends IQProvider
{

    public static final RosterPacketProvider INSTANCE = new RosterPacketProvider();

    public RosterPacketProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public RosterPacket parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        Object obj;
        RosterPacket rosterpacket;
        rosterpacket = new RosterPacket();
        obj = null;
        rosterpacket.setVersion(xmlpullparser.getAttributeValue("", "ver"));
_L12:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 56
    //                   2 59
    //                   3 273;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        Object obj1;
        obj1 = xmlpullparser.getName();
        i = -1;
        ((String) (obj1)).hashCode();
        JVM INSTR lookupswitch 2: default 100
    //                   3242771: 204
    //                   98629247: 219;
           goto _L4 _L5 _L6
_L4:
        break; /* Loop/switch isn't completed */
_L5:
        if (((String) (obj1)).equals("item"))
        {
            i = 0;
        }
          goto _L7
_L6:
        if (((String) (obj1)).equals("group"))
        {
            i = 1;
        }
_L7:
        switch (i)
        {
        default:
            continue; /* Loop/switch isn't completed */

        case 0: // '\0'
            obj1 = new org.jivesoftware.smack.roster.packet.RosterPacket.Item(xmlpullparser.getAttributeValue("", "jid"), xmlpullparser.getAttributeValue("", "name"));
            ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj1)).setItemStatus(org.jivesoftware.smack.roster.packet.RosterPacket.ItemStatus.fromString(xmlpullparser.getAttributeValue("", "ask")));
            obj = xmlpullparser.getAttributeValue("", "subscription");
            if (obj == null)
            {
                obj = "none";
            }
            ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj1)).setItemType(org.jivesoftware.smack.roster.packet.RosterPacket.ItemType.valueOf(((String) (obj))));
            obj = obj1;
            continue; /* Loop/switch isn't completed */

        case 1: // '\001'
            obj1 = xmlpullparser.nextText();
            break;
        }
        if (obj1 != null && ((String) (obj1)).trim().length() > 0)
        {
            ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj)).addGroupName(((String) (obj1)));
        }
        continue; /* Loop/switch isn't completed */
_L3:
        obj1 = xmlpullparser.getName();
        i = -1;
        ((String) (obj1)).hashCode();
        JVM INSTR lookupswitch 2: default 316
    //                   3242771: 352
    //                   107944136: 367;
           goto _L8 _L9 _L10
_L8:
        switch (i)
        {
        case 0: // '\0'
            rosterpacket.addRosterItem(((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj)));
            break;

        case 1: // '\001'
            return rosterpacket;
        }
        continue; /* Loop/switch isn't completed */
_L9:
        if (((String) (obj1)).equals("item"))
        {
            i = 0;
        }
          goto _L8
_L10:
        if (((String) (obj1)).equals("query"))
        {
            i = 1;
        }
          goto _L8
        if (true) goto _L12; else goto _L11
_L11:
    }

}
