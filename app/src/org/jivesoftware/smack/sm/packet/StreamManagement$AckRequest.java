// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.packet;

import org.jivesoftware.smack.packet.FullStreamElement;

// Referenced classes of package org.jivesoftware.smack.sm.packet:
//            StreamManagement

public static class  extends FullStreamElement
{

    public static final String ELEMENT = "r";
    public static final  INSTANCE = new <init>();

    public String getElementName()
    {
        return "r";
    }

    public String getNamespace()
    {
        return "urn:xmpp:sm:3";
    }

    public CharSequence toXML()
    {
        return "<r xmlns='urn:xmpp:sm:3'/>";
    }


    private ()
    {
    }
}
