// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;


// Referenced classes of package org.jivesoftware.smack:
//            AbstractConnectionListener

public abstract class AbstractConnectionClosedListener extends AbstractConnectionListener
{

    public AbstractConnectionClosedListener()
    {
    }

    public final void connectionClosed()
    {
        connectionTerminated();
    }

    public final void connectionClosedOnError(Exception exception)
    {
        connectionTerminated();
    }

    public abstract void connectionTerminated();
}
