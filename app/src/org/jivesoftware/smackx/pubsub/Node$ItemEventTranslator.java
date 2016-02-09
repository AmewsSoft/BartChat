// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.delay.DelayInformationManager;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            Node, EventElement, ItemsExtension, ItemPublishEvent

public class listener
    implements StanzaListener
{

    private ItemEventListener listener;
    final Node this$0;

    public void processPacket(Stanza stanza)
    {
        ItemsExtension itemsextension = (ItemsExtension)((EventElement)stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns())).getEvent();
        stanza = new ItemPublishEvent(itemsextension.getNode(), itemsextension.getItems(), Node.access$000(stanza), DelayInformationManager.getDelayTimestamp(stanza));
        listener.handlePublishedItems(stanza);
    }

    public r(ItemEventListener itemeventlistener)
    {
        this$0 = Node.this;
        super();
        listener = itemeventlistener;
    }
}
