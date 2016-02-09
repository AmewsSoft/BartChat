// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.chatstates;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.chatstates.packet.ChatStateExtension;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

// Referenced classes of package org.jivesoftware.smackx.chatstates:
//            ChatStateListener, ChatState

public class ChatStateManager extends Manager
{
    private class IncomingMessageInterceptor
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
            fireNewChatState(chat, message);
        }

        private IncomingMessageInterceptor()
        {
            this$0 = ChatStateManager.this;
            super();
        }

    }

    private class OutgoingMessageInterceptor
        implements MessageListener
    {

        final ChatStateManager this$0;

        public void processMessage(Message message)
        {
            for (Chat chat = chatManager.getThreadChat(message.getThread()); chat == null || !updateChatState(chat, ChatState.active);)
            {
                return;
            }

            message.addExtension(new ChatStateExtension(ChatState.active));
        }

        private OutgoingMessageInterceptor()
        {
            this$0 = ChatStateManager.this;
            super();
        }

    }


    private static final Map INSTANCES = new WeakHashMap();
    public static final String NAMESPACE = "http://jabber.org/protocol/chatstates";
    private static final StanzaFilter filter = new NotFilter(new StanzaExtensionFilter("http://jabber.org/protocol/chatstates"));
    private final ChatManager chatManager;
    private final Map chatStates = new WeakHashMap();
    private final IncomingMessageInterceptor incomingInterceptor = new IncomingMessageInterceptor();
    private final OutgoingMessageInterceptor outgoingInterceptor = new OutgoingMessageInterceptor();

    private ChatStateManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        chatManager = ChatManager.getInstanceFor(xmppconnection);
        chatManager.addOutgoingMessageInterceptor(outgoingInterceptor, filter);
        chatManager.addChatListener(incomingInterceptor);
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("http://jabber.org/protocol/chatstates");
        INSTANCES.put(xmppconnection, this);
    }

    private void fireNewChatState(Chat chat, ChatState chatstate)
    {
        Iterator iterator = chat.getListeners().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            ChatMessageListener chatmessagelistener = (ChatMessageListener)iterator.next();
            if (chatmessagelistener instanceof ChatStateListener)
            {
                ((ChatStateListener)chatmessagelistener).stateChanged(chat, chatstate);
            }
        } while (true);
    }

    public static ChatStateManager getInstance(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/chatstates/ChatStateManager;
        JVM INSTR monitorenter ;
        ChatStateManager chatstatemanager1 = (ChatStateManager)INSTANCES.get(xmppconnection);
        ChatStateManager chatstatemanager;
        chatstatemanager = chatstatemanager1;
        if (chatstatemanager1 != null)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        chatstatemanager = new ChatStateManager(xmppconnection);
        org/jivesoftware/smackx/chatstates/ChatStateManager;
        JVM INSTR monitorexit ;
        return chatstatemanager;
        xmppconnection;
        throw xmppconnection;
    }

    private boolean updateChatState(Chat chat, ChatState chatstate)
    {
        this;
        JVM INSTR monitorenter ;
        if ((ChatState)chatStates.get(chat) == chatstate) goto _L2; else goto _L1
_L1:
        chatStates.put(chat, chatstate);
        boolean flag = true;
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
        chat;
        throw chat;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        } else
        {
            obj = (ChatStateManager)obj;
            return connection().equals(((ChatStateManager) (obj)).connection());
        }
    }

    public int hashCode()
    {
        return connection().hashCode();
    }

    public void setCurrentState(ChatState chatstate, Chat chat)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (chat == null || chatstate == null)
        {
            throw new IllegalArgumentException("Arguments cannot be null.");
        }
        if (!updateChatState(chat, chatstate))
        {
            return;
        } else
        {
            Message message = new Message();
            message.addExtension(new ChatStateExtension(chatstate));
            chat.sendMessage(message);
            return;
        }
    }




}
