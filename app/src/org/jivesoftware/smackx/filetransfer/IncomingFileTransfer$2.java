// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.io.InputStream;
import java.util.concurrent.Callable;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            IncomingFileTransfer, FileTransferRequest, StreamNegotiator

class val.streamNegotiator
    implements Callable
{

    final IncomingFileTransfer this$0;
    final StreamNegotiator val$streamNegotiator;

    public InputStream call()
        throws Exception
    {
        return val$streamNegotiator.createIncomingStream(IncomingFileTransfer.access$300(IncomingFileTransfer.this).getStreamInitiation());
    }

    public volatile Object call()
        throws Exception
    {
        return call();
    }

    A()
    {
        this$0 = final_incomingfiletransfer;
        val$streamNegotiator = StreamNegotiator.this;
        super();
    }
}
