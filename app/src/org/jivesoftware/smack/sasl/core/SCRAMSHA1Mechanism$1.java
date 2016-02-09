// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl.core;


// Referenced classes of package org.jivesoftware.smack.sasl.core:
//            SCRAMSHA1Mechanism

static class ate
{

    static final int $SwitchMap$org$jivesoftware$smack$sasl$core$SCRAMSHA1Mechanism$State[];

    static 
    {
        $SwitchMap$org$jivesoftware$smack$sasl$core$SCRAMSHA1Mechanism$State = new int[ate.values().length];
        try
        {
            $SwitchMap$org$jivesoftware$smack$sasl$core$SCRAMSHA1Mechanism$State[ate.AUTH_TEXT_SENT.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$org$jivesoftware$smack$sasl$core$SCRAMSHA1Mechanism$State[ate.RESPONSE_SENT.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror)
        {
            return;
        }
    }
}
