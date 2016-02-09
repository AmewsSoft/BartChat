// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter

public class PacketExtensionFilter
    implements StanzaFilter
{

    private final String elementName;
    private final String namespace;

    public PacketExtensionFilter(String s)
    {
        this(null, s);
    }

    public PacketExtensionFilter(String s, String s1)
    {
        StringUtils.requireNotNullOrEmpty(s1, "namespace must not be null or empty");
        elementName = s;
        namespace = s1;
    }

    public PacketExtensionFilter(ExtensionElement extensionelement)
    {
        this(extensionelement.getElementName(), extensionelement.getNamespace());
    }

    public boolean accept(Stanza stanza)
    {
        return stanza.hasExtension(elementName, namespace);
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(": element=").append(elementName).append(" namespace=").append(namespace).toString();
    }
}
