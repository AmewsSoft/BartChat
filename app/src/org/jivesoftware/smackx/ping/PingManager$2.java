// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.ping;

import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.ping.packet.Ping;

// Referenced classes of package org.jivesoftware.smackx.ping:
//            PingManager

class andler.Mode extends AbstractIqRequestHandler
{

    final PingManager this$0;

    public IQ handleIQRequest(IQ iq)
    {
        return ((Ping)iq).getPong();
    }

    andler.Mode(String s, String s1, org.jivesoftware.smack.packet.ndler.Mode mode, org.jivesoftware.smack.iqrequest.er.Mode mode1)
    {
        this$0 = PingManager.this;
        super(s, s1, mode, mode1);
    }
}
