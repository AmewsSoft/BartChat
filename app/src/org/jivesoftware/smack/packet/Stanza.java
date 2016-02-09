// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.jivesoftware.smack.packet.id.StanzaIdUtil;
import org.jivesoftware.smack.util.MultiMap;
import org.jivesoftware.smack.util.PacketUtil;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smack.packet:
//            TopLevelStreamElement, Packet, ExtensionElement, XMPPError

public abstract class Stanza
    implements TopLevelStreamElement, Packet
{

    protected static final String DEFAULT_LANGUAGE;
    public static final String ITEM = "item";
    public static final String TEXT = "text";
    private XMPPError error;
    private String from;
    private String id;
    protected String language;
    private final MultiMap packetExtensions;
    private String to;

    protected Stanza()
    {
        this(StanzaIdUtil.newStanzaId());
    }

    protected Stanza(String s)
    {
        packetExtensions = new MultiMap();
        id = null;
        to = null;
        from = null;
        error = null;
        setStanzaId(s);
    }

    protected Stanza(Stanza stanza)
    {
        packetExtensions = new MultiMap();
        id = null;
        to = null;
        from = null;
        error = null;
        id = stanza.getStanzaId();
        to = stanza.getTo();
        from = stanza.getFrom();
        error = stanza.error;
        for (stanza = stanza.getExtensions().iterator(); stanza.hasNext(); addExtension((ExtensionElement)stanza.next())) { }
    }

    public static String getDefaultLanguage()
    {
        return DEFAULT_LANGUAGE;
    }

    protected void addCommonAttributes(XmlStringBuilder xmlstringbuilder)
    {
        xmlstringbuilder.optAttribute("to", getTo());
        xmlstringbuilder.optAttribute("from", getFrom());
        xmlstringbuilder.optAttribute("id", getStanzaId());
        xmlstringbuilder.xmllangAttribute(getLanguage());
    }

    public void addExtension(ExtensionElement extensionelement)
    {
        if (extensionelement == null)
        {
            return;
        }
        String s = XmppStringUtils.generateKey(extensionelement.getElementName(), extensionelement.getNamespace());
        synchronized (packetExtensions)
        {
            packetExtensions.put(s, extensionelement);
        }
        return;
        extensionelement;
        multimap;
        JVM INSTR monitorexit ;
        throw extensionelement;
    }

    public void addExtensions(Collection collection)
    {
        if (collection != null)
        {
            collection = collection.iterator();
            while (collection.hasNext()) 
            {
                addExtension((ExtensionElement)collection.next());
            }
        }
    }

    protected void appendErrorIfExists(XmlStringBuilder xmlstringbuilder)
    {
        XMPPError xmpperror = getError();
        if (xmpperror != null)
        {
            xmlstringbuilder.append(xmpperror.toXML());
        }
    }

    public XMPPError getError()
    {
        return error;
    }

    public ExtensionElement getExtension(String s)
    {
        return PacketUtil.extensionElementFrom(getExtensions(), null, s);
    }

    public ExtensionElement getExtension(String s, String s1)
    {
        if (s1 != null) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        return s;
_L2:
        s1 = XmppStringUtils.generateKey(s, s1);
        synchronized (packetExtensions)
        {
            s1 = (ExtensionElement)packetExtensions.getFirst(s1);
        }
        s = s1;
        if (s1 != null) goto _L4; else goto _L3
_L3:
        return null;
        s1;
        s;
        JVM INSTR monitorexit ;
        throw s1;
    }

    public List getExtensions()
    {
        List list;
        synchronized (packetExtensions)
        {
            list = packetExtensions.values();
        }
        return list;
        exception;
        multimap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Set getExtensions(String s, String s1)
    {
        StringUtils.requireNotNullOrEmpty(s, "elementName must not be null or empty");
        StringUtils.requireNotNullOrEmpty(s1, "namespace must not be null or empty");
        s = XmppStringUtils.generateKey(s, s1);
        return packetExtensions.getAll(s);
    }

    protected final XmlStringBuilder getExtensionsXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        for (Iterator iterator = getExtensions().iterator(); iterator.hasNext(); xmlstringbuilder.append(((ExtensionElement)iterator.next()).toXML())) { }
        return xmlstringbuilder;
    }

    public String getFrom()
    {
        return from;
    }

    public String getLanguage()
    {
        return language;
    }

    public String getPacketID()
    {
        return getStanzaId();
    }

    public String getStanzaId()
    {
        return id;
    }

    public String getTo()
    {
        return to;
    }

    public boolean hasExtension(String s)
    {
label0:
        {
            synchronized (packetExtensions)
            {
                Iterator iterator = packetExtensions.values().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break label0;
                    }
                } while (!((ExtensionElement)iterator.next()).getNamespace().equals(s));
            }
            return true;
        }
        multimap;
        JVM INSTR monitorexit ;
        return false;
        s;
        multimap;
        JVM INSTR monitorexit ;
        throw s;
    }

    public boolean hasExtension(String s, String s1)
    {
        if (s == null)
        {
            return hasExtension(s1);
        }
        s1 = XmppStringUtils.generateKey(s, s1);
        boolean flag;
        synchronized (packetExtensions)
        {
            flag = packetExtensions.containsKey(s1);
        }
        return flag;
        s1;
        s;
        JVM INSTR monitorexit ;
        throw s1;
    }

    public boolean hasStanzaIdSet()
    {
        return id != null;
    }

    public ExtensionElement removeExtension(String s, String s1)
    {
        s1 = XmppStringUtils.generateKey(s, s1);
        synchronized (packetExtensions)
        {
            s1 = (ExtensionElement)packetExtensions.remove(s1);
        }
        return s1;
        s1;
        s;
        JVM INSTR monitorexit ;
        throw s1;
    }

    public ExtensionElement removeExtension(ExtensionElement extensionelement)
    {
        return removeExtension(extensionelement.getElementName(), extensionelement.getNamespace());
    }

    public void setError(XMPPError xmpperror)
    {
        error = xmpperror;
    }

    public void setFrom(String s)
    {
        from = s;
    }

    public void setLanguage(String s)
    {
        language = s;
    }

    public void setPacketID(String s)
    {
        setStanzaId(s);
    }

    public void setStanzaId(String s)
    {
        if (s != null)
        {
            StringUtils.requireNotNullOrEmpty(s, "id must either be null or not the empty String");
        }
        id = s;
    }

    public void setTo(String s)
    {
        to = s;
    }

    public String toString()
    {
        return toXML().toString();
    }

    static 
    {
        DEFAULT_LANGUAGE = Locale.getDefault().getLanguage().toLowerCase(Locale.US);
    }
}
