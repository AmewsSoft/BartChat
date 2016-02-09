// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.stringencoder.android;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import org.jivesoftware.smack.util.stringencoder.StringEncoder;

public class AndroidBase64UrlSafeEncoder
    implements StringEncoder
{

    private static final int BASE64_ENCODER_FLAGS = 10;
    private static AndroidBase64UrlSafeEncoder instance = new AndroidBase64UrlSafeEncoder();

    private AndroidBase64UrlSafeEncoder()
    {
    }

    public static AndroidBase64UrlSafeEncoder getInstance()
    {
        return instance;
    }

    public String decode(String s)
    {
        s = Base64.decode(s, 10);
        try
        {
            s = new String(s, "UTF-8");
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new IllegalStateException("UTF-8 not supported", s);
        }
        return s;
    }

    public String encode(String s)
    {
        try
        {
            s = Base64.encodeToString(s.getBytes("UTF-8"), 10);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new IllegalStateException("UTF-8 not supported", s);
        }
        return s;
    }

}
