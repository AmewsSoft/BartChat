// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;


// Referenced classes of package org.jivesoftware.smack:
//            XMPPConnection

public interface ConnectionListener
{

    public abstract void authenticated(XMPPConnection xmppconnection, boolean flag);

    public abstract void connected(XMPPConnection xmppconnection);

    public abstract void connectionClosed();

    public abstract void connectionClosedOnError(Exception exception);

    public abstract void reconnectingIn(int i);

    public abstract void reconnectionFailed(Exception exception);

    public abstract void reconnectionSuccessful();
}
