// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.stringencoder;

import org.jivesoftware.smack.util.Objects;

// Referenced classes of package org.jivesoftware.smack.util.stringencoder:
//            StringEncoder

public class Base64UrlSafeEncoder
{

    private static StringEncoder base64UrlSafeEncoder;

    public Base64UrlSafeEncoder()
    {
    }

    public static final String decode(String s)
    {
        return base64UrlSafeEncoder.decode(s);
    }

    public static final String encode(String s)
    {
        return base64UrlSafeEncoder.encode(s);
    }

    public static StringEncoder getStringEncoder()
    {
        return base64UrlSafeEncoder;
    }

    public static void setEncoder(StringEncoder stringencoder)
    {
        Objects.requireNonNull(stringencoder, "encoder must no be null");
        base64UrlSafeEncoder = stringencoder;
    }
}
