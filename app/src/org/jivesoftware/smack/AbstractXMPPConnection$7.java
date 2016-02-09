// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack:
//            StanzaListener, AbstractXMPPConnection

class val.callback
    implements StanzaListener
{

    final AbstractXMPPConnection this$0;
    final StanzaListener val$callback;

    public void processPacket(Stanza stanza)
        throws edException
    {
        val$callback.processPacket(stanza);
        removeSyncStanzaListener(this);
        return;
        stanza;
        removeSyncStanzaListener(this);
        throw stanza;
    }

    edException()
    {
        this$0 = final_abstractxmppconnection;
        val$callback = StanzaListener.this;
        super();
    }
}
