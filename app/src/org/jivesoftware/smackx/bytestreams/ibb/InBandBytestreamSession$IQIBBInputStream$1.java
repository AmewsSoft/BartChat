// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.util.concurrent.BlockingQueue;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamSession

class lastSequence
    implements StanzaListener
{

    private long lastSequence;
    final lastSequence this$1;

    public void processPacket(Stanza stanza)
        throws org.jivesoftware.smack.
    {
        DataPacketExtension datapacketextension = ((Data)stanza).getDataPacketExtension();
        if (datapacketextension.getSeq() <= lastSequence)
        {
            stanza = IQ.createErrorResponse((IQ)stanza, new XMPPError(org.jivesoftware.smack.packet.tStream._cls1.lastSequence));
            InBandBytestreamSession.access$800(_fld0).sendStanza(stanza);
        } else
        {
            if (datapacketextension.getDecodedData() == null)
            {
                stanza = IQ.createErrorResponse((IQ)stanza, new XMPPError(org.jivesoftware.smack.packet.odedData));
                InBandBytestreamSession.access$800(_fld0).sendStanza(stanza);
                return;
            }
            taQueue.offer(datapacketextension);
            stanza = IQ.createResultIQ((IQ)stanza);
            InBandBytestreamSession.access$800(_fld0).sendStanza(stanza);
            lastSequence = datapacketextension.getSeq();
            if (lastSequence == 65535L)
            {
                lastSequence = -1L;
                return;
            }
        }
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
        lastSequence = -1L;
    }
}
