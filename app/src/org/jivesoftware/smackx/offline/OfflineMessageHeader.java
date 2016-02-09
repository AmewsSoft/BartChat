// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.offline;


public class OfflineMessageHeader
{

    private String jid;
    private String stamp;
    private String user;

    public OfflineMessageHeader(org.jivesoftware.smackx.disco.packet.DiscoverItems.Item item)
    {
        user = item.getEntityID();
        jid = item.getName();
        stamp = item.getNode();
    }

    public String getJid()
    {
        return jid;
    }

    public String getStamp()
    {
        return stamp;
    }

    public String getUser()
    {
        return user;
    }
}
