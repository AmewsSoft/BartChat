// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamManager

static final class _cls1.val.connection
    implements ConnectionCreationListener
{

    public void connectionCreated(final XMPPConnection connection)
    {
        InBandBytestreamManager.getByteStreamManager(connection);
        connection.addConnectionListener(new AbstractConnectionClosedListener() {

            final InBandBytestreamManager._cls1 this$0;
            final XMPPConnection val$connection;

            public void connectionTerminated()
            {
                InBandBytestreamManager.access$000(InBandBytestreamManager.getByteStreamManager(connection));
            }

            public void reconnectionSuccessful()
            {
                InBandBytestreamManager.getByteStreamManager(connection);
            }

            
            {
                this$0 = InBandBytestreamManager._cls1.this;
                connection = xmppconnection;
                super();
            }
        });
    }

    _cls1.val.connection()
    {
    }
}
