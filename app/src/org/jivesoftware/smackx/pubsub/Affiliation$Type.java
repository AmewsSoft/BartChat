// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            Affiliation

public static final class  extends Enum
{

    private static final publisher $VALUES[];
    public static final publisher member;
    public static final publisher none;
    public static final publisher outcast;
    public static final publisher owner;
    public static final publisher publisher;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/pubsub/Affiliation$Type, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        member = new <init>("member", 0);
        none = new <init>("none", 1);
        outcast = new <init>("outcast", 2);
        owner = new <init>("owner", 3);
        publisher = new <init>("publisher", 4);
        $VALUES = (new .VALUES[] {
            member, none, outcast, owner, publisher
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
