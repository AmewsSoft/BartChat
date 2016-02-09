// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.tcp;

import java.util.Map;

// Referenced classes of package org.jivesoftware.smack.tcp:
//            XMPPTCPConnection

class val.id
    implements Runnable
{

    final XMPPTCPConnection this$0;
    final String val$id;

    public void run()
    {
        XMPPTCPConnection.access$3900(XMPPTCPConnection.this).remove(val$id);
    }

    ()
    {
        this$0 = final_xmpptcpconnection;
        val$id = String.this;
        super();
    }
}
