// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.parsing;


public class UnparsablePacket
{

    private final CharSequence content;
    private final Exception e;

    public UnparsablePacket(CharSequence charsequence, Exception exception)
    {
        content = charsequence;
        e = exception;
    }

    public CharSequence getContent()
    {
        return content;
    }

    public Exception getParsingException()
    {
        return e;
    }
}
