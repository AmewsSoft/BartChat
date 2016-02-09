// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.lang.ref.WeakReference;
import org.jivesoftware.smack.util.Objects;

// Referenced classes of package org.jivesoftware.smack:
//            XMPPConnection

public abstract class Manager
{

    final WeakReference weakConnection;

    public Manager(XMPPConnection xmppconnection)
    {
        Objects.requireNonNull(xmppconnection, "XMPPConnection must not be null");
        weakConnection = new WeakReference(xmppconnection);
    }

    protected final XMPPConnection connection()
    {
        return (XMPPConnection)weakConnection.get();
    }
}
