// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps;

import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.caps.packet.CapsExtension;

// Referenced classes of package org.jivesoftware.smackx.caps:
//            EntityCapsManager

class  extends AbstractConnectionListener
{

    final EntityCapsManager this$0;

    private void processCapsStreamFeatureIfAvailable(XMPPConnection xmppconnection)
    {
        CapsExtension capsextension = (CapsExtension)xmppconnection.getFeature("c", "http://jabber.org/protocol/caps");
        if (capsextension == null)
        {
            return;
        } else
        {
            EntityCapsManager.access$200(xmppconnection.getServiceName(), capsextension);
            return;
        }
    }

    public void authenticated(XMPPConnection xmppconnection, boolean flag)
    {
        processCapsStreamFeatureIfAvailable(xmppconnection);
        if (!flag)
        {
            EntityCapsManager.access$102(EntityCapsManager.this, false);
        }
    }

    public void connected(XMPPConnection xmppconnection)
    {
        processCapsStreamFeatureIfAvailable(xmppconnection);
    }

    ()
    {
        this$0 = EntityCapsManager.this;
        super();
    }
}
