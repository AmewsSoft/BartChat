// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.address.packet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class MultipleAddresses
    implements ExtensionElement
{
    public static class Address
        implements NamedElement
    {

        public static final String ELEMENT = "address";
        private boolean delivered;
        private String description;
        private String jid;
        private String node;
        private final Type type;
        private String uri;

        private void setDelivered(boolean flag)
        {
            delivered = flag;
        }

        private void setDescription(String s)
        {
            description = s;
        }

        private void setJid(String s)
        {
            jid = s;
        }

        private void setNode(String s)
        {
            node = s;
        }

        private void setUri(String s)
        {
            uri = s;
        }

        public String getDescription()
        {
            return description;
        }

        public String getElementName()
        {
            return "address";
        }

        public String getJid()
        {
            return jid;
        }

        public String getNode()
        {
            return node;
        }

        public Type getType()
        {
            return type;
        }

        public String getUri()
        {
            return uri;
        }

        public boolean isDelivered()
        {
            return delivered;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
            xmlstringbuilder.halfOpenElement(this).attribute("type", type);
            xmlstringbuilder.optAttribute("jid", jid);
            xmlstringbuilder.optAttribute("node", node);
            xmlstringbuilder.optAttribute("desc", description);
            if (description != null && description.trim().length() > 0)
            {
                xmlstringbuilder.append(" desc=\"");
                xmlstringbuilder.append(description).append("\"");
            }
            xmlstringbuilder.optBooleanAttribute("delivered", delivered);
            xmlstringbuilder.optAttribute("uri", uri);
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }






        private Address(Type type1)
        {
            type = type1;
        }

    }

    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type bcc;
        public static final Type cc;
        public static final Type noreply;
        public static final Type ofrom;
        public static final Type replyroom;
        public static final Type replyto;
        public static final Type to;

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(org/jivesoftware/smackx/address/packet/MultipleAddresses$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            bcc = new Type("bcc", 0);
            cc = new Type("cc", 1);
            noreply = new Type("noreply", 2);
            replyroom = new Type("replyroom", 3);
            replyto = new Type("replyto", 4);
            to = new Type("to", 5);
            ofrom = new Type("ofrom", 6);
            $VALUES = (new Type[] {
                bcc, cc, noreply, replyroom, replyto, to, ofrom
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String ELEMENT = "addresses";
    public static final String NAMESPACE = "http://jabber.org/protocol/address";
    private List addresses;

    public MultipleAddresses()
    {
        addresses = new ArrayList();
    }

    public void addAddress(Type type, String s, String s1, String s2, boolean flag, String s3)
    {
        type = new Address(type);
        type.setJid(s);
        type.setNode(s1);
        type.setDescription(s2);
        type.setDelivered(flag);
        type.setUri(s3);
        addresses.add(type);
    }

    public List getAddressesOfType(Type type)
    {
        ArrayList arraylist = new ArrayList(addresses.size());
        Iterator iterator = addresses.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Address address = (Address)iterator.next();
            if (address.getType().equals(type))
            {
                arraylist.add(address);
            }
        } while (true);
        return arraylist;
    }

    public String getElementName()
    {
        return "addresses";
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/address";
    }

    public void setNoReply()
    {
        Address address = new Address(Type.noreply);
        addresses.add(address);
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        for (Iterator iterator = addresses.iterator(); iterator.hasNext(); xmlstringbuilder.append(((Address)iterator.next()).toXML())) { }
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
