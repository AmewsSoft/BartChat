// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.BytestreamManager;
import org.jivesoftware.smackx.bytestreams.BytestreamSession;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            InitiationListener, Socks5Proxy, Socks5Utils, Socks5ClientForInitiator, 
//            Socks5Client, Socks5BytestreamSession

public final class Socks5BytestreamManager
    implements BytestreamManager
{

    private static final String SESSION_ID_PREFIX = "js5_";
    private static final Map managers = new HashMap();
    private static final Random randomGenerator = new Random();
    private final List allRequestListeners = Collections.synchronizedList(new LinkedList());
    private final XMPPConnection connection;
    private List ignoredBytestreamRequests;
    private final InitiationListener initiationListener = new InitiationListener(this);
    private String lastWorkingProxy;
    private final List proxyBlacklist = Collections.synchronizedList(new LinkedList());
    private int proxyConnectionTimeout;
    private boolean proxyPrioritizationEnabled;
    private int targetResponseTimeout;
    private final Map userListeners = new ConcurrentHashMap();

    private Socks5BytestreamManager(XMPPConnection xmppconnection)
    {
        targetResponseTimeout = 10000;
        proxyConnectionTimeout = 10000;
        lastWorkingProxy = null;
        proxyPrioritizationEnabled = true;
        ignoredBytestreamRequests = Collections.synchronizedList(new LinkedList());
        connection = xmppconnection;
    }

    private void activate()
    {
        connection.registerIQRequestHandler(initiationListener);
        enableService();
    }

    private Bytestream createBytestreamInitiation(String s, String s1, List list)
    {
        s = new Bytestream(s);
        for (list = list.iterator(); list.hasNext(); s.addStreamHost((org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost)list.next())) { }
        s.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        s.setTo(s1);
        return s;
    }

    private Bytestream createStreamHostRequest(String s)
    {
        Bytestream bytestream = new Bytestream();
        bytestream.setType(org.jivesoftware.smack.packet.IQ.Type.get);
        bytestream.setTo(s);
        return bytestream;
    }

    private List determineProxies()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        ServiceDiscoveryManager servicediscoverymanager;
        ArrayList arraylist;
        Iterator iterator;
        servicediscoverymanager = ServiceDiscoveryManager.getInstanceFor(connection);
        arraylist = new ArrayList();
        iterator = servicediscoverymanager.discoverItems(connection.getServiceName()).getItems().iterator();
_L2:
        org.jivesoftware.smackx.disco.packet.DiscoverItems.Item item;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        item = (org.jivesoftware.smackx.disco.packet.DiscoverItems.Item)iterator.next();
        if (proxyBlacklist.contains(item.getEntityID()))
        {
            continue; /* Loop/switch isn't completed */
        }
        DiscoverInfo discoverinfo = servicediscoverymanager.discoverInfo(item.getEntityID());
        org.jivesoftware.smack.XMPPException.XMPPErrorException xmpperrorexception;
        if (discoverinfo.hasIdentity("proxy", "bytestreams"))
        {
            arraylist.add(item.getEntityID());
        } else
        {
            proxyBlacklist.add(item.getEntityID());
        }
        continue; /* Loop/switch isn't completed */
        xmpperrorexception;
_L3:
        proxyBlacklist.add(item.getEntityID());
        if (true) goto _L2; else goto _L1
_L1:
        return arraylist;
        org.jivesoftware.smack.SmackException.NoResponseException noresponseexception;
        noresponseexception;
          goto _L3
    }

    private List determineStreamHostInfos(List list)
    {
        ArrayList arraylist = new ArrayList();
        List list1 = getLocalStreamHost();
        if (list1 != null)
        {
            arraylist.addAll(list1);
        }
        for (list = list.iterator(); list.hasNext();)
        {
            String s = (String)list.next();
            Bytestream bytestream = createStreamHostRequest(s);
            try
            {
                arraylist.addAll(((Bytestream)connection.createPacketCollectorAndSend(bytestream).nextResultOrThrow()).getStreamHosts());
            }
            catch (Exception exception)
            {
                proxyBlacklist.add(s);
            }
        }

        return arraylist;
    }

    private void enableService()
    {
        ServiceDiscoveryManager.getInstanceFor(connection).addFeature("http://jabber.org/protocol/bytestreams");
    }

    public static Socks5BytestreamManager getBytestreamManager(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/bytestreams/socks5/Socks5BytestreamManager;
        JVM INSTR monitorenter ;
        if (xmppconnection != null) goto _L2; else goto _L1
_L1:
        Socks5BytestreamManager socks5bytestreammanager = null;
_L4:
        org/jivesoftware/smackx/bytestreams/socks5/Socks5BytestreamManager;
        JVM INSTR monitorexit ;
        return socks5bytestreammanager;
_L2:
        Socks5BytestreamManager socks5bytestreammanager1 = (Socks5BytestreamManager)managers.get(xmppconnection);
        socks5bytestreammanager = socks5bytestreammanager1;
        if (socks5bytestreammanager1 != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        socks5bytestreammanager = new Socks5BytestreamManager(xmppconnection);
        managers.put(xmppconnection, socks5bytestreammanager);
        socks5bytestreammanager.activate();
        if (true) goto _L4; else goto _L3
_L3:
        xmppconnection;
        throw xmppconnection;
    }

    private List getLocalStreamHost()
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = Socks5Proxy.getSocks5Proxy();
        if (((Socks5Proxy) (obj1)).isRunning()) goto _L2; else goto _L1
_L1:
        Object obj2;
        return ((List) (obj));
_L2:
        if (((List) (obj2 = ((Socks5Proxy) (obj1)).getLocalAddresses())).isEmpty()) goto _L1; else goto _L3
_L3:
        int j;
        j = ((Socks5Proxy) (obj1)).getPort();
        obj1 = new ArrayList();
        obj2 = ((List) (obj2)).iterator();
_L6:
        obj = obj1;
        if (!((Iterator) (obj2)).hasNext()) goto _L1; else goto _L4
_L4:
        String as[];
        int i;
        int k;
        obj = (String)((Iterator) (obj2)).next();
        as = new String[3];
        as[0] = "127.0.0.1";
        as[1] = "0:0:0:0:0:0:0:1";
        as[2] = "::1";
        k = as.length;
        i = 0;
_L7:
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_134;
        }
        if (((String) (obj)).startsWith(as[i])) goto _L6; else goto _L5
