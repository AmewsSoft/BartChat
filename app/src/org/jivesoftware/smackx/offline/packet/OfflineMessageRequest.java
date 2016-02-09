// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.offline.packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OfflineMessageRequest extends IQ
{
    public static class Item
    {

        private String action;
        private String jid;
        private String node;

        public String getAction()
        {
            return action;
        }

        public String getJid()
        {
            return jid;
        }

        public String getNode()
        {
            return node;
        }

        public void setAction(String s)
        {
            action = s;
        }

        public void setJid(String s)
        {
            jid = s;
        }

        public String toXML()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("<item");
            if (getAction() != null)
            {
                stringbuilder.append(" action=\"").append(getAction()).append("\"");
            }
            if (getJid() != null)
            {
                stringbuilder.append(" jid=\"").append(getJid()).append("\"");
            }
            if (getNode() != null)
            {
                stringbuilder.append(" node=\"").append(getNode()).append("\"");
            }
            stringbuilder.append("/>");
            return stringbuilder.toString();
        }

        public Item(String s)
        {
            node = s;
        }
    }

    public static class Provider extends IQProvider
    {

        private Item parseItem(XmlPullParser xmlpullparser)
            throws XmlPullParserException, IOException
        {
            boolean flag = false;
            Item item = new Item(xmlpullparser.getAttributeValue("", "node"));
            item.setAction(xmlpullparser.getAttributeValue("", "action"));
            item.setJid(xmlpullparser.getAttributeValue("", "jid"));
            do
            {
                if (flag)
                {
                    break;
                }
                if (xmlpullparser.next() == 3 && xmlpullparser.getName().equals("item"))
                {
                    flag = true;
                }
            } while (true);
            return item;
        }

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public OfflineMessageRequest parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException
        {
            OfflineMessageRequest offlinemessagerequest = new OfflineMessageRequest();
            i = 0;
            do
            {
                if (i != 0)
                {
                    break;
                }
                int j = xmlpullparser.next();
                if (j == 2)
                {
                    if (xmlpullparser.getName().equals("item"))
                    {
                        offlinemessagerequest.addItem(parseItem(xmlpullparser));
                    } else
                    if (xmlpullparser.getName().equals("purge"))
                    {
                        offlinemessagerequest.setPurge(true);
                    } else
                    if (xmlpullparser.getName().equals("fetch"))
                    {
                        offlinemessagerequest.setFetch(true);
                    }
                } else
                if (j == 3 && xmlpullparser.getName().equals("offline"))
                {
                    i = 1;
                }
            } while (true);
            return offlinemessagerequest;
        }

        public Provider()
        {
        }
    }


    public static final String ELEMENT = "offline";
    public static final String NAMESPACE = "http://jabber.org/protocol/offline";
    private boolean fetch;
    private List items;
    private boolean purge;

    public OfflineMessageRequest()
    {
        super("offline", "http://jabber.org/protocol/offline");
        items = new ArrayList();
        purge = false;
        fetch = false;
    }

    public void addItem(Item item)
    {
        synchronized (items)
        {
            items.add(item);
        }
        return;
        item;
        list;
        JVM INSTR monitorexit ;
        throw item;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        List list = items;
        list;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if (i >= items.size())
        {
            break; /* Loop/switch isn't completed */
        }
        iqchildelementxmlstringbuilder.append(((Item)items.get(i)).toXML());
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        list;
        JVM INSTR monitorexit ;
        if (purge)
        {
            iqchildelementxmlstringbuilder.append("<purge/>");
        }
        if (fetch)
        {
            iqchildelementxmlstringbuilder.append("<fetch/>");
        }
        return iqchildelementxmlstringbuilder;
        iqchildelementxmlstringbuilder;
        list;
        JVM INSTR monitorexit ;
        throw iqchildelementxmlstringbuilder;
    }

    public List getItems()
    {
        List list1;
        synchronized (items)
        {
            list1 = Collections.unmodifiableList(new ArrayList(items));
        }
        return list1;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean isFetch()
    {
        return fetch;
    }

    public boolean isPurge()
    {
        return purge;
    }

    public void setFetch(boolean flag)
    {
        fetch = flag;
    }

    public void setPurge(boolean flag)
    {
        purge = flag;
    }
}
