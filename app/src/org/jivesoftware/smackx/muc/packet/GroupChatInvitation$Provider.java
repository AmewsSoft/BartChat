// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.muc.packet:
//            GroupChatInvitation

public static class  extends ExtensionElementProvider
{

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public GroupChatInvitation parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        String s = xmlpullparser.getAttributeValue("", "jid");
        xmlpullparser.next();
        return new GroupChatInvitation(s);
    }

    public ()
    {
    }
}
