// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.filetransfer.StreamNegotiator;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            Socks5BytestreamManager, Socks5BytestreamRequest

final class InitiationListener extends AbstractIqRequestHandler
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/bytestreams/socks5/InitiationListener.getName());
    private final ExecutorService initiationListenerExecutor = Executors.newCachedThreadPool();
    private final Socks5BytestreamManager manager;

    protected InitiationListener(Socks5BytestreamManager socks5bytestreammanager)
    {
        super("query", "http://jabber.org/protocol/bytestreams", org.jivesoftware.smack.packet.IQ.Type.set, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async);
        manager = socks5bytestreammanager;
    }

    private void processRequest(Stanza stanza)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj = (Bytestream)stanza;
        StreamNegotiator.signal((new StringBuilder()).append(((Bytestream) (obj)).getFrom()).append('\t').append(((Bytestream) (obj)).getSessionID()).toString(), ((IQ) (obj)));
        if (!manager.getIgnoredBytestreamRequests().remove(((Bytestream) (obj)).getSessionID()))
        {
            stanza = new Socks5BytestreamRequest(manager, ((Bytestream) (obj)));
            BytestreamListener bytestreamlistener = manager.getUserListener(((Bytestream) (obj)).getFrom());
            if (bytestreamlistener != null)
            {
                bytestreamlistener.incomingBytestreamRequest(stanza);
                return;
            }
            if (!manager.getAllRequestListeners().isEmpty())
            {
                obj = manager.getAllRequestListeners().iterator();
                while (((Iterator) (obj)).hasNext()) 
                {
                    ((BytestreamListener)((Iterator) (obj)).next()).incomingBytestreamRequest(stanza);
                }
            } else
            {
                manager.replyRejectPacket(((IQ) (obj)));
                return;
            }
        }
    }

    public IQ handleIQRequest(final IQ packet)
    {
        initiationListenerExecutor.execute(new Runnable() {

            final InitiationListener this$0;
            final IQ val$packet;

            public void run()
            {
                try
                {
                    processRequest(packet);
                    return;
                }
                catch (org.jivesoftware.smack.SmackException.NotConnectedException notconnectedexception)
                {
                    InitiationListener.LOGGER.log(Level.WARNING, "process request", notconnectedexception);
                }
            }

            
            {
                this$0 = InitiationListener.this;
                packet = iq;
                super();
            }
        });
        return null;
    }

    protected void shutdown()
    {
        initiationListenerExecutor.shutdownNow();
    }



}
