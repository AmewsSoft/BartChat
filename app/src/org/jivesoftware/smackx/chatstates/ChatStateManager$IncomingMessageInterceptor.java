// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.chatstates;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;

// Referenced classes of package org.jivesoftware.smackx.chatstates:
//            ChatStateManager, ChatState

private class <init>
    implements ChatManagerListener, ChatMessageListener
{

    final ChatStateManager this$0;

    public void chatCreated(Chat chat, boolean flag)
    {
        chat.addMessageListener(this);
    }

    public void processMessage(Chat chat, Message message)
    {
        message = message.getExtension("http://jabber.org/protocol/chatstates");
        if (message == null)
        {
            return;
        }
        try
        {
            message = ChatState.valueOf(message.getElementName());
        }
        // Misplaced declaration of an exception variable
        catch (Chat chat)
        {
            return;
        }
        ChatStateManager.access$400(ChatStateManager.this, chat, message);
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
