// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.packet;

import org.jivesoftware.smack.packet.FullStreamElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.sm.packet:
//            StreamManagement

public static class handledCount extends FullStreamElement
{

    public static final String ELEMENT = "a";
    private final long handledCount;

    public String getElementName()
    {
        return "a";
    }

    public long getHandledCount()
    {
        return handledCount;
    }

    public String getNamespace()
    {
        return "urn:xmpp:sm:3";
    }

    public CharSequence toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("h", Long.toString(handledCount));
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }

    public A(long l)
    {
        handledCount = l;
    }
}
