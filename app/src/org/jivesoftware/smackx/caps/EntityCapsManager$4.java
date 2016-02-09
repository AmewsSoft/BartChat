// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jxmpp.util.cache.LruCache;

// Referenced classes of package org.jivesoftware.smackx.caps:
//            EntityCapsManager

class this._cls0
    implements StanzaListener
{

    final EntityCapsManager this$0;

    public void processPacket(Stanza stanza)
    {
        stanza = stanza.getFrom();
        EntityCapsManager.access$300().remove(stanza);
    }

    ()
    {
        this$0 = EntityCapsManager.this;
        super();
    }
}
