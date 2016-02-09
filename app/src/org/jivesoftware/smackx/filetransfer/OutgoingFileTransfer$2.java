// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            OutgoingFileTransfer

class val.description
    implements Runnable
{

    final OutgoingFileTransfer this$0;
    final String val$description;
    final File val$file;

    public void run()
    {
        Object obj;
        Object obj2;
        Object obj3;
        try
        {
            OutgoingFileTransfer.access$002(OutgoingFileTransfer.this, OutgoingFileTransfer.access$100(OutgoingFileTransfer.this, val$file.getName(), val$file.length(), val$description));
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
        obj3 = null;
        obj = null;
        obj2 = null;
        Object obj1 = new FileInputStream(val$file);
        writeToStream(((InputStream) (obj1)), OutgoingFileTransfer.access$000(OutgoingFileTransfer.this));
        if (obj1 != null)
        {
            try
            {
                ((InputStream) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                OutgoingFileTransfer.access$300().log(Level.WARNING, "Closing input stream", ((Throwable) (obj)));
            }
        }
        try
        {
            OutgoingFileTransfer.access$000(OutgoingFileTransfer.this).close();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            OutgoingFileTransfer.access$300().log(Level.WARNING, "Closing output stream", ((Throwable) (obj)));
        }
_L1:
        updateStatus(progress, plete);
        return;
        obj;
        obj1 = obj2;
        obj2 = obj;
_L5:
        obj = obj1;
        setStatus(or);
        obj = obj1;
        setError(file);
        obj = obj1;
        setException(((Exception) (obj2)));
        if (obj1 != null)
        {
            try
            {
                ((InputStream) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                OutgoingFileTransfer.access$300().log(Level.WARNING, "Closing input stream", ((Throwable) (obj)));
            }
        }
        try
        {
            OutgoingFileTransfer.access$000(OutgoingFileTransfer.this).close();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            OutgoingFileTransfer.access$300().log(Level.WARNING, "Closing output stream", ((Throwable) (obj)));
        }
          goto _L1
        IOException ioexception;
        ioexception;
        obj1 = obj3;
_L4:
        obj = obj1;
        setStatus(or);
        obj = obj1;
        setException(ioexception);
        if (obj1 != null)
        {
            try
            {
                ((InputStream) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                OutgoingFileTransfer.access$300().log(Level.WARNING, "Closing input stream", ((Throwable) (obj)));
            }
        }
        try
        {
            OutgoingFileTransfer.access$000(OutgoingFileTransfer.this).close();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            OutgoingFileTransfer.access$300().log(Level.WARNING, "Closing output stream", ((Throwable) (obj)));
        }
          goto _L1
        obj1;
_L3:
        if (obj != null)
        {
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                OutgoingFileTransfer.access$300().log(Level.WARNING, "Closing input stream", ((Throwable) (obj)));
            }
        }
        try
        {
            OutgoingFileTransfer.access$000(OutgoingFileTransfer.this).close();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            OutgoingFileTransfer.access$300().log(Level.WARNING, "Closing output stream", ((Throwable) (obj)));
        }
        throw obj1;
        ioexception;
        obj = obj1;
        obj1 = ioexception;
        if (true) goto _L3; else goto _L2
_L2:
        ioexception;
          goto _L4
        ioexception;
          goto _L5
    }

    A()
    {
        this$0 = final_outgoingfiletransfer;
        val$file = file1;
        val$description = String.this;
        super();
    }
}
