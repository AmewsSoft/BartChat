// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.util.StringUtils;

public class OnceForThisStanza
    implements StanzaFilter
{

    private final XMPPTCPConnection connection;
    private final String id;

    private OnceForThisStanza(XMPPTCPConnection xmpptcpconnection, Stanza stanza)
    {
        connection = xmpptcpconnection;
        id = stanza.getStanzaId();
        if (StringUtils.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("Stanza ID must be set");
        } else
        {
            return;
        }
    }

    public static void setup(XMPPTCPConnection xmpptcpconnection, Stanza stanza)
    {
        xmpptcpconnection.addRequestAckPredicate(new OnceForThisStanza(xmpptcpconnection, stanza));
    }

    public boolean accept(Stanza stanza)
    {
        for (stanza = stanza.getStanzaId(); StringUtils.isNullOrEmpty(stanza) || !id.equals(stanza);)
        {
            return false;
        }

        connection.removeRequestAckPredicate(this);
        return true;
    }
}
