// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdata.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xdata.FormField;

// Referenced classes of package org.jivesoftware.smackx.xdata.packet:
//            DataForm

public static class fields
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

    public (List list)
    {
        fields = new ArrayList();
        fields = list;
    }
}
