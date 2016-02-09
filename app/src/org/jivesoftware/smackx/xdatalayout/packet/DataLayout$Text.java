// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdatalayout.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.xdatalayout.packet:
//            DataLayout

public static class text
    implements ormLayoutElement
{

    public static final String ELEMENT = "text";
    private final String text;

    public String getElementName()
    {
        return "text";
    }

    public String getText()
    {
        return text;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.element("text", getText());
        return xmlstringbuilder;
    }

    public ormLayoutElement(String s)
    {
        text = s;
    }
}
