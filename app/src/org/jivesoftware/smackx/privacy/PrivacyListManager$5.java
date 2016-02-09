// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.privacy;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.privacy.packet.Privacy;

// Referenced classes of package org.jivesoftware.smackx.privacy:
//            PrivacyListManager

class this._cls0
    implements StanzaListener
{

    final PrivacyListManager this$0;

    public void processPacket(Stanza stanza)
        throws org.jivesoftware.smack.nectedException
    {
        stanza = (Privacy)stanza;
        String s = stanza.getActiveName();
        if (s != null)
        {
            PrivacyListManager.access$202(PrivacyListManager.this, s);
        }
        stanza = stanza.getDefaultName();
        if (stanza != null)
        {
            PrivacyListManager.access$402(PrivacyListManager.this, stanza);
        }
    }

    eption()
    {
        this$0 = PrivacyListManager.this;
        super();
    }
}
