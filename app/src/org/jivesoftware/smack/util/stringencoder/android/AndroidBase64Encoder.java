// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.stringencoder.android;

import android.util.Base64;

public class AndroidBase64Encoder
    implements org.jivesoftware.smack.util.stringencoder.Base64.Encoder
{

    private static final int BASE64_ENCODER_FLAGS = 2;
    private static AndroidBase64Encoder instance = new AndroidBase64Encoder();

    private AndroidBase64Encoder()
    {
    }

    public static AndroidBase64Encoder getInstance()
    {
        return instance;
    }

    public byte[] decode(String s)
    {
        return Base64.decode(s, 0);
    }

    public byte[] decode(byte abyte0[], int i, int j)
    {
        return Base64.decode(abyte0, i, j, 0);
    }

    public byte[] encode(byte abyte0[], int i, int j)
    {
        return Base64.encode(abyte0, i, j, 2);
    }

    public String encodeToString(byte abyte0[], int i, int j)
    {
        return Base64.encodeToString(abyte0, i, j, 2);
    }

}
