// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter

public final class StanzaTypeFilter
    implements StanzaFilter
{

    public static final StanzaTypeFilter IQ = new StanzaTypeFilter(org/jivesoftware/smack/packet/IQ);
    public static final StanzaTypeFilter MESSAGE = new StanzaTypeFilter(org/jivesoftware/smack/packet/Message);
    public static final StanzaTypeFilter PRESENCE = new StanzaTypeFilter(org/jivesoftware/smack/packet/Presence);
    private final Class packetType;

    public StanzaTypeFilter(Class class1)
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
