// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bookmarks;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.iqprivate.PrivateDataManager;

// Referenced classes of package org.jivesoftware.smackx.bookmarks:
//            Bookmarks, BookmarkedConference, BookmarkedURL

public class BookmarkManager
{

    private static final Map bookmarkManagerMap = new WeakHashMap();
    private final Object bookmarkLock = new Object();
    private Bookmarks bookmarks;
    private PrivateDataManager privateDataManager;

    private BookmarkManager(XMPPConnection xmppconnection)
        throws XMPPException, SmackException
    {
        privateDataManager = PrivateDataManager.getInstanceFor(xmppconnection);
        bookmarkManagerMap.put(xmppconnection, this);
    }

    public static BookmarkManager getBookmarkManager(XMPPConnection xmppconnection)
        throws XMPPException, SmackException
    {
        org/jivesoftware/smackx/bookmarks/BookmarkManager;
        JVM INSTR monitorenter ;
        BookmarkManager bookmarkmanager1 = (BookmarkManager)bookmarkManagerMap.get(xmppconnection);
        BookmarkManager bookmarkmanager;
        bookmarkmanager = bookmarkmanager1;
        if (bookmarkmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        bookmarkmanager = new BookmarkManager(xmppconnection);
        org/jivesoftware/smackx/bookmarks/BookmarkManager;
        JVM INSTR monitorexit ;
        return bookmarkmanager;
        xmppconnection;
        throw xmppconnection;
    }

    private Bookmarks retrieveBookmarks()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Bookmarks bookmarks1;
        synchronized (bookmarkLock)
        {
            if (bookmarks == null)
            {
                bookmarks = (Bookmarks)privateDataManager.getPrivateData("storage", "storage:bookmarks");
            }
            bookmarks1 = bookmarks;
        }
        return bookmarks1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void addBookmarkedConference(String s, String s1, boolean flag, String s2, String s3)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        retrieveBookmarks();
        s1 = new BookmarkedConference(s, s1, flag, s2, s3);
        List list = bookmarks.getBookmarkedConferences();
        if (list.contains(s1))
        {
            s1 = (BookmarkedConference)list.get(list.indexOf(s1));
            if (s1.isShared())
            {
                throw new IllegalArgumentException("Cannot modify shared bookmark");
            }
            s1.setAutoJoin(flag);
            s1.setName(s);
            s1.setNickname(s2);
            s1.setPassword(s3);
        } else
        {
            bookmarks.addBookmarkedConference(s1);
        }
        privateDataManager.setPrivateData(bookmarks);
    }

    public void addBookmarkedURL(String s, String s1, boolean flag)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        retrieveBookmarks();
        s = new BookmarkedURL(s, s1, flag);
        List list = bookmarks.getBookmarkedURLS();
        if (list.contains(s))
        {
            s = (BookmarkedURL)list.get(list.indexOf(s));
            if (s.isShared())
            {
                throw new IllegalArgumentException("Cannot modify shared bookmarks");
            }
            s.setName(s1);
            s.setRss(flag);
        } else
        {
            bookmarks.addBookmarkedURL(s);
        }
        privateDataManager.setPrivateData(bookmarks);
    }

    public List getBookmarkedConferences()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        retrieveBookmarks();
        return Collections.unmodifiableList(bookmarks.getBookmarkedConferences());
    }

    public List getBookmarkedURLs()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        retrieveBookmarks();
        return Collections.unmodifiableList(bookmarks.getBookmarkedURLS());
    }

    public void removeBookmarkedConference(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        retrieveBookmarks();
        Iterator iterator = bookmarks.getBookmarkedConferences().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            BookmarkedConference bookmarkedconference = (BookmarkedConference)iterator.next();
            if (!bookmarkedconference.getJid().equalsIgnoreCase(s))
            {
                continue;
            }
            if (bookmarkedconference.isShared())
            {
                throw new IllegalArgumentException("Conference is shared and can't be removed");
            }
            iterator.remove();
            privateDataManager.setPrivateData(bookmarks);
            break;
        } while (true);
    }

    public void removeBookmarkedURL(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        retrieveBookmarks();
        Iterator iterator = bookmarks.getBookmarkedURLS().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            BookmarkedURL bookmarkedurl = (BookmarkedURL)iterator.next();
            if (!bookmarkedurl.getURL().equalsIgnoreCase(s))
            {
                continue;
            }
            if (bookmarkedurl.isShared())
            {
                throw new IllegalArgumentException("Cannot delete a shared bookmark.");
            }
            iterator.remove();
            privateDataManager.setPrivateData(bookmarks);
            break;
        } while (true);
    }

    static 
    {
        PrivateDataManager.addPrivateDataProvider("storage", "storage:bookmarks", new Bookmarks.Provider());
    }
}
