// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.chat;


// Referenced classes of package org.jivesoftware.smack.chat:
//            ChatManager

public static final class  extends Enum
{

    private static final BARE_JID $VALUES[];
    public static final BARE_JID BARE_JID;
    public static final BARE_JID NONE;
    public static final BARE_JID SUPPLIED_JID;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(org/jivesoftware/smack/chat/ChatManager$MatchMode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        NONE = new <init>("NONE", 0);
        SUPPLIED_JID = new <init>("SUPPLIED_JID", 1);
        BARE_JID = new <init>("BARE_JID", 2);
        $VALUES = (new .VALUES[] {
            NONE, SUPPLIED_JID, BARE_JID
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
