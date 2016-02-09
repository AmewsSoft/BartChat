// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class SASLError extends Enum
{

    private static final SASLError $VALUES[];
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/sasl/SASLError.getName());
    public static final SASLError aborted;
    public static final SASLError account_disabled;
    public static final SASLError credentials_expired;
    public static final SASLError encryption_required;
    public static final SASLError incorrect_encoding;
    public static final SASLError invalid_authzid;
    public static final SASLError invalid_mechanism;
    public static final SASLError malformed_request;
    public static final SASLError mechanism_too_weak;
    public static final SASLError not_authorized;
    public static final SASLError temporary_auth_failure;

    private SASLError(String s, int i)
    {
        super(s, i);
    }

    public static SASLError fromString(String s)
    {
        s = s.replace('-', '_');
        SASLError saslerror;
        try
        {
            saslerror = valueOf(s);
        }
        catch (Exception exception)
        {
            LOGGER.log(Level.WARNING, (new StringBuilder()).append("Could not transform string '").append(s).append("' to SASLError").toString(), exception);
            return null;
        }
        return saslerror;
    }

    public static SASLError valueOf(String s)
    {
        return (SASLError)Enum.valueOf(org/jivesoftware/smack/sasl/SASLError, s);
    }

    public static SASLError[] values()
    {
        return (SASLError[])$VALUES.clone();
    }

    public String toString()
    {
        return name().replace('_', '-');
    }

    static 
    {
        aborted = new SASLError("aborted", 0);
        account_disabled = new SASLError("account_disabled", 1);
        credentials_expired = new SASLError("credentials_expired", 2);
        encryption_required = new SASLError("encryption_required", 3);
        incorrect_encoding = new SASLError("incorrect_encoding", 4);
        invalid_authzid = new SASLError("invalid_authzid", 5);
        invalid_mechanism = new SASLError("invalid_mechanism", 6);
        malformed_request = new SASLError("malformed_request", 7);
        mechanism_too_weak = new SASLError("mechanism_too_weak", 8);
        not_authorized = new SASLError("not_authorized", 9);
        temporary_auth_failure = new SASLError("temporary_auth_failure", 10);
        $VALUES = (new SASLError[] {
            aborted, account_disabled, credentials_expired, encryption_required, incorrect_encoding, invalid_authzid, invalid_mechanism, malformed_request, mechanism_too_weak, not_authorized, 
            temporary_auth_failure
        });
    }
}
