// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.time;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.time.packet.Time;

public class EntityTimeManager extends Manager
{

    private static final Map INSTANCES = new WeakHashMap();
    private static boolean autoEnable = true;
    private boolean enabled;

    private EntityTimeManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        enabled = false;
        if (autoEnable)
        {
            enable();
        }
        xmppconnection.registerIQRequestHandler(new AbstractIqRequestHandler("time", "urn:xmpp:time", org.jivesoftware.smack.packet.IQ.Type.get, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async) {

            final EntityTimeManager this$0;

            public IQ handleIQRequest(IQ iq)
            {
                if (enabled)
                {
                    return Time.createResponse(iq);
                } else
                {
                    return IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.not_acceptable));
                }
            }

            
            {
                this$0 = EntityTimeManager.this;
                super(s, s1, type, mode);
            }
        });
    }

    public static EntityTimeManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/time/EntityTimeManager;
        JVM INSTR monitorenter ;
        EntityTimeManager entitytimemanager1 = (EntityTimeManager)INSTANCES.get(xmppconnection);
        EntityTimeManager entitytimemanager;
        entitytimemanager = entitytimemanager1;
        if (entitytimemanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        entitytimemanager = new EntityTimeManager(xmppconnection);
        INSTANCES.put(xmppconnection, entitytimemanager);
        org/jivesoftware/smackx/time/EntityTimeManager;
        JVM INSTR monitorexit ;
        return entitytimemanager;
        xmppconnection;
        throw xmppconnection;
    }

    public static void setAutoEnable(boolean flag)
    {
        autoEnable = flag;
    }

    public void disable()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = enabled;
        if (flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        ServiceDiscoveryManager.getInstanceFor(connection()).removeFeature("urn:xmpp:time");
        enabled = false;
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void enable()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = enabled;
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        ServiceDiscoveryManager.getInstanceFor(connection()).addFeature("urn:xmpp:time");
        enabled = true;
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Time getTime(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (!isTimeSupported(s))
        {
            return null;
        } else
        {
            s = new Time();
            return (Time)connection().createPacketCollectorAndSend(s).nextResultOrThrow();
        }
    }

    public boolean isTimeSupported(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(s, "urn:xmpp:time");
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                EntityTimeManager.getInstanceFor(xmppconnection);
            }

        });
    }

}
