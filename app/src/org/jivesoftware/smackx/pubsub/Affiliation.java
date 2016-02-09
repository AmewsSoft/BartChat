// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.packet.ExtensionElement;

public class Affiliation
    implements ExtensionElement
{
    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type member;
        public static final Type none;
        public static final Type outcast;
        public static final Type owner;
        public static final Type publisher;

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(org/jivesoftware/smackx/pubsub/Affiliation$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            member = new Type("member", 0);
            none = new Type("none", 1);
            outcast = new Type("outcast", 2);
            owner = new Type("owner", 3);
            publisher = new Type("publisher", 4);
            $VALUES = (new Type[] {
                member, none, outcast, owner, publisher
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    protected String node;
    protected Type type;

    public Affiliation(String s, Type type1)
    {
        node = s;
        type = type1;
    }

    private void appendAttribute(StringBuilder stringbuilder, String s, String s1)
    {
        stringbuilder.append(" ");
        stringbuilder.append(s);
        stringbuilder.append("='");
        stringbuilder.append(s1);
        stringbuilder.append("'");
    }

    public String getElementName()
    {
        return "subscription";
    }

    public String getNamespace()
    {
        return null;
    }

    public String getNodeId()
    {
        return node;
    }

    public Type getType()
    {
        return type;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder("<");
        stringbuilder.append(getElementName());
        appendAttribute(stringbuilder, "node", node);
        appendAttribute(stringbuilder, "affiliation", type.toString());
        stringbuilder.append("/>");
        return stringbuilder.toString();
    }
}
