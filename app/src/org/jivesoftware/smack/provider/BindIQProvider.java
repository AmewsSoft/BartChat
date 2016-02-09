// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Bind;
import org.jivesoftware.smack.packet.Element;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smack.provider:
//            IQProvider

public class BindIQProvider extends IQProvider
{

    public BindIQProvider()
    {
    }

    public Bind parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        Bind bind = null;
_L8:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 32
    //                   2 35
    //                   3 161;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 2: default 76
    //                   -341064690: 116
    //                   105221: 132;
           goto _L4 _L5 _L6
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            bind = Bind.newSet(xmlpullparser.nextText());
            break;

        case 1: // '\001'
            bind = Bind.newResult(xmlpullparser.nextText());
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (s.equals("resource"))
        {
            byte0 = 0;
        }
          goto _L4
_L6:
        if (s.equals("jid"))
        {
            byte0 = 1;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            return bind;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }
}
