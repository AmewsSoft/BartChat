// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.roster.packet.RosterPacket;

// Referenced classes of package org.jivesoftware.smack.roster:
//            RosterGroup, Roster

public class RosterEntry
{

    private final XMPPConnection connection;
    private String name;
    private final Roster roster;
    private org.jivesoftware.smack.roster.packet.RosterPacket.ItemStatus status;
    private org.jivesoftware.smack.roster.packet.RosterPacket.ItemType type;
    private final String user;

    RosterEntry(String s, String s1, org.jivesoftware.smack.roster.packet.RosterPacket.ItemType itemtype, org.jivesoftware.smack.roster.packet.RosterPacket.ItemStatus itemstatus, Roster roster1, XMPPConnection xmppconnection)
    {
        user = s;
        name = s1;
        type = itemtype;
        status = itemstatus;
        roster = roster1;
        connection = xmppconnection;
    }

    static org.jivesoftware.smack.roster.packet.RosterPacket.Item toRosterItem(RosterEntry rosterentry)
    {
        org.jivesoftware.smack.roster.packet.RosterPacket.Item item = new org.jivesoftware.smack.roster.packet.RosterPacket.Item(rosterentry.getUser(), rosterentry.getName());
        item.setItemType(rosterentry.getType());
        item.setItemStatus(rosterentry.getStatus());
        for (rosterentry = rosterentry.getGroups().iterator(); rosterentry.hasNext(); item.addGroupName(((RosterGroup)rosterentry.next()).getName())) { }
        return item;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj != null && (obj instanceof RosterEntry))
        {
            return user.equals(((RosterEntry)obj).getUser());
        } else
        {
            return false;
        }
    }

    public boolean equalsDeep(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        obj = (RosterEntry)obj;
        if (name == null)
        {
            if (((RosterEntry) (obj)).name != null)
            {
                return false;
            }
        } else
        if (!name.equals(((RosterEntry) (obj)).name))
        {
            return false;
        }
        if (status == null)
        {
            if (((RosterEntry) (obj)).status != null)
            {
                return false;
            }
        } else
        if (!status.equals(((RosterEntry) (obj)).status))
        {
            return false;
        }
        if (type == null)
        {
            if (((RosterEntry) (obj)).type != null)
            {
                return false;
            }
        } else
        if (!type.equals(((RosterEntry) (obj)).type))
        {
            return false;
        }
        if (user != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (((RosterEntry) (obj)).user == null) goto _L1; else goto _L3
_L3:
        return false;
        if (user.equals(((RosterEntry) (obj)).user)) goto _L1; else goto _L4
_L4:
        return false;
    }

    public List getGroups()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = roster.getGroups().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            RosterGroup rostergroup = (RosterGroup)iterator.next();
            if (rostergroup.contains(this))
            {
                arraylist.add(rostergroup);
            }
        } while (true);
        return arraylist;
    }

    public String getName()
    {
        return name;
    }

    public org.jivesoftware.smack.roster.packet.RosterPacket.ItemStatus getStatus()
    {
        return status;
    }

    public org.jivesoftware.smack.roster.packet.RosterPacket.ItemType getType()
    {
        return type;
    }

    public String getUser()
    {
        return user;
    }

    public int hashCode()
    {
        if (user == null)
        {
            return 0;
        } else
        {
            return user.hashCode();
        }
    }

    public void setName(String s)
        throws org.jivesoftware.smack.SmackException.NotConnectedException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException
    {
        if (s != null && s.equals(name))
        {
            return;
        } else
        {
            RosterPacket rosterpacket = new RosterPacket();
            rosterpacket.setType(org.jivesoftware.smack.packet.IQ.Type.set);
            rosterpacket.addRosterItem(toRosterItem(this));
            connection.createPacketCollectorAndSend(rosterpacket).nextResultOrThrow();
            name = s;
            return;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (name != null)
        {
            stringbuilder.append(name).append(": ");
        }
        stringbuilder.append(user);
        Object obj = getGroups();
        if (!((Collection) (obj)).isEmpty())
        {
            stringbuilder.append(" [");
            obj = ((Collection) (obj)).iterator();
            stringbuilder.append(((RosterGroup)((Iterator) (obj)).next()).getName());
            for (; ((Iterator) (obj)).hasNext(); stringbuilder.append(((RosterGroup)((Iterator) (obj)).next()).getName()))
            {
                stringbuilder.append(", ");
            }

            stringbuilder.append("]");
        }
        return stringbuilder.toString();
    }

    void updateState(String s, org.jivesoftware.smack.roster.packet.RosterPacket.ItemType itemtype, org.jivesoftware.smack.roster.packet.RosterPacket.ItemStatus itemstatus)
    {
        name = s;
        type = itemtype;
        status = itemstatus;
    }
}
