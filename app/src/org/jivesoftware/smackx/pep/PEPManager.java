// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pep;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.pep.packet.PEPEvent;
import org.jivesoftware.smackx.pep.packet.PEPItem;
import org.jivesoftware.smackx.pep.packet.PEPPubSub;

// Referenced classes of package org.jivesoftware.smackx.pep:
//            PEPListener

public class PEPManager
{

    private XMPPConnection connection;
    private StanzaFilter packetFilter;
    private StanzaListener packetListener;
    private List pepListeners;

    public PEPManager(XMPPConnection xmppconnection)
    {
        pepListeners = new ArrayList();
        packetFilter = new StanzaExtensionFilter("event", "http://jabber.org/protocol/pubsub#event");
        connection = xmppconnection;
        init();
    }

    private void firePEPListeners(String s, PEPEvent pepevent)
    {
        PEPListener apeplistener[];
        synchronized (pepListeners)
        {
            apeplistener = new PEPListener[pepListeners.size()];
            pepListeners.toArray(apeplistener);
        }
        for (int i = 0; i < apeplistener.length; i++)
        {
            apeplistener[i].eventReceived(s, pepevent);
        }

        break MISSING_BLOCK_LABEL_72;
        s;
        list;
        JVM INSTR monitorexit ;
        throw s;
    }

    private void init()
    {
        packetListener = new StanzaListener() {

            final PEPManager this$0;

            public void processPacket(Stanza stanza)
            {
                stanza = (Message)stanza;
                PEPEvent pepevent = (PEPEvent)stanza.getExtension("event", "http://jabber.org/protocol/pubsub#event");
                firePEPListeners(stanza.getFrom(), pepevent);
            }

            
            {
                this$0 = PEPManager.this;
                super();
            }
        };
        connection.addSyncStanzaListener(packetListener, packetFilter);
    }

    public void addPEPListener(PEPListener peplistener)
    {
        synchronized (pepListeners)
        {
            if (!pepListeners.contains(peplistener))
            {
                pepListeners.add(peplistener);
            }
        }
        return;
        peplistener;
        list;
        JVM INSTR monitorexit ;
        throw peplistener;
    }

    public void destroy()
    {
        if (connection != null)
        {
            connection.removeSyncStanzaListener(packetListener);
        }
    }

    protected void finalize()
        throws Throwable
    {
        destroy();
        super.finalize();
    }

    public void publish(PEPItem pepitem)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        pepitem = new PEPPubSub(pepitem);
        pepitem.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        connection.sendStanza(pepitem);
    }

    public void removePEPListener(PEPListener peplistener)
    {
        synchronized (pepListeners)
        {
            pepListeners.remove(peplistener);
        }
        return;
        peplistener;
        list;
        JVM INSTR monitorexit ;
        throw peplistener;
    }

}
