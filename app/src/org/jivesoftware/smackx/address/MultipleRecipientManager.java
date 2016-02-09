// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.address;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.address.packet.MultipleAddresses;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smackx.address:
//            MultipleRecipientInfo

public class MultipleRecipientManager
{
    private static class PacketCopy extends Stanza
    {

        private CharSequence text;

        public CharSequence toXML()
        {
            return text;
        }

        public PacketCopy(CharSequence charsequence)
        {
            text = charsequence;
        }
    }


    public MultipleRecipientManager()
    {
    }

    private static String getMultipleRecipienServiceAddress(XMPPConnection xmppconnection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        xmppconnection = ServiceDiscoveryManager.getInstanceFor(xmppconnection).findServices("http://jabber.org/protocol/address", true, true);
        if (xmppconnection.size() > 0)
        {
            return (String)xmppconnection.get(0);
        } else
        {
            return null;
        }
    }

    public static MultipleRecipientInfo getMultipleRecipientInfo(Stanza stanza)
    {
        stanza = (MultipleAddresses)stanza.getExtension("addresses", "http://jabber.org/protocol/address");
        if (stanza == null)
        {
            return null;
        } else
        {
            return new MultipleRecipientInfo(stanza);
        }
    }

    public static void reply(XMPPConnection xmppconnection, Message message, Message message1)
        throws SmackException, org.jivesoftware.smack.XMPPException.XMPPErrorException
    {
        Object obj1 = getMultipleRecipientInfo(message);
        if (obj1 == null)
        {
            throw new SmackException("Original message does not contain multiple recipient info");
        }
        if (((MultipleRecipientInfo) (obj1)).shouldNotReply())
        {
            throw new SmackException("Original message should not be replied");
        }
        if (((MultipleRecipientInfo) (obj1)).getReplyRoom() != null)
        {
            throw new SmackException("Reply should be sent through a room");
        }
        if (message.getThread() != null)
        {
            message1.setThread(message.getThread());
        }
        Object obj = ((MultipleRecipientInfo) (obj1)).getReplyAddress();
        if (obj != null && ((org.jivesoftware.smackx.address.packet.MultipleAddresses.Address) (obj)).getJid() != null)
        {
            message1.setTo(((org.jivesoftware.smackx.address.packet.MultipleAddresses.Address) (obj)).getJid());
            xmppconnection.sendStanza(message1);
            return;
        }
        obj = new ArrayList(((MultipleRecipientInfo) (obj1)).getTOAddresses().size());
        ArrayList arraylist = new ArrayList(((MultipleRecipientInfo) (obj1)).getCCAddresses().size());
        for (Iterator iterator = ((MultipleRecipientInfo) (obj1)).getTOAddresses().iterator(); iterator.hasNext(); ((List) (obj)).add(((org.jivesoftware.smackx.address.packet.MultipleAddresses.Address)iterator.next()).getJid())) { }
        for (obj1 = ((MultipleRecipientInfo) (obj1)).getCCAddresses().iterator(); ((Iterator) (obj1)).hasNext(); arraylist.add(((org.jivesoftware.smackx.address.packet.MultipleAddresses.Address)((Iterator) (obj1)).next()).getJid())) { }
        if (!((List) (obj)).contains(message.getFrom()) && !arraylist.contains(message.getFrom()))
        {
            ((List) (obj)).add(message.getFrom());
        }
        message = xmppconnection.getUser();
        if (!((List) (obj)).remove(message) && !arraylist.remove(message))
        {
            message = XmppStringUtils.parseBareJid(message);
            ((List) (obj)).remove(message);
            arraylist.remove(message);
        }
        send(xmppconnection, message1, ((Collection) (obj)), arraylist, null, null, null, false);
    }

    public static void send(XMPPConnection xmppconnection, Stanza stanza, Collection collection, Collection collection1, Collection collection2)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.FeatureNotSupportedException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        send(xmppconnection, stanza, collection, collection1, collection2, null, null, false);
    }

    public static void send(XMPPConnection xmppconnection, Stanza stanza, Collection collection, Collection collection1, Collection collection2, String s, String s1, boolean flag)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.FeatureNotSupportedException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (collection != null && collection.size() == 1 && (collection1 == null || collection1.isEmpty()) && (collection2 == null || collection2.isEmpty()) && !flag && StringUtils.isNullOrEmpty(s) && StringUtils.isNullOrEmpty(s1))
        {
            stanza.setTo((String)collection.iterator().next());
            xmppconnection.sendStanza(stanza);
            return;
        }
        String s2 = getMultipleRecipienServiceAddress(xmppconnection);
        if (s2 != null)
        {
            sendThroughService(xmppconnection, stanza, collection, collection1, collection2, s, s1, flag, s2);
            return;
        }
        if (flag || s != null && s.trim().length() > 0 || s1 != null && s1.trim().length() > 0)
        {
            throw new org.jivesoftware.smack.SmackException.FeatureNotSupportedException("Extended Stanza Addressing");
        } else
        {
            sendToIndividualRecipients(xmppconnection, stanza, collection, collection1, collection2);
            return;
        }
    }

    private static void sendThroughService(XMPPConnection xmppconnection, Stanza stanza, Collection collection, Collection collection1, Collection collection2, String s, String s1, boolean flag, 
            String s2)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        MultipleAddresses multipleaddresses;
        multipleaddresses = new MultipleAddresses();
        if (collection != null)
        {
            String s3;
            for (collection = collection.iterator(); collection.hasNext(); multipleaddresses.addAddress(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.to, s3, null, null, false, null))
            {
                s3 = (String)collection.next();
            }

        }
        if (collection1 != null)
        {
            for (collection = collection1.iterator(); collection.hasNext(); multipleaddresses.addAddress(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.to, collection1, null, null, false, null))
            {
                collection1 = (String)collection.next();
            }

        }
        if (collection2 != null)
        {
            for (collection = collection2.iterator(); collection.hasNext(); multipleaddresses.addAddress(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.bcc, collection1, null, null, false, null))
            {
                collection1 = (String)collection.next();
            }

        }
        if (!flag) goto _L2; else goto _L1
_L1:
        multipleaddresses.setNoReply();
_L4:
        stanza.setTo(s2);
        stanza.addExtension(multipleaddresses);
        xmppconnection.sendStanza(stanza);
        return;
_L2:
        if (s != null && s.trim().length() > 0)
        {
            multipleaddresses.addAddress(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.replyto, s, null, null, false, null);
        }
        if (s1 != null && s1.trim().length() > 0)
        {
            multipleaddresses.addAddress(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.replyroom, s1, null, null, false, null);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static void sendToIndividualRecipients(XMPPConnection xmppconnection, Stanza stanza, Collection collection, Collection collection1, Collection collection2)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (collection != null)
        {
            for (collection = collection.iterator(); collection.hasNext(); xmppconnection.sendStanza(new PacketCopy(stanza.toXML())))
            {
                stanza.setTo((String)collection.next());
            }

        }
        if (collection1 != null)
        {
            for (collection = collection1.iterator(); collection.hasNext(); xmppconnection.sendStanza(new PacketCopy(stanza.toXML())))
            {
                stanza.setTo((String)collection.next());
            }

        }
        if (collection2 != null)
        {
            for (collection = collection2.iterator(); collection.hasNext(); xmppconnection.sendStanza(new PacketCopy(stanza.toXML())))
            {
                stanza.setTo((String)collection.next());
            }

        }
    }
}
