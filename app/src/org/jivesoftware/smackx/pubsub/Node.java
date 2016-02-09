// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.delay.DelayInformationManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.pubsub.listener.ItemDeleteListener;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.pubsub.listener.NodeConfigListener;
import org.jivesoftware.smackx.pubsub.packet.PubSub;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;
import org.jivesoftware.smackx.pubsub.util.NodeUtils;
import org.jivesoftware.smackx.shim.packet.Header;
import org.jivesoftware.smackx.shim.packet.HeadersExtension;
import org.jivesoftware.smackx.xdata.Form;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, PubSubElementType, SubscriptionsExtension, EventElementType, 
//            AffiliationsExtension, SubscribeForm, OptionsExtension, FormNode, 
//            FormNodeType, PubSubManager, SubscribeExtension, Subscription, 
//            UnsubscribeExtension, ConfigureForm, EventElement, EmbeddedPacketExtension, 
//            ItemsExtension, RetractItem, ItemDeleteEvent, ItemPublishEvent, 
//            ConfigurationEvent

public abstract class Node
{
    class EventContentFilter
        implements StanzaFilter
    {

        private String firstElement;
        private String secondElement;
        final Node this$0;

        public boolean accept(Stanza stanza)
        {
            if (!(stanza instanceof Message))
            {
                return false;
            }
            stanza = (EventElement)stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns());
            if (stanza == null)
            {
                return false;
            }
            stanza = stanza.getEvent();
            if (stanza == null)
            {
                return false;
            }
            if (stanza.getElementName().equals(firstElement))
            {
                if (!stanza.getNode().equals(getId()))
                {
                    return false;
                }
                if (secondElement == null)
                {
                    return true;
                }
                if (stanza instanceof EmbeddedPacketExtension)
                {
                    stanza = ((EmbeddedPacketExtension)stanza).getExtensions();
                    if (stanza.size() > 0 && ((ExtensionElement)stanza.get(0)).getElementName().equals(secondElement))
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        EventContentFilter(String s)
        {
            this$0 = Node.this;
            super();
            firstElement = s;
        }

        EventContentFilter(String s, String s1)
        {
            this$0 = Node.this;
            super();
            firstElement = s;
            secondElement = s1;
        }
    }

    public class ItemDeleteTranslator
        implements StanzaListener
    {

        private ItemDeleteListener listener;
        final Node this$0;

        public void processPacket(Stanza stanza)
        {
            Object obj = (EventElement)stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns());
            if (((ExtensionElement)((EventElement) (obj)).getExtensions().get(0)).getElementName().equals(PubSubElementType.PURGE_EVENT.getElementName()))
            {
                listener.handlePurge();
                return;
            }
            obj = (ItemsExtension)((EventElement) (obj)).getEvent();
            Object obj1 = ((ItemsExtension) (obj)).getItems();
            ArrayList arraylist = new ArrayList(((Collection) (obj1)).size());
            for (obj1 = ((Collection) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); arraylist.add(((RetractItem)((Iterator) (obj1)).next()).getId())) { }
            stanza = new ItemDeleteEvent(((ItemsExtension) (obj)).getNode(), arraylist, Node.getSubscriptionIds(stanza));
            listener.handleDeletedItems(stanza);
        }

        public ItemDeleteTranslator(ItemDeleteListener itemdeletelistener)
        {
            this$0 = Node.this;
            super();
            listener = itemdeletelistener;
        }
    }

    public class ItemEventTranslator
        implements StanzaListener
    {

        private ItemEventListener listener;
        final Node this$0;

        public void processPacket(Stanza stanza)
        {
            ItemsExtension itemsextension = (ItemsExtension)((EventElement)stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns())).getEvent();
            stanza = new ItemPublishEvent(itemsextension.getNode(), itemsextension.getItems(), Node.getSubscriptionIds(stanza), DelayInformationManager.getDelayTimestamp(stanza));
            listener.handlePublishedItems(stanza);
        }

        public ItemEventTranslator(ItemEventListener itemeventlistener)
        {
            this$0 = Node.this;
            super();
            listener = itemeventlistener;
        }
    }

