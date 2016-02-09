// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.util.Locale;

public final class MUCAffiliation extends Enum
{

    private static final MUCAffiliation $VALUES[];
    public static final MUCAffiliation admin;
    public static final MUCAffiliation member;
    public static final MUCAffiliation none;
    public static final MUCAffiliation outcast;
    public static final MUCAffiliation owner;

    private MUCAffiliation(String s, int i)
    {
        super(s, i);
    }

    public static MUCAffiliation fromString(String s)
    {
        if (s == null)
        {
            return null;
        } else
        {
            return valueOf(s.toLowerCase(Locale.US));
        }
    }

    public static MUCAffiliation valueOf(String s)
    {
        return (MUCAffiliation)Enum.valueOf(org/jivesoftware/smackx/muc/MUCAffiliation, s);
    }

    public static MUCAffiliation[] values()
    {
        return (MUCAffiliation[])$VALUES.clone();
    }

    static 
    {
        owner = new MUCAffiliation("owner", 0);
        admin = new MUCAffiliation("admin", 1);
        member = new MUCAffiliation("member", 2);
        outcast = new MUCAffiliation("outcast", 3);
        none = new MUCAffiliation("none", 4);
        $VALUES = (new MUCAffiliation[] {
            owner, admin, member, outcast, none
        });
    }
}
