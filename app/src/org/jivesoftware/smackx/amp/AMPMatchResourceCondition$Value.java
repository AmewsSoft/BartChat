// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp;


// Referenced classes of package org.jivesoftware.smackx.amp:
//            AMPMatchResourceCondition

public static final class A extends Enum
{

    private static final other $VALUES[];
    public static final other any;
    public static final other exact;
    public static final other other;

    public static A valueOf(String s)
    {
        return (A)Enum.valueOf(org/jivesoftware/smackx/amp/AMPMatchResourceCondition$Value, s);
    }

    public static A[] values()
    {
        return (A[])$VALUES.clone();
    }

    static 
    {
        any = new <init>("any", 0);
        exact = new <init>("exact", 1);
        other = new <init>("other", 2);
        $VALUES = (new .VALUES[] {
            any, exact, other
        });
    }

    private A(String s, int i)
    {
        super(s, i);
    }
}
