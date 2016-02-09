// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.delay.provider;

import java.text.ParseException;
import java.util.Date;
import org.jxmpp.util.XmppDateTime;

// Referenced classes of package org.jivesoftware.smackx.delay.provider:
//            AbstractDelayInformationProvider

public class DelayInformationProvider extends AbstractDelayInformationProvider
{

    public static final DelayInformationProvider INSTANCE = new DelayInformationProvider();

    public DelayInformationProvider()
    {
    }

    protected Date parseDate(String s)
        throws ParseException
    {
        return XmppDateTime.parseXEP0082Date(s);
    }

}
