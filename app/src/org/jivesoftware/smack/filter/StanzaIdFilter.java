// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter

public class StanzaIdFilter
    implements StanzaFilter
{

    private final String stanzaId;

    public StanzaIdFilter(String s)
    {
        stanzaId = (String)StringUtils.requireNotNullOrEmpty(s, "Stanza ID must not be null or empty.");
    }

    public StanzaIdFilter(Stanza stanza)
    {
        this(stanza.getStanzaId());
    }

    public boolean accept(Stanza stanza)
    {
        return stanzaId.equals(stanza.getStanzaId());
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(": id=").append(stanzaId).toString();
    }
}
