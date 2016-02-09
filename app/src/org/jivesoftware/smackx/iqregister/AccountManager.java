// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqregister;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.StanzaIdFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.iqregister.packet.Registration;
import org.jxmpp.util.XmppStringUtils;

public class AccountManager extends Manager
{

    private static final Map INSTANCES = new WeakHashMap();
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/iqregister/AccountManager.getName());
    private static boolean allowSensitiveOperationOverInsecureConnectionDefault = false;
    private boolean accountCreationSupported;
    private boolean allowSensitiveOperationOverInsecureConnection;
    private Registration info;

    private AccountManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        allowSensitiveOperationOverInsecureConnection = allowSensitiveOperationOverInsecureConnectionDefault;
        info = null;
        accountCreationSupported = false;
    }

    private PacketCollector createPacketCollectorAndSend(IQ iq)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return connection().createPacketCollectorAndSend(new StanzaIdFilter(iq.getStanzaId()), iq);
    }

    public static AccountManager getInstance(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/iqregister/AccountManager;
        JVM INSTR monitorenter ;
        AccountManager accountmanager1 = (AccountManager)INSTANCES.get(xmppconnection);
        AccountManager accountmanager;
        accountmanager = accountmanager1;
        if (accountmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        accountmanager = new AccountManager(xmppconnection);
        INSTANCES.put(xmppconnection, accountmanager);
        org/jivesoftware/smackx/iqregister/AccountManager;
        JVM INSTR monitorexit ;
        return accountmanager;
        xmppconnection;
        throw xmppconnection;
    }

    private void getRegistrationInfo()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        this;
        JVM INSTR monitorenter ;
        Registration registration = new Registration();
        registration.setTo(connection().getServiceName());
        info = (Registration)createPacketCollectorAndSend(registration).nextResultOrThrow();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void sensitiveOperationOverInsecureConnectionDefault(boolean flag)
    {
        allowSensitiveOperationOverInsecureConnectionDefault = flag;
    }

    public void changePassword(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (!connection().isSecureConnection() && !allowSensitiveOperationOverInsecureConnection)
        {
            LOGGER.warning("Changing password over insecure connection. This will throw an exception in future versions of Smack if AccountManager.sensitiveOperationOverInsecureConnection(true) is not set");
        }
        HashMap hashmap = new HashMap();
        hashmap.put("username", XmppStringUtils.parseLocalpart(connection().getUser()));
        hashmap.put("password", s);
        s = new Registration(hashmap);
        s.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        s.setTo(connection().getServiceName());
        createPacketCollectorAndSend(s).nextResultOrThrow();
    }

    public void createAccount(String s, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        HashMap hashmap = new HashMap();
        for (Iterator iterator = getAccountAttributes().iterator(); iterator.hasNext(); hashmap.put((String)iterator.next(), "")) { }
        createAccount(s, s1, ((Map) (hashmap)));
    }

    public void createAccount(String s, String s1, Map map)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (!connection().isSecureConnection() && !allowSensitiveOperationOverInsecureConnection)
        {
            LOGGER.warning("Creating account over insecure connection. This will throw an exception in future versions of Smack if AccountManager.sensitiveOperationOverInsecureConnection(true) is not set");
        }
        map.put("username", s);
        map.put("password", s1);
        s = new Registration(map);
        s.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        s.setTo(connection().getServiceName());
        createPacketCollectorAndSend(s).nextResultOrThrow();
    }

    public void deleteAccount()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj = new HashMap();
        ((Map) (obj)).put("remove", "");
        obj = new Registration(((Map) (obj)));
        ((Registration) (obj)).setType(org.jivesoftware.smack.packet.IQ.Type.set);
        ((Registration) (obj)).setTo(connection().getServiceName());
        createPacketCollectorAndSend(((IQ) (obj))).nextResultOrThrow();
    }

    public String getAccountAttribute(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (info == null)
        {
            getRegistrationInfo();
        }
        return (String)info.getAttributes().get(s);
    }

    public Set getAccountAttributes()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (info == null)
        {
            getRegistrationInfo();
        }
        Map map = info.getAttributes();
        if (map != null)
        {
            return Collections.unmodifiableSet(map.keySet());
        } else
        {
            return Collections.emptySet();
        }
    }

    public String getAccountInstructions()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (info == null)
        {
            getRegistrationInfo();
        }
        return info.getInstructions();
    }

    public void sensitiveOperationOverInsecureConnection(boolean flag)
    {
        allowSensitiveOperationOverInsecureConnection = flag;
    }

    void setSupportsAccountCreation(boolean flag)
    {
        accountCreationSupported = flag;
    }

    public boolean supportsAccountCreation()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        boolean flag = true;
        if (accountCreationSupported)
        {
            return true;
        }
        if (info == null)
        {
            getRegistrationInfo();
            if (info.getType() == org.jivesoftware.smack.packet.IQ.Type.error)
            {
                flag = false;
            }
            accountCreationSupported = flag;
        }
        return accountCreationSupported;
    }

}
