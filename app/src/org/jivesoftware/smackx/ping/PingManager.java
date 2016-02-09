// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.ping;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.SmackExecutorThreadFactory;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.ping.packet.Ping;

// Referenced classes of package org.jivesoftware.smackx.ping:
//            PingFailedListener

public class PingManager extends Manager
{

    private static final Map INSTANCES = new WeakHashMap();
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/ping/PingManager.getName());
    private static int defaultPingInterval = 1800;
    private final ScheduledExecutorService executorService;
    private ScheduledFuture nextAutomaticPing;
    private final Set pingFailedListeners = Collections.synchronizedSet(new HashSet());
    private int pingInterval;
    private final Runnable pingServerRunnable = new Runnable() {

        final PingManager this$0;

        public void run()
        {
            PingManager.LOGGER.fine("ServerPingTask run()");
            pingServerIfNecessary();
        }

            
            {
                this$0 = PingManager.this;
                super();
            }
    };

    private PingManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        pingInterval = defaultPingInterval;
        executorService = Executors.newSingleThreadScheduledExecutor(new SmackExecutorThreadFactory(xmppconnection.getConnectionCounter(), "Ping"));
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("urn:xmpp:ping");
        xmppconnection.registerIQRequestHandler(new AbstractIqRequestHandler("ping", "urn:xmpp:ping", org.jivesoftware.smack.packet.IQ.Type.get, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async) {

            final PingManager this$0;

            public IQ handleIQRequest(IQ iq)
            {
                return ((Ping)iq).getPong();
            }

            
            {
                this$0 = PingManager.this;
                super(s, s1, type, mode);
            }
        });
        xmppconnection.addConnectionListener(new AbstractConnectionClosedListener() {

            final PingManager this$0;

            public void authenticated(XMPPConnection xmppconnection1, boolean flag)
            {
                maybeSchedulePingServerTask();
            }

            public void connectionTerminated()
            {
                maybeStopPingServerTask();
            }

            
            {
                this$0 = PingManager.this;
                super();
            }
        });
        maybeSchedulePingServerTask();
    }

    public static PingManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/ping/PingManager;
        JVM INSTR monitorenter ;
        PingManager pingmanager1 = (PingManager)INSTANCES.get(xmppconnection);
        PingManager pingmanager;
        pingmanager = pingmanager1;
        if (pingmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        pingmanager = new PingManager(xmppconnection);
        INSTANCES.put(xmppconnection, pingmanager);
        org/jivesoftware/smackx/ping/PingManager;
        JVM INSTR monitorexit ;
        return pingmanager;
        xmppconnection;
        throw xmppconnection;
    }

    private void maybeSchedulePingServerTask()
    {
        maybeSchedulePingServerTask(0);
    }

    private void maybeSchedulePingServerTask(int i)
    {
        this;
        JVM INSTR monitorenter ;
        maybeStopPingServerTask();
        if (pingInterval > 0)
        {
            int j = pingInterval - i;
            LOGGER.fine((new StringBuilder()).append("Scheduling ServerPingTask in ").append(j).append(" seconds (pingInterval=").append(pingInterval).append(", delta=").append(i).append(")").toString());
            nextAutomaticPing = executorService.schedule(pingServerRunnable, j, TimeUnit.SECONDS);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void maybeStopPingServerTask()
    {
        if (nextAutomaticPing != null)
        {
            nextAutomaticPing.cancel(true);
            nextAutomaticPing = null;
        }
    }

    public static void setDefaultPingInterval(int i)
    {
        defaultPingInterval = i;
    }

    protected void finalize()
        throws Throwable
    {
        LOGGER.fine("finalizing PingManager: Shutting down executor service");
        executorService.shutdown();
        super.finalize();
        return;
        Object obj;
        obj;
        LOGGER.log(Level.WARNING, "finalize() threw throwable", ((Throwable) (obj)));
        super.finalize();
        return;
        obj;
        super.finalize();
        throw obj;
    }

    public int getPingInterval()
    {
        return pingInterval;
    }

    public boolean isPingSupported(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(s, "urn:xmpp:ping");
    }

    public boolean ping(String s)
        throws org.jivesoftware.smack.SmackException.NotConnectedException, org.jivesoftware.smack.SmackException.NoResponseException
    {
        return ping(s, connection().getPacketReplyTimeout());
    }

    public boolean ping(String s, long l)
        throws org.jivesoftware.smack.SmackException.NotConnectedException, org.jivesoftware.smack.SmackException.NoResponseException
    {
        XMPPConnection xmppconnection = connection();
        if (!xmppconnection.isAuthenticated())
        {
            throw new org.jivesoftware.smack.SmackException.NotConnectedException();
        }
        Ping ping1 = new Ping(s);
        try
        {
            xmppconnection.createPacketCollectorAndSend(ping1).nextResultOrThrow(l);
        }
        catch (XMPPException xmppexception)
        {
            return s.equals(xmppconnection.getServiceName());
        }
        return true;
    }

    public boolean pingMyServer()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return pingMyServer(true);
    }

    public boolean pingMyServer(boolean flag)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return pingMyServer(flag, connection().getPacketReplyTimeout());
    }

    public boolean pingMyServer(boolean flag, long l)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        boolean flag1;
        try
        {
            flag1 = ping(connection().getServiceName(), l);
        }
        catch (org.jivesoftware.smack.SmackException.NoResponseException noresponseexception)
        {
            flag1 = false;
        }
        if (!flag1 && flag)
        {
            for (Iterator iterator = pingFailedListeners.iterator(); iterator.hasNext(); ((PingFailedListener)iterator.next()).pingFailed()) { }
        }
        return flag1;
    }

    public void pingServerIfNecessary()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = connection();
        if (obj != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (pingInterval <= 0) goto _L1; else goto _L3
_L3:
        long l = ((XMPPConnection) (obj)).getLastStanzaReceived();
        if (l <= 0L)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        int i = (int)((System.currentTimeMillis() - l) / 1000L);
        if (i >= pingInterval)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        maybeSchedulePingServerTask(i);
          goto _L1
        obj;
        throw obj;
        boolean flag = ((XMPPConnection) (obj)).isAuthenticated();
        if (!flag) goto _L5; else goto _L4
_L4:
        int j;
        flag = false;
        j = 0;
_L9:
        if (j >= 3) goto _L7; else goto _L6
_L6:
        if (j == 0)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        Thread.sleep(1000L);
        flag = pingMyServer(false);
_L8:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_195;
        }
_L7:
        if (flag)
        {
            break MISSING_BLOCK_LABEL_172;
        }
        Iterator iterator = pingFailedListeners.iterator();
        while (iterator.hasNext()) 
        {
            ((PingFailedListener)iterator.next()).pingFailed();
        }
          goto _L1
        Object obj1;
        obj1;
        LOGGER.log(Level.WARNING, "SmackError while pinging server", ((Throwable) (obj1)));
        flag = false;
          goto _L8
        maybeSchedulePingServerTask();
          goto _L1
_L5:
        LOGGER.warning("XMPPConnection was not authenticated");
          goto _L1
        obj1;
          goto _L1
        j++;
          goto _L9
    }

    public void registerPingFailedListener(PingFailedListener pingfailedlistener)
    {
        pingFailedListeners.add(pingfailedlistener);
    }

    public void setPingInterval(int i)
    {
        pingInterval = i;
        maybeSchedulePingServerTask();
    }

    public void unregisterPingFailedListener(PingFailedListener pingfailedlistener)
    {
        pingFailedListeners.remove(pingfailedlistener);
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                PingManager.getInstanceFor(xmppconnection);
            }

        });
    }



}
