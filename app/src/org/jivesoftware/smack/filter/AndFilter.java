// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.filter:
//            AbstractListFilter, StanzaFilter

public class AndFilter extends AbstractListFilter
    implements StanzaFilter
{

    public AndFilter()
    {
    }

    public transient AndFilter(StanzaFilter astanzafilter[])
    {
        super(astanzafilter);
    }

    public boolean accept(Stanza stanza)
    {
        for (Iterator iterator = filters.iterator(); iterator.hasNext();)
        {
            if (!((StanzaFilter)iterator.next()).accept(stanza))
            {
                return false;
            }
        }

        return true;
    }
}
