// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smack.filter:
//            StanzaFilter, AndFilter, OrFilter, IQTypeFilter, 
//            StanzaIdFilter, FromMatchesFilter

public class IQReplyFilter
    implements StanzaFilter
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/filter/IQReplyFilter.getName());
    private final OrFilter fromFilter = new OrFilter();
    private final StanzaFilter iqAndIdFilter;
    private final String local;
    private final String packetId;
    private final String server;
    private final String to;

    public IQReplyFilter(IQ iq, XMPPConnection xmppconnection)
    {
        if (!iq.isRequestIQ())
        {
            throw new IllegalArgumentException("IQ must be a request IQ, i.e. of type 'get' or 'set'.");
        }
        String s;
        if (iq.getTo() != null)
        {
            to = iq.getTo().toLowerCase(Locale.US);
        } else
        {
            to = null;
        }
        s = xmppconnection.getUser();
        if (s == null)
        {
            throw new IllegalArgumentException("Must have a local (user) JID set. Either you didn't configure one or you where not connected at least once");
        }
        local = s.toLowerCase(Locale.US);
        server = xmppconnection.getServiceName().toLowerCase(Locale.US);
        packetId = iq.getStanzaId();
        iqAndIdFilter = new AndFilter(new StanzaFilter[] {
            new OrFilter(new StanzaFilter[] {
                IQTypeFilter.ERROR, IQTypeFilter.RESULT
            }), new StanzaIdFilter(iq)
        });
        fromFilter.addFilter(FromMatchesFilter.createFull(to));
        if (to == null)
        {
            fromFilter.addFilter(FromMatchesFilter.createBare(local));
            fromFilter.addFilter(FromMatchesFilter.createFull(server));
        } else
        if (to.equals(XmppStringUtils.parseBareJid(local)))
        {
            fromFilter.addFilter(FromMatchesFilter.createFull(null));
            return;
        }
    }

    public boolean accept(Stanza stanza)
    {
        if (!iqAndIdFilter.accept(stanza))
        {
            return false;
        }
        if (fromFilter.accept(stanza))
        {
            return true;
        } else
        {
            String s = String.format("Rejected potentially spoofed reply to IQ-packet. Filter settings: packetId=%s, to=%s, local=%s, server=%s. Received packet with from=%s", new Object[] {
                packetId, to, local, server, stanza.getFrom()
            });
            LOGGER.log(Level.WARNING, s, stanza);
            return false;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getClass().getSimpleName());
        stringbuilder.append(": iqAndIdFilter (").append(iqAndIdFilter.toString()).append("), ");
        stringbuilder.append(": fromFilter (").append(fromFilter.toString()).append(')');
        return stringbuilder.toString();
    }

}
