// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.List;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            Node, EventElement, NodeExtension, EmbeddedPacketExtension

class secondElement
    implements StanzaFilter
{

    private String firstElement;
    private String secondElement;
    final Node this$0;

    public boolean accept(Stanza stanza)
    {
        if (!(stanza instanceof Message))
        {
            return false;
        }
        stanza = (EventElement)stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns());
        if (stanza == null)
        {
            return false;
        }
        stanza = stanza.getEvent();
        if (stanza == null)
        {
            return false;
        }
        if (stanza.getElementName().equals(firstElement))
        {
            if (!stanza.getNode().equals(getId()))
            {
                return false;
            }
            if (secondElement == null)
            {
                return true;
            }
            if (stanza instanceof EmbeddedPacketExtension)
            {
                stanza = ((EmbeddedPacketExtension)stanza).getExtensions();
                if (stanza.size() > 0 && ((ExtensionElement)stanza.get(0)).getElementName().equals(secondElement))
                {
                    return true;
                }
            }
        }
        return false;
    }

    (String s)
    {
        this$0 = Node.this;
        super();
        firstElement = s;
    }

    firstElement(String s, String s1)
    {
        this$0 = Node.this;
        super();
        firstElement = s;
        secondElement = s1;
    }
}
