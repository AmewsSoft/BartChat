// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.delay;

import java.util.Date;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.delay.packet.DelayInformation;

public class DelayInformationManager
{

    public static final String LEGACY_DELAYED_DELIVERY_ELEMENT = "x";
    public static final String LEGACY_DELAYED_DELIVERY_NAMESPACE = "jabber:x:delay";

    public DelayInformationManager()
    {
    }

    public static DelayInformation getDelayInformation(Stanza stanza)
    {
        DelayInformation delayinformation = getXep203DelayInformation(stanza);
        if (delayinformation != null)
        {
            return delayinformation;
        } else
        {
            return getLegacyDelayInformation(stanza);
        }
    }

    public static Date getDelayTimestamp(Stanza stanza)
    {
        stanza = getDelayInformation(stanza);
        if (stanza == null)
        {
            return null;
        } else
        {
            return stanza.getStamp();
        }
    }

    public static DelayInformation getLegacyDelayInformation(Stanza stanza)
    {
        return (DelayInformation)stanza.getExtension("x", "jabber:x:delay");
    }

    public static DelayInformation getXep203DelayInformation(Stanza stanza)
    {
        return DelayInformation.from(stanza);
    }

    public static boolean isDelayedStanza(Stanza stanza)
    {
        return getDelayInformation(stanza) != null;
    }
}
