// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;


// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            OutgoingFileTransfer

class gotiationProgress
    implements Runnable
{

    final OutgoingFileTransfer this$0;
    final String val$description;
    final String val$fileName;
    final long val$fileSize;
    final gotiationProgress val$progress;

    public void run()
    {
        try
        {
            OutgoingFileTransfer.access$002(OutgoingFileTransfer.this, OutgoingFileTransfer.access$100(OutgoingFileTransfer.this, val$fileName, val$fileSize, val$description));
            val$progress.outputStreamEstablished(OutgoingFileTransfer.access$000(OutgoingFileTransfer.this));
            return;
        }
        catch (org.jivesoftware.smack.Exception exception)
        {
            OutgoingFileTransfer.access$200(OutgoingFileTransfer.this, exception);
            return;
        }
        catch (Exception exception1)
        {
            setException(exception1);
        }
    }

    gotiationProgress()
    {
        this$0 = final_outgoingfiletransfer;
        val$fileName = s;
        val$fileSize = l;
        val$description = s1;
        val$progress = gotiationProgress.this;
        super();
    }
}
