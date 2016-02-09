// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;


// Referenced classes of package org.jivesoftware.smack.packet:
//            StreamOpen

public static final class  extends Enum
{

    private static final server $VALUES[];
    public static final server client;
    public static final server server;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smack/packet/StreamOpen$StreamContentNamespace, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        client = new <init>("client", 0);
        server = new <init>("server", 1);
        $VALUES = (new .VALUES[] {
            client, server
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
