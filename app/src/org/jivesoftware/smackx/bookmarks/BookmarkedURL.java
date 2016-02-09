// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bookmarks;


// Referenced classes of package org.jivesoftware.smackx.bookmarks:
//            SharedBookmark

public class BookmarkedURL
    implements SharedBookmark
{

    private final String URL;
    private boolean isRss;
    private boolean isShared;
    private String name;

    protected BookmarkedURL(String s)
    {
        URL = s;
    }

    protected BookmarkedURL(String s, String s1, boolean flag)
    {
        URL = s;
        name = s1;
        isRss = flag;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof BookmarkedURL))
        {
            return false;
        } else
        {
            return ((BookmarkedURL)obj).getURL().equalsIgnoreCase(URL);
        }
    }

    public String getName()
    {
        return name;
    }

    public String getURL()
    {
        return URL;
    }

    public boolean isRss()
    {
        return isRss;
    }

    public boolean isShared()
    {
        return isShared;
    }

    protected void setName(String s)
    {
        name = s;
    }

    protected void setRss(boolean flag)
    {
        isRss = flag;
    }

    protected void setShared(boolean flag)
    {
        isShared = flag;
    }
}
