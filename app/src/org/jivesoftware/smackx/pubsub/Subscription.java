// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, PubSubElementType

public class Subscription extends NodeExtension
{
    public static final class State extends Enum
    {

        private static final State $VALUES[];
        public static final State none;
        public static final State pending;
        public static final State subscribed;
        public static final State unconfigured;

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(org/jivesoftware/smackx/pubsub/Subscription$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        static 
        {
            subscribed = new State("subscribed", 0);
            unconfigured = new State("unconfigured", 1);
            pending = new State("pending", 2);
            none = new State("none", 3);
            $VALUES = (new State[] {
                subscribed, unconfigured, pending, none
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    protected boolean configRequired;
    protected String id;
    protected String jid;
    protected State state;

    public Subscription(String s)
    {
        this(s, null, null, null);
    }

    public Subscription(String s, String s1)
    {
        this(s, s1, null, null);
    }

    public Subscription(String s, String s1, String s2, State state1)
    {
        super(PubSubElementType.SUBSCRIPTION, s1);
        configRequired = false;
        jid = s;
        id = s2;
        state = state1;
    }

    public Subscription(String s, String s1, String s2, State state1, boolean flag)
    {
        super(PubSubElementType.SUBSCRIPTION, s1);
        configRequired = false;
        jid = s;
        id = s2;
        state = state1;
        configRequired = flag;
    }

    private void appendAttribute(StringBuilder stringbuilder, String s, String s1)
    {
        stringbuilder.append(" ");
        stringbuilder.append(s);
        stringbuilder.append("='");
        stringbuilder.append(s1);
        stringbuilder.append("'");
    }

    public String getId()
    {
        return id;
    }

    public String getJid()
    {
        return jid;
    }

    public State getState()
    {
        return state;
    }

    public boolean isConfigRequired()
    {
        return configRequired;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder("<subscription");
        appendAttribute(stringbuilder, "jid", jid);
        if (getNode() != null)
        {
            appendAttribute(stringbuilder, "node", getNode());
        }
        if (id != null)
        {
            appendAttribute(stringbuilder, "subid", id);
        }
        if (state != null)
        {
            appendAttribute(stringbuilder, "subscription", state.toString());
        }
        stringbuilder.append("/>");
        return stringbuilder.toString();
    }
}
