// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bookmarks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.iqprivate.packet.PrivateData;
import org.jivesoftware.smackx.iqprivate.provider.PrivateDataProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.bookmarks:
//            BookmarkedConference, BookmarkedURL

public class Bookmarks
    implements PrivateData
{
    public static class Provider
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
                    BookmarkedURL bookmarkedurl = Bookmarks.getURLStorage(xmlpullparser);
                    if (bookmarkedurl != null)
                    {
                        bookmarks.addBookmarkedURL(bookmarkedurl);
                    }
                } else
                if (i == 2 && "conference".equals(xmlpullparser.getName()))
                {
                    bookmarks.addBookmarkedConference(Bookmarks.getConferenceStorage(xmlpullparser));
                } else
                if (i == 3 && "storage".equals(xmlpullparser.getName()))
                {
                    flag = true;
                }
            } while (true);
            return bookmarks;
        }

        public Provider()
        {
        }
    }


    public static final String ELEMENT = "storage";
    public static final String NAMESPACE = "storage:bookmarks";
    private List bookmarkedConferences;
    private List bookmarkedURLS;

    public Bookmarks()
    {
        bookmarkedURLS = new ArrayList();
        bookmarkedConferences = new ArrayList();
    }

    private static BookmarkedConference getConferenceStorage(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        String s = xmlpullparser.getAttributeValue("", "name");
        String s1 = xmlpullparser.getAttributeValue("", "autojoin");
        BookmarkedConference bookmarkedconference = new BookmarkedConference(xmlpullparser.getAttributeValue("", "jid"));
        bookmarkedconference.setName(s);
        bookmarkedconference.setAutoJoin(Boolean.valueOf(s1).booleanValue());
        boolean flag = false;
        do
        {
            if (flag)
            {
                break;
            }
            int i = xmlpullparser.next();
            if (i == 2 && "nick".equals(xmlpullparser.getName()))
            {
                bookmarkedconference.setNickname(xmlpullparser.nextText());
            } else
            if (i == 2 && "password".equals(xmlpullparser.getName()))
            {
                bookmarkedconference.setPassword(xmlpullparser.nextText());
            } else
            if (i == 2 && "shared_bookmark".equals(xmlpullparser.getName()))
            {
                bookmarkedconference.setShared(true);
            } else
            if (i == 3 && "conference".equals(xmlpullparser.getName()))
            {
                flag = true;
            }
        } while (true);
        return bookmarkedconference;
    }

    private static BookmarkedURL getURLStorage(XmlPullParser xmlpullparser)
        throws IOException, XmlPullParserException
    {
        Object obj = xmlpullparser.getAttributeValue("", "name");
        String s = xmlpullparser.getAttributeValue("", "url");
        String s1 = xmlpullparser.getAttributeValue("", "rss");
        boolean flag;
        boolean flag1;
        if (s1 != null && "true".equals(s1))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj = new BookmarkedURL(s, ((String) (obj)), flag1);
        flag = false;
        do
        {
            if (flag)
            {
                break;
            }
            int i = xmlpullparser.next();
            if (i == 2 && "shared_bookmark".equals(xmlpullparser.getName()))
            {
                ((BookmarkedURL) (obj)).setShared(true);
            } else
            if (i == 3 && "url".equals(xmlpullparser.getName()))
            {
                flag = true;
            }
        } while (true);
        return ((BookmarkedURL) (obj));
    }

    public void addBookmarkedConference(BookmarkedConference bookmarkedconference)
    {
        bookmarkedConferences.add(bookmarkedconference);
    }

    public void addBookmarkedURL(BookmarkedURL bookmarkedurl)
    {
        bookmarkedURLS.add(bookmarkedurl);
    }

    public void clearBookmarkedConferences()
    {
        bookmarkedConferences.clear();
    }

    public void clearBookmarkedURLS()
    {
        bookmarkedURLS.clear();
    }

    public List getBookmarkedConferences()
    {
        return bookmarkedConferences;
    }

    public List getBookmarkedURLS()
    {
        return bookmarkedURLS;
    }

    public String getElementName()
    {
        return "storage";
    }

    public String getNamespace()
    {
        return "storage:bookmarks";
    }

    public void removeBookmarkedConference(BookmarkedConference bookmarkedconference)
    {
        bookmarkedConferences.remove(bookmarkedconference);
    }

    public void removeBookmarkedURL(BookmarkedURL bookmarkedurl)
    {
        bookmarkedURLS.remove(bookmarkedurl);
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("storage").xmlnsAttribute("storage:bookmarks").rightAngleBracket();
        Iterator iterator = getBookmarkedURLS().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            BookmarkedURL bookmarkedurl = (BookmarkedURL)iterator.next();
            if (!bookmarkedurl.isShared())
            {
                xmlstringbuilder.halfOpenElement("url").attribute("name", bookmarkedurl.getName()).attribute("url", bookmarkedurl.getURL());
                xmlstringbuilder.condAttribute(bookmarkedurl.isRss(), "rss", "true");
                xmlstringbuilder.closeEmptyElement();
            }
        } while (true);
        iterator = getBookmarkedConferences().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            BookmarkedConference bookmarkedconference = (BookmarkedConference)iterator.next();
            if (!bookmarkedconference.isShared())
            {
                xmlstringbuilder.halfOpenElement("conference");
                xmlstringbuilder.attribute("name", bookmarkedconference.getName());
                xmlstringbuilder.attribute("autojoin", Boolean.toString(bookmarkedconference.isAutoJoin()));
                xmlstringbuilder.attribute("jid", bookmarkedconference.getJid());
                xmlstringbuilder.rightAngleBracket();
                xmlstringbuilder.optElement("nick", bookmarkedconference.getNickname());
                xmlstringbuilder.optElement("password", bookmarkedconference.getPassword());
                xmlstringbuilder.closeElement("conference");
            }
        } while (true);
        xmlstringbuilder.closeElement("storage");
        return xmlstringbuilder;
    }


}
