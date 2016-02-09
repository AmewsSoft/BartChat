// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;

// Referenced classes of package org.jivesoftware.smack.sm.predicates:
//            AfterXStanzas, ForMatchingPredicateOrAfterXStanzas, ForEveryMessage

public class Predicate
{

    public Predicate()
    {
    }

    public static AfterXStanzas after5Stanzas()
    {
        return new AfterXStanzas(5);
    }

    public static StanzaFilter forMessagesOrAfter5Stanzas()
    {
        return new ForMatchingPredicateOrAfterXStanzas(ForEveryMessage.INSTANCE, 5);
    }
}
