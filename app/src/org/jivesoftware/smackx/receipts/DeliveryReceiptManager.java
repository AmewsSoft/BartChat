// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.receipts;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

// Referenced classes of package org.jivesoftware.smackx.receipts:
//            DeliveryReceiptRequest, DeliveryReceipt, ReceiptReceivedListener

public class DeliveryReceiptManager extends Manager
{
    public static final class AutoReceiptMode extends Enum
    {

        private static final AutoReceiptMode $VALUES[];
        public static final AutoReceiptMode always;
        public static final AutoReceiptMode disabled;
        public static final AutoReceiptMode ifIsSubscribed;

        public static AutoReceiptMode valueOf(String s)
        {
            return (AutoReceiptMode)Enum.valueOf(org/jivesoftware/smackx/receipts/DeliveryReceiptManager$AutoReceiptMode, s);
        }

        public static AutoReceiptMode[] values()
        {
            return (AutoReceiptMode[])$VALUES.clone();
        }

        static 
        {
            disabled = new AutoReceiptMode("disabled", 0);
            ifIsSubscribed = new AutoReceiptMode("ifIsSubscribed", 1);
            always = new AutoReceiptMode("always", 2);
            $VALUES = (new AutoReceiptMode[] {
                disabled, ifIsSubscribed, always
            });
        }

        private AutoReceiptMode(String s, int i)
        {
            super(s, i);
        }
    }


    private static final StanzaListener AUTO_ADD_DELIVERY_RECEIPT_REQUESTS_LISTENER = new StanzaListener() {

        public void processPacket(Stanza stanza)
            throws org.jivesoftware.smack.SmackException.NotConnectedException
        {
            DeliveryReceiptRequest.addTo((Message)stanza);
        }

    };
    private static final StanzaFilter MESSAGES_WITH_DELIVERY_RECEIPT;
    private static final StanzaFilter MESSAGES_WITH_DEVLIERY_RECEIPT_REQUEST;
    private static AutoReceiptMode defaultAutoReceiptMode;
    private static Map instances = new WeakHashMap();
    private AutoReceiptMode autoReceiptMode;
    private final Set receiptReceivedListeners = new CopyOnWriteArraySet();

