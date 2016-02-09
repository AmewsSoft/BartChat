// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            Subscription

public static final class  extends Enum
{

    private static final none $VALUES[];
    public static final none none;
    public static final none pending;
    public static final none subscribed;
    public static final none unconfigured;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/pubsub/Subscription$State, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        subscribed = new <init>("subscribed", 0);
        unconfigured = new <init>("unconfigured", 1);
        pending = new <init>("pending", 2);
        none = new <init>("none", 3);
        $VALUES = (new .VALUES[] {
            subscribed, unconfigured, pending, none
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
