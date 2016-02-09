// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.chatstates;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;

// Referenced classes of package org.jivesoftware.smackx.chatstates:
//            ChatState

public interface ChatStateListener
    extends ChatMessageListener
{

    public abstract void stateChanged(Chat chat, ChatState chatstate);
}
