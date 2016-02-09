// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdata;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

public class XDataManager extends Manager
{

    private static final Map INSTANCES = new WeakHashMap();
    public static final String NAMESPACE = "jabber:x:data";

    private XDataManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("jabber:x:data");
    }

    public static XDataManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/xdata/XDataManager;
        JVM INSTR monitorenter ;
        XDataManager xdatamanager1 = (XDataManager)INSTANCES.get(xmppconnection);
        XDataManager xdatamanager;
        xdatamanager = xdatamanager1;
        if (xdatamanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        xdatamanager = new XDataManager(xmppconnection);
        INSTANCES.put(xmppconnection, xdatamanager);
        org/jivesoftware/smackx/xdata/XDataManager;
        JVM INSTR monitorexit ;
        return xdatamanager;
        xmppconnection;
        throw xmppconnection;
    }

    public boolean isSupported(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(s, "jabber:x:data");
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                XDataManager.getInstanceFor(xmppconnection);
            }

        });
    }
}
