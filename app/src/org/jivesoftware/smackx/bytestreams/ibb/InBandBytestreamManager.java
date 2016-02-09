// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.BytestreamManager;
import org.jivesoftware.smackx.bytestreams.BytestreamSession;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InitiationListener, DataListener, CloseListener, InBandBytestreamSession

public class InBandBytestreamManager
    implements BytestreamManager
{
    public static final class StanzaType extends Enum
    {

        private static final StanzaType $VALUES[];
        public static final StanzaType IQ;
        public static final StanzaType MESSAGE;

        public static StanzaType valueOf(String s)
        {
            return (StanzaType)Enum.valueOf(org/jivesoftware/smackx/bytestreams/ibb/InBandBytestreamManager$StanzaType, s);
        }

        public static StanzaType[] values()
        {
            return (StanzaType[])$VALUES.clone();
        }

        static 
        {
            IQ = new StanzaType("IQ", 0);
            MESSAGE = new StanzaType("MESSAGE", 1);
            $VALUES = (new StanzaType[] {
                IQ, MESSAGE
            });
        }

        private StanzaType(String s, int i)
        {
            super(s, i);
        }
    }


    public static final int MAXIMUM_BLOCK_SIZE = 65535;
    private static final String SESSION_ID_PREFIX = "jibb_";
    private static final Map managers = new HashMap();
    private static final Random randomGenerator = new Random();
    private final List allRequestListeners = Collections.synchronizedList(new LinkedList());
    private final CloseListener closeListener = new CloseListener(this);
    private final XMPPConnection connection;
    private final DataListener dataListener = new DataListener(this);
    private int defaultBlockSize;
    private List ignoredBytestreamRequests;
    private final InitiationListener initiationListener = new InitiationListener(this);
    private int maximumBlockSize;
    private final Map sessions = new ConcurrentHashMap();
    private StanzaType stanza;
    private final Map userListeners = new ConcurrentHashMap();

    private InBandBytestreamManager(XMPPConnection xmppconnection)
    {
        defaultBlockSize = 4096;
        maximumBlockSize = 65535;
        stanza = org.jivesoftware.smackx.bytestreams.ibb.StanzaType.IQ;
        ignoredBytestreamRequests = Collections.synchronizedList(new LinkedList());
        connection = xmppconnection;
        xmppconnection.registerIQRequestHandler(initiationListener);
        xmppconnection.registerIQRequestHandler(dataListener);
        xmppconnection.registerIQRequestHandler(closeListener);
    }

    private void disableService()
    {
        managers.remove(connection);
        connection.unregisterIQRequestHandler(initiationListener);
        connection.unregisterIQRequestHandler(dataListener);
        connection.unregisterIQRequestHandler(closeListener);
        initiationListener.shutdown();
        userListeners.clear();
        allRequestListeners.clear();
        sessions.clear();
        ignoredBytestreamRequests.clear();
    }

    public static InBandBytestreamManager getByteStreamManager(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/bytestreams/ibb/InBandBytestreamManager;
        JVM INSTR monitorenter ;
        if (xmppconnection != null) goto _L2; else goto _L1
_L1:
        InBandBytestreamManager inbandbytestreammanager = null;
_L4:
        org/jivesoftware/smackx/bytestreams/ibb/InBandBytestreamManager;
        JVM INSTR monitorexit ;
        return inbandbytestreammanager;
_L2:
        InBandBytestreamManager inbandbytestreammanager1 = (InBandBytestreamManager)managers.get(xmppconnection);
        inbandbytestreammanager = inbandbytestreammanager1;
        if (inbandbytestreammanager1 != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        inbandbytestreammanager = new InBandBytestreamManager(xmppconnection);
        managers.put(xmppconnection, inbandbytestreammanager);
        if (true) goto _L4; else goto _L3
_L3:
        xmppconnection;
        throw xmppconnection;
    }

    private String getNextSessionID()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("jibb_");
        stringbuilder.append(Math.abs(randomGenerator.nextLong()));
        return stringbuilder.toString();
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamlistener)
    {
        allRequestListeners.add(bytestreamlistener);
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamlistener, String s)
    {
        userListeners.put(s, bytestreamlistener);
    }

    public volatile BytestreamSession establishSession(String s)
        throws XMPPException, IOException, InterruptedException, SmackException
    {
        return establishSession(s);
    }

    public volatile BytestreamSession establishSession(String s, String s1)
        throws XMPPException, IOException, InterruptedException, SmackException
    {
        return establishSession(s, s1);
    }

    public InBandBytestreamSession establishSession(String s)
        throws XMPPException, SmackException
    {
        return establishSession(s, getNextSessionID());
    }

    public InBandBytestreamSession establishSession(String s, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        Open open = new Open(s1, defaultBlockSize, stanza);
        open.setTo(s);
        connection.createPacketCollectorAndSend(open).nextResultOrThrow();
        s = new InBandBytestreamSession(connection, open, s);
        sessions.put(s1, s);
        return s;
    }

    protected List getAllRequestListeners()
    {
        return allRequestListeners;
    }

    protected XMPPConnection getConnection()
    {
        return connection;
    }

    public int getDefaultBlockSize()
    {
        return defaultBlockSize;
    }

    protected List getIgnoredBytestreamRequests()
    {
        return ignoredBytestreamRequests;
    }

    public int getMaximumBlockSize()
    {
        return maximumBlockSize;
    }

    protected Map getSessions()
    {
        return sessions;
    }

    public StanzaType getStanza()
    {
        return stanza;
    }

    protected BytestreamListener getUserListener(String s)
    {
        return (BytestreamListener)userListeners.get(s);
    }

    public void ignoreBytestreamRequestOnce(String s)
    {
        ignoredBytestreamRequests.add(s);
    }

    public void removeIncomingBytestreamListener(String s)
    {
        userListeners.remove(s);
    }

    public void removeIncomingBytestreamListener(BytestreamListener bytestreamlistener)
    {
        allRequestListeners.remove(bytestreamlistener);
    }

    protected void replyItemNotFoundPacket(IQ iq)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        iq = IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.item_not_found));
        connection.sendStanza(iq);
    }

    protected void replyRejectPacket(IQ iq)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        iq = IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.not_acceptable));
        connection.sendStanza(iq);
    }

    protected void replyResourceConstraintPacket(IQ iq)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        iq = IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.resource_constraint));
        connection.sendStanza(iq);
    }

    public void setDefaultBlockSize(int i)
    {
        if (i <= 0 || i > 65535)
        {
            throw new IllegalArgumentException("Default block size must be between 1 and 65535");
        } else
        {
            defaultBlockSize = i;
            return;
        }
    }

    public void setMaximumBlockSize(int i)
    {
        if (i <= 0 || i > 65535)
        {
            throw new IllegalArgumentException("Maximum block size must be between 1 and 65535");
        } else
        {
            maximumBlockSize = i;
            return;
        }
    }

    public void setStanza(StanzaType stanzatype)
    {
        stanza = stanzatype;
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                InBandBytestreamManager.getByteStreamManager(xmppconnection);
                xmppconnection.addConnectionListener(xmppconnection. new AbstractConnectionClosedListener() {

                    final _cls1 this$0;
                    final XMPPConnection val$connection;

                    public void connectionTerminated()
                    {
                        InBandBytestreamManager.getByteStreamManager(connection).disableService();
                    }

                    public void reconnectionSuccessful()
                    {
                        InBandBytestreamManager.getByteStreamManager(connection);
                    }

            
            {
                this$0 = final__pcls1;
                connection = XMPPConnection.this;
                super();
            }
                });
            }

        });
    }

}
