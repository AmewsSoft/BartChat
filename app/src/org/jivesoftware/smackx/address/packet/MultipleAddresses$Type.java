// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.address.packet;


// Referenced classes of package org.jivesoftware.smackx.address.packet:
//            MultipleAddresses

public static final class  extends Enum
{

    private static final ofrom $VALUES[];
    public static final ofrom bcc;
    public static final ofrom cc;
    public static final ofrom noreply;
    public static final ofrom ofrom;
    public static final ofrom replyroom;
    public static final ofrom replyto;
    public static final ofrom to;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/address/packet/MultipleAddresses$Type, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        bcc = new <init>("bcc", 0);
        cc = new <init>("cc", 1);
        noreply = new <init>("noreply", 2);
        replyroom = new <init>("replyroom", 3);
        replyto = new <init>("replyto", 4);
        to = new <init>("to", 5);
        ofrom = new <init>("ofrom", 6);
        $VALUES = (new .VALUES[] {
            bcc, cc, noreply, replyroom, replyto, to, ofrom
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
