// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.compression.XMPPInputOutputStream;
import org.jivesoftware.smack.debugger.SmackDebugger;
import org.jivesoftware.smack.filter.IQReplyFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaIdFilter;
import org.jivesoftware.smack.iqrequest.IQRequestHandler;
import org.jivesoftware.smack.packet.Bind;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Mechanisms;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Session;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.parsing.ParsingExceptionCallback;
import org.jivesoftware.smack.parsing.UnparsablePacket;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.DNSUtil;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smack.util.ParserUtils;
import org.jivesoftware.smack.util.SmackExecutorThreadFactory;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.dns.HostAddress;
import org.jxmpp.util.XmppStringUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smack:
//            XMPPConnection, SmackConfiguration, SynchronizationPoint, SASLAuthentication, 
//            StanzaListener, XMPPConnectionRegistry, ConnectionConfiguration, SmackException, 
//            PacketCollector, ConnectionListener, XMPPException, ExceptionCallback

public abstract class AbstractXMPPConnection
    implements XMPPConnection
{
    protected static class InterceptorWrapper
    {

        private final StanzaFilter packetFilter;
        private final StanzaListener packetInterceptor;

        public boolean filterMatches(Stanza stanza)
        {
            return packetFilter == null || packetFilter.accept(stanza);
        }

        public StanzaListener getInterceptor()
        {
            return packetInterceptor;
        }

        public InterceptorWrapper(StanzaListener stanzalistener, StanzaFilter stanzafilter)
        {
            packetInterceptor = stanzalistener;
            packetFilter = stanzafilter;
        }
    }

    private class ListenerNotification
        implements Runnable
    {

        private final Stanza packet;
        final AbstractXMPPConnection this$0;

        public void run()
        {
            invokePacketCollectorsAndNotifyRecvListeners(packet);
        }

        public ListenerNotification(Stanza stanza)
        {
            this$0 = AbstractXMPPConnection.this;
            super();
            packet = stanza;
        }
    }

    protected static class ListenerWrapper
    {

        private final StanzaFilter packetFilter;
        private final StanzaListener packetListener;

        public boolean filterMatches(Stanza stanza)
        {
            return packetFilter == null || packetFilter.accept(stanza);
        }

        public StanzaListener getListener()
        {
            return packetListener;
        }

        public ListenerWrapper(StanzaListener stanzalistener, StanzaFilter stanzafilter)
        {
            packetListener = stanzalistener;
            packetFilter = stanzafilter;
        }
    }


    static final boolean $assertionsDisabled;
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/AbstractXMPPConnection.getName());
    private static final AtomicInteger connectionCounter = new AtomicInteger(0);
    private static boolean replyToUnknownIqDefault = true;
    private final Map asyncRecvListeners = new LinkedHashMap();
    protected boolean authenticated;
    private final ExecutorService cachedExecutorService;
    private final Collection collectors = new ConcurrentLinkedQueue();
    protected XMPPInputOutputStream compressionHandler;
    protected final ConnectionConfiguration config;
    protected boolean connected;
    protected final int connectionCounterValue;
    protected final Set connectionListeners = new CopyOnWriteArraySet();
    protected final Lock connectionLock = new ReentrantLock();
    protected SmackDebugger debugger;
    private final ThreadPoolExecutor executorService;
    private XMPPConnection.FromMode fromMode;
    private final Map getIqRequestHandler = new HashMap();
    protected String host;
    protected List hostAddresses;
    private final Map interceptors = new HashMap();
    protected final SynchronizationPoint lastFeaturesReceived = new SynchronizationPoint(this);
    private long lastStanzaReceived;
    private long packetReplyTimeout;
    private ParsingExceptionCallback parsingExceptionCallback;
    protected int port;
    protected Reader reader;
    private final ScheduledExecutorService removeCallbacksService;
    private boolean replyToUnkownIq;
    protected SASLAuthentication saslAuthentication;
    protected final SynchronizationPoint saslFeatureReceived = new SynchronizationPoint(this);
    private final Map sendListeners = new HashMap();
    private String serviceName;
    private final Map setIqRequestHandler = new HashMap();
    private final ExecutorService singleThreadedExecutorService = Executors.newSingleThreadExecutor(new SmackExecutorThreadFactory(getConnectionCounter(), "Single Threaded Executor"));
    protected final Map streamFeatures = new HashMap();
    protected String streamId;
    private final Map syncRecvListeners = new LinkedHashMap();
    private String usedPassword;
    private String usedResource;
    private String usedUsername;
    protected String user;
    protected boolean wasAuthenticated;
    protected Writer writer;

    protected AbstractXMPPConnection(ConnectionConfiguration connectionconfiguration)
    {
        connected = false;
        packetReplyTimeout = SmackConfiguration.getDefaultPacketReplyTimeout();
        debugger = null;
        saslAuthentication = new SASLAuthentication(this);
        connectionCounterValue = connectionCounter.getAndIncrement();
        fromMode = XMPPConnection.FromMode.OMITTED;
        parsingExceptionCallback = SmackConfiguration.getDefaultParsingExceptionCallback();
        executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue(100), new SmackExecutorThreadFactory(connectionCounterValue, "Incoming Processor"));
        removeCallbacksService = Executors.newSingleThreadScheduledExecutor(new SmackExecutorThreadFactory(connectionCounterValue, "Remove Callbacks"));
        cachedExecutorService = Executors.newCachedThreadPool(new SmackExecutorThreadFactory(connectionCounterValue, "Cached Executor"));
        authenticated = false;
        wasAuthenticated = false;
        replyToUnkownIq = replyToUnknownIqDefault;
        config = connectionconfiguration;
    }

    private void addStreamFeature(ExtensionElement extensionelement)
    {
        String s = XmppStringUtils.generateKey(extensionelement.getElementName(), extensionelement.getNamespace());
        streamFeatures.put(s, extensionelement);
    }

    private void firePacketInterceptors(Stanza stanza)
    {
        LinkedList linkedlist = new LinkedList();
        Map map = interceptors;
        map;
        JVM INSTR monitorenter ;
        Iterator iterator1 = interceptors.values().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            InterceptorWrapper interceptorwrapper = (InterceptorWrapper)iterator1.next();
            if (interceptorwrapper.filterMatches(stanza))
            {
                linkedlist.add(interceptorwrapper.getInterceptor());
            }
        } while (true);
        break MISSING_BLOCK_LABEL_82;
        stanza;
        map;
        JVM INSTR monitorexit ;
        throw stanza;
        map;
        JVM INSTR monitorexit ;
        for (Iterator iterator = linkedlist.iterator(); iterator.hasNext();)
        {
            StanzaListener stanzalistener = (StanzaListener)iterator.next();
            try
            {
                stanzalistener.processPacket(stanza);
            }
            catch (Exception exception)
            {
                LOGGER.log(Level.SEVERE, "Packet interceptor threw exception", exception);
            }
        }

        return;
    }

    protected static Collection getConnectionCreationListeners()
    {
        return XMPPConnectionRegistry.getConnectionCreationListeners();
    }

    public static void setReplyToUnknownIqDefault(boolean flag)
    {
        replyToUnknownIqDefault = flag;
    }

    public void addAsyncStanzaListener(StanzaListener stanzalistener, StanzaFilter stanzafilter)
    {
        if (stanzalistener == null)
        {
            throw new NullPointerException("Packet listener is null.");
        }
        ListenerWrapper listenerwrapper = new ListenerWrapper(stanzalistener, stanzafilter);
        synchronized (asyncRecvListeners)
        {
            asyncRecvListeners.put(stanzalistener, listenerwrapper);
        }
        return;
        stanzalistener;
        stanzafilter;
        JVM INSTR monitorexit ;
        throw stanzalistener;
    }

    public void addConnectionListener(ConnectionListener connectionlistener)
    {
        if (connectionlistener == null)
        {
            return;
        } else
        {
            connectionListeners.add(connectionlistener);
            return;
        }
    }

    public void addOneTimeSyncCallback(final StanzaListener callback, StanzaFilter stanzafilter)
    {
        callback = new StanzaListener() {

            final AbstractXMPPConnection this$0;
            final StanzaListener val$callback;

            public void processPacket(Stanza stanza)
                throws SmackException.NotConnectedException
            {
                callback.processPacket(stanza);
                removeSyncStanzaListener(this);
                return;
                stanza;
                removeSyncStanzaListener(this);
                throw stanza;
            }

            
            {
                this$0 = AbstractXMPPConnection.this;
                callback = stanzalistener;
                super();
            }
        };
        addSyncStanzaListener(callback, stanzafilter);
        removeCallbacksService.schedule(new Runnable() {

            final AbstractXMPPConnection this$0;
            final StanzaListener val$packetListener;

            public void run()
            {
                removeSyncStanzaListener(packetListener);
            }

            
            {
                this$0 = AbstractXMPPConnection.this;
                packetListener = stanzalistener;
                super();
            }
        }, getPacketReplyTimeout(), TimeUnit.MILLISECONDS);
    }

    public void addPacketInterceptor(StanzaListener stanzalistener, StanzaFilter stanzafilter)
    {
        if (stanzalistener == null)
        {
            throw new NullPointerException("Packet interceptor is null.");
        }
        InterceptorWrapper interceptorwrapper = new InterceptorWrapper(stanzalistener, stanzafilter);
        synchronized (interceptors)
        {
            interceptors.put(stanzalistener, interceptorwrapper);
        }
        return;
        stanzalistener;
        stanzafilter;
        JVM INSTR monitorexit ;
        throw stanzalistener;
    }

    public void addPacketListener(StanzaListener stanzalistener, StanzaFilter stanzafilter)
    {
        addAsyncStanzaListener(stanzalistener, stanzafilter);
    }

    public void addPacketSendingListener(StanzaListener stanzalistener, StanzaFilter stanzafilter)
    {
        if (stanzalistener == null)
        {
            throw new NullPointerException("Packet listener is null.");
        }
        ListenerWrapper listenerwrapper = new ListenerWrapper(stanzalistener, stanzafilter);
        synchronized (sendListeners)
        {
            sendListeners.put(stanzalistener, listenerwrapper);
        }
        return;
        stanzalistener;
        stanzafilter;
        JVM INSTR monitorexit ;
        throw stanzalistener;
    }

    public void addSyncStanzaListener(StanzaListener stanzalistener, StanzaFilter stanzafilter)
    {
        if (stanzalistener == null)
        {
            throw new NullPointerException("Packet listener is null.");
        }
        ListenerWrapper listenerwrapper = new ListenerWrapper(stanzalistener, stanzafilter);
        synchronized (syncRecvListeners)
        {
            syncRecvListeners.put(stanzalistener, listenerwrapper);
        }
        return;
        stanzalistener;
        stanzafilter;
        JVM INSTR monitorexit ;
        throw stanzalistener;
    }

    protected void afterFeaturesReceived()
        throws SmackException.SecurityRequiredException, SmackException.NotConnectedException
    {
    }

    protected void afterSuccessfulLogin(boolean flag)
        throws SmackException.NotConnectedException
    {
        authenticated = true;
        if (config.isDebuggerEnabled() && debugger != null)
        {
            debugger.userHasLogged(user);
        }
        callConnectionAuthenticatedListener(flag);
        if (config.isSendPresence() && !flag)
        {
            sendStanza(new Presence(org.jivesoftware.smack.packet.Presence.Type.available));
        }
    }

    protected final void asyncGo(Runnable runnable)
    {
        cachedExecutorService.execute(runnable);
    }

    protected void bindResourceAndEstablishSession(String s)
        throws XMPPException.XMPPErrorException, IOException, SmackException
    {
        LOGGER.finer("Waiting for last features to be received before continuing with resource binding");
        lastFeaturesReceived.checkIfSuccessOrWait();
        if (!hasFeature("bind", "urn:ietf:params:xml:ns:xmpp-bind"))
        {
            throw new SmackException.ResourceBindingNotOfferedException();
        }
        s = Bind.newSet(s);
        user = ((Bind)createPacketCollectorAndSend(new StanzaIdFilter(s), s).nextResultOrThrow()).getJid();
        serviceName = XmppStringUtils.parseDomain(user);
        s = (org.jivesoftware.smack.packet.Session.Feature)getFeature("session", "urn:ietf:params:xml:ns:xmpp-session");
        if (s != null && !s.isOptional() && !getConfiguration().isLegacySessionDisabled())
        {
            s = new Session();
            createPacketCollectorAndSend(new StanzaIdFilter(s), s).nextResultOrThrow();
        }
    }

    protected void callConnectionAuthenticatedListener(boolean flag)
    {
        for (Iterator iterator = connectionListeners.iterator(); iterator.hasNext();)
        {
            ConnectionListener connectionlistener = (ConnectionListener)iterator.next();
            try
            {
                connectionlistener.authenticated(this, flag);
            }
            catch (Exception exception)
            {
                LOGGER.log(Level.SEVERE, "Exception in authenticated listener", exception);
            }
        }

    }

    void callConnectionClosedListener()
    {
        for (Iterator iterator = connectionListeners.iterator(); iterator.hasNext();)
        {
            ConnectionListener connectionlistener = (ConnectionListener)iterator.next();
            try
            {
                connectionlistener.connectionClosed();
            }
            catch (Exception exception)
            {
                LOGGER.log(Level.SEVERE, "Error in listener while closing connection", exception);
            }
        }

    }

    protected void callConnectionClosedOnErrorListener(Exception exception)
    {
        LOGGER.log(Level.WARNING, "Connection closed with error", exception);
        for (Iterator iterator = connectionListeners.iterator(); iterator.hasNext();)
        {
            ConnectionListener connectionlistener = (ConnectionListener)iterator.next();
            try
            {
                connectionlistener.connectionClosedOnError(exception);
            }
            catch (Exception exception1)
            {
                LOGGER.log(Level.SEVERE, "Error in listener while closing connection", exception1);
            }
        }

    }

    protected void callConnectionConnectedListener()
    {
        for (Iterator iterator = connectionListeners.iterator(); iterator.hasNext(); ((ConnectionListener)iterator.next()).connected(this)) { }
    }

    public AbstractXMPPConnection connect()
        throws SmackException, IOException, XMPPException
    {
        this;
        JVM INSTR monitorenter ;
        throwAlreadyConnectedExceptionIfAppropriate();
        saslAuthentication.init();
        saslFeatureReceived.init();
        lastFeaturesReceived.init();
        streamId = null;
        connectInternal();
        this;
        JVM INSTR monitorexit ;
        return this;
        Exception exception;
        exception;
        throw exception;
    }

    protected abstract void connectInternal()
        throws SmackException, IOException, XMPPException;

    public PacketCollector createPacketCollector(PacketCollector.Configuration configuration)
    {
        configuration = new PacketCollector(this, configuration);
        collectors.add(configuration);
        return configuration;
    }

    public PacketCollector createPacketCollector(StanzaFilter stanzafilter)
    {
        return createPacketCollector(PacketCollector.newConfiguration().setStanzaFilter(stanzafilter));
    }

    public PacketCollector createPacketCollectorAndSend(StanzaFilter stanzafilter, Stanza stanza)
        throws SmackException.NotConnectedException
    {
        PacketCollector packetcollector = createPacketCollector(stanzafilter);
        sendStanza(stanza);
        return packetcollector;
        stanzafilter;
_L2:
        packetcollector.cancel();
        throw stanzafilter;
        stanzafilter;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public PacketCollector createPacketCollectorAndSend(IQ iq)
        throws SmackException.NotConnectedException
    {
        return createPacketCollectorAndSend(((StanzaFilter) (new IQReplyFilter(iq, this))), ((Stanza) (iq)));
    }

    public void disconnect()
    {
        try
        {
            disconnect(new Presence(org.jivesoftware.smack.packet.Presence.Type.unavailable));
            return;
        }
        catch (SmackException.NotConnectedException notconnectedexception)
        {
            LOGGER.log(Level.FINEST, "Connection is already disconnected", notconnectedexception);
        }
    }

    public void disconnect(Presence presence)
        throws SmackException.NotConnectedException
    {
        this;
        JVM INSTR monitorenter ;
        sendStanza(presence);
        shutdown();
        callConnectionClosedListener();
        this;
        JVM INSTR monitorexit ;
        return;
        presence;
        throw presence;
    }

    protected void finalize()
        throws Throwable
    {
        LOGGER.fine((new StringBuilder()).append("finalizing XMPPConnection ( ").append(getConnectionCounter()).append("): Shutting down executor services").toString());
        executorService.shutdownNow();
        cachedExecutorService.shutdown();
        removeCallbacksService.shutdownNow();
        singleThreadedExecutorService.shutdownNow();
        super.finalize();
        return;
        Object obj;
        obj;
        LOGGER.log(Level.WARNING, "finalize() threw trhowable", ((Throwable) (obj)));
        super.finalize();
        return;
        obj;
        super.finalize();
        throw obj;
    }

    protected void firePacketSendingListeners(final Stanza packet)
    {
        final LinkedList listenersToNotify = new LinkedList();
        Map map = sendListeners;
        map;
        JVM INSTR monitorenter ;
        Iterator iterator = sendListeners.values().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            ListenerWrapper listenerwrapper = (ListenerWrapper)iterator.next();
            if (listenerwrapper.filterMatches(packet))
            {
                listenersToNotify.add(listenerwrapper.getListener());
            }
        } while (true);
        break MISSING_BLOCK_LABEL_82;
        packet;
        map;
        JVM INSTR monitorexit ;
        throw packet;
        map;
        JVM INSTR monitorexit ;
        if (listenersToNotify.isEmpty())
        {
            return;
        } else
        {
            asyncGo(new Runnable() {

                final AbstractXMPPConnection this$0;
                final List val$listenersToNotify;
                final Stanza val$packet;

                public void run()
                {
                    for (Iterator iterator1 = listenersToNotify.iterator(); iterator1.hasNext();)
                    {
                        StanzaListener stanzalistener = (StanzaListener)iterator1.next();
                        try
                        {
                            stanzalistener.processPacket(packet);
                        }
                        catch (Exception exception)
                        {
                            AbstractXMPPConnection.LOGGER.log(Level.WARNING, "Sending listener threw exception", exception);
                        }
                    }

                }

            
            {
                this$0 = AbstractXMPPConnection.this;
                listenersToNotify = list;
                packet = stanza;
                super();
            }
            });
            return;
        }
    }

    protected ConnectionConfiguration getConfiguration()
    {
        return config;
    }

    public int getConnectionCounter()
    {
        return connectionCounterValue;
    }

    protected Lock getConnectionLock()
    {
        return connectionLock;
    }

    public ExtensionElement getFeature(String s, String s1)
    {
        return (ExtensionElement)streamFeatures.get(XmppStringUtils.generateKey(s, s1));
    }

    public XMPPConnection.FromMode getFromMode()
    {
        return fromMode;
    }

    public String getHost()
    {
        return host;
    }

    public long getLastStanzaReceived()
    {
        return lastStanzaReceived;
    }

    public long getPacketReplyTimeout()
    {
        return packetReplyTimeout;
    }

    public ParsingExceptionCallback getParsingExceptionCallback()
    {
        return parsingExceptionCallback;
    }

    public int getPort()
    {
        return port;
    }

    protected SASLAuthentication getSASLAuthentication()
    {
        return saslAuthentication;
    }

    public String getServiceName()
    {
        if (serviceName != null)
        {
            return serviceName;
        } else
        {
            return config.getServiceName();
        }
    }

    public String getStreamId()
    {
        if (!isConnected())
        {
            return null;
        } else
        {
            return streamId;
        }
    }

    public final String getUser()
    {
        return user;
    }

    public boolean hasFeature(String s, String s1)
    {
        return getFeature(s, s1) != null;
    }

    protected void initDebugger()
    {
label0:
        {
            if (reader == null || writer == null)
            {
                throw new NullPointerException("Reader or writer isn't initialized.");
            }
            if (config.isDebuggerEnabled())
            {
                if (debugger == null)
                {
                    debugger = SmackConfiguration.createDebugger(this, writer, reader);
                }
                if (debugger != null)
                {
                    break label0;
                }
                LOGGER.severe("Debugging enabled but could not find debugger class");
            }
            return;
        }
        reader = debugger.newConnectionReader(reader);
        writer = debugger.newConnectionWriter(writer);
    }

    protected void invokePacketCollectorsAndNotifyRecvListeners(final Stanza packet)
    {
        if (!(packet instanceof IQ)) goto _L2; else goto _L1
_L1:
        final Object iq;
        Object obj;
        iq = (IQ)packet;
        obj = ((IQ) (iq)).getType();
        static class _cls9
        {

            static final int $SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode[];
            static final int $SwitchMap$org$jivesoftware$smack$iqrequest$IQRequestHandler$Mode[];
            static final int $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$iqrequest$IQRequestHandler$Mode = new int[org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$iqrequest$IQRequestHandler$Mode[org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.sync.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$iqrequest$IQRequestHandler$Mode[org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type = new int[org.jivesoftware.smack.packet.IQ.Type.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[org.jivesoftware.smack.packet.IQ.Type.set.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[org.jivesoftware.smack.packet.IQ.Type.get.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                $SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode = new int[XMPPConnection.FromMode.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode[XMPPConnection.FromMode.OMITTED.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode[XMPPConnection.FromMode.USER.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$XMPPConnection$FromMode[XMPPConnection.FromMode.UNCHANGED.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls9..SwitchMap.org.jivesoftware.smack.packet.IQ.Type[((org.jivesoftware.smack.packet.IQ.Type) (obj)).ordinal()];
        JVM INSTR tableswitch 1 2: default 48
    //                   1 130
    //                   2 130;
           goto _L2 _L3 _L3
_L2:
        final Object finalIqRequestHandler = new LinkedList();
        iq = asyncRecvListeners;
        iq;
        JVM INSTR monitorenter ;
        obj = asyncRecvListeners.values().iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            ListenerWrapper listenerwrapper = (ListenerWrapper)((Iterator) (obj)).next();
            if (listenerwrapper.filterMatches(packet))
            {
                ((Collection) (finalIqRequestHandler)).add(listenerwrapper.getListener());
            }
        } while (true);
          goto _L4
        packet;
        iq;
        JVM INSTR monitorexit ;
        throw packet;
_L3:
        finalIqRequestHandler = XmppStringUtils.generateKey(((IQ) (iq)).getChildElementName(), ((IQ) (iq)).getChildElementNamespace());
        _cls9..SwitchMap.org.jivesoftware.smack.packet.IQ.Type[((org.jivesoftware.smack.packet.IQ.Type) (obj)).ordinal()];
        JVM INSTR tableswitch 1 2: default 172
    //                   1 183
    //                   2 227;
           goto _L5 _L6 _L7
_L5:
        throw new IllegalStateException("Should only encounter IQ type 'get' or 'set'");
_L6:
        synchronized (setIqRequestHandler)
        {
            finalIqRequestHandler = (IQRequestHandler)setIqRequestHandler.get(finalIqRequestHandler);
        }
_L9:
        if (finalIqRequestHandler != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!replyToUnkownIq)
        {
            return;
        }
        break; /* Loop/switch isn't completed */
        packet;
        map;
        JVM INSTR monitorexit ;
        throw packet;
_L7:
        synchronized (getIqRequestHandler)
        {
            finalIqRequestHandler = (IQRequestHandler)getIqRequestHandler.get(finalIqRequestHandler);
        }
        if (true) goto _L9; else goto _L8
        packet;
        map1;
        JVM INSTR monitorexit ;
        throw packet;
_L8:
        finalIqRequestHandler = IQ.createErrorResponse(((IQ) (iq)), new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.feature_not_implemented));
        try
        {
            sendStanza(((Stanza) (finalIqRequestHandler)));
        }
        // Misplaced declaration of an exception variable
        catch (final Object finalIqRequestHandler)
        {
            LOGGER.log(Level.WARNING, "NotConnectedException while sending error IQ to unkown IQ request", ((Throwable) (finalIqRequestHandler)));
        }
        if (true) goto _L2; else goto _L10
_L10:
        packet = null;
        _cls9..SwitchMap.org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode[((IQRequestHandler) (finalIqRequestHandler)).getMode().ordinal()];
        JVM INSTR tableswitch 1 2: default 340
    //                   1 357
    //                   2 365;
           goto _L11 _L12 _L13
_L11:
        packet.execute(new Runnable() {

            final AbstractXMPPConnection this$0;
            final IQRequestHandler val$finalIqRequestHandler;
            final IQ val$iq;

            public void run()
            {
                IQ iq1 = finalIqRequestHandler.handleIQRequest(iq);
                if (iq1 == null)
                {
                    return;
                }
                try
                {
                    sendStanza(iq1);
                    return;
                }
                catch (SmackException.NotConnectedException notconnectedexception)
                {
                    AbstractXMPPConnection.LOGGER.log(Level.WARNING, "NotConnectedException while sending response to IQ request", notconnectedexception);
                }
            }

            
            {
                this$0 = AbstractXMPPConnection.this;
                finalIqRequestHandler = iqrequesthandler;
                iq = iq1;
                super();
            }
        });
        return;
_L12:
        packet = singleThreadedExecutorService;
        continue; /* Loop/switch isn't completed */
_L13:
        packet = cachedExecutorService;
        if (true) goto _L11; else goto _L4
_L4:
        iq;
        JVM INSTR monitorexit ;
        for (iq = ((Collection) (finalIqRequestHandler)).iterator(); ((Iterator) (iq)).hasNext(); asyncGo(new Runnable() {

        final AbstractXMPPConnection this$0;
        final StanzaListener val$listener;
        final Stanza val$packet;

        public void run()
        {
            try
            {
                listener.processPacket(packet);
                return;
            }
            catch (Exception exception)
            {
                AbstractXMPPConnection.LOGGER.log(Level.SEVERE, "Exception in async packet listener", exception);
            }
        }

            
            {
                this$0 = AbstractXMPPConnection.this;
                listener = stanzalistener;
                packet = stanza;
                super();
            }
    })) { }
        for (iq = collectors.iterator(); ((Iterator) (iq)).hasNext(); ((PacketCollector)((Iterator) (iq)).next()).processPacket(packet)) { }
        ((Collection) (finalIqRequestHandler)).clear();
        iq = syncRecvListeners;
        iq;
        JVM INSTR monitorenter ;
        Iterator iterator = syncRecvListeners.values().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            ListenerWrapper listenerwrapper1 = (ListenerWrapper)iterator.next();
            if (listenerwrapper1.filterMatches(packet))
            {
                ((Collection) (finalIqRequestHandler)).add(listenerwrapper1.getListener());
            }
        } while (true);
          goto _L14
        packet;
        iq;
        JVM INSTR monitorexit ;
        throw packet;
_L14:
        iq;
        JVM INSTR monitorexit ;
        singleThreadedExecutorService.execute(new Runnable() {

            final AbstractXMPPConnection this$0;
            final Collection val$listenersToNotify;
            final Stanza val$packet;

            public void run()
            {
                Iterator iterator1 = listenersToNotify.iterator();
_L2:
                StanzaListener stanzalistener;
                if (!iterator1.hasNext())
                {
                    break MISSING_BLOCK_LABEL_55;
                }
                stanzalistener = (StanzaListener)iterator1.next();
                stanzalistener.processPacket(packet);
                continue; /* Loop/switch isn't completed */
                SmackException.NotConnectedException notconnectedexception;
                notconnectedexception;
                AbstractXMPPConnection.LOGGER.log(Level.WARNING, "Got not connected exception, aborting", notconnectedexception);
                return;
                Exception exception;
                exception;
                AbstractXMPPConnection.LOGGER.log(Level.SEVERE, "Exception in packet listener", exception);
                if (true) goto _L2; else goto _L1
_L1:
            }

            
            {
                this$0 = AbstractXMPPConnection.this;
                listenersToNotify = collection;
                packet = stanza;
                super();
            }
        });
        return;
    }

    public final boolean isAnonymous()
    {
        return config.getUsername() == null && usedUsername == null && !config.allowNullOrEmptyUsername;
    }

    public final boolean isAuthenticated()
    {
        return authenticated;
    }

    public final boolean isConnected()
    {
        return connected;
    }

    public abstract boolean isSecureConnection();

    public abstract boolean isUsingCompression();

    public void login()
        throws XMPPException, SmackException, IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (!isAnonymous()) goto _L2; else goto _L1
_L1:
        throwNotConnectedExceptionIfAppropriate();
        throwAlreadyLoggedInExceptionIfAppropriate();
        loginAnonymously();
_L7:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (usedUsername == null) goto _L4; else goto _L3
_L3:
        Object obj = usedUsername;
_L8:
        if (usedPassword == null) goto _L6; else goto _L5
_L5:
        String s = usedPassword;
_L9:
        String s1;
        if (usedResource == null)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        s1 = usedResource;
_L10:
        login(((CharSequence) (obj)), s, s1);
          goto _L7
        obj;
        throw obj;
_L4:
        obj = config.getUsername();
          goto _L8
_L6:
        s = config.getPassword();
          goto _L9
        s1 = config.getResource();
          goto _L10
    }

    public void login(CharSequence charsequence, String s)
        throws XMPPException, SmackException, IOException
    {
        this;
        JVM INSTR monitorenter ;
        login(charsequence, s, config.getResource());
        this;
        JVM INSTR monitorexit ;
        return;
        charsequence;
        throw charsequence;
    }

    public void login(CharSequence charsequence, String s, String s1)
        throws XMPPException, SmackException, IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (!config.allowNullOrEmptyUsername)
        {
            StringUtils.requireNotNullOrEmpty(charsequence, "Username must not be null or empty");
        }
        throwNotConnectedExceptionIfAppropriate();
        throwAlreadyLoggedInExceptionIfAppropriate();
        if (charsequence == null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        charsequence = charsequence.toString();
_L1:
        usedUsername = charsequence;
        usedPassword = s;
        usedResource = s1;
        loginNonAnonymously(usedUsername, usedPassword, usedResource);
        this;
        JVM INSTR monitorexit ;
        return;
        charsequence = null;
          goto _L1
        charsequence;
        throw charsequence;
    }

    protected abstract void loginAnonymously()
        throws XMPPException, SmackException, IOException;

    protected abstract void loginNonAnonymously(String s, String s1, String s2)
        throws XMPPException, SmackException, IOException;

    protected void notifyReconnection()
    {
        for (Iterator iterator = connectionListeners.iterator(); iterator.hasNext();)
        {
            ConnectionListener connectionlistener = (ConnectionListener)iterator.next();
            try
            {
                connectionlistener.reconnectionSuccessful();
            }
            catch (Exception exception)
            {
                LOGGER.log(Level.WARNING, "notifyReconnection()", exception);
            }
        }

    }

    protected void parseAndProcessStanza(XmlPullParser xmlpullparser)
        throws Exception
    {
        Object obj;
        int i;
        ParserUtils.assertAtStartTag(xmlpullparser);
        i = xmlpullparser.getDepth();
        obj = null;
        Stanza stanza = PacketParserUtils.parseStanza(xmlpullparser);
_L2:
        ParserUtils.assertAtEndTag(xmlpullparser);
        if (stanza != null)
        {
            processPacket(stanza);
        }
        return;
        Exception exception;
        exception;
        UnparsablePacket unparsablepacket = new UnparsablePacket(PacketParserUtils.parseContentDepth(xmlpullparser, i), exception);
        ParsingExceptionCallback parsingexceptioncallback = getParsingExceptionCallback();
        exception = obj;
        if (parsingexceptioncallback != null)
        {
            parsingexceptioncallback.handleUnparsablePacket(unparsablepacket);
            exception = obj;
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected final void parseFeatures(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        int j;
        streamFeatures.clear();
        j = xmlpullparser.getDepth();
_L13:
        Object obj;
        Object obj1;
        String s;
        int i;
        i = xmlpullparser.next();
        if (i != 2 || xmlpullparser.getDepth() != j + 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = null;
        obj1 = xmlpullparser.getName();
        s = xmlpullparser.getNamespace();
        i = -1;
        ((String) (obj1)).hashCode();
        JVM INSTR lookupswitch 5: default 120
    //                   -676919238: 204
    //                   3023933: 220
    //                   1316817241: 188
    //                   1431984486: 252
    //                   1984987798: 236;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        i;
        JVM INSTR tableswitch 0 4: default 156
    //                   0 268
    //                   1 276
    //                   2 291
    //                   3 298
    //                   4 306;
           goto _L7 _L8 _L9 _L10 _L11 _L12
_L7:
        obj1 = ProviderManager.getStreamFeatureProvider(((String) (obj1)), s);
        if (obj1 != null)
        {
            obj = (ExtensionElement)((ExtensionElementProvider) (obj1)).parse(xmlpullparser);
        }
_L14:
        if (obj != null)
        {
            addStreamFeature(((ExtensionElement) (obj)));
        }
          goto _L13
_L4:
        if (((String) (obj1)).equals("starttls"))
        {
            i = 0;
        }
          goto _L1
_L2:
        if (((String) (obj1)).equals("mechanisms"))
        {
            i = 1;
        }
          goto _L1
_L3:
        if (((String) (obj1)).equals("bind"))
        {
            i = 2;
        }
          goto _L1
_L6:
        if (((String) (obj1)).equals("session"))
        {
            i = 3;
        }
          goto _L1
_L5:
        if (((String) (obj1)).equals("compression"))
        {
            i = 4;
        }
          goto _L1
_L8:
        obj = PacketParserUtils.parseStartTlsFeature(xmlpullparser);
          goto _L14
_L9:
        obj = new Mechanisms(PacketParserUtils.parseMechanisms(xmlpullparser));
          goto _L14
_L10:
        obj = org.jivesoftware.smack.packet.Bind.Feature.INSTANCE;
          goto _L14
_L11:
        obj = PacketParserUtils.parseSessionFeature(xmlpullparser);
          goto _L14
_L12:
        obj = PacketParserUtils.parseCompressionFeature(xmlpullparser);
          goto _L14
        if (i != 3 || xmlpullparser.getDepth() != j) goto _L13; else goto _L15
_L15:
        if (hasFeature("mechanisms", "urn:ietf:params:xml:ns:xmpp-sasl") && (!hasFeature("starttls", "urn:ietf:params:xml:ns:xmpp-tls") || config.getSecurityMode() == ConnectionConfiguration.SecurityMode.disabled))
        {
            saslFeatureReceived.reportSuccess();
        }
        if (hasFeature("bind", "urn:ietf:params:xml:ns:xmpp-bind") && (!hasFeature("compression", "http://jabber.org/protocol/compress") || !config.isCompressionEnabled()))
        {
            lastFeaturesReceived.reportSuccess();
        }
        afterFeaturesReceived();
        return;
    }

    protected List populateHostAddresses()
    {
        LinkedList linkedlist = new LinkedList();
        if (config.host != null)
        {
            hostAddresses = new ArrayList(1);
            HostAddress hostaddress = new HostAddress(config.host, config.port);
            hostAddresses.add(hostaddress);
        } else
        {
            hostAddresses = DNSUtil.resolveXMPPDomain(config.serviceName, linkedlist);
        }
        if (!$assertionsDisabled && hostAddresses.isEmpty())
        {
            throw new AssertionError();
        } else
        {
            return linkedlist;
        }
    }

    protected void processPacket(Stanza stanza)
    {
        if (!$assertionsDisabled && stanza == null)
        {
            throw new AssertionError();
        } else
        {
            lastStanzaReceived = System.currentTimeMillis();
            executorService.submit(new ListenerNotification(stanza));
            return;
        }
    }

    public IQRequestHandler registerIQRequestHandler(IQRequestHandler iqrequesthandler)
    {
        Map map1;
        String s;
        s = XmppStringUtils.generateKey(iqrequesthandler.getElement(), iqrequesthandler.getNamespace());
        switch (_cls9..SwitchMap.org.jivesoftware.smack.packet.IQ.Type[iqrequesthandler.getType().ordinal()])
        {
        default:
            throw new IllegalArgumentException("Only IQ type of 'get' and 'set' allowed");

        case 1: // '\001'
            synchronized (setIqRequestHandler)
            {
                iqrequesthandler = (IQRequestHandler)setIqRequestHandler.put(s, iqrequesthandler);
            }
            return iqrequesthandler;

        case 2: // '\002'
            map1 = getIqRequestHandler;
            break;
        }
        break MISSING_BLOCK_LABEL_99;
        iqrequesthandler;
        map;
        JVM INSTR monitorexit ;
        throw iqrequesthandler;
        map1;
        JVM INSTR monitorenter ;
        iqrequesthandler = (IQRequestHandler)getIqRequestHandler.put(s, iqrequesthandler);
        map1;
        JVM INSTR monitorexit ;
        return iqrequesthandler;
        iqrequesthandler;
        map1;
        JVM INSTR monitorexit ;
        throw iqrequesthandler;
    }

    public boolean removeAsyncStanzaListener(StanzaListener stanzalistener)
    {
        Map map = asyncRecvListeners;
        map;
        JVM INSTR monitorenter ;
        boolean flag;
        if (asyncRecvListeners.remove(stanzalistener) != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        map;
        JVM INSTR monitorexit ;
        return flag;
        stanzalistener;
        map;
        JVM INSTR monitorexit ;
        throw stanzalistener;
    }

    public void removeConnectionListener(ConnectionListener connectionlistener)
    {
        connectionListeners.remove(connectionlistener);
    }

    public void removePacketCollector(PacketCollector packetcollector)
    {
        collectors.remove(packetcollector);
    }

    public void removePacketInterceptor(StanzaListener stanzalistener)
    {
        synchronized (interceptors)
        {
            interceptors.remove(stanzalistener);
        }
        return;
        stanzalistener;
        map;
        JVM INSTR monitorexit ;
        throw stanzalistener;
    }

    public boolean removePacketListener(StanzaListener stanzalistener)
    {
        return removeAsyncStanzaListener(stanzalistener);
    }

    public void removePacketSendingListener(StanzaListener stanzalistener)
    {
        synchronized (sendListeners)
        {
            sendListeners.remove(stanzalistener);
        }
        return;
        stanzalistener;
        map;
        JVM INSTR monitorexit ;
        throw stanzalistener;
    }

    public boolean removeSyncStanzaListener(StanzaListener stanzalistener)
    {
        Map map = syncRecvListeners;
        map;
        JVM INSTR monitorenter ;
        boolean flag;
        if (syncRecvListeners.remove(stanzalistener) != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        map;
        JVM INSTR monitorexit ;
        return flag;
        stanzalistener;
        map;
        JVM INSTR monitorexit ;
        throw stanzalistener;
    }

    protected final ScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
    {
        return removeCallbacksService.schedule(runnable, l, timeunit);
    }

    public abstract void send(PlainStreamElement plainstreamelement)
        throws SmackException.NotConnectedException;

    public void sendIqWithResponseCallback(IQ iq, StanzaListener stanzalistener)
        throws SmackException.NotConnectedException
    {
        sendIqWithResponseCallback(iq, stanzalistener, null);
    }

    public void sendIqWithResponseCallback(IQ iq, StanzaListener stanzalistener, ExceptionCallback exceptioncallback)
        throws SmackException.NotConnectedException
    {
        sendIqWithResponseCallback(iq, stanzalistener, exceptioncallback, getPacketReplyTimeout());
    }

    public void sendIqWithResponseCallback(IQ iq, StanzaListener stanzalistener, ExceptionCallback exceptioncallback, long l)
        throws SmackException.NotConnectedException
    {
        sendStanzaWithResponseCallback(iq, new IQReplyFilter(iq, this), stanzalistener, exceptioncallback, l);
    }

    public void sendPacket(Stanza stanza)
        throws SmackException.NotConnectedException
    {
        sendStanza(stanza);
    }

    public void sendStanza(Stanza stanza)
        throws SmackException.NotConnectedException
    {
        Objects.requireNonNull(stanza, "Packet must not be null");
        throwNotConnectedExceptionIfAppropriate();
        _cls9..SwitchMap.org.jivesoftware.smack.XMPPConnection.FromMode[fromMode.ordinal()];
        JVM INSTR tableswitch 1 2: default 44
    //                   1 55
    //                   2 63;
           goto _L1 _L2 _L3
_L1:
        firePacketInterceptors(stanza);
        sendStanzaInternal(stanza);
        return;
_L2:
        stanza.setFrom(null);
        continue; /* Loop/switch isn't completed */
_L3:
        stanza.setFrom(getUser());
        if (true) goto _L1; else goto _L4
_L4:
    }

    protected abstract void sendStanzaInternal(Stanza stanza)
        throws SmackException.NotConnectedException;

    public void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzafilter, StanzaListener stanzalistener)
        throws SmackException.NotConnectedException
    {
        sendStanzaWithResponseCallback(stanza, stanzafilter, stanzalistener, null);
    }

    public void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzafilter, StanzaListener stanzalistener, ExceptionCallback exceptioncallback)
        throws SmackException.NotConnectedException
    {
        sendStanzaWithResponseCallback(stanza, stanzafilter, stanzalistener, exceptioncallback, getPacketReplyTimeout());
    }

    public void sendStanzaWithResponseCallback(Stanza stanza, final StanzaFilter replyFilter, final StanzaListener callback, final ExceptionCallback exceptionCallback, long l)
        throws SmackException.NotConnectedException
    {
        Objects.requireNonNull(stanza, "stanza must not be null");
        Objects.requireNonNull(replyFilter, "replyFilter must not be null");
        Objects.requireNonNull(callback, "callback must not be null");
        callback = new StanzaListener() {

            final AbstractXMPPConnection this$0;
            final StanzaListener val$callback;
            final ExceptionCallback val$exceptionCallback;

            public void processPacket(Stanza stanza1)
                throws SmackException.NotConnectedException
            {
                XMPPException.XMPPErrorException.ifHasErrorThenThrow(stanza1);
                callback.processPacket(stanza1);
                removeAsyncStanzaListener(this);
                return;
                stanza1;
                if (exceptionCallback != null)
                {
                    exceptionCallback.processException(stanza1);
                }
                removeAsyncStanzaListener(this);
                return;
                stanza1;
                removeAsyncStanzaListener(this);
                throw stanza1;
            }

            
            {
                this$0 = AbstractXMPPConnection.this;
                callback = stanzalistener;
                exceptionCallback = exceptioncallback;
                super();
            }
        };
        removeCallbacksService.schedule(new Runnable() {

            final AbstractXMPPConnection this$0;
            final ExceptionCallback val$exceptionCallback;
            final StanzaListener val$packetListener;
            final StanzaFilter val$replyFilter;

            public void run()
            {
                if (removeAsyncStanzaListener(packetListener) && exceptionCallback != null)
                {
                    exceptionCallback.processException(SmackException.NoResponseException.newWith(AbstractXMPPConnection.this, replyFilter));
                }
            }

            
            {
                this$0 = AbstractXMPPConnection.this;
                packetListener = stanzalistener;
                exceptionCallback = exceptioncallback;
                replyFilter = stanzafilter;
                super();
            }
        }, l, TimeUnit.MILLISECONDS);
        addAsyncStanzaListener(callback, replyFilter);
        sendStanza(stanza);
    }

    public void setFromMode(XMPPConnection.FromMode frommode)
    {
        fromMode = frommode;
    }

    public void setPacketReplyTimeout(long l)
    {
        packetReplyTimeout = l;
    }

    public void setParsingExceptionCallback(ParsingExceptionCallback parsingexceptioncallback)
    {
        parsingExceptionCallback = parsingexceptioncallback;
    }

    public void setReplyToUnknownIq(boolean flag)
    {
        replyToUnkownIq = flag;
    }

    protected void setWasAuthenticated()
    {
        if (!wasAuthenticated)
        {
            wasAuthenticated = authenticated;
        }
    }

    protected abstract void shutdown();

    protected void throwAlreadyConnectedExceptionIfAppropriate()
        throws SmackException.AlreadyConnectedException
    {
        if (isConnected())
        {
            throw new SmackException.AlreadyConnectedException();
        } else
        {
            return;
        }
    }

    protected void throwAlreadyLoggedInExceptionIfAppropriate()
        throws SmackException.AlreadyLoggedInException
    {
        if (isAuthenticated())
        {
            throw new SmackException.AlreadyLoggedInException();
        } else
        {
            return;
        }
    }

    protected void throwNotConnectedExceptionIfAppropriate()
        throws SmackException.NotConnectedException
    {
        if (!isConnected())
        {
            throw new SmackException.NotConnectedException();
        } else
        {
            return;
        }
    }

    public IQRequestHandler unregisterIQRequestHandler(String s, String s1, org.jivesoftware.smack.packet.IQ.Type type)
    {
        s = XmppStringUtils.generateKey(s, s1);
        switch (_cls9..SwitchMap.org.jivesoftware.smack.packet.IQ.Type[type.ordinal()])
        {
        default:
            throw new IllegalArgumentException("Only IQ type of 'get' and 'set' allowed");

        case 1: // '\001'
            synchronized (setIqRequestHandler)
            {
                s = (IQRequestHandler)setIqRequestHandler.remove(s);
            }
            return s;

        case 2: // '\002'
            s1 = getIqRequestHandler;
            break;
        }
        break MISSING_BLOCK_LABEL_82;
        s;
        s1;
        JVM INSTR monitorexit ;
        throw s;
        s1;
        JVM INSTR monitorenter ;
        s = (IQRequestHandler)getIqRequestHandler.remove(s);
        s1;
        JVM INSTR monitorexit ;
        return s;
        s;
        s1;
        JVM INSTR monitorexit ;
        throw s;
    }

    public final IQRequestHandler unregisterIQRequestHandler(IQRequestHandler iqrequesthandler)
    {
        return unregisterIQRequestHandler(iqrequesthandler.getElement(), iqrequesthandler.getNamespace(), iqrequesthandler.getType());
    }

    static 
    {
        boolean flag;
        if (!org/jivesoftware/smack/AbstractXMPPConnection.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
        SmackConfiguration.getVersion();
    }

}
