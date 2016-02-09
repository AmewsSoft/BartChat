// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.privacy;

import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smackx.privacy:
//            PrivacyListManager

class it> extends AbstractConnectionListener
{

    final PrivacyListManager this$0;

    public void authenticated(XMPPConnection xmppconnection, boolean flag)
    {
        if (flag)
        {
            return;
        } else
        {
            PrivacyListManager.access$202(PrivacyListManager.this, PrivacyListManager.access$402(PrivacyListManager.this, null));
            return;
        }
    }

    ()
    {
        this$0 = PrivacyListManager.this;
        super();
    }
}
