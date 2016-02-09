// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jivesoftware.smackx.caps.cache.EntityCapsPersistentCache;
import org.jivesoftware.smackx.caps.packet.CapsExtension;
import org.jivesoftware.smackx.disco.AbstractNodeInformationProvider;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jxmpp.util.cache.LruCache;

// Referenced classes of package org.jivesoftware.smackx.caps:
//            CapsVersionAndHash

public class EntityCapsManager extends Manager
{
    public static class NodeVerHash
    {

        private String hash;
        private String node;
        private String nodeVer;
        private String ver;

        public String getHash()
        {
            return hash;
        }

        public String getNode()
        {
            return node;
        }

        public String getNodeVer()
        {
            return nodeVer;
        }

        public String getVer()
        {
            return ver;
        }


        NodeVerHash(String s, String s1, String s2)
        {
            node = s;
            ver = s1;
            hash = s2;
            nodeVer = (new StringBuilder()).append(s).append("#").append(s1).toString();
        }

        NodeVerHash(String s, CapsVersionAndHash capsversionandhash)
        {
            this(s, capsversionandhash.version, capsversionandhash.hash);
        }
    }


    private static final LruCache CAPS_CACHE = new LruCache(1000);
    private static String DEFAULT_ENTITY_NODE = "http://www.igniterealtime.org/projects/smack";
    private static final String DEFAULT_HASH = "SHA-1";
    public static final String ELEMENT = "c";
    private static final LruCache JID_TO_NODEVER_CACHE = new LruCache(10000);
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/caps/EntityCapsManager.getName());
    public static final String NAMESPACE = "http://jabber.org/protocol/caps";
    private static final StanzaFilter PRESENCES;
    private static final StanzaFilter PRESENCES_WITHOUT_CAPS = new AndFilter(new StanzaFilter[] {
        new StanzaTypeFilter(org/jivesoftware/smack/packet/Presence), new NotFilter(new StanzaExtensionFilter("c", "http://jabber.org/protocol/caps"))
    });
    private static final StanzaFilter PRESENCES_WITH_CAPS = new AndFilter(new StanzaFilter[] {
        new StanzaTypeFilter(org/jivesoftware/smack/packet/Presence), new StanzaExtensionFilter("c", "http://jabber.org/protocol/caps")
    });
    private static final Map SUPPORTED_HASHES;
    private static boolean autoEnableEntityCaps = true;
    private static Map instances = new WeakHashMap();
    protected static EntityCapsPersistentCache persistentCache;
    private CapsVersionAndHash currentCapsVersion;
    private boolean entityCapsEnabled;
    private String entityNode;
    private final Queue lastLocalCapsVersions = new ConcurrentLinkedQueue();
    private boolean presenceSend;
    private final ServiceDiscoveryManager sdm;

    private EntityCapsManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        presenceSend = false;
        entityNode = DEFAULT_ENTITY_NODE;
        sdm = ServiceDiscoveryManager.getInstanceFor(xmppconnection);
        instances.put(xmppconnection, this);
        xmppconnection.addConnectionListener(new AbstractConnectionListener() {

            final EntityCapsManager this$0;

            private void processCapsStreamFeatureIfAvailable(XMPPConnection xmppconnection1)
            {
                CapsExtension capsextension = (CapsExtension)xmppconnection1.getFeature("c", "http://jabber.org/protocol/caps");
                if (capsextension == null)
                {
                    return;
                } else
                {
                    EntityCapsManager.addCapsExtensionInfo(xmppconnection1.getServiceName(), capsextension);
                    return;
                }
            }

            public void authenticated(XMPPConnection xmppconnection1, boolean flag)
            {
                processCapsStreamFeatureIfAvailable(xmppconnection1);
                if (!flag)
                {
                    presenceSend = false;
                }
            }

            public void connected(XMPPConnection xmppconnection1)
            {
                processCapsStreamFeatureIfAvailable(xmppconnection1);
            }

            
            {
                this$0 = EntityCapsManager.this;
                super();
            }
        });
        updateLocalEntityCaps();
        if (autoEnableEntityCaps)
        {
            enableEntityCaps();
        }
        xmppconnection.addAsyncStanzaListener(new StanzaListener() {

            final EntityCapsManager this$0;

            public void processPacket(Stanza stanza)
            {
                if (!entityCapsEnabled())
                {
                    return;
                } else
                {
                    CapsExtension capsextension = CapsExtension.from(stanza);
                    EntityCapsManager.addCapsExtensionInfo(stanza.getFrom(), capsextension);
                    return;
                }
            }

            
            {
                this$0 = EntityCapsManager.this;
                super();
            }
        }, PRESENCES_WITH_CAPS);
        xmppconnection.addAsyncStanzaListener(new StanzaListener() {

            final EntityCapsManager this$0;

            public void processPacket(Stanza stanza)
            {
                stanza = stanza.getFrom();
                EntityCapsManager.JID_TO_NODEVER_CACHE.remove(stanza);
            }

            
            {
                this$0 = EntityCapsManager.this;
                super();
            }
        }, PRESENCES_WITHOUT_CAPS);
        xmppconnection.addPacketSendingListener(new StanzaListener() {

            final EntityCapsManager this$0;

            public void processPacket(Stanza stanza)
            {
                presenceSend = true;
            }

            
            {
                this$0 = EntityCapsManager.this;
                super();
            }
        }, PRESENCES);
        xmppconnection.addPacketInterceptor(new StanzaListener() {

            final EntityCapsManager this$0;

            public void processPacket(Stanza stanza)
            {
                if (!entityCapsEnabled)
                {
                    return;
                } else
                {
                    CapsVersionAndHash capsversionandhash = getCapsVersion();
                    stanza.addExtension(new CapsExtension(entityNode, capsversionandhash.version, capsversionandhash.hash));
                    return;
                }
            }

            
            {
                this$0 = EntityCapsManager.this;
                super();
            }
        }, PRESENCES);
        sdm.setEntityCapsManager(this);
    }

    private static void addCapsExtensionInfo(String s, CapsExtension capsextension)
    {
        String s1 = capsextension.getHash();
        String s2 = s1.toUpperCase(Locale.US);
        if (!SUPPORTED_HASHES.containsKey(s2))
        {
            return;
        } else
        {
            s1 = s1.toLowerCase(Locale.US);
            String s3 = capsextension.getNode();
            capsextension = capsextension.getVer();
            JID_TO_NODEVER_CACHE.put(s, new NodeVerHash(s3, capsextension, s1));
            return;
        }
    }

    public static void addDiscoverInfoByNode(String s, DiscoverInfo discoverinfo)
    {
        CAPS_CACHE.put(s, discoverinfo);
        if (persistentCache != null)
        {
            persistentCache.addDiscoverInfoByNodePersistent(s, discoverinfo);
        }
    }

    public static void clearMemoryCache()
    {
        JID_TO_NODEVER_CACHE.clear();
        CAPS_CACHE.clear();
    }

    private static void formFieldValuesToCaps(List list, StringBuilder stringbuilder)
    {
        TreeSet treeset = new TreeSet();
        for (list = list.iterator(); list.hasNext(); treeset.add((String)list.next())) { }
        for (list = treeset.iterator(); list.hasNext(); stringbuilder.append("<"))
        {
            stringbuilder.append((String)list.next());
        }

    }

    protected static CapsVersionAndHash generateVerificationString(DiscoverInfo discoverinfo)
    {
        return generateVerificationString(discoverinfo, null);
    }

    protected static CapsVersionAndHash generateVerificationString(DiscoverInfo discoverinfo, String s)
    {
        String s1;
        MessageDigest messagedigest;
        DataForm dataform;
        StringBuilder stringbuilder;
        s1 = s;
        if (s == null)
        {
            s1 = "SHA-1";
        }
        messagedigest = (MessageDigest)SUPPORTED_HASHES.get(s1.toUpperCase(Locale.US));
        if (messagedigest == null)
        {
            return null;
        }
        s1 = s1.toLowerCase(Locale.US);
        dataform = DataForm.from(discoverinfo);
        stringbuilder = new StringBuilder();
        s = new TreeSet();
        for (Iterator iterator = discoverinfo.getIdentities().iterator(); iterator.hasNext(); s.add((org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity)iterator.next())) { }
        Iterator iterator1 = s.iterator();
        while (iterator1.hasNext()) 
        {
            org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity identity = (org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity)iterator1.next();
            stringbuilder.append(identity.getCategory());
            stringbuilder.append("/");
            stringbuilder.append(identity.getType());
            stringbuilder.append("/");
            if (identity.getLanguage() == null)
            {
                s = "";
            } else
            {
                s = identity.getLanguage();
            }
            stringbuilder.append(s);
            stringbuilder.append("/");
            if (identity.getName() == null)
            {
                s = "";
            } else
            {
                s = identity.getName();
            }
            stringbuilder.append(s);
            stringbuilder.append("<");
        }
        s = new TreeSet();
        for (discoverinfo = discoverinfo.getFeatures().iterator(); discoverinfo.hasNext(); s.add(((org.jivesoftware.smackx.disco.packet.DiscoverInfo.Feature)discoverinfo.next()).getVar())) { }
        for (discoverinfo = s.iterator(); discoverinfo.hasNext(); stringbuilder.append("<"))
        {
            stringbuilder.append((String)discoverinfo.next());
        }

        if (dataform == null || !dataform.hasHiddenFormTypeField()) goto _L2; else goto _L1
_L1:
        dataform;
        JVM INSTR monitorenter ;
        TreeSet treeset = new TreeSet(new Comparator() {

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((FormField)obj, (FormField)obj1);
            }

            public int compare(FormField formfield, FormField formfield1)
            {
                return formfield.getVariable().compareTo(formfield1.getVariable());
            }

        });
        discoverinfo = null;
        Iterator iterator2 = dataform.getFields().iterator();
