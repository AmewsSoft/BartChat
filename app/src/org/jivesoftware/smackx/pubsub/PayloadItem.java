// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.packet.ExtensionElement;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            Item

public class PayloadItem extends Item
{

    private ExtensionElement payload;

    public PayloadItem(String s, String s1, ExtensionElement extensionelement)
    {
        super(s, s1);
        if (extensionelement == null)
        {
            throw new IllegalArgumentException("payload cannot be 'null'");
        } else
        {
            payload = extensionelement;
            return;
        }
    }

    public PayloadItem(String s, ExtensionElement extensionelement)
    {
        super(s);
        if (extensionelement == null)
        {
            throw new IllegalArgumentException("payload cannot be 'null'");
        } else
        {
            payload = extensionelement;
            return;
        }
    }

    public PayloadItem(ExtensionElement extensionelement)
    {
        if (extensionelement == null)
        {
            throw new IllegalArgumentException("payload cannot be 'null'");
        } else
        {
            payload = extensionelement;
            return;
        }
    }

    public ExtensionElement getPayload()
    {
        return payload;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append(" | Content [").append(toXML()).append("]").toString();
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder("<item");
        if (getId() != null)
        {
            stringbuilder.append(" id='");
            stringbuilder.append(getId());
            stringbuilder.append("'");
        }
        if (getNode() != null)
        {
            stringbuilder.append(" node='");
            stringbuilder.append(getNode());
            stringbuilder.append("'");
        }
        stringbuilder.append(">");
        stringbuilder.append(payload.toXML());
        stringbuilder.append("</item>");
        return stringbuilder.toString();
    }
}
