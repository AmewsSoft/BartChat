// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ExceptionCallback;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.jivesoftware.smack.roster.rosterstore.RosterStore;
import org.jivesoftware.smack.util.Objects;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smack.roster:
//            RosterEntry, RosterGroup, RosterListener, RosterEntries, 
//            RosterLoadedListener

public class Roster extends Manager
{
    private class PresencePacketListener
        implements StanzaListener
    {

        final Roster this$0;

        private Map getUserPresences(String s)
        {
            Map map = (Map)presenceMap.get(s);
            Object obj = map;
            if (map == null)
            {
                obj = new ConcurrentHashMap();
                presenceMap.put(s, obj);
            }
            return ((Map) (obj));
        }

        public void processPacket(Stanza stanza)
            throws org.jivesoftware.smack.SmackException.NotConnectedException
        {
            Presence presence;
            XMPPConnection xmppconnection;
            String s;
            String s1;
            xmppconnection = connection();
            presence = (Presence)stanza;
            s1 = presence.getFrom();
            s = getMapKey(s1);
            stanza = null;
            static class _cls4
            {

                static final int $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[];
                static final int $SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode[];
                static final int $SwitchMap$org$jivesoftware$smack$roster$packet$RosterPacket$ItemType[];

                static 
                {
                    $SwitchMap$org$jivesoftware$smack$packet$Presence$Type = new int[org.jivesoftware.smack.packet.Presence.Type.values().length];
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[org.jivesoftware.smack.packet.Presence.Type.available.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror11) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[org.jivesoftware.smack.packet.Presence.Type.unavailable.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror10) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[org.jivesoftware.smack.packet.Presence.Type.subscribe.ordinal()] = 3;
                    }
                    catch (NoSuchFieldError nosuchfielderror9) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[org.jivesoftware.smack.packet.Presence.Type.unsubscribe.ordinal()] = 4;
                    }
                    catch (NoSuchFieldError nosuchfielderror8) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[org.jivesoftware.smack.packet.Presence.Type.error.ordinal()] = 5;
                    }
                    catch (NoSuchFieldError nosuchfielderror7) { }
                    $SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode = new int[SubscriptionMode.values().length];
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode[SubscriptionMode.accept_all.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror6) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode[SubscriptionMode.reject_all.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror5) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$roster$Roster$SubscriptionMode[SubscriptionMode.manual.ordinal()] = 3;
                    }
                    catch (NoSuchFieldError nosuchfielderror4) { }
                    $SwitchMap$org$jivesoftware$smack$roster$packet$RosterPacket$ItemType = new int[org.jivesoftware.smack.roster.packet.RosterPacket.ItemType.values().length];
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$roster$packet$RosterPacket$ItemType[org.jivesoftware.smack.roster.packet.RosterPacket.ItemType.from.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$roster$packet$RosterPacket$ItemType[org.jivesoftware.smack.roster.packet.RosterPacket.ItemType.both.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$roster$packet$RosterPacket$ItemType[org.jivesoftware.smack.roster.packet.RosterPacket.ItemType.none.ordinal()] = 3;
                    }
                    catch (NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smack$roster$packet$RosterPacket$ItemType[org.jivesoftware.smack.roster.packet.RosterPacket.ItemType.to.ordinal()] = 4;
                    }
                    catch (NoSuchFieldError nosuchfielderror)
                    {
                        return;
                    }
                }
            }

            _cls4..SwitchMap.org.jivesoftware.smack.packet.Presence.Type[presence.getType().ordinal()];
            JVM INSTR tableswitch 1 5: default 76
        //                       1 77
        //                       2 132
        //                       3 235
        //                       4 320
        //                       5 360;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            return;
