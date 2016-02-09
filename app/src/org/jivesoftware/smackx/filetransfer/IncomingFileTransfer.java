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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            FileTransfer, FileTransferRequest, FileTransferNegotiator, StreamNegotiator

public class IncomingFileTransfer extends FileTransfer
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/filetransfer/IncomingFileTransfer.getName());
    private InputStream inputStream;
    private FileTransferRequest recieveRequest;

    protected IncomingFileTransfer(FileTransferRequest filetransferrequest, FileTransferNegotiator filetransfernegotiator)
    {
        super(filetransferrequest.getRequestor(), filetransferrequest.getStreamID(), filetransfernegotiator);
        recieveRequest = filetransferrequest;
    }

    private InputStream negotiateStream()
        throws SmackException, org.jivesoftware.smack.XMPPException.XMPPErrorException
    {
        final StreamNegotiator streamNegotiator;
        setStatus(FileTransfer.Status.negotiating_transfer);
        streamNegotiator = negotiator.selectStreamNegotiator(recieveRequest);
        setStatus(FileTransfer.Status.negotiating_stream);
        streamNegotiator = new FutureTask(new Callable() {

            final IncomingFileTransfer this$0;
            final StreamNegotiator val$streamNegotiator;

            public InputStream call()
                throws Exception
            {
                return streamNegotiator.createIncomingStream(recieveRequest.getStreamInitiation());
            }

            public volatile Object call()
                throws Exception
            {
                return call();
            }

            
            {
                this$0 = IncomingFileTransfer.this;
                streamNegotiator = streamnegotiator;
                super();
            }
        });
        streamNegotiator.run();
        InputStream inputstream = (InputStream)streamNegotiator.get(15L, TimeUnit.SECONDS);
        streamNegotiator.cancel(true);
        setStatus(FileTransfer.Status.negotiated);
        return inputstream;
        Object obj;
        obj;
        throw new SmackException("Interruption while executing", ((Throwable) (obj)));
        obj;
        streamNegotiator.cancel(true);
        throw obj;
        obj;
        throw new SmackException("Error in execution", ((Throwable) (obj)));
        obj;
        throw new SmackException("Request timed out", ((Throwable) (obj)));
    }

    public void cancel()
    {
        setStatus(FileTransfer.Status.cancelled);
    }

    public InputStream recieveFile()
        throws SmackException, org.jivesoftware.smack.XMPPException.XMPPErrorException
    {
        if (inputStream != null)
        {
            throw new IllegalStateException("Transfer already negotiated!");
        }
        try
        {
            inputStream = negotiateStream();
        }
        catch (org.jivesoftware.smack.XMPPException.XMPPErrorException xmpperrorexception)
        {
            setException(xmpperrorexception);
            throw xmpperrorexception;
        }
        return inputStream;
    }

    public void recieveFile(final File file)
        throws SmackException, IOException
    {
        if (file == null)
        {
            throw new IllegalArgumentException("File cannot be null");
        }
        if (!file.exists())
        {
            file.createNewFile();
        }
        if (!file.canWrite())
        {
            throw new IllegalArgumentException("Cannot write to provided file");
        } else
        {
            (new Thread(new Runnable() {

                final IncomingFileTransfer this$0;
                final File val$file;

                public void run()
                {
                    Object obj;
                    Object obj1;
                    FileNotFoundException filenotfoundexception;
                    try
                    {
                        inputStream = negotiateStream();
                    }
                    catch (Exception exception)
                    {
                        setStatus(FileTransfer.Status.error);
                        setException(exception);
                        return;
                    }
                    filenotfoundexception = null;
                    obj1 = null;
                    obj = new FileOutputStream(file);
                    setStatus(FileTransfer.Status.in_progress);
                    writeToStream(inputStream, ((OutputStream) (obj)));
_L1:
                    if (getStatus().equals(FileTransfer.Status.in_progress))
                    {
                        setStatus(FileTransfer.Status.complete);
                    }
                    IOException ioexception1;
                    if (inputStream != null)
                    {
                        try
                        {
                            inputStream.close();
                        }
                        // Misplaced declaration of an exception variable
                        catch (IOException ioexception1)
                        {
                            IncomingFileTransfer.LOGGER.log(Level.WARNING, "Closing input stream", ioexception1);
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
                    setStatus(FileTransfer.Status.error);
                    setError(FileTransfer.Error.bad_file);
                    setException(((Exception) (obj1)));
                      goto _L1
                    ioexception1;
                    exception = filenotfoundexception;
_L2:
                    setStatus(FileTransfer.Status.error);
                    setError(FileTransfer.Error.stream);
                    setException(ioexception1);
                      goto _L1
                    IOException ioexception;
                    ioexception;
                    IncomingFileTransfer.LOGGER.log(Level.WARNING, "Closing output stream", ioexception);
                    return;
                    ioexception1;
                      goto _L2
                    ioexception1;
                      goto _L3
                }

            
            {
                this$0 = IncomingFileTransfer.this;
                file = file1;
                super();
            }
            }, (new StringBuilder()).append("File Transfer ").append(streamID).toString())).start();
            return;
        }
    }




/*
    static InputStream access$002(IncomingFileTransfer incomingfiletransfer, InputStream inputstream)
    {
        incomingfiletransfer.inputStream = inputstream;
        return inputstream;
    }

*/



}
