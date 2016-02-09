// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PresenceListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromMatchesFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.MessageWithSubjectFilter;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.filter.ToFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.iqregister.packet.Registration;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCAdmin;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCOwner;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            UserStatusListener, ParticipantStatusListener, DiscussionHistory, MultiUserChatManager, 
//            InvitationRejectionListener, Affiliate, Occupant, MUCAffiliation, 
//            MUCRole, MUCNotJoinedException, SubjectUpdatedListener

public class MultiUserChat
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/muc/MultiUserChat.getName());
    private final XMPPConnection connection;
    private final StanzaListener declinesListener = new StanzaListener() {

        final MultiUserChat this$0;

        public void processPacket(Stanza stanza)
        {
            stanza = MUCUser.from(stanza);
            if (stanza.getDecline() == null)
            {
                return;
            } else
            {
                fireInvitationRejectionListeners(stanza.getDecline().getFrom(), stanza.getDecline().getReason());
                return;
            }
        }

            
            {
                this$0 = MultiUserChat.this;
                super();
            }
    };
    private final StanzaFilter fromRoomFilter;
    private final StanzaFilter fromRoomGroupchatFilter;
    private final Set invitationRejectionListeners = new CopyOnWriteArraySet();
    private boolean joined;
    private PacketCollector messageCollector;
    private final StanzaListener messageListener = new StanzaListener() {

        final MultiUserChat this$0;

        public void processPacket(Stanza stanza)
            throws org.jivesoftware.smack.SmackException.NotConnectedException
        {
            stanza = (Message)stanza;
            for (Iterator iterator = messageListeners.iterator(); iterator.hasNext(); ((MessageListener)iterator.next()).processMessage(stanza)) { }
        }

            
            {
                this$0 = MultiUserChat.this;
                super();
            }
    };
    private final Set messageListeners = new CopyOnWriteArraySet();
    private final MultiUserChatManager multiUserChatManager;
    private String nickname;
    private final Map occupantsMap = new ConcurrentHashMap();
    private final Set participantStatusListeners = new CopyOnWriteArraySet();
    private final StanzaListener presenceInterceptor = new StanzaListener() {

        final MultiUserChat this$0;

        public void processPacket(Stanza stanza)
        {
            stanza = (Presence)stanza;
            for (Iterator iterator = presenceInterceptors.iterator(); iterator.hasNext(); ((PresenceListener)iterator.next()).processPresence(stanza)) { }
        }

            
            {
                this$0 = MultiUserChat.this;
                super();
            }
    };
    private final Set presenceInterceptors = new CopyOnWriteArraySet();
    private final StanzaListener presenceListener = new StanzaListener() {

        final MultiUserChat this$0;

        public void processPacket(Stanza stanza)
        {
            Presence presence;
            String s1;
            Object obj;
            boolean flag;
            presence = (Presence)stanza;
            s1 = presence.getFrom();
            obj = (new StringBuilder()).append(room).append("/").append(nickname).toString();
            flag = presence.getFrom().equals(obj);
            static class _cls7
            {

                static final int $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[];

                static 
                {
                    $SwitchMap$org$jivesoftware$smack$packet$Presence$Type = new int[org.jivesoftware.smack.packet.Presence.Type.values().length];
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[org.jivesoftware.smack.packet.Presence.Type.available.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[org.jivesoftware.smack.packet.Presence.Type.unavailable.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror)
                    {
                        return;
                    }
                }
            }

            _cls7..SwitchMap.org.jivesoftware.smack.packet.Presence.Type[presence.getType().ordinal()];
            JVM INSTR tableswitch 1 2: default 92
        //                       1 132
        //                       2 278;
               goto _L1 _L2 _L3
_L1:
            for (stanza = presenceListeners.iterator(); stanza.hasNext(); ((PresenceListener)stanza.next()).processPresence(presence)) { }
            break; /* Loop/switch isn't completed */
_L2:
            if ((Presence)occupantsMap.put(s1, presence) != null)
            {
                Object obj1 = MUCUser.from(stanza);
                obj = ((MUCUser) (obj1)).getItem().getAffiliation();
                obj1 = ((MUCUser) (obj1)).getItem().getRole();
                Object obj2 = MUCUser.from(stanza);
                stanza = ((MUCUser) (obj2)).getItem().getAffiliation();
                obj2 = ((MUCUser) (obj2)).getItem().getRole();
                checkRoleModifications(((MUCRole) (obj1)), ((MUCRole) (obj2)), flag, s1);
                checkAffiliationModifications(((MUCAffiliation) (obj)), stanza, flag, s1);
            } else
            if (!flag)
            {
                stanza = participantStatusListeners.iterator();
                while (stanza.hasNext()) 
                {
                    ((ParticipantStatusListener)stanza.next()).joined(s1);
                }
            }
            continue; /* Loop/switch isn't completed */
_L3:
            occupantsMap.remove(s1);
            stanza = MUCUser.from(stanza);
            if (stanza == null || !stanza.hasStatus())
            {
                continue; /* Loop/switch isn't completed */
            }
            checkPresenceCode(stanza.getStatus(), presence.getFrom().equals(obj), stanza, s1);
            continue; /* Loop/switch isn't completed */
            if (flag) goto _L1; else goto _L4
_L4:
            stanza = participantStatusListeners.iterator();
            while (stanza.hasNext()) 
            {
                ((ParticipantStatusListener)stanza.next()).left(s1);
            }
            if (true) goto _L1; else goto _L5
_L5:
        }

            
            {
                this$0 = MultiUserChat.this;
                super();
            }
    };
    private final Set presenceListeners = new CopyOnWriteArraySet();
    private final String room;
    private String subject;
    private final StanzaListener subjectListener = new StanzaListener() {

        final MultiUserChat this$0;

        public void processPacket(Stanza stanza)
        {
            stanza = (Message)stanza;
            subject = stanza.getSubject();
            for (Iterator iterator = subjectUpdatedListeners.iterator(); iterator.hasNext(); ((SubjectUpdatedListener)iterator.next()).subjectUpdated(subject, stanza.getFrom())) { }
        }

            
            {
                this$0 = MultiUserChat.this;
                super();
            }
    };
    private final Set subjectUpdatedListeners = new CopyOnWriteArraySet();
    private final Set userStatusListeners = new CopyOnWriteArraySet();

    MultiUserChat(XMPPConnection xmppconnection, String s, MultiUserChatManager multiuserchatmanager)
    {
        nickname = null;
        joined = false;
        connection = xmppconnection;
        room = s.toLowerCase(Locale.US);
        multiUserChatManager = multiuserchatmanager;
        fromRoomFilter = FromMatchesFilter.create(s);
        fromRoomGroupchatFilter = new AndFilter(new StanzaFilter[] {
            fromRoomFilter, MessageTypeFilter.GROUPCHAT
        });
    }

    private void changeAffiliationByAdmin(String s, MUCAffiliation mucaffiliation)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(s, mucaffiliation, null);
    }

    private void changeAffiliationByAdmin(String s, MUCAffiliation mucaffiliation, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        MUCAdmin mucadmin = new MUCAdmin();
        mucadmin.setTo(room);
        mucadmin.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        mucadmin.addItem(new MUCItem(mucaffiliation, s, s1));
        connection.createPacketCollectorAndSend(mucadmin).nextResultOrThrow();
    }

    private void changeAffiliationByAdmin(Collection collection, MUCAffiliation mucaffiliation)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        MUCAdmin mucadmin = new MUCAdmin();
        mucadmin.setTo(room);
        mucadmin.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        for (collection = collection.iterator(); collection.hasNext(); mucadmin.addItem(new MUCItem(mucaffiliation, (String)collection.next()))) { }
        connection.createPacketCollectorAndSend(mucadmin).nextResultOrThrow();
    }

    private void changeRole(String s, MUCRole mucrole, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        MUCAdmin mucadmin = new MUCAdmin();
        mucadmin.setTo(room);
        mucadmin.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        mucadmin.addItem(new MUCItem(mucrole, s, s1));
        connection.createPacketCollectorAndSend(mucadmin).nextResultOrThrow();
    }

    private void changeRole(Collection collection, MUCRole mucrole)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        MUCAdmin mucadmin = new MUCAdmin();
        mucadmin.setTo(room);
        mucadmin.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        for (collection = collection.iterator(); collection.hasNext(); mucadmin.addItem(new MUCItem(mucrole, (String)collection.next()))) { }
        connection.createPacketCollectorAndSend(mucadmin).nextResultOrThrow();
    }

    private void checkAffiliationModifications(MUCAffiliation mucaffiliation, MUCAffiliation mucaffiliation1, boolean flag, String s)
    {
        if ("owner".equals(mucaffiliation) && !"owner".equals(mucaffiliation1))
        {
            if (flag)
            {
                for (Iterator iterator = userStatusListeners.iterator(); iterator.hasNext(); ((UserStatusListener)iterator.next()).ownershipRevoked()) { }
            } else
            {
                for (Iterator iterator1 = participantStatusListeners.iterator(); iterator1.hasNext(); ((ParticipantStatusListener)iterator1.next()).ownershipRevoked(s)) { }
            }
        } else
        if ("admin".equals(mucaffiliation) && !"admin".equals(mucaffiliation1))
        {
            if (flag)
            {
                for (Iterator iterator2 = userStatusListeners.iterator(); iterator2.hasNext(); ((UserStatusListener)iterator2.next()).adminRevoked()) { }
            } else
            {
                for (Iterator iterator3 = participantStatusListeners.iterator(); iterator3.hasNext(); ((ParticipantStatusListener)iterator3.next()).adminRevoked(s)) { }
            }
        } else
        if ("member".equals(mucaffiliation) && !"member".equals(mucaffiliation1))
        {
            if (flag)
            {
                for (Iterator iterator4 = userStatusListeners.iterator(); iterator4.hasNext(); ((UserStatusListener)iterator4.next()).membershipRevoked()) { }
            } else
            {
                for (Iterator iterator5 = participantStatusListeners.iterator(); iterator5.hasNext(); ((ParticipantStatusListener)iterator5.next()).membershipRevoked(s)) { }
            }
        }
        if (!"owner".equals(mucaffiliation) && "owner".equals(mucaffiliation1))
        {
            if (flag)
            {
                for (mucaffiliation = userStatusListeners.iterator(); mucaffiliation.hasNext(); ((UserStatusListener)mucaffiliation.next()).ownershipGranted()) { }
            } else
            {
                for (mucaffiliation = participantStatusListeners.iterator(); mucaffiliation.hasNext(); ((ParticipantStatusListener)mucaffiliation.next()).ownershipGranted(s)) { }
            }
        } else
        if (!"admin".equals(mucaffiliation) && "admin".equals(mucaffiliation1))
        {
            if (flag)
            {
                for (mucaffiliation = userStatusListeners.iterator(); mucaffiliation.hasNext(); ((UserStatusListener)mucaffiliation.next()).adminGranted()) { }
            } else
            {
                for (mucaffiliation = participantStatusListeners.iterator(); mucaffiliation.hasNext(); ((ParticipantStatusListener)mucaffiliation.next()).adminGranted(s)) { }
            }
        } else
        if (!"member".equals(mucaffiliation) && "member".equals(mucaffiliation1))
        {
            if (flag)
            {
                for (mucaffiliation = userStatusListeners.iterator(); mucaffiliation.hasNext(); ((UserStatusListener)mucaffiliation.next()).membershipGranted()) { }
            } else
            {
                for (mucaffiliation = participantStatusListeners.iterator(); mucaffiliation.hasNext(); ((ParticipantStatusListener)mucaffiliation.next()).membershipGranted(s)) { }
            }
        }
    }

    private void checkPresenceCode(Set set, boolean flag, MUCUser mucuser, String s)
    {
        if (set.contains(org.jivesoftware.smackx.muc.packet.MUCUser.Status.KICKED_307))
        {
            if (flag)
            {
                joined = false;
                for (Iterator iterator = userStatusListeners.iterator(); iterator.hasNext(); ((UserStatusListener)iterator.next()).kicked(mucuser.getItem().getActor(), mucuser.getItem().getReason())) { }
                occupantsMap.clear();
                nickname = null;
                userHasLeft();
            } else
            {
                Iterator iterator2 = participantStatusListeners.iterator();
                while (iterator2.hasNext()) 
                {
                    ((ParticipantStatusListener)iterator2.next()).kicked(s, mucuser.getItem().getActor(), mucuser.getItem().getReason());
                }
            }
        }
        if (set.contains(org.jivesoftware.smackx.muc.packet.MUCUser.Status.BANNED_301))
        {
            if (flag)
            {
                joined = false;
                for (Iterator iterator1 = userStatusListeners.iterator(); iterator1.hasNext(); ((UserStatusListener)iterator1.next()).banned(mucuser.getItem().getActor(), mucuser.getItem().getReason())) { }
                occupantsMap.clear();
                nickname = null;
                userHasLeft();
            } else
            {
                Iterator iterator4 = participantStatusListeners.iterator();
                while (iterator4.hasNext()) 
                {
                    ((ParticipantStatusListener)iterator4.next()).banned(s, mucuser.getItem().getActor(), mucuser.getItem().getReason());
                }
            }
        }
        if (set.contains(org.jivesoftware.smackx.muc.packet.MUCUser.Status.REMOVED_AFFIL_CHANGE_321) && flag)
        {
            joined = false;
            for (Iterator iterator3 = userStatusListeners.iterator(); iterator3.hasNext(); ((UserStatusListener)iterator3.next()).membershipRevoked()) { }
            occupantsMap.clear();
            nickname = null;
            userHasLeft();
        }
        if (set.contains(org.jivesoftware.smackx.muc.packet.MUCUser.Status.NEW_NICKNAME_303))
        {
            for (set = participantStatusListeners.iterator(); set.hasNext(); ((ParticipantStatusListener)set.next()).nicknameChanged(s, mucuser.getItem().getNick())) { }
        }
    }

    private void checkRoleModifications(MUCRole mucrole, MUCRole mucrole1, boolean flag, String s)
    {
        if (("visitor".equals(mucrole) || "none".equals(mucrole)) && "participant".equals(mucrole1))
        {
            if (flag)
            {
                for (Iterator iterator = userStatusListeners.iterator(); iterator.hasNext(); ((UserStatusListener)iterator.next()).voiceGranted()) { }
            } else
            {
                for (Iterator iterator1 = participantStatusListeners.iterator(); iterator1.hasNext(); ((ParticipantStatusListener)iterator1.next()).voiceGranted(s)) { }
            }
        } else
        if ("participant".equals(mucrole) && ("visitor".equals(mucrole1) || "none".equals(mucrole1)))
        {
            if (flag)
            {
                for (Iterator iterator2 = userStatusListeners.iterator(); iterator2.hasNext(); ((UserStatusListener)iterator2.next()).voiceRevoked()) { }
            } else
            {
                for (Iterator iterator3 = participantStatusListeners.iterator(); iterator3.hasNext(); ((ParticipantStatusListener)iterator3.next()).voiceRevoked(s)) { }
            }
        }
        if (!"moderator".equals(mucrole) && "moderator".equals(mucrole1))
        {
            if ("visitor".equals(mucrole) || "none".equals(mucrole))
            {
                if (flag)
                {
                    for (mucrole = userStatusListeners.iterator(); mucrole.hasNext(); ((UserStatusListener)mucrole.next()).voiceGranted()) { }
                } else
                {
                    for (mucrole = participantStatusListeners.iterator(); mucrole.hasNext(); ((ParticipantStatusListener)mucrole.next()).voiceGranted(s)) { }
                }
            }
            if (flag)
            {
                for (mucrole = userStatusListeners.iterator(); mucrole.hasNext(); ((UserStatusListener)mucrole.next()).moderatorGranted()) { }
            } else
            {
                for (mucrole = participantStatusListeners.iterator(); mucrole.hasNext(); ((ParticipantStatusListener)mucrole.next()).moderatorGranted(s)) { }
            }
        } else
        if ("moderator".equals(mucrole) && !"moderator".equals(mucrole1))
        {
            if ("visitor".equals(mucrole1) || "none".equals(mucrole1))
            {
                if (flag)
                {
                    for (mucrole = userStatusListeners.iterator(); mucrole.hasNext(); ((UserStatusListener)mucrole.next()).voiceRevoked()) { }
                } else
                {
                    for (mucrole = participantStatusListeners.iterator(); mucrole.hasNext(); ((ParticipantStatusListener)mucrole.next()).voiceRevoked(s)) { }
                }
            }
            if (flag)
            {
                for (mucrole = userStatusListeners.iterator(); mucrole.hasNext(); ((UserStatusListener)mucrole.next()).moderatorRevoked()) { }
            } else
            {
                for (mucrole = participantStatusListeners.iterator(); mucrole.hasNext(); ((ParticipantStatusListener)mucrole.next()).moderatorRevoked(s)) { }
            }
        }
    }

    private Presence enter(String s, String s1, DiscussionHistory discussionhistory, long l)
        throws org.jivesoftware.smack.SmackException.NotConnectedException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException
    {
        Presence presence;
        StringUtils.requireNotNullOrEmpty(s, "Nickname must not be null or blank.");
        presence = new Presence(org.jivesoftware.smack.packet.Presence.Type.available);
        presence.setTo((new StringBuilder()).append(room).append("/").append(s).toString());
        MUCInitialPresence mucinitialpresence = new MUCInitialPresence();
        if (s1 != null)
        {
            mucinitialpresence.setPassword(s1);
        }
        if (discussionhistory != null)
        {
            mucinitialpresence.setHistory(discussionhistory.getMUCHistory());
        }
        presence.addExtension(mucinitialpresence);
        s1 = new AndFilter(new StanzaFilter[] {
            FromMatchesFilter.createFull((new StringBuilder()).append(room).append("/").append(s).toString()), new StanzaTypeFilter(org/jivesoftware/smack/packet/Presence)
        });
        connection.addSyncStanzaListener(messageListener, fromRoomGroupchatFilter);
        connection.addSyncStanzaListener(presenceListener, new AndFilter(new StanzaFilter[] {
            fromRoomFilter, StanzaTypeFilter.PRESENCE
        }));
        connection.addSyncStanzaListener(subjectListener, new AndFilter(new StanzaFilter[] {
            fromRoomFilter, MessageWithSubjectFilter.INSTANCE
        }));
        connection.addSyncStanzaListener(declinesListener, new AndFilter(new StanzaFilter[] {
            new StanzaExtensionFilter("x", "http://jabber.org/protocol/muc#user"), new NotFilter(MessageTypeFilter.ERROR)
        }));
        connection.addPacketInterceptor(presenceInterceptor, new AndFilter(new StanzaFilter[] {
            new ToFilter(room), StanzaTypeFilter.PRESENCE
        }));
        messageCollector = connection.createPacketCollector(fromRoomGroupchatFilter);
        s1 = (Presence)connection.createPacketCollectorAndSend(s1, presence).nextResultOrThrow(l);
        nickname = s;
        joined = true;
        multiUserChatManager.addJoinedRoom(room);
        return s1;
        s;
_L2:
        removeConnectionCallbacks();
        throw s;
        s;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private void fireInvitationRejectionListeners(String s, String s1)
    {
        InvitationRejectionListener ainvitationrejectionlistener[];
        synchronized (invitationRejectionListeners)
        {
            ainvitationrejectionlistener = new InvitationRejectionListener[invitationRejectionListeners.size()];
            invitationRejectionListeners.toArray(ainvitationrejectionlistener);
        }
        int j = ainvitationrejectionlistener.length;
        for (int i = 0; i < j; i++)
        {
            ainvitationrejectionlistener[i].invitationDeclined(s, s1);
        }

        break MISSING_BLOCK_LABEL_76;
        s;
        set;
        JVM INSTR monitorexit ;
        throw s;
    }

    private List getAffiliatesByAdmin(MUCAffiliation mucaffiliation)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj = new MUCAdmin();
        ((MUCAdmin) (obj)).setTo(room);
        ((MUCAdmin) (obj)).setType(org.jivesoftware.smack.packet.IQ.Type.get);
        ((MUCAdmin) (obj)).addItem(new MUCItem(mucaffiliation));
        obj = (MUCAdmin)connection.createPacketCollectorAndSend(((IQ) (obj))).nextResultOrThrow();
        mucaffiliation = new ArrayList();
        for (obj = ((MUCAdmin) (obj)).getItems().iterator(); ((Iterator) (obj)).hasNext(); mucaffiliation.add(new Affiliate((MUCItem)((Iterator) (obj)).next()))) { }
        return mucaffiliation;
    }

    private List getOccupants(MUCRole mucrole)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj = new MUCAdmin();
        ((MUCAdmin) (obj)).setTo(room);
        ((MUCAdmin) (obj)).setType(org.jivesoftware.smack.packet.IQ.Type.get);
        ((MUCAdmin) (obj)).addItem(new MUCItem(mucrole));
        obj = (MUCAdmin)connection.createPacketCollectorAndSend(((IQ) (obj))).nextResultOrThrow();
        mucrole = new ArrayList();
        for (obj = ((MUCAdmin) (obj)).getItems().iterator(); ((Iterator) (obj)).hasNext(); mucrole.add(new Occupant((MUCItem)((Iterator) (obj)).next()))) { }
        return mucrole;
    }

    private void removeConnectionCallbacks()
    {
        connection.removeSyncStanzaListener(messageListener);
        connection.removeSyncStanzaListener(presenceListener);
        connection.removeSyncStanzaListener(declinesListener);
        connection.removePacketInterceptor(presenceInterceptor);
        if (messageCollector != null)
        {
            messageCollector.cancel();
            messageCollector = null;
        }
    }

    private void userHasLeft()
    {
        this;
        JVM INSTR monitorenter ;
        multiUserChatManager.removeJoinedRoom(room);
        removeConnectionCallbacks();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean addInvitationRejectionListener(InvitationRejectionListener invitationrejectionlistener)
    {
        return invitationRejectionListeners.add(invitationrejectionlistener);
    }

    public boolean addMessageListener(MessageListener messagelistener)
    {
        return messageListeners.add(messagelistener);
    }

    public boolean addParticipantListener(PresenceListener presencelistener)
    {
        return presenceListeners.add(presencelistener);
    }

    public boolean addParticipantStatusListener(ParticipantStatusListener participantstatuslistener)
    {
        return participantStatusListeners.add(participantstatuslistener);
    }

    public void addPresenceInterceptor(PresenceListener presencelistener)
    {
        presenceInterceptors.add(presencelistener);
    }

    public boolean addSubjectUpdatedListener(SubjectUpdatedListener subjectupdatedlistener)
    {
        return subjectUpdatedListeners.add(subjectupdatedlistener);
    }

    public boolean addUserStatusListener(UserStatusListener userstatuslistener)
    {
        return userStatusListeners.add(userstatuslistener);
    }

    public void banUser(String s, String s1)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(s, MUCAffiliation.outcast, s1);
    }

    public void banUsers(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(collection, MUCAffiliation.outcast);
    }

    public void changeAvailabilityStatus(String s, org.jivesoftware.smack.packet.Presence.Mode mode)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        StringUtils.requireNotNullOrEmpty(nickname, "Nickname must not be null or blank.");
        if (!joined)
        {
            throw new IllegalStateException("Must be logged into the room to change the availability status.");
        } else
        {
            Presence presence = new Presence(org.jivesoftware.smack.packet.Presence.Type.available);
            presence.setStatus(s);
            presence.setMode(mode);
            presence.setTo((new StringBuilder()).append(room).append("/").append(nickname).toString());
            connection.sendStanza(presence);
            return;
        }
    }

    public void changeNickname(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        StringUtils.requireNotNullOrEmpty(s, "Nickname must not be null or blank.");
        if (!joined)
        {
            throw new IllegalStateException("Must be logged into the room to change nickname.");
        } else
        {
            Presence presence = new Presence(org.jivesoftware.smack.packet.Presence.Type.available);
            presence.setTo((new StringBuilder()).append(room).append("/").append(s).toString());
            AndFilter andfilter = new AndFilter(new StanzaFilter[] {
                FromMatchesFilter.createFull((new StringBuilder()).append(room).append("/").append(s).toString()), new StanzaTypeFilter(org/jivesoftware/smack/packet/Presence)
            });
            connection.createPacketCollectorAndSend(andfilter, presence).nextResultOrThrow();
            nickname = s;
            return;
        }
    }

    public void changeSubject(final String subject)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Message message = createMessage();
        message.setSubject(subject);
        subject = new AndFilter(new StanzaFilter[] {
            fromRoomGroupchatFilter, new StanzaFilter() {

                final MultiUserChat this$0;
                final String val$subject;

                public boolean accept(Stanza stanza)
                {
                    stanza = (Message)stanza;
                    return subject.equals(stanza.getSubject());
                }

            
            {
                this$0 = MultiUserChat.this;
                subject = s;
                super();
            }
            }
        });
        connection.createPacketCollectorAndSend(subject, message).nextResultOrThrow();
    }

    public void create(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, SmackException
    {
        this;
        JVM INSTR monitorenter ;
        if (joined)
        {
            throw new IllegalStateException("Creation failed - User already joined the room.");
        }
        break MISSING_BLOCK_LABEL_25;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        boolean flag = createOrJoin(s);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        leave();
        throw new SmackException("Creation failed - Missing acknowledge of room creation.");
    }

    public Message createMessage()
    {
        return new Message(room, org.jivesoftware.smack.packet.Message.Type.groupchat);
    }

    public boolean createOrJoin(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, SmackException
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = createOrJoin(s, null, null, connection.getPacketReplyTimeout());
        this;
        JVM INSTR monitorexit ;
        return flag;
        s;
        throw s;
    }

    public boolean createOrJoin(String s, String s1, DiscussionHistory discussionhistory, long l)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, SmackException
    {
        this;
        JVM INSTR monitorenter ;
        if (joined)
        {
            throw new IllegalStateException("Creation failed - User already joined the room.");
        }
        break MISSING_BLOCK_LABEL_25;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        s = MUCUser.from(enter(s, s1, discussionhistory, l));
        if (s == null) goto _L2; else goto _L1
_L1:
        boolean flag = s.getStatus().contains(org.jivesoftware.smackx.muc.packet.MUCUser.Status.ROOM_CREATED_201);
        if (!flag) goto _L2; else goto _L3
_L3:
        flag = true;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public Chat createPrivateChat(String s, ChatMessageListener chatmessagelistener)
    {
        return ChatManager.getInstanceFor(connection).createChat(s, chatmessagelistener);
    }

    public void destroy(String s, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        MUCOwner mucowner = new MUCOwner();
        mucowner.setTo(room);
        mucowner.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        Destroy destroy1 = new Destroy();
        destroy1.setReason(s);
        destroy1.setJid(s1);
        mucowner.setDestroy(destroy1);
        connection.createPacketCollectorAndSend(mucowner).nextResultOrThrow();
        occupantsMap.clear();
        nickname = null;
        joined = false;
        userHasLeft();
    }

    public List getAdmins()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getAffiliatesByAdmin(MUCAffiliation.admin);
    }

    public Form getConfigurationForm()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        MUCOwner mucowner = new MUCOwner();
        mucowner.setTo(room);
        mucowner.setType(org.jivesoftware.smack.packet.IQ.Type.get);
        return Form.getFormFrom((IQ)connection.createPacketCollectorAndSend(mucowner).nextResultOrThrow());
    }

    public List getMembers()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getAffiliatesByAdmin(MUCAffiliation.member);
    }

    public List getModerators()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getOccupants(MUCRole.moderator);
    }

    public String getNickname()
    {
        return nickname;
    }

    public Occupant getOccupant(String s)
    {
        s = (Presence)occupantsMap.get(s);
        if (s != null)
        {
            return new Occupant(s);
        } else
        {
            return null;
        }
    }

    public Presence getOccupantPresence(String s)
    {
        return (Presence)occupantsMap.get(s);
    }

    public List getOccupants()
    {
        return Collections.unmodifiableList(new ArrayList(occupantsMap.keySet()));
    }

    public int getOccupantsCount()
    {
        return occupantsMap.size();
    }

    public List getOutcasts()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getAffiliatesByAdmin(MUCAffiliation.outcast);
    }

    public List getOwners()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getAffiliatesByAdmin(MUCAffiliation.owner);
    }

    public List getParticipants()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getOccupants(MUCRole.participant);
    }

    public Form getRegistrationForm()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Registration registration = new Registration();
        registration.setType(org.jivesoftware.smack.packet.IQ.Type.get);
        registration.setTo(room);
        return Form.getFormFrom((IQ)connection.createPacketCollectorAndSend(registration).nextResultOrThrow());
    }

    public String getReservedNickname()
        throws SmackException
    {
        Object obj;
        obj = ServiceDiscoveryManager.getInstanceFor(connection).discoverInfo(room, "x-roomuser-item").getIdentities().iterator();
        if (!((Iterator) (obj)).hasNext())
        {
            break MISSING_BLOCK_LABEL_64;
        }
        obj = ((org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity)((Iterator) (obj)).next()).getName();
        return ((String) (obj));
        XMPPException xmppexception;
        xmppexception;
        LOGGER.log(Level.SEVERE, "Error retrieving room nickname", xmppexception);
        return null;
    }

    public String getRoom()
    {
        return room;
    }

    public String getSubject()
    {
        return subject;
    }

    public void grantAdmin(String s)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(s, MUCAffiliation.admin);
    }

    public void grantAdmin(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(collection, MUCAffiliation.admin);
    }

    public void grantMembership(String s)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(s, MUCAffiliation.member, null);
    }

    public void grantMembership(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(collection, MUCAffiliation.member);
    }

    public void grantModerator(String s)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeRole(s, MUCRole.moderator, null);
    }

    public void grantModerator(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeRole(collection, MUCRole.moderator);
    }

    public void grantOwnership(String s)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(s, MUCAffiliation.owner, null);
    }

    public void grantOwnership(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(collection, MUCAffiliation.owner);
    }

    public void grantVoice(String s)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeRole(s, MUCRole.participant, null);
    }

    public void grantVoice(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeRole(collection, MUCRole.participant);
    }

    public void invite(String s, String s1)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        invite(new Message(), s, s1);
    }

    public void invite(Message message, String s, String s1)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        message.setTo(room);
        MUCUser mucuser = new MUCUser();
        org.jivesoftware.smackx.muc.packet.MUCUser.Invite invite1 = new org.jivesoftware.smackx.muc.packet.MUCUser.Invite();
        invite1.setTo(s);
        invite1.setReason(s1);
        mucuser.setInvite(invite1);
        message.addExtension(mucuser);
        connection.sendStanza(message);
    }

    public boolean isJoined()
    {
        return joined;
    }

    public void join(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        join(s, null, null, connection.getPacketReplyTimeout());
    }

    public void join(String s, String s1)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, SmackException
    {
        join(s, s1, null, connection.getPacketReplyTimeout());
    }

    public void join(String s, String s1, DiscussionHistory discussionhistory, long l)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        this;
        JVM INSTR monitorenter ;
        if (joined)
        {
            leave();
        }
        enter(s, s1, discussionhistory, l);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void kickParticipant(String s, String s1)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeRole(s, MUCRole.none, s1);
    }

    public void leave()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = joined;
        if (flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Presence presence = new Presence(org.jivesoftware.smack.packet.Presence.Type.unavailable);
        presence.setTo((new StringBuilder()).append(room).append("/").append(nickname).toString());
        connection.sendStanza(presence);
        occupantsMap.clear();
        nickname = null;
        joined = false;
        userHasLeft();
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Message nextMessage()
        throws MUCNotJoinedException
    {
        if (messageCollector == null)
        {
            throw new MUCNotJoinedException(this);
        } else
        {
            return (Message)messageCollector.nextResult();
        }
    }

    public Message nextMessage(long l)
        throws MUCNotJoinedException
    {
        if (messageCollector == null)
        {
            throw new MUCNotJoinedException(this);
        } else
        {
            return (Message)messageCollector.nextResult(l);
        }
    }

    public Message pollMessage()
        throws MUCNotJoinedException
    {
        if (messageCollector == null)
        {
            throw new MUCNotJoinedException(this);
        } else
        {
            return (Message)messageCollector.pollResult();
        }
    }

    public boolean removeInvitationRejectionListener(InvitationRejectionListener invitationrejectionlistener)
    {
        return invitationRejectionListeners.remove(invitationrejectionlistener);
    }

    public boolean removeMessageListener(MessageListener messagelistener)
    {
        return messageListeners.remove(messagelistener);
    }

    public boolean removeParticipantListener(PresenceListener presencelistener)
    {
        return presenceListeners.remove(presencelistener);
    }

    public boolean removeParticipantStatusListener(ParticipantStatusListener participantstatuslistener)
    {
        return participantStatusListeners.remove(participantstatuslistener);
    }

    public void removePresenceInterceptor(StanzaListener stanzalistener)
    {
        presenceInterceptors.remove(stanzalistener);
    }

    public boolean removeSubjectUpdatedListener(SubjectUpdatedListener subjectupdatedlistener)
    {
        return subjectUpdatedListeners.remove(subjectupdatedlistener);
    }

    public boolean removeUserStatusListener(UserStatusListener userstatuslistener)
    {
        return userStatusListeners.remove(userstatuslistener);
    }

    public void requestVoice()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        DataForm dataform = new DataForm(org.jivesoftware.smackx.xdata.packet.DataForm.Type.submit);
        Object obj = new FormField("FORM_TYPE");
        ((FormField) (obj)).addValue("http://jabber.org/protocol/muc#request");
        dataform.addField(((FormField) (obj)));
        obj = new FormField("muc#role");
        ((FormField) (obj)).setType(org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        ((FormField) (obj)).setLabel("Requested role");
        ((FormField) (obj)).addValue("participant");
        dataform.addField(((FormField) (obj)));
        obj = new Message(room);
        ((Message) (obj)).addExtension(dataform);
        connection.sendStanza(((Stanza) (obj)));
    }

    public void revokeAdmin(String s)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(s, MUCAffiliation.member);
    }

    public void revokeAdmin(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(collection, MUCAffiliation.admin);
    }

    public void revokeMembership(String s)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(s, MUCAffiliation.none, null);
    }

    public void revokeMembership(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(collection, MUCAffiliation.none);
    }

    public void revokeModerator(String s)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeRole(s, MUCRole.participant, null);
    }

    public void revokeModerator(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeRole(collection, MUCRole.participant);
    }

    public void revokeOwnership(String s)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(s, MUCAffiliation.admin, null);
    }

    public void revokeOwnership(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeAffiliationByAdmin(collection, MUCAffiliation.admin);
    }

    public void revokeVoice(String s)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeRole(s, MUCRole.visitor, null);
    }

    public void revokeVoice(Collection collection)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        changeRole(collection, MUCRole.visitor);
    }

    public void sendConfigurationForm(Form form)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        MUCOwner mucowner = new MUCOwner();
        mucowner.setTo(room);
        mucowner.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        mucowner.addExtension(form.getDataFormToSend());
        connection.createPacketCollectorAndSend(mucowner).nextResultOrThrow();
    }

    public void sendMessage(String s)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Message message = createMessage();
        message.setBody(s);
        connection.sendStanza(message);
    }

    public void sendMessage(Message message)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        message.setTo(room);
        message.setType(org.jivesoftware.smack.packet.Message.Type.groupchat);
        connection.sendStanza(message);
    }

    public void sendRegistrationForm(Form form)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Registration registration = new Registration();
        registration.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        registration.setTo(room);
        registration.addExtension(form.getDataFormToSend());
        connection.createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    public String toString()
    {
        return (new StringBuilder()).append("MUC: ").append(room).append("(").append(connection.getUser()).append(")").toString();
    }






/*
    static String access$102(MultiUserChat multiuserchat, String s)
    {
        multiuserchat.subject = s;
        return s;
    }

*/










}
