// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.ping.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.util.Async;
import org.jivesoftware.smackx.ping.PingManager;

// Referenced classes of package org.jivesoftware.smackx.ping.android:
//            ServerPingWithAlarmManager

static final class _cls1.val.pingManager extends BroadcastReceiver
{

    public void onReceive(Context context, Intent intent)
    {
        ServerPingWithAlarmManager.access$000().fine("Ping Alarm broadcast received");
        for (context = ServerPingWithAlarmManager.access$100().keySet().iterator(); context.hasNext();)
        {
            intent = (XMPPConnection)context.next();
            if (ServerPingWithAlarmManager.getInstanceFor(intent).isEnabled())
            {
                ServerPingWithAlarmManager.access$000().fine((new StringBuilder()).append("Calling pingServerIfNecessary for connection ").append(intent.getConnectionCounter()).toString());
                Async.go(new Runnable() {

                    final ServerPingWithAlarmManager._cls2 this$0;
                    final PingManager val$pingManager;

                    public void run()
                    {
                        pingManager.pingServerIfNecessary();
                    }

            
            {
                this$0 = ServerPingWithAlarmManager._cls2.this;
                pingManager = pingmanager;
                super();
            }
                }, (new StringBuilder()).append("PingServerIfNecessary (").append(intent.getConnectionCounter()).append(')').toString());
            } else
            {
                ServerPingWithAlarmManager.access$000().fine((new StringBuilder()).append("NOT calling pingServerIfNecessary (disabled) on connection ").append(intent.getConnectionCounter()).toString());
            }
        }

    }

    _cls1.val.pingManager()
    {
    }
}
