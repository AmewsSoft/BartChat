// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack:
//            AbstractXMPPConnection, StanzaListener

class val.packet
    implements Runnable
{

    final AbstractXMPPConnection this$0;
    final List val$listenersToNotify;
    final Stanza val$packet;

    public void run()
    {
        for (Iterator iterator = val$listenersToNotify.iterator(); iterator.hasNext();)
        {
            StanzaListener stanzalistener = (StanzaListener)iterator.next();
            try
            {
                stanzalistener.processPacket(val$packet);
            }
            catch (Exception exception)
            {
                AbstractXMPPConnection.access$000().log(Level.WARNING, "Sending listener threw exception", exception);
            }
        }

    }

    ()
    {
        this$0 = final_abstractxmppconnection;
        val$listenersToNotify = list;
        val$packet = Stanza.this;
        super();
    }
}
