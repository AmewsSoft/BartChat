// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;


// Referenced classes of package org.jivesoftware.smack:
//            AbstractXMPPConnection, StanzaListener

class val.packetListener
    implements Runnable
{

    final AbstractXMPPConnection this$0;
    final StanzaListener val$packetListener;

    public void run()
    {
        removeSyncStanzaListener(val$packetListener);
    }

    ()
    {
        this$0 = final_abstractxmppconnection;
        val$packetListener = StanzaListener.this;
        super();
    }
}
