// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.util.concurrent.BlockingQueue;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamSession

private class <init> extends <init>
{

    final InBandBytestreamSession this$0;

    protected StanzaFilter getDataPacketFilter()
    {
        return new AndFilter(new StanzaFilter[] {
            new StanzaTypeFilter(org/jivesoftware/smack/packet/Message), new nit>(InBandBytestreamSession.this, null)
        });
    }

    protected StanzaListener getDataPacketListener()
    {
        return new StanzaListener() {

            final InBandBytestreamSession.MessageIBBInputStream this$1;

            public void processPacket(Stanza stanza)
            {
                stanza = (DataPacketExtension)stanza.getExtension("data", "http://jabber.org/protocol/ibb");
                if (stanza.getDecodedData() == null)
                {
                    return;
                } else
                {
                    dataQueue.offer(stanza);
                    return;
                }
            }

            
            {
                this$1 = InBandBytestreamSession.MessageIBBInputStream.this;
                super();
            }
        };
    }

    private _cls1.this._cls1()
    {
        this$0 = InBandBytestreamSession.this;
        super(InBandBytestreamSession.this);
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
