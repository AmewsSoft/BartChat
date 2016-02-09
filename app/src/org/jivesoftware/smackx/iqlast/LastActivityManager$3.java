// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqlast;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smackx.iqlast:
//            LastActivityManager

class this._cls0
    implements StanzaListener
{

    final LastActivityManager this$0;

    public void processPacket(Stanza stanza)
    {
        if (((Message)stanza).getType() == org.jivesoftware.smack.packet.)
        {
            return;
        } else
        {
            LastActivityManager.access$000(LastActivityManager.this);
            return;
        }
    }

    ()
    {
        this$0 = LastActivityManager.this;
        super();
    }
}
