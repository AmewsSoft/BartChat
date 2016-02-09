// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bookmarks;

import java.io.IOException;
import org.jivesoftware.smackx.iqprivate.packet.PrivateData;
import org.jivesoftware.smackx.iqprivate.provider.PrivateDataProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.bookmarks:
//            Bookmarks

public static class aProvider
    implements PrivateDataProvider
{

    public PrivateData parsePrivateData(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        Bookmarks bookmarks = new Bookmarks();
        boolean flag = false;
        do
        {
            if (flag)
            {
                break;
            }
            int i = xmlpullparser.next();
            if (i == 2 && "url".equals(xmlpullparser.getName()))
            {
                BookmarkedURL bookmarkedurl = Bookmarks.access$000(xmlpullparser);
                if (bookmarkedurl != null)
                {
                    bookmarks.addBookmarkedURL(bookmarkedurl);
                }
            } else
            if (i == 2 && "conference".equals(xmlpullparser.getName()))
            {
                bookmarks.addBookmarkedConference(Bookmarks.access$100(xmlpullparser));
            } else
            if (i == 3 && "storage".equals(xmlpullparser.getName()))
            {
                flag = true;
            }
        } while (true);
        return bookmarks;
    }

    public aProvider()
    {
    }
}
