// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdata;


// Referenced classes of package org.jivesoftware.smackx.xdata:
//            FormField

public static final class  extends Enum
{

    private static final text_single $VALUES[];
    public static final text_single bool;
    public static final text_single fixed;
    public static final text_single hidden;
    public static final text_single jid_multi;
    public static final text_single jid_single;
    public static final text_single list_multi;
    public static final text_single list_single;
    public static final text_single text_multi;
    public static final text_single text_private;
    public static final text_single text_single;

    public static  fromString(String s)
    {
        byte byte0;
        if (s == null)
        {
            return null;
        }
        byte0 = -1;
        s.hashCode();
        JVM INSTR tableswitch 64711720 64711720: default 32
    //                   64711720 64;
           goto _L1 _L2
_L1:
        switch (byte0)
        {
        default:
            return valueOf(s.replace('-', '_'));

        case 0: // '\0'
            return bool;
        }
_L2:
        if (s.equals("boolean"))
        {
            byte0 = 0;
        }
          goto _L1
    }

    public static bool valueOf(String s)
    {
        return (bool)Enum.valueOf(org/jivesoftware/smackx/xdata/FormField$Type, s);
    }

    public static bool[] values()
    {
        return (bool[])$VALUES.clone();
    }

    public String toString()
    {
        switch (itchMap.org.jivesoftware.smackx.xdata.FormField.Type[ordinal()])
        {
        default:
            return name().replace('_', '-');

        case 1: // '\001'
            return "boolean";
        }
    }

    static 
    {
        bool = new <init>("bool", 0);
        fixed = new <init>("fixed", 1);
        hidden = new <init>("hidden", 2);
        jid_multi = new <init>("jid_multi", 3);
        jid_single = new <init>("jid_single", 4);
        list_multi = new <init>("list_multi", 5);
        list_single = new <init>("list_single", 6);
        text_multi = new <init>("text_multi", 7);
        text_private = new <init>("text_private", 8);
        text_single = new <init>("text_single", 9);
        $VALUES = (new .VALUES[] {
            bool, fixed, hidden, jid_multi, jid_single, list_multi, list_single, text_multi, text_private, text_single
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
