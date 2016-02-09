// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster.packet;

import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.roster.packet:
//            RosterPacket

public static class itemStatus
{

    public static final String GROUP = "group";
    private final Set groupNames = new CopyOnWriteArraySet();
    private tatus itemStatus;
    private ype itemType;
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
        obj = (groupNames)obj;
        if (groupNames == null)
        {
            if (((groupNames) (obj)).groupNames != null)
            {
                return false;
            }
        } else
        if (!groupNames.equals(((groupNames) (obj)).groupNames))
        {
            return false;
        }
        if (itemStatus != ((itemStatus) (obj)).itemStatus)
        {
            return false;
        }
        if (itemType != ((itemType) (obj)).itemType)
        {
            return false;
        }
        if (name == null)
        {
            if (((name) (obj)).name != null)
            {
                return false;
            }
        } else
        if (!name.equals(((name) (obj)).name))
        {
            return false;
        }
        if (user != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (((user) (obj)).user == null) goto _L1; else goto _L3
_L3:
        return false;
        if (user.equals(((user) (obj)).user)) goto _L1; else goto _L4
_L4:
        return false;
    }

    public Set getGroupNames()
    {
        return Collections.unmodifiableSet(groupNames);
    }

    public tatus getItemStatus()
    {
        return itemStatus;
    }

    public ype getItemType()
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

    public void setItemStatus(tatus tatus)
    {
        itemStatus = tatus;
    }

    public void setItemType(ype ype)
    {
        itemType = ype;
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

    public ype(String s, String s1)
    {
        user = s.toLowerCase(Locale.US);
        name = s1;
        itemType = null;
        itemStatus = null;
    }
}
