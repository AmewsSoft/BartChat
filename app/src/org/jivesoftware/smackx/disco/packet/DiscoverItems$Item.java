// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.disco.packet:
//            DiscoverItems

public static class entityID
{

    public static final String REMOVE_ACTION = "remove";
    public static final String UPDATE_ACTION = "update";
    private String action;
    private String entityID;
    private String name;
    private String node;

    public String getAction()
    {
        return action;
    }

    public String getEntityID()
    {
        return entityID;
    }

    public String getName()
    {
        return name;
    }

    public String getNode()
    {
        return node;
    }

    public void setAction(String s)
    {
        action = s;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setNode(String s)
    {
        node = s;
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("item");
        xmlstringbuilder.attribute("jid", entityID);
        xmlstringbuilder.optAttribute("name", name);
        xmlstringbuilder.optAttribute("node", node);
        xmlstringbuilder.optAttribute("action", action);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }

    public (String s)
    {
        entityID = s;
    }
}
