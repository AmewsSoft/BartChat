// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, PubSubElementType

public class GetItemsRequest extends NodeExtension
{

    protected final int maxItems;
    protected final String subId;

    public GetItemsRequest(String s)
    {
        this(s, null, -1);
    }

    public GetItemsRequest(String s, int i)
    {
        this(s, null, i);
    }

    public GetItemsRequest(String s, String s1)
    {
        this(s, s1, -1);
    }

    public GetItemsRequest(String s, String s1, int i)
    {
        super(PubSubElementType.ITEMS, s);
        maxItems = i;
        subId = s1;
    }

    public int getMaxItems()
    {
        return maxItems;
    }

    public String getSubscriptionId()
    {
        return subId;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement(getElementName());
        xmlstringbuilder.attribute("node", getNode());
        xmlstringbuilder.optAttribute("subid", getSubscriptionId());
        xmlstringbuilder.optIntAttribute("max_items", getMaxItems());
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }
}
