// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.util;

import java.util.Calendar;
import java.util.Comparator;

// Referenced classes of package org.jxmpp.util:
//            XmppDateTime

class val.now
    implements Comparator
{

    private final Calendar val$now;

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((Calendar)obj, (Calendar)obj1);
    }

    public int compare(Calendar calendar, Calendar calendar1)
    {
        return (new Long(val$now.getTimeInMillis() - calendar.getTimeInMillis())).compareTo(new Long(val$now.getTimeInMillis() - calendar1.getTimeInMillis()));
    }

    ()
    {
        val$now = calendar;
        super();
    }
}