    public class NodeConfigTranslator
        implements StanzaListener
    {

        private NodeConfigListener listener;
        final Node this$0;

        public void processPacket(Stanza stanza)
        {
            stanza = (ConfigurationEvent)((EventElement)stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns())).getEvent();
            listener.handleNodeConfiguration(stanza);
        }

        public NodeConfigTranslator(NodeConfigListener nodeconfiglistener)
        {
            this$0 = Node.this;
            super();
            listener = nodeconfiglistener;
        }
    }


    protected XMPPConnection con;
    protected ConcurrentHashMap configEventToListenerMap;
    protected String id;
    protected ConcurrentHashMap itemDeleteToListenerMap;
    protected ConcurrentHashMap itemEventToListenerMap;
    protected String to;

    Node(XMPPConnection xmppconnection, String s)
    {
        itemEventToListenerMap = new ConcurrentHashMap();
        itemDeleteToListenerMap = new ConcurrentHashMap();
        configEventToListenerMap = new ConcurrentHashMap();
        con = xmppconnection;
        id = s;
    }

    private static List getSubscriptionIds(Stanza stanza)
    {
        Object obj = (HeadersExtension)stanza.getExtension("headers", "http://jabber.org/protocol/shim");
        stanza = null;
        if (obj != null)
        {
            ArrayList arraylist = new ArrayList(((HeadersExtension) (obj)).getHeaders().size());
            obj = ((HeadersExtension) (obj)).getHeaders().iterator();
            do
            {
                stanza = arraylist;
                if (!((Iterator) (obj)).hasNext())
                {
                    break;
                }
                arraylist.add(((Header)((Iterator) (obj)).next()).getValue());
            } while (true);
        }
        return stanza;
    }

    private List getSubscriptions(List list, Collection collection, PubSubNamespace pubsubnamespace)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        pubsubnamespace = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new NodeExtension(PubSubElementType.SUBSCRIPTIONS, getId()), pubsubnamespace);
        if (list != null)
        {
            for (list = list.iterator(); list.hasNext(); pubsubnamespace.addExtension((ExtensionElement)list.next())) { }
        }
        list = sendPubsubPacket(pubsubnamespace);
        if (collection != null)
        {
            collection.addAll(list.getExtensions());
        }
        return ((SubscriptionsExtension)list.getExtension(PubSubElementType.SUBSCRIPTIONS)).getSubscriptions();
    }

    public void addConfigurationListener(NodeConfigListener nodeconfiglistener)
    {
        NodeConfigTranslator nodeconfigtranslator = new NodeConfigTranslator(nodeconfiglistener);
        configEventToListenerMap.put(nodeconfiglistener, nodeconfigtranslator);
        con.addSyncStanzaListener(nodeconfigtranslator, new EventContentFilter(EventElementType.configuration.toString()));
    }

    public void addItemDeleteListener(ItemDeleteListener itemdeletelistener)
    {
        ItemDeleteTranslator itemdeletetranslator = new ItemDeleteTranslator(itemdeletelistener);
        itemDeleteToListenerMap.put(itemdeletelistener, itemdeletetranslator);
        itemdeletelistener = new EventContentFilter(EventElementType.items.toString(), "retract");
        EventContentFilter eventcontentfilter = new EventContentFilter(EventElementType.purge.toString());
        con.addSyncStanzaListener(itemdeletetranslator, new OrFilter(new StanzaFilter[] {
            itemdeletelistener, eventcontentfilter
        }));
    }

    public void addItemEventListener(ItemEventListener itemeventlistener)
    {
        ItemEventTranslator itemeventtranslator = new ItemEventTranslator(itemeventlistener);
        itemEventToListenerMap.put(itemeventlistener, itemeventtranslator);
        con.addSyncStanzaListener(itemeventtranslator, new EventContentFilter(EventElementType.items.toString(), "item"));
    }

    protected PubSub createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type type, ExtensionElement extensionelement)
    {
        return createPubsubPacket(type, extensionelement, null);
    }

    protected PubSub createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type type, ExtensionElement extensionelement, PubSubNamespace pubsubnamespace)
    {
        return PubSub.createPubsubPacket(to, type, extensionelement, pubsubnamespace);
    }

    public DiscoverInfo discoverInfo()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        DiscoverInfo discoverinfo = new DiscoverInfo();
        discoverinfo.setTo(to);
        discoverinfo.setNode(getId());
        return (DiscoverInfo)con.createPacketCollectorAndSend(discoverinfo).nextResultOrThrow();
    }

    public List getAffiliations()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getAffiliations(null, null);
    }

    public List getAffiliations(List list, Collection collection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        PubSub pubsub = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new NodeExtension(PubSubElementType.AFFILIATIONS, getId()));
        if (list != null)
        {
            for (list = list.iterator(); list.hasNext(); pubsub.addExtension((ExtensionElement)list.next())) { }
        }
        list = sendPubsubPacket(pubsub);
        if (collection != null)
        {
            collection.addAll(list.getExtensions());
        }
        return ((AffiliationsExtension)list.getExtension(PubSubElementType.AFFILIATIONS)).getAffiliations();
    }

    public String getId()
    {
        return id;
    }

    public ConfigureForm getNodeConfiguration()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return NodeUtils.getFormFromPacket(sendPubsubPacket(createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new NodeExtension(PubSubElementType.CONFIGURE_OWNER, getId()), PubSubNamespace.OWNER)), PubSubElementType.CONFIGURE_OWNER);
    }

    public SubscribeForm getSubscriptionOptions(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getSubscriptionOptions(s, null);
    }

    public SubscribeForm getSubscriptionOptions(String s, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return new SubscribeForm(((FormNode)sendPubsubPacket(createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new OptionsExtension(s, getId(), s1))).getExtension(PubSubElementType.OPTIONS)).getForm());
    }

    public List getSubscriptions()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getSubscriptions(null, null);
    }

    public List getSubscriptions(List list, Collection collection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getSubscriptions(list, collection, null);
    }

    public List getSubscriptionsAsOwner()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getSubscriptionsAsOwner(null, null);
    }

    public List getSubscriptionsAsOwner(List list, Collection collection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getSubscriptions(list, collection, PubSubNamespace.OWNER);
    }

    public void removeConfigurationListener(NodeConfigListener nodeconfiglistener)
    {
        nodeconfiglistener = (StanzaListener)configEventToListenerMap.remove(nodeconfiglistener);
        if (nodeconfiglistener != null)
        {
            con.removeSyncStanzaListener(nodeconfiglistener);
        }
    }

    public void removeItemDeleteListener(ItemDeleteListener itemdeletelistener)
    {
        itemdeletelistener = (StanzaListener)itemDeleteToListenerMap.remove(itemdeletelistener);
        if (itemdeletelistener != null)
        {
            con.removeSyncStanzaListener(itemdeletelistener);
        }
    }

    public void removeItemEventListener(ItemEventListener itemeventlistener)
    {
        itemeventlistener = (StanzaListener)itemEventToListenerMap.remove(itemeventlistener);
        if (itemeventlistener != null)
        {
            con.removeSyncStanzaListener(itemeventlistener);
        }
    }

    public void sendConfigurationForm(Form form)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        form = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new FormNode(FormNodeType.CONFIGURE_OWNER, getId(), form), PubSubNamespace.OWNER);
        con.createPacketCollectorAndSend(form).nextResultOrThrow();
    }

    protected PubSub sendPubsubPacket(PubSub pubsub)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return PubSubManager.sendPubsubPacket(con, pubsub);
    }

    void setTo(String s)
    {
        to = s;
    }

    public Subscription subscribe(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return (Subscription)sendPubsubPacket(createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new SubscribeExtension(s, getId()))).getExtension(PubSubElementType.SUBSCRIPTION);
    }

    public Subscription subscribe(String s, SubscribeForm subscribeform)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        s = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new SubscribeExtension(s, getId()));
        s.addExtension(new FormNode(FormNodeType.OPTIONS, subscribeform));
        return (Subscription)PubSubManager.sendPubsubPacket(con, s).getExtension(PubSubElementType.SUBSCRIPTION);
    }

    public String toString()
    {
        return (new StringBuilder()).append(super.toString()).append(" ").append(getClass().getName()).append(" id: ").append(id).toString();
    }

    public void unsubscribe(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        unsubscribe(s, null);
    }

    public void unsubscribe(String s, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        sendPubsubPacket(createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new UnsubscribeExtension(s, getId(), s1)));
    }

}
