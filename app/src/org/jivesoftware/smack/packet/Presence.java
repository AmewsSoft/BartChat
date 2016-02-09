// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Locale;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smack.util.TypedCloneable;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            Stanza

public final class Presence extends Stanza
    implements TypedCloneable
{
    public static final class Mode extends Enum
    {

        private static final Mode $VALUES[];
        public static final Mode available;
        public static final Mode away;
        public static final Mode chat;
        public static final Mode dnd;
        public static final Mode xa;

        public static Mode fromString(String s)
        {
            return valueOf(s.toLowerCase(Locale.US));
        }

        public static Mode valueOf(String s)
        {
            return (Mode)Enum.valueOf(org/jivesoftware/smack/packet/Presence$Mode, s);
        }

        public static Mode[] values()
        {
            return (Mode[])$VALUES.clone();
        }

        static 
        {
            chat = new Mode("chat", 0);
            available = new Mode("available", 1);
            away = new Mode("away", 2);
            xa = new Mode("xa", 3);
            dnd = new Mode("dnd", 4);
            $VALUES = (new Mode[] {
                chat, available, away, xa, dnd
            });
        }

        private Mode(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type available;
        public static final Type error;
        public static final Type probe;
        public static final Type subscribe;
        public static final Type subscribed;
        public static final Type unavailable;
        public static final Type unsubscribe;
        public static final Type unsubscribed;

        public static Type fromString(String s)
        {
            return valueOf(s.toLowerCase(Locale.US));
        }

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(org/jivesoftware/smack/packet/Presence$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            available = new Type("available", 0);
            unavailable = new Type("unavailable", 1);
            subscribe = new Type("subscribe", 2);
            subscribed = new Type("subscribed", 3);
            unsubscribe = new Type("unsubscribe", 4);
            unsubscribed = new Type("unsubscribed", 5);
            error = new Type("error", 6);
            probe = new Type("probe", 7);
            $VALUES = (new Type[] {
                available, unavailable, subscribe, subscribed, unsubscribe, unsubscribed, error, probe
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String ELEMENT = "presence";
    private Mode mode;
    private int priority;
    private String status;
    private Type type;

    public Presence(Type type1)
    {
        type = Type.available;
        status = null;
        priority = 0x80000000;
        mode = null;
        setType(type1);
    }

    public Presence(Type type1, String s, int i, Mode mode1)
    {
        type = Type.available;
        status = null;
        priority = 0x80000000;
        mode = null;
        setType(type1);
        setStatus(s);
        setPriority(i);
        setMode(mode1);
    }

    public Presence(Presence presence)
    {
        super(presence);
        type = Type.available;
        status = null;
        priority = 0x80000000;
        mode = null;
        type = presence.type;
        status = presence.status;
        priority = presence.priority;
        mode = presence.mode;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public Presence clone()
    {
        return new Presence(this);
    }

    public Mode getMode()
    {
        if (mode == null)
        {
            return Mode.available;
        } else
        {
            return mode;
        }
    }

    public int getPriority()
    {
        return priority;
    }

    public String getStatus()
    {
        return status;
    }

    public Type getType()
    {
        return type;
    }

    public boolean isAvailable()
    {
        return type == Type.available;
    }

    public boolean isAway()
    {
        return type == Type.available && (mode == Mode.away || mode == Mode.xa || mode == Mode.dnd);
    }

    public void setMode(Mode mode1)
    {
        mode = mode1;
    }

    public void setPriority(int i)
    {
        if (i < -128 || i > 128)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Priority value ").append(i).append(" is not valid. Valid range is -128 through 128.").toString());
        } else
        {
            priority = i;
            return;
        }
    }

    public void setStatus(String s)
    {
        status = s;
    }

    public void setType(Type type1)
    {
        type = (Type)Objects.requireNonNull(type1, "Type cannot be null");
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("presence");
        addCommonAttributes(xmlstringbuilder);
        if (type != Type.available)
        {
            xmlstringbuilder.attribute("type", type);
        }
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optElement("status", status);
        if (priority != 0x80000000)
        {
            xmlstringbuilder.element("priority", Integer.toString(priority));
        }
        if (mode != null && mode != Mode.available)
        {
            xmlstringbuilder.element("show", mode);
        }
        xmlstringbuilder.append(getExtensionsXML());
        appendErrorIfExists(xmlstringbuilder);
        xmlstringbuilder.closeElement("presence");
        return xmlstringbuilder;
    }
}
