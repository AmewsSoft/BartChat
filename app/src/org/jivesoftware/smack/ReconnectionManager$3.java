// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.StreamError;

// Referenced classes of package org.jivesoftware.smack:
//            AbstractConnectionListener, ReconnectionManager, XMPPConnection

class ener extends AbstractConnectionListener
{

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
            if (!(exception instanceof rorException) || org.jivesoftware.smack.packet.conflict != (exception = ((rorException)exception).getStreamError()).getCondition())
            {
                ReconnectionManager.access$400(ReconnectionManager.this);
                return;
            }
        }
    }

    dition()
    {
        this$0 = ReconnectionManager.this;
        super();
    }
}
