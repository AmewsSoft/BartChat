// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.util.Iterator;
import java.util.Set;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.muc.packet.MUCUser;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChatManager, InvitationListener

class this._cls0
    implements StanzaListener
{

    final MultiUserChatManager this$0;

    public void processPacket(Stanza stanza)
    {
        Message message = (Message)stanza;
        MUCUser mucuser = MUCUser.from(message);
        if (mucuser.getInvite() != null)
        {
            stanza = getMultiUserChat(stanza.getFrom());
            for (Iterator iterator = MultiUserChatManager.access$000(MultiUserChatManager.this).iterator(); iterator.hasNext(); ((InvitationListener)iterator.next()).invitationReceived(MultiUserChatManager.access$100(MultiUserChatManager.this), stanza, mucuser.getInvite()._mth0(), mucuser.getInvite().n(), mucuser.getPassword(), message)) { }
        }
    }

    ()
    {
        this$0 = MultiUserChatManager.this;
        super();
    }
}
