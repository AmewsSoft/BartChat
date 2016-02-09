// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.pubsub.listener.NodeConfigListener;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            Node, EventElement, ConfigurationEvent

public class listener
    implements StanzaListener
{

    private NodeConfigListener listener;
    final Node this$0;

    public void processPacket(Stanza stanza)
    {
        stanza = (ConfigurationEvent)((EventElement)stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns())).getEvent();
        listener.handleNodeConfiguration(stanza);
    }

    public r(NodeConfigListener nodeconfiglistener)
    {
        this$0 = Node.this;
        super();
        listener = nodeconfiglistener;
    }
}
