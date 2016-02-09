// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.io.IOException;
import java.io.OutputStream;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamSession

private abstract class buffer extends OutputStream
{

    protected final byte buffer[];
    protected int bufferPointer;
    protected boolean isClosed;
    protected long seq;
    final InBandBytestreamSession this$0;

    private void flushBuffer()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        int i = bufferPointer;
        if (i != 0) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Object obj;
        obj = Base64.encodeToString(buffer, 0, bufferPointer);
        obj = new DataPacketExtension(InBandBytestreamSession.access$1100(InBandBytestreamSession.this).getSessionID(), seq, ((String) (obj)));
        writeToXML(((DataPacketExtension) (obj)));
        bufferPointer = 0;
        if (seq + 1L != 65535L)
        {
            break; /* Loop/switch isn't completed */
        }
        long l = 0L;
_L4:
        seq = l;
        if (true) goto _L1; else goto _L3
        Object obj1;
        obj1;
        throw obj1;
        obj1;
        IOException ioexception = new IOException();
        ioexception.initCause(((Throwable) (obj1)));
        throw ioexception;
_L3:
        l = seq;
        l++;
          goto _L4
    }

    private void writeOut(byte abyte0[], int i, int j)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (isClosed)
        {
            throw new IOException("Stream is closed");
        }
        break MISSING_BLOCK_LABEL_24;
        abyte0;
        this;
        JVM INSTR monitorexit ;
        throw abyte0;
        int k = 0;
        if (j > buffer.length - bufferPointer)
        {
            k = buffer.length - bufferPointer;
            System.arraycopy(abyte0, i, buffer, bufferPointer, k);
            bufferPointer = bufferPointer + k;
            flushBuffer();
        }
        System.arraycopy(abyte0, i + k, buffer, bufferPointer, j - k);
        bufferPointer = bufferPointer + (j - k);
        this;
        JVM INSTR monitorexit ;
    }

    public void close()
        throws IOException
    {
        if (isClosed)
        {
            return;
        } else
        {
            closeByLocal(false);
            return;
        }
    }

    protected void closeInternal(boolean flag)
    {
        if (!isClosed)
        {
            isClosed = true;
            if (flag)
            {
                try
                {
                    flushBuffer();
                    return;
                }
                catch (IOException ioexception)
                {
                    return;
                }
            }
        }
    }

    public void flush()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (isClosed)
        {
            throw new IOException("Stream is closed");
        }
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        flushBuffer();
        this;
        JVM INSTR monitorexit ;
    }

    public void write(int i)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (isClosed)
        {
            throw new IOException("Stream is closed");
        }
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        byte abyte0[];
        int j;
        if (bufferPointer >= buffer.length)
        {
            flushBuffer();
        }
        abyte0 = buffer;
        j = bufferPointer;
        bufferPointer = j + 1;
        abyte0[j] = (byte)i;
        this;
        JVM INSTR monitorexit ;
    }

    public void write(byte abyte0[])
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        write(abyte0, 0, abyte0.length);
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        throw abyte0;
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (abyte0 != null)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        throw new NullPointerException();
        abyte0;
        this;
        JVM INSTR monitorexit ;
        throw abyte0;
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        if (i > abyte0.length || j < 0)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        if (i + j <= abyte0.length && i + j >= 0)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        throw new IndexOutOfBoundsException();
        if (j != 0) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
label0:
        {
            if (isClosed)
            {
                throw new IOException("Stream is closed");
            }
            if (j < buffer.length)
            {
                break label0;
            }
            writeOut(abyte0, i, buffer.length);
            write(abyte0, buffer.length + i, j - buffer.length);
        }
          goto _L1
        writeOut(abyte0, i, j);
          goto _L1
    }

    protected abstract void writeToXML(DataPacketExtension datapacketextension)
        throws IOException, org.jivesoftware.smack.ion;

    public ()
    {
        this$0 = InBandBytestreamSession.this;
        super();
        bufferPointer = 0;
        seq = 0L;
        isClosed = false;
        buffer = new byte[InBandBytestreamSession.access$1100(InBandBytestreamSession.this).getBlockSize()];
    }
}
