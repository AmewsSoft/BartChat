// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.filter.StanzaFilter;

// Referenced classes of package org.jivesoftware.smack:
//            AbstractXMPPConnection, ExceptionCallback, StanzaListener

class val.replyFilter
    implements Runnable
{

    final AbstractXMPPConnection this$0;
    final ExceptionCallback val$exceptionCallback;
    final StanzaListener val$packetListener;
    final StanzaFilter val$replyFilter;

    public void run()
    {
        if (removeAsyncStanzaListener(val$packetListener) && val$exceptionCallback != null)
        {
            val$exceptionCallback.processException(Exception.newWith(AbstractXMPPConnection.this, val$replyFilter));
        }
    }

    Exception()
    {
        this$0 = final_abstractxmppconnection;
        val$packetListener = stanzalistener;
        val$exceptionCallback = exceptioncallback;
        val$replyFilter = StanzaFilter.this;
        super();
    }
}
