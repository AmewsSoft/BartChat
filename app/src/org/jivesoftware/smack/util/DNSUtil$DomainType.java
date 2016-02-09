// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;


// Referenced classes of package org.jivesoftware.smack.util:
//            DNSUtil

private static final class  extends Enum
{

    private static final Client $VALUES[];
    public static final Client Client;
    public static final Client Server;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smack/util/DNSUtil$DomainType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        Server = new <init>("Server", 0);
        Client = new <init>("Client", 1);
        $VALUES = (new .VALUES[] {
            Server, Client
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
