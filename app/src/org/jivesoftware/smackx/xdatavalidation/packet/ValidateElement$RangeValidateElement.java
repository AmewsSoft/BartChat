// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdatavalidation.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdatavalidation.ValidationConsistencyException;

// Referenced classes of package org.jivesoftware.smackx.xdatavalidation.packet:
//            ValidateElement

public static class max extends ValidateElement
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
        return super.toXML();
    }

    public Y(String s, String s1, String s2)
    {
        super(s, null);
        min = s1;
        max = s2;
    }
}
