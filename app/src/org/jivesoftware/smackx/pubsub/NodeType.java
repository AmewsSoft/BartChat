// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


public final class NodeType extends Enum
{

    private static final NodeType $VALUES[];
    public static final NodeType collection;
    public static final NodeType leaf;

    private NodeType(String s, int i)
    {
        super(s, i);
    }

    public static NodeType valueOf(String s)
    {
        return (NodeType)Enum.valueOf(org/jivesoftware/smackx/pubsub/NodeType, s);
    }

    public static NodeType[] values()
    {
        return (NodeType[])$VALUES.clone();
    }

    static 
    {
        leaf = new NodeType("leaf", 0);
        collection = new NodeType("collection", 1);
        $VALUES = (new NodeType[] {
            leaf, collection
        });
    }
}
