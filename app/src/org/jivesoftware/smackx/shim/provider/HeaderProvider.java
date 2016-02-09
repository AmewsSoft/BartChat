// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.shim.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.shim.packet.Header;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class HeaderProvider extends ExtensionElementProvider
{

    public HeaderProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public Header parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        String s1 = xmlpullparser.getAttributeValue(null, "name");
        String s = null;
        xmlpullparser.next();
        if (xmlpullparser.getEventType() == 4)
        {
            s = xmlpullparser.getText();
        }
        for (; xmlpullparser.getEventType() != 3; xmlpullparser.next()) { }
        return new Header(s1, s);
    }
}
