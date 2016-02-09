// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


public abstract class NodeEvent
{

    private String nodeId;

    protected NodeEvent(String s)
    {
        nodeId = s;
    }

    public String getNodeId()
    {
        return nodeId;
    }
}