    private DeliveryReceiptManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        autoReceiptMode = defaultAutoReceiptMode;
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("urn:xmpp:receipts");
        xmppconnection.addAsyncStanzaListener(new StanzaListener() {

            final DeliveryReceiptManager this$0;

            public void processPacket(Stanza stanza)
                throws org.jivesoftware.smack.SmackException.NotConnectedException
            {
                DeliveryReceipt deliveryreceipt = DeliveryReceipt.from((Message)stanza);
                for (Iterator iterator = receiptReceivedListeners.iterator(); iterator.hasNext(); ((ReceiptReceivedListener)iterator.next()).onReceiptReceived(stanza.getFrom(), stanza.getTo(), deliveryreceipt.getId(), stanza)) { }
            }

            
            {
                this$0 = DeliveryReceiptManager.this;
                super();
            }
        }, MESSAGES_WITH_DELIVERY_RECEIPT);
        xmppconnection.addAsyncStanzaListener(new StanzaListener() {

            final DeliveryReceiptManager this$0;

            public void processPacket(Stanza stanza)
                throws org.jivesoftware.smack.SmackException.NotConnectedException
            {
                String s;
                XMPPConnection xmppconnection1;
                s = stanza.getFrom();
                xmppconnection1 = connection();
                static class _cls5
                {

                    static final int $SwitchMap$org$jivesoftware$smackx$receipts$DeliveryReceiptManager$AutoReceiptMode[];

                    static 
                    {
                        $SwitchMap$org$jivesoftware$smackx$receipts$DeliveryReceiptManager$AutoReceiptMode = new int[AutoReceiptMode.values().length];
                        try
                        {
                            $SwitchMap$org$jivesoftware$smackx$receipts$DeliveryReceiptManager$AutoReceiptMode[AutoReceiptMode.disabled.ordinal()] = 1;
                        }
                        catch (NoSuchFieldError nosuchfielderror2) { }
                        try
                        {
                            $SwitchMap$org$jivesoftware$smackx$receipts$DeliveryReceiptManager$AutoReceiptMode[AutoReceiptMode.ifIsSubscribed.ordinal()] = 2;
                        }
                        catch (NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$org$jivesoftware$smackx$receipts$DeliveryReceiptManager$AutoReceiptMode[AutoReceiptMode.always.ordinal()] = 3;
                        }
                        catch (NoSuchFieldError nosuchfielderror)
                        {
                            return;
                        }
                    }
                }

                _cls5..SwitchMap.org.jivesoftware.smackx.receipts.DeliveryReceiptManager.AutoReceiptMode[autoReceiptMode.ordinal()];
                JVM INSTR tableswitch 1 2: default 48
            //                           1 61
            //                           2 62;
                   goto _L1 _L2 _L3
_L1:
                xmppconnection1.sendStanza(DeliveryReceiptManager.receiptMessageFor((Message)stanza));
_L2:
                return;
_L3:
                if (!Roster.getInstanceFor(xmppconnection1).isSubscribedToMyPresence(s))
                {
                    return;
                }
                if (true) goto _L1; else goto _L4
_L4:
            }

            
            {
                this$0 = DeliveryReceiptManager.this;
                super();
            }
        }, MESSAGES_WITH_DEVLIERY_RECEIPT_REQUEST);
    }

    public static String addDeliveryReceiptRequest(Message message)
    {
        return DeliveryReceiptRequest.addTo(message);
    }

    public static DeliveryReceiptManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/receipts/DeliveryReceiptManager;
        JVM INSTR monitorenter ;
        DeliveryReceiptManager deliveryreceiptmanager1 = (DeliveryReceiptManager)instances.get(xmppconnection);
        DeliveryReceiptManager deliveryreceiptmanager;
        deliveryreceiptmanager = deliveryreceiptmanager1;
        if (deliveryreceiptmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        deliveryreceiptmanager = new DeliveryReceiptManager(xmppconnection);
        instances.put(xmppconnection, deliveryreceiptmanager);
        org/jivesoftware/smackx/receipts/DeliveryReceiptManager;
        JVM INSTR monitorexit ;
        return deliveryreceiptmanager;
        xmppconnection;
        throw xmppconnection;
    }

    public static boolean hasDeliveryReceiptRequest(Message message)
    {
        return DeliveryReceiptRequest.from(message) != null;
    }

    public static Message receiptMessageFor(Message message)
    {
        Message message1 = new Message(message.getFrom(), message.getType());
        message1.addExtension(new DeliveryReceipt(message.getStanzaId()));
        return message1;
    }

    public static void setDefaultAutoReceiptMode(AutoReceiptMode autoreceiptmode)
    {
        defaultAutoReceiptMode = autoreceiptmode;
    }

    public void addReceiptReceivedListener(ReceiptReceivedListener receiptreceivedlistener)
    {
        receiptReceivedListeners.add(receiptreceivedlistener);
    }

    public void autoAddDeliveryReceiptRequests()
    {
        connection().addPacketSendingListener(AUTO_ADD_DELIVERY_RECEIPT_REQUESTS_LISTENER, MessageTypeFilter.NORMAL_OR_CHAT_OR_HEADLINE);
    }

    public void dontAutoAddDeliveryReceiptRequests()
    {
        connection().removePacketSendingListener(AUTO_ADD_DELIVERY_RECEIPT_REQUESTS_LISTENER);
    }

    public AutoReceiptMode getAutoReceiptMode()
    {
        return autoReceiptMode;
    }

    public boolean isSupported(String s)
        throws SmackException, XMPPException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(s, "urn:xmpp:receipts");
    }

    public void removeReceiptReceivedListener(ReceiptReceivedListener receiptreceivedlistener)
    {
        receiptReceivedListeners.remove(receiptreceivedlistener);
    }

    public void setAutoReceiptMode(AutoReceiptMode autoreceiptmode)
    {
        autoReceiptMode = autoreceiptmode;
    }

    static 
    {
        MESSAGES_WITH_DEVLIERY_RECEIPT_REQUEST = new AndFilter(new StanzaFilter[] {
            StanzaTypeFilter.MESSAGE, new StanzaExtensionFilter(new DeliveryReceiptRequest())
        });
        MESSAGES_WITH_DELIVERY_RECEIPT = new AndFilter(new StanzaFilter[] {
            StanzaTypeFilter.MESSAGE, new StanzaExtensionFilter("received", "urn:xmpp:receipts")
        });
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                DeliveryReceiptManager.getInstanceFor(xmppconnection);
            }

        });
        defaultAutoReceiptMode = AutoReceiptMode.ifIsSubscribed;
    }



}
