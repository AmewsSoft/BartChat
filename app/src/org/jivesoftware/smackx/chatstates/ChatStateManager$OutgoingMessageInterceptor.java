// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.chatstates;

import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.chatstates.packet.ChatStateExtension;

// Referenced classes of package org.jivesoftware.smackx.chatstates:
//            ChatStateManager, ChatState

private class <init>
    implements MessageListener
{

    final ChatStateManager this$0;

    public void processMessage(Message message)
    {
        for (org.jivesoftware.smack.chat.Chat chat = ChatStateManager.access$200(ChatStateManager.this).getThreadChat(message.getThread()); chat == null || !ChatStateManager.access$300(ChatStateManager.this, chat, ChatState.active);)
        {
            return;
        }

        message.addExtension(new ChatStateExtension(ChatState.active));
    }

    private ()
    {
        this$0 = ChatStateManager.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
