// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

public class RetractItem
    implements ExtensionElement
{

    private String id;

    public RetractItem(String s)
    {
        if (s == null)
        {
            throw new IllegalArgumentException("itemId must not be 'null'");
        } else
        {
            id = s;
            return;
        }
    }

    public String getElementName()
    {
        return "retract";
    }

    public String getId()
    {
        return id;
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
        return (new StringBuilder()).append("<retract id='").append(id).append("'/>").toString();
    }
}
