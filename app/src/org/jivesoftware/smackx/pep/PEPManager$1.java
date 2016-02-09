// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pep;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.pep.packet.PEPEvent;

// Referenced classes of package org.jivesoftware.smackx.pep:
//            PEPManager

class this._cls0
    implements StanzaListener
{

    final PEPManager this$0;

    public void processPacket(Stanza stanza)
    {
        stanza = (Message)stanza;
        PEPEvent pepevent = (PEPEvent)stanza.getExtension("event", "http://jabber.org/protocol/pubsub#event");
        PEPManager.access$000(PEPManager.this, stanza.getFrom(), pepevent);
    }

    nt()
    {
        this$0 = PEPManager.this;
        super();
    }
}
