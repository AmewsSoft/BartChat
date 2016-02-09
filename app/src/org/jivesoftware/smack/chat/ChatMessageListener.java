// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.chat;

import org.jivesoftware.smack.packet.Message;

// Referenced classes of package org.jivesoftware.smack.chat:
//            Chat

public interface ChatMessageListener
{

    public abstract void processMessage(Chat chat, Message message);
}
