// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;


public class HostedRoom
{

    private final String jid;
    private final String name;

    public HostedRoom(org.jivesoftware.smackx.disco.packet.DiscoverItems.Item item)
    {
        jid = item.getEntityID();
        name = item.getName();
    }

    public String getJid()
    {
        return jid;
    }

    public String getName()
    {
        return name;
    }
}
