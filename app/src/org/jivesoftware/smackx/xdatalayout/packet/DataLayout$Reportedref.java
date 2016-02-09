// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdatalayout.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.xdatalayout.packet:
//            DataLayout

public static class utElement
    implements utElement
{

    public static final String ELEMENT = "reportedref";

    public String getElementName()
    {
        return "reportedref";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }

    public utElement()
    {
    }
}
