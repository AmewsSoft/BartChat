// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.time;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smackx.time:
//            EntityTimeManager

static final class 
    implements ConnectionCreationListener
{

    public void connectionCreated(XMPPConnection xmppconnection)
    {
        EntityTimeManager.getInstanceFor(xmppconnection);
    }

    ()
    {
    }
}
