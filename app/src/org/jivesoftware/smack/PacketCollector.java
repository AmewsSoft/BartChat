// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack:
//            XMPPConnection, SmackConfiguration

public class PacketCollector
{
    public static class Configuration
    {

        private PacketCollector collectorToReset;
        private StanzaFilter packetFilter;
        private int size;

        public Configuration setCollectorToReset(PacketCollector packetcollector)
        {
            collectorToReset = packetcollector;
            return this;
        }

        public Configuration setPacketFilter(StanzaFilter stanzafilter)
        {
            return setStanzaFilter(stanzafilter);
        }

        public Configuration setSize(int i)
        {
            size = i;
            return this;
        }

        public Configuration setStanzaFilter(StanzaFilter stanzafilter)
        {
            packetFilter = stanzafilter;
            return this;
        }




        private Configuration()
        {
            size = SmackConfiguration.getPacketCollectorSize();
        }

    }


    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/PacketCollector.getName());
    private boolean cancelled;
    private final PacketCollector collectorToReset;
    private final XMPPConnection connection;
    private final StanzaFilter packetFilter;
    private final ArrayBlockingQueue resultQueue;
    private volatile long waitStart;

    protected PacketCollector(XMPPConnection xmppconnection, Configuration configuration)
    {
        cancelled = false;
        connection = xmppconnection;
        packetFilter = configuration.packetFilter;
        resultQueue = new ArrayBlockingQueue(configuration.size);
        collectorToReset = configuration.collectorToReset;
    }

    public static Configuration newConfiguration()
    {
        return new Configuration();
    }

    private final void throwIfCancelled()
    {
        if (cancelled)
        {
            throw new IllegalStateException("Packet collector already cancelled");
        } else
        {
            return;
        }
    }

    public void cancel()
    {
        if (!cancelled)
        {
            cancelled = true;
            connection.removePacketCollector(this);
        }
    }

    public int getCollectedCount()
    {
        return resultQueue.size();
    }

    public StanzaFilter getPacketFilter()
    {
        return getStanzaFilter();
    }

    public StanzaFilter getStanzaFilter()
    {
        return packetFilter;
    }

    public Stanza nextResult()
    {
        return nextResult(connection.getPacketReplyTimeout());
    }

    public Stanza nextResult(long l)
    {
        Stanza stanza;
        long l1;
        throwIfCancelled();
        stanza = null;
        l1 = l;
        waitStart = System.currentTimeMillis();
_L4:
        Stanza stanza1 = (Stanza)resultQueue.poll(l1, TimeUnit.MILLISECONDS);
        stanza = stanza1;
_L2:
        if (stanza != null)
        {
            return stanza;
        }
        break; /* Loop/switch isn't completed */
        InterruptedException interruptedexception;
        interruptedexception;
        LOGGER.log(Level.FINE, "nextResult was interrupted", interruptedexception);
        if (true) goto _L2; else goto _L1
_L1:
        long l2 = l - (System.currentTimeMillis() - waitStart);
        l1 = l2;
        if (l2 <= 0L)
        {
            return null;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Stanza nextResultBlockForever()
    {
        Stanza stanza;
        throwIfCancelled();
        stanza = null;
_L2:
        if (stanza != null)
        {
            break; /* Loop/switch isn't completed */
        }
        Stanza stanza1 = (Stanza)resultQueue.take();
        stanza = stanza1;
        continue; /* Loop/switch isn't completed */
        InterruptedException interruptedexception;
        interruptedexception;
        LOGGER.log(Level.FINE, "nextResultBlockForever was interrupted", interruptedexception);
        if (true) goto _L2; else goto _L1
_L1:
        return stanza;
    }

    public Stanza nextResultOrThrow()
        throws SmackException.NoResponseException, XMPPException.XMPPErrorException
    {
        return nextResultOrThrow(connection.getPacketReplyTimeout());
    }

    public Stanza nextResultOrThrow(long l)
        throws SmackException.NoResponseException, XMPPException.XMPPErrorException
    {
        Stanza stanza = nextResult(l);
        cancel();
        if (stanza == null)
        {
            throw SmackException.NoResponseException.newWith(connection, this);
        } else
        {
            XMPPException.XMPPErrorException.ifHasErrorThenThrow(stanza);
            return stanza;
        }
    }

    public Stanza pollResult()
    {
        return (Stanza)resultQueue.poll();
    }

    public Stanza pollResultOrThrow()
        throws XMPPException.XMPPErrorException
    {
        Stanza stanza = pollResult();
        if (stanza != null)
        {
            XMPPException.XMPPErrorException.ifHasErrorThenThrow(stanza);
        }
        return stanza;
    }

    protected void processPacket(Stanza stanza)
    {
        if (packetFilter == null || packetFilter.accept(stanza))
        {
            for (; !resultQueue.offer(stanza); resultQueue.poll()) { }
            if (collectorToReset != null)
            {
                collectorToReset.waitStart = System.currentTimeMillis();
            }
        }
    }

}
