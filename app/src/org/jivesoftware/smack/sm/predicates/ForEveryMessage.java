// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

public class ForEveryMessage
    implements StanzaFilter
{

    public static final ForEveryMessage INSTANCE = new ForEveryMessage();

    private ForEveryMessage()
    {
    }

    public boolean accept(Stanza stanza)
    {
        return stanza instanceof Message;
    }

}
