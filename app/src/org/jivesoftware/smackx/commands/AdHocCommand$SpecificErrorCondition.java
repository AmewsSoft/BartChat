// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;


// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommand

public static final class value extends Enum
{

    private static final sessionExpired $VALUES[];
    public static final sessionExpired badAction;
    public static final sessionExpired badLocale;
    public static final sessionExpired badPayload;
    public static final sessionExpired badSessionid;
    public static final sessionExpired malformedAction;
    public static final sessionExpired sessionExpired;
    private String value;

    public static value valueOf(String s)
    {
        return (value)Enum.valueOf(org/jivesoftware/smackx/commands/AdHocCommand$SpecificErrorCondition, s);
    }

    public static value[] values()
    {
        return (value[])$VALUES.clone();
    }

    public String toString()
    {
        return value;
    }

    static 
    {
        badAction = new <init>("badAction", 0, "bad-action");
        malformedAction = new <init>("malformedAction", 1, "malformed-action");
        badLocale = new <init>("badLocale", 2, "bad-locale");
        badPayload = new <init>("badPayload", 3, "bad-payload");
        badSessionid = new <init>("badSessionid", 4, "bad-sessionid");
        sessionExpired = new <init>("sessionExpired", 5, "session-expired");
        $VALUES = (new .VALUES[] {
            badAction, malformedAction, badLocale, badPayload, badSessionid, sessionExpired
        });
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        value = s1;
    }
}
