// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp;

import java.util.Date;
import org.jivesoftware.smack.XMPPConnection;
import org.jxmpp.util.XmppDateTime;

// Referenced classes of package org.jivesoftware.smackx.amp:
//            AMPManager

public class AMPExpireAtCondition
    implements org.jivesoftware.smackx.amp.packet.AMPExtension.Condition
{

    public static final String NAME = "expire-at";
    private final String value;

    public AMPExpireAtCondition(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Can't create AMPExpireAtCondition with null value");
        } else
        {
            value = s;
            return;
        }
    }

    public AMPExpireAtCondition(Date date)
    {
        if (date == null)
        {
            throw new NullPointerException("Can't create AMPExpireAtCondition with null value");
        } else
        {
            value = XmppDateTime.formatXEP0082Date(date);
            return;
        }
    }

    public static boolean isSupported(XMPPConnection xmppconnection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return AMPManager.isConditionSupported(xmppconnection, "expire-at");
    }

    public String getName()
    {
        return "expire-at";
    }

    public String getValue()
    {
        return value;
    }
}
