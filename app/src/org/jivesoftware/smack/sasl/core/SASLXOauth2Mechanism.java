// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl.core;

import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.util.stringencoder.Base64;

public class SASLXOauth2Mechanism extends SASLMechanism
{

    public static final String NAME = "X-OAUTH2";

    public SASLXOauth2Mechanism()
    {
    }

    protected void authenticateInternal(CallbackHandler callbackhandler)
        throws SmackException
    {
        throw new UnsupportedOperationException("CallbackHandler not (yet) supported");
    }

    public void checkIfSuccessfulOrThrow()
        throws SmackException
    {
    }

    protected byte[] getAuthenticationText()
        throws SmackException
    {
        return Base64.encode(toBytes((new StringBuilder()).append('\0').append(authenticationId).append('\0').append(password).toString()));
    }

    public String getName()
    {
        return "X-OAUTH2";
    }

    public int getPriority()
    {
        return 410;
    }

    public volatile SASLMechanism newInstance()
    {
        return newInstance();
    }

    public SASLXOauth2Mechanism newInstance()
    {
        return new SASLXOauth2Mechanism();
    }
}
