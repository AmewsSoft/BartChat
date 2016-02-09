// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmppDateTime
{
    public static final class DateFormatType extends Enum
    {

        private static final DateFormatType ENUM$VALUES[];
        public static final DateFormatType XEP_0082_DATETIME_MILLIS_PROFILE;
        public static final DateFormatType XEP_0082_DATETIME_PROFILE;
        public static final DateFormatType XEP_0082_DATE_PROFILE;
        public static final DateFormatType XEP_0082_TIME_MILLIS_PROFILE;
        public static final DateFormatType XEP_0082_TIME_MILLIS_ZONE_PROFILE;
        public static final DateFormatType XEP_0082_TIME_PROFILE;
        public static final DateFormatType XEP_0082_TIME_ZONE_PROFILE;
        public static final DateFormatType XEP_0091_DATETIME;
        private final boolean CONVERT_TIMEZONE;
        private final DateFormat FORMATTER;
        private final String FORMAT_STRING;
        private final boolean HANDLE_MILLIS;

        public static DateFormatType valueOf(String s)
        {
            return (DateFormatType)Enum.valueOf(org/jxmpp/util/XmppDateTime$DateFormatType, s);
        }

        public static DateFormatType[] values()
        {
            DateFormatType adateformattype[] = ENUM$VALUES;
            int i = adateformattype.length;
            DateFormatType adateformattype1[] = new DateFormatType[i];
            System.arraycopy(adateformattype, 0, adateformattype1, 0, i);
            return adateformattype1;
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
                s = XmppDateTime.handleMilliseconds(s1);
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
            XEP_0082_DATE_PROFILE = new DateFormatType("XEP_0082_DATE_PROFILE", 0, "yyyy-MM-dd");
            XEP_0082_DATETIME_PROFILE = new DateFormatType("XEP_0082_DATETIME_PROFILE", 1, "yyyy-MM-dd'T'HH:mm:ssZ");
            XEP_0082_DATETIME_MILLIS_PROFILE = new DateFormatType("XEP_0082_DATETIME_MILLIS_PROFILE", 2, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            XEP_0082_TIME_PROFILE = new DateFormatType("XEP_0082_TIME_PROFILE", 3, "hh:mm:ss");
            XEP_0082_TIME_ZONE_PROFILE = new DateFormatType("XEP_0082_TIME_ZONE_PROFILE", 4, "hh:mm:ssZ");
            XEP_0082_TIME_MILLIS_PROFILE = new DateFormatType("XEP_0082_TIME_MILLIS_PROFILE", 5, "hh:mm:ss.SSS");
            XEP_0082_TIME_MILLIS_ZONE_PROFILE = new DateFormatType("XEP_0082_TIME_MILLIS_ZONE_PROFILE", 6, "hh:mm:ss.SSSZ");
            XEP_0091_DATETIME = new DateFormatType("XEP_0091_DATETIME", 7, "yyyyMMdd'T'HH:mm:ss");
            ENUM$VALUES = (new DateFormatType[] {
                XEP_0082_DATE_PROFILE, XEP_0082_DATETIME_PROFILE, XEP_0082_DATETIME_MILLIS_PROFILE, XEP_0082_TIME_PROFILE, XEP_0082_TIME_ZONE_PROFILE, XEP_0082_TIME_MILLIS_PROFILE, XEP_0082_TIME_MILLIS_ZONE_PROFILE, XEP_0091_DATETIME
            });
        }

        private DateFormatType(String s, int i, String s1)
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

    private static class PatternCouplings
    {

        final DateFormatType formatter;
        final Pattern pattern;

        public PatternCouplings(Pattern pattern1, DateFormatType dateformattype)
        {
            pattern = pattern1;
            formatter = dateformattype;
        }
    }


    private static final Pattern SECOND_FRACTION = Pattern.compile(".*\\.(\\d{1,})(Z|((\\+|-)\\d{4}))");
    private static final List couplings;
    private static final DateFormatType dateFormatter;
    private static final Pattern datePattern;
    private static final DateFormatType dateTimeFormatter;
    private static final DateFormatType dateTimeNoMillisFormatter;
    private static final Pattern dateTimeNoMillisPattern;
    private static final Pattern dateTimePattern;
    private static final DateFormatType timeFormatter;
    private static final DateFormatType timeNoMillisFormatter;
    private static final DateFormatType timeNoMillisNoZoneFormatter;
    private static final Pattern timeNoMillisNoZonePattern;
    private static final Pattern timeNoMillisPattern;
    private static final DateFormatType timeNoZoneFormatter;
    private static final Pattern timeNoZonePattern;
    private static final Pattern timePattern;
    private static final DateFormat xep0091Date6DigitFormatter;
    private static final DateFormat xep0091Date7Digit1MonthFormatter;
    private static final DateFormat xep0091Date7Digit2MonthFormatter;
    private static final DateFormat xep0091Formatter;
    private static final Pattern xep0091Pattern = Pattern.compile("^\\d+T\\d+:\\d+:\\d+$");

    public XmppDateTime()
    {
    }

    public static String asString(TimeZone timezone)
    {
        int i = timezone.getRawOffset();
        int j = i / 0x36ee80;
        return String.format("%+d:%02d", new Object[] {
            Integer.valueOf(j), Integer.valueOf(Math.abs(i / 60000 - j * 60))
        });
    }

    public static String convertRfc822TimezoneToXep82(String s)
    {
        int i = s.length();
        return (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s.substring(0, i - 2)))).append(':').toString()))).append(s.substring(i - 2, i)).toString();
    }

    public static String convertXep82TimezoneToRfc822(String s)
    {
        if (s.charAt(s.length() - 1) == 'Z')
        {
            return s.replace("Z", "+0000");
        } else
        {
            return s.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
        }
    }

    private static Calendar determineNearestDate(final Calendar now, List list)
    {
        Collections.sort(list, new Comparator() {

            private final Calendar val$now;

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((Calendar)obj, (Calendar)obj1);
            }

            public int compare(Calendar calendar, Calendar calendar1)
            {
                return (new Long(now.getTimeInMillis() - calendar.getTimeInMillis())).compareTo(new Long(now.getTimeInMillis() - calendar1.getTimeInMillis()));
            }

            
            {
                now = calendar;
                super();
            }
        });
        return (Calendar)list.get(0);
    }

    private static transient List filterDatesBefore(Calendar calendar, Calendar acalendar[])
    {
        ArrayList arraylist = new ArrayList();
        int j = acalendar.length;
        int i = 0;
        do
        {
            if (i >= j)
            {
                return arraylist;
            }
            Calendar calendar1 = acalendar[i];
            if (calendar1 != null && calendar1.before(calendar))
            {
                arraylist.add(calendar1);
            }
            i++;
        } while (true);
    }

    public static String formatXEP0082Date(Date date)
    {
        synchronized (dateTimeFormatter)
        {
            date = dateTimeFormatter.format(date);
        }
        return date;
        date;
        dateformattype;
        JVM INSTR monitorexit ;
        throw date;
    }

    private static Date handleDateWithMissingLeadingZeros(String s, int i)
        throws ParseException
    {
        if (i == 6)
        {
            synchronized (xep0091Date6DigitFormatter)
            {
                s = xep0091Date6DigitFormatter.parse(s);
            }
            return s;
        }
        break MISSING_BLOCK_LABEL_29;
        s;
        dateformat;
        JVM INSTR monitorexit ;
        throw s;
        Calendar calendar = Calendar.getInstance();
        s = filterDatesBefore(calendar, new Calendar[] {
            parseXEP91Date(s, xep0091Date7Digit1MonthFormatter), parseXEP91Date(s, xep0091Date7Digit2MonthFormatter)
        });
        if (!s.isEmpty())
        {
            return determineNearestDate(calendar, s).getTime();
        } else
        {
            return null;
        }
    }

    private static String handleMilliseconds(String s)
    {
        Matcher matcher = SECOND_FRACTION.matcher(s);
        int j;
        if (matcher.matches())
        {
            if ((j = matcher.group(1).length()) != 3)
            {
                int k = s.indexOf(".");
                StringBuilder stringbuilder = new StringBuilder((s.length() - j) + 3);
                if (j > 3)
                {
                    stringbuilder.append(s.substring(0, k + 4));
                } else
                {
                    stringbuilder.append(s.substring(0, k + j + 1));
                    int i = j;
                    while (i < 3) 
                    {
                        stringbuilder.append('0');
                        i++;
                    }
                }
                stringbuilder.append(s.substring(k + j + 1));
                return stringbuilder.toString();
            }
        }
        return s;
    }

    public static Date parseDate(String s)
        throws ParseException
    {
        if (xep0091Pattern.matcher(s).matches())
        {
            int i = s.split("T")[0].length();
            if (i < 8)
            {
                Date date = handleDateWithMissingLeadingZeros(s, i);
                if (date != null)
                {
                    return date;
                }
            } else
            {
                synchronized (xep0091Formatter)
                {
                    s = xep0091Formatter.parse(s);
                }
                return s;
            }
        }
        break MISSING_BLOCK_LABEL_67;
        s;
        dateformat;
        JVM INSTR monitorexit ;
        throw s;
        return parseXEP0082Date(s);
    }

    public static Date parseXEP0082Date(String s)
        throws ParseException
    {
        obj = couplings.iterator();
        PatternCouplings patterncouplings;
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                synchronized (dateTimeNoMillisFormatter)
                {
                    s = dateTimeNoMillisFormatter.parse(s);
                }
                return s;
            }
            patterncouplings = (PatternCouplings)((Iterator) (obj)).next();
        } while (!patterncouplings.pattern.matcher(s).matches());
        return patterncouplings.formatter.parse(s);
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
    }

    private static Calendar parseXEP91Date(String s, DateFormat dateformat)
    {
        dateformat;
        JVM INSTR monitorenter ;
        dateformat.parse(s);
        s = dateformat.getCalendar();
        dateformat;
        JVM INSTR monitorexit ;
        return s;
        s;
        dateformat;
        JVM INSTR monitorexit ;
        try
        {
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return null;
        }
    }

    static 
    {
        dateFormatter = DateFormatType.XEP_0082_DATE_PROFILE;
        datePattern = Pattern.compile("^\\d+-\\d+-\\d+$");
        timeFormatter = DateFormatType.XEP_0082_TIME_MILLIS_ZONE_PROFILE;
        timePattern = Pattern.compile("^(\\d+:){2}\\d+.\\d+(Z|([+-](\\d+:\\d+)))$");
        timeNoZoneFormatter = DateFormatType.XEP_0082_TIME_MILLIS_PROFILE;
        timeNoZonePattern = Pattern.compile("^(\\d+:){2}\\d+.\\d+$");
        timeNoMillisFormatter = DateFormatType.XEP_0082_TIME_ZONE_PROFILE;
        timeNoMillisPattern = Pattern.compile("^(\\d+:){2}\\d+(Z|([+-](\\d+:\\d+)))$");
        timeNoMillisNoZoneFormatter = DateFormatType.XEP_0082_TIME_PROFILE;
        timeNoMillisNoZonePattern = Pattern.compile("^(\\d+:){2}\\d+$");
        dateTimeFormatter = DateFormatType.XEP_0082_DATETIME_MILLIS_PROFILE;
        dateTimePattern = Pattern.compile("^\\d+(-\\d+){2}+T(\\d+:){2}\\d+.\\d+(Z|([+-](\\d+:\\d+)))?$");
        dateTimeNoMillisFormatter = DateFormatType.XEP_0082_DATETIME_PROFILE;
        dateTimeNoMillisPattern = Pattern.compile("^\\d+(-\\d+){2}+T(\\d+:){2}\\d+(Z|([+-](\\d+:\\d+)))?$");
        xep0091Formatter = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        xep0091Date6DigitFormatter = new SimpleDateFormat("yyyyMd'T'HH:mm:ss");
        xep0091Date7Digit1MonthFormatter = new SimpleDateFormat("yyyyMdd'T'HH:mm:ss");
        xep0091Date7Digit2MonthFormatter = new SimpleDateFormat("yyyyMMd'T'HH:mm:ss");
        couplings = new ArrayList();
        TimeZone timezone = TimeZone.getTimeZone("UTC");
        xep0091Formatter.setTimeZone(timezone);
        xep0091Date6DigitFormatter.setTimeZone(timezone);
        xep0091Date7Digit1MonthFormatter.setTimeZone(timezone);
        xep0091Date7Digit1MonthFormatter.setLenient(false);
        xep0091Date7Digit2MonthFormatter.setTimeZone(timezone);
        xep0091Date7Digit2MonthFormatter.setLenient(false);
        couplings.add(new PatternCouplings(datePattern, dateFormatter));
        couplings.add(new PatternCouplings(dateTimePattern, dateTimeFormatter));
        couplings.add(new PatternCouplings(dateTimeNoMillisPattern, dateTimeNoMillisFormatter));
        couplings.add(new PatternCouplings(timePattern, timeFormatter));
        couplings.add(new PatternCouplings(timeNoZonePattern, timeNoZoneFormatter));
        couplings.add(new PatternCouplings(timeNoMillisPattern, timeNoMillisFormatter));
        couplings.add(new PatternCouplings(timeNoMillisNoZonePattern, timeNoMillisNoZoneFormatter));
    }

}
