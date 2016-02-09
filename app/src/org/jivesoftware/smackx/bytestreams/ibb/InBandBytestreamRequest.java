// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.util.Map;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.bytestreams.BytestreamRequest;
import org.jivesoftware.smackx.bytestreams.BytestreamSession;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamManager, InBandBytestreamSession

public class InBandBytestreamRequest
    implements BytestreamRequest
{

    private final Open byteStreamRequest;
    private final InBandBytestreamManager manager;

    protected InBandBytestreamRequest(InBandBytestreamManager inbandbytestreammanager, Open open)
    {
        manager = inbandbytestreammanager;
        byteStreamRequest = open;
    }

    public volatile BytestreamSession accept()
        throws InterruptedException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, SmackException
    {
        return accept();
    }

    public InBandBytestreamSession accept()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        XMPPConnection xmppconnection = manager.getConnection();
        InBandBytestreamSession inbandbytestreamsession = new InBandBytestreamSession(xmppconnection, byteStreamRequest, byteStreamRequest.getFrom());
        manager.getSessions().put(byteStreamRequest.getSessionID(), inbandbytestreamsession);
        xmppconnection.sendStanza(IQ.createResultIQ(byteStreamRequest));
        return inbandbytestreamsession;
    }

    public String getFrom()
    {
        return byteStreamRequest.getFrom();
    }

    public String getSessionID()
    {
        return byteStreamRequest.getSessionID();
    }

    public void reject()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        manager.replyRejectPacket(byteStreamRequest);
    }
}
