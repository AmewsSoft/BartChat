// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.EmptyResultIQ;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.packet.PubSub;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;
import org.jivesoftware.smackx.pubsub.util.NodeUtils;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, PubSubElementType, LeafNode, FormNode, 
//            FormNodeType, ConfigureNodeFields, NodeType, Node, 
//            CollectionNode, AffiliationsExtension, SubscriptionsExtension, ConfigureForm

public final class PubSubManager
{

    private XMPPConnection con;
    private Map nodeMap;
    private String to;

    public PubSubManager(XMPPConnection xmppconnection)
    {
        nodeMap = new ConcurrentHashMap();
        con = xmppconnection;
        to = (new StringBuilder()).append("pubsub.").append(xmppconnection.getServiceName()).toString();
    }

    public PubSubManager(XMPPConnection xmppconnection, String s)
    {
        nodeMap = new ConcurrentHashMap();
        con = xmppconnection;
        to = s;
    }

    static PubSub sendPubsubPacket(XMPPConnection xmppconnection, String s, org.jivesoftware.smack.packet.IQ.Type type, List list, PubSubNamespace pubsubnamespace)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        s = new PubSub(s, type, pubsubnamespace);
        for (type = list.iterator(); type.hasNext(); s.addExtension((ExtensionElement)type.next())) { }
        return sendPubsubPacket(xmppconnection, ((PubSub) (s)));
    }

    static PubSub sendPubsubPacket(XMPPConnection xmppconnection, PubSub pubsub)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        xmppconnection = (IQ)xmppconnection.createPacketCollectorAndSend(pubsub).nextResultOrThrow();
        if (xmppconnection instanceof EmptyResultIQ)
        {
            return null;
        } else
        {
            return (PubSub)xmppconnection;
        }
    }

    private PubSub sendPubsubPacket(org.jivesoftware.smack.packet.IQ.Type type, ExtensionElement extensionelement, PubSubNamespace pubsubnamespace)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return sendPubsubPacket(con, to, type, Collections.singletonList(extensionelement), pubsubnamespace);
    }

    public LeafNode createNode()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj = (NodeExtension)sendPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new NodeExtension(PubSubElementType.CREATE), null).getExtension("create", PubSubNamespace.BASIC.getXmlns());
        obj = new LeafNode(con, ((NodeExtension) (obj)).getNode());
        ((LeafNode) (obj)).setTo(to);
        nodeMap.put(((LeafNode) (obj)).getId(), obj);
        return ((LeafNode) (obj));
    }

    public LeafNode createNode(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return (LeafNode)createNode(s, null);
    }

    public Node createNode(String s, Form form)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        PubSub pubsub = PubSub.createPubsubPacket(to, org.jivesoftware.smack.packet.IQ.Type.set, new NodeExtension(PubSubElementType.CREATE, s), null);
        boolean flag1 = true;
        boolean flag = flag1;
        if (form != null)
        {
            pubsub.addExtension(new FormNode(FormNodeType.CONFIGURE, form));
            form = form.getField(ConfigureNodeFields.node_type.getFieldName());
            flag = flag1;
            if (form != null)
            {
                flag = ((String)form.getValues().get(0)).equals(NodeType.leaf.toString());
            }
        }
        sendPubsubPacket(con, pubsub);
        if (flag)
        {
            s = new LeafNode(con, s);
        } else
        {
            s = new CollectionNode(con, s);
        }
        s.setTo(to);
        nodeMap.put(s.getId(), s);
        return s;
    }

    public void deleteNode(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        sendPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.set, new NodeExtension(PubSubElementType.DELETE, s), PubSubElementType.DELETE.getNamespace());
        nodeMap.remove(s);
    }

    public DiscoverItems discoverNodes(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        DiscoverItems discoveritems = new DiscoverItems();
        if (s != null)
        {
            discoveritems.setNode(s);
        }
        discoveritems.setTo(to);
        return (DiscoverItems)con.createPacketCollectorAndSend(discoveritems).nextResultOrThrow();
    }

    public List getAffiliations()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ((AffiliationsExtension)sendPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new NodeExtension(PubSubElementType.AFFILIATIONS), null).getExtension(PubSubElementType.AFFILIATIONS)).getAffiliations();
    }

    public ConfigureForm getDefaultConfiguration()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return NodeUtils.getFormFromPacket(sendPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new NodeExtension(PubSubElementType.DEFAULT), PubSubElementType.DEFAULT.getNamespace()), PubSubElementType.DEFAULT);
    }

    public Node getNode(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Node node = (Node)nodeMap.get(s);
        Object obj = node;
        if (node == null)
        {
            obj = new DiscoverInfo();
            ((DiscoverInfo) (obj)).setTo(to);
            ((DiscoverInfo) (obj)).setNode(s);
            obj = (DiscoverInfo)con.createPacketCollectorAndSend(((IQ) (obj))).nextResultOrThrow();
            if (((DiscoverInfo) (obj)).hasIdentity("pubsub", "leaf"))
            {
                obj = new LeafNode(con, s);
            } else
            if (((DiscoverInfo) (obj)).hasIdentity("pubsub", "collection"))
            {
                obj = new CollectionNode(con, s);
            } else
            {
                throw new AssertionError((new StringBuilder()).append("PubSub service '").append(to).append("' returned disco info result for node '").append(s).append("', but it did not contain an Identity of type 'leaf' or 'collection' (and category 'pubsub'), which is not allowed according to XEP-60 5.3.").toString());
            }
            ((Node) (obj)).setTo(to);
            nodeMap.put(s, obj);
        }
        return ((Node) (obj));
    }

    public List getSubscriptions()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ((SubscriptionsExtension)sendPubsubPacket(org.jivesoftware.smack.packet.IQ.Type.get, new NodeExtension(PubSubElementType.SUBSCRIPTIONS), null).getExtension(PubSubElementType.SUBSCRIPTIONS.getElementName(), PubSubElementType.SUBSCRIPTIONS.getNamespace().getXmlns())).getSubscriptions();
    }

    public DiscoverInfo getSupportedFeatures()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(con).discoverInfo(to);
    }
}
