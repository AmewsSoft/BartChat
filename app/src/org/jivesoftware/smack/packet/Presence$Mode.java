// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Locale;

// Referenced classes of package org.jivesoftware.smack.packet:
//            Presence

public static final class  extends Enum
{

    private static final dnd $VALUES[];
    public static final dnd available;
    public static final dnd away;
    public static final dnd chat;
    public static final dnd dnd;
    public static final dnd xa;

    public static  fromString(String s)
    {
        return valueOf(s.toLowerCase(Locale.US));
    }

    public static valueOf valueOf(String s)
    {
        return (valueOf)Enum.valueOf(org/jivesoftware/smack/packet/Presence$Mode, s);
    }

    public static valueOf[] values()
    {
        return (valueOf[])$VALUES.clone();
    }

    static 
    {
        chat = new <init>("chat", 0);
        available = new <init>("available", 1);
        away = new <init>("away", 2);
        xa = new <init>("xa", 3);
        dnd = new <init>("dnd", 4);
        $VALUES = (new .VALUES[] {
            chat, available, away, xa, dnd
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
