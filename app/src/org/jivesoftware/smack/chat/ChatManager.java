// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.chat;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FlexibleStanzaTypeFilter;
import org.jivesoftware.smack.filter.FromMatchesFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.ThreadFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smack.chat:
//            Chat, ChatManagerListener, ChatMessageListener

public class ChatManager extends Manager
{
    public static final class MatchMode extends Enum
    {

        private static final MatchMode $VALUES[];
        public static final MatchMode BARE_JID;
        public static final MatchMode NONE;
        public static final MatchMode SUPPLIED_JID;

        public static MatchMode valueOf(String s)
        {
            return (MatchMode)Enum.valueOf(org/jivesoftware/smack/chat/ChatManager$MatchMode, s);
        }

        public static MatchMode[] values()
        {
            return (MatchMode[])$VALUES.clone();
        }

        static 
        {
            NONE = new MatchMode("NONE", 0);
            SUPPLIED_JID = new MatchMode("SUPPLIED_JID", 1);
            BARE_JID = new MatchMode("BARE_JID", 2);
            $VALUES = (new MatchMode[] {
                NONE, SUPPLIED_JID, BARE_JID
            });
        }

        private MatchMode(String s, int i)
        {
            super(s, i);
        }
    }


    private static final Map INSTANCES = new WeakHashMap();
    private static boolean defaultIsNormalInclude = true;
    private static MatchMode defaultMatchMode;
    private Map baseJidChats;
    private Set chatManagerListeners;
    private Map interceptors;
    private Map jidChats;
    private MatchMode matchMode;
    private boolean normalIncluded;
    private final StanzaFilter packetFilter;
    private Map threadChats;

