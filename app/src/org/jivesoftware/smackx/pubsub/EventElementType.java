// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


public final class EventElementType extends Enum
{

    private static final EventElementType $VALUES[];
    public static final EventElementType collection;
    public static final EventElementType configuration;
    public static final EventElementType delete;
    public static final EventElementType items;
    public static final EventElementType purge;
    public static final EventElementType subscription;

    private EventElementType(String s, int i)
    {
        super(s, i);
    }

    public static EventElementType valueOf(String s)
    {
        return (EventElementType)Enum.valueOf(org/jivesoftware/smackx/pubsub/EventElementType, s);
    }

    public static EventElementType[] values()
    {
        return (EventElementType[])$VALUES.clone();
    }

    static 
    {
        collection = new EventElementType("collection", 0);
        configuration = new EventElementType("configuration", 1);
        delete = new EventElementType("delete", 2);
        items = new EventElementType("items", 3);
        purge = new EventElementType("purge", 4);
        subscription = new EventElementType("subscription", 5);
        $VALUES = (new EventElementType[] {
            collection, configuration, delete, items, purge, subscription
        });
    }
}
