// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.disco.AbstractNodeInformationProvider;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.muc.packet.MUCUser;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChat, HostedRoom, RoomInfo, InvitationListener

public class MultiUserChatManager extends Manager
{

    private static final String DISCO_NODE = "http://jabber.org/protocol/muc#rooms";
    private static final Map INSTANCES = new WeakHashMap();
    private static final StanzaFilter INVITATION_FILTER;
    private final Set invitationsListeners = new CopyOnWriteArraySet();
    private final Set joinedRooms = new HashSet();
    private final Map multiUserChats = new HashMap();

    private MultiUserChatManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        xmppconnection.addAsyncStanzaListener(new StanzaListener() {

            final MultiUserChatManager this$0;

            public void processPacket(Stanza stanza)
            {
                Message message = (Message)stanza;
                MUCUser mucuser = MUCUser.from(message);
                if (mucuser.getInvite() != null)
                {
                    stanza = getMultiUserChat(stanza.getFrom());
                    for (Iterator iterator = invitationsListeners.iterator(); iterator.hasNext(); ((InvitationListener)iterator.next()).invitationReceived(connection(), stanza, mucuser.getInvite().getFrom(), mucuser.getInvite().getReason(), mucuser.getPassword(), message)) { }
                }
            }

            
            {
                this$0 = MultiUserChatManager.this;
                super();
            }
        }, INVITATION_FILTER);
    }

    private MultiUserChat createNewMucAndAddToMap(String s)
    {
        MultiUserChat multiuserchat = new MultiUserChat(connection(), s, this);
        multiUserChats.put(s, new WeakReference(multiuserchat));
        return multiuserchat;
    }

    public static MultiUserChatManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/muc/MultiUserChatManager;
        JVM INSTR monitorenter ;
        MultiUserChatManager multiuserchatmanager1 = (MultiUserChatManager)INSTANCES.get(xmppconnection);
        MultiUserChatManager multiuserchatmanager;
        multiuserchatmanager = multiuserchatmanager1;
        if (multiuserchatmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        multiuserchatmanager = new MultiUserChatManager(xmppconnection);
        INSTANCES.put(xmppconnection, multiuserchatmanager);
        org/jivesoftware/smackx/muc/MultiUserChatManager;
        JVM INSTR monitorexit ;
        return multiuserchatmanager;
        xmppconnection;
        throw xmppconnection;
    }

    public void addInvitationListener(InvitationListener invitationlistener)
    {
        invitationsListeners.add(invitationlistener);
    }

    void addJoinedRoom(String s)
    {
        joinedRooms.add(s);
    }

    public void decline(String s, String s1, String s2)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        s = new Message(s);
        MUCUser mucuser = new MUCUser();
        org.jivesoftware.smackx.muc.packet.MUCUser.Decline decline1 = new org.jivesoftware.smackx.muc.packet.MUCUser.Decline();
        decline1.setTo(s1);
        decline1.setReason(s2);
        mucuser.setDecline(decline1);
        s.addExtension(mucuser);
        connection().sendStanza(s);
    }

    public List getHostedRooms(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj = ServiceDiscoveryManager.getInstanceFor(connection()).discoverItems(s).getItems();
        s = new ArrayList(((List) (obj)).size());
        for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); s.add(new HostedRoom((org.jivesoftware.smackx.disco.packet.DiscoverItems.Item)((Iterator) (obj)).next()))) { }
        return s;
    }

    public List getJoinedRooms(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj = ServiceDiscoveryManager.getInstanceFor(connection()).discoverItems(s, "http://jabber.org/protocol/muc#rooms").getItems();
        s = new ArrayList(((List) (obj)).size());
        for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); s.add(((org.jivesoftware.smackx.disco.packet.DiscoverItems.Item)((Iterator) (obj)).next()).getEntityID())) { }
        return s;
    }

    public Set getJoinedRooms()
    {
        return Collections.unmodifiableSet(joinedRooms);
    }

    public MultiUserChat getMultiUserChat(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = (WeakReference)multiUserChats.get(s);
        if (obj != null) goto _L2; else goto _L1
_L1:
        obj = createNewMucAndAddToMap(s);
_L4:
        this;
        JVM INSTR monitorexit ;
        return ((MultiUserChat) (obj));
_L2:
        MultiUserChat multiuserchat = (MultiUserChat)((WeakReference) (obj)).get();
        obj = multiuserchat;
        if (multiuserchat != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = createNewMucAndAddToMap(s);
        if (true) goto _L4; else goto _L3
_L3:
        s;
        throw s;
    }

    public RoomInfo getRoomInfo(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return new RoomInfo(ServiceDiscoveryManager.getInstanceFor(connection()).discoverInfo(s));
    }

    public List getServiceNames()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection()).findServices("http://jabber.org/protocol/muc", false, false);
    }

    public boolean isServiceEnabled(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(s, "http://jabber.org/protocol/muc");
    }

    public void removeInvitationListener(InvitationListener invitationlistener)
    {
        invitationsListeners.remove(invitationlistener);
    }

    void removeJoinedRoom(String s)
    {
        joinedRooms.remove(s);
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("http://jabber.org/protocol/muc");
                WeakReference weakreference = new WeakReference(xmppconnection);
                ServiceDiscoveryManager.getInstanceFor(xmppconnection).setNodeInformationProvider("http://jabber.org/protocol/muc#rooms", weakreference. new AbstractNodeInformationProvider() {

                    final _cls1 this$0;
                    final WeakReference val$weakRefConnection;

                    public List getNodeItems()
                    {
                        Object obj = (XMPPConnection)weakRefConnection.get();
                        if (obj != null) goto _L2; else goto _L1
_L1:
                        obj = Collections.emptyList();
_L4:
                        return ((List) (obj));
_L2:
                        obj = MultiUserChatManager.getInstanceFor(((XMPPConnection) (obj))).getJoinedRooms();
                        ArrayList arraylist = new ArrayList();
                        Iterator iterator = ((Set) (obj)).iterator();
                        do
                        {
                            obj = arraylist;
                            if (!iterator.hasNext())
                            {
                                continue;
                            }
                            arraylist.add(new org.jivesoftware.smackx.disco.packet.DiscoverItems.Item((String)iterator.next()));
                        } while (true);
                        if (true) goto _L4; else goto _L3
_L3:
                    }

            
            {
                this$0 = final__pcls1;
                weakRefConnection = WeakReference.this;
                super();
            }
                });
            }

        });
        INVITATION_FILTER = new AndFilter(new StanzaFilter[] {
            StanzaTypeFilter.MESSAGE, new StanzaExtensionFilter(new MUCUser()), new NotFilter(MessageTypeFilter.ERROR)
        });
    }


}
