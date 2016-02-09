// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.rsm;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.jivesoftware.smack.util.PacketUtil;
import org.jivesoftware.smackx.rsm.packet.RSMSet;

public class RSMManager
{

    public RSMManager()
    {
    }

    Collection continuePage(int i, Collection collection)
    {
        return continuePage(i, collection, null);
    }

    Collection continuePage(int i, Collection collection, Collection collection1)
    {
        if (collection == null)
        {
            throw new IllegalArgumentException("returnedExtensions must no be null");
        }
        Object obj = collection1;
        if (collection1 == null)
        {
            obj = new LinkedList();
        }
        collection = (RSMSet)PacketUtil.extensionElementFrom(collection, "set", "http://jabber.org/protocol/rsm");
        if (collection == null)
        {
            throw new IllegalArgumentException("returnedExtensions did not contain a RSMset");
        } else
        {
            ((Collection) (obj)).add(new RSMSet(i, collection.getLast(), org.jivesoftware.smackx.rsm.packet.RSMSet.PageDirection.after));
            return ((Collection) (obj));
        }
    }

    Collection page(int i)
    {
        LinkedList linkedlist = new LinkedList();
        linkedlist.add(new RSMSet(i));
        return linkedlist;
    }
}
