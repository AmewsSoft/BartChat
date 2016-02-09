// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;


// Referenced classes of package org.jivesoftware.smack:
//            SynchronizationPoint

static class ate
{

    static final int $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[];

    static 
    {
        $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State = new int[ate.values().length];
        try
        {
            $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[ate.Failure.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[ate.Initial.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[ate.NoResponse.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[ate.RequestSent.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror)
        {
            return;
        }
    }
}
