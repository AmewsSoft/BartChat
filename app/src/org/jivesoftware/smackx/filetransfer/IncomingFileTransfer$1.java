// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            IncomingFileTransfer

class val.file
    implements Runnable
{

    final IncomingFileTransfer this$0;
    final File val$file;

    public void run()
    {
        Object obj;
        Object obj1;
        FileNotFoundException filenotfoundexception;
        try
        {
            IncomingFileTransfer.access$002(IncomingFileTransfer.this, IncomingFileTransfer.access$100(IncomingFileTransfer.this));
        }
        catch (Exception exception)
        {
            setStatus(or);
            setException(exception);
            return;
        }
        filenotfoundexception = null;
        obj1 = null;
        obj = new FileOutputStream(val$file);
        setStatus(progress);
        writeToStream(IncomingFileTransfer.access$000(IncomingFileTransfer.this), ((OutputStream) (obj)));
_L1:
        if (getStatus().als(progress))
        {
            setStatus(plete);
        }
        IOException ioexception1;
        if (IncomingFileTransfer.access$000(IncomingFileTransfer.this) != null)
        {
            try
            {
                IncomingFileTransfer.access$000(IncomingFileTransfer.this).close();
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception1)
            {
                IncomingFileTransfer.access$200().log(Level.WARNING, "Closing input stream", ioexception1);
            }
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        ((OutputStream) (obj)).close();
        return;
        filenotfoundexception;
        exception = ((Exception) (obj1));
        obj1 = filenotfoundexception;
_L3:
        setStatus(or);
        setError(file);
        setException(((Exception) (obj1)));
          goto _L1
        ioexception1;
        exception = filenotfoundexception;
_L2:
        setStatus(or);
        setError(am);
        setException(ioexception1);
          goto _L1
        IOException ioexception;
        ioexception;
        IncomingFileTransfer.access$200().log(Level.WARNING, "Closing output stream", ioexception);
        return;
        ioexception1;
          goto _L2
        ioexception1;
          goto _L3
    }

    A()
    {
        this$0 = final_incomingfiletransfer;
        val$file = File.this;
        super();
    }
}
