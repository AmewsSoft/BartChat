// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import java.util.Locale;
import org.jivesoftware.smack.packet.Stanza;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter

public class FromMatchesFilter
    implements StanzaFilter
{

    private final String address;
    private final boolean matchBareJID;

    public FromMatchesFilter(String s, boolean flag)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            s = s.toLowerCase(Locale.US);
        }
        address = s;
        matchBareJID = flag;
    }

    public static FromMatchesFilter create(String s)
    {
        return new FromMatchesFilter(s, "".equals(XmppStringUtils.parseResource(s)));
    }

    public static FromMatchesFilter createBare(String s)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            s = XmppStringUtils.parseBareJid(s);
        }
        return new FromMatchesFilter(s, true);
    }

    public static FromMatchesFilter createFull(String s)
    {
        return new FromMatchesFilter(s, false);
    }

    public boolean accept(Stanza stanza)
    {
        stanza = stanza.getFrom();
        if (stanza == null)
        {
            return address == null;
        }
        String s = stanza.toLowerCase(Locale.US);
        stanza = s;
        if (matchBareJID)
        {
            stanza = XmppStringUtils.parseBareJid(s);
        }
        return stanza.equals(address);
    }

    public String toString()
    {
        String s;
        if (matchBareJID)
        {
            s = "bare";
        } else
        {
            s = "full";
        }
        return (new StringBuilder()).append(getClass().getSimpleName()).append(" (").append(s).append("): ").append(address).toString();
    }
}
