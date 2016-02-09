// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.privacy.packet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.privacy.packet:
//            PrivacyItem

public class Privacy extends IQ
{

    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:privacy";
    private String activeName;
    private boolean declineActiveList;
    private boolean declineDefaultList;
    private String defaultName;
    private Map itemLists;

    public Privacy()
    {
        super("query", "jabber:iq:privacy");
        declineActiveList = false;
        declineDefaultList = false;
        itemLists = new HashMap();
    }

    public boolean changeDefaultList(String s)
    {
        if (getItemLists().containsKey(s))
        {
            setDefaultName(s);
            return true;
        } else
        {
            return false;
        }
    }

    public void deleteList(String s)
    {
        getItemLists().remove(s);
    }

    public void deletePrivacyList(String s)
    {
        getItemLists().remove(s);
        if (getDefaultName() != null && s.equals(getDefaultName()))
        {
            setDefaultName(null);
        }
    }

    public String getActiveName()
    {
        return activeName;
    }

    public List getActivePrivacyList()
    {
        if (getActiveName() == null)
        {
            return null;
        } else
        {
            return (List)getItemLists().get(getActiveName());
        }
    }

    public String getDefaultName()
    {
        return defaultName;
    }

    public List getDefaultPrivacyList()
    {
        if (getDefaultName() == null)
        {
            return null;
        } else
        {
            return (List)getItemLists().get(getDefaultName());
        }
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        Iterator iterator;
        java.util.Map.Entry entry;
        if (isDeclineActiveList())
        {
            iqchildelementxmlstringbuilder.append("<active/>");
            break MISSING_BLOCK_LABEL_19;
        } else
        {
            if (getActiveName() != null)
            {
                iqchildelementxmlstringbuilder.append("<active name=\"").escape(getActiveName()).append("\"/>");
            }
            continue;
        }
        do
        {
            if (isDeclineDefaultList())
            {
                iqchildelementxmlstringbuilder.append("<default/>");
            } else
            if (getDefaultName() != null)
            {
                iqchildelementxmlstringbuilder.append("<default name=\"").escape(getDefaultName()).append("\"/>");
            }
            iterator = getItemLists().entrySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                entry = (java.util.Map.Entry)iterator.next();
                Object obj = (String)entry.getKey();
                List list = (List)entry.getValue();
                if (list.isEmpty())
                {
                    iqchildelementxmlstringbuilder.append("<list name=\"").escape(((String) (obj))).append("\"/>");
                } else
                {
                    iqchildelementxmlstringbuilder.append("<list name=\"").escape(((String) (obj))).append("\">");
                }
                for (obj = list.iterator(); ((Iterator) (obj)).hasNext(); iqchildelementxmlstringbuilder.append(((PrivacyItem)((Iterator) (obj)).next()).toXML())) { }
                if (!list.isEmpty())
                {
                    iqchildelementxmlstringbuilder.append("</list>");
                }
            } while (true);
            return iqchildelementxmlstringbuilder;
        } while (true);
    }

    public PrivacyItem getItem(String s, int i)
    {
        Iterator iterator = getPrivacyList(s).iterator();
        s = null;
        do
        {
            if (s != null || !iterator.hasNext())
            {
                break;
            }
            PrivacyItem privacyitem = (PrivacyItem)iterator.next();
            if (privacyitem.getOrder() == (long)i)
            {
                s = privacyitem;
            }
        } while (true);
        return s;
    }

    public Map getItemLists()
    {
        return itemLists;
    }

    public List getPrivacyList(String s)
    {
        return (List)getItemLists().get(s);
    }

    public Set getPrivacyListNames()
    {
        return itemLists.keySet();
    }

    public boolean isDeclineActiveList()
    {
        return declineActiveList;
    }

    public boolean isDeclineDefaultList()
    {
        return declineDefaultList;
    }

    public void setActiveName(String s)
    {
        activeName = s;
    }

    public List setActivePrivacyList()
    {
        setActiveName(getDefaultName());
        return (List)getItemLists().get(getActiveName());
    }

    public void setDeclineActiveList(boolean flag)
    {
        declineActiveList = flag;
    }

    public void setDeclineDefaultList(boolean flag)
    {
        declineDefaultList = flag;
    }

    public void setDefaultName(String s)
    {
        defaultName = s;
    }

    public List setPrivacyList(String s, List list)
    {
        getItemLists().put(s, list);
        return list;
    }
}
