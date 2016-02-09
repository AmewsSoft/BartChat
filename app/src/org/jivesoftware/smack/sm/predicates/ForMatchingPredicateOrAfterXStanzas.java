// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.sm.predicates:
//            AfterXStanzas

public class ForMatchingPredicateOrAfterXStanzas
    implements StanzaFilter
{

    private final AfterXStanzas afterXStanzas;
    private final StanzaFilter predicate;

    public ForMatchingPredicateOrAfterXStanzas(StanzaFilter stanzafilter, int i)
    {
        predicate = stanzafilter;
        afterXStanzas = new AfterXStanzas(i);
    }

    public boolean accept(Stanza stanza)
    {
        if (predicate.accept(stanza))
        {
            afterXStanzas.resetCounter();
            return true;
        } else
        {
            return afterXStanzas.accept(stanza);
        }
    }
}
