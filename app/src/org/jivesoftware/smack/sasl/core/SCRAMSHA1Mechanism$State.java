// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl.core;


// Referenced classes of package org.jivesoftware.smack.sasl.core:
//            SCRAMSHA1Mechanism

private static final class  extends Enum
{

    private static final VALID_SERVER_RESPONSE $VALUES[];
    public static final VALID_SERVER_RESPONSE AUTH_TEXT_SENT;
    public static final VALID_SERVER_RESPONSE INITIAL;
    public static final VALID_SERVER_RESPONSE RESPONSE_SENT;
    public static final VALID_SERVER_RESPONSE VALID_SERVER_RESPONSE;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smack/sasl/core/SCRAMSHA1Mechanism$State, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        INITIAL = new <init>("INITIAL", 0);
        AUTH_TEXT_SENT = new <init>("AUTH_TEXT_SENT", 1);
        RESPONSE_SENT = new <init>("RESPONSE_SENT", 2);
        VALID_SERVER_RESPONSE = new <init>("VALID_SERVER_RESPONSE", 3);
        $VALUES = (new .VALUES[] {
            INITIAL, AUTH_TEXT_SENT, RESPONSE_SENT, VALID_SERVER_RESPONSE
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
