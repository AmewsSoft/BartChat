// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5.packet;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5.packet:
//            Bytestream

public static class JID
    implements NamedElement
{

    public static String ELEMENTNAME = "streamhost-used";
    private final String JID;

    public String getElementName()
    {
        return ELEMENTNAME;
    }

    public String getJID()
    {
        return JID;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("jid", getJID());
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }


    public Q(String s)
    {
        JID = s;
    }
}
