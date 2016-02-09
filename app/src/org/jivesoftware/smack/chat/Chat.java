// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.chat;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.util.StringUtils;

// Referenced classes of package org.jivesoftware.smack.chat:
//            ChatManager, ChatMessageListener

public class Chat
{

    private ChatManager chatManager;
    private final Set listeners = new CopyOnWriteArraySet();
    private String participant;
    private String threadID;

    Chat(ChatManager chatmanager, String s, String s1)
    {
        if (StringUtils.isEmpty(s1))
        {
            throw new IllegalArgumentException("Thread ID must not be null");
        } else
        {
            chatManager = chatmanager;
            participant = s;
            threadID = s1;
            return;
        }
    }

    public void addMessageListener(ChatMessageListener chatmessagelistener)
    {
        if (chatmessagelistener == null)
        {
            return;
        } else
        {
            listeners.add(chatmessagelistener);
            return;
        }
    }

    public void close()
    {
        chatManager.closeChat(this);
        listeners.clear();
    }

    public PacketCollector createCollector()
    {
        return chatManager.createPacketCollector(this);
    }

    void deliver(Message message)
    {
        message.setThread(threadID);
        for (Iterator iterator = listeners.iterator(); iterator.hasNext(); ((ChatMessageListener)iterator.next()).processMessage(this, message)) { }
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof Chat) && threadID.equals(((Chat)obj).getThreadID()) && participant.equals(((Chat)obj).getParticipant());
    }

    public Set getListeners()
    {
        return Collections.unmodifiableSet(listeners);
    }

    public String getParticipant()
    {
        return participant;
    }

    public String getThreadID()
    {
        return threadID;
    }

    public int hashCode()
    {
        return (threadID.hashCode() + 31) * 31 + participant.hashCode();
    }

    public void removeMessageListener(ChatMessageListener chatmessagelistener)
    {
        listeners.remove(chatmessagelistener);
    }

    public void sendMessage(String s)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Message message = new Message();
        message.setBody(s);
        sendMessage(message);
    }

    public void sendMessage(Message message)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        message.setTo(participant);
        message.setType(org.jivesoftware.smack.packet.Message.Type.chat);
        message.setThread(threadID);
        chatManager.sendMessage(this, message);
    }

    public String toString()
    {
        return (new StringBuilder()).append("Chat [(participant=").append(participant).append("), (thread=").append(threadID).append(")]").toString();
    }
}
