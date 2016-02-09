// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.tcp;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;

// Referenced classes of package org.jivesoftware.smack.tcp:
//            XMPPTCPConnection

class val.ackedStanzas
    implements Runnable
{

    final XMPPTCPConnection this$0;
    final List val$ackedStanzas;

    public void run()
    {
        Iterator iterator = val$ackedStanzas.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Stanza stanza = (Stanza)iterator.next();
            for (Iterator iterator1 = XMPPTCPConnection.access$4000(XMPPTCPConnection.this).iterator(); iterator1.hasNext();)
            {
                StanzaListener stanzalistener = (StanzaListener)iterator1.next();
                try
                {
                    stanzalistener.processPacket(stanza);
                }
                catch (org.jivesoftware.smack.nnectedException nnectedexception1)
                {
                    XMPPTCPConnection.access$2000().log(Level.FINER, "Received not connected exception", nnectedexception1);
                }
            }

            Object obj = stanza.getStanzaId();
            if (!StringUtils.isNullOrEmpty(((CharSequence) (obj))))
            {
                obj = (StanzaListener)XMPPTCPConnection.access$3900(XMPPTCPConnection.this).remove(obj);
                if (obj != null)
                {
                    try
                    {
                        ((StanzaListener) (obj)).processPacket(stanza);
                    }
                    catch (org.jivesoftware.smack.nnectedException nnectedexception)
                    {
                        XMPPTCPConnection.access$2000().log(Level.FINER, "Received not connected exception", nnectedexception);
                    }
                }
            }
        } while (true);
    }

    tedException()
    {
        this$0 = final_xmpptcpconnection;
        val$ackedStanzas = List.this;
        super();
    }
}
