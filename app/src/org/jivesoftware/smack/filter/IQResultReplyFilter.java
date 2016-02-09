// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.filter:
//            IQReplyFilter, IQTypeFilter, StanzaFilter

public class IQResultReplyFilter extends IQReplyFilter
{

    public IQResultReplyFilter(IQ iq, XMPPConnection xmppconnection)
    {
        super(iq, xmppconnection);
    }

    public boolean accept(Stanza stanza)
    {
        if (!super.accept(stanza))
        {
            return false;
        } else
        {
            return IQTypeFilter.RESULT.accept(stanza);
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getClass().getSimpleName());
        stringbuilder.append((new StringBuilder()).append(" (").append(super.toString()).append(')').toString());
        return stringbuilder.toString();
    }
}
