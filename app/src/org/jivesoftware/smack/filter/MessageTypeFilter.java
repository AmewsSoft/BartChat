// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.filter:
//            FlexibleStanzaTypeFilter, OrFilter, StanzaFilter

public class MessageTypeFilter extends FlexibleStanzaTypeFilter
{

    public static final StanzaFilter CHAT;
    public static final StanzaFilter ERROR;
    public static final StanzaFilter GROUPCHAT;
    public static final StanzaFilter HEADLINE;
    public static final StanzaFilter NORMAL;
    public static final StanzaFilter NORMAL_OR_CHAT;
    public static final StanzaFilter NORMAL_OR_CHAT_OR_HEADLINE;
    private final org.jivesoftware.smack.packet.Message.Type type;

    private MessageTypeFilter(org.jivesoftware.smack.packet.Message.Type type1)
    {
        super(org/jivesoftware/smack/packet/Message);
        type = type1;
    }

    protected boolean acceptSpecific(Message message)
    {
        return message.getType() == type;
    }

    protected volatile boolean acceptSpecific(Stanza stanza)
    {
        return acceptSpecific((Message)stanza);
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(": type=").append(type).toString();
    }

    static 
    {
        NORMAL = new MessageTypeFilter(org.jivesoftware.smack.packet.Message.Type.normal);
        CHAT = new MessageTypeFilter(org.jivesoftware.smack.packet.Message.Type.chat);
        GROUPCHAT = new MessageTypeFilter(org.jivesoftware.smack.packet.Message.Type.groupchat);
        HEADLINE = new MessageTypeFilter(org.jivesoftware.smack.packet.Message.Type.headline);
        ERROR = new MessageTypeFilter(org.jivesoftware.smack.packet.Message.Type.error);
        NORMAL_OR_CHAT = new OrFilter(new StanzaFilter[] {
            NORMAL, CHAT
        });
        NORMAL_OR_CHAT_OR_HEADLINE = new OrFilter(new StanzaFilter[] {
            NORMAL_OR_CHAT, HEADLINE
        });
    }
}
