// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, EmbeddedPacketExtension, PubSubElementType

public class ItemsExtension extends NodeExtension
    implements EmbeddedPacketExtension
{
    public static final class ItemsElementType extends Enum
    {

        private static final ItemsElementType $VALUES[];
        public static final ItemsElementType items;
        public static final ItemsElementType retract;
        private String att;
        private PubSubElementType elem;

        public static ItemsElementType valueOf(String s)
        {
            return (ItemsElementType)Enum.valueOf(org/jivesoftware/smackx/pubsub/ItemsExtension$ItemsElementType, s);
        }

        public static ItemsElementType[] values()
        {
            return (ItemsElementType[])$VALUES.clone();
        }

        public String getElementAttribute()
        {
            return att;
        }

        public PubSubElementType getNodeElement()
        {
            return elem;
        }

        static 
        {
            items = new ItemsElementType("items", 0, PubSubElementType.ITEMS, "max_items");
            retract = new ItemsElementType("retract", 1, PubSubElementType.RETRACT, "notify");
            $VALUES = (new ItemsElementType[] {
                items, retract
            });
        }

        private ItemsElementType(String s, int i, PubSubElementType pubsubelementtype, String s1)
        {
            super(s, i);
            elem = pubsubelementtype;
            att = s1;
        }
    }


    protected List items;
    protected Boolean notify;
    protected ItemsElementType type;

    public ItemsExtension(String s, List list, boolean flag)
    {
        super(ItemsElementType.retract.getNodeElement(), s);
        type = ItemsElementType.retract;
        items = list;
        notify = Boolean.valueOf(flag);
    }

    public ItemsExtension(ItemsElementType itemselementtype, String s, List list)
    {
        super(itemselementtype.getNodeElement(), s);
        type = itemselementtype;
        items = list;
    }

    public List getExtensions()
    {
        return getItems();
    }

    public List getItems()
    {
        return items;
    }

    public ItemsElementType getItemsElementType()
    {
        return type;
    }

    public boolean getNotify()
    {
        return notify.booleanValue();
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append("Content [").append(toXML()).append("]").toString();
    }

    public CharSequence toXML()
    {
        if (items == null || items.size() == 0)
        {
            return super.toXML();
        }
        StringBuilder stringbuilder = new StringBuilder("<");
        stringbuilder.append(getElementName());
        stringbuilder.append(" node='");
        stringbuilder.append(getNode());
        if (notify != null)
        {
            stringbuilder.append("' ");
            stringbuilder.append(type.getElementAttribute());
            stringbuilder.append("='");
            int i;
            if (notify.equals(Boolean.TRUE))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            stringbuilder.append(i);
            stringbuilder.append("'>");
        } else
        {
            stringbuilder.append("'>");
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) 
            {
                stringbuilder.append(((ExtensionElement)iterator.next()).toXML());
            }
        }
        stringbuilder.append("</");
        stringbuilder.append(getElementName());
        stringbuilder.append(">");
        return stringbuilder.toString();
    }
}
