// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.chatstates;


public final class ChatState extends Enum
{

    private static final ChatState $VALUES[];
    public static final ChatState active;
    public static final ChatState composing;
    public static final ChatState gone;
    public static final ChatState inactive;
    public static final ChatState paused;

    private ChatState(String s, int i)
    {
        super(s, i);
    }

    public static ChatState valueOf(String s)
    {
        return (ChatState)Enum.valueOf(org/jivesoftware/smackx/chatstates/ChatState, s);
    }

    public static ChatState[] values()
    {
        return (ChatState[])$VALUES.clone();
    }

    static 
    {
        active = new ChatState("active", 0);
        composing = new ChatState("composing", 1);
        paused = new ChatState("paused", 2);
        inactive = new ChatState("inactive", 3);
        gone = new ChatState("gone", 4);
        $VALUES = (new ChatState[] {
            active, composing, paused, inactive, gone
        });
    }
}
