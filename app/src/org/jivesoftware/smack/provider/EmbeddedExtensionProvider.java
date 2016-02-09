// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smack.provider:
//            ExtensionElementProvider

public abstract class EmbeddedExtensionProvider extends ExtensionElementProvider
{

    public EmbeddedExtensionProvider()
    {
    }

    protected abstract ExtensionElement createReturnExtension(String s, String s1, Map map, List list);

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public final ExtensionElement parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        String s = xmlpullparser.getNamespace();
        String s1 = xmlpullparser.getName();
        int l = xmlpullparser.getAttributeCount();
        HashMap hashmap = new HashMap(l);
        for (int j = 0; j < l; j++)
        {
            hashmap.put(xmlpullparser.getAttributeName(j), xmlpullparser.getAttributeValue(j));
        }

        ArrayList arraylist = new ArrayList();
        int k;
        do
        {
            k = xmlpullparser.next();
            if (k == 2)
            {
                PacketParserUtils.addExtensionElement(arraylist, xmlpullparser);
            }
        } while (k != 3 || xmlpullparser.getDepth() != i);
        return createReturnExtension(s1, s, hashmap, arraylist);
    }
}
