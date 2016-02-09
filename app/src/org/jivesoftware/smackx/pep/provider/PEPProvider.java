// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pep.provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PEPProvider extends ExtensionElementProvider
{

    private static final Map nodeParsers = new HashMap();

    public PEPProvider()
    {
    }

    public static void registerPEPParserExtension(String s, ExtensionElementProvider extensionelementprovider)
    {
        nodeParsers.put(s, extensionelementprovider);
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public ExtensionElement parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        ExtensionElement extensionelement = null;
        i = 0;
        do
        {
            if (i != 0)
            {
                break;
            }
            int j = xmlpullparser.next();
            if (j == 2)
            {
                if (!xmlpullparser.getName().equals("event") && xmlpullparser.getName().equals("items"))
                {
                    Object obj = xmlpullparser.getAttributeValue("", "node");
                    obj = (ExtensionElementProvider)nodeParsers.get(obj);
                    if (obj != null)
                    {
                        extensionelement = (ExtensionElement)((ExtensionElementProvider) (obj)).parse(xmlpullparser);
                    }
                }
            } else
            if (j == 3 && xmlpullparser.getName().equals("event"))
            {
                i = 1;
            }
        } while (true);
        return extensionelement;
    }

}
