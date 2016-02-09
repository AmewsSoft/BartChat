// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5.packet;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5.packet:
//            Bytestream

public static class port
    implements NamedElement
{

    public static String ELEMENTNAME = "streamhost";
    private final String JID;
    private final String addy;
    private final int port;

    public String getAddress()
    {
        return addy;
    }

    public String getElementName()
    {
        return ELEMENTNAME;
    }

    public String getJID()
    {
        return JID;
    }

    public int getPort()
    {
        return port;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("jid", getJID());
        xmlstringbuilder.attribute("host", getAddress());
        if (getPort() != 0)
        {
            xmlstringbuilder.attribute("port", Integer.toString(getPort()));
        } else
        {
            xmlstringbuilder.attribute("zeroconf", "_jabber.bytestreams");
        }
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }


    public (String s, String s1)
    {
        this(s, s1, 0);
    }

    public <init>(String s, String s1, int i)
    {
        JID = s;
        addy = s1;
        port = i;
    }
}
