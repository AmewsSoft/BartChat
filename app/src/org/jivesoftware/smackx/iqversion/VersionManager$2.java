// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqversion;

import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.iqversion.packet.Version;

// Referenced classes of package org.jivesoftware.smackx.iqversion:
//            VersionManager

class ode extends AbstractIqRequestHandler
{

    final VersionManager this$0;

    public IQ handleIQRequest(IQ iq)
    {
        if (VersionManager.access$000(VersionManager.this) == null)
        {
            return IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.on.not_acceptable));
        } else
        {
            return Version.createResultFor(iq, VersionManager.access$000(VersionManager.this));
        }
    }

    ode(String s, String s1, org.jivesoftware.smack.packet.r r, org.jivesoftware.smack.iqrequest.Mode mode)
    {
        this$0 = VersionManager.this;
        super(s, s1, r, mode);
    }
}
