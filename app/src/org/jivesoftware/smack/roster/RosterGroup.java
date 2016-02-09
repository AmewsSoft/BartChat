// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smack.roster:
//            RosterEntry

public class RosterGroup
{

    private final XMPPConnection connection;
    private final Set entries = new LinkedHashSet();
    private final String name;

    RosterGroup(String s, XMPPConnection xmppconnection)
    {
        name = s;
        connection = xmppconnection;
    }

    public void addEntry(RosterEntry rosterentry)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj = null;
        synchronized (entries)
        {
            if (!entries.contains(rosterentry))
            {
                obj = new RosterPacket();
                ((RosterPacket) (obj)).setType(org.jivesoftware.smack.packet.IQ.Type.set);
                rosterentry = RosterEntry.toRosterItem(rosterentry);
                rosterentry.addGroupName(getName());
                ((RosterPacket) (obj)).addRosterItem(rosterentry);
                obj = connection.createPacketCollectorAndSend(((org.jivesoftware.smack.packet.IQ) (obj)));
            }
        }
        if (obj != null)
        {
            ((PacketCollector) (obj)).nextResultOrThrow();
        }
        return;
        rosterentry;
        set;
        JVM INSTR monitorexit ;
        throw rosterentry;
    }

    void addEntryLocal(RosterEntry rosterentry)
    {
        synchronized (entries)
        {
            entries.remove(rosterentry);
            entries.add(rosterentry);
        }
        return;
        rosterentry;
        set;
        JVM INSTR monitorexit ;
        throw rosterentry;
    }

    public boolean contains(String s)
    {
        return getEntry(s) != null;
    }

    public boolean contains(RosterEntry rosterentry)
    {
        boolean flag;
        synchronized (entries)
        {
            flag = entries.contains(rosterentry);
        }
        return flag;
        rosterentry;
        set;
        JVM INSTR monitorexit ;
        throw rosterentry;
    }

    public List getEntries()
    {
        ArrayList arraylist;
        synchronized (entries)
        {
            arraylist = new ArrayList(entries);
        }
        return arraylist;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public RosterEntry getEntry(String s)
    {
        if (s == null)
        {
            return null;
        }
        String s1 = XmppStringUtils.parseBareJid(s).toLowerCase(Locale.US);
        RosterEntry rosterentry;
        synchronized (entries)
        {
            Iterator iterator = entries.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break MISSING_BLOCK_LABEL_76;
                }
                rosterentry = (RosterEntry)iterator.next();
            } while (!rosterentry.getUser().equals(s1));
        }
        return rosterentry;
        exception;
        s;
        JVM INSTR monitorexit ;
        throw exception;
        s;
        JVM INSTR monitorexit ;
        return null;
    }

    public int getEntryCount()
    {
        int i;
        synchronized (entries)
        {
            i = entries.size();
        }
        return i;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String getName()
    {
        return name;
    }

    public void removeEntry(RosterEntry rosterentry)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Object obj = null;
        synchronized (entries)
        {
            if (entries.contains(rosterentry))
            {
                obj = new RosterPacket();
                ((RosterPacket) (obj)).setType(org.jivesoftware.smack.packet.IQ.Type.set);
                rosterentry = RosterEntry.toRosterItem(rosterentry);
                rosterentry.removeGroupName(getName());
                ((RosterPacket) (obj)).addRosterItem(rosterentry);
                obj = connection.createPacketCollectorAndSend(((org.jivesoftware.smack.packet.IQ) (obj)));
            }
        }
        if (obj != null)
        {
            ((PacketCollector) (obj)).nextResultOrThrow();
        }
        return;
        rosterentry;
        set;
        JVM INSTR monitorexit ;
        throw rosterentry;
    }

    void removeEntryLocal(RosterEntry rosterentry)
    {
        synchronized (entries)
        {
            if (entries.contains(rosterentry))
            {
                entries.remove(rosterentry);
            }
        }
        return;
        rosterentry;
        set;
        JVM INSTR monitorexit ;
        throw rosterentry;
    }

    public void setName(String s)
        throws org.jivesoftware.smack.SmackException.NotConnectedException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException
    {
        Set set = entries;
        set;
        JVM INSTR monitorenter ;
        RosterPacket rosterpacket;
        for (Iterator iterator = entries.iterator(); iterator.hasNext(); connection.createPacketCollectorAndSend(rosterpacket).nextResultOrThrow())
        {
            Object obj = (RosterEntry)iterator.next();
            rosterpacket = new RosterPacket();
            rosterpacket.setType(org.jivesoftware.smack.packet.IQ.Type.set);
            obj = RosterEntry.toRosterItem(((RosterEntry) (obj)));
            ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj)).removeGroupName(name);
            ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj)).addGroupName(s);
            rosterpacket.addRosterItem(((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj)));
        }

        break MISSING_BLOCK_LABEL_106;
        s;
        set;
        JVM INSTR monitorexit ;
        throw s;
        set;
        JVM INSTR monitorexit ;
    }
}
