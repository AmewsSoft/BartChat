// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.util.Async;

// Referenced classes of package org.jivesoftware.smack:
//            XMPPConnectionRegistry, XMPPConnection, ConnectionListener, AbstractXMPPConnection, 
//            ConnectionCreationListener, AbstractConnectionListener

public class ReconnectionManager
{

    private static final Map INSTANCES = new WeakHashMap();
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/ReconnectionManager.getName());
    private static boolean enabledPerDefault = false;
    private boolean automaticReconnectEnabled;
    private final ConnectionListener connectionListener = new AbstractConnectionListener() {

        final ReconnectionManager this$0;

        public void authenticated(XMPPConnection xmppconnection, boolean flag)
        {
            done = false;
        }

        public void connectionClosed()
        {
            done = true;
        }

        public void connectionClosedOnError(Exception exception)
        {
            done = false;
            if (isAutomaticReconnectEnabled())
            {
                if (!(exception instanceof XMPPException.StreamErrorException) || org.jivesoftware.smack.packet.StreamError.Condition.conflict != (exception = ((XMPPException.StreamErrorException)exception).getStreamError()).getCondition())
                {
                    reconnect();
                    return;
                }
            }
        }

            
            {
                this$0 = ReconnectionManager.this;
                super();
            }
    };
    boolean done;
    private final int randomBase = (new Random()).nextInt(13) + 2;
    private final Runnable reconnectionRunnable = new Thread() {

        private int attempts;
        final ReconnectionManager this$0;

        private int timeDelay()
        {
            attempts = attempts + 1;
            if (attempts > 13)
            {
                return randomBase * 6 * 5;
            }
            if (attempts > 7)
            {
                return randomBase * 6;
            } else
            {
                return randomBase;
            }
        }

        public void run()
        {
            AbstractXMPPConnection abstractxmppconnection1 = (AbstractXMPPConnection)weakRefConnection.get();
            if (abstractxmppconnection1 != null) goto _L2; else goto _L1
_L1:
            return;
_L8:
            interruptedexception = abstractxmppconnection1.connectionListeners.iterator();
            while (interruptedexception.hasNext()) 
            {
                ((ConnectionListener)interruptedexception.next()).reconnectingIn(0);
            }
            try
            {
                if (isReconnectionPossible(abstractxmppconnection1))
                {
                    abstractxmppconnection1.connect();
                }
            }
            catch (Exception exception)
            {
                Iterator iterator1 = abstractxmppconnection1.connectionListeners.iterator();
                while (iterator1.hasNext()) 
                {
                    ((ConnectionListener)iterator1.next()).reconnectionFailed(exception);
                }
            }
_L2:
            if (!isReconnectionPossible(abstractxmppconnection1)) goto _L1; else goto _L3
_L3:
            i = timeDelay();
_L7:
            if (!isReconnectionPossible(abstractxmppconnection1) || i <= 0)
            {
                break MISSING_BLOCK_LABEL_135;
            }
            Thread.sleep(1000L);
            j = i - 1;
            iterator = abstractxmppconnection1.connectionListeners.iterator();
_L5:
            i = j;
            if (!iterator.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            ((ConnectionListener)iterator.next()).reconnectingIn(j);
            if (true) goto _L5; else goto _L4
_L4:
            if (true) goto _L7; else goto _L6
_L6:
            interruptedexception;
            ReconnectionManager.LOGGER.log(Level.FINE, "waiting for reconnection interrupted", interruptedexception);
              goto _L8
        }

            
            {
                this$0 = ReconnectionManager.this;
                super();
                attempts = 0;
            }
    };
    private Thread reconnectionThread;
    private final WeakReference weakRefConnection;

    private ReconnectionManager(AbstractXMPPConnection abstractxmppconnection)
    {
        automaticReconnectEnabled = false;
        done = false;
        weakRefConnection = new WeakReference(abstractxmppconnection);
        if (getEnabledPerDefault())
        {
            enableAutomaticReconnection();
        }
    }

    public static boolean getEnabledPerDefault()
    {
        return enabledPerDefault;
    }

    public static ReconnectionManager getInstanceFor(AbstractXMPPConnection abstractxmppconnection)
    {
        org/jivesoftware/smack/ReconnectionManager;
        JVM INSTR monitorenter ;
        ReconnectionManager reconnectionmanager1 = (ReconnectionManager)INSTANCES.get(abstractxmppconnection);
        ReconnectionManager reconnectionmanager;
        reconnectionmanager = reconnectionmanager1;
        if (reconnectionmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        reconnectionmanager = new ReconnectionManager(abstractxmppconnection);
        INSTANCES.put(abstractxmppconnection, reconnectionmanager);
        org/jivesoftware/smack/ReconnectionManager;
        JVM INSTR monitorexit ;
        return reconnectionmanager;
        abstractxmppconnection;
        throw abstractxmppconnection;
    }

    private boolean isReconnectionPossible(XMPPConnection xmppconnection)
    {
        return !done && !xmppconnection.isConnected() && isAutomaticReconnectEnabled();
    }

    private void reconnect()
    {
        this;
        JVM INSTR monitorenter ;
        XMPPConnection xmppconnection = (XMPPConnection)weakRefConnection.get();
        if (xmppconnection != null) goto _L2; else goto _L1
_L1:
        LOGGER.fine("Connection is null, will not reconnect");
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (reconnectionThread == null || !reconnectionThread.isAlive())
        {
            reconnectionThread = Async.go(reconnectionRunnable, (new StringBuilder()).append("Smack Reconnection Manager (").append(xmppconnection.getConnectionCounter()).append(')').toString());
        }
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public static void setEnabledPerDefault(boolean flag)
    {
        enabledPerDefault = flag;
    }

    public void disableAutomaticReconnection()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = automaticReconnectEnabled;
        if (flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Object obj = (XMPPConnection)weakRefConnection.get();
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        throw new IllegalStateException("Connection instance no longer available");
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        ((XMPPConnection) (obj)).removeConnectionListener(connectionListener);
        automaticReconnectEnabled = false;
          goto _L1
    }

    public void enableAutomaticReconnection()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = automaticReconnectEnabled;
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Object obj = (XMPPConnection)weakRefConnection.get();
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        throw new IllegalStateException("Connection instance no longer available");
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        ((XMPPConnection) (obj)).addConnectionListener(connectionListener);
        automaticReconnectEnabled = true;
          goto _L1
    }

    public boolean isAutomaticReconnectEnabled()
    {
        return automaticReconnectEnabled;
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                if (xmppconnection instanceof AbstractXMPPConnection)
                {
                    ReconnectionManager.getInstanceFor((AbstractXMPPConnection)xmppconnection);
                }
            }

        });
    }





}
