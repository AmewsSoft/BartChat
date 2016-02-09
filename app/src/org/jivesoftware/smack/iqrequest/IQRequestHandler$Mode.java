// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.iqrequest;


// Referenced classes of package org.jivesoftware.smack.iqrequest:
//            IQRequestHandler

public static final class  extends Enum
{

    private static final async $VALUES[];
    public static final async async;
    public static final async sync;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smack/iqrequest/IQRequestHandler$Mode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        sync = new <init>("sync", 0);
        async = new <init>("async", 1);
        $VALUES = (new .VALUES[] {
            sync, async
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
