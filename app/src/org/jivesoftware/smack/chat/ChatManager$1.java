// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.chat;

import org.jivesoftware.smack.filter.FlexibleStanzaTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.chat:
//            ChatManager

class zaTypeFilter extends FlexibleStanzaTypeFilter
{

    final ChatManager this$0;

    protected boolean acceptSpecific(Message message)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (ChatManager.access$000(ChatManager.this))
        {
            flag = flag1;
            if (message.getType() == org.jivesoftware.smack.packet.ormal)
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

    ()
    {
        this$0 = ChatManager.this;
        super();
    }
}
