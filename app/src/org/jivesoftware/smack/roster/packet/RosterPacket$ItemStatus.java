// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster.packet;


// Referenced classes of package org.jivesoftware.smack.roster.packet:
//            RosterPacket

public static final class  extends Enum
{

    private static final unsubscribe $VALUES[];
    public static final unsubscribe SUBSCRIPTION_PENDING;
    public static final unsubscribe UNSUBSCRIPTION_PENDING;
    public static final unsubscribe subscribe;
    public static final unsubscribe unsubscribe;

    public static  fromString(String s)
    {
        if (s == null)
        {
            return null;
        }
        try
        {
            s = valueOf(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return null;
        }
        return s;
    }

    public static valueOf valueOf(String s)
    {
        return (valueOf)Enum.valueOf(org/jivesoftware/smack/roster/packet/RosterPacket$ItemStatus, s);
    }

    public static valueOf[] values()
    {
        return (valueOf[])$VALUES.clone();
    }

    static 
    {
        subscribe = new <init>("subscribe", 0);
        unsubscribe = new <init>("unsubscribe", 1);
        $VALUES = (new .VALUES[] {
            subscribe, unsubscribe
        });
        SUBSCRIPTION_PENDING = subscribe;
        UNSUBSCRIPTION_PENDING = unsubscribe;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
