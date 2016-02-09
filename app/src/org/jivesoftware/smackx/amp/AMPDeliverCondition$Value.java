// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp;


// Referenced classes of package org.jivesoftware.smackx.amp:
//            AMPDeliverCondition

public static final class  extends Enum
{

    private static final stored $VALUES[];
    public static final stored direct;
    public static final stored forward;
    public static final stored gateway;
    public static final stored none;
    public static final stored stored;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/amp/AMPDeliverCondition$Value, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        direct = new <init>("direct", 0);
        forward = new <init>("forward", 1);
        gateway = new <init>("gateway", 2);
        none = new <init>("none", 3);
        stored = new <init>("stored", 4);
        $VALUES = (new .VALUES[] {
            direct, forward, gateway, none, stored
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
