// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;

// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommandManager

class er extends AbstractIqRequestHandler
{

    final AdHocCommandManager this$0;

    public IQ handleIQRequest(IQ iq)
    {
        iq = (AdHocCommandData)iq;
        iq = AdHocCommandManager.access$100(AdHocCommandManager.this, iq);
        return iq;
        iq;
_L2:
        AdHocCommandManager.access$200().log(Level.INFO, "processAdHocCommand threw exceptino", iq);
        return null;
        iq;
        if (true) goto _L2; else goto _L1
_L1:
    }

    tion(String s, String s1, org.jivesoftware.smack.packet. , org.jivesoftware.smack.iqrequest.tion tion)
    {
        this$0 = AdHocCommandManager.this;
        super(s, s1, , tion);
    }
}
