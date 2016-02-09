// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5.packet;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5.packet:
//            Bytestream

public static class target
    implements NamedElement
{

    public static String ELEMENTNAME = "activate";
    private final String target;

    public String getElementName()
    {
        return ELEMENTNAME;
    }

    public String getTarget()
    {
        return target;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.escape(getTarget());
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }


    public (String s)
    {
        target = s;
    }
}
