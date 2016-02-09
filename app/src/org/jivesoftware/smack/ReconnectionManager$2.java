// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package org.jivesoftware.smack:
//            ReconnectionManager, AbstractXMPPConnection, ConnectionListener

class attempts extends Thread
{

    private int attempts;
    final ReconnectionManager this$0;

    private int timeDelay()
    {
        attempts = attempts + 1;
        if (attempts > 13)
        {
            return ReconnectionManager.access$000(ReconnectionManager.this) * 6 * 5;
        }
        if (attempts > 7)
        {
            return ReconnectionManager.access$000(ReconnectionManager.this) * 6;
        } else
        {
            return ReconnectionManager.access$000(ReconnectionManager.this);
        }
    }

    public void run()
    {
        AbstractXMPPConnection abstractxmppconnection = (AbstractXMPPConnection)ReconnectionManager.access$100(ReconnectionManager.this).get();
        if (abstractxmppconnection != null) goto _L2; else goto _L1
_L1:
        return;
_L8:
        interruptedexception = abstractxmppconnection.connectionListeners.iterator();
        while (interruptedexception.hasNext()) 
        {
            ((ConnectionListener)interruptedexception.next()).reconnectingIn(0);
        }
        try
        {
            if (ReconnectionManager.access$200(ReconnectionManager.this, abstractxmppconnection))
            {
                abstractxmppconnection.connect();
            }
        }
        catch (Exception exception)
        {
            Iterator iterator1 = abstractxmppconnection.connectionListeners.iterator();
            while (iterator1.hasNext()) 
            {
                ((ConnectionListener)iterator1.next()).reconnectionFailed(exception);
            }
        }
_L2:
        if (!ReconnectionManager.access$200(ReconnectionManager.this, abstractxmppconnection)) goto _L1; else goto _L3
_L3:
        i = timeDelay();
_L7:
        if (!ReconnectionManager.access$200(ReconnectionManager.this, abstractxmppconnection) || i <= 0)
        {
            break MISSING_BLOCK_LABEL_135;
        }
        Thread.sleep(1000L);
        j = i - 1;
        iterator = abstractxmppconnection.connectionListeners.iterator();
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
        ReconnectionManager.access$300().log(Level.FINE, "waiting for reconnection interrupted", interruptedexception);
          goto _L8
    }

    ()
    {
        this$0 = ReconnectionManager.this;
        super();
        attempts = 0;
    }
}
