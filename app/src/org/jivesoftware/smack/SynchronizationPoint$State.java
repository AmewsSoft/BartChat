// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;


// Referenced classes of package org.jivesoftware.smack:
//            SynchronizationPoint

private static final class  extends Enum
{

    private static final Failure $VALUES[];
    public static final Failure Failure;
    public static final Failure Initial;
    public static final Failure NoResponse;
    public static final Failure RequestSent;
    public static final Failure Success;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smack/SynchronizationPoint$State, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        Initial = new <init>("Initial", 0);
        RequestSent = new <init>("RequestSent", 1);
        NoResponse = new <init>("NoResponse", 2);
        Success = new <init>("Success", 3);
        Failure = new <init>("Failure", 4);
        $VALUES = (new .VALUES[] {
            Initial, RequestSent, NoResponse, Success, Failure
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
