// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.delay.packet;

import java.util.Date;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jxmpp.util.XmppDateTime;

public class DelayInformation
    implements ExtensionElement
{

    public static final String ELEMENT = "delay";
    public static final String NAMESPACE = "urn:xmpp:delay";
    private final String from;
    private final String reason;
    private final Date stamp;

    public DelayInformation(Date date)
    {
        this(date, null, null);
    }

    public DelayInformation(Date date, String s, String s1)
    {
        stamp = date;
        from = s;
        reason = s1;
    }

    public static DelayInformation from(Stanza stanza)
    {
        return (DelayInformation)stanza.getExtension("delay", "urn:xmpp:delay");
    }

    public static DelayInformation getFrom(Stanza stanza)
    {
        return from(stanza);
    }

    public String getElementName()
    {
        return "delay";
    }

    public String getFrom()
    {
        return from;
    }

    public String getNamespace()
    {
        return "urn:xmpp:delay";
    }

    public String getReason()
    {
        return reason;
    }

    public Date getStamp()
    {
        return stamp;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("stamp", XmppDateTime.formatXEP0082Date(stamp));
        xmlstringbuilder.optAttribute("from", from);
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optAppend(reason);
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
