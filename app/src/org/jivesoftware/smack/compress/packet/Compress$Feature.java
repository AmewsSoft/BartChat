// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.compress.packet;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.compress.packet:
//            Compress

public static class methods
    implements ExtensionElement
{

    public static final String ELEMENT = "compression";
    public final List methods;

    public String getElementName()
    {
        return "compression";
    }

    public List getMethods()
    {
        return Collections.unmodifiableList(methods);
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/compress";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        for (Iterator iterator = methods.iterator(); iterator.hasNext(); xmlstringbuilder.element("method", (String)iterator.next())) { }
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }

    public (List list)
    {
        methods = list;
    }
}
