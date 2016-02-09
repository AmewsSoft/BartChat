// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco.packet;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class DiscoverItems extends IQ
{
    public static class Item
    {

        public static final String REMOVE_ACTION = "remove";
        public static final String UPDATE_ACTION = "update";
        private String action;
        private String entityID;
        private String name;
        private String node;

        public String getAction()
        {
            return action;
        }

        public String getEntityID()
        {
            return entityID;
        }

        public String getName()
        {
            return name;
        }

        public String getNode()
        {
            return node;
        }

        public void setAction(String s)
        {
            action = s;
        }

        public void setName(String s)
        {
            name = s;
        }

        public void setNode(String s)
        {
            node = s;
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
            xmlstringbuilder.halfOpenElement("item");
            xmlstringbuilder.attribute("jid", entityID);
            xmlstringbuilder.optAttribute("name", name);
            xmlstringbuilder.optAttribute("node", node);
            xmlstringbuilder.optAttribute("action", action);
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }

        public Item(String s)
        {
            entityID = s;
        }
    }


    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "http://jabber.org/protocol/disco#items";
    private final List items = new LinkedList();
    private String node;

    public DiscoverItems()
    {
        super("query", "http://jabber.org/protocol/disco#items");
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public void addItems(Collection collection)
    {
        if (collection != null)
        {
            collection = collection.iterator();
            while (collection.hasNext()) 
            {
                addItem((Item)collection.next());
            }
        }
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.optAttribute("node", getNode());
        iqchildelementxmlstringbuilder.rightAngleBracket();
        for (Iterator iterator = items.iterator(); iterator.hasNext(); iqchildelementxmlstringbuilder.append(((Item)iterator.next()).toXML())) { }
        return iqchildelementxmlstringbuilder;
    }

    public List getItems()
    {
        return Collections.unmodifiableList(items);
    }

    public String getNode()
    {
        return node;
    }

    public void setNode(String s)
    {
        node = s;
    }
}
