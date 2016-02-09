// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.sm.packet:
//            StreamManagement

public static class 
    implements ExtensionElement
{

    public static final String ELEMENT = "sm";
    public static final  INSTANCE = new <init>();

    public String getElementName()
    {
        return "sm";
    }

    public String getNamespace()
    {
        return "urn:xmpp:sm:3";
    }

    public CharSequence toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }


    private ()
    {
    }
}
