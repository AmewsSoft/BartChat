// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jivesoftware.smackx.bytestreams.BytestreamSession;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Close;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamManager

public class InBandBytestreamSession
    implements BytestreamSession
{
    private class IBBDataPacketFilter
        implements StanzaFilter
    {

        final InBandBytestreamSession this$0;

        public boolean accept(Stanza stanza)
        {
            if (stanza.getFrom().equalsIgnoreCase(remoteJID)) goto _L2; else goto _L1
_L1:
            return false;
_L2:
            if (!(stanza instanceof Data))
            {
                break; /* Loop/switch isn't completed */
            }
            stanza = ((Data)stanza).getDataPacketExtension();
_L4:
            if (stanza.getSessionID().equals(byteStreamRequest.getSessionID()))
            {
                return true;
            }
            if (true) goto _L1; else goto _L3
_L3:
            DataPacketExtension datapacketextension = (DataPacketExtension)stanza.getExtension("data", "http://jabber.org/protocol/ibb");
            stanza = datapacketextension;
            if (datapacketextension == null)
            {
                return false;
            }
              goto _L4
            if (true) goto _L1; else goto _L5
_L5:
        }

        private IBBDataPacketFilter()
        {
            this$0 = InBandBytestreamSession.this;
            super();
        }

    }

    private abstract class IBBInputStream extends InputStream
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
            connection.removeSyncStanzaListener(dataPacketListener);
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
        static int access$402(IBBInputStream ibbinputstream, int i)
        {
            ibbinputstream.readTimeout = i;
            return i;
        }

*/




        public IBBInputStream()
        {
            this$0 = InBandBytestreamSession.this;
            super();
            bufferPointer = -1;
            seq = -1L;
            isClosed = false;
            closeInvoked = false;
            readTimeout = 0;
            connection.addSyncStanzaListener(dataPacketListener, getDataPacketFilter());
        }
    }

    private abstract class IBBOutputStream extends OutputStream
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
            obj = new DataPacketExtension(byteStreamRequest.getSessionID(), seq, ((String) (obj)));
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
            throws IOException, org.jivesoftware.smack.SmackException.NotConnectedException;

        public IBBOutputStream()
        {
            this$0 = InBandBytestreamSession.this;
            super();
            bufferPointer = 0;
            seq = 0L;
            isClosed = false;
            buffer = new byte[byteStreamRequest.getBlockSize()];
        }
    }

    private class IQIBBInputStream extends IBBInputStream
    {

        final InBandBytestreamSession this$0;

        protected StanzaFilter getDataPacketFilter()
        {
            return new AndFilter(new StanzaFilter[] {
                new StanzaTypeFilter(org/jivesoftware/smackx/bytestreams/ibb/packet/Data), new IBBDataPacketFilter()
            });
        }

        protected StanzaListener getDataPacketListener()
        {
            return new StanzaListener() {

                private long lastSequence;
                final IQIBBInputStream this$1;

                public void processPacket(Stanza stanza)
                    throws org.jivesoftware.smack.SmackException.NotConnectedException
                {
                    DataPacketExtension datapacketextension = ((Data)stanza).getDataPacketExtension();
                    if (datapacketextension.getSeq() <= lastSequence)
                    {
                        stanza = IQ.createErrorResponse((IQ)stanza, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.unexpected_request));
                        connection.sendStanza(stanza);
                    } else
                    {
                        if (datapacketextension.getDecodedData() == null)
                        {
                            stanza = IQ.createErrorResponse((IQ)stanza, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.bad_request));
                            connection.sendStanza(stanza);
                            return;
                        }
                        dataQueue.offer(datapacketextension);
                        stanza = IQ.createResultIQ((IQ)stanza);
                        connection.sendStanza(stanza);
                        lastSequence = datapacketextension.getSeq();
                        if (lastSequence == 65535L)
                        {
                            lastSequence = -1L;
                            return;
                        }
                    }
                }

            
            {
                this$1 = IQIBBInputStream.this;
                super();
                lastSequence = -1L;
            }
            };
        }

        private IQIBBInputStream()
        {
            this$0 = InBandBytestreamSession.this;
            super();
        }

    }

    private class IQIBBOutputStream extends IBBOutputStream
    {

        final InBandBytestreamSession this$0;

        protected void writeToXML(DataPacketExtension datapacketextension)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            datapacketextension = new Data(datapacketextension);
            datapacketextension.setTo(remoteJID);
            connection.createPacketCollectorAndSend(datapacketextension).nextResultOrThrow();
_L2:
            this;
            JVM INSTR monitorexit ;
            return;
            datapacketextension;
            if (isClosed) goto _L2; else goto _L1
_L1:
            close();
            IOException ioexception = new IOException();
            ioexception.initCause(datapacketextension);
            throw ioexception;
            datapacketextension;
            this;
            JVM INSTR monitorexit ;
            throw datapacketextension;
        }

        private IQIBBOutputStream()
        {
            this$0 = InBandBytestreamSession.this;
            super();
        }

    }

    private class MessageIBBInputStream extends IBBInputStream
    {

        final InBandBytestreamSession this$0;

        protected StanzaFilter getDataPacketFilter()
        {
            return new AndFilter(new StanzaFilter[] {
                new StanzaTypeFilter(org/jivesoftware/smack/packet/Message), new IBBDataPacketFilter()
            });
        }

        protected StanzaListener getDataPacketListener()
        {
            return new StanzaListener() {

                final MessageIBBInputStream this$1;

                public void processPacket(Stanza stanza)
                {
                    stanza = (DataPacketExtension)stanza.getExtension("data", "http://jabber.org/protocol/ibb");
                    if (stanza.getDecodedData() == null)
                    {
                        return;
                    } else
                    {
                        dataQueue.offer(stanza);
                        return;
                    }
                }

            
            {
                this$1 = MessageIBBInputStream.this;
                super();
            }
            };
        }

        private MessageIBBInputStream()
        {
            this$0 = InBandBytestreamSession.this;
            super();
        }

    }

    private class MessageIBBOutputStream extends IBBOutputStream
    {

        final InBandBytestreamSession this$0;

        protected void writeToXML(DataPacketExtension datapacketextension)
            throws org.jivesoftware.smack.SmackException.NotConnectedException
        {
            this;
            JVM INSTR monitorenter ;
            Message message = new Message(remoteJID);
            message.addExtension(datapacketextension);
            connection.sendStanza(message);
            this;
            JVM INSTR monitorexit ;
            return;
            datapacketextension;
            throw datapacketextension;
        }

        private MessageIBBOutputStream()
        {
            this$0 = InBandBytestreamSession.this;
            super();
        }

    }


    private final Open byteStreamRequest;
    private boolean closeBothStreamsEnabled;
    private final XMPPConnection connection;
    private IBBInputStream inputStream;
    private boolean isClosed;
    private IBBOutputStream outputStream;
    private String remoteJID;

    protected InBandBytestreamSession(XMPPConnection xmppconnection, Open open, String s)
    {
        closeBothStreamsEnabled = false;
        isClosed = false;
        connection = xmppconnection;
        byteStreamRequest = open;
        remoteJID = s;
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smackx$bytestreams$ibb$InBandBytestreamManager$StanzaType[];

            static 
            {
                $SwitchMap$org$jivesoftware$smackx$bytestreams$ibb$InBandBytestreamManager$StanzaType = new int[InBandBytestreamManager.StanzaType.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$bytestreams$ibb$InBandBytestreamManager$StanzaType[org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType.IQ.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$bytestreams$ibb$InBandBytestreamManager$StanzaType[InBandBytestreamManager.StanzaType.MESSAGE.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType[open.getStanza().ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            inputStream = new IQIBBInputStream(null);
            outputStream = new IQIBBOutputStream(null);
            return;

        case 2: // '\002'
            inputStream = new MessageIBBInputStream(null);
            break;
        }
        outputStream = new MessageIBBOutputStream(null);
    }

    public void close()
        throws IOException
    {
        closeByLocal(true);
        closeByLocal(false);
    }

    protected void closeByLocal(boolean flag)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag1 = isClosed;
        if (!flag1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (!closeBothStreamsEnabled) goto _L4; else goto _L3
_L3:
        inputStream.closeInternal();
        outputStream.closeInternal(true);
_L6:
        if (!inputStream.isClosed || !outputStream.isClosed) goto _L1; else goto _L5
_L5:
        Close close1;
        isClosed = true;
        close1 = new Close(byteStreamRequest.getSessionID());
        close1.setTo(remoteJID);
        connection.createPacketCollectorAndSend(close1).nextResultOrThrow();
        inputStream.cleanup();
        InBandBytestreamManager.getByteStreamManager(connection).getSessions().remove(this);
          goto _L1
        Object obj;
        obj;
        throw obj;
_L4:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_146;
        }
        inputStream.closeInternal();
          goto _L6
        outputStream.closeInternal(true);
          goto _L6
        obj;
        IOException ioexception = new IOException();
        ioexception.initCause(((Throwable) (obj)));
        throw ioexception;
          goto _L1
    }

    protected void closeByPeer(Close close1)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        inputStream.closeInternal();
        inputStream.cleanup();
        outputStream.closeInternal(false);
        close1 = IQ.createResultIQ(close1);
        connection.sendStanza(close1);
    }

    public InputStream getInputStream()
    {
        return inputStream;
    }

    public OutputStream getOutputStream()
    {
        return outputStream;
    }

    public int getReadTimeout()
    {
        return inputStream.readTimeout;
    }

    public boolean isCloseBothStreamsEnabled()
    {
        return closeBothStreamsEnabled;
    }

    public void processIQPacket(Data data)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        inputStream.dataPacketListener.processPacket(data);
    }

    public void setCloseBothStreamsEnabled(boolean flag)
    {
        closeBothStreamsEnabled = flag;
    }

    public void setReadTimeout(int i)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException("Timeout must be >= 0");
        } else
        {
            inputStream.readTimeout = i;
            return;
        }
    }



}
