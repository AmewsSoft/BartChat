// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

// Referenced classes of package org.jivesoftware.smack:
//            ConnectionCreationListener

public class XMPPConnectionRegistry
{

    private static final Set connectionEstablishedListeners = new CopyOnWriteArraySet();

    public XMPPConnectionRegistry()
    {
    }

    public static void addConnectionCreationListener(ConnectionCreationListener connectioncreationlistener)
    {
        connectionEstablishedListeners.add(connectioncreationlistener);
    }

    protected static Collection getConnectionCreationListeners()
    {
        return Collections.unmodifiableCollection(connectionEstablishedListeners);
    }

    public static void removeConnectionCreationListener(ConnectionCreationListener connectioncreationlistener)
    {
        connectionEstablishedListeners.remove(connectioncreationlistener);
    }

}
