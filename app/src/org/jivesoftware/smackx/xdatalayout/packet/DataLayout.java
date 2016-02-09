// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdatalayout.packet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class DataLayout
    implements ExtensionElement
{
    public static interface DataFormLayoutElement
        extends NamedElement
    {
    }

    public static class Fieldref
        implements DataFormLayoutElement
    {

        public static final String ELEMENT = "fieldref";
        private final String var;

        public String getElementName()
        {
            return "fieldref";
        }

        public String getVar()
        {
            return var;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.attribute("var", getVar());
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }

        public Fieldref(String s)
        {
            var = s;
        }
    }

    public static class Reportedref
        implements DataFormLayoutElement
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

        public Reportedref()
        {
        }
    }

    public static class Section
        implements DataFormLayoutElement
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
            DataLayout.walkList(xmlstringbuilder, getSectionLayout());
            xmlstringbuilder.closeElement("section");
            return xmlstringbuilder;
        }

        public Section(String s)
        {
            label = s;
        }
    }

    public static class Text
        implements DataFormLayoutElement
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

        public Text(String s)
        {
            text = s;
        }
    }


    public static final String ELEMENT = "page";
    public static final String NAMESPACE = "http://jabber.org/protocol/xdata-layout";
    private final String label;
    private final List pageLayout = new ArrayList();

    public DataLayout(String s)
    {
        label = s;
    }

    private static void walkList(XmlStringBuilder xmlstringbuilder, List list)
    {
        for (list = list.iterator(); list.hasNext(); xmlstringbuilder.append(((DataFormLayoutElement)list.next()).toXML())) { }
    }

    public String getElementName()
    {
        return "page";
    }

    public String getLabel()
    {
        return label;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/xdata-layout";
    }

    public List getPageLayout()
    {
        return pageLayout;
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
        walkList(xmlstringbuilder, getPageLayout());
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }

}
