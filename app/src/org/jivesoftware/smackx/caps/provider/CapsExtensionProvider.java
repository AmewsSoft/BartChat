// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.caps.packet.CapsExtension;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class CapsExtensionProvider extends ExtensionElementProvider
{

    public CapsExtensionProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public CapsExtension parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        String s;
        String s1;
        String s2;
        if (xmlpullparser.getEventType() == 2 && xmlpullparser.getName().equalsIgnoreCase("c"))
        {
            s = xmlpullparser.getAttributeValue(null, "hash");
            s1 = xmlpullparser.getAttributeValue(null, "ver");
            s2 = xmlpullparser.getAttributeValue(null, "node");
            xmlpullparser.next();
            if (xmlpullparser.getEventType() != 3 || !xmlpullparser.getName().equalsIgnoreCase("c"))
            {
                throw new SmackException("Malformed nested Caps element");
            }
        } else
        {
            throw new SmackException("Malformed Caps element");
        }
        if (s != null && s1 != null && s2 != null)
        {
            return new CapsExtension(s2, s1, s);
        } else
        {
            throw new SmackException("Caps elment with missing attributes");
        }
    }
}
