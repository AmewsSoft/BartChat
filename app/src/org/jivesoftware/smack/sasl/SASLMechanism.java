// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl;

import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.util.StringTransformer;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.stringencoder.Base64;

public abstract class SASLMechanism
    implements Comparable
{

    public static final String CRAMMD5 = "CRAM-MD5";
    public static final String DIGESTMD5 = "DIGEST-MD5";
    public static final String EXTERNAL = "EXTERNAL";
    public static final String GSSAPI = "GSSAPI";
    public static final String PLAIN = "PLAIN";
    private static StringTransformer saslPrepTransformer;
    protected String authenticationId;
    protected XMPPConnection connection;
    protected String host;
    protected String password;
    protected String serviceName;

    public SASLMechanism()
    {
    }

    private final void authenticate()
        throws SmackException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        byte abyte0[] = getAuthenticationText();
        String s;
        if (abyte0 != null)
        {
            s = Base64.encodeToString(abyte0);
        } else
        {
            s = "=";
        }
        connection.send(new org.jivesoftware.smack.sasl.packet.SaslStreamElements.AuthMechanism(getName(), s));
    }

    protected static String saslPrep(String s)
    {
        StringTransformer stringtransformer = saslPrepTransformer;
        String s1 = s;
        if (stringtransformer != null)
        {
            s1 = stringtransformer.transform(s);
        }
        return s1;
    }

    public static void setSaslPrepTransformer(StringTransformer stringtransformer)
    {
        saslPrepTransformer = stringtransformer;
    }

    protected static byte[] toBytes(String s)
    {
        return StringUtils.toBytes(s);
    }

    public final void authenticate(String s, String s1, String s2, String s3)
        throws SmackException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        authenticationId = s;
        host = s1;
        serviceName = s2;
        password = s3;
        authenticateInternal();
        authenticate();
    }

    public void authenticate(String s, String s1, CallbackHandler callbackhandler)
        throws SmackException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        host = s;
        serviceName = s1;
        authenticateInternal(callbackhandler);
        authenticate();
    }

    protected void authenticateInternal()
        throws SmackException
    {
    }

    protected abstract void authenticateInternal(CallbackHandler callbackhandler)
        throws SmackException;

    public final void challengeReceived(String s, boolean flag)
        throws SmackException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        s = evaluateChallenge(Base64.decode(s));
        if (flag)
        {
            return;
        }
        if (s == null)
        {
            s = new org.jivesoftware.smack.sasl.packet.SaslStreamElements.Response();
        } else
        {
            s = new org.jivesoftware.smack.sasl.packet.SaslStreamElements.Response(Base64.encodeToString(s));
        }
        connection.send(s);
    }

    public abstract void checkIfSuccessfulOrThrow()
        throws SmackException;

    public volatile int compareTo(Object obj)
    {
        return compareTo((SASLMechanism)obj);
    }

    public final int compareTo(SASLMechanism saslmechanism)
    {
        return getPriority() - saslmechanism.getPriority();
    }

    protected byte[] evaluateChallenge(byte abyte0[])
        throws SmackException
    {
        return null;
    }

    protected abstract byte[] getAuthenticationText()
        throws SmackException;

    public abstract String getName();

    public abstract int getPriority();

    public SASLMechanism instanceForAuthentication(XMPPConnection xmppconnection)
    {
        SASLMechanism saslmechanism = newInstance();
        saslmechanism.connection = xmppconnection;
        return saslmechanism;
    }

    protected abstract SASLMechanism newInstance();
}
