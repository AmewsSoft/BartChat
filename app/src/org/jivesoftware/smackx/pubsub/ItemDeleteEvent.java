// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.List;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            SubscriptionEvent

public class ItemDeleteEvent extends SubscriptionEvent
{

    private List itemIds;

    public ItemDeleteEvent(String s, List list, List list1)
    {
        super(s, list1);
        itemIds = Collections.emptyList();
        if (list == null)
        {
            throw new IllegalArgumentException("deletedItemIds cannot be null");
        } else
        {
            itemIds = list;
            return;
        }
    }

    public List getItemIds()
    {
        return Collections.unmodifiableList(itemIds);
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append("  [subscriptions: ").append(getSubscriptions()).append("], [Deleted Items: ").append(itemIds).append(']').toString();
    }
}
