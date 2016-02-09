// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, PubSubElementType, Item

public class PublishItem extends NodeExtension
{

    protected Collection items;

    public PublishItem(String s, Collection collection)
    {
        super(PubSubElementType.PUBLISH, s);
        items = collection;
    }

    public PublishItem(String s, Item item)
    {
        super(PubSubElementType.PUBLISH, s);
        items = new ArrayList(1);
        items.add(item);
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder("<");
        stringbuilder.append(getElementName());
        stringbuilder.append(" node='");
        stringbuilder.append(getNode());
        stringbuilder.append("'>");
        for (Iterator iterator = items.iterator(); iterator.hasNext(); stringbuilder.append(((Item)iterator.next()).toXML())) { }
        stringbuilder.append("</publish>");
        return stringbuilder.toString();
    }
}
