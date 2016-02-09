// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;


// Referenced classes of package org.jivesoftware.smack:
//            ConnectionConfiguration

public static final class A extends Enum
{

    private static final disabled $VALUES[];
    public static final disabled disabled;
    public static final disabled ifpossible;
    public static final disabled required;

    public static A valueOf(String s)
    {
        return (A)Enum.valueOf(org/jivesoftware/smack/ConnectionConfiguration$SecurityMode, s);
    }

    public static A[] values()
    {
        return (A[])$VALUES.clone();
    }

    static 
    {
        required = new <init>("required", 0);
        ifpossible = new <init>("ifpossible", 1);
        disabled = new <init>("disabled", 2);
        $VALUES = (new .VALUES[] {
            required, ifpossible, disabled
        });
    }

    private A(String s, int i)
    {
        super(s, i);
    }
}
