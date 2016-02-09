// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import java.lang.reflect.ParameterizedType;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.Objects;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter

public abstract class FlexibleStanzaTypeFilter
    implements StanzaFilter
{

    protected final Class stanzaType;

    public FlexibleStanzaTypeFilter()
    {
        stanzaType = (Class)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public FlexibleStanzaTypeFilter(Class class1)
    {
        stanzaType = (Class)Objects.requireNonNull(class1, "Type must not be null");
    }

    public final boolean accept(Stanza stanza)
    {
        if (stanzaType.isInstance(stanza))
        {
            return acceptSpecific(stanza);
        } else
        {
            return false;
        }
    }

    protected abstract boolean acceptSpecific(Stanza stanza);

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append(stanzaType.toString()).toString();
    }
}
