// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.io.IOException;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamSession

private class <init> extends <init>
{

    final InBandBytestreamSession this$0;

    protected void writeToXML(DataPacketExtension datapacketextension)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        datapacketextension = new Data(datapacketextension);
        datapacketextension.setTo(InBandBytestreamSession.access$1000(InBandBytestreamSession.this));
        InBandBytestreamSession.access$800(InBandBytestreamSession.this).createPacketCollectorAndSend(datapacketextension).nextResultOrThrow();
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        datapacketextension;
        if (isClosed) goto _L2; else goto _L1
_L1:
        close();
        IOException ioexception = new IOException();
        ioexception.initCause(datapacketextension);
        throw ioexception;
        datapacketextension;
        this;
        JVM INSTR monitorexit ;
        throw datapacketextension;
    }

    private ()
    {
        this$0 = InBandBytestreamSession.this;
        super(InBandBytestreamSession.this);
    }

    nit>(nit> nit>)
    {
        this();
    }
}
