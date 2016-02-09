// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

public class AfterXStanzas
    implements StanzaFilter
{

    final int count;
    int currentCount;

    public AfterXStanzas(int i)
    {
        count = i;
        currentCount = 0;
    }

    public boolean accept(Stanza stanza)
    {
        this;
        JVM INSTR monitorenter ;
        currentCount = currentCount + 1;
        if (currentCount != count) goto _L2; else goto _L1
_L1:
        resetCounter();
        boolean flag = true;
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
        stanza;
        throw stanza;
    }

    public void resetCounter()
    {
        this;
        JVM INSTR monitorenter ;
        currentCount = 0;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }
}
