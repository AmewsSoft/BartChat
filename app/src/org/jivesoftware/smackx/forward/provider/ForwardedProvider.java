// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.forward.provider;

import java.io.IOException;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.delay.provider.DelayInformationProvider;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ForwardedProvider extends ExtensionElementProvider
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/forward/provider/ForwardedProvider.getName());

    public ForwardedProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public Forwarded parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        Object obj;
        Object obj1;
        obj1 = null;
        obj = null;
_L1:
        Object obj2;
        Object obj3;
        obj2 = obj1;
        obj3 = obj;
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 40
    //                   2 43
    //                   3 149;
           goto _L1 _L2 _L3
_L2:
        byte byte0;
        obj2 = xmlpullparser.getName();
        obj3 = xmlpullparser.getNamespace();
        byte0 = -1;
        ((String) (obj2)).hashCode();
        JVM INSTR lookupswitch 2: default 92
    //                   95467907: 181
    //                   954925063: 197;
           goto _L4 _L5 _L6
_L4:
        byte0;
        JVM INSTR tableswitch 0 1: default 116
    //                   0 213
    //                   1 295;
           goto _L7 _L8 _L9
_L7:
        LOGGER.warning((new StringBuilder()).append("Unsupported forwarded packet type: ").append(((String) (obj2))).toString());
        obj3 = obj;
        obj2 = obj1;
_L3:
        obj1 = obj2;
        obj = obj3;
        if (xmlpullparser.getDepth() == i)
        {
            if (obj3 == null)
            {
                throw new SmackException("forwarded extension must contain a packet");
            } else
            {
                return new Forwarded(((org.jivesoftware.smackx.delay.packet.DelayInformation) (obj2)), ((org.jivesoftware.smack.packet.Stanza) (obj3)));
            }
        }
          goto _L1
_L5:
        if (((String) (obj2)).equals("delay"))
        {
            byte0 = 0;
        }
          goto _L4
_L6:
        if (((String) (obj2)).equals("message"))
        {
            byte0 = 1;
        }
          goto _L4
_L8:
        if ("urn:xmpp:delay".equals(obj3))
        {
            obj2 = DelayInformationProvider.INSTANCE.parse(xmlpullparser, xmlpullparser.getDepth());
            obj3 = obj;
        } else
        {
            LOGGER.warning((new StringBuilder()).append("Namespace '").append(((String) (obj3))).append("' does not match expected namespace '").append("urn:xmpp:delay").append("'").toString());
            obj2 = obj1;
            obj3 = obj;
        }
          goto _L3
_L9:
        obj3 = PacketParserUtils.parseMessage(xmlpullparser);
        obj2 = obj1;
          goto _L3
    }

}
