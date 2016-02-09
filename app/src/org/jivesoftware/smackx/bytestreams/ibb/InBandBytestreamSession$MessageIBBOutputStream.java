// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamSession

private class <init> extends <init>
{

    final InBandBytestreamSession this$0;

    protected void writeToXML(DataPacketExtension datapacketextension)
        throws org.jivesoftware.smack.eIBBOutputStream
    {
        this;
        JVM INSTR monitorenter ;
        Message message = new Message(InBandBytestreamSession.access$1000(InBandBytestreamSession.this));
        message.addExtension(datapacketextension);
        InBandBytestreamSession.access$800(InBandBytestreamSession.this).sendStanza(message);
        this;
        JVM INSTR monitorexit ;
        return;
        datapacketextension;
        throw datapacketextension;
    }

    private ()
    {
        this$0 = InBandBytestreamSession.this;
        super(InBandBytestreamSession.this);
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
