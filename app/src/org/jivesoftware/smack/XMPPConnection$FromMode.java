// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;


// Referenced classes of package org.jivesoftware.smack:
//            XMPPConnection

public static final class  extends Enum
{

    private static final USER $VALUES[];
    public static final USER OMITTED;
    public static final USER UNCHANGED;
    public static final USER USER;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smack/XMPPConnection$FromMode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        UNCHANGED = new <init>("UNCHANGED", 0);
        OMITTED = new <init>("OMITTED", 1);
        USER = new <init>("USER", 2);
        $VALUES = (new .VALUES[] {
            UNCHANGED, OMITTED, USER
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
