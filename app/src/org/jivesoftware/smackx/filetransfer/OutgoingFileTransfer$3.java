// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            OutgoingFileTransfer

class val.in
    implements Runnable
{

    final OutgoingFileTransfer this$0;
    final String val$description;
    final String val$fileName;
    final long val$fileSize;
    final InputStream val$in;

    public void run()
    {
        try
        {
            OutgoingFileTransfer.access$002(OutgoingFileTransfer.this, OutgoingFileTransfer.access$100(OutgoingFileTransfer.this, val$fileName, val$fileSize, val$description));
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
        while (OutgoingFileTransfer.access$000(OutgoingFileTransfer.this) == null || !updateStatus(otiated, progress)) 
        {
            return;
        }
        writeToStream(val$in, OutgoingFileTransfer.access$000(OutgoingFileTransfer.this));
        Object obj;
        try
        {
            if (val$in != null)
            {
                val$in.close();
            }
            OutgoingFileTransfer.access$000(OutgoingFileTransfer.this).flush();
            OutgoingFileTransfer.access$000(OutgoingFileTransfer.this).close();
        }
        catch (IOException ioexception) { }
        updateStatus(progress, plete);
        return;
        obj;
        setStatus(or);
        setException(((Exception) (obj)));
        try
        {
            if (val$in != null)
            {
                val$in.close();
            }
            OutgoingFileTransfer.access$000(OutgoingFileTransfer.this).flush();
            OutgoingFileTransfer.access$000(OutgoingFileTransfer.this).close();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_128;
        }
        obj;
        try
        {
            if (val$in != null)
            {
                val$in.close();
            }
            OutgoingFileTransfer.access$000(OutgoingFileTransfer.this).flush();
            OutgoingFileTransfer.access$000(OutgoingFileTransfer.this).close();
        }
        catch (IOException ioexception1) { }
        throw obj;
    }

    A()
    {
        this$0 = final_outgoingfiletransfer;
        val$fileName = s;
        val$fileSize = l;
        val$description = s1;
        val$in = InputStream.this;
        super();
    }
}
