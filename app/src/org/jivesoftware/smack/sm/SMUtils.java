// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm;

import java.math.BigInteger;

public class SMUtils
{

    private static long MASK_32_BIT;

    public SMUtils()
    {
    }

    public static long calculateDelta(long l, long l1)
    {
        return l - l1 & MASK_32_BIT;
    }

    public static long incrementHeight(long l)
    {
        return MASK_32_BIT & l + 1L;
    }

    static 
    {
        MASK_32_BIT = BigInteger.ONE.shiftLeft(32).subtract(BigInteger.ONE).longValue();
    }
}
