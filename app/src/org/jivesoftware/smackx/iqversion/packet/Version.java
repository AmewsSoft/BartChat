// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqversion.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;

public class Version extends IQ
{

    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:version";
    private final String name;
    private String os;
    private final String version;

    public Version()
    {
        super("query", "jabber:iq:version");
        name = null;
        version = null;
        setType(org.jivesoftware.smack.packet.IQ.Type.get);
    }

    public Version(String s)
    {
        this();
        setTo(s);
    }

    public Version(String s, String s1)
    {
        this(s, s1, null);
    }

    public Version(String s, String s1, String s2)
    {
        super("query", "jabber:iq:version");
        setType(org.jivesoftware.smack.packet.IQ.Type.result);
        name = (String)StringUtils.requireNotNullOrEmpty(s, "name must not be null");
        version = (String)StringUtils.requireNotNullOrEmpty(s1, "version must not be null");
        os = s2;
    }

    public Version(Version version1)
    {
        this(version1.name, version1.version, version1.os);
    }

    public static Version createResultFor(Stanza stanza, Version version1)
    {
        version1 = new Version(version1);
        version1.setStanzaId(stanza.getStanzaId());
        version1.setTo(stanza.getFrom());
        return version1;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        iqchildelementxmlstringbuilder.optElement("name", name);
        iqchildelementxmlstringbuilder.optElement("version", version);
        iqchildelementxmlstringbuilder.optElement("os", os);
        return iqchildelementxmlstringbuilder;
    }

    public String getName()
    {
        return name;
    }

    public String getOs()
    {
        return os;
    }

    public String getVersion()
    {
        return version;
    }

    public void setOs(String s)
    {
        os = s;
    }
}
