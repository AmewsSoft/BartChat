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

class val.pingManager
    implements Runnable
{

    final val.pingManager this$0;
    final PingManager val$pingManager;

    public void run()
    {
        val$pingManager.pingServerIfNecessary();
    }

    I()
    {
        this$0 = final_i;
        val$pingManager = PingManager.this;
        super();
    }

    // Unreferenced inner class org/jivesoftware/smackx/ping/android/ServerPingWithAlarmManager$2

/* anonymous class */
    static final class ServerPingWithAlarmManager._cls2 extends BroadcastReceiver
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
                    Async.go(PingManager.getInstanceFor(intent). new ServerPingWithAlarmManager._cls2._cls1(), (new StringBuilder()).append("PingServerIfNecessary (").append(intent.getConnectionCounter()).append(')').toString());
                } else
                {
                    ServerPingWithAlarmManager.access$000().fine((new StringBuilder()).append("NOT calling pingServerIfNecessary (disabled) on connection ").append(intent.getConnectionCounter()).toString());
                }
            }

        }

    }

}
