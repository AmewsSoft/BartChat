// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqversion;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.iqversion.packet.Version;

public class VersionManager extends Manager
{

    private static final Map INSTANCES = new WeakHashMap();
    private static boolean autoAppendSmackVersion = true;
    private static Version defaultVersion;
    private Version ourVersion;

    private VersionManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        ourVersion = defaultVersion;
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("jabber:iq:version");
        xmppconnection.registerIQRequestHandler(new AbstractIqRequestHandler("query", "jabber:iq:version", org.jivesoftware.smack.packet.IQ.Type.get, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async) {

            final VersionManager this$0;

            public IQ handleIQRequest(IQ iq)
            {
                if (ourVersion == null)
                {
                    return IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.not_acceptable));
                } else
                {
                    return Version.createResultFor(iq, ourVersion);
                }
            }

            
            {
                this$0 = VersionManager.this;
                super(s, s1, type, mode);
            }
        });
    }

    private static Version generateVersionFrom(String s, String s1, String s2)
    {
        String s3 = s;
        if (autoAppendSmackVersion)
        {
            s3 = (new StringBuilder()).append(s).append(" (Smack ").append(SmackConfiguration.getVersion()).append(')').toString();
        }
        return new Version(s3, s1, s2);
    }

    public static VersionManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/iqversion/VersionManager;
        JVM INSTR monitorenter ;
        VersionManager versionmanager1 = (VersionManager)INSTANCES.get(xmppconnection);
        VersionManager versionmanager;
        versionmanager = versionmanager1;
        if (versionmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        versionmanager = new VersionManager(xmppconnection);
        INSTANCES.put(xmppconnection, versionmanager);
        org/jivesoftware/smackx/iqversion/VersionManager;
        JVM INSTR monitorexit ;
        return versionmanager;
        xmppconnection;
        throw xmppconnection;
    }

    public static void setAutoAppendSmackVersion(boolean flag)
    {
        autoAppendSmackVersion = flag;
    }

    public static void setDefaultVersion(String s, String s1)
    {
        setDefaultVersion(s, s1, null);
    }

    public static void setDefaultVersion(String s, String s1, String s2)
    {
        defaultVersion = generateVersionFrom(s, s1, s2);
    }

    public Version getVersion(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (!isSupported(s))
        {
            return null;
        } else
        {
            return (Version)connection().createPacketCollectorAndSend(new Version(s)).nextResultOrThrow();
        }
    }

    public boolean isSupported(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(s, "jabber:iq:version");
    }

    public void setVersion(String s, String s1)
    {
        setVersion(s, s1, null);
    }

    public void setVersion(String s, String s1, String s2)
    {
        ourVersion = generateVersionFrom(s, s1, s2);
    }

    public void unsetVersion()
    {
        ourVersion = null;
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                VersionManager.getInstanceFor(xmppconnection);
            }

        });
    }

}
