// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.PresenceListener;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCUser;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChat, ParticipantStatusListener

class this._cls0
    implements StanzaListener
{

    final MultiUserChat this$0;

    public void processPacket(Stanza stanza)
    {
        Presence presence;
        String s;
        Object obj;
        boolean flag;
        presence = (Presence)stanza;
        s = presence.getFrom();
        obj = (new StringBuilder()).append(MultiUserChat.access$300(MultiUserChat.this)).append("/").append(MultiUserChat.access$400(MultiUserChat.this)).toString();
        flag = presence.getFrom().equals(obj);
        .SwitchMap.org.jivesoftware.smack.packet.Presence.Type[presence.getType().dinal()];
        JVM INSTR tableswitch 1 2: default 92
    //                   1 132
    //                   2 278;
           goto _L1 _L2 _L3
_L1:
        for (stanza = MultiUserChat.access$1000(MultiUserChat.this).iterator(); stanza.hasNext(); ((PresenceListener)stanza.next()).processPresence(presence)) { }
        break; /* Loop/switch isn't completed */
_L2:
        if ((Presence)MultiUserChat.access$500(MultiUserChat.this).put(s, presence) != null)
        {
            Object obj1 = MUCUser.from(stanza);
            obj = ((MUCUser) (obj1)).getItem().getAffiliation();
            obj1 = ((MUCUser) (obj1)).getItem().getRole();
            Object obj2 = MUCUser.from(stanza);
            stanza = ((MUCUser) (obj2)).getItem().getAffiliation();
            obj2 = ((MUCUser) (obj2)).getItem().getRole();
            MultiUserChat.access$600(MultiUserChat.this, ((MUCRole) (obj1)), ((MUCRole) (obj2)), flag, s);
            MultiUserChat.access$700(MultiUserChat.this, ((MUCAffiliation) (obj)), stanza, flag, s);
        } else
        if (!flag)
        {
            stanza = MultiUserChat.access$800(MultiUserChat.this).iterator();
            while (stanza.hasNext()) 
            {
                ((ParticipantStatusListener)stanza.next()).joined(s);
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        MultiUserChat.access$500(MultiUserChat.this).remove(s);
        stanza = MUCUser.from(stanza);
        if (stanza == null || !stanza.hasStatus())
        {
            continue; /* Loop/switch isn't completed */
        }
        MultiUserChat.access$900(MultiUserChat.this, stanza.getStatus(), presence.getFrom().equals(obj), stanza, s);
        continue; /* Loop/switch isn't completed */
        if (flag) goto _L1; else goto _L4
_L4:
        stanza = MultiUserChat.access$800(MultiUserChat.this).iterator();
        while (stanza.hasNext()) 
        {
            ((ParticipantStatusListener)stanza.next()).left(s);
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    sListener()
    {
        this$0 = MultiUserChat.this;
        super();
    }
}
