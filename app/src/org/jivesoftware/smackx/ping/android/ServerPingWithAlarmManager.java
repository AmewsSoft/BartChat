// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.ping.android;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.util.Async;
import org.jivesoftware.smackx.ping.PingManager;

public class ServerPingWithAlarmManager extends Manager
{

    private static final BroadcastReceiver ALARM_BROADCAST_RECEIVER = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            ServerPingWithAlarmManager.LOGGER.fine("Ping Alarm broadcast received");
            for (context = ServerPingWithAlarmManager.INSTANCES.keySet().iterator(); context.hasNext();)
            {
                intent = (XMPPConnection)context.next();
                if (ServerPingWithAlarmManager.getInstanceFor(intent).isEnabled())
                {
                    ServerPingWithAlarmManager.LOGGER.fine((new StringBuilder()).append("Calling pingServerIfNecessary for connection ").append(intent.getConnectionCounter()).toString());
                    Async.go(PingManager.getInstanceFor(intent). new Runnable() {

                        final _cls2 this$0;
                        final PingManager val$pingManager;

                        public void run()
                        {
                            pingManager.pingServerIfNecessary();
                        }

            
            {
                this$0 = final__pcls2;
                pingManager = PingManager.this;
                super();
            }
                    }, (new StringBuilder()).append("PingServerIfNecessary (").append(intent.getConnectionCounter()).append(')').toString());
                } else
                {
                    ServerPingWithAlarmManager.LOGGER.fine((new StringBuilder()).append("NOT calling pingServerIfNecessary (disabled) on connection ").append(intent.getConnectionCounter()).toString());
                }
            }

        }

    };
    private static final Map INSTANCES = new WeakHashMap();
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/ping/android/ServerPingWithAlarmManager.getName());
    private static final String PING_ALARM_ACTION = "org.igniterealtime.smackx.ping.ACTION";
    private static AlarmManager sAlarmManager;
    private static Context sContext;
    private static PendingIntent sPendingIntent;
    private boolean mEnabled;

    private ServerPingWithAlarmManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        mEnabled = true;
    }

    public static ServerPingWithAlarmManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/ping/android/ServerPingWithAlarmManager;
        JVM INSTR monitorenter ;
        ServerPingWithAlarmManager serverpingwithalarmmanager1 = (ServerPingWithAlarmManager)INSTANCES.get(xmppconnection);
        ServerPingWithAlarmManager serverpingwithalarmmanager;
        serverpingwithalarmmanager = serverpingwithalarmmanager1;
        if (serverpingwithalarmmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        serverpingwithalarmmanager = new ServerPingWithAlarmManager(xmppconnection);
        INSTANCES.put(xmppconnection, serverpingwithalarmmanager);
        org/jivesoftware/smackx/ping/android/ServerPingWithAlarmManager;
        JVM INSTR monitorexit ;
        return serverpingwithalarmmanager;
        xmppconnection;
        throw xmppconnection;
    }

    public static void onCreate(Context context)
    {
        sContext = context;
        context.registerReceiver(ALARM_BROADCAST_RECEIVER, new IntentFilter("org.igniterealtime.smackx.ping.ACTION"));
        sAlarmManager = (AlarmManager)context.getSystemService("alarm");
        sPendingIntent = PendingIntent.getBroadcast(context, 0, new Intent("org.igniterealtime.smackx.ping.ACTION"), 0);
        sAlarmManager.setInexactRepeating(2, SystemClock.elapsedRealtime() + 0x1b7740L, 0x1b7740L, sPendingIntent);
    }

    public static void onDestroy()
    {
        sContext.unregisterReceiver(ALARM_BROADCAST_RECEIVER);
        sAlarmManager.cancel(sPendingIntent);
    }

    public boolean isEnabled()
    {
        return mEnabled;
    }

    public void setEnabled(boolean flag)
    {
        mEnabled = flag;
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                ServerPingWithAlarmManager.getInstanceFor(xmppconnection);
            }

        });
    }


}
