// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdatalayout.packet;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.xdatalayout.packet:
//            DataLayout

public static class label
    implements LayoutElement
{

    public static final String ELEMENT = "section";
    private final String label;
    private final List sectionLayout = new ArrayList();

    public String getElementName()
    {
        return "section";
    }

    public String getLabel()
    {
        return label;
    }

    public List getSectionLayout()
    {
        return sectionLayout;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.optAttribute("label", getLabel());
        xmlstringbuilder.rightAngleBracket();
        DataLayout.access$000(xmlstringbuilder, getSectionLayout());
        xmlstringbuilder.closeElement("section");
        return xmlstringbuilder;
    }

    public LayoutElement(String s)
    {
        label = s;
    }
}
