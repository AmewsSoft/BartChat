// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.pubsub.listener.ItemDeleteListener;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            Node, EventElement, PubSubElementType, ItemsExtension, 
//            RetractItem, ItemDeleteEvent

public class listener
    implements StanzaListener
{

    private ItemDeleteListener listener;
    final Node this$0;

    public void processPacket(Stanza stanza)
    {
        Object obj = (EventElement)stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns());
        if (((ExtensionElement)((EventElement) (obj)).getExtensions().get(0)).getElementName().equals(PubSubElementType.PURGE_EVENT.getElementName()))
        {
            listener.handlePurge();
            return;
        }
        obj = (ItemsExtension)((EventElement) (obj)).getEvent();
        Object obj1 = ((ItemsExtension) (obj)).getItems();
        ArrayList arraylist = new ArrayList(((Collection) (obj1)).size());
        for (obj1 = ((Collection) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); arraylist.add(((RetractItem)((Iterator) (obj1)).next()).getId())) { }
        stanza = new ItemDeleteEvent(((ItemsExtension) (obj)).getNode(), arraylist, Node.access$000(stanza));
        listener.handleDeletedItems(stanza);
    }

    public r(ItemDeleteListener itemdeletelistener)
    {
        this$0 = Node.this;
        super();
        listener = itemdeletelistener;
    }
}
