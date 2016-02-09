// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.muc.packet.MUCUser;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChat

class this._cls0
    implements StanzaListener
{

    final MultiUserChat this$0;

    public void processPacket(Stanza stanza)
    {
        stanza = MUCUser.from(stanza);
        if (stanza.getDecline() == null)
        {
            return;
        } else
        {
            MultiUserChat.access$1100(MultiUserChat.this, stanza.getDecline().getFrom(), stanza.getDecline().getReason());
            return;
        }
    }

    ecline()
    {
        this$0 = MultiUserChat.this;
        super();
    }
}
