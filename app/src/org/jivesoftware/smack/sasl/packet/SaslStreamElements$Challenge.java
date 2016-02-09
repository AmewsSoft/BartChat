// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl.packet;

import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.sasl.packet:
//            SaslStreamElements

public static class data
    implements PlainStreamElement
{

    public static final String ELEMENT = "challenge";
    private final String data;

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = (new XmlStringBuilder()).halfOpenElement("challenge").xmlnsAttribute("urn:ietf:params:xml:ns:xmpp-sasl").rightAngleBracket();
        xmlstringbuilder.optAppend(data);
        xmlstringbuilder.closeElement("challenge");
        return xmlstringbuilder;
    }

    public (String s)
    {
        data = StringUtils.returnIfNotEmptyTrimmed(s);
    }
}
