// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;


// Referenced classes of package org.jivesoftware.smack.util:
//            DNSUtil

static class mainType
{

    static final int $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType[];

    static 
    {
        $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType = new int[mainType.values().length];
        try
        {
            $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType[mainType.Server.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType[mainType.Client.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror)
        {
            return;
        }
    }
}
