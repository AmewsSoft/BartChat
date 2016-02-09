// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class RosterPacket extends IQ
{
    public static class Item
    {

        public static final String GROUP = "group";
        private final Set groupNames = new CopyOnWriteArraySet();
        private ItemStatus itemStatus;
        private ItemType itemType;
        private String name;
        private String user;

        public void addGroupName(String s)
        {
            groupNames.add(s);
        }

        public boolean equals(Object obj)
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
            obj = (Item)obj;
            if (groupNames == null)
            {
                if (((Item) (obj)).groupNames != null)
                {
                    return false;
                }
            } else
            if (!groupNames.equals(((Item) (obj)).groupNames))
            {
                return false;
            }
            if (itemStatus != ((Item) (obj)).itemStatus)
            {
                return false;
            }
            if (itemType != ((Item) (obj)).itemType)
            {
                return false;
            }
            if (name == null)
            {
                if (((Item) (obj)).name != null)
                {
                    return false;
                }
            } else
            if (!name.equals(((Item) (obj)).name))
            {
                return false;
            }
            if (user != null)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (((Item) (obj)).user == null) goto _L1; else goto _L3
_L3:
            return false;
            if (user.equals(((Item) (obj)).user)) goto _L1; else goto _L4
_L4:
            return false;
        }

        public Set getGroupNames()
        {
            return Collections.unmodifiableSet(groupNames);
        }

        public ItemStatus getItemStatus()
        {
            return itemStatus;
        }

        public ItemType getItemType()
        {
            return itemType;
        }

        public String getName()
        {
            return name;
        }

        public String getUser()
        {
            return user;
        }

        public int hashCode()
        {
            int i1 = 0;
            int i;
            int j;
            int k;
            int l;
            if (groupNames == null)
            {
                i = 0;
            } else
            {
                i = groupNames.hashCode();
            }
            if (itemStatus == null)
            {
                j = 0;
            } else
            {
                j = itemStatus.hashCode();
            }
            if (itemType == null)
            {
                k = 0;
            } else
            {
                k = itemType.hashCode();
            }
            if (name == null)
            {
                l = 0;
            } else
            {
                l = name.hashCode();
            }
            if (user != null)
            {
                i1 = user.hashCode();
            }
            return ((((i + 31) * 31 + j) * 31 + k) * 31 + l) * 31 + i1;
        }

        public void removeGroupName(String s)
        {
            groupNames.remove(s);
        }

        public void setItemStatus(ItemStatus itemstatus)
        {
            itemStatus = itemstatus;
        }

        public void setItemType(ItemType itemtype)
        {
            itemType = itemtype;
        }

        public void setName(String s)
        {
            name = s;
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
            xmlstringbuilder.halfOpenElement("item").attribute("jid", user);
            xmlstringbuilder.optAttribute("name", name);
            xmlstringbuilder.optAttribute("subscription", itemType);
            xmlstringbuilder.optAttribute("ask", itemStatus);
            xmlstringbuilder.rightAngleBracket();
            String s;
            for (Iterator iterator = groupNames.iterator(); iterator.hasNext(); xmlstringbuilder.openElement("group").escape(s).closeElement("group"))
            {
                s = (String)iterator.next();
            }

            xmlstringbuilder.closeElement("item");
            return xmlstringbuilder;
        }

        public Item(String s, String s1)
        {
            user = s.toLowerCase(Locale.US);
            name = s1;
            itemType = null;
            itemStatus = null;
        }
    }

    public static final class ItemStatus extends Enum
    {

        private static final ItemStatus $VALUES[];
        public static final ItemStatus SUBSCRIPTION_PENDING;
        public static final ItemStatus UNSUBSCRIPTION_PENDING;
        public static final ItemStatus subscribe;
        public static final ItemStatus unsubscribe;

        public static ItemStatus fromString(String s)
        {
            if (s == null)
            {
                return null;
            }
            try
            {
                s = valueOf(s);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return null;
            }
            return s;
        }

        public static ItemStatus valueOf(String s)
        {
            return (ItemStatus)Enum.valueOf(org/jivesoftware/smack/roster/packet/RosterPacket$ItemStatus, s);
        }

        public static ItemStatus[] values()
        {
            return (ItemStatus[])$VALUES.clone();
        }

        static 
        {
            subscribe = new ItemStatus("subscribe", 0);
            unsubscribe = new ItemStatus("unsubscribe", 1);
            $VALUES = (new ItemStatus[] {
                subscribe, unsubscribe
            });
            SUBSCRIPTION_PENDING = subscribe;
            UNSUBSCRIPTION_PENDING = unsubscribe;
        }

        private ItemStatus(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class ItemType extends Enum
    {

        private static final ItemType $VALUES[];
        public static final ItemType both;
        public static final ItemType from;
        public static final ItemType none;
        public static final ItemType remove;
        public static final ItemType to;

        public static ItemType valueOf(String s)
        {
            return (ItemType)Enum.valueOf(org/jivesoftware/smack/roster/packet/RosterPacket$ItemType, s);
        }

        public static ItemType[] values()
        {
            return (ItemType[])$VALUES.clone();
        }

        static 
        {
            none = new ItemType("none", 0);
            to = new ItemType("to", 1);
            from = new ItemType("from", 2);
            both = new ItemType("both", 3);
            remove = new ItemType("remove", 4);
            $VALUES = (new ItemType[] {
                none, to, from, both, remove
            });
        }

        private ItemType(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:roster";
    private final List rosterItems = new ArrayList();
    private String rosterVersion;

    public RosterPacket()
    {
        super("query", "jabber:iq:roster");
    }

    public void addRosterItem(Item item)
    {
        synchronized (rosterItems)
        {
            rosterItems.add(item);
        }
        return;
        item;
        list;
        JVM INSTR monitorexit ;
        throw item;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.optAttribute("ver", rosterVersion);
        iqchildelementxmlstringbuilder.rightAngleBracket();
        List list = rosterItems;
        list;
        JVM INSTR monitorenter ;
        for (Iterator iterator = rosterItems.iterator(); iterator.hasNext(); iqchildelementxmlstringbuilder.append(((Item)iterator.next()).toXML())) { }
        break MISSING_BLOCK_LABEL_67;
        iqchildelementxmlstringbuilder;
        list;
        JVM INSTR monitorexit ;
        throw iqchildelementxmlstringbuilder;
        list;
        JVM INSTR monitorexit ;
        return iqchildelementxmlstringbuilder;
    }

    public int getRosterItemCount()
    {
        int i;
        synchronized (rosterItems)
        {
            i = rosterItems.size();
        }
        return i;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public List getRosterItems()
    {
        ArrayList arraylist;
        synchronized (rosterItems)
        {
            arraylist = new ArrayList(rosterItems);
        }
        return arraylist;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String getVersion()
    {
        return rosterVersion;
    }

    public void setVersion(String s)
    {
        rosterVersion = s;
    }
}
