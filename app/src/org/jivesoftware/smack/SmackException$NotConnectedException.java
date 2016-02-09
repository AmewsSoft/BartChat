// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;


// Referenced classes of package org.jivesoftware.smack:
//            SmackException

public static class A extends SmackException
{

    private static final long serialVersionUID = 0x7fa5ca7107423695L;

    public A()
    {
        super("Client is not, or no longer, connected");
    }
}
