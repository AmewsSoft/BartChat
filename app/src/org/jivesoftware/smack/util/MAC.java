// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MAC
{

    public static final String HMACSHA1 = "HmacSHA1";
    private static Mac HMAC_SHA1;

    public MAC()
    {
    }

    public static byte[] hmacsha1(SecretKeySpec secretkeyspec, byte abyte0[])
        throws InvalidKeyException
    {
        org/jivesoftware/smack/util/MAC;
        JVM INSTR monitorenter ;
        HMAC_SHA1.init(secretkeyspec);
        secretkeyspec = HMAC_SHA1.doFinal(abyte0);
        org/jivesoftware/smack/util/MAC;
        JVM INSTR monitorexit ;
        return secretkeyspec;
        secretkeyspec;
        throw secretkeyspec;
    }

    public static byte[] hmacsha1(byte abyte0[], byte abyte1[])
        throws InvalidKeyException
    {
        return hmacsha1(new SecretKeySpec(abyte0, "HmacSHA1"), abyte1);
    }

    static 
    {
        try
        {
            HMAC_SHA1 = Mac.getInstance("HmacSHA1");
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new IllegalStateException(nosuchalgorithmexception);
        }
    }
}
