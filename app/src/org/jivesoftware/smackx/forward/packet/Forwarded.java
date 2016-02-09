// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.forward.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.delay.packet.DelayInformation;

public class Forwarded
    implements ExtensionElement
{

    public static final String ELEMENT = "forwarded";
    public static final String NAMESPACE = "urn:xmpp:forward:0";
    private final DelayInformation delay;
    private final Stanza forwardedPacket;

    public Forwarded(Stanza stanza)
    {
        this(null, stanza);
    }

    public Forwarded(DelayInformation delayinformation, Stanza stanza)
    {
        delay = delayinformation;
        forwardedPacket = stanza;
    }

    public static Forwarded from(Stanza stanza)
    {
        return (Forwarded)stanza.getExtension("forwarded", "urn:xmpp:forward:0");
    }

    public DelayInformation getDelayInformation()
    {
        return delay;
    }

    public String getElementName()
    {
        return "forwarded";
    }

    public Stanza getForwardedPacket()
    {
        return forwardedPacket;
    }

    public String getNamespace()
    {
        return "urn:xmpp:forward:0";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optElement(getDelayInformation());
        xmlstringbuilder.append(forwardedPacket.toXML());
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
