// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack:
//            AbstractXMPPConnection

private class packet
    implements Runnable
{

    private final Stanza packet;
    final AbstractXMPPConnection this$0;

    public void run()
    {
        invokePacketCollectorsAndNotifyRecvListeners(packet);
    }

    public (Stanza stanza)
    {
        this$0 = AbstractXMPPConnection.this;
        super();
        packet = stanza;
    }
}
