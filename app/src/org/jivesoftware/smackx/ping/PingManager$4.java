// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.ping;

import java.util.logging.Logger;

// Referenced classes of package org.jivesoftware.smackx.ping:
//            PingManager

class this._cls0
    implements Runnable
{

    final PingManager this$0;

    public void run()
    {
        PingManager.access$200().fine("ServerPingTask run()");
        pingServerIfNecessary();
    }

    ()
    {
        this$0 = PingManager.this;
        super();
    }
}
