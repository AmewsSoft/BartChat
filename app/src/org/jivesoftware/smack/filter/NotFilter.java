// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.Objects;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter

public class NotFilter
    implements StanzaFilter
{

    private final StanzaFilter filter;

    public NotFilter(StanzaFilter stanzafilter)
    {
        filter = (StanzaFilter)Objects.requireNonNull(stanzafilter, "Parameter must not be null.");
    }

    public boolean accept(Stanza stanza)
    {
        return !filter.accept(stanza);
    }
}
