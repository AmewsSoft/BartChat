// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamManager

class val.connection extends AbstractConnectionClosedListener
{

    final val.connection this$0;
    final XMPPConnection val$connection;

    public void connectionTerminated()
    {
        InBandBytestreamManager.access$000(InBandBytestreamManager.getByteStreamManager(val$connection));
    }

    public void reconnectionSuccessful()
    {
        InBandBytestreamManager.getByteStreamManager(val$connection);
    }

    I()
    {
        this$0 = final_i;
        val$connection = XMPPConnection.this;
        super();
    }

    // Unreferenced inner class org/jivesoftware/smackx/bytestreams/ibb/InBandBytestreamManager$1

/* anonymous class */
    static final class InBandBytestreamManager._cls1
        implements ConnectionCreationListener
    {

        public void connectionCreated(XMPPConnection xmppconnection)
        {
            InBandBytestreamManager.getByteStreamManager(xmppconnection);
            xmppconnection.addConnectionListener(xmppconnection. new InBandBytestreamManager._cls1._cls1());
        }

    }

}
