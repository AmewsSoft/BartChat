// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Locale;

// Referenced classes of package org.jivesoftware.smack.packet:
//            Presence

public static final class  extends Enum
{

    private static final probe $VALUES[];
    public static final probe available;
    public static final probe error;
    public static final probe probe;
    public static final probe subscribe;
    public static final probe subscribed;
    public static final probe unavailable;
    public static final probe unsubscribe;
    public static final probe unsubscribed;

    public static  fromString(String s)
    {
        return valueOf(s.toLowerCase(Locale.US));
    }

    public static valueOf valueOf(String s)
    {
        return (valueOf)Enum.valueOf(org/jivesoftware/smack/packet/Presence$Type, s);
    }

    public static valueOf[] values()
    {
        return (valueOf[])$VALUES.clone();
    }

    static 
    {
        available = new <init>("available", 0);
        unavailable = new <init>("unavailable", 1);
        subscribe = new <init>("subscribe", 2);
        subscribed = new <init>("subscribed", 3);
        unsubscribe = new <init>("unsubscribe", 4);
        unsubscribed = new <init>("unsubscribed", 5);
        error = new <init>("error", 6);
        probe = new <init>("probe", 7);
        $VALUES = (new .VALUES[] {
            available, unavailable, subscribe, subscribed, unsubscribe, unsubscribed, error, probe
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
