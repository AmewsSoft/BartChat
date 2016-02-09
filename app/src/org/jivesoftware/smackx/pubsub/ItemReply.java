// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


public final class ItemReply extends Enum
{

    private static final ItemReply $VALUES[];
    public static final ItemReply owner;
    public static final ItemReply publisher;

    private ItemReply(String s, int i)
    {
        super(s, i);
    }

    public static ItemReply valueOf(String s)
    {
        return (ItemReply)Enum.valueOf(org/jivesoftware/smackx/pubsub/ItemReply, s);
    }

    public static ItemReply[] values()
    {
        return (ItemReply[])$VALUES.clone();
    }

    static 
    {
        owner = new ItemReply("owner", 0);
        publisher = new ItemReply("publisher", 1);
        $VALUES = (new ItemReply[] {
            owner, publisher
        });
    }
}
