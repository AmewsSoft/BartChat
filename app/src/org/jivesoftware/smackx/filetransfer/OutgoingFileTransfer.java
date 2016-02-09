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
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.XMPPError;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            FileTransfer, FileTransferNegotiator, StreamNegotiator

public class OutgoingFileTransfer extends FileTransfer
{
    public static interface NegotiationProgress
    {

        public abstract void errorEstablishingStream(Exception exception);

        public abstract void outputStreamEstablished(OutputStream outputstream);

        public abstract void statusUpdated(FileTransfer.Status status, FileTransfer.Status status1);
    }


    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/filetransfer/OutgoingFileTransfer.getName());
    private static int RESPONSE_TIMEOUT = 60000;
    private NegotiationProgress callback;
    private String initiator;
    private OutputStream outputStream;
    private Thread transferThread;

    protected OutgoingFileTransfer(String s, String s1, String s2, FileTransferNegotiator filetransfernegotiator)
    {
        super(s1, s2, filetransfernegotiator);
        initiator = s;
    }

    private void checkTransferThread()
    {
        if (transferThread != null && transferThread.isAlive() || isDone())
        {
            throw new IllegalStateException("File transfer in progress or has already completed.");
        } else
        {
            return;
        }
    }

    public static int getResponseTimeout()
    {
        return RESPONSE_TIMEOUT;
    }

    private void handleXMPPException(org.jivesoftware.smack.XMPPException.XMPPErrorException xmpperrorexception)
    {
        XMPPError xmpperror = xmpperrorexception.getXMPPError();
        if (xmpperror == null) goto _L2; else goto _L1
_L1:
        static class _cls4
        {

            static final int $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition = new int[org.jivesoftware.smack.packet.XMPPError.Condition.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[org.jivesoftware.smack.packet.XMPPError.Condition.forbidden.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[org.jivesoftware.smack.packet.XMPPError.Condition.bad_request.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls4..SwitchMap.org.jivesoftware.smack.packet.XMPPError.Condition[xmpperror.getCondition().ordinal()];
        JVM INSTR tableswitch 1 2: default 44
    //                   1 57
    //                   2 65;
           goto _L3 _L4 _L5
_L3:
        setStatus(FileTransfer.Status.error);
_L2:
        setException(xmpperrorexception);
        return;
_L4:
        setStatus(FileTransfer.Status.refused);
        return;
_L5:
        setStatus(FileTransfer.Status.error);
        setError(FileTransfer.Error.not_acceptable);
        if (true) goto _L2; else goto _L6
_L6:
    }

    private OutputStream negotiateStream(String s, long l, String s1)
        throws SmackException, XMPPException
    {
        if (!updateStatus(FileTransfer.Status.initial, FileTransfer.Status.negotiating_transfer))
        {
            throw new org.jivesoftware.smack.SmackException.IllegalStateChangeException();
        }
        s = negotiator.negotiateOutgoingTransfer(getPeer(), streamID, s, l, s1, RESPONSE_TIMEOUT);
        if (!updateStatus(FileTransfer.Status.negotiating_transfer, FileTransfer.Status.negotiating_stream))
        {
            throw new org.jivesoftware.smack.SmackException.IllegalStateChangeException();
        }
        outputStream = s.createOutgoingStream(streamID, initiator, getPeer());
        if (!updateStatus(FileTransfer.Status.negotiating_stream, FileTransfer.Status.negotiated))
        {
            throw new org.jivesoftware.smack.SmackException.IllegalStateChangeException();
        } else
        {
            return outputStream;
        }
    }

    public static void setResponseTimeout(int i)
    {
        RESPONSE_TIMEOUT = i;
    }

    public void cancel()
    {
        setStatus(FileTransfer.Status.cancelled);
    }

    public long getBytesSent()
    {
        return amountWritten;
    }

    protected OutputStream getOutputStream()
    {
        if (getStatus().equals(FileTransfer.Status.negotiated))
        {
            return outputStream;
        } else
        {
            return null;
        }
    }

    public OutputStream sendFile(String s, long l, String s1)
        throws XMPPException, SmackException
    {
        this;
        JVM INSTR monitorenter ;
        if (isDone() || outputStream != null)
        {
            throw new IllegalStateException("The negotation process has already been attempted on this file transfer");
        }
        break MISSING_BLOCK_LABEL_31;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        setFileInfo(s, l);
        outputStream = negotiateStream(s, l, s1);
        s = outputStream;
        this;
        JVM INSTR monitorexit ;
        return s;
        s;
        handleXMPPException(s);
        throw s;
    }

    public void sendFile(final File file, final String description)
        throws SmackException
    {
        this;
        JVM INSTR monitorenter ;
        checkTransferThread();
        if (file == null)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        if (file.exists() && file.canRead())
        {
            break MISSING_BLOCK_LABEL_39;
        }
        throw new IllegalArgumentException("Could not read file");
        file;
        this;
        JVM INSTR monitorexit ;
        throw file;
        setFileInfo(file.getAbsolutePath(), file.getName(), file.length());
        transferThread = new Thread(new Runnable() {

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
                    outputStream = negotiateStream(file.getName(), file.length(), description);
                }
                catch (org.jivesoftware.smack.XMPPException.XMPPErrorException xmpperrorexception)
                {
                    handleXMPPException(xmpperrorexception);
                    return;
                }
                catch (Exception exception)
                {
                    setException(exception);
                }
                while (outputStream == null || !updateStatus(FileTransfer.Status.negotiated, FileTransfer.Status.in_progress)) 
                {
                    return;
                }
                obj3 = null;
                obj = null;
                obj2 = null;
                Object obj1 = new FileInputStream(file);
                writeToStream(((InputStream) (obj1)), outputStream);
                if (obj1 != null)
                {
                    try
                    {
                        ((InputStream) (obj1)).close();
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj)
                    {
                        OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing input stream", ((Throwable) (obj)));
                    }
                }
                try
                {
                    outputStream.close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing output stream", ((Throwable) (obj)));
                }
_L1:
                updateStatus(FileTransfer.Status.in_progress, FileTransfer.Status.complete);
                return;
                obj;
                obj1 = obj2;
                obj2 = obj;
_L5:
                obj = obj1;
                setStatus(FileTransfer.Status.error);
                obj = obj1;
                setError(FileTransfer.Error.bad_file);
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
                        OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing input stream", ((Throwable) (obj)));
                    }
                }
                try
                {
                    outputStream.close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing output stream", ((Throwable) (obj)));
                }
                  goto _L1
                IOException ioexception;
                ioexception;
                obj1 = obj3;
_L4:
                obj = obj1;
                setStatus(FileTransfer.Status.error);
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
                        OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing input stream", ((Throwable) (obj)));
                    }
                }
                try
                {
                    outputStream.close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing output stream", ((Throwable) (obj)));
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
                        OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing input stream", ((Throwable) (obj)));
                    }
                }
                try
                {
                    outputStream.close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing output stream", ((Throwable) (obj)));
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

            
            {
                this$0 = OutgoingFileTransfer.this;
                file = file1;
                description = s;
                super();
            }
        }, (new StringBuilder()).append("File Transfer ").append(streamID).toString());
        transferThread.start();
        this;
        JVM INSTR monitorexit ;
    }

    public void sendFile(final String fileName, final long fileSize, final String description, final NegotiationProgress progress)
    {
        this;
        JVM INSTR monitorenter ;
        if (progress != null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        throw new IllegalArgumentException("Callback progress cannot be null.");
        fileName;
        this;
        JVM INSTR monitorexit ;
        throw fileName;
        checkTransferThread();
        if (isDone() || outputStream != null)
        {
            throw new IllegalStateException("The negotation process has already been attempted for this file transfer");
        }
        setFileInfo(fileName, fileSize);
        callback = progress;
        transferThread = new Thread(new Runnable() {

            final OutgoingFileTransfer this$0;
            final String val$description;
            final String val$fileName;
            final long val$fileSize;
            final NegotiationProgress val$progress;

            public void run()
            {
                try
                {
                    outputStream = negotiateStream(fileName, fileSize, description);
                    progress.outputStreamEstablished(outputStream);
                    return;
                }
                catch (org.jivesoftware.smack.XMPPException.XMPPErrorException xmpperrorexception)
                {
                    handleXMPPException(xmpperrorexception);
                    return;
                }
                catch (Exception exception)
                {
                    setException(exception);
                }
            }

            
            {
                this$0 = OutgoingFileTransfer.this;
                fileName = s;
                fileSize = l;
                description = s1;
                progress = negotiationprogress;
                super();
            }
        }, (new StringBuilder()).append("File Transfer Negotiation ").append(streamID).toString());
        transferThread.start();
        this;
        JVM INSTR monitorexit ;
    }

    public void sendStream(final InputStream in, final String fileName, final long fileSize, final String description)
    {
        this;
        JVM INSTR monitorenter ;
        checkTransferThread();
        setFileInfo(fileName, fileSize);
        transferThread = new Thread(new Runnable() {

            final OutgoingFileTransfer this$0;
            final String val$description;
            final String val$fileName;
            final long val$fileSize;
            final InputStream val$in;

            public void run()
            {
                try
                {
                    outputStream = negotiateStream(fileName, fileSize, description);
                }
                catch (org.jivesoftware.smack.XMPPException.XMPPErrorException xmpperrorexception)
                {
                    handleXMPPException(xmpperrorexception);
                    return;
                }
                catch (Exception exception)
                {
                    setException(exception);
                }
                while (outputStream == null || !updateStatus(FileTransfer.Status.negotiated, FileTransfer.Status.in_progress)) 
                {
                    return;
                }
                writeToStream(in, outputStream);
                Object obj;
                try
                {
                    if (in != null)
                    {
                        in.close();
                    }
                    outputStream.flush();
                    outputStream.close();
                }
                catch (IOException ioexception) { }
                updateStatus(FileTransfer.Status.in_progress, FileTransfer.Status.complete);
                return;
                obj;
                setStatus(FileTransfer.Status.error);
                setException(((Exception) (obj)));
                try
                {
                    if (in != null)
                    {
                        in.close();
                    }
                    outputStream.flush();
                    outputStream.close();
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
                    if (in != null)
                    {
                        in.close();
                    }
                    outputStream.flush();
                    outputStream.close();
                }
                catch (IOException ioexception1) { }
                throw obj;
            }

            
            {
                this$0 = OutgoingFileTransfer.this;
                fileName = s;
                fileSize = l;
                description = s1;
                in = inputstream;
                super();
            }
        }, (new StringBuilder()).append("File Transfer ").append(streamID).toString());
        transferThread.start();
        this;
        JVM INSTR monitorexit ;
        return;
        in;
        throw in;
    }

    protected void setException(Exception exception)
    {
        super.setException(exception);
        if (callback != null)
        {
            callback.errorEstablishingStream(exception);
        }
    }

    protected void setOutputStream(OutputStream outputstream)
    {
        if (outputStream == null)
        {
            outputStream = outputstream;
        }
    }

    protected void setStatus(FileTransfer.Status status)
    {
        FileTransfer.Status status1 = getStatus();
        super.setStatus(status);
        if (callback != null)
        {
            callback.statusUpdated(status1, status);
        }
    }

    protected boolean updateStatus(FileTransfer.Status status, FileTransfer.Status status1)
    {
        boolean flag = super.updateStatus(status, status1);
        if (callback != null && flag)
        {
            callback.statusUpdated(status, status1);
        }
        return flag;
    }




/*
    static OutputStream access$002(OutgoingFileTransfer outgoingfiletransfer, OutputStream outputstream)
    {
        outgoingfiletransfer.outputStream = outputstream;
        return outputstream;
    }

*/



}
