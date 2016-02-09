// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            PubSubElementType

public class NodeExtension
    implements ExtensionElement
{

    private final PubSubElementType element;
    private final String node;

    public NodeExtension(PubSubElementType pubsubelementtype)
    {
        this(pubsubelementtype, null);
    }

    public NodeExtension(PubSubElementType pubsubelementtype, String s)
    {
        element = pubsubelementtype;
        node = s;
    }

    public String getElementName()
    {
        return element.getElementName();
    }

    public String getNamespace()
    {
        return element.getNamespace().getXmlns();
    }

    public String getNode()
    {
        return node;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append(" - content [").append(toXML()).append("]").toString();
    }

    public CharSequence toXML()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append('<').append(getElementName());
        String s;
        if (node == null)
        {
            s = "";
        } else
        {
            s = (new StringBuilder()).append(" node='").append(node).append('\'').toString();
        }
        return stringbuilder.append(s).append("/>").toString();
    }
}
