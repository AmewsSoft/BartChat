// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack:
//            AbstractXMPPConnection, StanzaListener

protected static class packetFilter
{

    private final StanzaFilter packetFilter;
    private final StanzaListener packetListener;

    public boolean filterMatches(Stanza stanza)
    {
        return packetFilter == null || packetFilter.accept(stanza);
    }

    public StanzaListener getListener()
    {
        return packetListener;
    }

    public (StanzaListener stanzalistener, StanzaFilter stanzafilter)
    {
        packetListener = stanzalistener;
        packetFilter = stanzafilter;
    }
}
