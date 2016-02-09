// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.rsm.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.ParserUtils;
import org.jivesoftware.smackx.rsm.packet.RSMSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RSMSetProvider extends ExtensionElementProvider
{

    public RSMSetProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public RSMSet parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        String s;
        String s1;
        String s2;
        String s3;
        int j;
        int k;
        int l;
        int i1;
        s3 = null;
        s2 = null;
        i1 = -1;
        l = -1;
        s1 = null;
        k = -1;
        s = null;
        j = -1;
_L21:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 52
    //                   2 55
    //                   3 379;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s4 = xmlpullparser.getName();
        s4.hashCode();
        JVM INSTR lookupswitch 7: default 136
    //                   -1392885889: 214
    //                   107876: 294
    //                   3314326: 278
    //                   92734940: 198
    //                   94851343: 230
    //                   97440432: 246
    //                   100346066: 262;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L4:
        byte byte0 = -1;
_L13:
        switch (byte0)
        {
        case 0: // '\0'
            s3 = xmlpullparser.nextText();
            break;

        case 1: // '\001'
            s2 = xmlpullparser.nextText();
            break;

        case 2: // '\002'
            i1 = ParserUtils.getIntegerFromNextText(xmlpullparser);
            break;

        case 3: // '\003'
            j = ParserUtils.getIntegerAttribute(xmlpullparser, "index", -1);
            s = xmlpullparser.nextText();
            break;

        case 4: // '\004'
            l = ParserUtils.getIntegerFromNextText(xmlpullparser);
            break;

        case 5: // '\005'
            s1 = xmlpullparser.nextText();
            break;

        case 6: // '\006'
            k = ParserUtils.getIntegerFromNextText(xmlpullparser);
            break;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if (!s4.equals("after")) goto _L4; else goto _L12
_L12:
        byte0 = 0;
          goto _L13
_L5:
        if (!s4.equals("before")) goto _L4; else goto _L14
_L14:
        byte0 = 1;
          goto _L13
_L9:
        if (!s4.equals("count")) goto _L4; else goto _L15
_L15:
        byte0 = 2;
          goto _L13
_L10:
        if (!s4.equals("first")) goto _L4; else goto _L16
_L16:
        byte0 = 3;
          goto _L13
_L11:
        if (!s4.equals("index")) goto _L4; else goto _L17
_L17:
        byte0 = 4;
          goto _L13
_L7:
        if (!s4.equals("last")) goto _L4; else goto _L18
_L18:
        byte0 = 5;
          goto _L13
_L6:
        if (!s4.equals("max")) goto _L4; else goto _L19
_L19:
        byte0 = 6;
          goto _L13
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            return new RSMSet(s3, s2, i1, l, s1, k, s, j);
        }
        if (true) goto _L21; else goto _L20
_L20:
    }
}
