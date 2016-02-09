// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdatavalidation.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.NumberUtil;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdatavalidation.ValidationConsistencyException;

public abstract class ValidateElement
    implements ExtensionElement
{
    public static class BasicValidateElement extends ValidateElement
    {

        public static final String METHOD = "basic";

        protected void appendXML(XmlStringBuilder xmlstringbuilder)
        {
            xmlstringbuilder.emptyElement("basic");
        }

        public void checkConsistency(FormField formfield)
        {
            checkListRangeConsistency(formfield);
            if (formfield.getType() == null) goto _L2; else goto _L1
_L1:
            static class _cls1
            {

                static final int $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[];

                static 
                {
                    $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type = new int[org.jivesoftware.smackx.xdata.FormField.Type.values().length];
                    try
                    {
                        $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[org.jivesoftware.smackx.xdata.FormField.Type.hidden.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror4) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[org.jivesoftware.smackx.xdata.FormField.Type.jid_multi.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[org.jivesoftware.smackx.xdata.FormField.Type.jid_single.ordinal()] = 3;
                    }
                    catch (NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[org.jivesoftware.smackx.xdata.FormField.Type.list_multi.ordinal()] = 4;
                    }
                    catch (NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[org.jivesoftware.smackx.xdata.FormField.Type.text_multi.ordinal()] = 5;
                    }
                    catch (NoSuchFieldError nosuchfielderror)
                    {
                        return;
                    }
                }
            }

            _cls1..SwitchMap.org.jivesoftware.smackx.xdata.FormField.Type[formfield.getType().ordinal()];
            JVM INSTR tableswitch 1 3: default 48
        //                       1 49
        //                       2 49
        //                       3 49;
               goto _L2 _L3 _L3 _L3
_L2:
            return;
_L3:
            throw new ValidationConsistencyException(String.format("Field type '%1$s' is not consistent with validation method '%2$s'.", new Object[] {
                formfield.getType(), "basic"
            }));
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public BasicValidateElement(String s)
        {
            super(s, null);
        }
    }

    public static class ListRange
        implements NamedElement
    {

        public static final String ELEMENT = "list-range";
        private final Long max;
        private final Long min;

        public String getElementName()
        {
            return "list-range";
        }

        public Long getMax()
        {
            return max;
        }

        public Long getMin()
        {
            return min;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.optLongAttribute("min", getMin());
            xmlstringbuilder.optLongAttribute("max", getMax());
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }

        public ListRange(Long long1, Long long2)
        {
            if (long1 != null)
            {
                NumberUtil.checkIfInUInt32Range(long1.longValue());
            }
            if (long2 != null)
            {
                NumberUtil.checkIfInUInt32Range(long2.longValue());
            }
            if (long2 == null && long1 == null)
            {
                throw new IllegalArgumentException("Either min or max must be given");
            } else
            {
                min = long1;
                max = long2;
                return;
            }
        }
    }

    public static class OpenValidateElement extends ValidateElement
    {

        public static final String METHOD = "open";

        protected void appendXML(XmlStringBuilder xmlstringbuilder)
        {
            xmlstringbuilder.emptyElement("open");
        }

        public void checkConsistency(FormField formfield)
        {
            checkListRangeConsistency(formfield);
            if (formfield.getType() == null) goto _L2; else goto _L1
_L1:
            _cls1..SwitchMap.org.jivesoftware.smackx.xdata.FormField.Type[formfield.getType().ordinal()];
            JVM INSTR tableswitch 1 1: default 40
        //                       1 41;
               goto _L2 _L3
_L2:
            return;
_L3:
            throw new ValidationConsistencyException(String.format("Field type '%1$s' is not consistent with validation method '%2$s'.", new Object[] {
                formfield.getType(), "open"
            }));
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public OpenValidateElement(String s)
        {
            super(s, null);
        }
    }

    public static class RangeValidateElement extends ValidateElement
    {

        public static final String METHOD = "range";
        private final String max;
        private final String min;

        protected void appendXML(XmlStringBuilder xmlstringbuilder)
        {
            xmlstringbuilder.halfOpenElement("range");
            xmlstringbuilder.optAttribute("min", getMin());
            xmlstringbuilder.optAttribute("max", getMax());
            xmlstringbuilder.closeEmptyElement();
        }

        public void checkConsistency(FormField formfield)
        {
            checkNonMultiConsistency(formfield, "range");
            if (getDatatype().equals("xs:string"))
            {
                throw new ValidationConsistencyException(String.format("Field data type '%1$s' is not consistent with validation method '%2$s'.", new Object[] {
                    getDatatype(), "range"
                }));
            } else
            {
                return;
            }
        }

        public String getMax()
        {
            return max;
        }

        public String getMin()
        {
            return min;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public RangeValidateElement(String s, String s1, String s2)
        {
            super(s, null);
            min = s1;
            max = s2;
        }
    }

    public static class RegexValidateElement extends ValidateElement
    {

        public static final String METHOD = "regex";
        private final String regex;

        protected void appendXML(XmlStringBuilder xmlstringbuilder)
        {
            xmlstringbuilder.element("regex", getRegex());
        }

        public void checkConsistency(FormField formfield)
        {
            checkNonMultiConsistency(formfield, "regex");
        }

        public String getRegex()
        {
            return regex;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public RegexValidateElement(String s, String s1)
        {
            super(s, null);
            regex = s1;
        }
    }


    public static final String DATATYPE_XS_STRING = "xs:string";
    public static final String ELEMENT = "validate";
    public static final String NAMESPACE = "http://jabber.org/protocol/xdata-validate";
    private final String datatype;
    private ListRange listRange;

    private ValidateElement(String s)
    {
        if (!StringUtils.isNotEmpty(s))
        {
            s = null;
        }
        datatype = s;
    }

    ValidateElement(String s, _cls1 _pcls1)
    {
        this(s);
    }

    protected abstract void appendXML(XmlStringBuilder xmlstringbuilder);

    public abstract void checkConsistency(FormField formfield);

    protected void checkListRangeConsistency(FormField formfield)
    {
        Object obj = getListRange();
        if (obj != null)
        {
            Long long1 = ((ListRange) (obj)).getMax();
            obj = ((ListRange) (obj)).getMin();
            if ((long1 != null || obj != null) && formfield.getType() != org.jivesoftware.smackx.xdata.FormField.Type.list_multi)
            {
                throw new ValidationConsistencyException("Field type is not of type 'list-multi' while a 'list-range' is defined.");
            }
        }
    }

    protected void checkNonMultiConsistency(FormField formfield, String s)
    {
        checkListRangeConsistency(formfield);
        if (formfield.getType() == null) goto _L2; else goto _L1
_L1:
        _cls1..SwitchMap.org.jivesoftware.smackx.xdata.FormField.Type[formfield.getType().ordinal()];
        JVM INSTR tableswitch 1 5: default 56
    //                   1 57
    //                   2 57
    //                   3 56
    //                   4 57
    //                   5 57;
           goto _L2 _L3 _L3 _L2 _L3 _L3
_L2:
        return;
_L3:
        throw new ValidationConsistencyException(String.format("Field type '%1$s' is not consistent with validation method '%2$s'.", new Object[] {
            formfield.getType(), s
        }));
    }

    public String getDatatype()
    {
        if (datatype != null)
        {
            return datatype;
        } else
        {
            return "xs:string";
        }
    }

    public String getElementName()
    {
        return "validate";
    }

    public ListRange getListRange()
    {
        return listRange;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/xdata-validate";
    }

    public void setListRange(ListRange listrange)
    {
        listRange = listrange;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.optAttribute("datatype", datatype);
        xmlstringbuilder.rightAngleBracket();
        appendXML(xmlstringbuilder);
        xmlstringbuilder.optAppend(getListRange());
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
