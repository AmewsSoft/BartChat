// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bookmarks;


// Referenced classes of package org.jivesoftware.smackx.bookmarks:
//            SharedBookmark

public class BookmarkedConference
    implements SharedBookmark
{

    private boolean autoJoin;
    private boolean isShared;
    private final String jid;
    private String name;
    private String nickname;
    private String password;

    protected BookmarkedConference(String s)
    {
        jid = s;
    }

    protected BookmarkedConference(String s, String s1, boolean flag, String s2, String s3)
    {
        name = s;
        jid = s1;
        autoJoin = flag;
        nickname = s2;
        password = s3;
    }

    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof BookmarkedConference))
        {
            return false;
        } else
        {
            return ((BookmarkedConference)obj).getJid().equalsIgnoreCase(jid);
        }
    }

    public String getJid()
    {
        return jid;
    }

    public String getName()
    {
        return name;
    }

    public String getNickname()
    {
        return nickname;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean isAutoJoin()
    {
        return autoJoin;
    }

    public boolean isShared()
    {
        return isShared;
    }

    protected void setAutoJoin(boolean flag)
    {
        autoJoin = flag;
    }

    protected void setName(String s)
    {
        name = s;
    }

    protected void setNickname(String s)
    {
        nickname = s;
    }

    protected void setPassword(String s)
    {
        password = s;
    }

    protected void setShared(boolean flag)
    {
        isShared = flag;
    }
}
