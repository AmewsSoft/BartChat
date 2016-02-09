// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdatalayout.provider;

import java.io.IOException;
import java.util.List;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DataLayoutProvider
{

    public DataLayoutProvider()
    {
    }

    public static DataLayout parse(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        DataLayout datalayout = new DataLayout(xmlpullparser.getAttributeValue("", "label"));
        parseLayout(datalayout.getPageLayout(), xmlpullparser);
        return datalayout;
    }

    private static org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Fieldref parseFieldref(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
        org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Fieldref fieldref = new org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Fieldref(xmlpullparser.getAttributeValue("", "var"));
        while (xmlpullparser.next() != 3 || xmlpullparser.getDepth() != i) ;
        return fieldref;
    }

    private static void parseLayout(List list, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
_L10:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 36
    //                   2 39
    //                   3 255;
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
    //                   -928989863: 182
    //                   -241484064: 196
    //                   3556653: 154
    //                   1970241253: 168;
           goto _L4 _L5 _L6 _L7 _L8
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            list.add(new org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text(xmlpullparser.nextText()));
            break;

        case 1: // '\001'
            list.add(parseSection(xmlpullparser));
            break;

        case 2: // '\002'
            list.add(parseFieldref(xmlpullparser));
            break;

        case 3: // '\003'
            list.add(new org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Reportedref());
            break;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (s.equals("text"))
        {
            byte0 = 0;
        }
          goto _L4
_L8:
        if (s.equals("section"))
        {
            byte0 = 1;
        }
          goto _L4
_L5:
        if (s.equals("fieldref"))
        {
            byte0 = 2;
        }
          goto _L4
_L6:
        if (s.equals("reportedref"))
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

    private static org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Section parseSection(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Section section = new org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Section(xmlpullparser.getAttributeValue("", "label"));
        parseLayout(section.getSectionLayout(), xmlpullparser);
        return section;
    }
}
