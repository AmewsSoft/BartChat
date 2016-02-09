// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;


// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommand

public static final class  extends Enum
{

    private static final canceled $VALUES[];
    public static final canceled canceled;
    public static final canceled completed;
    public static final canceled executing;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/commands/AdHocCommand$Status, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        executing = new <init>("executing", 0);
        completed = new <init>("completed", 1);
        canceled = new <init>("canceled", 2);
        $VALUES = (new .VALUES[] {
            executing, completed, canceled
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
