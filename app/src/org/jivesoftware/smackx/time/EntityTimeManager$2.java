// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.time;

import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.time.packet.Time;

// Referenced classes of package org.jivesoftware.smackx.time:
//            EntityTimeManager

class .Mode extends AbstractIqRequestHandler
{

    final EntityTimeManager this$0;

    public IQ handleIQRequest(IQ iq)
    {
        if (EntityTimeManager.access$000(EntityTimeManager.this))
        {
            return Time.createResponse(iq);
        } else
        {
            return IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.not_acceptable));
        }
    }

    .Mode(String s, String s1, org.jivesoftware.smack.packet.Mode mode, org.jivesoftware.smack.iqrequest.e e)
    {
        this$0 = EntityTimeManager.this;
        super(s, s1, mode, e);
    }
}
