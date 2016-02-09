// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            FileTransferNegotiator

public abstract class FileTransfer
{
    public static final class Error extends Enum
    {

        private static final Error $VALUES[];
        public static final Error bad_file;
        public static final Error connection;
        public static final Error no_response;
        public static final Error none;
        public static final Error not_acceptable;
        public static final Error stream;
        private final String msg;

        public static Error valueOf(String s)
        {
            return (Error)Enum.valueOf(org/jivesoftware/smackx/filetransfer/FileTransfer$Error, s);
        }

        public static Error[] values()
        {
            return (Error[])$VALUES.clone();
        }

        public String getMessage()
        {
            return msg;
        }

        public String toString()
        {
            return msg;
        }

        static 
        {
            none = new Error("none", 0, "No error");
            not_acceptable = new Error("not_acceptable", 1, "The peer did not find any of the provided stream mechanisms acceptable.");
            bad_file = new Error("bad_file", 2, "The provided file to transfer does not exist or could not be read.");
            no_response = new Error("no_response", 3, "The remote user did not respond or the connection timed out.");
            connection = new Error("connection", 4, "An error occured over the socket connected to send the file.");
            stream = new Error("stream", 5, "An error occured while sending or recieving the file.");
            $VALUES = (new Error[] {
                none, not_acceptable, bad_file, no_response, connection, stream
            });
        }

        private Error(String s, int i, String s1)
        {
            super(s, i);
            msg = s1;
        }
    }

    public static final class Status extends Enum
    {

        private static final Status $VALUES[];
        public static final Status cancelled;
        public static final Status complete;
        public static final Status error;
        public static final Status in_progress;
        public static final Status initial;
        public static final Status negotiated;
        public static final Status negotiating_stream;
        public static final Status negotiating_transfer;
        public static final Status refused;
        private String status;

        public static Status valueOf(String s)
        {
            return (Status)Enum.valueOf(org/jivesoftware/smackx/filetransfer/FileTransfer$Status, s);
        }

        public static Status[] values()
        {
            return (Status[])$VALUES.clone();
        }

        public String toString()
        {
            return status;
        }

        static 
        {
            error = new Status("error", 0, "Error");
            initial = new Status("initial", 1, "Initial");
            negotiating_transfer = new Status("negotiating_transfer", 2, "Negotiating Transfer");
            refused = new Status("refused", 3, "Refused");
            negotiating_stream = new Status("negotiating_stream", 4, "Negotiating Stream");
            negotiated = new Status("negotiated", 5, "Negotiated");
            in_progress = new Status("in_progress", 6, "In Progress");
            complete = new Status("complete", 7, "Complete");
            cancelled = new Status("cancelled", 8, "Cancelled");
            $VALUES = (new Status[] {
                error, initial, negotiating_transfer, refused, negotiating_stream, negotiated, in_progress, complete, cancelled
            });
        }

        private Status(String s, int i, String s1)
        {
            super(s, i);
            status = s1;
        }
    }


    private static final int BUFFER_SIZE = 8192;
    protected long amountWritten;
    private Error error;
    private Exception exception;
    private String fileName;
    private String filePath;
    private long fileSize;
    protected FileTransferNegotiator negotiator;
    private String peer;
    private Status status;
    private final Object statusMonitor = new Object();
    protected String streamID;

    protected FileTransfer(String s, String s1, FileTransferNegotiator filetransfernegotiator)
    {
        status = Status.initial;
        amountWritten = -1L;
        peer = s;
        streamID = s1;
        negotiator = filetransfernegotiator;
    }

    public abstract void cancel();

    public long getAmountWritten()
    {
        return amountWritten;
    }

    public Error getError()
    {
        return error;
    }

    public Exception getException()
    {
        return exception;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public long getFileSize()
    {
        return fileSize;
    }

    public String getPeer()
    {
        return peer;
    }

    public double getProgress()
    {
        if (amountWritten <= 0L || fileSize <= 0L)
        {
            return 0.0D;
        } else
        {
            return (double)amountWritten / (double)fileSize;
        }
    }

    public Status getStatus()
    {
        return status;
    }

    public String getStreamID()
    {
        return streamID;
    }

    public boolean isDone()
    {
        return status == Status.cancelled || status == Status.error || status == Status.complete || status == Status.refused;
    }

    protected void setError(Error error1)
    {
        error = error1;
    }

    protected void setException(Exception exception1)
    {
        exception = exception1;
    }

    protected void setFileInfo(String s, long l)
    {
        fileName = s;
        fileSize = l;
    }

    protected void setFileInfo(String s, String s1, long l)
    {
        filePath = s;
        fileName = s1;
        fileSize = l;
    }

    protected void setStatus(Status status1)
    {
        synchronized (statusMonitor)
        {
            status = status1;
        }
        return;
        status1;
        obj;
        JVM INSTR monitorexit ;
        throw status1;
    }

    protected boolean updateStatus(Status status1, Status status2)
    {
label0:
        {
            synchronized (statusMonitor)
            {
                if (status1 == status)
                {
                    break label0;
                }
            }
            return false;
        }
        status = status2;
        obj;
        JVM INSTR monitorexit ;
        return true;
        status1;
        obj;
        JVM INSTR monitorexit ;
        throw status1;
    }

    protected void writeToStream(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        byte abyte0[] = new byte[8192];
        amountWritten = 0L;
        do
        {
            int i = inputstream.read(abyte0);
            if (i <= 0 || getStatus().equals(Status.cancelled))
            {
                break;
            }
            outputstream.write(abyte0, 0, i);
            amountWritten = amountWritten + (long)i;
        } while (true);
        if (!getStatus().equals(Status.cancelled) && getError() == Error.none && amountWritten != fileSize)
        {
            setStatus(Status.error);
            error = Error.connection;
        }
    }
}
