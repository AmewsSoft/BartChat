// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.si.packet.StreamInitiation;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            StreamNegotiator

class val.response
    implements org.jivesoftware.smack.util.k
{

    final StreamNegotiator this$0;
    final XMPPConnection val$connection;
    final StreamInitiation val$response;

    public void action()
        throws org.jivesoftware.smack.onnectedException
    {
        val$connection.sendStanza(val$response);
    }

    ion()
    {
        this$0 = final_streamnegotiator;
        val$connection = xmppconnection;
        val$response = StreamInitiation.this;
        super();
    }
}
