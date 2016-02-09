// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.jivesoftware.smack.roster.rosterstore.RosterStore;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smack.roster:
//            Roster, RosterEntry

private class <init> extends AbstractIqRequestHandler
{

    final Roster this$0;

    public IQ handleIQRequest(IQ iq)
    {
        RosterPacket rosterpacket;
        Object obj;
        Object obj1;
        Object obj2;
        ArrayList arraylist;
        Object obj3;
        String s;
        obj = Roster.access$2000(Roster.this);
        rosterpacket = (RosterPacket)iq;
        obj1 = XmppStringUtils.parseBareJid(((XMPPConnection) (obj)).getUser());
        obj2 = rosterpacket.getFrom();
        if (obj2 != null && !((String) (obj2)).equals(obj1))
        {
            Roster.access$200().warning((new StringBuilder()).append("Ignoring roster push with a non matching 'from' ourJid='").append(((String) (obj1))).append("' from='").append(((String) (obj2))).append("'").toString());
            return IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.e_unavailable));
        }
        obj3 = rosterpacket.getRosterItems();
        if (((Collection) (obj3)).size() != 1)
        {
            Roster.access$200().warning((new StringBuilder()).append("Ignoring roster push with not exaclty one entry. size=").append(((Collection) (obj3)).size()).toString());
            return IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.quest));
        }
        iq = new ArrayList();
        obj1 = new ArrayList();
        obj2 = new ArrayList();
        arraylist = new ArrayList();
        obj3 = (org.jivesoftware.smack.roster.packet.s)((Collection) (obj3)).iterator().next();
        obj = new RosterEntry(((org.jivesoftware.smack.roster.packet.s) (obj3)).s(), ((org.jivesoftware.smack.roster.packet.s) (obj3)).s(), ((org.jivesoftware.smack.roster.packet.s) (obj3)).ype(), ((org.jivesoftware.smack.roster.packet.ype) (obj3)).tatus(), Roster.this, ((XMPPConnection) (obj)));
        s = rosterpacket.getVersion();
        if (!((org.jivesoftware.smack.roster.packet.tatus) (obj3)).ype().ls(org.jivesoftware.smack.roster.packet.ve)) goto _L2; else goto _L1
_L1:
        Roster.access$1400(Roster.this, ((Collection) (obj2)), ((RosterEntry) (obj)));
        if (Roster.access$1500(Roster.this) != null)
        {
            Roster.access$1500(Roster.this).removeEntry(((RosterEntry) (obj)).getUser(), s);
        }
_L4:
        Roster.access$1600(Roster.this);
        Roster.access$1800(Roster.this, iq, ((Collection) (obj1)), ((Collection) (obj2)));
        return IQ.createResultIQ(rosterpacket);
_L2:
        if (Roster.access$1200(((org.jivesoftware.smack.roster.packet.ry) (obj3))))
        {
            Roster.access$1300(Roster.this, iq, ((Collection) (obj1)), arraylist, ((org.jivesoftware.smack.roster.packet.ry) (obj3)), ((RosterEntry) (obj)));
            if (Roster.access$1500(Roster.this) != null)
            {
                Roster.access$1500(Roster.this).addEntry(((org.jivesoftware.smack.roster.packet.ry) (obj3)), s);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private pe()
    {
        this$0 = Roster.this;
        super("query", "jabber:iq:roster", org.jivesoftware.smack.packet.r, org.jivesoftware.smack.iqrequest.);
    }

    ync(ync ync)
    {
        this();
    }
}
