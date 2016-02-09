// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;


public class NumberUtil
{

    public NumberUtil()
    {
    }

    public static void checkIfInUInt32Range(long l)
    {
        if (l < 0L)
        {
            throw new IllegalArgumentException("unsigned 32-bit integers can't be negative");
        }
        if (l > 0xffffffffL)
        {
            throw new IllegalArgumentException("unsigned 32-bit integers can't be greater then 2^32 - 1");
        } else
        {
            return;
        }
    }
}
