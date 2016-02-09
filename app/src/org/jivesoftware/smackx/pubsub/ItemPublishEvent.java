// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.Date;
import java.util.List;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            SubscriptionEvent

public class ItemPublishEvent extends SubscriptionEvent
{

    private List items;
    private Date originalDate;

    public ItemPublishEvent(String s, List list)
    {
        super(s);
        items = list;
    }

    public ItemPublishEvent(String s, List list, List list1)
    {
        super(s, list1);
        items = list;
    }

    public ItemPublishEvent(String s, List list, List list1, Date date)
    {
        super(s, list1);
        items = list;
        if (date != null)
        {
            originalDate = date;
        }
    }

    public List getItems()
    {
        return Collections.unmodifiableList(items);
    }

    public Date getPublishedDate()
    {
        return originalDate;
    }

    public boolean isDelayed()
    {
        return originalDate != null;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(getClass().getName()).append("  [subscriptions: ").append(getSubscriptions()).append("], [Delayed: ");
        String s;
        if (isDelayed())
        {
            s = originalDate.toString();
        } else
        {
            s = "false";
        }
        return stringbuilder.append(s).append(']').toString();
    }
}
