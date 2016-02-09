// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamSession

private abstract class getDataPacketFilter extends InputStream
{

    private byte buffer[];
    private int bufferPointer;
    private boolean closeInvoked;
    private final StanzaListener dataPacketListener = getDataPacketListener();
    protected final BlockingQueue dataQueue = new LinkedBlockingQueue();
    private boolean isClosed;
    private int readTimeout;
    private long seq;
    final InBandBytestreamSession this$0;

    private void checkClosed()
        throws IOException
    {
        if (closeInvoked)
        {
            dataQueue.clear();
            throw new IOException("Stream is closed");
        } else
        {
            return;
        }
    }

    private void cleanup()
    {
        InBandBytestreamSession.access$800(InBandBytestreamSession.this).removeSyncStanzaListener(dataPacketListener);
    }

    private void closeInternal()
    {
        if (isClosed)
        {
            return;
        } else
        {
            isClosed = true;
            return;
        }
    }

    private boolean loadBuffer()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        DataPacketExtension datapacketextension = null;
        if (readTimeout != 0) goto _L2; else goto _L1
_L1:
        DataPacketExtension datapacketextension1 = datapacketextension;
        if (datapacketextension != null) goto _L4; else goto _L3
_L3:
        if (!isClosed) goto _L6; else goto _L5
_L5:
        boolean flag = dataQueue.isEmpty();
        if (!flag) goto _L6; else goto _L7
_L7:
        flag = false;
_L9:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L6:
        datapacketextension = (DataPacketExtension)dataQueue.poll(1000L, TimeUnit.MILLISECONDS);
          goto _L1
_L2:
        datapacketextension = (DataPacketExtension)dataQueue.poll(readTimeout, TimeUnit.MILLISECONDS);
        datapacketextension1 = datapacketextension;
        if (datapacketextension != null) goto _L4; else goto _L8
_L8:
        Exception exception;
        long l;
        try
        {
            throw new SocketTimeoutException();
        }
        catch (InterruptedException interruptedexception) { }
        finally
        {
            this;
        }
        Thread.currentThread().interrupt();
        flag = false;
          goto _L9
_L4:
        if (seq == 65535L)
        {
            seq = -1L;
        }
        l = datapacketextension1.getSeq();
        if (l - 1L != seq)
        {
            InBandBytestreamSession.this.close();
            throw new IOException("Packets out of sequence");
        }
        if (true)
        {
            break MISSING_BLOCK_LABEL_174;
        }
        JVM INSTR monitorexit ;
        throw exception;
        seq = l;
        buffer = datapacketextension1.getDecodedData();
        bufferPointer = 0;
        flag = true;
          goto _L9
    }

    public void close()
        throws IOException
    {
        if (closeInvoked)
        {
            return;
        } else
        {
            closeInvoked = true;
            closeByLocal(true);
            return;
        }
    }

    protected abstract StanzaFilter getDataPacketFilter();

    protected abstract StanzaListener getDataPacketListener();

    public boolean markSupported()
    {
        return false;
    }

    public int read()
        throws IOException
    {
        int i = -1;
        this;
        JVM INSTR monitorenter ;
        checkClosed();
        if (bufferPointer != -1 && bufferPointer < buffer.length) goto _L2; else goto _L1
_L1:
        boolean flag = loadBuffer();
        if (flag) goto _L2; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return i;
_L2:
        byte abyte0[];
        abyte0 = buffer;
        i = bufferPointer;
        bufferPointer = i + 1;
        i = abyte0[i];
        i &= 0xff;
        if (true) goto _L3; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    public int read(byte abyte0[])
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        int i = read(abyte0, 0, abyte0.length);
        this;
        JVM INSTR monitorexit ;
        return i;
        abyte0;
        throw abyte0;
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        int k = -1;
        this;
        JVM INSTR monitorenter ;
        if (abyte0 != null)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        throw new NullPointerException();
        abyte0;
        this;
        JVM INSTR monitorexit ;
        throw abyte0;
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        if (i > abyte0.length || j < 0)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        if (i + j <= abyte0.length && i + j >= 0)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        throw new IndexOutOfBoundsException();
        if (j != 0) goto _L2; else goto _L1
_L1:
        k = 0;
_L4:
        this;
        JVM INSTR monitorexit ;
        return k;
_L2:
        checkClosed();
        if ((bufferPointer == -1 || bufferPointer >= buffer.length) && !loadBuffer()) goto _L4; else goto _L3
_L3:
        int l = buffer.length - bufferPointer;
        k = j;
        if (j > l)
        {
            k = l;
        }
        System.arraycopy(buffer, bufferPointer, abyte0, i, k);
        bufferPointer = bufferPointer + k;
          goto _L4
    }




/*
    static int access$402( , int i)
    {
        .readTimeout = i;
        return i;
    }

*/




    public readTimeout()
    {
        this$0 = InBandBytestreamSession.this;
        super();
        bufferPointer = -1;
        seq = -1L;
        isClosed = false;
        closeInvoked = false;
        readTimeout = 0;
        InBandBytestreamSession.access$800(InBandBytestreamSession.this).addSyncStanzaListener(dataPacketListener, getDataPacketFilter());
    }
}
