// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.privacy;

import java.util.List;

public class PrivacyList
{

    private final boolean isActiveList;
    private final boolean isDefaultList;
    private final List items;
    private final String listName;

    protected PrivacyList(boolean flag, boolean flag1, String s, List list)
    {
        isActiveList = flag;
        isDefaultList = flag1;
        listName = s;
        items = list;
    }

    public List getItems()
    {
        return items;
    }

    public String getName()
    {
        return listName;
    }

    public boolean isActiveList()
    {
        return isActiveList;
    }

    public boolean isDefaultList()
    {
        return isDefaultList;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Privacy List: ").append(listName).append("(active:").append(isActiveList).append(", default:").append(isDefaultList).append(")").toString();
    }
}
