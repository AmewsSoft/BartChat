// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.chat;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.chat:
//            ChatManager

class this._cls0
    implements StanzaListener
{

    final ChatManager this$0;

    public void processPacket(Stanza stanza)
    {
        Message message = (Message)stanza;
        Object obj;
        if (message.getThread() == null)
        {
            stanza = ChatManager.access$100(ChatManager.this, message.getFrom());
        } else
        {
            stanza = getThreadChat(message.getThread());
        }
        obj = stanza;
        if (stanza == null)
        {
            obj = ChatManager.access$200(ChatManager.this, message);
        }
        if (obj == null)
        {
            return;
        } else
        {
            ChatManager.access$300(ChatManager.this, ((Chat) (obj)), message);
            return;
        }
    }

    ()
    {
        this$0 = ChatManager.this;
        super();
    }
}
