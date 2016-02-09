// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.address.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.address.packet.MultipleAddresses;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MultipleAddressesProvider extends ExtensionElementProvider
{

    public MultipleAddressesProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public MultipleAddresses parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        MultipleAddresses multipleaddresses = new MultipleAddresses();
_L7:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 36
    //                   2 39
    //                   3 186;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR tableswitch -1147692044 -1147692044: default 72
    //                   -1147692044 170;
           goto _L4 _L5
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            multipleaddresses.addAddress(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.valueOf(xmlpullparser.getAttributeValue("", "type")), xmlpullparser.getAttributeValue("", "jid"), xmlpullparser.getAttributeValue("", "node"), xmlpullparser.getAttributeValue("", "desc"), "true".equals(xmlpullparser.getAttributeValue("", "delivered")), xmlpullparser.getAttributeValue("", "uri"));
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (s.equals("address"))
        {
            byte0 = 0;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            return multipleaddresses;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }
}
