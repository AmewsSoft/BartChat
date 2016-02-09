// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.packet;

import org.jivesoftware.smack.packet.FullStreamElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.sm.packet:
//            StreamManagement

private static abstract class previd extends FullStreamElement
{

    private final long handledCount;
    private final String previd;

    public long getHandledCount()
    {
        return handledCount;
    }

    public final String getNamespace()
    {
        return "urn:xmpp:sm:3";
    }

    public String getPrevId()
    {
        return previd;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public final XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("h", Long.toString(handledCount));
        xmlstringbuilder.attribute("previd", previd);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }

    public (long l, String s)
    {
        handledCount = l;
        previd = s;
    }
}
