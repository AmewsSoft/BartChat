// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqlast.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.iqlast.packet:
//            LastActivity

public static class A extends IQProvider
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
            LastActivity.access$000(lastactivity, xmlpullparser.nextText());
        }
        // Misplaced declaration of an exception variable
        catch (XmlPullParser xmlpullparser)
        {
            throw new SmackException(xmlpullparser);
        }
        return lastactivity;
    }

    public A()
    {
    }
}
