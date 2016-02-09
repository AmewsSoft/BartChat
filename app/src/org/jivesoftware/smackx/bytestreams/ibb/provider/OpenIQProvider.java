// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb.provider;

import java.io.IOException;
import java.util.Locale;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OpenIQProvider extends IQProvider
{

    public OpenIQProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public Open parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        String s = xmlpullparser.getAttributeValue("", "sid");
        i = Integer.parseInt(xmlpullparser.getAttributeValue("", "block-size"));
        Object obj = xmlpullparser.getAttributeValue("", "stanza");
        if (obj == null)
        {
            obj = org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType.IQ;
        } else
        {
            obj = org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType.valueOf(((String) (obj)).toUpperCase(Locale.US));
        }
        xmlpullparser.next();
        return new Open(s, i, ((org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType) (obj)));
    }
}
