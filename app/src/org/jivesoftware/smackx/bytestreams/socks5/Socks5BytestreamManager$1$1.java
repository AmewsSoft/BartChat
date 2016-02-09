// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            Socks5BytestreamManager

class val.connection extends AbstractConnectionClosedListener
{

    final val.connection this$0;
    final XMPPConnection val$connection;

    public void connectionTerminated()
    {
        Socks5BytestreamManager.getBytestreamManager(val$connection).disableService();
    }

    public void reconnectionSuccessful()
    {
        Socks5BytestreamManager.getBytestreamManager(val$connection);
    }

    ()
    {
        this$0 = final_;
        val$connection = XMPPConnection.this;
        super();
    }

    // Unreferenced inner class org/jivesoftware/smackx/bytestreams/socks5/Socks5BytestreamManager$1

/* anonymous class */
    static final class Socks5BytestreamManager._cls1
        implements ConnectionCreationListener
    {

        public void connectionCreated(XMPPConnection xmppconnection)
        {
            Socks5BytestreamManager.getBytestreamManager(xmppconnection);
            xmppconnection.addConnectionListener(xmppconnection. new Socks5BytestreamManager._cls1._cls1());
        }

    }

}
