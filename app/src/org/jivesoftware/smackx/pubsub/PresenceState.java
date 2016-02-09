// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


public final class PresenceState extends Enum
{

    private static final PresenceState $VALUES[];
    public static final PresenceState away;
    public static final PresenceState chat;
    public static final PresenceState dnd;
    public static final PresenceState online;
    public static final PresenceState xa;

    private PresenceState(String s, int i)
    {
        super(s, i);
    }

    public static PresenceState valueOf(String s)
    {
        return (PresenceState)Enum.valueOf(org/jivesoftware/smackx/pubsub/PresenceState, s);
    }

    public static PresenceState[] values()
    {
        return (PresenceState[])$VALUES.clone();
    }

    static 
    {
        chat = new PresenceState("chat", 0);
        online = new PresenceState("online", 1);
        away = new PresenceState("away", 2);
        xa = new PresenceState("xa", 3);
        dnd = new PresenceState("dnd", 4);
        $VALUES = (new PresenceState[] {
            chat, online, away, xa, dnd
        });
    }
}
