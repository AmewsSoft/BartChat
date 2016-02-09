// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack:
//            StanzaListener, AbstractXMPPConnection, ExceptionCallback

class val.exceptionCallback
    implements StanzaListener
{

    final AbstractXMPPConnection this$0;
    final StanzaListener val$callback;
    final ExceptionCallback val$exceptionCallback;

    public void processPacket(Stanza stanza)
        throws edException
    {
        ception.ifHasErrorThenThrow(stanza);
        val$callback.processPacket(stanza);
        removeAsyncStanzaListener(this);
        return;
        stanza;
        if (val$exceptionCallback != null)
        {
            val$exceptionCallback.processException(stanza);
        }
        removeAsyncStanzaListener(this);
        return;
        stanza;
        removeAsyncStanzaListener(this);
        throw stanza;
    }

    ception()
    {
        this$0 = final_abstractxmppconnection;
        val$callback = stanzalistener;
        val$exceptionCallback = ExceptionCallback.this;
        super();
    }
}
