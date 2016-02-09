// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, PubSubElementType

public class Item extends NodeExtension
{

    private String id;

    public Item()
    {
        super(PubSubElementType.ITEM);
    }

    public Item(String s)
    {
        super(PubSubElementType.ITEM);
        id = s;
    }

    public Item(String s, String s1)
    {
        super(PubSubElementType.ITEM_EVENT, s1);
        id = s;
    }

    public String getId()
    {
        return id;
    }

    public String getNamespace()
    {
        return null;
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
        if (id != null)
        {
            stringbuilder.append(" id='");
            stringbuilder.append(id);
            stringbuilder.append("'");
        }
        if (getNode() != null)
        {
            stringbuilder.append(" node='");
            stringbuilder.append(getNode());
            stringbuilder.append("'");
        }
        stringbuilder.append("/>");
        return stringbuilder.toString();
    }
}
