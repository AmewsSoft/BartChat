// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.packet;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class Destroy
    implements NamedElement
{

    public static final String ELEMENT = "destroy";
    private String jid;
    private String reason;

    public Destroy()
    {
    }

    public String getElementName()
    {
        return "destroy";
    }

    public String getJid()
    {
        return jid;
    }

    public String getReason()
    {
        return reason;
    }

    public void setJid(String s)
    {
        jid = s;
    }

    public void setReason(String s)
    {
        reason = s;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.optAttribute("jid", getJid());
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optElement("reason", getReason());
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
