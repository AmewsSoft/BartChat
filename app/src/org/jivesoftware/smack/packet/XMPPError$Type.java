// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;


// Referenced classes of package org.jivesoftware.smack.packet:
//            XMPPError

public static final class  extends Enum
{

    private static final CONTINUE $VALUES[];
    public static final CONTINUE AUTH;
    public static final CONTINUE CANCEL;
    public static final CONTINUE CONTINUE;
    public static final CONTINUE MODIFY;
    public static final CONTINUE WAIT;

    public static  fromString(String s)
    {
        return valueOf(s.toUpperCase());
    }

    public static valueOf valueOf(String s)
    {
        return (valueOf)Enum.valueOf(org/jivesoftware/smack/packet/XMPPError$Type, s);
    }

    public static valueOf[] values()
    {
        return (valueOf[])$VALUES.clone();
    }

    public String toString()
    {
        return name().toLowerCase();
    }

    static 
    {
        WAIT = new <init>("WAIT", 0);
        CANCEL = new <init>("CANCEL", 1);
        MODIFY = new <init>("MODIFY", 2);
        AUTH = new <init>("AUTH", 3);
        CONTINUE = new <init>("CONTINUE", 4);
        $VALUES = (new .VALUES[] {
            WAIT, CANCEL, MODIFY, AUTH, CONTINUE
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
