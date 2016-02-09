// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack:
//            AbstractXMPPConnection, StanzaListener

class val.packet
    implements Runnable
{

    final AbstractXMPPConnection this$0;
    final StanzaListener val$listener;
    final Stanza val$packet;

    public void run()
    {
        try
        {
            val$listener.processPacket(val$packet);
            return;
        }
        catch (Exception exception)
        {
            AbstractXMPPConnection.access$000().log(Level.SEVERE, "Exception in async packet listener", exception);
        }
    }

    ()
    {
        this$0 = final_abstractxmppconnection;
        val$listener = stanzalistener;
        val$packet = Stanza.this;
        super();
    }
}
