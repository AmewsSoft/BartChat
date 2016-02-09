// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;


// Referenced classes of package org.jivesoftware.smack:
//            ConnectionCreationListener, ReconnectionManager, AbstractXMPPConnection, XMPPConnection

static final class 
    implements ConnectionCreationListener
{

    public void connectionCreated(XMPPConnection xmppconnection)
    {
        if (xmppconnection instanceof AbstractXMPPConnection)
        {
            ReconnectionManager.getInstanceFor((AbstractXMPPConnection)xmppconnection);
        }
    }

    ()
    {
    }
}
