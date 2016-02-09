// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;


// Referenced classes of package org.jivesoftware.smack.packet:
//            ExtensionElement, Bind

public static class ent
    implements ExtensionElement
{

    public static final toXML INSTANCE = new <init>();

    public String getElementName()
    {
        return "bind";
    }

    public String getNamespace()
    {
        return "urn:ietf:params:xml:ns:xmpp-bind";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        return "<bind xmlns='urn:ietf:params:xml:ns:xmpp-bind'/>";
    }


    private ent()
    {
    }
}
