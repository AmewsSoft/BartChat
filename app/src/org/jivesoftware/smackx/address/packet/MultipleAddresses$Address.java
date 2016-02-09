// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.address.packet;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.address.packet:
//            MultipleAddresses

public static class <init>
    implements NamedElement
{

    public static final String ELEMENT = "address";
    private boolean delivered;
    private String description;
    private String jid;
    private String node;
    private final uri type;
    private String uri;

    private void setDelivered(boolean flag)
    {
        delivered = flag;
    }

    private void setDescription(String s)
    {
        description = s;
    }

    private void setJid(String s)
    {
        jid = s;
    }

    private void setNode(String s)
    {
        node = s;
    }

    private void setUri(String s)
    {
        uri = s;
    }

    public String getDescription()
    {
        return description;
    }

    public String getElementName()
    {
        return "address";
    }

    public String getJid()
    {
        return jid;
    }

    public String getNode()
    {
        return node;
    }

    public node getType()
    {
        return type;
    }

    public String getUri()
    {
        return uri;
    }

    public boolean isDelivered()
    {
        return delivered;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement(this).attribute("type", type);
        xmlstringbuilder.optAttribute("jid", jid);
        xmlstringbuilder.optAttribute("node", node);
        xmlstringbuilder.optAttribute("desc", description);
        if (description != null && description.trim().length() > 0)
        {
            xmlstringbuilder.append(" desc=\"");
            xmlstringbuilder.append(description).append("\"");
        }
        xmlstringbuilder.optBooleanAttribute("delivered", delivered);
        xmlstringbuilder.optAttribute("uri", uri);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }






    private ( )
    {
        type = ;
    }

    type(type type1, type type2)
    {
        this(type1);
    }
}
