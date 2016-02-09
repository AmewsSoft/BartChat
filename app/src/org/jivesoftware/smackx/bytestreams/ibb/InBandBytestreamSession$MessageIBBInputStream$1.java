// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.util.concurrent.BlockingQueue;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamSession

class this._cls1
    implements StanzaListener
{

    final taQueue this$1;

    public void processPacket(Stanza stanza)
    {
        stanza = (DataPacketExtension)stanza.getExtension("data", "http://jabber.org/protocol/ibb");
        if (stanza.getDecodedData() == null)
        {
            return;
        } else
        {
            taQueue.offer(stanza);
            return;
        }
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
