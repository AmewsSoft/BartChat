// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.util.Objects;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter

public abstract class AbstractListFilter
    implements StanzaFilter
{

    protected final List filters;

    protected AbstractListFilter()
    {
        filters = new ArrayList();
    }

    protected transient AbstractListFilter(StanzaFilter astanzafilter[])
    {
        Objects.requireNonNull(astanzafilter, "Parameter must not be null.");
        int j = astanzafilter.length;
        for (int i = 0; i < j; i++)
        {
            Objects.requireNonNull(astanzafilter[i], "Parameter must not be null.");
        }

        filters = new ArrayList(Arrays.asList(astanzafilter));
    }

    public void addFilter(StanzaFilter stanzafilter)
    {
        Objects.requireNonNull(stanzafilter, "Parameter must not be null.");
        filters.add(stanzafilter);
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getClass().getSimpleName());
        stringbuilder.append(": (");
        Iterator iterator = filters.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            stringbuilder.append(((StanzaFilter)iterator.next()).toString());
            if (iterator.hasNext())
            {
                stringbuilder.append(", ");
            }
        } while (true);
        stringbuilder.append(")");
        return stringbuilder.toString();
    }
}
