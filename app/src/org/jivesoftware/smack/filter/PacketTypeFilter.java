// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter

public class PacketTypeFilter
    implements StanzaFilter
{

    public static final PacketTypeFilter MESSAGE = new PacketTypeFilter(org/jivesoftware/smack/packet/Message);
    public static final PacketTypeFilter PRESENCE = new PacketTypeFilter(org/jivesoftware/smack/packet/Presence);
    private final Class packetType;

    public PacketTypeFilter(Class class1)
    {
        packetType = class1;
    }

    public boolean accept(Stanza stanza)
    {
        return packetType.isInstance(stanza);
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append(packetType.getName()).toString();
    }

}
