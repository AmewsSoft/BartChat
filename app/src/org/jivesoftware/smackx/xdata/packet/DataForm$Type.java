// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdata.packet;

import java.util.Locale;

// Referenced classes of package org.jivesoftware.smackx.xdata.packet:
//            DataForm

public static final class  extends Enum
{

    private static final result $VALUES[];
    public static final result cancel;
    public static final result form;
    public static final result result;
    public static final result submit;

    public static  fromString(String s)
    {
        return valueOf(s.toLowerCase(Locale.US));
    }

    public static valueOf valueOf(String s)
    {
        return (valueOf)Enum.valueOf(org/jivesoftware/smackx/xdata/packet/DataForm$Type, s);
    }

    public static valueOf[] values()
    {
        return (valueOf[])$VALUES.clone();
    }

    static 
    {
        form = new <init>("form", 0);
        submit = new <init>("submit", 1);
        cancel = new <init>("cancel", 2);
        result = new <init>("result", 3);
        $VALUES = (new .VALUES[] {
            form, submit, cancel, result
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
