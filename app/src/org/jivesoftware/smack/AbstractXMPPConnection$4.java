// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack:
//            AbstractXMPPConnection, StanzaListener

class val.packet
    implements Runnable
{

    final AbstractXMPPConnection this$0;
    final Collection val$listenersToNotify;
    final Stanza val$packet;

    public void run()
    {
        Iterator iterator = val$listenersToNotify.iterator();
_L2:
        StanzaListener stanzalistener;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_55;
        }
        stanzalistener = (StanzaListener)iterator.next();
        stanzalistener.processPacket(val$packet);
        continue; /* Loop/switch isn't completed */
        edException edexception;
        edexception;
        AbstractXMPPConnection.access$000().log(Level.WARNING, "Got not connected exception, aborting", edexception);
        return;
        Exception exception;
        exception;
        AbstractXMPPConnection.access$000().log(Level.SEVERE, "Exception in packet listener", exception);
        if (true) goto _L2; else goto _L1
_L1:
    }

    edException()
    {
        this$0 = final_abstractxmppconnection;
        val$listenersToNotify = collection;
        val$packet = Stanza.this;
        super();
    }
}
