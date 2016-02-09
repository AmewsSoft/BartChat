// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, PubSubElementType, Subscription

public class SubscriptionsExtension extends NodeExtension
{

    protected List items;

    public SubscriptionsExtension(String s, List list)
    {
        super(PubSubElementType.SUBSCRIPTIONS, s);
        items = Collections.emptyList();
        if (list != null)
        {
            items = list;
        }
    }

    public SubscriptionsExtension(List list)
    {
        super(PubSubElementType.SUBSCRIPTIONS);
        items = Collections.emptyList();
        if (list != null)
        {
            items = list;
        }
    }

    public List getSubscriptions()
    {
        return items;
    }

    public CharSequence toXML()
    {
        if (items == null || items.size() == 0)
        {
            return super.toXML();
        }
        StringBuilder stringbuilder = new StringBuilder("<");
        stringbuilder.append(getElementName());
        if (getNode() != null)
        {
            stringbuilder.append(" node='");
            stringbuilder.append(getNode());
            stringbuilder.append("'");
        }
        stringbuilder.append(">");
        for (Iterator iterator = items.iterator(); iterator.hasNext(); stringbuilder.append(((Subscription)iterator.next()).toXML())) { }
        stringbuilder.append("</");
        stringbuilder.append(getElementName());
        stringbuilder.append(">");
        return stringbuilder.toString();
    }
}
