// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Arrays;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            EmbeddedPacketExtension, NodeExtension, EventElementType

public class EventElement
    implements EmbeddedPacketExtension
{

    private NodeExtension ext;
    private EventElementType type;

    public EventElement(EventElementType eventelementtype, NodeExtension nodeextension)
    {
        type = eventelementtype;
        ext = nodeextension;
    }

    public String getElementName()
    {
        return "event";
    }

    public NodeExtension getEvent()
    {
        return ext;
    }

    public EventElementType getEventType()
    {
        return type;
    }

    public List getExtensions()
    {
        return Arrays.asList(new ExtensionElement[] {
            getEvent()
        });
    }

    public String getNamespace()
    {
        return PubSubNamespace.EVENT.getXmlns();
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append("<event xmlns='").append(PubSubNamespace.EVENT.getXmlns()).append("'>").toString());
        stringbuilder.append(ext.toXML());
        stringbuilder.append("</event>");
        return stringbuilder.toString();
    }
}
