// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.caps.packet.CapsExtension;

// Referenced classes of package org.jivesoftware.smackx.caps:
//            EntityCapsManager

class this._cls0
    implements StanzaListener
{

    final EntityCapsManager this$0;

    public void processPacket(Stanza stanza)
    {
        if (!entityCapsEnabled())
        {
            return;
        } else
        {
            CapsExtension capsextension = CapsExtension.from(stanza);
            EntityCapsManager.access$200(stanza.getFrom(), capsextension);
            return;
        }
    }

    ()
    {
        this$0 = EntityCapsManager.this;
        super();
    }
}
