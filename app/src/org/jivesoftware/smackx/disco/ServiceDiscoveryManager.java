// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.caps.EntityCapsManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jxmpp.util.cache.Cache;
import org.jxmpp.util.cache.ExpirationCache;

// Referenced classes of package org.jivesoftware.smackx.disco:
//            NodeInformationProvider

public class ServiceDiscoveryManager extends Manager
{

    private static final String DEFAULT_IDENTITY_CATEGORY = "client";
    private static final String DEFAULT_IDENTITY_NAME = "Smack";
    private static final String DEFAULT_IDENTITY_TYPE = "pc";
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/disco/ServiceDiscoveryManager.getName());
    private static org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity defaultIdentity = new org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity("client", "Smack", "pc");
    private static Map instances = new WeakHashMap();
    private EntityCapsManager capsManager;
    private DataForm extendedInfo;
    private final Set features = new HashSet();
    private Set identities;
    private org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity identity;
    private Map nodeInformationProviders;
    private Cache services;

    private ServiceDiscoveryManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        identities = new HashSet();
        identity = defaultIdentity;
        extendedInfo = null;
        nodeInformationProviders = new ConcurrentHashMap();
        services = new ExpirationCache(25, 0x5265c00L);
        addFeature("http://jabber.org/protocol/disco#info");
        addFeature("http://jabber.org/protocol/disco#items");
        xmppconnection.registerIQRequestHandler(new AbstractIqRequestHandler("query", "http://jabber.org/protocol/disco#items", org.jivesoftware.smack.packet.IQ.Type.get, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async) {

            final ServiceDiscoveryManager this$0;

            public IQ handleIQRequest(IQ iq)
            {
                iq = (DiscoverItems)iq;
                DiscoverItems discoveritems = new DiscoverItems();
                discoveritems.setType(org.jivesoftware.smack.packet.IQ.Type.result);
                discoveritems.setTo(iq.getFrom());
                discoveritems.setStanzaId(iq.getStanzaId());
                discoveritems.setNode(iq.getNode());
                NodeInformationProvider nodeinformationprovider = getNodeInformationProvider(iq.getNode());
                if (nodeinformationprovider != null)
                {
                    discoveritems.addItems(nodeinformationprovider.getNodeItems());
                    discoveritems.addExtensions(nodeinformationprovider.getNodePacketExtensions());
                } else
                if (iq.getNode() != null)
                {
                    discoveritems.setType(org.jivesoftware.smack.packet.IQ.Type.error);
                    discoveritems.setError(new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.item_not_found));
                    return discoveritems;
                }
                return discoveritems;
            }

            
            {
                this$0 = ServiceDiscoveryManager.this;
                super(s, s1, type, mode);
            }
        });
        xmppconnection.registerIQRequestHandler(new AbstractIqRequestHandler("query", "http://jabber.org/protocol/disco#info", org.jivesoftware.smack.packet.IQ.Type.get, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async) {

            final ServiceDiscoveryManager this$0;

            public IQ handleIQRequest(IQ iq)
            {
                Object obj = (DiscoverInfo)iq;
                iq = new DiscoverInfo();
                iq.setType(org.jivesoftware.smack.packet.IQ.Type.result);
                iq.setTo(((DiscoverInfo) (obj)).getFrom());
                iq.setStanzaId(((DiscoverInfo) (obj)).getStanzaId());
                iq.setNode(((DiscoverInfo) (obj)).getNode());
                if (((DiscoverInfo) (obj)).getNode() == null)
                {
                    addDiscoverInfoTo(iq);
                    return iq;
                }
                obj = getNodeInformationProvider(((DiscoverInfo) (obj)).getNode());
                if (obj != null)
                {
                    iq.addFeatures(((NodeInformationProvider) (obj)).getNodeFeatures());
                    iq.addIdentities(((NodeInformationProvider) (obj)).getNodeIdentities());
                    iq.addExtensions(((NodeInformationProvider) (obj)).getNodePacketExtensions());
                    return iq;
                } else
                {
                    iq.setType(org.jivesoftware.smack.packet.IQ.Type.error);
                    iq.setError(new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.item_not_found));
                    return iq;
                }
            }

            
            {
                this$0 = ServiceDiscoveryManager.this;
                super(s, s1, type, mode);
            }
        });
    }

    public static boolean canPublishItems(DiscoverInfo discoverinfo)
    {
        return discoverinfo.containsFeature("http://jabber.org/protocol/disco#publish");
    }

    public static ServiceDiscoveryManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/disco/ServiceDiscoveryManager;
        JVM INSTR monitorenter ;
        ServiceDiscoveryManager servicediscoverymanager1 = (ServiceDiscoveryManager)instances.get(xmppconnection);
        ServiceDiscoveryManager servicediscoverymanager;
        servicediscoverymanager = servicediscoverymanager1;
        if (servicediscoverymanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        servicediscoverymanager = new ServiceDiscoveryManager(xmppconnection);
        instances.put(xmppconnection, servicediscoverymanager);
        org/jivesoftware/smackx/disco/ServiceDiscoveryManager;
        JVM INSTR monitorexit ;
        return servicediscoverymanager;
        xmppconnection;
        throw xmppconnection;
    }

    private NodeInformationProvider getNodeInformationProvider(String s)
    {
        if (s == null)
        {
            return null;
        } else
        {
            return (NodeInformationProvider)nodeInformationProviders.get(s);
        }
    }

    private void renewEntityCapsVersion()
    {
        if (capsManager != null && capsManager.entityCapsEnabled())
        {
            capsManager.updateLocalEntityCaps();
        }
    }

    public static void setDefaultIdentity(org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity identity1)
    {
        defaultIdentity = identity1;
    }

    public void addDiscoverInfoTo(DiscoverInfo discoverinfo)
    {
        discoverinfo.addIdentities(getIdentities());
        Set set = features;
        set;
        JVM INSTR monitorenter ;
        for (Iterator iterator = getFeatures().iterator(); iterator.hasNext(); discoverinfo.addFeature((String)iterator.next())) { }
        break MISSING_BLOCK_LABEL_56;
        discoverinfo;
        set;
        JVM INSTR monitorexit ;
        throw discoverinfo;
        discoverinfo.addExtension(extendedInfo);
        set;
        JVM INSTR monitorexit ;
    }

    public void addFeature(String s)
    {
        synchronized (features)
        {
            if (!features.contains(s))
            {
                features.add(s);
                renewEntityCapsVersion();
            }
        }
        return;
        s;
        set;
        JVM INSTR monitorexit ;
        throw s;
    }

    public void addIdentity(org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity identity1)
    {
        identities.add(identity1);
        renewEntityCapsVersion();
    }

    public boolean canPublishItems(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return canPublishItems(discoverInfo(s));
    }

    public DiscoverInfo discoverInfo(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj1 = null;
        Object obj;
        if (s == null)
        {
            obj = discoverInfo(null, null);
        } else
        {
            DiscoverInfo discoverinfo = EntityCapsManager.getDiscoverInfoByUser(s);
            obj = discoverinfo;
            if (discoverinfo == null)
            {
                org.jivesoftware.smackx.caps.EntityCapsManager.NodeVerHash nodeverhash = EntityCapsManager.getNodeVerHashByJid(s);
                obj = obj1;
                if (nodeverhash != null)
                {
                    obj = nodeverhash.getNodeVer();
                }
                s = discoverInfo(s, ((String) (obj)));
                obj = s;
                if (nodeverhash != null)
                {
                    obj = s;
                    if (EntityCapsManager.verifyDiscoverInfoVersion(nodeverhash.getVer(), nodeverhash.getHash(), s))
                    {
                        EntityCapsManager.addDiscoverInfoByNode(nodeverhash.getNodeVer(), s);
                        return s;
                    }
                }
            }
        }
        return ((DiscoverInfo) (obj));
    }

    public DiscoverInfo discoverInfo(String s, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        DiscoverInfo discoverinfo = new DiscoverInfo();
        discoverinfo.setType(org.jivesoftware.smack.packet.IQ.Type.get);
        discoverinfo.setTo(s);
        discoverinfo.setNode(s1);
        return (DiscoverInfo)connection().createPacketCollectorAndSend(discoverinfo).nextResultOrThrow();
    }

    public DiscoverItems discoverItems(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return discoverItems(s, null);
    }

    public DiscoverItems discoverItems(String s, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        DiscoverItems discoveritems = new DiscoverItems();
        discoveritems.setType(org.jivesoftware.smack.packet.IQ.Type.get);
        discoveritems.setTo(s);
        discoveritems.setNode(s1);
        return (DiscoverItems)connection().createPacketCollectorAndSend(discoveritems).nextResultOrThrow();
    }

    public List findServices(String s, boolean flag, boolean flag1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        org.jivesoftware.smackx.disco.packet.DiscoverItems.Item item;
        Object obj = connection().getServiceName();
        if (flag1)
        {
            List list = (List)services.get(s);
            if (list != null)
            {
                return list;
            }
        }
        LinkedList linkedlist = new LinkedList();
        Object obj2;
        try
        {
            obj2 = discoverInfo(((String) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            LOGGER.log(Level.WARNING, "Could not discover information about service", s);
            return linkedlist;
        }
        if (((DiscoverInfo) (obj2)).containsFeature(s))
        {
            linkedlist.add(obj);
            if (flag)
            {
                if (flag1)
                {
                    services.put(s, linkedlist);
                }
                return linkedlist;
            }
        }
        try
        {
            obj = discoverItems(((String) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            LOGGER.log(Level.WARNING, "Could not discover items about service", s);
            return linkedlist;
        }
        obj2 = ((DiscoverItems) (obj)).getItems().iterator();
_L1:
        if (!((Iterator) (obj2)).hasNext())
        {
            break MISSING_BLOCK_LABEL_194;
        }
        item = (org.jivesoftware.smackx.disco.packet.DiscoverItems.Item)((Iterator) (obj2)).next();
        obj = discoverInfo(item.getEntityID());
        if (!((DiscoverInfo) (obj)).containsFeature(s))
        {
            break MISSING_BLOCK_LABEL_135;
        }
        linkedlist.add(item.getEntityID());
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_135;
        }
        if (flag1)
        {
            services.put(s, linkedlist);
        }
        return linkedlist;
        Object obj1;
        obj1;
_L2:
        LOGGER.log(Level.WARNING, (new StringBuilder()).append("Exception while discovering info for feature ").append(s).append(" of ").append(item.getEntityID()).append(" node: ").append(item.getNode()).toString(), ((Throwable) (obj1)));
          goto _L1
        obj1;
          goto _L2
    }

    public DataForm getExtendedInfo()
    {
        return extendedInfo;
    }

    public List getExtendedInfoAsList()
    {
        ArrayList arraylist = null;
        if (extendedInfo != null)
        {
            arraylist = new ArrayList(1);
            arraylist.add(extendedInfo);
        }
        return arraylist;
    }

    public List getFeatures()
    {
        ArrayList arraylist;
        synchronized (features)
        {
            arraylist = new ArrayList(features);
        }
        return arraylist;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Set getIdentities()
    {
        HashSet hashset = new HashSet(identities);
        hashset.add(defaultIdentity);
        return Collections.unmodifiableSet(hashset);
    }

    public org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity getIdentity()
    {
        return identity;
    }

    public String getIdentityName()
    {
        return identity.getName();
    }

    public String getIdentityType()
    {
        return identity.getType();
    }

    public boolean includesFeature(String s)
    {
        boolean flag;
        synchronized (features)
        {
            flag = features.contains(s);
        }
        return flag;
        s;
        set;
        JVM INSTR monitorexit ;
        throw s;
    }

    public void publishItems(String s, String s1, DiscoverItems discoveritems)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        discoveritems.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        discoveritems.setTo(s);
        discoveritems.setNode(s1);
        connection().createPacketCollectorAndSend(discoveritems).nextResultOrThrow();
    }

    public void publishItems(String s, DiscoverItems discoveritems)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        publishItems(s, null, discoveritems);
    }

    public void removeExtendedInfo()
    {
        extendedInfo = null;
        renewEntityCapsVersion();
    }

    public void removeFeature(String s)
    {
        synchronized (features)
        {
            features.remove(s);
            renewEntityCapsVersion();
        }
        return;
        s;
        set;
        JVM INSTR monitorexit ;
        throw s;
    }

    public boolean removeIdentity(org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity identity1)
    {
        if (identity1.equals(identity))
        {
            return false;
        } else
        {
            identities.remove(identity1);
            renewEntityCapsVersion();
            return true;
        }
    }

    public void removeNodeInformationProvider(String s)
    {
        nodeInformationProviders.remove(s);
    }

    public boolean serverSupportsFeature(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return supportsFeature(connection().getServiceName(), s);
    }

    public void setEntityCapsManager(EntityCapsManager entitycapsmanager)
    {
        capsManager = entitycapsmanager;
    }

    public void setExtendedInfo(DataForm dataform)
    {
        extendedInfo = dataform;
        renewEntityCapsVersion();
    }

    public void setIdentity(org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity identity1)
    {
        if (identity1 == null)
        {
            throw new IllegalArgumentException("Identity can not be null");
        } else
        {
            identity = identity1;
            renewEntityCapsVersion();
            return;
        }
    }

    public void setNodeInformationProvider(String s, NodeInformationProvider nodeinformationprovider)
    {
        nodeInformationProviders.put(s, nodeinformationprovider);
    }

    public boolean supportsFeature(String s, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return discoverInfo(s).containsFeature(s1);
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                ServiceDiscoveryManager.getInstanceFor(xmppconnection);
            }

        });
    }

}
