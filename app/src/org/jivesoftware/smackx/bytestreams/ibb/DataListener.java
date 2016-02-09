// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.util.Map;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamManager, InBandBytestreamSession

class DataListener extends AbstractIqRequestHandler
{

    private final InBandBytestreamManager manager;

    public DataListener(InBandBytestreamManager inbandbytestreammanager)
    {
        super("data", "http://jabber.org/protocol/ibb", org.jivesoftware.smack.packet.IQ.Type.set, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async);
        manager = inbandbytestreammanager;
    }

    public IQ handleIQRequest(IQ iq)
    {
        iq = (Data)iq;
        InBandBytestreamSession inbandbytestreamsession = (InBandBytestreamSession)manager.getSessions().get(iq.getDataPacketExtension().getSessionID());
        if (inbandbytestreamsession == null)
        {
            try
            {
                manager.replyItemNotFoundPacket(iq);
            }
            // Misplaced declaration of an exception variable
            catch (IQ iq)
            {
                return null;
            }
            return null;
        }
        inbandbytestreamsession.processIQPacket(iq);
        return null;
    }
}
