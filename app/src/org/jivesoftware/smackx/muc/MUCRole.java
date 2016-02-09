// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.util.Locale;

public final class MUCRole extends Enum
{

    private static final MUCRole $VALUES[];
    public static final MUCRole moderator;
    public static final MUCRole none;
    public static final MUCRole participant;
    public static final MUCRole visitor;

    private MUCRole(String s, int i)
    {
        super(s, i);
    }

    public static MUCRole fromString(String s)
    {
        if (s == null)
        {
            return null;
        } else
        {
            return valueOf(s.toLowerCase(Locale.US));
        }
    }

    public static MUCRole valueOf(String s)
    {
        return (MUCRole)Enum.valueOf(org/jivesoftware/smackx/muc/MUCRole, s);
    }

    public static MUCRole[] values()
    {
        return (MUCRole[])$VALUES.clone();
    }

    static 
    {
        moderator = new MUCRole("moderator", 0);
        none = new MUCRole("none", 1);
        participant = new MUCRole("participant", 2);
        visitor = new MUCRole("visitor", 3);
        $VALUES = (new MUCRole[] {
            moderator, none, participant, visitor
        });
    }
}
