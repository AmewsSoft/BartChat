// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqregister.packet;

import org.jivesoftware.smack.packet.ExtensionElement;

// Referenced classes of package org.jivesoftware.smackx.iqregister.packet:
//            Registration

public static class 
    implements ExtensionElement
{

    public static final String ELEMENT = "register";
    public static final  INSTANCE = new <init>();
    public static final String NAMESPACE = "http://jabber.org/features/iq-register";

    public String getElementName()
    {
        return "register";
    }

    public String getNamespace()
    {
        return "http://jabber.org/features/iq-register";
    }

    public CharSequence toXML()
    {
        return "<register xmlns='http://jabber.org/features/iq-register'/>";
    }


    private ()
    {
    }
}
