// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xhtmlim.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class XHTMLExtension
    implements ExtensionElement
{

    public static final String ELEMENT = "html";
    public static final String NAMESPACE = "http://jabber.org/protocol/xhtml-im";
    private List bodies;

    public XHTMLExtension()
    {
        bodies = new ArrayList();
    }

    public static XHTMLExtension from(Message message)
    {
        return (XHTMLExtension)message.getExtension("html", "http://jabber.org/protocol/xhtml-im");
    }

    public void addBody(CharSequence charsequence)
    {
        synchronized (bodies)
        {
            bodies.add(charsequence);
        }
        return;
        charsequence;
        list;
        JVM INSTR monitorexit ;
        throw charsequence;
    }

    public List getBodies()
    {
        List list1;
        synchronized (bodies)
        {
            list1 = Collections.unmodifiableList(new ArrayList(bodies));
        }
        return list1;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getBodiesCount()
    {
        int i;
        synchronized (bodies)
        {
            i = bodies.size();
        }
        return i;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String getElementName()
    {
        return "html";
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/xhtml-im";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        for (Iterator iterator = getBodies().iterator(); iterator.hasNext(); xmlstringbuilder.append((CharSequence)iterator.next())) { }
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
