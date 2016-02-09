// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.privacy.packet;

import org.jivesoftware.smack.util.NumberUtil;

public class PrivacyItem
{
    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type group;
        public static final Type jid;
        public static final Type subscription;

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(org/jivesoftware/smackx/privacy/packet/PrivacyItem$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            group = new Type("group", 0);
            jid = new Type("jid", 1);
            subscription = new Type("subscription", 2);
            $VALUES = (new Type[] {
                group, jid, subscription
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String SUBSCRIPTION_BOTH = "both";
    public static final String SUBSCRIPTION_FROM = "from";
    public static final String SUBSCRIPTION_NONE = "none";
    public static final String SUBSCRIPTION_TO = "to";
    private final boolean allow;
    private boolean filterIQ;
    private boolean filterMessage;
    private boolean filterPresenceIn;
    private boolean filterPresenceOut;
    private final long order;
    private final Type type;
    private final String value;

    public PrivacyItem(Type type1, String s, boolean flag, long l)
    {
        filterIQ = false;
        filterMessage = false;
        filterPresenceIn = false;
        filterPresenceOut = false;
        NumberUtil.checkIfInUInt32Range(l);
        type = type1;
        value = s;
        allow = flag;
        order = l;
    }

    public PrivacyItem(boolean flag, long l)
    {
        this(null, null, flag, l);
    }

    public long getOrder()
    {
        return order;
    }

    public Type getType()
    {
        return type;
    }

    public String getValue()
    {
        return value;
    }

    public boolean isAllow()
    {
        return allow;
    }

    public boolean isFilterEverything()
    {
        return !isFilterIQ() && !isFilterMessage() && !isFilterPresenceIn() && !isFilterPresenceOut();
    }

    public boolean isFilterIQ()
    {
        return filterIQ;
    }

    public boolean isFilterMessage()
    {
        return filterMessage;
    }

    public boolean isFilterPresenceIn()
    {
        return filterPresenceIn;
    }

    public boolean isFilterPresenceOut()
    {
        return filterPresenceOut;
    }

    public void setFilterIQ(boolean flag)
    {
        filterIQ = flag;
    }

    public void setFilterMessage(boolean flag)
    {
        filterMessage = flag;
    }

    public void setFilterPresenceIn(boolean flag)
    {
        filterPresenceIn = flag;
    }

    public void setFilterPresenceOut(boolean flag)
    {
        filterPresenceOut = flag;
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<item");
        if (isAllow())
        {
            stringbuilder.append(" action=\"allow\"");
        } else
        {
            stringbuilder.append(" action=\"deny\"");
        }
        stringbuilder.append(" order=\"").append(getOrder()).append("\"");
        if (getType() != null)
        {
            stringbuilder.append(" type=\"").append(getType()).append("\"");
        }
        if (getValue() != null)
        {
            stringbuilder.append(" value=\"").append(getValue()).append("\"");
        }
        if (isFilterEverything())
        {
            stringbuilder.append("/>");
        } else
        {
            stringbuilder.append(">");
            if (isFilterIQ())
            {
                stringbuilder.append("<iq/>");
            }
            if (isFilterMessage())
            {
                stringbuilder.append("<message/>");
            }
            if (isFilterPresenceIn())
            {
                stringbuilder.append("<presence-in/>");
            }
            if (isFilterPresenceOut())
            {
                stringbuilder.append("<presence-out/>");
            }
            stringbuilder.append("</item>");
        }
        return stringbuilder.toString();
    }
}
