// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, PubSubElementType

public class UnsubscribeExtension extends NodeExtension
{

    protected String id;
    protected String jid;

    public UnsubscribeExtension(String s)
    {
        this(s, null, null);
    }

    public UnsubscribeExtension(String s, String s1)
    {
        this(s, s1, null);
    }

    public UnsubscribeExtension(String s, String s1, String s2)
    {
        super(PubSubElementType.UNSUBSCRIBE, s1);
        jid = s;
        id = s2;
    }

    public String getId()
    {
        return id;
    }

    public String getJid()
    {
        return jid;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement(getElementName());
        xmlstringbuilder.attribute("jid", jid);
        xmlstringbuilder.optAttribute("node", getNode());
        xmlstringbuilder.optAttribute("subid", id);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }
}
