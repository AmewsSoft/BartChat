// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.util.Iterator;
import java.util.Set;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChat

class this._cls0
    implements StanzaListener
{

    final MultiUserChat this$0;

    public void processPacket(Stanza stanza)
        throws org.jivesoftware.smack.otConnectedException
    {
        stanza = (Message)stanza;
        for (Iterator iterator = MultiUserChat.access$000(MultiUserChat.this).iterator(); iterator.hasNext(); ((MessageListener)iterator.next()).processMessage(stanza)) { }
    }

    nectedException()
    {
        this$0 = MultiUserChat.this;
        super();
    }
}
