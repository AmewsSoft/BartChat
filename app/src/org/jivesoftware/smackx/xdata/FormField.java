// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement;

public class FormField
    implements NamedElement
{
    public static class Option
        implements NamedElement
    {

        public static final String ELEMENT = "option";
        private String label;
        private final String value;

        public boolean equals(Object obj)
        {
            if (obj != null)
            {
                if (obj == this)
                {
                    return true;
                }
                if (obj.getClass() == getClass())
                {
                    Object obj1 = (Option)obj;
                    if (value.equals(((Option) (obj1)).value))
                    {
                        if (label == null)
                        {
                            obj = "";
                        } else
                        {
                            obj = label;
                        }
                        if (((Option) (obj1)).label == null)
                        {
                            obj1 = "";
                        } else
                        {
                            obj1 = ((Option) (obj1)).label;
                        }
                        if (((String) (obj)).equals(obj1))
                        {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public String getElementName()
        {
            return "option";
        }

        public String getLabel()
        {
            return label;
        }

        public String getValue()
        {
            return value;
        }

        public int hashCode()
        {
            int j = value.hashCode();
            int i;
            if (label == null)
            {
                i = 0;
            } else
            {
                i = label.hashCode();
            }
            return (j + 37) * 37 + i;
        }

        public String toString()
        {
            return getLabel();
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
            xmlstringbuilder.element("value", getValue());
            xmlstringbuilder.closeElement(this);
            return xmlstringbuilder;
        }

        public Option(String s)
        {
            value = s;
        }

        public Option(String s, String s1)
        {
            label = s;
            value = s1;
        }
    }

    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type bool;
        public static final Type fixed;
        public static final Type hidden;
        public static final Type jid_multi;
        public static final Type jid_single;
        public static final Type list_multi;
        public static final Type list_single;
        public static final Type text_multi;
        public static final Type text_private;
        public static final Type text_single;

        public static Type fromString(String s)
        {
            byte byte0;
            if (s == null)
            {
                return null;
            }
            byte0 = -1;
            s.hashCode();
            JVM INSTR tableswitch 64711720 64711720: default 32
        //                       64711720 64;
               goto _L1 _L2
_L1:
            switch (byte0)
            {
            default:
                return valueOf(s.replace('-', '_'));

            case 0: // '\0'
                return bool;
            }
_L2:
            if (s.equals("boolean"))
            {
                byte0 = 0;
            }
              goto _L1
        }

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(org/jivesoftware/smackx/xdata/FormField$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        public String toString()
        {
            static class _cls1
            {

                static final int $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[];

                static 
                {
                    $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type = new int[Type.values().length];
                    try
                    {
                        $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[Type.bool.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror) { }
                }
            }

            switch (_cls1..SwitchMap.org.jivesoftware.smackx.xdata.FormField.Type[ordinal()])
            {
            default:
                return name().replace('_', '-');

            case 1: // '\001'
                return "boolean";
            }
        }

        static 
        {
            bool = new Type("bool", 0);
            fixed = new Type("fixed", 1);
            hidden = new Type("hidden", 2);
            jid_multi = new Type("jid_multi", 3);
            jid_single = new Type("jid_single", 4);
            list_multi = new Type("list_multi", 5);
            list_single = new Type("list_single", 6);
            text_multi = new Type("text_multi", 7);
            text_private = new Type("text_private", 8);
            text_single = new Type("text_single", 9);
            $VALUES = (new Type[] {
                bool, fixed, hidden, jid_multi, jid_single, list_multi, list_single, text_multi, text_private, text_single
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String ELEMENT = "field";
    public static final String FORM_TYPE = "FORM_TYPE";
    private String description;
    private String label;
    private final List options;
    private boolean required;
    private Type type;
    private ValidateElement validateElement;
    private final List values;
    private final String variable;

    public FormField()
    {
        this(null);
        type = Type.fixed;
    }

    public FormField(String s)
    {
        required = false;
        options = new ArrayList();
        values = new ArrayList();
        variable = s;
    }

    public void addOption(Option option)
    {
        synchronized (options)
        {
            options.add(option);
        }
        return;
        option;
        list;
        JVM INSTR monitorexit ;
        throw option;
    }

    public void addValue(String s)
    {
        synchronized (values)
        {
            values.add(s);
        }
        return;
        s;
        list;
        JVM INSTR monitorexit ;
        throw s;
    }

    public void addValues(List list)
    {
        synchronized (values)
        {
            values.addAll(list);
        }
        return;
        list;
        list1;
        JVM INSTR monitorexit ;
        throw list;
    }

    public boolean equals(Object obj)
    {
        if (obj != null)
        {
            if (obj == this)
            {
                return true;
            }
            if (obj instanceof FormField)
            {
                obj = (FormField)obj;
                return toXML().equals(((FormField) (obj)).toXML());
            }
        }
        return false;
    }

    public String getDescription()
    {
        return description;
    }

    public String getElementName()
    {
        return "field";
    }

    public String getLabel()
    {
        return label;
    }

    public List getOptions()
    {
        List list1;
        synchronized (options)
        {
            list1 = Collections.unmodifiableList(new ArrayList(options));
        }
        return list1;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Type getType()
    {
        return type;
    }

    public ValidateElement getValidateElement()
    {
        return validateElement;
    }

    public List getValues()
    {
        List list1;
        synchronized (values)
        {
            list1 = Collections.unmodifiableList(new ArrayList(values));
        }
        return list1;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String getVariable()
    {
        return variable;
    }

    public int hashCode()
    {
        return toXML().hashCode();
    }

    public boolean isRequired()
    {
        return required;
    }

    protected void resetValues()
    {
        synchronized (values)
        {
            values.removeAll(new ArrayList(values));
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setDescription(String s)
    {
        description = s;
    }

    public void setLabel(String s)
    {
        label = s;
    }

    public void setRequired(boolean flag)
    {
        required = flag;
    }

    public void setType(Type type1)
    {
        if (type1 == Type.fixed)
        {
            throw new IllegalArgumentException("Can not set type to fixed, use FormField constructor without arguments instead.");
        } else
        {
            type = type1;
            return;
        }
    }

    public void setValidateElement(ValidateElement validateelement)
    {
        validateelement.checkConsistency(this);
        validateElement = validateelement;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.optAttribute("label", getLabel());
        xmlstringbuilder.optAttribute("var", getVariable());
        xmlstringbuilder.optAttribute("type", getType());
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optElement("desc", getDescription());
        xmlstringbuilder.condEmptyElement(isRequired(), "required");
        for (Iterator iterator = getValues().iterator(); iterator.hasNext(); xmlstringbuilder.element("value", (String)iterator.next())) { }
        for (Iterator iterator1 = getOptions().iterator(); iterator1.hasNext(); xmlstringbuilder.append(((Option)iterator1.next()).toXML())) { }
        xmlstringbuilder.optElement(validateElement);
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
