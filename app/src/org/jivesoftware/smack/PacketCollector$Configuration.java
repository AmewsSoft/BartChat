// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.filter.StanzaFilter;

// Referenced classes of package org.jivesoftware.smack:
//            PacketCollector, SmackConfiguration

public static class <init>
{

    private PacketCollector collectorToReset;
    private StanzaFilter packetFilter;
    private int size;

    public <init> setCollectorToReset(PacketCollector packetcollector)
    {
        collectorToReset = packetcollector;
        return this;
    }

    public collectorToReset setPacketFilter(StanzaFilter stanzafilter)
    {
        return setStanzaFilter(stanzafilter);
    }

    public setStanzaFilter setSize(int i)
    {
        size = i;
        return this;
    }

    public size setStanzaFilter(StanzaFilter stanzafilter)
    {
        packetFilter = stanzafilter;
        return this;
    }




    private ()
    {
        size = SmackConfiguration.getPacketCollectorSize();
    }

    llectorSize(llectorSize llectorsize)
    {
        this();
    }
}
