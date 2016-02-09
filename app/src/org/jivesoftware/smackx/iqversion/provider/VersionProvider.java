// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqversion.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.iqversion.packet.Version;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VersionProvider extends IQProvider
{

    public VersionProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public Version parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        String s;
        String s1;
        String s2;
        s2 = null;
        s = null;
        s1 = null;
_L9:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 36
    //                   2 39
    //                   3 199;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s3;
        byte byte0;
        s3 = xmlpullparser.getName();
        byte0 = -1;
        s3.hashCode();
        JVM INSTR lookupswitch 3: default 88
    //                   3556: 162
    //                   3373707: 130
    //                   351608024: 146;
           goto _L4 _L5 _L6 _L7
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            s2 = xmlpullparser.nextText();
            break;

        case 1: // '\001'
            s = xmlpullparser.nextText();
            break;

        case 2: // '\002'
            s1 = xmlpullparser.nextText();
            break;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (s3.equals("name"))
        {
            byte0 = 0;
        }
          goto _L4
_L7:
        if (s3.equals("version"))
        {
            byte0 = 1;
        }
          goto _L4
_L5:
        if (s3.equals("os"))
        {
            byte0 = 2;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i && xmlpullparser.getName().equals("query"))
        {
            if (s2 == null && s == null && s1 == null)
            {
                return new Version();
            } else
            {
                return new Version(s2, s, s1);
            }
        }
        if (true) goto _L9; else goto _L8
_L8:
    }
}
