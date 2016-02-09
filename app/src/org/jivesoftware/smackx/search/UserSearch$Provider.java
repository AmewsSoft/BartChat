// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.search;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.search:
//            UserSearch, SimpleUserSearch

public static class  extends IQProvider
{

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public IQ parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        UserSearch usersearch;
        SimpleUserSearch simpleusersearch;
        usersearch = null;
        simpleusersearch = new SimpleUserSearch();
        i = 0;
_L8:
        if (i != 0) goto _L2; else goto _L1
_L1:
        int j = xmlpullparser.next();
        if (j != 2 || !xmlpullparser.getName().equals("instructions")) goto _L4; else goto _L3
_L3:
        UserSearch.access$000(simpleusersearch, xmlpullparser.nextText(), xmlpullparser);
_L6:
        return simpleusersearch;
_L4:
        if (j == 2 && xmlpullparser.getName().equals("item"))
        {
            simpleusersearch.parseItems(xmlpullparser);
            return simpleusersearch;
        }
        if (j == 2 && xmlpullparser.getNamespace().equals("jabber:x:data"))
        {
            usersearch = new UserSearch();
            PacketParserUtils.addExtensionElement(usersearch, xmlpullparser);
        } else
        if (j == 3 && xmlpullparser.getName().equals("query"))
        {
            i = 1;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (usersearch == null) goto _L6; else goto _L5
_L5:
        return usersearch;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public ()
    {
    }
}
