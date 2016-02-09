// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import java.util.Locale;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter

public class ToFilter
    implements StanzaFilter
{

    private final String to;

    public ToFilter(String s)
    {
        to = s.toLowerCase(Locale.US);
    }

    public boolean accept(Stanza stanza)
    {
        stanza = stanza.getTo();
        if (stanza == null)
        {
            return false;
        } else
        {
            return stanza.toLowerCase(Locale.US).equals(to);
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(": to=").append(to).toString();
    }
}
