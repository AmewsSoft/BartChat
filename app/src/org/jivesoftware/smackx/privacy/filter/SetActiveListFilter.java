// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.privacy.filter;

import org.jivesoftware.smack.filter.FlexibleStanzaTypeFilter;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.privacy.packet.Privacy;

public class SetActiveListFilter extends FlexibleStanzaTypeFilter
{

    public static final SetActiveListFilter INSTANCE = new SetActiveListFilter();

    private SetActiveListFilter()
    {
    }

    protected volatile boolean acceptSpecific(Stanza stanza)
    {
        return acceptSpecific((Privacy)stanza);
    }

    protected boolean acceptSpecific(Privacy privacy)
    {
        while (privacy.getType() != org.jivesoftware.smack.packet.IQ.Type.set || privacy.getActiveName() == null && !privacy.isDeclineActiveList()) 
        {
            return false;
        }
        return true;
    }

}
