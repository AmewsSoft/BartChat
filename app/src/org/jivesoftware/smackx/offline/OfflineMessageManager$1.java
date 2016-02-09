// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.offline;

import java.util.List;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.offline.packet.OfflineMessageInfo;

// Referenced classes of package org.jivesoftware.smackx.offline:
//            OfflineMessageManager

class val.nodes
    implements StanzaFilter
{

    final OfflineMessageManager this$0;
    final List val$nodes;

    public boolean accept(Stanza stanza)
    {
        stanza = (OfflineMessageInfo)stanza.getExtension("offline", "http://jabber.org/protocol/offline");
        return val$nodes.contains(stanza.getNode());
    }

    o()
    {
        this$0 = final_offlinemessagemanager;
        val$nodes = List.this;
        super();
    }
}
