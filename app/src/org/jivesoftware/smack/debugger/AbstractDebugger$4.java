// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.debugger;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smack.debugger:
//            AbstractDebugger

class val.connection
    implements ConnectionListener
{

    final AbstractDebugger this$0;
    final XMPPConnection val$connection;

    public void authenticated(XMPPConnection xmppconnection, boolean flag)
    {
        String s = (new StringBuilder()).append("XMPPConnection authenticated (").append(xmppconnection.getConnectionCounter()).append(")").toString();
        xmppconnection = s;
        if (flag)
        {
            xmppconnection = (new StringBuilder()).append(s).append(" and resumed").toString();
        }
        log(xmppconnection);
    }

    public void connected(XMPPConnection xmppconnection)
    {
        log((new StringBuilder()).append("XMPPConnection connected (").append(xmppconnection.getConnectionCounter()).append(")").toString());
    }

    public void connectionClosed()
    {
        log((new StringBuilder()).append("XMPPConnection closed (").append(val$connection.getConnectionCounter()).append(")").toString());
    }

    public void connectionClosedOnError(Exception exception)
    {
        log((new StringBuilder()).append("XMPPConnection closed due to an exception (").append(val$connection.getConnectionCounter()).append(")").toString());
        exception.printStackTrace();
    }

    public void reconnectingIn(int i)
    {
        log((new StringBuilder()).append("XMPPConnection (").append(val$connection.getConnectionCounter()).append(") will reconnect in ").append(i).toString());
    }

    public void reconnectionFailed(Exception exception)
    {
        log((new StringBuilder()).append("Reconnection failed due to an exception (").append(val$connection.getConnectionCounter()).append(")").toString());
        exception.printStackTrace();
    }

    public void reconnectionSuccessful()
    {
        log((new StringBuilder()).append("XMPPConnection reconnected (").append(val$connection.getConnectionCounter()).append(")").toString());
    }

    ()
    {
        this$0 = final_abstractdebugger;
        val$connection = XMPPConnection.this;
        super();
    }
}
