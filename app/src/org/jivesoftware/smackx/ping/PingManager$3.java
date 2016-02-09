// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.ping;

import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smackx.ping:
//            PingManager

class osedListener extends AbstractConnectionClosedListener
{

    final PingManager this$0;

    public void authenticated(XMPPConnection xmppconnection, boolean flag)
    {
        PingManager.access$000(PingManager.this);
    }

    public void connectionTerminated()
    {
        PingManager.access$100(PingManager.this);
    }

    osedListener()
    {
        this$0 = PingManager.this;
        super();
    }
}
