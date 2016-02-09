// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.iqrequest.IQRequestHandler;
import org.jivesoftware.smack.packet.IQ;

// Referenced classes of package org.jivesoftware.smack:
//            AbstractXMPPConnection

class val.iq
    implements Runnable
{

    final AbstractXMPPConnection this$0;
    final IQRequestHandler val$finalIqRequestHandler;
    final IQ val$iq;

    public void run()
    {
        IQ iq1 = val$finalIqRequestHandler.handleIQRequest(val$iq);
        if (iq1 == null)
        {
            return;
        }
        try
        {
            sendStanza(iq1);
            return;
        }
        catch (edException edexception)
        {
            AbstractXMPPConnection.access$000().log(Level.WARNING, "NotConnectedException while sending response to IQ request", edexception);
        }
    }

    r()
    {
        this$0 = final_abstractxmppconnection;
        val$finalIqRequestHandler = iqrequesthandler;
        val$iq = IQ.this;
        super();
    }
}
