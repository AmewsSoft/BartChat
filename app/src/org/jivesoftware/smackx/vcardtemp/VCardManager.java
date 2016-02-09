// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.vcardtemp;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.packet.id.StanzaIdUtil;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;

public class VCardManager extends Manager
{

    public static final String ELEMENT = "vCard";
    private static final Map INSTANCES = new WeakHashMap();
    public static final String NAMESPACE = "vcard-temp";

    private VCardManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("vcard-temp");
    }

    public static VCardManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/vcardtemp/VCardManager;
        JVM INSTR monitorenter ;
        VCardManager vcardmanager1 = (VCardManager)INSTANCES.get(xmppconnection);
        VCardManager vcardmanager;
        vcardmanager = vcardmanager1;
        if (vcardmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        vcardmanager = new VCardManager(xmppconnection);
        INSTANCES.put(xmppconnection, vcardmanager);
        org/jivesoftware/smackx/vcardtemp/VCardManager;
        JVM INSTR monitorexit ;
        return vcardmanager;
        xmppconnection;
        throw xmppconnection;
    }

    public static boolean isSupported(String s, XMPPConnection xmppconnection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return getInstanceFor(xmppconnection).isSupported(s);
    }

    public boolean isSupported(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(s, "vcard-temp");
    }

    public VCard loadVCard()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return loadVCard(null);
    }

    public VCard loadVCard(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        VCard vcard = new VCard();
        vcard.setTo(s);
        return (VCard)connection().createPacketCollectorAndSend(vcard).nextResultOrThrow();
    }

    public void saveVCard(VCard vcard)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        vcard.setTo(null);
        vcard.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        vcard.setStanzaId(StanzaIdUtil.newStanzaId());
        connection().createPacketCollectorAndSend(vcard).nextResultOrThrow();
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                VCardManager.getInstanceFor(xmppconnection);
            }

        });
    }
}
