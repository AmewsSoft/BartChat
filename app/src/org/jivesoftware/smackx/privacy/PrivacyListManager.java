// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.privacy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.IQResultReplyFilter;
import org.jivesoftware.smack.filter.IQTypeFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.privacy.filter.SetActiveListFilter;
import org.jivesoftware.smackx.privacy.filter.SetDefaultListFilter;
import org.jivesoftware.smackx.privacy.packet.Privacy;

// Referenced classes of package org.jivesoftware.smackx.privacy:
//            PrivacyList, PrivacyListListener

public class PrivacyListManager extends Manager
{

    private static final Map INSTANCES = new WeakHashMap();
    public static final String NAMESPACE = "jabber:iq:privacy";
    public static final StanzaFilter PRIVACY_FILTER;
    private static final StanzaFilter PRIVACY_RESULT;
    private volatile String cachedActiveListName;
    private volatile String cachedDefaultListName;
    private final Set listeners = new CopyOnWriteArraySet();

    private PrivacyListManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        xmppconnection.registerIQRequestHandler(new AbstractIqRequestHandler("query", "jabber:iq:privacy", org.jivesoftware.smack.packet.IQ.Type.set, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.sync) {

            final PrivacyListManager this$0;

            public IQ handleIQRequest(IQ iq)
            {
                iq = (Privacy)iq;
                for (Iterator iterator = listeners.iterator(); iterator.hasNext();)
                {
                    PrivacyListListener privacylistlistener = (PrivacyListListener)iterator.next();
                    Iterator iterator1 = iq.getItemLists().entrySet().iterator();
                    while (iterator1.hasNext()) 
                    {
                        Object obj = (java.util.Map.Entry)iterator1.next();
                        String s = (String)((java.util.Map.Entry) (obj)).getKey();
                        obj = (List)((java.util.Map.Entry) (obj)).getValue();
                        if (((List) (obj)).isEmpty())
                        {
                            privacylistlistener.updatedPrivacyList(s);
                        } else
                        {
                            privacylistlistener.setPrivacyList(s, ((List) (obj)));
                        }
                    }
                }

                return IQ.createResultIQ(iq);
            }

            
            {
                this$0 = PrivacyListManager.this;
                super(s, s1, type, mode);
            }
        });
        xmppconnection.addPacketSendingListener(new StanzaListener() {

            final PrivacyListManager this$0;

            public void processPacket(Stanza stanza)
                throws org.jivesoftware.smack.SmackException.NotConnectedException
            {
                XMPPConnection xmppconnection1 = connection();
                stanza = (Privacy)stanza;
                IQResultReplyFilter iqresultreplyfilter = new IQResultReplyFilter(stanza, xmppconnection1);
                String s = stanza.getActiveName();
                xmppconnection1.addOneTimeSyncCallback(s. new StanzaListener() {

                    final _cls3 this$1;
                    final String val$activeListName;
                    final boolean val$declinceActiveList;

                    public void processPacket(Stanza stanza)
                        throws org.jivesoftware.smack.SmackException.NotConnectedException
                    {
                        if (declinceActiveList)
                        {
                            cachedActiveListName = null;
                            return;
                        } else
                        {
                            cachedActiveListName = activeListName;
                            return;
                        }
                    }

            
            {
                this$1 = final__pcls3;
                declinceActiveList = flag;
                activeListName = String.this;
                super();
            }
                }, iqresultreplyfilter);
            }

            
            {
                this$0 = PrivacyListManager.this;
                super();
            }
        }, SetActiveListFilter.INSTANCE);
        xmppconnection.addPacketSendingListener(new StanzaListener() {

            final PrivacyListManager this$0;

            public void processPacket(Stanza stanza)
                throws org.jivesoftware.smack.SmackException.NotConnectedException
            {
                XMPPConnection xmppconnection1 = connection();
                stanza = (Privacy)stanza;
                IQResultReplyFilter iqresultreplyfilter = new IQResultReplyFilter(stanza, xmppconnection1);
                String s = stanza.getDefaultName();
                xmppconnection1.addOneTimeSyncCallback(s. new StanzaListener() {

                    final _cls4 this$1;
                    final boolean val$declinceDefaultList;
                    final String val$defaultListName;

                    public void processPacket(Stanza stanza)
                        throws org.jivesoftware.smack.SmackException.NotConnectedException
                    {
                        if (declinceDefaultList)
                        {
                            cachedDefaultListName = null;
                            return;
                        } else
                        {
                            cachedDefaultListName = defaultListName;
                            return;
                        }
                    }

            
            {
                this$1 = final__pcls4;
                declinceDefaultList = flag;
                defaultListName = String.this;
                super();
            }
                }, iqresultreplyfilter);
            }

            
            {
                this$0 = PrivacyListManager.this;
                super();
            }
        }, SetDefaultListFilter.INSTANCE);
        xmppconnection.addSyncStanzaListener(new StanzaListener() {

            final PrivacyListManager this$0;

            public void processPacket(Stanza stanza)
                throws org.jivesoftware.smack.SmackException.NotConnectedException
            {
                stanza = (Privacy)stanza;
                String s = stanza.getActiveName();
                if (s != null)
                {
                    cachedActiveListName = s;
                }
                stanza = stanza.getDefaultName();
                if (stanza != null)
                {
                    cachedDefaultListName = stanza;
                }
            }

            
            {
                this$0 = PrivacyListManager.this;
                super();
            }
        }, PRIVACY_RESULT);
        xmppconnection.addConnectionListener(new AbstractConnectionListener() {

            final PrivacyListManager this$0;

            public void authenticated(XMPPConnection xmppconnection1, boolean flag)
            {
                if (flag)
                {
                    return;
                } else
                {
                    cachedActiveListName = cachedDefaultListName = null;
                    return;
                }
            }

            
            {
                this$0 = PrivacyListManager.this;
                super();
            }
        });
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("jabber:iq:privacy");
    }

    public static PrivacyListManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/privacy/PrivacyListManager;
        JVM INSTR monitorenter ;
        PrivacyListManager privacylistmanager1 = (PrivacyListManager)INSTANCES.get(xmppconnection);
        PrivacyListManager privacylistmanager;
        privacylistmanager = privacylistmanager1;
        if (privacylistmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        privacylistmanager = new PrivacyListManager(xmppconnection);
        INSTANCES.put(xmppconnection, privacylistmanager);
        org/jivesoftware/smackx/privacy/PrivacyListManager;
        JVM INSTR monitorexit ;
        return privacylistmanager;
        xmppconnection;
        throw xmppconnection;
    }

    private List getPrivacyListItems(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Privacy privacy = new Privacy();
        privacy.setPrivacyList(s, new ArrayList());
        return getRequest(privacy).getPrivacyList(s);
    }

    private Privacy getPrivacyWithListNames()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getRequest(new Privacy());
    }

    private Privacy getRequest(Privacy privacy)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        privacy.setType(org.jivesoftware.smack.packet.IQ.Type.get);
        return (Privacy)connection().createPacketCollectorAndSend(privacy).nextResultOrThrow();
    }

    private Stanza setRequest(Privacy privacy)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        privacy.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        return connection().createPacketCollectorAndSend(privacy).nextResultOrThrow();
    }

    public boolean addListener(PrivacyListListener privacylistlistener)
    {
        return listeners.add(privacylistlistener);
    }

    public void createPrivacyList(String s, List list)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        updatePrivacyList(s, list);
    }

    public void declineActiveList()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Privacy privacy = new Privacy();
        privacy.setDeclineActiveList(true);
        setRequest(privacy);
    }

    public void declineDefaultList()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Privacy privacy = new Privacy();
        privacy.setDeclineDefaultList(true);
        setRequest(privacy);
    }

    public void deletePrivacyList(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Privacy privacy = new Privacy();
        privacy.setPrivacyList(s, new ArrayList());
        setRequest(privacy);
    }

    public PrivacyList getActiveList()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Privacy privacy = getPrivacyWithListNames();
        String s = privacy.getActiveName();
        boolean flag;
        if (s != null && s.equals(privacy.getDefaultName()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return new PrivacyList(true, flag, s, getPrivacyListItems(s));
    }

    public String getActiveListName()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (cachedActiveListName != null)
        {
            return cachedActiveListName;
        } else
        {
            return getPrivacyWithListNames().getActiveName();
        }
    }

    public PrivacyList getDefaultList()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Privacy privacy = getPrivacyWithListNames();
        String s = privacy.getDefaultName();
        boolean flag;
        if (s != null && s.equals(privacy.getActiveName()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return new PrivacyList(flag, true, s, getPrivacyListItems(s));
    }

    public String getDefaultListName()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (cachedDefaultListName != null)
        {
            return cachedDefaultListName;
        } else
        {
            return getPrivacyWithListNames().getDefaultName();
        }
    }

    public String getEffectiveListName()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        String s = getActiveListName();
        if (s != null)
        {
            return s;
        } else
        {
            return getDefaultListName();
        }
    }

    public PrivacyList getPrivacyList(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return new PrivacyList(false, false, s, getPrivacyListItems(s));
    }

    public List getPrivacyLists()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Privacy privacy = getPrivacyWithListNames();
        Object obj = privacy.getPrivacyListNames();
        ArrayList arraylist = new ArrayList(((Set) (obj)).size());
        String s;
        for (obj = ((Set) (obj)).iterator(); ((Iterator) (obj)).hasNext(); arraylist.add(new PrivacyList(s.equals(privacy.getActiveName()), s.equals(privacy.getDefaultName()), s, getPrivacyListItems(s))))
        {
            s = (String)((Iterator) (obj)).next();
        }

        return arraylist;
    }

    public boolean isSupported()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection()).serverSupportsFeature("jabber:iq:privacy");
    }

    public boolean removeListener(PrivacyListListener privacylistlistener)
    {
        return listeners.remove(privacylistlistener);
    }

    public void setActiveListName(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Privacy privacy = new Privacy();
        privacy.setActiveName(s);
        setRequest(privacy);
    }

    public void setDefaultListName(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Privacy privacy = new Privacy();
        privacy.setDefaultName(s);
        setRequest(privacy);
    }

    public void updatePrivacyList(String s, List list)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Privacy privacy = new Privacy();
        privacy.setPrivacyList(s, list);
        setRequest(privacy);
    }

    static 
    {
        PRIVACY_FILTER = new StanzaTypeFilter(org/jivesoftware/smackx/privacy/packet/Privacy);
        PRIVACY_RESULT = new AndFilter(new StanzaFilter[] {
            IQTypeFilter.RESULT, PRIVACY_FILTER
        });
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                PrivacyListManager.getInstanceFor(xmppconnection);
            }

        });
    }




/*
    static String access$202(PrivacyListManager privacylistmanager, String s)
    {
        privacylistmanager.cachedActiveListName = s;
        return s;
    }

*/



/*
    static String access$402(PrivacyListManager privacylistmanager, String s)
    {
        privacylistmanager.cachedDefaultListName = s;
        return s;
    }

*/
}
