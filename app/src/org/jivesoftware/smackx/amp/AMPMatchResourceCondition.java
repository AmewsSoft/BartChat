// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp;

import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smackx.amp:
//            AMPManager

public class AMPMatchResourceCondition
    implements org.jivesoftware.smackx.amp.packet.AMPExtension.Condition
{
    public static final class Value extends Enum
    {

        private static final Value $VALUES[];
        public static final Value any;
        public static final Value exact;
        public static final Value other;

        public static Value valueOf(String s)
        {
            return (Value)Enum.valueOf(org/jivesoftware/smackx/amp/AMPMatchResourceCondition$Value, s);
        }

        public static Value[] values()
        {
            return (Value[])$VALUES.clone();
        }

        static 
        {
            any = new Value("any", 0);
            exact = new Value("exact", 1);
            other = new Value("other", 2);
            $VALUES = (new Value[] {
                any, exact, other
            });
        }

        private Value(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String NAME = "match-resource";
    private final Value value;

    public AMPMatchResourceCondition(Value value1)
    {
        if (value1 == null)
        {
            throw new NullPointerException("Can't create AMPMatchResourceCondition with null value");
        } else
        {
            value = value1;
            return;
        }
    }

    public static boolean isSupported(XMPPConnection xmppconnection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return AMPManager.isConditionSupported(xmppconnection, "match-resource");
    }

    public String getName()
    {
        return "match-resource";
    }

    public String getValue()
    {
        return value.toString();
    }
}
