// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.util.Iterator;
import java.util.Set;
import org.jivesoftware.smack.PresenceListener;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChat

class this._cls0
    implements StanzaListener
{

    final MultiUserChat this$0;

    public void processPacket(Stanza stanza)
    {
        stanza = (Presence)stanza;
        for (Iterator iterator = MultiUserChat.access$1200(MultiUserChat.this).iterator(); iterator.hasNext(); ((PresenceListener)iterator.next()).processPresence(stanza)) { }
    }

    ()
    {
        this$0 = MultiUserChat.this;
        super();
    }
}
