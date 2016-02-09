// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqlast.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class LastActivity extends IQ
{
    public static class Provider extends IQProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public LastActivity parse(XmlPullParser xmlpullparser, int i)
            throws SmackException, XmlPullParserException
        {
            LastActivity lastactivity = new LastActivity();
            String s = xmlpullparser.getAttributeValue("", "seconds");
            if (s != null)
            {
                try
                {
                    lastactivity.setLastActivity(Long.parseLong(s));
                }
                // Misplaced declaration of an exception variable
                catch (XmlPullParser xmlpullparser)
                {
                    throw new SmackException("Could not parse last activity number", xmlpullparser);
                }
            }
            try
            {
                lastactivity.setMessage(xmlpullparser.nextText());
            }
            // Misplaced declaration of an exception variable
            catch (XmlPullParser xmlpullparser)
            {
                throw new SmackException(xmlpullparser);
            }
            return lastactivity;
        }

        public Provider()
        {
        }
    }


    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:last";
    public long lastActivity;
    public String message;

    public LastActivity()
    {
        super("query", "jabber:iq:last");
        lastActivity = -1L;
        setType(org.jivesoftware.smack.packet.IQ.Type.get);
    }

    public LastActivity(String s)
    {
        this();
        setTo(s);
    }

    private void setMessage(String s)
    {
        message = s;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.optLongAttribute("seconds", Long.valueOf(lastActivity));
        iqchildelementxmlstringbuilder.setEmptyElement();
        return iqchildelementxmlstringbuilder;
    }

    public long getIdleTime()
    {
        return lastActivity;
    }

    public String getStatusMessage()
    {
        return message;
    }

    public void setLastActivity(long l)
    {
        lastActivity = l;
    }

}
