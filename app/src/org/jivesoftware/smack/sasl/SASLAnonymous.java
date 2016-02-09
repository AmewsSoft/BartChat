// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl;

import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SmackException;

// Referenced classes of package org.jivesoftware.smack.sasl:
//            SASLMechanism

public class SASLAnonymous extends SASLMechanism
{

    public static final String NAME = "ANONYMOUS";

    public SASLAnonymous()
    {
    }

    protected void authenticateInternal(CallbackHandler callbackhandler)
        throws SmackException
    {
    }

    public void checkIfSuccessfulOrThrow()
        throws SmackException
    {
    }

    protected byte[] getAuthenticationText()
        throws SmackException
    {
        return null;
    }

    public String getName()
    {
        return "ANONYMOUS";
    }

    public int getPriority()
    {
        return 500;
    }

    public SASLAnonymous newInstance()
    {
        return new SASLAnonymous();
    }

    public volatile SASLMechanism newInstance()
    {
        return newInstance();
    }
}
