// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.packet;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.muc.packet:
//            MUCUser

public static class 
    implements NamedElement
{

    public static final String ELEMENT = "decline";
    private String from;
    private String reason;
    private String to;

    public String getElementName()
    {
        return "decline";
    }

    public String getFrom()
    {
        return from;
    }

    public String getReason()
    {
        return reason;
    }

    public String getTo()
    {
        return to;
    }

    public void setFrom(String s)
    {
        from = s;
    }

    public void setReason(String s)
    {
        reason = s;
    }

    public void setTo(String s)
    {
        to = s;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.optAttribute("to", getTo());
        xmlstringbuilder.optAttribute("from", getFrom());
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optElement("reason", getReason());
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }

    public ()
    {
    }
}
