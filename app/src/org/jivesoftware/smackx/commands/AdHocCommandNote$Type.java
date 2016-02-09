// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;


// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommandNote

public static final class  extends Enum
{

    private static final error $VALUES[];
    public static final error error;
    public static final error info;
    public static final error warn;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/commands/AdHocCommandNote$Type, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        info = new <init>("info", 0);
        warn = new <init>("warn", 1);
        error = new <init>("error", 2);
        $VALUES = (new .VALUES[] {
            info, warn, error
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
