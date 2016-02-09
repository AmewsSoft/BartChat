// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;


// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamManager

public static final class  extends Enum
{

    private static final MESSAGE $VALUES[];
    public static final MESSAGE IQ;
    public static final MESSAGE MESSAGE;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/bytestreams/ibb/InBandBytestreamManager$StanzaType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        IQ = new <init>("IQ", 0);
        MESSAGE = new <init>("MESSAGE", 1);
        $VALUES = (new .VALUES[] {
            IQ, MESSAGE
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
