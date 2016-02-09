// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.offline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.offline.packet.OfflineMessageInfo;
import org.jivesoftware.smackx.offline.packet.OfflineMessageRequest;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;

// Referenced classes of package org.jivesoftware.smackx.offline:
//            OfflineMessageHeader

public class OfflineMessageManager
{

    private static final StanzaFilter PACKET_FILTER;
    private static final String namespace = "http://jabber.org/protocol/offline";
    private final XMPPConnection connection;

    public OfflineMessageManager(XMPPConnection xmppconnection)
    {
        connection = xmppconnection;
    }

    public void deleteMessages()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        OfflineMessageRequest offlinemessagerequest = new OfflineMessageRequest();
        offlinemessagerequest.setPurge(true);
        connection.createPacketCollectorAndSend(offlinemessagerequest).nextResultOrThrow();
    }

    public void deleteMessages(List list)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        OfflineMessageRequest offlinemessagerequest = new OfflineMessageRequest();
        org.jivesoftware.smackx.offline.packet.OfflineMessageRequest.Item item;
        for (list = list.iterator(); list.hasNext(); offlinemessagerequest.addItem(item))
        {
            item = new org.jivesoftware.smackx.offline.packet.OfflineMessageRequest.Item((String)list.next());
            item.setAction("remove");
        }

        connection.createPacketCollectorAndSend(offlinemessagerequest).nextResultOrThrow();
    }

    public List getHeaders()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        ArrayList arraylist = new ArrayList();
        for (Iterator iterator = ServiceDiscoveryManager.getInstanceFor(connection).discoverItems(null, "http://jabber.org/protocol/offline").getItems().iterator(); iterator.hasNext(); arraylist.add(new OfflineMessageHeader((org.jivesoftware.smackx.disco.packet.DiscoverItems.Item)iterator.next()))) { }
        return arraylist;
    }

    public int getMessageCount()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        int i = 0;
        Form form = Form.getFormFrom(ServiceDiscoveryManager.getInstanceFor(connection).discoverInfo(null, "http://jabber.org/protocol/offline"));
        if (form != null)
        {
            i = Integer.parseInt((String)form.getField("number_of_messages").getValues().get(0));
        }
        return i;
    }

    public List getMessages()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj;
        Object obj1;
        obj = new OfflineMessageRequest();
        ((OfflineMessageRequest) (obj)).setFetch(true);
        obj = connection.createPacketCollectorAndSend(((org.jivesoftware.smack.packet.IQ) (obj)));
        obj1 = PacketCollector.newConfiguration().setStanzaFilter(PACKET_FILTER).setCollectorToReset(((PacketCollector) (obj)));
        obj1 = connection.createPacketCollector(((org.jivesoftware.smack.PacketCollector.Configuration) (obj1)));
        ((PacketCollector) (obj)).nextResultOrThrow();
        ((PacketCollector) (obj1)).cancel();
        obj = new ArrayList(((PacketCollector) (obj1)).getCollectedCount());
_L3:
        Message message = (Message)((PacketCollector) (obj1)).pollResult();
        if (message == null) goto _L2; else goto _L1
_L1:
        ((List) (obj)).add(message);
          goto _L3
        obj;
_L5:
        ((PacketCollector) (obj1)).cancel();
        throw obj;
_L2:
        ((PacketCollector) (obj1)).cancel();
        return ((List) (obj));
        obj;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public List getMessages(final List nodes)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        ArrayList arraylist;
        OfflineMessageRequest offlinemessagerequest;
        PacketCollector packetcollector;
        arraylist = new ArrayList();
        offlinemessagerequest = new OfflineMessageRequest();
        org.jivesoftware.smackx.offline.packet.OfflineMessageRequest.Item item;
        for (Iterator iterator = nodes.iterator(); iterator.hasNext(); offlinemessagerequest.addItem(item))
        {
            item = new org.jivesoftware.smackx.offline.packet.OfflineMessageRequest.Item((String)iterator.next());
            item.setAction("view");
        }

        nodes = new AndFilter(new StanzaFilter[] {
            PACKET_FILTER, new StanzaFilter() {

                final OfflineMessageManager this$0;
                final List val$nodes;

                public boolean accept(Stanza stanza)
                {
                    stanza = (OfflineMessageInfo)stanza.getExtension("offline", "http://jabber.org/protocol/offline");
                    return nodes.contains(stanza.getNode());
                }

            
            {
                this$0 = OfflineMessageManager.this;
                nodes = list;
                super();
            }
            }
        });
        packetcollector = connection.createPacketCollector(nodes);
        connection.createPacketCollectorAndSend(offlinemessagerequest).nextResultOrThrow();
        nodes = (Message)packetcollector.nextResult();
_L2:
        if (nodes == null)
        {
            break; /* Loop/switch isn't completed */
        }
        arraylist.add(nodes);
        nodes = (Message)packetcollector.nextResult();
        if (true) goto _L2; else goto _L1
_L1:
        packetcollector.cancel();
        return arraylist;
        nodes;
        packetcollector.cancel();
        throw nodes;
    }

    public boolean supportsFlexibleRetrieval()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection).serverSupportsFeature("http://jabber.org/protocol/offline");
    }

    static 
    {
        PACKET_FILTER = new AndFilter(new StanzaFilter[] {
            new StanzaExtensionFilter(new OfflineMessageInfo()), StanzaTypeFilter.MESSAGE
        });
    }
}