_L5:
        i++;
          goto _L7
        ((List) (obj1)).add(new org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost(connection.getUser(), ((String) (obj)), j));
          goto _L6
    }

    private String getNextSessionID()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("js5_");
        stringbuilder.append(Math.abs(randomGenerator.nextLong()));
        return stringbuilder.toString();
    }

    private boolean supportsSocks5(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection).supportsFeature(s, "http://jabber.org/protocol/bytestreams");
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamlistener)
    {
        allRequestListeners.add(bytestreamlistener);
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamlistener, String s)
    {
        userListeners.put(s, bytestreamlistener);
    }

    public void disableService()
    {
        this;
        JVM INSTR monitorenter ;
        ServiceDiscoveryManager servicediscoverymanager;
        connection.unregisterIQRequestHandler(initiationListener);
        initiationListener.shutdown();
        allRequestListeners.clear();
        userListeners.clear();
        lastWorkingProxy = null;
        proxyBlacklist.clear();
        ignoredBytestreamRequests.clear();
        managers.remove(connection);
        if (managers.size() == 0)
        {
            Socks5Proxy.getSocks5Proxy().stop();
        }
        servicediscoverymanager = ServiceDiscoveryManager.getInstanceFor(connection);
        if (servicediscoverymanager == null)
        {
            break MISSING_BLOCK_LABEL_112;
        }
        servicediscoverymanager.removeFeature("http://jabber.org/protocol/bytestreams");
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public volatile BytestreamSession establishSession(String s)
        throws XMPPException, IOException, InterruptedException, SmackException
    {
        return establishSession(s);
    }

    public volatile BytestreamSession establishSession(String s, String s1)
        throws XMPPException, IOException, InterruptedException, SmackException
    {
        return establishSession(s, s1);
    }

    public Socks5BytestreamSession establishSession(String s)
        throws XMPPException, IOException, InterruptedException, SmackException
    {
        return establishSession(s, getNextSessionID());
    }

    public Socks5BytestreamSession establishSession(String s, String s1)
        throws IOException, InterruptedException, org.jivesoftware.smack.SmackException.NoResponseException, SmackException, XMPPException
    {
        Object obj;
        String s2;
        List list;
        obj = null;
        if (!supportsSocks5(s))
        {
            throw new org.jivesoftware.smack.SmackException.FeatureNotSupportedException("SOCKS5 Bytestream", s);
        }
        ArrayList arraylist = new ArrayList();
        try
        {
            arraylist.addAll(determineProxies());
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        list = determineStreamHostInfos(arraylist);
        if (list.isEmpty())
        {
            if (obj != null)
            {
                throw obj;
            } else
            {
                throw new SmackException("no SOCKS5 proxies available");
            }
        }
        s2 = Socks5Utils.createDigest(s1, connection.getUser(), s);
        if (proxyPrioritizationEnabled && lastWorkingProxy != null)
        {
            Object obj1 = null;
            Iterator iterator = list.iterator();
            do
            {
                obj = obj1;
                if (!iterator.hasNext())
                {
                    break;
                }
                obj = (org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost)iterator.next();
            } while (!((org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost) (obj)).getJID().equals(lastWorkingProxy));
            if (obj != null)
            {
                list.remove(obj);
                list.add(0, obj);
            }
        }
        obj = Socks5Proxy.getSocks5Proxy();
        Object obj2;
        ((Socks5Proxy) (obj)).addTransfer(s2);
        obj2 = createBytestreamInitiation(s1, s, list);
        obj2 = ((Bytestream) (obj2)).getStreamHost(((Bytestream)connection.createPacketCollectorAndSend(((IQ) (obj2))).nextResultOrThrow(getTargetResponseTimeout())).getUsedHost().getJID());
        if (obj2 != null)
        {
            break MISSING_BLOCK_LABEL_276;
        }
        try
        {
            throw new SmackException("Remote user responded with unknown host");
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        finally
        {
            ((Socks5Proxy) (obj)).removeTransfer(s2);
        }
        throw new IOException("Timeout while connecting to SOCKS5 proxy");
        throw s;
        s = (new Socks5ClientForInitiator(((org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost) (obj2)), s2, connection, s1, s)).getSocket(getProxyConnectionTimeout());
        lastWorkingProxy = ((org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost) (obj2)).getJID();
        s = new Socks5BytestreamSession(s, ((org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost) (obj2)).getJID().equals(connection.getUser()));
        ((Socks5Proxy) (obj)).removeTransfer(s2);
        return s;
    }

    protected List getAllRequestListeners()
    {
        return allRequestListeners;
    }

    protected XMPPConnection getConnection()
    {
        return connection;
    }

    protected List getIgnoredBytestreamRequests()
    {
        return ignoredBytestreamRequests;
    }

    public int getProxyConnectionTimeout()
    {
        if (proxyConnectionTimeout <= 0)
        {
            proxyConnectionTimeout = 10000;
        }
        return proxyConnectionTimeout;
    }

    public int getTargetResponseTimeout()
    {
        if (targetResponseTimeout <= 0)
        {
            targetResponseTimeout = 10000;
        }
        return targetResponseTimeout;
    }

    protected BytestreamListener getUserListener(String s)
    {
        return (BytestreamListener)userListeners.get(s);
    }

    public void ignoreBytestreamRequestOnce(String s)
    {
        ignoredBytestreamRequests.add(s);
    }

    public boolean isProxyPrioritizationEnabled()
    {
        return proxyPrioritizationEnabled;
    }

    public void removeIncomingBytestreamListener(String s)
    {
        userListeners.remove(s);
    }

    public void removeIncomingBytestreamListener(BytestreamListener bytestreamlistener)
    {
        allRequestListeners.remove(bytestreamlistener);
    }

    protected void replyRejectPacket(IQ iq)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        iq = IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.not_acceptable));
        connection.sendStanza(iq);
    }

    public void setProxyConnectionTimeout(int i)
    {
        proxyConnectionTimeout = i;
    }

    public void setProxyPrioritizationEnabled(boolean flag)
    {
        proxyPrioritizationEnabled = flag;
    }

    public void setTargetResponseTimeout(int i)
    {
        targetResponseTimeout = i;
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                Socks5BytestreamManager.getBytestreamManager(xmppconnection);
                xmppconnection.addConnectionListener(xmppconnection. new AbstractConnectionClosedListener() {

                    final _cls1 this$0;
                    final XMPPConnection val$connection;

                    public void connectionTerminated()
                    {
                        Socks5BytestreamManager.getBytestreamManager(connection).disableService();
                    }

                    public void reconnectionSuccessful()
                    {
                        Socks5BytestreamManager.getBytestreamManager(connection);
                    }

            
            {
                this$0 = final__pcls1;
                connection = XMPPConnection.this;
                super();
            }
                });
            }

        });
    }
}
