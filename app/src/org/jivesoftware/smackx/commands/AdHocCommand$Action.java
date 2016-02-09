// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;


// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommand

public static final class  extends Enum
{

    private static final unknown $VALUES[];
    public static final unknown cancel;
    public static final unknown complete;
    public static final unknown execute;
    public static final unknown next;
    public static final unknown prev;
    public static final unknown unknown;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/commands/AdHocCommand$Action, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        execute = new <init>("execute", 0);
        cancel = new <init>("cancel", 1);
        prev = new <init>("prev", 2);
        next = new <init>("next", 3);
        complete = new <init>("complete", 4);
        unknown = new <init>("unknown", 5);
        $VALUES = (new .VALUES[] {
            execute, cancel, prev, next, complete, unknown
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
