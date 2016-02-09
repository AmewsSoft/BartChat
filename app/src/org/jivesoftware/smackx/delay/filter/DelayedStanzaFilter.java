// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.delay.filter;

import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.delay.DelayInformationManager;

public class DelayedStanzaFilter
    implements StanzaFilter
{

    public static final StanzaFilter INSTANCE;
    public static final StanzaFilter NOT_DELAYED_STANZA;

    private DelayedStanzaFilter()
    {
    }

    public boolean accept(Stanza stanza)
    {
        return DelayInformationManager.isDelayedStanza(stanza);
    }

    static 
    {
        INSTANCE = new DelayedStanzaFilter();
        NOT_DELAYED_STANZA = new NotFilter(INSTANCE);
    }
}
