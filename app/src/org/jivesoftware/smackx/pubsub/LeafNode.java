// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.packet.PubSub;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            Node, PubSubElementType, ItemsExtension, NodeExtension, 
//            Item, GetItemsRequest, PublishItem

public class LeafNode extends Node
{

    LeafNode(XMPPConnection xmppconnection, String s)
    {
        super(xmppconnection, s);
    }

    private List getItems(PubSub pubsub)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getItems(pubsub, ((List) (null)));
    }

    private List getItems(PubSub pubsub, List list)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        pubsub = (PubSub)con.createPacketCollectorAndSend(pubsub).nextResultOrThrow();
        ItemsExtension itemsextension = (ItemsExtension)pubsub.getExtension(PubSubElementType.ITEMS);
        if (list != null)
        {
            list.addAll(pubsub.getExtensions());
        }
        return itemsextension.getItems();
    }

    public void deleteAllItems()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        PubSub pubsub = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new NodeExtension(PubSubElementType.PURGE_OWNER, getId()), PubSubElementType.PURGE_OWNER.getNamespace());
        con.createPacketCollectorAndSend(pubsub).nextResultOrThrow();
    }

    public void deleteItem(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        ArrayList arraylist = new ArrayList(1);
        arraylist.add(s);
        deleteItem(((Collection) (arraylist)));
    }

    public void deleteItem(Collection collection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        ArrayList arraylist = new ArrayList(collection.size());
        for (collection = collection.iterator(); collection.hasNext(); arraylist.add(new Item((String)collection.next()))) { }
        collection = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new ItemsExtension(ItemsExtension.ItemsElementType.retract, getId(), arraylist));
        con.createPacketCollectorAndSend(collection).nextResultOrThrow();
    }

    public DiscoverItems discoverItems()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        DiscoverItems discoveritems = new DiscoverItems();
        discoveritems.setTo(to);
        discoveritems.setNode(getId());
        return (DiscoverItems)con.createPacketCollectorAndSend(discoveritems).nextResultOrThrow();
    }

    public List getItems()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getItems((List)null, (List)null);
    }

    public List getItems(int i)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getItems(createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new GetItemsRequest(getId(), i)));
    }

    public List getItems(int i, String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getItems(createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new GetItemsRequest(getId(), s, i)));
    }

    public List getItems(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getItems(createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new GetItemsRequest(getId(), s)));
    }

    public List getItems(Collection collection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        ArrayList arraylist = new ArrayList(collection.size());
        for (collection = collection.iterator(); collection.hasNext(); arraylist.add(new Item((String)collection.next()))) { }
        return getItems(createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new ItemsExtension(ItemsExtension.ItemsElementType.items, getId(), arraylist)));
    }

    public List getItems(List list, List list1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        PubSub pubsub = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new GetItemsRequest(getId()));
        pubsub.addExtensions(list);
        return getItems(pubsub, list1);
    }

    public void publish()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        PubSub pubsub = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new NodeExtension(PubSubElementType.PUBLISH, getId()));
        con.sendStanza(pubsub);
    }

    public void publish(Collection collection)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        collection = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new PublishItem(getId(), collection));
        con.sendStanza(collection);
    }

    public void publish(Item item)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        ArrayList arraylist = new ArrayList(1);
        Item item1 = item;
        if (item == null)
        {
            item1 = new Item();
        }
        arraylist.add(item1);
        publish(((Collection) (arraylist)));
    }

    public void send()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        PubSub pubsub = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new NodeExtension(PubSubElementType.PUBLISH, getId()));
        con.createPacketCollectorAndSend(pubsub).nextResultOrThrow();
    }

    public void send(Collection collection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        collection = createPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new PublishItem(getId(), collection));
        con.createPacketCollectorAndSend(collection).nextResultOrThrow();
    }

    public void send(Item item)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        ArrayList arraylist = new ArrayList(1);
        Item item1 = item;
        if (item == null)
        {
            item1 = new Item();
        }
        arraylist.add(item1);
        send(((Collection) (arraylist)));
    }
}
