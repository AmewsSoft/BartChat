// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster.packet;


// Referenced classes of package org.jivesoftware.smack.roster.packet:
//            RosterPacket

public static final class  extends Enum
{

    private static final remove $VALUES[];
    public static final remove both;
    public static final remove from;
    public static final remove none;
    public static final remove remove;
    public static final remove to;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smack/roster/packet/RosterPacket$ItemType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        none = new <init>("none", 0);
        to = new <init>("to", 1);
        from = new <init>("from", 2);
        both = new <init>("both", 3);
        remove = new <init>("remove", 4);
        $VALUES = (new .VALUES[] {
            none, to, from, both, remove
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
