// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            Socks5BytestreamManager

static final class _cls1.val.connection
    implements ConnectionCreationListener
{

    public void connectionCreated(final XMPPConnection connection)
    {
        Socks5BytestreamManager.getBytestreamManager(connection);
        connection.addConnectionListener(new AbstractConnectionClosedListener() {

            final Socks5BytestreamManager._cls1 this$0;
            final XMPPConnection val$connection;

            public void connectionTerminated()
            {
                Socks5BytestreamManager.getBytestreamManager(connection).disableService();
            }

            public void reconnectionSuccessful()
            {
                Socks5BytestreamManager.getBytestreamManager(connection);
            }

            
            {
                this$0 = Socks5BytestreamManager._cls1.this;
                connection = xmppconnection;
                super();
            }
        });
    }

    _cls1.val.connection()
    {
    }
}