_L2:
            stanza = getUserPresences(s);
            stanza.remove("");
            stanza.put(XmppStringUtils.parseResource(s1), presence);
            if (entries.containsKey(s))
            {
                fireRosterPresenceEvent(presence);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L3:
            if ("".equals(XmppStringUtils.parseResource(s1)))
            {
                getUserPresences(s).put("", presence);
            } else
            if (presenceMap.get(s) != null)
            {
                ((Map)presenceMap.get(s)).put(XmppStringUtils.parseResource(s1), presence);
            }
            if (entries.containsKey(s))
            {
                fireRosterPresenceEvent(presence);
                return;
            }
            if (true) goto _L1; else goto _L4
_L4:
            switch (_cls4..SwitchMap.org.jivesoftware.smack.roster.Roster.SubscriptionMode[subscriptionMode.ordinal()])
            {
            default:
                break;

            case 1: // '\001'
                break; /* Loop/switch isn't completed */

            case 2: // '\002'
                break;
            }
            break MISSING_BLOCK_LABEL_306;
_L8:
            if (stanza != null)
            {
                stanza.setTo(presence.getFrom());
                xmppconnection.sendStanza(stanza);
                return;
            }
            if (true) goto _L1; else goto _L7
_L7:
            stanza = new Presence(org.jivesoftware.smack.packet.Presence.Type.subscribed);
              goto _L8
            stanza = new Presence(org.jivesoftware.smack.packet.Presence.Type.unsubscribed);
              goto _L8
_L5:
            if (subscriptionMode != SubscriptionMode.manual)
            {
                stanza = new Presence(org.jivesoftware.smack.packet.Presence.Type.unsubscribed);
                stanza.setTo(presence.getFrom());
                xmppconnection.sendStanza(stanza);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L6:
            if ("".equals(XmppStringUtils.parseResource(s1)))
            {
                stanza = getUserPresences(s);
                stanza.clear();
                stanza.put("", presence);
                if (entries.containsKey(s))
                {
                    fireRosterPresenceEvent(presence);
                    return;
                }
            }
            if (true) goto _L1; else goto _L9
_L9:
        }

        private PresencePacketListener()
        {
            this$0 = Roster.this;
            super();
        }

    }

    private class RosterPushListener extends AbstractIqRequestHandler
    {

        final Roster this$0;

        public IQ handleIQRequest(IQ iq)
        {
            RosterPacket rosterpacket;
            Object obj;
            Object obj1;
            Object obj2;
            ArrayList arraylist;
            Object obj3;
            String s;
            obj = connection();
            rosterpacket = (RosterPacket)iq;
            obj1 = XmppStringUtils.parseBareJid(((XMPPConnection) (obj)).getUser());
            obj2 = rosterpacket.getFrom();
            if (obj2 != null && !((String) (obj2)).equals(obj1))
            {
                Roster.LOGGER.warning((new StringBuilder()).append("Ignoring roster push with a non matching 'from' ourJid='").append(((String) (obj1))).append("' from='").append(((String) (obj2))).append("'").toString());
                return IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.service_unavailable));
            }
            obj3 = rosterpacket.getRosterItems();
            if (((Collection) (obj3)).size() != 1)
            {
                Roster.LOGGER.warning((new StringBuilder()).append("Ignoring roster push with not exaclty one entry. size=").append(((Collection) (obj3)).size()).toString());
                return IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.bad_request));
            }
            iq = new ArrayList();
            obj1 = new ArrayList();
            obj2 = new ArrayList();
            arraylist = new ArrayList();
            obj3 = (org.jivesoftware.smack.roster.packet.RosterPacket.Item)((Collection) (obj3)).iterator().next();
            obj = new RosterEntry(((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj3)).getUser(), ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj3)).getName(), ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj3)).getItemType(), ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj3)).getItemStatus(), Roster.this, ((XMPPConnection) (obj)));
            s = rosterpacket.getVersion();
            if (!((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj3)).getItemType().equals(org.jivesoftware.smack.roster.packet.RosterPacket.ItemType.remove)) goto _L2; else goto _L1
_L1:
            deleteEntry(((Collection) (obj2)), ((RosterEntry) (obj)));
            if (rosterStore != null)
            {
                rosterStore.removeEntry(((RosterEntry) (obj)).getUser(), s);
            }
_L4:
            removeEmptyGroups();
            fireRosterChangedEvent(iq, ((Collection) (obj1)), ((Collection) (obj2)));
            return IQ.createResultIQ(rosterpacket);
