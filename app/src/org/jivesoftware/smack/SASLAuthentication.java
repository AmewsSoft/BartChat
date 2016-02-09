// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.packet.Mechanisms;
import org.jivesoftware.smack.sasl.SASLAnonymous;
import org.jivesoftware.smack.sasl.SASLErrorException;
import org.jivesoftware.smack.sasl.SASLMechanism;

// Referenced classes of package org.jivesoftware.smack:
//            SmackException, AbstractXMPPConnection

public class SASLAuthentication
{

    private static final Set BLACKLISTED_MECHANISMS = new HashSet();
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/SASLAuthentication.getName());
    private static final List REGISTERED_MECHANISMS = new ArrayList();
    private boolean authenticationSuccessful;
    private final AbstractXMPPConnection connection;
    private SASLMechanism currentMechanism;
    private Exception saslException;

    SASLAuthentication(AbstractXMPPConnection abstractxmppconnection)
    {
        currentMechanism = null;
        connection = abstractxmppconnection;
        init();
    }

    public static boolean blacklistSASLMechanism(String s)
    {
        boolean flag;
        synchronized (BLACKLISTED_MECHANISMS)
        {
            flag = BLACKLISTED_MECHANISMS.add(s);
        }
        return flag;
        s;
        set;
        JVM INSTR monitorexit ;
        throw s;
    }

    public static Set getBlacklistedSASLMechanisms()
    {
        HashSet hashset;
        synchronized (BLACKLISTED_MECHANISMS)
        {
            hashset = new HashSet(BLACKLISTED_MECHANISMS);
        }
        return hashset;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static Map getRegisterdSASLMechanisms()
    {
        Object obj = new HashMap();
        List list = REGISTERED_MECHANISMS;
        list;
        JVM INSTR monitorenter ;
        SASLMechanism saslmechanism;
        for (Iterator iterator = REGISTERED_MECHANISMS.iterator(); iterator.hasNext(); ((Map) (obj)).put(saslmechanism.getClass().getName(), saslmechanism.getName()))
        {
            saslmechanism = (SASLMechanism)iterator.next();
        }

        break MISSING_BLOCK_LABEL_68;
        obj;
        list;
        JVM INSTR monitorexit ;
        throw obj;
        list;
        JVM INSTR monitorexit ;
        return ((Map) (obj));
    }

    private void maybeThrowException()
        throws SmackException, SASLErrorException
    {
        if (saslException != null)
        {
            if (saslException instanceof SmackException)
            {
                throw (SmackException)saslException;
            }
            if (saslException instanceof SASLErrorException)
            {
                throw (SASLErrorException)saslException;
            } else
            {
                throw new IllegalStateException("Unexpected exception type", saslException);
            }
        } else
        {
            return;
        }
    }

    public static void registerSASLMechanism(SASLMechanism saslmechanism)
    {
        synchronized (REGISTERED_MECHANISMS)
        {
            REGISTERED_MECHANISMS.add(saslmechanism);
            Collections.sort(REGISTERED_MECHANISMS);
        }
        return;
        saslmechanism;
        list;
        JVM INSTR monitorexit ;
        throw saslmechanism;
    }

    private SASLMechanism selectMechanism()
    {
        Object obj1;
        Iterator iterator;
        obj1 = null;
        iterator = REGISTERED_MECHANISMS.iterator();
_L2:
        SASLMechanism saslmechanism;
        String s;
        obj = obj1;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        saslmechanism = (SASLMechanism)iterator.next();
        s = saslmechanism.getName();
        synchronized (BLACKLISTED_MECHANISMS)
        {
            if (!BLACKLISTED_MECHANISMS.contains(s))
            {
                break MISSING_BLOCK_LABEL_69;
            }
        }
        continue; /* Loop/switch isn't completed */
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
        if (!serverMechanisms().contains(s))
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = saslmechanism.instanceForAuthentication(connection);
        break; /* Loop/switch isn't completed */
        if (true) goto _L2; else goto _L1
_L1:
        return ((SASLMechanism) (obj));
    }

    private List serverMechanisms()
    {
        Mechanisms mechanisms = (Mechanisms)connection.getFeature("mechanisms", "urn:ietf:params:xml:ns:xmpp-sasl");
        if (mechanisms == null)
        {
            LOGGER.warning("Server did not report any SASL mechanisms");
            return Collections.emptyList();
        } else
        {
            return mechanisms.getMechanisms();
        }
    }

    public static boolean unBlacklistSASLMechanism(String s)
    {
        boolean flag;
        synchronized (BLACKLISTED_MECHANISMS)
        {
            flag = BLACKLISTED_MECHANISMS.remove(s);
        }
        return flag;
        s;
        set;
        JVM INSTR monitorexit ;
        throw s;
    }

    public static boolean unregisterSASLMechanism(String s)
    {
label0:
        {
            synchronized (REGISTERED_MECHANISMS)
            {
                Iterator iterator = REGISTERED_MECHANISMS.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break label0;
                    }
                } while (!((SASLMechanism)iterator.next()).getClass().getName().equals(s));
                iterator.remove();
            }
            return true;
        }
        list;
        JVM INSTR monitorexit ;
        return false;
        s;
        list;
        JVM INSTR monitorexit ;
        throw s;
    }

    public void authenticate(String s, String s1, String s2)
        throws XMPPException.XMPPErrorException, SASLErrorException, IOException, SmackException
    {
        s2 = selectMechanism();
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        currentMechanism = s2;
        this;
        JVM INSTR monitorenter ;
        currentMechanism.authenticate(s, connection.getHost(), connection.getServiceName(), s1);
        try
        {
            wait(connection.getPacketReplyTimeout());
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        this;
        JVM INSTR monitorexit ;
        maybeThrowException();
        if (!authenticationSuccessful)
        {
            throw SmackException.NoResponseException.newWith(connection);
        } else
        {
            return;
        }
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        throw new SmackException("SASL Authentication failed. No known authentication mechanisims.");
    }

    public void authenticate(String s, CallbackHandler callbackhandler)
        throws IOException, XMPPException.XMPPErrorException, SASLErrorException, SmackException
    {
        s = selectMechanism();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        currentMechanism = s;
        this;
        JVM INSTR monitorenter ;
        currentMechanism.authenticate(connection.getHost(), connection.getServiceName(), callbackhandler);
        try
        {
            wait(connection.getPacketReplyTimeout());
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        this;
        JVM INSTR monitorexit ;
        maybeThrowException();
        if (!authenticationSuccessful)
        {
            throw SmackException.NoResponseException.newWith(connection);
        } else
        {
            return;
        }
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        throw new SmackException("SASL Authentication failed. No known authentication mechanisims.");
    }

    public void authenticateAnonymously()
        throws SASLErrorException, SmackException, XMPPException.XMPPErrorException
    {
        currentMechanism = (new SASLAnonymous()).instanceForAuthentication(connection);
        this;
        JVM INSTR monitorenter ;
        currentMechanism.authenticate(null, null, null, "");
        try
        {
            wait(connection.getPacketReplyTimeout());
        }
        catch (InterruptedException interruptedexception) { }
        this;
        JVM INSTR monitorexit ;
        maybeThrowException();
        Exception exception;
        if (!authenticationSuccessful)
        {
            throw SmackException.NoResponseException.newWith(connection);
        } else
        {
            return;
        }
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void authenticated(org.jivesoftware.smack.sasl.packet.SaslStreamElements.Success success)
        throws SmackException
    {
        if (success.getData() != null)
        {
            challengeReceived(success.getData(), true);
        }
        currentMechanism.checkIfSuccessfulOrThrow();
        authenticationSuccessful = true;
        this;
        JVM INSTR monitorenter ;
        notify();
        this;
        JVM INSTR monitorexit ;
        return;
        success;
        this;
        JVM INSTR monitorexit ;
        throw success;
    }

    public void authenticationFailed(Exception exception)
    {
        saslException = exception;
        this;
        JVM INSTR monitorenter ;
        notify();
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void authenticationFailed(org.jivesoftware.smack.sasl.packet.SaslStreamElements.SASLFailure saslfailure)
    {
        authenticationFailed(((Exception) (new SASLErrorException(currentMechanism.getName(), saslfailure))));
    }

    public boolean authenticationSuccessful()
    {
        return authenticationSuccessful;
    }

    public void challengeReceived(String s)
        throws SmackException
    {
        challengeReceived(s, false);
    }

    public void challengeReceived(String s, boolean flag)
        throws SmackException
    {
        try
        {
            currentMechanism.challengeReceived(s, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            authenticationFailed(s);
        }
        throw s;
    }

    public boolean hasAnonymousAuthentication()
    {
        return serverMechanisms().contains("ANONYMOUS");
    }

    public boolean hasNonAnonymousAuthentication()
    {
        return !serverMechanisms().isEmpty() && (serverMechanisms().size() != 1 || !hasAnonymousAuthentication());
    }

    protected void init()
    {
        authenticationSuccessful = false;
        saslException = null;
    }

}
