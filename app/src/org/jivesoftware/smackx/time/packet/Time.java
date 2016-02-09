// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.time.packet;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jxmpp.util.XmppDateTime;

public class Time extends IQ
{

    public static final String ELEMENT = "time";
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/time/packet/Time.getName());
    public static final String NAMESPACE = "urn:xmpp:time";
    private String tzo;
    private String utc;

    public Time()
    {
        super("time", "urn:xmpp:time");
        setType(org.jivesoftware.smack.packet.IQ.Type.get);
    }

    public Time(Calendar calendar)
    {
        super("time", "urn:xmpp:time");
        tzo = XmppDateTime.asString(calendar.getTimeZone());
        utc = XmppDateTime.formatXEP0082Date(calendar.getTime());
    }

    public static Time createResponse(IQ iq)
    {
        Time time = new Time(Calendar.getInstance());
        time.setType(org.jivesoftware.smack.packet.IQ.Type.result);
        time.setTo(iq.getFrom());
        return time;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        if (utc != null)
        {
            iqchildelementxmlstringbuilder.append("<utc>").append(utc).append("</utc>");
            iqchildelementxmlstringbuilder.append("<tzo>").append(tzo).append("</tzo>");
            return iqchildelementxmlstringbuilder;
        } else
        {
            iqchildelementxmlstringbuilder.setEmptyElement();
            return iqchildelementxmlstringbuilder;
        }
    }

    public Date getTime()
    {
        if (utc == null)
        {
            return null;
        }
        Date date;
        try
        {
            date = XmppDateTime.parseDate(utc);
        }
        catch (Exception exception)
        {
            LOGGER.log(Level.SEVERE, "Error getting local time", exception);
            return null;
        }
        return date;
    }

    public String getTzo()
    {
        return tzo;
    }

    public String getUtc()
    {
        return utc;
    }

    public void setTime(Date date)
    {
    }

    public void setTzo(String s)
    {
        tzo = s;
    }

    public void setUtc(String s)
    {
        utc = s;
    }

}