_L4:
        do
        {
            if (!iterator2.hasNext())
            {
                break MISSING_BLOCK_LABEL_448;
            }
            s = (FormField)iterator2.next();
            if (s.getVariable().equals("FORM_TYPE"))
            {
                break MISSING_BLOCK_LABEL_443;
            }
            treeset.add(s);
        } while (true);
        discoverinfo;
        dataform;
        JVM INSTR monitorexit ;
        throw discoverinfo;
        discoverinfo = s;
        if (true) goto _L4; else goto _L3
_L3:
        if (discoverinfo == null)
        {
            break MISSING_BLOCK_LABEL_461;
        }
        formFieldValuesToCaps(discoverinfo.getValues(), stringbuilder);
        for (discoverinfo = treeset.iterator(); discoverinfo.hasNext(); formFieldValuesToCaps(s.getValues(), stringbuilder))
        {
            s = (FormField)discoverinfo.next();
            stringbuilder.append(s.getVariable());
            stringbuilder.append("<");
        }

        dataform;
        JVM INSTR monitorexit ;
_L2:
        messagedigest;
        JVM INSTR monitorenter ;
        discoverinfo = messagedigest.digest(stringbuilder.toString().getBytes());
        messagedigest;
        JVM INSTR monitorexit ;
        return new CapsVersionAndHash(Base64.encodeToString(discoverinfo), s1);
        discoverinfo;
        messagedigest;
        JVM INSTR monitorexit ;
        throw discoverinfo;
    }

    public static DiscoverInfo getDiscoverInfoByUser(String s)
    {
        s = (NodeVerHash)JID_TO_NODEVER_CACHE.get(s);
        if (s == null)
        {
            return null;
        } else
        {
            return getDiscoveryInfoByNodeVer(((NodeVerHash) (s)).nodeVer);
        }
    }

    public static DiscoverInfo getDiscoveryInfoByNodeVer(String s)
    {
        DiscoverInfo discoverinfo1 = (DiscoverInfo)CAPS_CACHE.get(s);
        DiscoverInfo discoverinfo = discoverinfo1;
        if (discoverinfo1 == null)
        {
            discoverinfo = discoverinfo1;
            if (persistentCache != null)
            {
                DiscoverInfo discoverinfo2 = persistentCache.lookup(s);
                discoverinfo = discoverinfo2;
                if (discoverinfo2 != null)
                {
                    CAPS_CACHE.put(s, discoverinfo2);
                    discoverinfo = discoverinfo2;
                }
            }
        }
        if (discoverinfo != null)
        {
            return new DiscoverInfo(discoverinfo);
        } else
        {
            return discoverinfo;
        }
    }

    public static EntityCapsManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/caps/EntityCapsManager;
        JVM INSTR monitorenter ;
        if (SUPPORTED_HASHES.size() <= 0)
        {
            throw new IllegalStateException("No supported hashes for EntityCapsManager");
        }
        break MISSING_BLOCK_LABEL_31;
        xmppconnection;
        org/jivesoftware/smackx/caps/EntityCapsManager;
        JVM INSTR monitorexit ;
        throw xmppconnection;
        EntityCapsManager entitycapsmanager1 = (EntityCapsManager)instances.get(xmppconnection);
        EntityCapsManager entitycapsmanager;
        entitycapsmanager = entitycapsmanager1;
        if (entitycapsmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        entitycapsmanager = new EntityCapsManager(xmppconnection);
        org/jivesoftware/smackx/caps/EntityCapsManager;
        JVM INSTR monitorexit ;
        return entitycapsmanager;
    }

    public static NodeVerHash getNodeVerHashByJid(String s)
    {
        return (NodeVerHash)JID_TO_NODEVER_CACHE.get(s);
    }

    public static String getNodeVersionByJid(String s)
    {
        s = (NodeVerHash)JID_TO_NODEVER_CACHE.get(s);
        if (s != null)
        {
            return ((NodeVerHash) (s)).nodeVer;
        } else
        {
            return null;
        }
    }

    public static void setDefaultEntityNode(String s)
    {
        DEFAULT_ENTITY_NODE = s;
    }

    public static void setMaxsCacheSizes(int i, int j)
    {
        JID_TO_NODEVER_CACHE.setMaxCacheSize(i);
        CAPS_CACHE.setMaxCacheSize(j);
    }

    public static void setPersistentCache(EntityCapsPersistentCache entitycapspersistentcache)
    {
        persistentCache = entitycapspersistentcache;
    }

    public static boolean verifyDiscoverInfoVersion(String s, String s1, DiscoverInfo discoverinfo)
    {
        while (discoverinfo.containsDuplicateIdentities() || discoverinfo.containsDuplicateFeatures() || verifyPacketExtensions(discoverinfo) || !s.equals(generateVerificationString(discoverinfo, s1).version)) 
        {
            return false;
        }
        return true;
    }

    protected static boolean verifyPacketExtensions(DiscoverInfo discoverinfo)
    {
        LinkedList linkedlist = new LinkedList();
        for (discoverinfo = discoverinfo.getExtensions().iterator(); discoverinfo.hasNext();)
        {
            Object obj = (ExtensionElement)discoverinfo.next();
            if (((ExtensionElement) (obj)).getNamespace().equals("jabber:x:data"))
            {
                obj = ((DataForm)obj).getFields().iterator();
                while (((Iterator) (obj)).hasNext()) 
                {
                    FormField formfield = (FormField)((Iterator) (obj)).next();
                    if (formfield.getVariable().equals("FORM_TYPE"))
                    {
                        for (Iterator iterator = linkedlist.iterator(); iterator.hasNext();)
                        {
                            if (formfield.equals((FormField)iterator.next()))
                            {
                                return true;
                            }
                        }

                        linkedlist.add(formfield);
                    }
                }
            }
        }

        return false;
    }

    public boolean areEntityCapsSupported(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return sdm.supportsFeature(s, "http://jabber.org/protocol/caps");
    }

    public boolean areEntityCapsSupportedByServer()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return areEntityCapsSupported(connection().getServiceName());
    }

    public void disableEntityCaps()
    {
        this;
        JVM INSTR monitorenter ;
        entityCapsEnabled = false;
        sdm.removeFeature("http://jabber.org/protocol/caps");
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void enableEntityCaps()
    {
        this;
        JVM INSTR monitorenter ;
        sdm.addFeature("http://jabber.org/protocol/caps");
        updateLocalEntityCaps();
        entityCapsEnabled = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean entityCapsEnabled()
    {
        return entityCapsEnabled;
    }

    public CapsVersionAndHash getCapsVersion()
    {
        return currentCapsVersion;
    }

    public String getLocalNodeVer()
    {
        return (new StringBuilder()).append(entityNode).append('#').append(getCapsVersion()).toString();
    }

    public void removeUserCapsNode(String s)
    {
        JID_TO_NODEVER_CACHE.remove(s);
    }

    public void setEntityNode(String s)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        entityNode = s;
        updateLocalEntityCaps();
    }

    public void updateLocalEntityCaps()
    {
        XMPPConnection xmppconnection;
        final LinkedList identities;
        xmppconnection = connection();
        identities = new DiscoverInfo();
        identities.setType(org.jivesoftware.smack.packet.IQ.Type.result);
        identities.setNode(getLocalNodeVer());
        if (xmppconnection != null)
        {
            identities.setFrom(xmppconnection.getUser());
        }
        sdm.addDiscoverInfoTo(identities);
        currentCapsVersion = generateVerificationString(identities);
        addDiscoverInfoByNode((new StringBuilder()).append(entityNode).append('#').append(currentCapsVersion.version).toString(), identities);
        if (lastLocalCapsVersions.size() > 10)
        {
            identities = (CapsVersionAndHash)lastLocalCapsVersions.poll();
            sdm.removeNodeInformationProvider((new StringBuilder()).append(entityNode).append('#').append(((CapsVersionAndHash) (identities)).version).toString());
        }
        lastLocalCapsVersions.add(currentCapsVersion);
        if (xmppconnection != null)
        {
            JID_TO_NODEVER_CACHE.put(xmppconnection.getUser(), new NodeVerHash(entityNode, currentCapsVersion));
        }
        identities = new LinkedList(ServiceDiscoveryManager.getInstanceFor(xmppconnection).getIdentities());
        sdm.setNodeInformationProvider((new StringBuilder()).append(entityNode).append('#').append(currentCapsVersion).toString(), new AbstractNodeInformationProvider() {

            List features;
            List packetExtensions;
            final EntityCapsManager this$0;
            final List val$identities;

            public List getNodeFeatures()
            {
                return features;
            }

            public List getNodeIdentities()
            {
                return identities;
            }

            public List getNodePacketExtensions()
            {
                return packetExtensions;
            }

            
            {
                this$0 = EntityCapsManager.this;
                identities = list;
                super();
                features = sdm.getFeatures();
                packetExtensions = sdm.getExtendedInfoAsList();
            }
        });
        if (xmppconnection == null || !xmppconnection.isAuthenticated() || !presenceSend)
        {
            break MISSING_BLOCK_LABEL_301;
        }
        identities = new Presence(org.jivesoftware.smack.packet.Presence.Type.available);
        xmppconnection.sendStanza(identities);
        return;
        org.jivesoftware.smack.SmackException.NotConnectedException notconnectedexception;
        notconnectedexception;
        LOGGER.log(Level.WARNING, "Could could not update presence with caps info", notconnectedexception);
        return;
    }

    static 
    {
        SUPPORTED_HASHES = new HashMap();
        PRESENCES = StanzaTypeFilter.PRESENCE;
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                EntityCapsManager.getInstanceFor(xmppconnection);
            }

        });
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("SHA-1");
            SUPPORTED_HASHES.put("SHA-1", messagedigest);
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception) { }
    }


/*
    static boolean access$102(EntityCapsManager entitycapsmanager, boolean flag)
    {
        entitycapsmanager.presenceSend = flag;
        return flag;
    }

*/





}
