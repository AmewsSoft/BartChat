// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.compression;


// Referenced classes of package org.jivesoftware.smack.compression:
//            XMPPInputOutputStream

public static final class  extends Enum
{

    private static final SYNC_FLUSH $VALUES[];
    public static final SYNC_FLUSH FULL_FLUSH;
    public static final SYNC_FLUSH SYNC_FLUSH;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smack/compression/XMPPInputOutputStream$FlushMethod, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        FULL_FLUSH = new <init>("FULL_FLUSH", 0);
        SYNC_FLUSH = new <init>("SYNC_FLUSH", 1);
        $VALUES = (new .VALUES[] {
            FULL_FLUSH, SYNC_FLUSH
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
