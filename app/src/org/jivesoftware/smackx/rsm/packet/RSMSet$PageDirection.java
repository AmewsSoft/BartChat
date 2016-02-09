// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.rsm.packet;


// Referenced classes of package org.jivesoftware.smackx.rsm.packet:
//            RSMSet

public static final class  extends Enum
{

    private static final after $VALUES[];
    public static final after after;
    public static final after before;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/rsm/packet/RSMSet$PageDirection, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        before = new <init>("before", 0);
        after = new <init>("after", 1);
        $VALUES = (new .VALUES[] {
            before, after
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
