// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.nick.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.nick.packet:
//            Nick

public static class ovider extends ExtensionElementProvider
{

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public Nick parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        xmlpullparser.next();
        String s = xmlpullparser.getText();
        for (; xmlpullparser.getEventType() != 3; xmlpullparser.next()) { }
        return new Nick(s);
    }

    public ovider()
    {
    }
}
