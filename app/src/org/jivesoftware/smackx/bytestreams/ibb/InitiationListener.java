// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

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
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.jivesoftware.smackx.filetransfer.StreamNegotiator;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamManager, InBandBytestreamRequest

class InitiationListener extends AbstractIqRequestHandler
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/bytestreams/ibb/InitiationListener.getName());
    private final ExecutorService initiationListenerExecutor = Executors.newCachedThreadPool();
    private final InBandBytestreamManager manager;

    protected InitiationListener(InBandBytestreamManager inbandbytestreammanager)
    {
        super("open", "http://jabber.org/protocol/ibb", org.jivesoftware.smack.packet.IQ.Type.set, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async);
        manager = inbandbytestreammanager;
    }

    private void processRequest(Stanza stanza)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj = (Open)stanza;
        if (((Open) (obj)).getBlockSize() <= manager.getMaximumBlockSize()) goto _L2; else goto _L1
_L1:
        manager.replyResourceConstraintPacket(((IQ) (obj)));
_L4:
        return;
_L2:
        StreamNegotiator.signal((new StringBuilder()).append(((Open) (obj)).getFrom()).append('\t').append(((Open) (obj)).getSessionID()).toString(), ((IQ) (obj)));
        if (!manager.getIgnoredBytestreamRequests().remove(((Open) (obj)).getSessionID()))
        {
            stanza = new InBandBytestreamRequest(manager, ((Open) (obj)));
            BytestreamListener bytestreamlistener = manager.getUserListener(((Open) (obj)).getFrom());
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
        if (true) goto _L4; else goto _L3
_L3:
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
                    InitiationListener.LOGGER.log(Level.WARNING, "proccessRequest", notconnectedexception);
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
