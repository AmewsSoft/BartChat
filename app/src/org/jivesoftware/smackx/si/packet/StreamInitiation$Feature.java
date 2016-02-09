// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.si.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.xdata.packet.DataForm;

// Referenced classes of package org.jivesoftware.smackx.si.packet:
//            StreamInitiation

public class data
    implements ExtensionElement
{

    private final DataForm data;
    final StreamInitiation this$0;

    public DataForm getData()
    {
        return data;
    }

    public String getElementName()
    {
        return "feature";
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/feature-neg";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<feature xmlns=\"http://jabber.org/protocol/feature-neg\">");
        stringbuilder.append(data.toXML());
        stringbuilder.append("</feature>");
        return stringbuilder.toString();
    }

    public (DataForm dataform)
    {
        this$0 = StreamInitiation.this;
        super();
        data = dataform;
    }
}
