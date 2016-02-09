// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.delay.provider;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.delay.packet.DelayInformation;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class AbstractDelayInformationProvider extends ExtensionElementProvider
{

    public AbstractDelayInformationProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public final DelayInformation parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        Object obj;
        String s;
        String s1;
        s1 = xmlpullparser.getAttributeValue("", "stamp");
        s = xmlpullparser.getAttributeValue("", "from");
        obj = null;
        if (xmlpullparser.isEmptyElementTag())
        {
            break MISSING_BLOCK_LABEL_132;
        }
        i = xmlpullparser.next();
        i;
        JVM INSTR tableswitch 3 4: default 64
    //                   3 126
    //                   4 91;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalStateException((new StringBuilder()).append("Unexpected event: ").append(i).toString());
_L3:
        obj = xmlpullparser.getText();
        xmlpullparser.next();
        xmlpullparser = ((XmlPullParser) (obj));
_L4:
        try
        {
            obj = parseDate(s1);
        }
        // Misplaced declaration of an exception variable
        catch (XmlPullParser xmlpullparser)
        {
            throw new SmackException(xmlpullparser);
        }
        return new DelayInformation(((Date) (obj)), s, xmlpullparser);
_L2:
        xmlpullparser = "";
          goto _L4
        xmlpullparser.next();
        xmlpullparser = ((XmlPullParser) (obj));
          goto _L4
    }

    protected abstract Date parseDate(String s)
        throws ParseException;
}
