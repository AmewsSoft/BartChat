// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm;

import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.sm:
//            StreamManagementException

public static class ackedStanzas extends StreamManagementException
{

    private static final long serialVersionUID = 1L;
    private final long ackedStanzaCount;
    private final List ackedStanzas;
    private final long handledCount;
    private final int outstandingStanzasCount;
    private final long previousServerHandledCount;

    public long getAckedStanzaCount()
    {
        return ackedStanzaCount;
    }

    public List getAckedStanzas()
    {
        return ackedStanzas;
    }

    public long getHandledCount()
    {
        return handledCount;
    }

    public int getOutstandingStanzasCount()
    {
        return outstandingStanzasCount;
    }

    public long getPreviousServerHandledCount()
    {
        return previousServerHandledCount;
    }

    public (long l, long l1, long l2, List list)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("There was an error regarding the Stream Mangement counters. Server reported ").append(l).append(" handled stanzas, which means that the ").append(l2).append(" recently send stanzas by client are now acked by the server. But Smack had only ").append(list.size()).append(" to acknowledge. The stanza id of the last acked outstanding stanza is ");
        String s;
        if (list.isEmpty())
        {
            s = "<no acked stanzas>";
        } else
        {
            s = ((Stanza)list.get(list.size() - 1)).getStanzaId();
        }
        super(stringbuilder.append(s).toString());
        handledCount = l;
        previousServerHandledCount = l1;
        ackedStanzaCount = l2;
        outstandingStanzasCount = list.size();
        ackedStanzas = Collections.unmodifiableList(list);
    }
}
