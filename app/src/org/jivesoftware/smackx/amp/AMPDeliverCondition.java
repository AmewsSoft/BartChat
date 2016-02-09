// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp;

import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smackx.amp:
//            AMPManager

public class AMPDeliverCondition
    implements org.jivesoftware.smackx.amp.packet.AMPExtension.Condition
{
    public static final class Value extends Enum
    {

        private static final Value $VALUES[];
        public static final Value direct;
        public static final Value forward;
        public static final Value gateway;
        public static final Value none;
        public static final Value stored;

        public static Value valueOf(String s)
        {
            return (Value)Enum.valueOf(org/jivesoftware/smackx/amp/AMPDeliverCondition$Value, s);
        }

        public static Value[] values()
        {
            return (Value[])$VALUES.clone();
        }

        static 
        {
            direct = new Value("direct", 0);
            forward = new Value("forward", 1);
            gateway = new Value("gateway", 2);
            none = new Value("none", 3);
            stored = new Value("stored", 4);
            $VALUES = (new Value[] {
                direct, forward, gateway, none, stored
            });
        }

        private Value(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String NAME = "deliver";
    private final Value value;

    public AMPDeliverCondition(Value value1)
    {
        if (value1 == null)
        {
            throw new NullPointerException("Can't create AMPDeliverCondition with null value");
        } else
        {
            value = value1;
            return;
        }
    }

    public static boolean isSupported(XMPPConnection xmppconnection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return AMPManager.isConditionSupported(xmppconnection, "deliver");
    }

    public String getName()
    {
        return "deliver";
    }

    public String getValue()
    {
        return value.toString();
    }
}
