// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.receipts;


// Referenced classes of package org.jivesoftware.smackx.receipts:
//            DeliveryReceiptManager

public static final class  extends Enum
{

    private static final always $VALUES[];
    public static final always always;
    public static final always disabled;
    public static final always ifIsSubscribed;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/receipts/DeliveryReceiptManager$AutoReceiptMode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        disabled = new <init>("disabled", 0);
        ifIsSubscribed = new <init>("ifIsSubscribed", 1);
        always = new <init>("always", 2);
        $VALUES = (new .VALUES[] {
            disabled, ifIsSubscribed, always
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
