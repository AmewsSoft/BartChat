// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqlast;

import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.iqlast.packet.LastActivity;

// Referenced classes of package org.jivesoftware.smackx.iqlast:
//            LastActivityManager

class e extends AbstractIqRequestHandler
{

    final LastActivityManager this$0;

    public IQ handleIQRequest(IQ iq)
    {
        if (!LastActivityManager.access$100(LastActivityManager.this))
        {
            return IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.t_acceptable));
        } else
        {
            LastActivity lastactivity = new LastActivity();
            lastactivity.setType(org.jivesoftware.smack.packet.etType);
            lastactivity.setTo(iq.getFrom());
            lastactivity.setFrom(iq.getTo());
            lastactivity.setStanzaId(iq.getStanzaId());
            lastactivity.setLastActivity(LastActivityManager.access$200(LastActivityManager.this));
            return lastactivity;
        }
    }

    e(String s, String s1, org.jivesoftware.smack.packet. , org.jivesoftware.smack.iqrequest.er er)
    {
        this$0 = LastActivityManager.this;
        super(s, s1, , er);
    }
}
