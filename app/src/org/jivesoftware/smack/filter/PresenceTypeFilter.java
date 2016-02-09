// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.Objects;

// Referenced classes of package org.jivesoftware.smack.filter:
//            FlexibleStanzaTypeFilter

public class PresenceTypeFilter extends FlexibleStanzaTypeFilter
{

    public static final PresenceTypeFilter AVAILABLE;
    public static final PresenceTypeFilter ERROR;
    public static final PresenceTypeFilter PROBE;
    public static final PresenceTypeFilter SUBSCRIBE;
    public static final PresenceTypeFilter SUBSCRIBED;
    public static final PresenceTypeFilter UNAVAILABLE;
    public static final PresenceTypeFilter UNSUBSCRIBE;
    public static final PresenceTypeFilter UNSUBSCRIBED;
    private final org.jivesoftware.smack.packet.Presence.Type type;

    private PresenceTypeFilter(org.jivesoftware.smack.packet.Presence.Type type1)
    {
        super(org/jivesoftware/smack/packet/Presence);
        type = (org.jivesoftware.smack.packet.Presence.Type)Objects.requireNonNull(type1, "type must not be null");
    }

    protected boolean acceptSpecific(Presence presence)
    {
        return presence.getType() == type;
    }

    protected volatile boolean acceptSpecific(Stanza stanza)
    {
        return acceptSpecific((Presence)stanza);
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(": type=").append(type).toString();
    }

    static 
    {
        AVAILABLE = new PresenceTypeFilter(org.jivesoftware.smack.packet.Presence.Type.available);
        UNAVAILABLE = new PresenceTypeFilter(org.jivesoftware.smack.packet.Presence.Type.unavailable);
        SUBSCRIBE = new PresenceTypeFilter(org.jivesoftware.smack.packet.Presence.Type.subscribe);
        SUBSCRIBED = new PresenceTypeFilter(org.jivesoftware.smack.packet.Presence.Type.subscribed);
        UNSUBSCRIBE = new PresenceTypeFilter(org.jivesoftware.smack.packet.Presence.Type.unsubscribe);
        UNSUBSCRIBED = new PresenceTypeFilter(org.jivesoftware.smack.packet.Presence.Type.unsubscribed);
        ERROR = new PresenceTypeFilter(org.jivesoftware.smack.packet.Presence.Type.error);
        PROBE = new PresenceTypeFilter(org.jivesoftware.smack.packet.Presence.Type.probe);
    }
}
