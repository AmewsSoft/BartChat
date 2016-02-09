// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.util.Iterator;
import java.util.Set;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChat, SubjectUpdatedListener

class this._cls0
    implements StanzaListener
{

    final MultiUserChat this$0;

    public void processPacket(Stanza stanza)
    {
        stanza = (Message)stanza;
        MultiUserChat.access$102(MultiUserChat.this, stanza.getSubject());
        for (Iterator iterator = MultiUserChat.access$200(MultiUserChat.this).iterator(); iterator.hasNext(); ((SubjectUpdatedListener)iterator.next()).subjectUpdated(MultiUserChat.access$100(MultiUserChat.this), stanza.getFrom())) { }
    }

    stener()
    {
        this$0 = MultiUserChat.this;
        super();
    }
}
