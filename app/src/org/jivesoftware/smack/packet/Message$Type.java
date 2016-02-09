// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Locale;

// Referenced classes of package org.jivesoftware.smack.packet:
//            Message

public static final class  extends Enum
{

    private static final error $VALUES[];
    public static final error chat;
    public static final error error;
    public static final error groupchat;
    public static final error headline;
    public static final error normal;

    public static  fromString(String s)
    {
        return valueOf(s.toLowerCase(Locale.US));
    }

    public static valueOf valueOf(String s)
    {
        return (valueOf)Enum.valueOf(org/jivesoftware/smack/packet/Message$Type, s);
    }

    public static valueOf[] values()
    {
        return (valueOf[])$VALUES.clone();
    }

    static 
    {
        normal = new <init>("normal", 0);
        chat = new <init>("chat", 1);
        groupchat = new <init>("groupchat", 2);
        headline = new <init>("headline", 3);
        error = new <init>("error", 4);
        $VALUES = (new .VALUES[] {
            normal, chat, groupchat, headline, error
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
