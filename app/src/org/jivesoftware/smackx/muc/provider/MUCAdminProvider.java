// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.muc.packet.MUCAdmin;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.muc.provider:
//            MUCParserUtils

public class MUCAdminProvider extends IQProvider
{

    public MUCAdminProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public MUCAdmin parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        MUCAdmin mucadmin = new MUCAdmin();
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
                if (xmlpullparser.getName().equals("item"))
                {
                    mucadmin.addItem(MUCParserUtils.parseItem(xmlpullparser));
                }
            } else
            if (j == 3 && xmlpullparser.getName().equals("query"))
            {
                i = 1;
            }
        } while (true);
        return mucadmin;
    }
}
