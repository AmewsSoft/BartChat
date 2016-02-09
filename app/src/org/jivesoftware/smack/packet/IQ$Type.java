// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Locale;

// Referenced classes of package org.jivesoftware.smack.packet:
//            IQ

public static final class  extends Enum
{

    private static final error $VALUES[];
    public static final error error;
    public static final error get;
    public static final error result;
    public static final error set;

    public static  fromString(String s)
    {
        return valueOf(s.toLowerCase(Locale.US));
    }

    public static valueOf valueOf(String s)
    {
        return (valueOf)Enum.valueOf(org/jivesoftware/smack/packet/IQ$Type, s);
    }

    public static valueOf[] values()
    {
        return (valueOf[])$VALUES.clone();
    }

    static 
    {
        get = new <init>("get", 0);
        set = new <init>("set", 1);
        result = new <init>("result", 2);
        error = new <init>("error", 3);
        $VALUES = (new .VALUES[] {
            get, set, result, error
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
