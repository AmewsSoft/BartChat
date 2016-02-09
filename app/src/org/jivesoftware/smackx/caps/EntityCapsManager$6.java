// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.caps.packet.CapsExtension;

// Referenced classes of package org.jivesoftware.smackx.caps:
//            EntityCapsManager, CapsVersionAndHash

class this._cls0
    implements StanzaListener
{

    final EntityCapsManager this$0;

    public void processPacket(Stanza stanza)
    {
        if (!EntityCapsManager.access$400(EntityCapsManager.this))
        {
            return;
        } else
        {
            CapsVersionAndHash capsversionandhash = getCapsVersion();
            stanza.addExtension(new CapsExtension(EntityCapsManager.access$500(EntityCapsManager.this), capsversionandhash.version, capsversionandhash.hash));
            return;
        }
    }

    ()
    {
        this$0 = EntityCapsManager.this;
        super();
    }
}
