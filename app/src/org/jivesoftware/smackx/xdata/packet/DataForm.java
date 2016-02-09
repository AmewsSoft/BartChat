// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdata.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xdata.FormField;

public class DataForm
    implements ExtensionElement
{
    public static class Item
    {

        public static final String ELEMENT = "item";
        private List fields;

        public List getFields()
        {
            return Collections.unmodifiableList(new ArrayList(fields));
        }

        public CharSequence toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
            xmlstringbuilder.openElement("item");
            for (Iterator iterator = getFields().iterator(); iterator.hasNext(); xmlstringbuilder.append(((FormField)iterator.next()).toXML())) { }
            xmlstringbuilder.closeElement("item");
            return xmlstringbuilder;
        }

        public Item(List list)
        {
            fields = new ArrayList();
            fields = list;
        }
    }

    public static class ReportedData
    {

        public static final String ELEMENT = "reported";
        private List fields;

        public List getFields()
        {
            return Collections.unmodifiableList(new ArrayList(fields));
        }

        public CharSequence toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
            xmlstringbuilder.openElement("reported");
            for (Iterator iterator = getFields().iterator(); iterator.hasNext(); xmlstringbuilder.append(((FormField)iterator.next()).toXML())) { }
            xmlstringbuilder.closeElement("reported");
            return xmlstringbuilder;
        }

        public ReportedData(List list)
        {
            fields = new ArrayList();
            fields = list;
        }
    }

    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type cancel;
        public static final Type form;
        public static final Type result;
        public static final Type submit;

        public static Type fromString(String s)
        {
            return valueOf(s.toLowerCase(Locale.US));
        }

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(org/jivesoftware/smackx/xdata/packet/DataForm$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            form = new Type("form", 0);
            submit = new Type("submit", 1);
            cancel = new Type("cancel", 2);
            result = new Type("result", 3);
            $VALUES = (new Type[] {
                form, submit, cancel, result
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String ELEMENT = "x";
    public static final String NAMESPACE = "jabber:x:data";
    private final List extensionElements = new ArrayList();
    private final List fields = new ArrayList();
    private List instructions;
    private final List items = new ArrayList();
    private ReportedData reportedData;
    private String title;
    private Type type;

    public DataForm(Type type1)
    {
        instructions = new ArrayList();
        type = type1;
    }

    public static DataForm from(Stanza stanza)
    {
        return (DataForm)stanza.getExtension("x", "jabber:x:data");
    }

    public void addExtensionElement(Element element)
    {
        extensionElements.add(element);
    }

    public void addField(FormField formfield)
    {
        String s = formfield.getVariable();
        if (s != null && getField(s) != null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("This data form already contains a form field with the variable name '").append(s).append("'").toString());
        }
        synchronized (fields)
        {
            fields.add(formfield);
        }
        return;
        formfield;
        list;
        JVM INSTR monitorexit ;
        throw formfield;
    }

    public void addInstruction(String s)
    {
        synchronized (instructions)
        {
            instructions.add(s);
        }
        return;
        s;
        list;
        JVM INSTR monitorexit ;
        throw s;
    }

    public void addItem(Item item)
    {
        synchronized (items)
        {
            items.add(item);
        }
        return;
        item;
        list;
        JVM INSTR monitorexit ;
        throw item;
    }

    public String getElementName()
    {
        return "x";
    }

    public List getExtensionElements()
    {
        return Collections.unmodifiableList(extensionElements);
    }

    public FormField getField(String s)
    {
label0:
        {
            FormField formfield;
            synchronized (fields)
            {
                Iterator iterator = fields.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break label0;
                    }
                    formfield = (FormField)iterator.next();
                } while (!s.equals(formfield.getVariable()));
            }
            return formfield;
        }
        list;
        JVM INSTR monitorexit ;
        return null;
        s;
        list;
        JVM INSTR monitorexit ;
        throw s;
    }

    public List getFields()
    {
        List list1;
        synchronized (fields)
        {
            list1 = Collections.unmodifiableList(new ArrayList(fields));
        }
        return list1;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public FormField getHiddenFormTypeField()
    {
        FormField formfield = getField("FORM_TYPE");
        if (formfield != null && formfield.getType() == org.jivesoftware.smackx.xdata.FormField.Type.hidden)
        {
            return formfield;
        } else
        {
            return null;
        }
    }

    public List getInstructions()
    {
        List list1;
        synchronized (instructions)
        {
            list1 = Collections.unmodifiableList(new ArrayList(instructions));
        }
        return list1;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public List getItems()
    {
        List list1;
        synchronized (items)
        {
            list1 = Collections.unmodifiableList(new ArrayList(items));
        }
        return list1;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String getNamespace()
    {
        return "jabber:x:data";
    }

    public ReportedData getReportedData()
    {
        return reportedData;
    }

    public String getTitle()
    {
        return title;
    }

    public Type getType()
    {
        return type;
    }

    public boolean hasHiddenFormTypeField()
    {
        return getHiddenFormTypeField() != null;
    }

    public void setInstructions(List list)
    {
        instructions = list;
    }

    public void setReportedData(ReportedData reporteddata)
    {
        reportedData = reporteddata;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("type", getType());
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optElement("title", getTitle());
        for (Iterator iterator = getInstructions().iterator(); iterator.hasNext(); xmlstringbuilder.element("instructions", (String)iterator.next())) { }
        if (getReportedData() != null)
        {
            xmlstringbuilder.append(getReportedData().toXML());
        }
        for (Iterator iterator1 = getItems().iterator(); iterator1.hasNext(); xmlstringbuilder.append(((Item)iterator1.next()).toXML())) { }
        for (Iterator iterator2 = getFields().iterator(); iterator2.hasNext(); xmlstringbuilder.append(((FormField)iterator2.next()).toXML())) { }
        for (Iterator iterator3 = extensionElements.iterator(); iterator3.hasNext(); xmlstringbuilder.append(((Element)iterator3.next()).toXML())) { }
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
