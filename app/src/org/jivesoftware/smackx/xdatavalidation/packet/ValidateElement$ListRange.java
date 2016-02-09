// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdatavalidation.packet;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.NumberUtil;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.xdatavalidation.packet:
//            ValidateElement

public static class max
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

    public (Long long1, Long long2)
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
