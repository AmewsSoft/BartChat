// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.debugger;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.debugger:
//            AbstractDebugger

class val.connection
    implements StanzaListener
{

    final AbstractDebugger this$0;
    final XMPPConnection val$connection;

    public void processPacket(Stanza stanza)
    {
        if (AbstractDebugger.printInterpreted)
        {
            log((new StringBuilder()).append("RCV PKT (").append(val$connection.getConnectionCounter()).append("): ").append(stanza.toXML()).toString());
        }
    }

    ()
    {
        this$0 = final_abstractdebugger;
        val$connection = XMPPConnection.this;
        super();
    }
}
