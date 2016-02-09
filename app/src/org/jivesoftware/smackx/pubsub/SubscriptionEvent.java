// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.List;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeEvent

public abstract class SubscriptionEvent extends NodeEvent
{

    private List subIds;

    protected SubscriptionEvent(String s)
    {
        super(s);
        subIds = Collections.emptyList();
    }

    protected SubscriptionEvent(String s, List list)
    {
        super(s);
        subIds = Collections.emptyList();
        if (list != null)
        {
            subIds = list;
        }
    }

    public List getSubscriptions()
    {
        return Collections.unmodifiableList(subIds);
    }

    protected void setSubscriptions(List list)
    {
        if (list != null)
        {
            subIds = list;
        }
    }
}