_L2:
            if (Roster.hasValidSubscriptionType(((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj3))))
            {
                addUpdateEntry(iq, ((Collection) (obj1)), arraylist, ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj3)), ((RosterEntry) (obj)));
                if (rosterStore != null)
                {
                    rosterStore.addEntry(((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj3)), s);
                }
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        private RosterPushListener()
        {
            this$0 = Roster.this;
            super("query", "jabber:iq:roster", org.jivesoftware.smack.packet.IQ.Type.set, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.sync);
        }

    }

    private class RosterResultListener
        implements StanzaListener
    {

        final Roster this$0;

        public void processPacket(Stanza stanza)
        {
            Object obj2 = connection();
            Roster.LOGGER.fine("RosterResultListener received stanza");
            Object obj = new ArrayList();
            ArrayList arraylist = new ArrayList();
            ArrayList arraylist1 = new ArrayList();
            Object obj1 = new ArrayList();
            if (stanza instanceof RosterPacket)
            {
                RosterPacket rosterpacket = (RosterPacket)stanza;
                stanza = new ArrayList();
                Iterator iterator = rosterpacket.getRosterItems().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    org.jivesoftware.smack.roster.packet.RosterPacket.Item item1 = (org.jivesoftware.smack.roster.packet.RosterPacket.Item)iterator.next();
                    if (Roster.hasValidSubscriptionType(item1))
                    {
                        stanza.add(item1);
                    }
                } while (true);
                org.jivesoftware.smack.roster.packet.RosterPacket.Item item2;
                RosterEntry rosterentry1;
                for (Iterator iterator1 = stanza.iterator(); iterator1.hasNext(); addUpdateEntry(((Collection) (obj)), arraylist, ((Collection) (obj1)), item2, rosterentry1))
                {
                    item2 = (org.jivesoftware.smack.roster.packet.RosterPacket.Item)iterator1.next();
                    rosterentry1 = new RosterEntry(item2.getUser(), item2.getName(), item2.getItemType(), item2.getItemStatus(), Roster.this, ((XMPPConnection) (obj2)));
                }

                obj2 = new HashSet();
                for (Iterator iterator2 = entries.values().iterator(); iterator2.hasNext(); ((Set) (obj2)).add(((RosterEntry)iterator2.next()).getUser())) { }
                ((Set) (obj2)).removeAll(((Collection) (obj)));
                ((Set) (obj2)).removeAll(arraylist);
                ((Set) (obj2)).removeAll(((Collection) (obj1)));
                for (obj1 = ((Set) (obj2)).iterator(); ((Iterator) (obj1)).hasNext(); deleteEntry(arraylist1, (RosterEntry)entries.get(obj2)))
                {
                    obj2 = (String)((Iterator) (obj1)).next();
                }

                if (rosterStore != null)
                {
                    obj1 = rosterpacket.getVersion();
                    rosterStore.resetEntries(stanza, ((String) (obj1)));
                }
                removeEmptyGroups();
            } else
            {
                stanza = rosterStore.getEntries().iterator();
                while (stanza.hasNext()) 
                {
                    org.jivesoftware.smack.roster.packet.RosterPacket.Item item = (org.jivesoftware.smack.roster.packet.RosterPacket.Item)stanza.next();
                    RosterEntry rosterentry = new RosterEntry(item.getUser(), item.getName(), item.getItemType(), item.getItemStatus(), Roster.this, ((XMPPConnection) (obj2)));
                    addUpdateEntry(((Collection) (obj)), arraylist, ((Collection) (obj1)), item, rosterentry);
                }
            }
            loaded = true;
            synchronized (Roster.this)
            {
                notifyAll();
            }
            fireRosterChangedEvent(((Collection) (obj)), arraylist, arraylist1);
            stanza = rosterLoadedListeners;
            stanza;
            JVM INSTR monitorenter ;
            for (obj = rosterLoadedListeners.iterator(); ((Iterator) (obj)).hasNext(); ((RosterLoadedListener)((Iterator) (obj)).next()).onRosterLoaded(Roster.this)) { }
            break MISSING_BLOCK_LABEL_599;
            obj;
            stanza;
            JVM INSTR monitorexit ;
            try
            {
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch (Stanza stanza)
            {
                Roster.LOGGER.log(Level.WARNING, "RosterLoadedListener threw exception", stanza);
            }
            return;
            exception;
            stanza;
            JVM INSTR monitorexit ;
            throw exception;
            stanza;
            JVM INSTR monitorexit ;
        }

        private RosterResultListener()
        {
            this$0 = Roster.this;
            super();
        }

    }

    public static final class SubscriptionMode extends Enum
    {

        private static final SubscriptionMode $VALUES[];
        public static final SubscriptionMode accept_all;
        public static final SubscriptionMode manual;
        public static final SubscriptionMode reject_all;

        public static SubscriptionMode valueOf(String s)
        {
            return (SubscriptionMode)Enum.valueOf(org/jivesoftware/smack/roster/Roster$SubscriptionMode, s);
        }

        public static SubscriptionMode[] values()
        {
            return (SubscriptionMode[])$VALUES.clone();
        }

        static 
        {
            accept_all = new SubscriptionMode("accept_all", 0);
            reject_all = new SubscriptionMode("reject_all", 1);
            manual = new SubscriptionMode("manual", 2);
            $VALUES = (new SubscriptionMode[] {
                accept_all, reject_all, manual
            });
        }

        private SubscriptionMode(String s, int i)
        {
            super(s, i);
        }
    }


    private static final Map INSTANCES = new WeakHashMap();
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/roster/Roster.getName());
    private static final StanzaFilter PRESENCE_PACKET_FILTER;
    private static SubscriptionMode defaultSubscriptionMode;
    private static boolean rosterLoadedAtLoginDefault = true;
    private final Map entries;
    private final Map groups;
    private boolean loaded;
    private final Map presenceMap;
    private final PresencePacketListener presencePacketListener;
    private final Set rosterListeners;
    private final Object rosterListenersAndEntriesLock;
    private boolean rosterLoadedAtLogin;
    private final Set rosterLoadedListeners;
    private RosterStore rosterStore;
    private SubscriptionMode subscriptionMode;
    private final Set unfiledEntries;

    private Roster(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        groups = new ConcurrentHashMap();
        entries = new ConcurrentHashMap();
        unfiledEntries = new CopyOnWriteArraySet();
        rosterListeners = new LinkedHashSet();
        presenceMap = new ConcurrentHashMap();
        rosterLoadedListeners = new LinkedHashSet();
        rosterListenersAndEntriesLock = new Object();
        loaded = false;
        presencePacketListener = new PresencePacketListener();
        rosterLoadedAtLogin = rosterLoadedAtLoginDefault;
        subscriptionMode = getDefaultSubscriptionMode();
        xmppconnection.registerIQRequestHandler(new RosterPushListener());
        xmppconnection.addSyncStanzaListener(presencePacketListener, PRESENCE_PACKET_FILTER);
        xmppconnection.addConnectionListener(new AbstractConnectionClosedListener() {

            final Roster this$0;

            public void authenticated(XMPPConnection xmppconnection1, boolean flag)
            {
                while (xmppconnection1.isAnonymous() || !isRosterLoadedAtLogin() || flag) 
                {
                    return;
                }
                try
                {
                    reload();
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (XMPPConnection xmppconnection1)
                {
                    Roster.LOGGER.log(Level.SEVERE, "Could not reload Roster", xmppconnection1);
                }
            }

            public void connectionTerminated()
            {
                setOfflinePresencesAndResetLoaded();
            }

            
            {
                this$0 = Roster.this;
                super();
            }
        });
        if (!xmppconnection.isAuthenticated())
        {
            break MISSING_BLOCK_LABEL_170;
        }
        reload();
        return;
        xmppconnection;
        LOGGER.log(Level.SEVERE, "Could not reload Roster", xmppconnection);
        return;
    }

    private void addUpdateEntry(Collection collection, Collection collection1, Collection collection2, org.jivesoftware.smack.roster.packet.RosterPacket.Item item, RosterEntry rosterentry)
    {
        RosterEntry rosterentry1;
        synchronized (rosterListenersAndEntriesLock)
        {
            rosterentry1 = (RosterEntry)entries.put(item.getUser(), rosterentry);
        }
        if (rosterentry1 == null)
        {
            collection.add(item.getUser());
        } else
        {
            collection = RosterEntry.toRosterItem(rosterentry1);
            if (!rosterentry1.equalsDeep(rosterentry) || !item.getGroupNames().equals(collection.getGroupNames()))
            {
                collection1.add(item.getUser());
            } else
            {
                collection2.add(item.getUser());
            }
        }
        if (item.getGroupNames().isEmpty())
        {
            unfiledEntries.add(rosterentry);
        } else
        {
            unfiledEntries.remove(rosterentry);
        }
        collection2 = new ArrayList();
        for (item = item.getGroupNames().iterator(); item.hasNext(); collection.addEntryLocal(rosterentry))
        {
            obj = (String)item.next();
            collection2.add(obj);
            collection1 = getGroup(((String) (obj)));
            collection = collection1;
            if (collection1 == null)
            {
                collection = createGroup(((String) (obj)));
                groups.put(obj, collection);
            }
        }

        break MISSING_BLOCK_LABEL_252;
        collection;
        obj;
        JVM INSTR monitorexit ;
        throw collection;
        collection = new ArrayList();
        for (collection1 = getGroups().iterator(); collection1.hasNext(); collection.add(((RosterGroup)collection1.next()).getName())) { }
        collection.removeAll(collection2);
        collection = collection.iterator();
        do
        {
            if (!collection.hasNext())
            {
                break;
            }
            collection1 = (String)collection.next();
            collection2 = getGroup(collection1);
            collection2.removeEntryLocal(rosterentry);
            if (collection2.getEntryCount() == 0)
            {
                groups.remove(collection1);
            }
        } while (true);
        return;
    }

    private void deleteEntry(Collection collection, RosterEntry rosterentry)
    {
        String s = rosterentry.getUser();
        entries.remove(s);
        unfiledEntries.remove(rosterentry);
        presenceMap.remove(XmppStringUtils.parseBareJid(s));
        collection.add(s);
        collection = groups.entrySet().iterator();
        do
        {
            if (!collection.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)collection.next();
            RosterGroup rostergroup = (RosterGroup)entry.getValue();
            rostergroup.removeEntryLocal(rosterentry);
            if (rostergroup.getEntryCount() == 0)
            {
                groups.remove(entry.getKey());
            }
        } while (true);
    }

    private void fireRosterChangedEvent(Collection collection, Collection collection1, Collection collection2)
    {
        Object obj = rosterListenersAndEntriesLock;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = rosterListeners.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            RosterListener rosterlistener = (RosterListener)iterator.next();
            if (!collection.isEmpty())
            {
                rosterlistener.entriesAdded(collection);
            }
            if (!collection1.isEmpty())
            {
                rosterlistener.entriesUpdated(collection1);
            }
            if (!collection2.isEmpty())
            {
                rosterlistener.entriesDeleted(collection2);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_102;
        collection;
        obj;
        JVM INSTR monitorexit ;
        throw collection;
        obj;
        JVM INSTR monitorexit ;
    }

    private void fireRosterPresenceEvent(Presence presence)
    {
        Object obj = rosterListenersAndEntriesLock;
        obj;
        JVM INSTR monitorenter ;
        for (Iterator iterator = rosterListeners.iterator(); iterator.hasNext(); ((RosterListener)iterator.next()).presenceChanged(presence)) { }
        break MISSING_BLOCK_LABEL_49;
        presence;
        obj;
        JVM INSTR monitorexit ;
        throw presence;
        obj;
        JVM INSTR monitorexit ;
    }

    public static SubscriptionMode getDefaultSubscriptionMode()
    {
        return defaultSubscriptionMode;
    }

    public static Roster getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smack/roster/Roster;
        JVM INSTR monitorenter ;
        Roster roster1 = (Roster)INSTANCES.get(xmppconnection);
        Roster roster;
        roster = roster1;
        if (roster1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        roster = new Roster(xmppconnection);
        INSTANCES.put(xmppconnection, roster);
        org/jivesoftware/smack/roster/Roster;
        JVM INSTR monitorexit ;
        return roster;
        xmppconnection;
        throw xmppconnection;
    }

    private String getMapKey(String s)
    {
        String s1;
        if (s == null)
        {
            s1 = null;
        } else
        {
            s1 = s;
            if (!entries.containsKey(s))
            {
                return XmppStringUtils.parseBareJid(s).toLowerCase(Locale.US);
            }
        }
        return s1;
    }

    private static boolean hasValidSubscriptionType(org.jivesoftware.smack.roster.packet.RosterPacket.Item item)
    {
        switch (_cls4..SwitchMap.org.jivesoftware.smack.roster.packet.RosterPacket.ItemType[item.getItemType().ordinal()])
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return true;
        }
    }

    private void removeEmptyGroups()
    {
        Iterator iterator = getGroups().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            RosterGroup rostergroup = (RosterGroup)iterator.next();
            if (rostergroup.getEntryCount() == 0)
            {
                groups.remove(rostergroup.getName());
            }
        } while (true);
    }

    public static void setDefaultSubscriptionMode(SubscriptionMode subscriptionmode)
    {
        defaultSubscriptionMode = subscriptionmode;
    }

    private void setOfflinePresencesAndResetLoaded()
    {
        Iterator iterator = presenceMap.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            Object obj = (Map)presenceMap.get(s);
            if (obj != null)
            {
                obj = ((Map) (obj)).keySet().iterator();
                while (((Iterator) (obj)).hasNext()) 
                {
                    String s1 = (String)((Iterator) (obj)).next();
                    Presence presence = new Presence(org.jivesoftware.smack.packet.Presence.Type.unavailable);
                    presence.setFrom((new StringBuilder()).append(s).append("/").append(s1).toString());
                    try
                    {
                        presencePacketListener.processPacket(presence);
                    }
                    catch (org.jivesoftware.smack.SmackException.NotConnectedException notconnectedexception)
                    {
                        throw new IllegalStateException("presencePakcetListener should never throw a NotConnectedException when processPacket is called with a presence of type unavailable", notconnectedexception);
                    }
                }
            }
        } while (true);
        loaded = false;
    }

    public boolean addRosterListener(RosterListener rosterlistener)
    {
        boolean flag;
        synchronized (rosterListenersAndEntriesLock)
        {
            flag = rosterListeners.add(rosterlistener);
        }
        return flag;
        rosterlistener;
        obj;
        JVM INSTR monitorexit ;
        throw rosterlistener;
    }

    public boolean addRosterLoadedListener(RosterLoadedListener rosterloadedlistener)
    {
        rosterloadedlistener;
        JVM INSTR monitorenter ;
        boolean flag = rosterLoadedListeners.add(rosterloadedlistener);
        rosterloadedlistener;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        rosterloadedlistener;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean contains(String s)
    {
        return getEntry(s) != null;
    }

    public void createEntry(String s, String s1, String as[])
        throws org.jivesoftware.smack.SmackException.NotLoggedInException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        XMPPConnection xmppconnection = connection();
        if (!xmppconnection.isAuthenticated())
        {
            throw new org.jivesoftware.smack.SmackException.NotLoggedInException();
        }
        if (xmppconnection.isAnonymous())
        {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        }
        RosterPacket rosterpacket = new RosterPacket();
        rosterpacket.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        s1 = new org.jivesoftware.smack.roster.packet.RosterPacket.Item(s, s1);
        if (as != null)
        {
            int j = as.length;
            for (int i = 0; i < j; i++)
            {
                String s2 = as[i];
                if (s2 != null && s2.trim().length() > 0)
                {
                    s1.addGroupName(s2);
                }
            }

        }
        rosterpacket.addRosterItem(s1);
        xmppconnection.createPacketCollectorAndSend(rosterpacket).nextResultOrThrow();
        s1 = new Presence(org.jivesoftware.smack.packet.Presence.Type.subscribe);
        s1.setTo(s);
        xmppconnection.sendStanza(s1);
    }

    public RosterGroup createGroup(String s)
    {
        Object obj = connection();
        if (((XMPPConnection) (obj)).isAnonymous())
        {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        }
        if (groups.containsKey(s))
        {
            return (RosterGroup)groups.get(s);
        } else
        {
            obj = new RosterGroup(s, ((XMPPConnection) (obj)));
            groups.put(s, obj);
            return ((RosterGroup) (obj));
        }
    }

    public List getAllPresences(String s)
    {
        Object obj = (Map)presenceMap.get(getMapKey(s));
        if (obj != null) goto _L2; else goto _L1
_L1:
        Presence presence = new Presence(org.jivesoftware.smack.packet.Presence.Type.unavailable);
        presence.setFrom(s);
        s = new ArrayList(Arrays.asList(new Presence[] {
            presence
        }));
_L4:
        return s;
_L2:
        ArrayList arraylist = new ArrayList(((Map) (obj)).values().size());
        obj = ((Map) (obj)).values().iterator();
        do
        {
            s = arraylist;
            if (!((Iterator) (obj)).hasNext())
            {
                continue;
            }
            arraylist.add(((Presence)((Iterator) (obj)).next()).clone());
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public List getAvailablePresences(String s)
    {
        Object obj = getAllPresences(s);
        s = new ArrayList(((List) (obj)).size());
        obj = ((List) (obj)).iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            Presence presence = (Presence)((Iterator) (obj)).next();
            if (presence.isAvailable())
            {
                s.add(presence);
            }
        } while (true);
        return s;
    }

    public Set getEntries()
    {
        Object obj = rosterListenersAndEntriesLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = new HashSet(entries.size());
        for (Iterator iterator = entries.values().iterator(); iterator.hasNext(); ((Set) (obj1)).add((RosterEntry)iterator.next())) { }
        break MISSING_BLOCK_LABEL_72;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        obj;
        JVM INSTR monitorexit ;
        return ((Set) (obj1));
    }

    public void getEntriesAndAddListener(RosterListener rosterlistener, RosterEntries rosterentries)
    {
        Objects.requireNonNull(rosterlistener, "listener must not be null");
        Objects.requireNonNull(rosterentries, "rosterEntries must not be null");
        synchronized (rosterListenersAndEntriesLock)
        {
            rosterentries.rosterEntires(entries.values());
            addRosterListener(rosterlistener);
        }
        return;
        rosterlistener;
        obj;
        JVM INSTR monitorexit ;
        throw rosterlistener;
    }

    public RosterEntry getEntry(String s)
    {
        if (s == null)
        {
            return null;
        } else
        {
            s = getMapKey(s);
            return (RosterEntry)entries.get(s);
        }
    }

    public int getEntryCount()
    {
        return getEntries().size();
    }

    public RosterGroup getGroup(String s)
    {
        return (RosterGroup)groups.get(s);
    }

    public int getGroupCount()
    {
        return groups.size();
    }

    public Collection getGroups()
    {
        return Collections.unmodifiableCollection(groups.values());
    }

    public Presence getPresence(String s)
    {
        Object obj = getMapKey(XmppStringUtils.parseBareJid(s));
        Map map = (Map)presenceMap.get(obj);
        if (map == null)
        {
            obj = new Presence(org.jivesoftware.smack.packet.Presence.Type.unavailable);
            ((Presence) (obj)).setFrom(s);
            return ((Presence) (obj));
        }
        obj = null;
        Presence presence1 = null;
        Iterator iterator = map.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Presence presence = (Presence)map.get((String)iterator.next());
            if (!presence.isAvailable())
            {
                presence1 = presence;
            } else
            if (obj == null || presence.getPriority() > ((Presence) (obj)).getPriority())
            {
                obj = presence;
            } else
            if (presence.getPriority() == ((Presence) (obj)).getPriority())
            {
                org.jivesoftware.smack.packet.Presence.Mode mode1 = presence.getMode();
                org.jivesoftware.smack.packet.Presence.Mode mode = mode1;
                if (mode1 == null)
                {
                    mode = org.jivesoftware.smack.packet.Presence.Mode.available;
                }
                org.jivesoftware.smack.packet.Presence.Mode mode2 = ((Presence) (obj)).getMode();
                mode1 = mode2;
                if (mode2 == null)
                {
                    mode1 = org.jivesoftware.smack.packet.Presence.Mode.available;
                }
                if (mode.compareTo(mode1) < 0)
                {
                    obj = presence;
                }
            }
        } while (true);
        if (obj == null)
        {
            if (presence1 != null)
            {
                return presence1.clone();
            } else
            {
                obj = new Presence(org.jivesoftware.smack.packet.Presence.Type.unavailable);
                ((Presence) (obj)).setFrom(s);
                return ((Presence) (obj));
            }
        } else
        {
            return ((Presence) (obj)).clone();
        }
    }

    public Presence getPresenceResource(String s)
    {
        Object obj1 = getMapKey(s);
        Object obj = XmppStringUtils.parseResource(s);
        obj1 = (Map)presenceMap.get(obj1);
        if (obj1 == null)
        {
            obj = new Presence(org.jivesoftware.smack.packet.Presence.Type.unavailable);
            ((Presence) (obj)).setFrom(s);
            return ((Presence) (obj));
        }
        obj = (Presence)((Map) (obj1)).get(obj);
        if (obj == null)
        {
            obj = new Presence(org.jivesoftware.smack.packet.Presence.Type.unavailable);
            ((Presence) (obj)).setFrom(s);
            return ((Presence) (obj));
        } else
        {
            return ((Presence) (obj)).clone();
        }
    }

    public List getPresences(String s)
    {
        Object obj = getMapKey(s);
        Map map = (Map)presenceMap.get(obj);
        if (map == null)
        {
            obj = new Presence(org.jivesoftware.smack.packet.Presence.Type.unavailable);
            ((Presence) (obj)).setFrom(s);
            return Arrays.asList(new Presence[] {
                obj
            });
        }
        ArrayList arraylist = new ArrayList();
        obj = null;
        for (Iterator iterator = map.values().iterator(); iterator.hasNext();)
        {
            Presence presence1 = (Presence)iterator.next();
            if (presence1.isAvailable())
            {
                arraylist.add(presence1.clone());
            } else
            {
                obj = presence1;
            }
        }

        if (!arraylist.isEmpty())
        {
            return arraylist;
        }
        if (obj != null)
        {
            return Arrays.asList(new Presence[] {
                ((Presence) (obj)).clone()
            });
        } else
        {
            Presence presence = new Presence(org.jivesoftware.smack.packet.Presence.Type.unavailable);
            presence.setFrom(s);
            return Arrays.asList(new Presence[] {
                presence
            });
        }
    }

    RosterStore getRosterStore()
    {
        return rosterStore;
    }

    public SubscriptionMode getSubscriptionMode()
    {
        return subscriptionMode;
    }

    public Set getUnfiledEntries()
    {
        return Collections.unmodifiableSet(unfiledEntries);
    }

    public int getUnfiledEntryCount()
    {
        return unfiledEntries.size();
    }

    public boolean isLoaded()
    {
        return loaded;
    }

    public boolean isRosterLoadedAtLogin()
    {
        return rosterLoadedAtLogin;
    }

    public boolean isRosterVersioningSupported()
    {
        return connection().hasFeature("ver", "urn:xmpp:features:rosterver");
    }

    public boolean isSubscribedToMyPresence(String s)
    {
        if (!connection().getServiceName().equals(s)) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        s = getEntry(s);
        if (s == null)
        {
            return false;
        }
        switch (_cls4..SwitchMap.org.jivesoftware.smack.roster.packet.RosterPacket.ItemType[s.getType().ordinal()])
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
            break;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void reload()
        throws org.jivesoftware.smack.SmackException.NotLoggedInException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        XMPPConnection xmppconnection = connection();
        if (!xmppconnection.isAuthenticated())
        {
            throw new org.jivesoftware.smack.SmackException.NotLoggedInException();
        }
        if (xmppconnection.isAnonymous())
        {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        }
        RosterPacket rosterpacket = new RosterPacket();
        if (rosterStore != null && isRosterVersioningSupported())
        {
            rosterpacket.setVersion(rosterStore.getRosterVersion());
        }
        xmppconnection.sendIqWithResponseCallback(rosterpacket, new RosterResultListener(), new ExceptionCallback() {

            final Roster this$0;

            public void processException(Exception exception)
            {
                Roster.LOGGER.log(Level.SEVERE, "Exception reloading roster", exception);
            }

            
            {
                this$0 = Roster.this;
                super();
            }
        });
    }

    public void reloadAndWait()
        throws org.jivesoftware.smack.SmackException.NotLoggedInException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        reload();
        waitUntilLoaded();
    }

    public void removeEntry(RosterEntry rosterentry)
        throws org.jivesoftware.smack.SmackException.NotLoggedInException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        XMPPConnection xmppconnection = connection();
        if (!xmppconnection.isAuthenticated())
        {
            throw new org.jivesoftware.smack.SmackException.NotLoggedInException();
        }
        if (xmppconnection.isAnonymous())
        {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        }
        if (!entries.containsKey(rosterentry.getUser()))
        {
            return;
        } else
        {
            RosterPacket rosterpacket = new RosterPacket();
            rosterpacket.setType(org.jivesoftware.smack.packet.IQ.Type.set);
            rosterentry = RosterEntry.toRosterItem(rosterentry);
            rosterentry.setItemType(org.jivesoftware.smack.roster.packet.RosterPacket.ItemType.remove);
            rosterpacket.addRosterItem(rosterentry);
            xmppconnection.createPacketCollectorAndSend(rosterpacket).nextResultOrThrow();
            return;
        }
    }

    public boolean removeRosterListener(RosterListener rosterlistener)
    {
        boolean flag;
        synchronized (rosterListenersAndEntriesLock)
        {
            flag = rosterListeners.remove(rosterlistener);
        }
        return flag;
        rosterlistener;
        obj;
        JVM INSTR monitorexit ;
        throw rosterlistener;
    }

    public boolean removeRosterLoadedListener(RosterLoadedListener rosterloadedlistener)
    {
        rosterloadedlistener;
        JVM INSTR monitorenter ;
        boolean flag = rosterLoadedListeners.remove(rosterloadedlistener);
        rosterloadedlistener;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        rosterloadedlistener;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setRosterLoadedAtLogin(boolean flag)
    {
        rosterLoadedAtLogin = flag;
    }

    public boolean setRosterStore(RosterStore rosterstore)
    {
        rosterStore = rosterstore;
        reload();
        return true;
        rosterstore;
_L2:
        LOGGER.log(Level.FINER, "Could not reload roster", rosterstore);
        return false;
        rosterstore;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void setSubscriptionMode(SubscriptionMode subscriptionmode)
    {
        subscriptionMode = subscriptionmode;
    }

    protected boolean waitUntilLoaded()
    {
        XMPPConnection xmppconnection = connection();
_L4:
        if (loaded) goto _L2; else goto _L1
_L1:
        long l;
        l = xmppconnection.getPacketReplyTimeout();
        System.currentTimeMillis();
        if (l > 0L) goto _L3; else goto _L2
_L2:
        return isLoaded();
_L3:
        this;
        JVM INSTR monitorenter ;
        if (!loaded)
        {
            wait(l);
        }
        this;
        JVM INSTR monitorexit ;
        System.currentTimeMillis();
          goto _L4
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        try
        {
            throw exception;
        }
        catch (InterruptedException interruptedexception)
        {
            LOGGER.log(Level.FINE, "interrupted", interruptedexception);
        }
          goto _L2
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                Roster.getInstanceFor(xmppconnection);
            }

        });
        PRESENCE_PACKET_FILTER = StanzaTypeFilter.PRESENCE;
        defaultSubscriptionMode = SubscriptionMode.accept_all;
    }









/*
    static boolean access$1702(Roster roster, boolean flag)
    {
        roster.loaded = flag;
        return flag;
    }

*/










}
