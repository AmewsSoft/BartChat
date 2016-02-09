// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.packet;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.muc.MUCAffiliation;
import org.jivesoftware.smackx.muc.MUCRole;

public class MUCItem
    implements NamedElement
{

    public static final String ELEMENT = "item";
    private final String actor;
    private final MUCAffiliation affiliation;
    private final String jid;
    private final String nick;
    private final String reason;
    private final MUCRole role;

    public MUCItem(MUCAffiliation mucaffiliation)
    {
        this(mucaffiliation, null, null, null, null, null);
    }

    public MUCItem(MUCAffiliation mucaffiliation, String s)
    {
        this(mucaffiliation, null, null, null, s, null);
    }

    public MUCItem(MUCAffiliation mucaffiliation, String s, String s1)
    {
        this(mucaffiliation, null, null, s1, s, null);
    }

    public MUCItem(MUCAffiliation mucaffiliation, MUCRole mucrole, String s, String s1, String s2, String s3)
    {
        affiliation = mucaffiliation;
        role = mucrole;
        actor = s;
        reason = s1;
        jid = s2;
        nick = s3;
    }

    public MUCItem(MUCRole mucrole)
    {
        this(null, mucrole, null, null, null, null);
    }

    public MUCItem(MUCRole mucrole, String s)
    {
        this(null, mucrole, null, null, null, s);
    }

    public MUCItem(MUCRole mucrole, String s, String s1)
    {
        this(null, mucrole, null, s1, null, s);
    }

    public String getActor()
    {
        return actor;
    }

    public MUCAffiliation getAffiliation()
    {
        return affiliation;
    }

    public String getElementName()
    {
        return "item";
    }

    public String getJid()
    {
        return jid;
    }

    public String getNick()
    {
        return nick;
    }

    public String getReason()
    {
        return reason;
    }

    public MUCRole getRole()
    {
        return role;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.optAttribute("affiliation", getAffiliation());
        xmlstringbuilder.optAttribute("jid", getJid());
        xmlstringbuilder.optAttribute("nick", getNick());
        xmlstringbuilder.optAttribute("role", getRole());
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optElement("reason", getReason());
        if (getActor() != null)
        {
            xmlstringbuilder.halfOpenElement("actor").attribute("jid", getActor()).closeEmptyElement();
        }
        xmlstringbuilder.closeElement("item");
        return xmlstringbuilder;
    }
}
