// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

// Referenced classes of package org.jxmpp.util:
//            XmppDateTime

public static final class HANDLE_MILLIS extends Enum
{

    private static final XEP_0091_DATETIME ENUM$VALUES[];
    public static final XEP_0091_DATETIME XEP_0082_DATETIME_MILLIS_PROFILE;
    public static final XEP_0091_DATETIME XEP_0082_DATETIME_PROFILE;
    public static final XEP_0091_DATETIME XEP_0082_DATE_PROFILE;
    public static final XEP_0091_DATETIME XEP_0082_TIME_MILLIS_PROFILE;
    public static final XEP_0091_DATETIME XEP_0082_TIME_MILLIS_ZONE_PROFILE;
    public static final XEP_0091_DATETIME XEP_0082_TIME_PROFILE;
    public static final XEP_0091_DATETIME XEP_0082_TIME_ZONE_PROFILE;
    public static final XEP_0091_DATETIME XEP_0091_DATETIME;
    private final boolean CONVERT_TIMEZONE;
    private final DateFormat FORMATTER;
    private final String FORMAT_STRING;
    private final boolean HANDLE_MILLIS;

    public static HANDLE_MILLIS valueOf(String s)
    {
        return (HANDLE_MILLIS)Enum.valueOf(org/jxmpp/util/XmppDateTime$DateFormatType, s);
    }

    public static HANDLE_MILLIS[] values()
    {
        HANDLE_MILLIS ahandle_millis[] = ENUM$VALUES;
        int i = ahandle_millis.length;
        HANDLE_MILLIS ahandle_millis1[] = new ENUM.VALUES[i];
        System.arraycopy(ahandle_millis, 0, ahandle_millis1, 0, i);
        return ahandle_millis1;
    }

    public String format(Date date)
    {
        String s;
        synchronized (FORMATTER)
        {
            s = FORMATTER.format(date);
        }
        date = s;
        if (CONVERT_TIMEZONE)
        {
            date = XmppDateTime.convertRfc822TimezoneToXep82(s);
        }
        return date;
        date;
        dateformat;
        JVM INSTR monitorexit ;
        throw date;
    }

    public Date parse(String s)
        throws ParseException
    {
        String s1 = s;
        if (CONVERT_TIMEZONE)
        {
            s1 = XmppDateTime.convertXep82TimezoneToRfc822(s);
        }
        s = s1;
        if (HANDLE_MILLIS)
        {
            s = XmppDateTime.access$0(s1);
        }
        synchronized (FORMATTER)
        {
            s = FORMATTER.parse(s);
        }
        return s;
        s;
        dateformat;
        JVM INSTR monitorexit ;
        throw s;
    }

    static 
    {
        XEP_0082_DATE_PROFILE = new <init>("XEP_0082_DATE_PROFILE", 0, "yyyy-MM-dd");
        XEP_0082_DATETIME_PROFILE = new <init>("XEP_0082_DATETIME_PROFILE", 1, "yyyy-MM-dd'T'HH:mm:ssZ");
        XEP_0082_DATETIME_MILLIS_PROFILE = new <init>("XEP_0082_DATETIME_MILLIS_PROFILE", 2, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        XEP_0082_TIME_PROFILE = new <init>("XEP_0082_TIME_PROFILE", 3, "hh:mm:ss");
        XEP_0082_TIME_ZONE_PROFILE = new <init>("XEP_0082_TIME_ZONE_PROFILE", 4, "hh:mm:ssZ");
        XEP_0082_TIME_MILLIS_PROFILE = new <init>("XEP_0082_TIME_MILLIS_PROFILE", 5, "hh:mm:ss.SSS");
        XEP_0082_TIME_MILLIS_ZONE_PROFILE = new <init>("XEP_0082_TIME_MILLIS_ZONE_PROFILE", 6, "hh:mm:ss.SSSZ");
        XEP_0091_DATETIME = new <init>("XEP_0091_DATETIME", 7, "yyyyMMdd'T'HH:mm:ss");
        ENUM$VALUES = (new ENUM.VALUES[] {
            XEP_0082_DATE_PROFILE, XEP_0082_DATETIME_PROFILE, XEP_0082_DATETIME_MILLIS_PROFILE, XEP_0082_TIME_PROFILE, XEP_0082_TIME_ZONE_PROFILE, XEP_0082_TIME_MILLIS_PROFILE, XEP_0082_TIME_MILLIS_ZONE_PROFILE, XEP_0091_DATETIME
        });
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        FORMAT_STRING = s1;
        FORMATTER = new SimpleDateFormat(FORMAT_STRING);
        FORMATTER.setTimeZone(TimeZone.getTimeZone("UTC"));
        boolean flag;
        if (s1.charAt(s1.length() - 1) == 'Z')
        {
            flag = true;
        } else
        {
            flag = false;
        }
        CONVERT_TIMEZONE = flag;
        HANDLE_MILLIS = s1.contains("SSS");
    }
}
