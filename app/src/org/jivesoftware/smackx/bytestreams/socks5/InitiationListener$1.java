// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.IQ;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            InitiationListener

class val.packet
    implements Runnable
{

    final InitiationListener this$0;
    final IQ val$packet;

    public void run()
    {
        try
        {
            InitiationListener.access$000(InitiationListener.this, val$packet);
            return;
        }
        catch (org.jivesoftware.smack.nectedException nectedexception)
        {
            InitiationListener.access$100().log(Level.WARNING, "process request", nectedexception);
        }
    }

    ()
    {
        this$0 = final_initiationlistener;
        val$packet = IQ.this;
        super();
    }
}
