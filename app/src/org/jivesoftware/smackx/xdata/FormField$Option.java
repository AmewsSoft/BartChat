// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdata;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.xdata:
//            FormField

public static class value
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
                Object obj1 = (value)obj;
                if (value.equals(((value) (obj1)).value))
                {
                    if (label == null)
                    {
                        obj = "";
                    } else
                    {
                        obj = label;
                    }
                    if (((label) (obj1)).label == null)
                    {
                        obj1 = "";
                    } else
                    {
                        obj1 = ((label) (obj1)).label;
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

    public (String s)
    {
        value = s;
    }

    public value(String s, String s1)
    {
        label = s;
        value = s1;
    }
}
