// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.muc.provider:
//            MUCParserUtils

public class MUCUserProvider extends ExtensionElementProvider
{

    public MUCUserProvider()
    {
    }

    private static org.jivesoftware.smackx.muc.packet.MUCUser.Decline parseDecline(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        boolean flag = false;
        org.jivesoftware.smackx.muc.packet.MUCUser.Decline decline = new org.jivesoftware.smackx.muc.packet.MUCUser.Decline();
        decline.setFrom(xmlpullparser.getAttributeValue("", "from"));
        decline.setTo(xmlpullparser.getAttributeValue("", "to"));
        do
        {
            if (flag)
            {
                break;
            }
            int i = xmlpullparser.next();
            if (i == 2)
            {
                if (xmlpullparser.getName().equals("reason"))
                {
                    decline.setReason(xmlpullparser.nextText());
                }
            } else
            if (i == 3 && xmlpullparser.getName().equals("decline"))
            {
                flag = true;
            }
        } while (true);
        return decline;
    }

    private static org.jivesoftware.smackx.muc.packet.MUCUser.Invite parseInvite(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        boolean flag = false;
        org.jivesoftware.smackx.muc.packet.MUCUser.Invite invite = new org.jivesoftware.smackx.muc.packet.MUCUser.Invite();
        invite.setFrom(xmlpullparser.getAttributeValue("", "from"));
        invite.setTo(xmlpullparser.getAttributeValue("", "to"));
        do
        {
            if (flag)
            {
                break;
            }
            int i = xmlpullparser.next();
            if (i == 2)
            {
                if (xmlpullparser.getName().equals("reason"))
                {
                    invite.setReason(xmlpullparser.nextText());
                }
            } else
            if (i == 3 && xmlpullparser.getName().equals("invite"))
            {
                flag = true;
            }
        } while (true);
        return invite;
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public MUCUser parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        MUCUser mucuser = new MUCUser();
_L12:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 36
    //                   2 39
    //                   3 328;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 6: default 112
    //                   -1183699191: 166
    //                   -892481550: 214
    //                   3242771: 182
    //                   1216985755: 198
    //                   1542349558: 230
    //                   1557372922: 246;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            mucuser.setInvite(parseInvite(xmlpullparser));
            break;

        case 1: // '\001'
            mucuser.setItem(MUCParserUtils.parseItem(xmlpullparser));
            break;

        case 2: // '\002'
            mucuser.setPassword(xmlpullparser.nextText());
            break;

        case 3: // '\003'
            mucuser.addStatusCode(org.jivesoftware.smackx.muc.packet.MUCUser.Status.create(xmlpullparser.getAttributeValue("", "code")));
            break;

        case 4: // '\004'
            mucuser.setDecline(parseDecline(xmlpullparser));
            break;

        case 5: // '\005'
            mucuser.setDestroy(MUCParserUtils.parseDestroy(xmlpullparser));
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (s.equals("invite"))
        {
            byte0 = 0;
        }
          goto _L4
_L7:
        if (s.equals("item"))
        {
            byte0 = 1;
        }
          goto _L4
_L8:
        if (s.equals("password"))
        {
            byte0 = 2;
        }
          goto _L4
_L6:
        if (s.equals("status"))
        {
            byte0 = 3;
        }
          goto _L4
_L9:
        if (s.equals("decline"))
        {
            byte0 = 4;
        }
          goto _L4
_L10:
        if (s.equals("destroy"))
        {
            byte0 = 5;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            return mucuser;
        }
        if (true) goto _L12; else goto _L11
_L11:
    }
}
