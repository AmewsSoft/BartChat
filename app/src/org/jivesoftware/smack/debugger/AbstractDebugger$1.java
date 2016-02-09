// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.debugger;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.util.ReaderListener;

// Referenced classes of package org.jivesoftware.smack.debugger:
//            AbstractDebugger

class val.connection
    implements ReaderListener
{

    final AbstractDebugger this$0;
    final XMPPConnection val$connection;

    public void read(String s)
    {
        log((new StringBuilder()).append("RECV (").append(val$connection.getConnectionCounter()).append("): ").append(s).toString());
    }

    ()
    {
        this$0 = final_abstractdebugger;
        val$connection = XMPPConnection.this;
        super();
    }
}
