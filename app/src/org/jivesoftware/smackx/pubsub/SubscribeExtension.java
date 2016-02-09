// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, PubSubElementType

public class SubscribeExtension extends NodeExtension
{

    protected String jid;

    public SubscribeExtension(String s)
    {
        super(PubSubElementType.SUBSCRIBE);
        jid = s;
    }

    public SubscribeExtension(String s, String s1)
    {
        super(PubSubElementType.SUBSCRIBE, s1);
        jid = s;
    }

    public String getJid()
    {
        return jid;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder("<");
        stringbuilder.append(getElementName());
        if (getNode() != null)
        {
            stringbuilder.append(" node='");
            stringbuilder.append(getNode());
            stringbuilder.append("'");
        }
        stringbuilder.append(" jid='");
        stringbuilder.append(getJid());
        stringbuilder.append("'/>");
        return stringbuilder.toString();
    }
}
