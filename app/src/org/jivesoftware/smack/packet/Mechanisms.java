// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            ExtensionElement

public class Mechanisms
    implements ExtensionElement
{

    public static final String ELEMENT = "mechanisms";
    public static final String NAMESPACE = "urn:ietf:params:xml:ns:xmpp-sasl";
    public final List mechanisms;

    public Mechanisms(String s)
    {
        mechanisms = new LinkedList();
        mechanisms.add(s);
    }

    public Mechanisms(Collection collection)
    {
        mechanisms = new LinkedList();
        mechanisms.addAll(collection);
    }

    public String getElementName()
    {
        return "mechanisms";
    }

    public List getMechanisms()
    {
        return Collections.unmodifiableList(mechanisms);
    }

    public String getNamespace()
    {
        return "urn:ietf:params:xml:ns:xmpp-sasl";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        for (Iterator iterator = mechanisms.iterator(); iterator.hasNext(); xmlstringbuilder.element("mechanism", (String)iterator.next())) { }
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
