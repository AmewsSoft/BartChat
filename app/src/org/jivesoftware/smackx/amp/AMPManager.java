// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

public class AMPManager
{

    public AMPManager()
    {
    }

    public static boolean isActionSupported(XMPPConnection xmppconnection, org.jivesoftware.smackx.amp.packet.AMPExtension.Action action)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return isFeatureSupportedByServer(xmppconnection, (new StringBuilder()).append("http://jabber.org/protocol/amp?action=").append(action.toString()).toString(), "http://jabber.org/protocol/amp");
    }

    public static boolean isConditionSupported(XMPPConnection xmppconnection, String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return isFeatureSupportedByServer(xmppconnection, (new StringBuilder()).append("http://jabber.org/protocol/amp?condition=").append(s).toString(), "http://jabber.org/protocol/amp");
    }

    private static boolean isFeatureSupportedByServer(XMPPConnection xmppconnection, String s, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(xmppconnection).supportsFeature(s1, s);
    }

    public static boolean isServiceEnabled(XMPPConnection xmppconnection)
    {
        xmppconnection.getServiceName();
        return ServiceDiscoveryManager.getInstanceFor(xmppconnection).includesFeature("http://jabber.org/protocol/amp");
    }

    public static void setServiceEnabled(XMPPConnection xmppconnection, boolean flag)
    {
        org/jivesoftware/smackx/amp/AMPManager;
        JVM INSTR monitorenter ;
        boolean flag1 = isServiceEnabled(xmppconnection);
        if (flag1 != flag) goto _L2; else goto _L1
_L1:
        org/jivesoftware/smackx/amp/AMPManager;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("http://jabber.org/protocol/amp");
          goto _L1
        xmppconnection;
        throw xmppconnection;
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).removeFeature("http://jabber.org/protocol/amp");
          goto _L1
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                AMPManager.setServiceEnabled(xmppconnection, true);
            }

        });
    }
}
