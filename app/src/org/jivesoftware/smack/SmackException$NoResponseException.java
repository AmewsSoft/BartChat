// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.filter.StanzaFilter;

// Referenced classes of package org.jivesoftware.smack:
//            SmackException, PacketCollector, XMPPConnection

public static class filter extends SmackException
{

    private static final long serialVersionUID = 0xa5785b3b891da66cL;
    private final StanzaFilter filter;

    public static filter newWith(XMPPConnection xmppconnection)
    {
        return newWith(xmppconnection, (StanzaFilter)null);
    }

    public static newWith newWith(XMPPConnection xmppconnection, PacketCollector packetcollector)
    {
        return newWith(xmppconnection, packetcollector.getStanzaFilter());
    }

    public static newWith newWith(XMPPConnection xmppconnection, StanzaFilter stanzafilter)
    {
        long l = xmppconnection.getPacketReplyTimeout();
        xmppconnection = new StringBuilder(256);
        xmppconnection.append((new StringBuilder()).append("No response received within reply timeout. Timeout was ").append(l).append("ms (~").append(l / 1000L).append("s). Used filter: ").toString());
        if (stanzafilter != null)
        {
            xmppconnection.append(stanzafilter.toString());
        } else
        {
            xmppconnection.append("No filter used or filter was 'null'");
        }
        xmppconnection.append('.');
        return new <init>(xmppconnection.toString(), stanzafilter);
    }

    public StanzaFilter getFilter()
    {
        return filter;
    }

    private (String s, StanzaFilter stanzafilter)
    {
        super(s);
        filter = stanzafilter;
    }
}
