// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.packet;

import java.util.Date;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jxmpp.util.XmppDateTime;

public class MUCInitialPresence
    implements ExtensionElement
{
    public static class History
        implements NamedElement
    {

        public static final String ELEMENT = "history";
        private int maxChars;
        private int maxStanzas;
        private int seconds;
        private Date since;

        public String getElementName()
        {
            return "history";
        }

        public int getMaxChars()
        {
            return maxChars;
        }

        public int getMaxStanzas()
        {
            return maxStanzas;
        }

        public int getSeconds()
        {
            return seconds;
        }

        public Date getSince()
        {
            return since;
        }

        public void setMaxChars(int i)
        {
            maxChars = i;
        }

        public void setMaxStanzas(int i)
        {
            maxStanzas = i;
        }

        public void setSeconds(int i)
        {
            seconds = i;
        }

        public void setSince(Date date)
        {
            since = date;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.optIntAttribute("maxchars", getMaxChars());
            xmlstringbuilder.optIntAttribute("maxstanzas", getMaxStanzas());
            xmlstringbuilder.optIntAttribute("seconds", getSeconds());
            if (getSince() != null)
            {
                xmlstringbuilder.attribute("since", XmppDateTime.formatXEP0082Date(getSince()));
            }
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }

        public History()
        {
            maxChars = -1;
            maxStanzas = -1;
            seconds = -1;
        }
    }


    public static final String ELEMENT = "x";
    public static final String NAMESPACE = "http://jabber.org/protocol/muc";
    private History history;
    private String password;

    public MUCInitialPresence()
    {
    }

    public static MUCInitialPresence from(Stanza stanza)
    {
        return (MUCInitialPresence)stanza.getExtension("x", "http://jabber.org/protocol/muc");
    }

    public static MUCInitialPresence getFrom(Stanza stanza)
    {
        return from(stanza);
    }

    public String getElementName()
    {
        return "x";
    }

    public History getHistory()
    {
        return history;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/muc";
    }

    public String getPassword()
    {
        return password;
    }

    public void setHistory(History history1)
    {
        history = history1;
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optElement("password", getPassword());
        xmlstringbuilder.optElement(getHistory());
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
