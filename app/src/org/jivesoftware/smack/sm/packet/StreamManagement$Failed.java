// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.packet;

import org.jivesoftware.smack.packet.FullStreamElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.sm.packet:
//            StreamManagement

public static class condition extends FullStreamElement
{

    public static final String ELEMENT = "failed";
    private org.jivesoftware.smack.packet.yElement condition;

    public String getElementName()
    {
        return "failed";
    }

    public String getNamespace()
    {
        return "urn:xmpp:sm:3";
    }

    public org.jivesoftware.smack.packet.ed getXMPPErrorCondition()
    {
        return condition;
    }

    public CharSequence toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        if (condition != null)
        {
            xmlstringbuilder.rightAngleBracket();
            xmlstringbuilder.append(condition.ring());
            xmlstringbuilder.xmlnsAttribute("urn:ietf:params:xml:ns:xmpp-stanzas");
            xmlstringbuilder.closeElement("failed");
            return xmlstringbuilder;
        } else
        {
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }
    }

    public ()
    {
    }

    public (org.jivesoftware.smack.packet.ed ed)
    {
        condition = ed;
    }
}
