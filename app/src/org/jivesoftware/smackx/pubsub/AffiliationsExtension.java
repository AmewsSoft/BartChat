// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, PubSubElementType, Affiliation

public class AffiliationsExtension extends NodeExtension
{

    protected List items;

    public AffiliationsExtension()
    {
        super(PubSubElementType.AFFILIATIONS);
        items = Collections.emptyList();
    }

    public AffiliationsExtension(List list)
    {
        super(PubSubElementType.AFFILIATIONS);
        items = Collections.emptyList();
        items = list;
    }

    public List getAffiliations()
    {
        return items;
    }

    public CharSequence toXML()
    {
        if (items == null || items.size() == 0)
        {
            return super.toXML();
        }
        StringBuilder stringbuilder = new StringBuilder("<");
        stringbuilder.append(getElementName());
        stringbuilder.append(">");
        for (Iterator iterator = items.iterator(); iterator.hasNext(); stringbuilder.append(((Affiliation)iterator.next()).toXML())) { }
        stringbuilder.append("</");
        stringbuilder.append(getElementName());
        stringbuilder.append(">");
        return stringbuilder.toString();
    }
}
