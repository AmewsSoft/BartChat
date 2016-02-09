// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdatavalidation.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xdata.FormField;

// Referenced classes of package org.jivesoftware.smackx.xdatavalidation.packet:
//            ValidateElement

public static class regex extends ValidateElement
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
        return super.toXML();
    }

    public Y(String s, String s1)
    {
        super(s, null);
        regex = s1;
    }
}
