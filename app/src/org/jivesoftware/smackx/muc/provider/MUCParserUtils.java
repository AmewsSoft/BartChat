// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.provider;

import java.io.IOException;
import org.jivesoftware.smackx.muc.MUCAffiliation;
import org.jivesoftware.smackx.muc.MUCRole;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MUCParserUtils
{

    public MUCParserUtils()
    {
    }

    public static Destroy parseDestroy(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        boolean flag = false;
        Destroy destroy = new Destroy();
        destroy.setJid(xmlpullparser.getAttributeValue("", "jid"));
        do
        {
            if (flag)
            {
                break;
            }
            int i = xmlpullparser.next();
            if (i == 2)
            {
                if (xmlpullparser.getName().equals("reason"))
                {
                    destroy.setReason(xmlpullparser.nextText());
                }
            } else
            if (i == 3 && xmlpullparser.getName().equals("destroy"))
            {
                flag = true;
            }
        } while (true);
        return destroy;
    }

    public static MUCItem parseItem(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        String s;
        String s1;
        MUCAffiliation mucaffiliation;
        String s4;
        MUCRole mucrole;
        String s5;
        int i;
        i = xmlpullparser.getDepth();
        mucaffiliation = MUCAffiliation.fromString(xmlpullparser.getAttributeValue("", "affiliation"));
        s4 = xmlpullparser.getAttributeValue("", "nick");
        mucrole = MUCRole.fromString(xmlpullparser.getAttributeValue("", "role"));
        s5 = xmlpullparser.getAttributeValue("", "jid");
        s1 = null;
        s = null;
_L1:
        String s2;
        String s3;
        s2 = s1;
        s3 = s;
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 100
    //                   2 103
    //                   3 173;
           goto _L1 _L2 _L3
_L2:
        byte byte0;
        s2 = xmlpullparser.getName();
        byte0 = -1;
        s2.hashCode();
        JVM INSTR lookupswitch 2: default 144
    //                   -934964668: 223
    //                   92645877: 208;
           goto _L4 _L5 _L6
_L4:
        byte0;
        JVM INSTR tableswitch 0 1: default 168
    //                   0 238
    //                   1 255;
           goto _L7 _L8 _L9
_L7:
        s3 = s;
        s2 = s1;
_L3:
        s1 = s2;
        s = s3;
        if (xmlpullparser.getDepth() == i)
        {
            return new MUCItem(mucaffiliation, mucrole, s2, s3, s5, s4);
        }
          goto _L1
_L6:
        if (s2.equals("actor"))
        {
            byte0 = 0;
        }
          goto _L4
_L5:
        if (s2.equals("reason"))
        {
            byte0 = 1;
        }
          goto _L4
_L8:
        s2 = xmlpullparser.getAttributeValue("", "jid");
        s3 = s;
          goto _L3
_L9:
        s3 = xmlpullparser.nextText();
        s2 = s1;
          goto _L3
    }
}
