// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.util.Map;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Close;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamManager, InBandBytestreamSession

class CloseListener extends AbstractIqRequestHandler
{

    private final InBandBytestreamManager manager;

    protected CloseListener(InBandBytestreamManager inbandbytestreammanager)
    {
        super("close", "http://jabber.org/protocol/ibb", org.jivesoftware.smack.packet.IQ.Type.set, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async);
        manager = inbandbytestreammanager;
    }

    public IQ handleIQRequest(IQ iq)
    {
        iq = (Close)iq;
        InBandBytestreamSession inbandbytestreamsession = (InBandBytestreamSession)manager.getSessions().get(iq.getSessionID());
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
        try
        {
            inbandbytestreamsession.closeByPeer(iq);
        }
        // Misplaced declaration of an exception variable
        catch (IQ iq)
        {
            return null;
        }
        manager.getSessions().remove(iq.getSessionID());
        return null;
    }
}
