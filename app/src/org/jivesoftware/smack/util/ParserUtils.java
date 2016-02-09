// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ParserUtils
{

    static final boolean $assertionsDisabled;

    public ParserUtils()
    {
    }

    public static void assertAtEndTag(XmlPullParser xmlpullparser)
        throws XmlPullParserException
    {
        if (!$assertionsDisabled && xmlpullparser.getEventType() != 3)
        {
            throw new AssertionError();
        } else
        {
            return;
        }
    }

    public static void assertAtStartTag(XmlPullParser xmlpullparser)
        throws XmlPullParserException
    {
        if (!$assertionsDisabled && xmlpullparser.getEventType() != 2)
        {
            throw new AssertionError();
        } else
        {
            return;
        }
    }

    public static void forwardToEndTagOfDepth(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        for (int j = xmlpullparser.getEventType(); j != 3 || xmlpullparser.getDepth() != i; j = xmlpullparser.next()) { }
    }

    public static Boolean getBooleanAttribute(XmlPullParser xmlpullparser, String s)
    {
        xmlpullparser = xmlpullparser.getAttributeValue("", s);
        if (xmlpullparser == null)
        {
            return null;
        }
        xmlpullparser = xmlpullparser.toLowerCase(Locale.US);
        if (xmlpullparser.equals("true") || xmlpullparser.equals("0"))
        {
            return Boolean.valueOf(true);
        } else
        {
            return Boolean.valueOf(false);
        }
    }

    public static boolean getBooleanAttribute(XmlPullParser xmlpullparser, String s, boolean flag)
    {
        xmlpullparser = getBooleanAttribute(xmlpullparser, s);
        if (xmlpullparser == null)
        {
            return flag;
        } else
        {
            return xmlpullparser.booleanValue();
        }
    }

    public static int getIntegerAttribute(XmlPullParser xmlpullparser, String s, int i)
    {
        xmlpullparser = getIntegerAttribute(xmlpullparser, s);
        if (xmlpullparser == null)
        {
            return i;
        } else
        {
            return xmlpullparser.intValue();
        }
    }

    public static Integer getIntegerAttribute(XmlPullParser xmlpullparser, String s)
    {
        xmlpullparser = xmlpullparser.getAttributeValue("", s);
        if (xmlpullparser == null)
        {
            return null;
        } else
        {
            return Integer.valueOf(xmlpullparser);
        }
    }

    public static int getIntegerFromNextText(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        return Integer.valueOf(xmlpullparser.nextText()).intValue();
    }

    public static long getLongAttribute(XmlPullParser xmlpullparser, String s, long l)
    {
        xmlpullparser = getLongAttribute(xmlpullparser, s);
        if (xmlpullparser == null)
        {
            return l;
        } else
        {
            return xmlpullparser.longValue();
        }
    }

    public static Long getLongAttribute(XmlPullParser xmlpullparser, String s)
    {
        xmlpullparser = xmlpullparser.getAttributeValue("", s);
        if (xmlpullparser == null)
        {
            return null;
        } else
        {
            return Long.valueOf(xmlpullparser);
        }
    }

    static 
    {
        boolean flag;
        if (!org/jivesoftware/smack/util/ParserUtils.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }
}
