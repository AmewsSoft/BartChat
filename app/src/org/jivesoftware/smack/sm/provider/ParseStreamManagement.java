// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.provider;

import java.io.IOException;
import org.jivesoftware.smack.util.ParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ParseStreamManagement
{

    public ParseStreamManagement()
    {
    }

    public static org.jivesoftware.smack.sm.packet.StreamManagement.AckAnswer ackAnswer(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        ParserUtils.assertAtStartTag(xmlpullparser);
        long l = ParserUtils.getLongAttribute(xmlpullparser, "h").longValue();
        xmlpullparser.next();
        ParserUtils.assertAtEndTag(xmlpullparser);
        return new org.jivesoftware.smack.sm.packet.StreamManagement.AckAnswer(l);
    }

    public static org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest ackRequest(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        ParserUtils.assertAtStartTag(xmlpullparser);
        xmlpullparser.next();
        ParserUtils.assertAtEndTag(xmlpullparser);
        return org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest.INSTANCE;
    }

    public static org.jivesoftware.smack.sm.packet.StreamManagement.Enabled enabled(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        ParserUtils.assertAtStartTag(xmlpullparser);
        boolean flag = ParserUtils.getBooleanAttribute(xmlpullparser, "resume", false);
        String s = xmlpullparser.getAttributeValue("", "id");
        String s1 = xmlpullparser.getAttributeValue("", "location");
        int i = ParserUtils.getIntegerAttribute(xmlpullparser, "max", -1);
        xmlpullparser.next();
        ParserUtils.assertAtEndTag(xmlpullparser);
        return new org.jivesoftware.smack.sm.packet.StreamManagement.Enabled(s, flag, s1, i);
    }

    public static org.jivesoftware.smack.sm.packet.StreamManagement.Failed failed(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        org.jivesoftware.smack.packet.XMPPError.Condition condition;
        ParserUtils.assertAtStartTag(xmlpullparser);
        condition = null;
_L5:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 36
    //                   2 39
    //                   3 68;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L3:
        continue; /* Loop/switch isn't completed */
_L2:
        String s = xmlpullparser.getName();
        if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(xmlpullparser.getNamespace()))
        {
            condition = org.jivesoftware.smack.packet.XMPPError.Condition.fromString(s);
        }
        if (true) goto _L5; else goto _L4
_L4:
        if (!"failed".equals(xmlpullparser.getName())) goto _L5; else goto _L6
_L6:
        ParserUtils.assertAtEndTag(xmlpullparser);
        return new org.jivesoftware.smack.sm.packet.StreamManagement.Failed(condition);
    }

    public static org.jivesoftware.smack.sm.packet.StreamManagement.Resumed resumed(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        ParserUtils.assertAtStartTag(xmlpullparser);
        long l = ParserUtils.getLongAttribute(xmlpullparser, "h").longValue();
        String s = xmlpullparser.getAttributeValue("", "previd");
        xmlpullparser.next();
        ParserUtils.assertAtEndTag(xmlpullparser);
        return new org.jivesoftware.smack.sm.packet.StreamManagement.Resumed(l, s);
    }
}
