// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.stringprep;

import java.io.IOException;

public class XmppStringprepException extends IOException
{

    private static final long serialVersionUID = 0x8a26e07f46cde470L;
    private final String causingString;

    public XmppStringprepException(String s, Exception exception)
    {
        super((new StringBuilder("XmppStringprepException caused by '")).append(s).append("': ").append(exception).toString());
        initCause(exception);
        causingString = s;
    }

    public XmppStringprepException(String s, String s1)
    {
        super(s1);
        causingString = s;
    }

    public String getCausingString()
    {
        return causingString;
    }
}
