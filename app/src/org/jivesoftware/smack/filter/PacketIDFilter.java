// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter

public class PacketIDFilter
    implements StanzaFilter
{

    private final String packetID;

    public PacketIDFilter(String s)
    {
        StringUtils.requireNotNullOrEmpty(s, "Packet ID must not be null or empty.");
        packetID = s;
    }

    public PacketIDFilter(Stanza stanza)
    {
        this(stanza.getStanzaId());
    }

    public boolean accept(Stanza stanza)
    {
        return packetID.equals(stanza.getStanzaId());
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(": id=").append(packetID).toString();
    }
}