    private ChatManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        packetFilter = new OrFilter(new StanzaFilter[] {
            MessageTypeFilter.CHAT, new FlexibleStanzaTypeFilter() {

                final ChatManager this$0;

                protected boolean acceptSpecific(Message message)
                {
                    boolean flag1 = false;
                    boolean flag = flag1;
                    if (normalIncluded)
                    {
                        flag = flag1;
                        if (message.getType() == org.jivesoftware.smack.packet.Message.Type.normal)
                        {
                            flag = true;
                        }
                    }
                    return flag;
                }

                protected volatile boolean acceptSpecific(Stanza stanza)
                {
                    return acceptSpecific((Message)stanza);
                }

            
            {
                this$0 = ChatManager.this;
                super();
            }
            }
        });
        normalIncluded = defaultIsNormalInclude;
        matchMode = defaultMatchMode;
        threadChats = new ConcurrentHashMap();
        jidChats = new ConcurrentHashMap();
        baseJidChats = new ConcurrentHashMap();
        chatManagerListeners = new CopyOnWriteArraySet();
        interceptors = new WeakHashMap();
        xmppconnection.addSyncStanzaListener(new StanzaListener() {

            final ChatManager this$0;

            public void processPacket(Stanza stanza)
            {
                Message message = (Message)stanza;
                Object obj;
                if (message.getThread() == null)
                {
                    stanza = getUserChat(message.getFrom());
                } else
                {
                    stanza = getThreadChat(message.getThread());
                }
                obj = stanza;
                if (stanza == null)
                {
                    obj = createChat(message);
                }
                if (obj == null)
                {
                    return;
                } else
                {
                    deliverMessage(((Chat) (obj)), message);
                    return;
                }
            }

            
            {
                this$0 = ChatManager.this;
                super();
            }
        }, packetFilter);
        INSTANCES.put(xmppconnection, this);
    }

    private Chat createChat(String s, String s1, boolean flag)
    {
        Chat chat = new Chat(this, s, s1);
        threadChats.put(s1, chat);
        jidChats.put(s, chat);
        baseJidChats.put(XmppStringUtils.parseBareJid(s), chat);
        for (s = chatManagerListeners.iterator(); s.hasNext(); ((ChatManagerListener)s.next()).chatCreated(chat, flag)) { }
        return chat;
    }

    private Chat createChat(Message message)
    {
        String s1 = message.getFrom();
        if (s1 == null)
        {
            return null;
        }
        String s = message.getThread();
        message = s;
        if (s == null)
        {
            message = nextID();
        }
        return createChat(s1, ((String) (message)), false);
    }

    private void deliverMessage(Chat chat, Message message)
    {
        chat.deliver(message);
    }

    public static ChatManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smack/chat/ChatManager;
        JVM INSTR monitorenter ;
        ChatManager chatmanager1 = (ChatManager)INSTANCES.get(xmppconnection);
        ChatManager chatmanager;
        chatmanager = chatmanager1;
        if (chatmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        chatmanager = new ChatManager(xmppconnection);
        org/jivesoftware/smack/chat/ChatManager;
        JVM INSTR monitorexit ;
        return chatmanager;
        xmppconnection;
        throw xmppconnection;
    }

    private Chat getUserChat(String s)
    {
        Chat chat;
        chat = null;
        break MISSING_BLOCK_LABEL_2;
        if (matchMode != MatchMode.NONE && s != null)
        {
            Chat chat1 = (Chat)jidChats.get(s);
            chat = chat1;
            if (chat1 == null)
            {
                chat = chat1;
                if (matchMode == MatchMode.BARE_JID)
                {
                    return (Chat)baseJidChats.get(XmppStringUtils.parseBareJid(s));
                }
            }
        }
        return chat;
    }

    private static String nextID()
    {
        return UUID.randomUUID().toString();
    }

    public static void setDefaultIsNormalIncluded(boolean flag)
    {
        defaultIsNormalInclude = flag;
    }

    public static void setDefaultMatchMode(MatchMode matchmode)
    {
        defaultMatchMode = matchmode;
    }

    public void addChatListener(ChatManagerListener chatmanagerlistener)
    {
        chatManagerListeners.add(chatmanagerlistener);
    }

    public void addOutgoingMessageInterceptor(MessageListener messagelistener)
    {
        addOutgoingMessageInterceptor(messagelistener, null);
    }

    public void addOutgoingMessageInterceptor(MessageListener messagelistener, StanzaFilter stanzafilter)
    {
        if (messagelistener == null)
        {
            return;
        } else
        {
            interceptors.put(messagelistener, stanzafilter);
            return;
        }
    }

    void closeChat(Chat chat)
    {
        threadChats.remove(chat.getThreadID());
        chat = chat.getParticipant();
        jidChats.remove(chat);
        baseJidChats.remove(XmppStringUtils.parseBareJid(chat));
    }

    public Chat createChat(String s)
    {
        return createChat(s, null);
    }

    public Chat createChat(String s, String s1, ChatMessageListener chatmessagelistener)
    {
        String s2 = s1;
        if (s1 == null)
        {
            s2 = nextID();
        }
        if ((Chat)threadChats.get(s2) != null)
        {
            throw new IllegalArgumentException("ThreadID is already used");
        } else
        {
            s = createChat(s, s2, true);
            s.addMessageListener(chatmessagelistener);
            return s;
        }
    }

    public Chat createChat(String s, ChatMessageListener chatmessagelistener)
    {
        return createChat(s, null, chatmessagelistener);
    }

    PacketCollector createPacketCollector(Chat chat)
    {
        return connection().createPacketCollector(new AndFilter(new StanzaFilter[] {
            new ThreadFilter(chat.getThreadID()), FromMatchesFilter.create(chat.getParticipant())
        }));
    }

    public Set getChatListeners()
    {
        return Collections.unmodifiableSet(chatManagerListeners);
    }

    public MatchMode getMatchMode()
    {
        return matchMode;
    }

    public Chat getThreadChat(String s)
    {
        return (Chat)threadChats.get(s);
    }

    public boolean isNormalIncluded()
    {
        return normalIncluded;
    }

    public void removeChatListener(ChatManagerListener chatmanagerlistener)
    {
        chatManagerListeners.remove(chatmanagerlistener);
    }

    void sendMessage(Chat chat, Message message)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        chat = interceptors.entrySet().iterator();
        do
        {
            if (!chat.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)chat.next();
            StanzaFilter stanzafilter = (StanzaFilter)entry.getValue();
            if (stanzafilter != null && stanzafilter.accept(message))
            {
                ((MessageListener)entry.getKey()).processMessage(message);
            }
        } while (true);
        if (message.getFrom() == null)
        {
            message.setFrom(connection().getUser());
        }
        connection().sendStanza(message);
    }

    public void setMatchMode(MatchMode matchmode)
    {
        matchMode = matchmode;
    }

    public void setNormalIncluded(boolean flag)
    {
        normalIncluded = flag;
    }

    static 
    {
        defaultMatchMode = MatchMode.BARE_JID;
    }




}
