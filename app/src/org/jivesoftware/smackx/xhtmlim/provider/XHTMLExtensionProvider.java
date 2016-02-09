// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xhtmlim.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.xhtmlim.packet.XHTMLExtension;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class XHTMLExtensionProvider extends ExtensionElementProvider
{

    public XHTMLExtensionProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public XHTMLExtension parse(XmlPullParser xmlpullparser, int i)
        throws IOException, XmlPullParserException
    {
        XHTMLExtension xhtmlextension = new XHTMLExtension();
_L2:
        int j;
        j = xmlpullparser.getEventType();
        String s = xmlpullparser.getName();
        if (j != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        if (s.equals("body"))
        {
            xhtmlextension.addBody(PacketParserUtils.parseElement(xmlpullparser));
        }
_L4:
        xmlpullparser.next();
        if (true) goto _L2; else goto _L1
_L1:
        if (j != 3 || xmlpullparser.getDepth() != i) goto _L4; else goto _L3
_L3:
        return xhtmlextension;
    }
}
