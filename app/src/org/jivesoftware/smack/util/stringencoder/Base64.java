// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.stringencoder;

import java.io.UnsupportedEncodingException;
import org.jivesoftware.smack.util.Objects;

public class Base64
{
    public static interface Encoder
    {

        public abstract byte[] decode(String s);

        public abstract byte[] decode(byte abyte0[], int i, int j);

        public abstract byte[] encode(byte abyte0[], int i, int j);

        public abstract String encodeToString(byte abyte0[], int i, int j);
    }


    private static Encoder base64encoder;

    public Base64()
    {
    }

    public static final byte[] decode(String s)
    {
        return base64encoder.decode(s);
    }

    public static final byte[] decode(byte abyte0[])
    {
        return base64encoder.decode(abyte0, 0, abyte0.length);
    }

    public static final byte[] decode(byte abyte0[], int i, int j)
    {
        return base64encoder.decode(abyte0, i, j);
    }

    public static final String decodeToString(String s)
    {
        s = decode(s);
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

    public static final String decodeToString(byte abyte0[], int i, int j)
    {
        abyte0 = decode(abyte0, i, j);
        try
        {
            abyte0 = new String(abyte0, "UTF-8");
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new IllegalStateException("UTF-8 not supported", abyte0);
        }
        return abyte0;
    }

    public static final String encode(String s)
    {
        try
        {
            s = encodeToString(s.getBytes("UTF-8"));
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new IllegalStateException("UTF-8 not supported", s);
        }
        return s;
    }

    public static final byte[] encode(byte abyte0[])
    {
        return encode(abyte0, 0, abyte0.length);
    }

    public static final byte[] encode(byte abyte0[], int i, int j)
    {
        return base64encoder.encode(abyte0, i, j);
    }

    public static final String encodeToString(byte abyte0[])
    {
        abyte0 = encode(abyte0);
        try
        {
            abyte0 = new String(abyte0, "US-ASCII");
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new AssertionError(abyte0);
        }
        return abyte0;
    }

    public static final String encodeToString(byte abyte0[], int i, int j)
    {
        abyte0 = encode(abyte0, i, j);
        try
        {
            abyte0 = new String(abyte0, "US-ASCII");
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new AssertionError(abyte0);
        }
        return abyte0;
    }

    public static void setEncoder(Encoder encoder)
    {
        Objects.requireNonNull(encoder, "encoder must no be null");
        base64encoder = encoder;
    }
}
