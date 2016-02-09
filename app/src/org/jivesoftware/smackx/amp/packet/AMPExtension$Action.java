// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp.packet;


// Referenced classes of package org.jivesoftware.smackx.amp.packet:
//            AMPExtension

public static final class  extends Enum
{

    private static final notify $VALUES[];
    public static final String ATTRIBUTE_NAME = "action";
    public static final notify alert;
    public static final notify drop;
    public static final notify error;
    public static final notify notify;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smackx/amp/packet/AMPExtension$Action, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        alert = new <init>("alert", 0);
        drop = new <init>("drop", 1);
        error = new <init>("error", 2);
        notify = new <init>("notify", 3);
        $VALUES = (new .VALUES[] {
            alert, drop, error, notify
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
