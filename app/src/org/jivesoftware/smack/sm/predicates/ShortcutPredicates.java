// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.predicates;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

public class ShortcutPredicates
    implements StanzaFilter
{

    private final Set predicates;

    public ShortcutPredicates()
    {
        predicates = new LinkedHashSet();
    }

    public ShortcutPredicates(Collection collection)
    {
        predicates = new LinkedHashSet();
        predicates.addAll(collection);
    }

    public boolean accept(Stanza stanza)
    {
        for (Iterator iterator = predicates.iterator(); iterator.hasNext();)
        {
            if (((StanzaFilter)iterator.next()).accept(stanza))
            {
                return true;
            }
        }

        return false;
    }

    public boolean addPredicate(StanzaFilter stanzafilter)
    {
        return predicates.add(stanzafilter);
    }

    public boolean removePredicate(StanzaFilter stanzafilter)
    {
        return predicates.remove(stanzafilter);
    }
}
