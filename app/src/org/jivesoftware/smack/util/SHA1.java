// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Referenced classes of package org.jivesoftware.smack.util:
//            StringUtils

public class SHA1
{

    private static MessageDigest SHA1_DIGEST;

    public SHA1()
    {
    }

    public static byte[] bytes(String s)
    {
        return bytes(StringUtils.toBytes(s));
    }

    public static byte[] bytes(byte abyte0[])
    {
        org/jivesoftware/smack/util/SHA1;
        JVM INSTR monitorenter ;
        SHA1_DIGEST.update(abyte0);
        abyte0 = SHA1_DIGEST.digest();
        org/jivesoftware/smack/util/SHA1;
        JVM INSTR monitorexit ;
        return abyte0;
        abyte0;
        throw abyte0;
    }

    public static String hex(String s)
    {
        return hex(StringUtils.toBytes(s));
    }

    public static String hex(byte abyte0[])
    {
        return StringUtils.encodeHex(bytes(abyte0));
    }

    static 
    {
        try
        {
            SHA1_DIGEST = MessageDigest.getInstance("SHA-1");
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new IllegalStateException(nosuchalgorithmexception);
        }
    }
}
