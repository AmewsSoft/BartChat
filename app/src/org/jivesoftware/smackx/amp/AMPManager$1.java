// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smackx.amp:
//            AMPManager

static final class Listener
    implements ConnectionCreationListener
{

    public void connectionCreated(XMPPConnection xmppconnection)
    {
        AMPManager.setServiceEnabled(xmppconnection, true);
    }

    Listener()
    {
    }
}
