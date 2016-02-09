// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.tcp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.SynchronizationPoint;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.compress.packet.Compress;
import org.jivesoftware.smack.compression.XMPPInputOutputStream;
import org.jivesoftware.smack.debugger.SmackDebugger;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.StartTls;
import org.jivesoftware.smack.packet.StreamOpen;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.sm.SMUtils;
import org.jivesoftware.smack.sm.predicates.Predicate;
import org.jivesoftware.smack.sm.provider.ParseStreamManagement;
import org.jivesoftware.smack.util.ArrayBlockingQueueWithShutdown;
import org.jivesoftware.smack.util.Async;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.TLSUtils;
import org.jivesoftware.smack.util.dns.HostAddress;
import org.jxmpp.util.XmppStringUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smack.tcp:
//            XMPPTCPConnectionConfiguration, BundleAndDeferCallback, BundleAndDefer

public class XMPPTCPConnection extends AbstractXMPPConnection
{
    protected class PacketReader
    {

        static final boolean $assertionsDisabled;
        private volatile boolean done;
        XmlPullParser parser;
        final XMPPTCPConnection this$0;

        private void parsePackets()
        {
            int i;
            initalOpenStreamSend.checkIfSuccessOrWait();
            i = parser.getEventType();
_L7:
            if (done) goto _L2; else goto _L1
_L1:
            i;
            JVM INSTR tableswitch 1 3: default 1452
        //                       1 1441
        //                       2 69
        //                       3 1414;
               goto _L3 _L4 _L5 _L6
_L3:
            i = parser.next();
              goto _L7
_L5:
            Object obj = parser.getName();
            i = -1;
            ((String) (obj)).hashCode();
            JVM INSTR lookupswitch 16: default 1455
        //                       -1867169789: 408
        //                       -1609594047: 438
        //                       -1281977283: 453
        //                       -1276666629: 307
        //                       -1086574198: 378
        //                       -891990144: 321
        //                       -369449087: 423
        //                       -309519186: 363
        //                       -290659267: 349
        //                       97: 483
        //                       114: 498
        //                       3368: 293
        //                       96784904: 335
        //                       954925063: 279
        //                       1097547223: 468
        //                       1402633315: 393;
               goto _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24
_L39:
            XMPPTCPConnection.LOGGER.warning((new StringBuilder()).append("Unknown top level stream element: ").append(((String) (obj))).toString());
              goto _L3
_L2:
            return;
_L22:
            if (((String) (obj)).equals("message"))
            {
                i = 0;
            }
              goto _L8
_L20:
            if (((String) (obj)).equals("iq"))
            {
                i = 1;
            }
              goto _L8
_L12:
            if (((String) (obj)).equals("presence"))
            {
                i = 2;
            }
              goto _L8
_L14:
            if (((String) (obj)).equals("stream"))
            {
                i = 3;
            }
              goto _L8
_L21:
            if (((String) (obj)).equals("error"))
            {
                i = 4;
            }
              goto _L8
_L17:
            if (((String) (obj)).equals("features"))
            {
                i = 5;
            }
              goto _L8
_L16:
            if (((String) (obj)).equals("proceed"))
            {
                i = 6;
            }
              goto _L8
_L13:
            if (((String) (obj)).equals("failure"))
            {
                i = 7;
            }
              goto _L8
_L24:
            if (((String) (obj)).equals("challenge"))
            {
                i = 8;
            }
              goto _L8
_L9:
            if (((String) (obj)).equals("success"))
            {
                i = 9;
            }
              goto _L8
_L15:
            if (((String) (obj)).equals("compressed"))
            {
                i = 10;
            }
              goto _L8
_L10:
            if (((String) (obj)).equals("enabled"))
            {
                i = 11;
            }
              goto _L8
_L11:
            if (((String) (obj)).equals("failed"))
            {
                i = 12;
            }
              goto _L8
_L23:
            if (((String) (obj)).equals("resumed"))
            {
                i = 13;
            }
              goto _L8
_L18:
            if (((String) (obj)).equals("a"))
            {
                i = 14;
            }
              goto _L8
_L19:
            boolean flag = ((String) (obj)).equals("r");
            if (flag)
            {
                i = 15;
            }
              goto _L8
_L40:
            parseAndProcessStanza(parser);
            clientHandledStanzasCount = SMUtils.incrementHeight(clientHandledStanzasCount);
              goto _L3
            Object obj1;
            obj1;
            clientHandledStanzasCount = SMUtils.incrementHeight(clientHandledStanzasCount);
            throw obj1;
_L41:
            if (!"jabber:client".equals(parser.getNamespace(null))) goto _L3; else goto _L25
_L25:
            
// JavaClassFileOutputException: get_constant: invalid tag

        void init()
        {
            done = false;
            Async.go(new Runnable() {

                final PacketReader this$1;

                public void run()
                {
                    parsePackets();
                }

            
            {
                this$1 = PacketReader.this;
                super();
            }
            }, (new StringBuilder()).append("Smack Packet Reader (").append(getConnectionCounter()).append(")").toString());
        }

        void shutdown()
        {
            done = true;
        }

        static 
        {
            boolean flag;
            if (!org/jivesoftware/smack/tcp/XMPPTCPConnection.desiredAssertionStatus())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            $assertionsDisabled = flag;
        }



        protected PacketReader()
        {
            this$0 = XMPPTCPConnection.this;
            super();
        }
    }

    protected class PacketWriter
    {

        public static final int QUEUE_SIZE = 500;
        private volatile boolean instantShutdown;
        private final ArrayBlockingQueueWithShutdown queue = new ArrayBlockingQueueWithShutdown(500, true);
        private boolean shouldBundleAndDefer;
        protected SynchronizationPoint shutdownDone;
        protected volatile Long shutdownTimestamp;
        final XMPPTCPConnection this$0;

        private boolean done()
        {
            return shutdownTimestamp != null;
        }

        private void drainWriterQueueToUnacknowledgedStanzas()
        {
            Object obj = new ArrayList(queue.size());
            queue.drainTo(((Collection) (obj)));
            obj = ((List) (obj)).iterator();
            do
            {
                if (!((Iterator) (obj)).hasNext())
                {
                    break;
                }
                Element element = (Element)((Iterator) (obj)).next();
                if (element instanceof Stanza)
                {
                    unacknowledgedStanzas.add((Stanza)element);
                }
            } while (true);
        }

        private Element nextStreamElement()
        {
            Element element;
            if (queue.isEmpty())
            {
                shouldBundleAndDefer = true;
            }
            element = null;
            Element element1 = (Element)queue.take();
            element = element1;
_L2:
            return element;
            InterruptedException interruptedexception;
            interruptedexception;
            if (!queue.isShutdown())
            {
                XMPPTCPConnection.LOGGER.log(Level.WARNING, "Packet writer thread was interrupted. Don't do that. Use disconnect() instead.", interruptedexception);
                return null;
            }
            if (true) goto _L2; else goto _L1
_L1:
        }

        private void writePackets()
        {
            openStream();
            initalOpenStreamSend.reportSuccess();
_L4:
            if (done()) goto _L2; else goto _L1
_L1:
            Element element = nextStreamElement();
            if (element == null) goto _L4; else goto _L3
_L3:
            Object obj1 = bundleAndDeferCallback;
            if (obj1 == null) goto _L6; else goto _L5
_L5:
            if (!isAuthenticated() || !shouldBundleAndDefer) goto _L6; else goto _L7
_L7:
            Object obj;
            int i;
            shouldBundleAndDefer = false;
            obj = new AtomicBoolean();
            i = ((BundleAndDeferCallback) (obj1)).getBundleAndDeferMillis(new BundleAndDefer(((AtomicBoolean) (obj))));
            if (i <= 0) goto _L6; else goto _L8
_L8:
            long l = i;
            long l1 = System.currentTimeMillis();
            obj;
            JVM INSTR monitorenter ;
_L9:
            if (((AtomicBoolean) (obj)).get() || l <= 0L)
            {
                break MISSING_BLOCK_LABEL_143;
            }
            obj.wait(l);
            l = (long)i - (System.currentTimeMillis() - l1);
              goto _L9
            obj;
            JVM INSTR monitorexit ;
_L6:
            obj1 = null;
            if (!(element instanceof Stanza)) goto _L11; else goto _L10
_L10:
            obj = (Stanza)element;
_L16:
            if (unacknowledgedStanzas == null || obj == null)
            {
                break MISSING_BLOCK_LABEL_237;
            }
            if ((double)unacknowledgedStanzas.size() == 400D)
            {
                access$3400.write(org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest.INSTANCE.toXML().toString());
                access$3400.flush();
            }
            unacknowledgedStanzas.put(obj);
            access$3400.write(element.toXML().toString());
            if (queue.isEmpty())
            {
                access$3400.flush();
            }
            if (obj == null) goto _L4; else goto _L12
_L12:
            firePacketSendingListeners(((Stanza) (obj)));
              goto _L4
_L14:
            if (done() || isSocketClosed())
            {
                break MISSING_BLOCK_LABEL_580;
            }
            notifyConnectionError(((Exception) (obj)));
_L24:
            XMPPTCPConnection.LOGGER.fine("Reporting shutdownDone success in writer thread");
            shutdownDone.reportSuccess();
            return;
            obj1;
            obj;
            JVM INSTR monitorexit ;
            try
            {
                throw obj1;
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
            finally
            {
                XMPPTCPConnection.LOGGER.fine("Reporting shutdownDone success in writer thread");
            }
            if (true) goto _L14; else goto _L13
_L13:
            shutdownDone.reportSuccess();
            throw obj;
_L11:
            obj = obj1;
            if (!(element instanceof org.jivesoftware.smack.sm.packet.StreamManagement.Enable)) goto _L16; else goto _L15
_L15:
            unacknowledgedStanzas = new ArrayBlockingQueue(500);
            obj = obj1;
              goto _L16
            obj;
            throw new IllegalStateException(((Throwable) (obj)));
_L2:
            boolean flag = instantShutdown;
            if (flag) goto _L18; else goto _L17
_L17:
            for (; !queue.isEmpty(); access$3400.write(((Element) (obj)).toXML().toString()))
            {
                obj = (Element)queue.remove();
            }

              goto _L19
            obj;
            XMPPTCPConnection.LOGGER.log(Level.WARNING, "Exception flushing queue during shutdown, ignore and continue", ((Throwable) (obj)));
_L20:
            access$3400.write("</stream:stream>");
            access$3400.flush();
_L21:
            queue.clear();
_L23:
            try
            {
                access$3400.close();
            }
            catch (Exception exception) { }
            XMPPTCPConnection.LOGGER.fine("Reporting shutdownDone success in writer thread");
            shutdownDone.reportSuccess();
            return;
_L19:
            access$3400.flush();
              goto _L20
            obj;
            XMPPTCPConnection.LOGGER.log(Level.WARNING, "Exception writing closing stream element", ((Throwable) (obj)));
              goto _L21
_L18:
            if (!instantShutdown || !isSmEnabled()) goto _L23; else goto _L22
_L22:
            drainWriterQueueToUnacknowledgedStanzas();
              goto _L23
            XMPPTCPConnection.LOGGER.log(Level.FINE, "Ignoring Exception in writePackets()", ((Throwable) (obj)));
              goto _L24
        }

        void init()
        {
            shutdownDone.init();
            shutdownTimestamp = null;
            if (unacknowledgedStanzas != null)
            {
                drainWriterQueueToUnacknowledgedStanzas();
            }
            queue.start();
            Async.go(new Runnable() {

                final PacketWriter this$1;

                public void run()
                {
                    writePackets();
                }

            
            {
                this$1 = PacketWriter.this;
                super();
            }
            }, (new StringBuilder()).append("Smack Packet Writer (").append(getConnectionCounter()).append(")").toString());
        }

        protected void sendStreamElement(Element element)
            throws org.jivesoftware.smack.SmackException.NotConnectedException
        {
            boolean flag;
            throwNotConnectedExceptionIfDoneAndResumptionNotPossible();
            flag = false;
_L2:
            if (flag)
            {
                break; /* Loop/switch isn't completed */
            }
            queue.put(element);
            flag = true;
            continue; /* Loop/switch isn't completed */
            InterruptedException interruptedexception;
            interruptedexception;
            throwNotConnectedExceptionIfDoneAndResumptionNotPossible();
            XMPPTCPConnection.LOGGER.log(Level.WARNING, "Sending thread was interrupted", interruptedexception);
            if (true) goto _L2; else goto _L1
_L1:
        }

        void shutdown(boolean flag)
        {
            instantShutdown = flag;
            shutdownTimestamp = Long.valueOf(System.currentTimeMillis());
            queue.shutdown();
            try
            {
                shutdownDone.checkIfSuccessOrWait();
                return;
            }
            catch (org.jivesoftware.smack.SmackException.NoResponseException noresponseexception)
            {
                XMPPTCPConnection.LOGGER.log(Level.WARNING, "shutdownDone was not marked as successful by the writer thread", noresponseexception);
            }
        }

        protected void throwNotConnectedExceptionIfDoneAndResumptionNotPossible()
            throws org.jivesoftware.smack.SmackException.NotConnectedException
        {
            if (done() && !isSmResumptionPossible())
            {
                throw new org.jivesoftware.smack.SmackException.NotConnectedException();
            } else
            {
                return;
            }
        }



        protected PacketWriter()
        {
            this$0 = XMPPTCPConnection.this;
            super();
            shutdownDone = new SynchronizationPoint(XMPPTCPConnection.this);
            shutdownTimestamp = null;
        }
    }


    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/tcp/XMPPTCPConnection.getName());
    private static final int QUEUE_SIZE = 500;
    private static BundleAndDeferCallback defaultBundleAndDeferCallback;
    private static boolean useSmDefault = false;
    private static boolean useSmResumptionDefault = true;
    private BundleAndDeferCallback bundleAndDeferCallback;
    private long clientHandledStanzasCount;
    private final SynchronizationPoint compressSyncPoint;
    private final XMPPTCPConnectionConfiguration config;
    private boolean disconnectedButResumeable;
    private final SynchronizationPoint initalOpenStreamSend;
    private final SynchronizationPoint maybeCompressFeaturesReceived;
    protected PacketReader packetReader;
    protected PacketWriter packetWriter;
    private final Set requestAckPredicates;
    private long serverHandledStanzasCount;
    private int smClientMaxResumptionTime;
    private final SynchronizationPoint smEnabledSyncPoint;
    private final SynchronizationPoint smResumedSyncPoint;
    private int smServerMaxResumptimTime;
    private String smSessionId;
    private boolean smWasEnabledAtLeastOnce;
    private Socket socket;
    private volatile boolean socketClosed;
    private final Collection stanzaAcknowledgedListeners;
    private final Map stanzaIdAcknowledgedListeners;
    private BlockingQueue unacknowledgedStanzas;
    private boolean useSm;
    private boolean useSmResumption;
    private boolean usingTLS;

    public XMPPTCPConnection(CharSequence charsequence, String s)
    {
        this(((CharSequence) (XmppStringUtils.parseLocalpart(charsequence.toString()))), s, XmppStringUtils.parseDomain(charsequence.toString()));
    }

    public XMPPTCPConnection(CharSequence charsequence, String s, String s1)
    {
        this(((XMPPTCPConnectionConfiguration.Builder)((XMPPTCPConnectionConfiguration.Builder)XMPPTCPConnectionConfiguration.builder().setUsernameAndPassword(charsequence, s)).setServiceName(s1)).build());
    }

    public XMPPTCPConnection(XMPPTCPConnectionConfiguration xmpptcpconnectionconfiguration)
    {
        super(xmpptcpconnectionconfiguration);
        disconnectedButResumeable = false;
        socketClosed = false;
        usingTLS = false;
        initalOpenStreamSend = new SynchronizationPoint(this);
        maybeCompressFeaturesReceived = new SynchronizationPoint(this);
        compressSyncPoint = new SynchronizationPoint(this);
        bundleAndDeferCallback = defaultBundleAndDeferCallback;
        smResumedSyncPoint = new SynchronizationPoint(this);
        smEnabledSyncPoint = new SynchronizationPoint(this);
        smClientMaxResumptionTime = -1;
        smServerMaxResumptimTime = -1;
        useSm = useSmDefault;
        useSmResumption = useSmResumptionDefault;
        serverHandledStanzasCount = 0L;
        clientHandledStanzasCount = 0L;
        smWasEnabledAtLeastOnce = false;
        stanzaAcknowledgedListeners = new ConcurrentLinkedQueue();
        stanzaIdAcknowledgedListeners = new ConcurrentHashMap();
        requestAckPredicates = new LinkedHashSet();
        config = xmpptcpconnectionconfiguration;
    }

    private void connectUsingConfiguration()
        throws IOException, org.jivesoftware.smack.SmackException.ConnectionException
    {
        SocketFactory socketfactory;
        Object obj;
        List list;
        list = populateHostAddresses();
        obj = config.getSocketFactory();
        socketfactory = ((SocketFactory) (obj));
        if (obj == null)
        {
            socketfactory = SocketFactory.getDefault();
        }
        obj = hostAddresses.iterator();
        break MISSING_BLOCK_LABEL_33;
        obj2;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_294;
        }
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        obj2 = (InetAddress)iterator.next();
        s = (new StringBuilder()).append(obj2).append(" at port ").append(i).toString();
        LOGGER.finer((new StringBuilder()).append("Trying to establish TCP connection to ").append(s).toString());
        socket.connect(new InetSocketAddress(((InetAddress) (obj2)), i), config.getConnectTimeout());
        LOGGER.finer((new StringBuilder()).append("Established TCP connection to ").append(s).toString());
        host = ((String) (obj1));
        port = i;
        return;
        throw obj2;
_L2:
        Object obj1;
        Iterator iterator;
        Object obj2;
        String s;
        int i;
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            HostAddress hostaddress = (HostAddress)((Iterator) (obj)).next();
            obj1 = hostaddress.getFQDN();
            i = hostaddress.getPort();
            socket = socketfactory.createSocket();
            try
            {
                iterator = Arrays.asList(InetAddress.getAllByName(((String) (obj1)))).iterator();
                if (!iterator.hasNext())
                {
                    LOGGER.warning("InetAddress.getAllByName() returned empty result array.");
                    throw new UnknownHostException(((String) (obj1)));
                }
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1)
            {
                hostaddress.setException(((Exception) (obj1)));
                list.add(hostaddress);
            }
        } while (true);
        throw org.jivesoftware.smack.SmackException.ConnectionException.from(list);
    }

    private void initConnection()
        throws IOException
    {
        boolean flag;
        if (packetReader == null || packetWriter == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        compressionHandler = null;
        initReaderAndWriter();
        if (flag)
        {
            packetWriter = new PacketWriter();
            packetReader = new PacketReader();
            if (config.isDebuggerEnabled())
            {
                addAsyncStanzaListener(debugger.getReaderListener(), null);
                if (debugger.getWriterListener() != null)
                {
                    addPacketSendingListener(debugger.getWriterListener(), null);
                }
            }
        }
        packetWriter.init();
        packetReader.init();
        if (flag)
        {
            for (Iterator iterator = getConnectionCreationListeners().iterator(); iterator.hasNext(); ((ConnectionCreationListener)iterator.next()).connectionCreated(this)) { }
        }
    }

    private void initReaderAndWriter()
        throws IOException
    {
        InputStream inputstream1 = socket.getInputStream();
        java.io.OutputStream outputstream1 = socket.getOutputStream();
        InputStream inputstream = inputstream1;
        java.io.OutputStream outputstream = outputstream1;
        if (compressionHandler != null)
        {
            inputstream = compressionHandler.getInputStream(inputstream1);
            outputstream = compressionHandler.getOutputStream(outputstream1);
        }
        writer = new OutputStreamWriter(outputstream, "UTF-8");
        reader = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
        initDebugger();
    }

    private XMPPInputOutputStream maybeGetCompressionHandler()
    {
        org.jivesoftware.smack.compress.packet.Compress.Feature feature = (org.jivesoftware.smack.compress.packet.Compress.Feature)getFeature("compression", "http://jabber.org/protocol/compress");
        if (feature == null)
        {
            return null;
        }
        for (Iterator iterator = SmackConfiguration.getCompresionHandlers().iterator(); iterator.hasNext();)
        {
            XMPPInputOutputStream xmppinputoutputstream = (XMPPInputOutputStream)iterator.next();
            String s = xmppinputoutputstream.getCompressionMethod();
            if (feature.getMethods().contains(s))
            {
                return xmppinputoutputstream;
            }
        }

        return null;
    }

    private void notifyConnectionError(Exception exception)
    {
        this;
        JVM INSTR monitorenter ;
        if (packetReader != null && !packetReader.done) goto _L2; else goto _L1
_L1:
        if (packetWriter == null) goto _L4; else goto _L3
_L3:
        boolean flag = packetWriter.done();
        if (!flag) goto _L2; else goto _L4
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        instantShutdown();
        callConnectionClosedOnErrorListener(exception);
        if (true) goto _L4; else goto _L5
_L5:
        exception;
        throw exception;
    }

    private void proceedTLSReceived()
        throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException, NoSuchProviderException, UnrecoverableKeyException, KeyManagementException, SmackException
    {
        Object obj;
        Object obj1;
        SSLContext sslcontext;
        sslcontext = config.getCustomSSLContext();
        obj = null;
        obj1 = null;
        if (config.getCallbackHandler() == null || sslcontext != null) goto _L2; else goto _L1
_L1:
        if (!config.getKeystoreType().equals("NONE")) goto _L4; else goto _L3
_L3:
        obj = null;
        obj1 = null;
_L9:
        keymanagerfactory = KeyManagerFactory.getInstance("SunX509");
        if (obj1 != null) goto _L6; else goto _L5
_L5:
        keymanagerfactory.init(((KeyStore) (obj)), null);
_L10:
        obj = keymanagerfactory.getKeyManagers();
          goto _L2
_L4:
        if (!config.getKeystoreType().equals("PKCS11")) goto _L8; else goto _L7
_L7:
        obj = (Provider)Class.forName("sun.security.pkcs11.SunPKCS11").getConstructor(new Class[] {
            java/io/InputStream
        }).newInstance(new Object[] {
            new ByteArrayInputStream((new StringBuilder()).append("name = SmartCard\nlibrary = ").append(config.getPKCS11Library()).toString().getBytes())
        });
        Security.addProvider(((Provider) (obj)));
        obj = KeyStore.getInstance("PKCS11", ((Provider) (obj)));
        obj1 = new PasswordCallback("PKCS11 Password: ", false);
        config.getCallbackHandler().handle(new Callback[] {
            obj1
        });
        ((KeyStore) (obj)).load(null, ((PasswordCallback) (obj1)).getPassword());
          goto _L9
        obj;
_L12:
        obj = null;
        obj1 = null;
          goto _L9
_L8:
label0:
        {
            if (!config.getKeystoreType().equals("Apple"))
            {
                break label0;
            }
            obj = KeyStore.getInstance("KeychainStore", "Apple");
            ((KeyStore) (obj)).load(null, null);
        }
          goto _L9
        obj = KeyStore.getInstance(config.getKeystoreType());
        obj1 = new PasswordCallback("Keystore Password: ", false);
        config.getCallbackHandler().handle(new Callback[] {
            obj1
        });
        ((KeyStore) (obj)).load(new FileInputStream(config.getKeystorePath()), ((PasswordCallback) (obj1)).getPassword());
          goto _L9
        obj;
_L11:
        obj = null;
        obj1 = null;
          goto _L9
_L6:
        keymanagerfactory.init(((KeyStore) (obj)), ((PasswordCallback) (obj1)).getPassword());
        ((PasswordCallback) (obj1)).clearPassword();
          goto _L10
        obj;
        obj = null;
_L2:
        obj1 = sslcontext;
        if (sslcontext == null)
        {
            obj1 = SSLContext.getInstance("TLS");
            ((SSLContext) (obj1)).init(((javax.net.ssl.KeyManager []) (obj)), null, new SecureRandom());
        }
        obj = socket;
        socket = ((SSLContext) (obj1)).getSocketFactory().createSocket(((Socket) (obj)), host, ((Socket) (obj)).getPort(), true);
        initReaderAndWriter();
        obj = (SSLSocket)socket;
        TLSUtils.setEnabledProtocolsAndCiphers(((SSLSocket) (obj)), config.getEnabledSSLProtocols(), config.getEnabledSSLCiphers());
        ((SSLSocket) (obj)).startHandshake();
        obj1 = getConfiguration().getHostnameVerifier();
        KeyManagerFactory keymanagerfactory;
        if (obj1 == null)
        {
            throw new IllegalStateException("No HostnameVerifier set. Use connectionConfiguration.setHostnameVerifier() to configure.");
        }
        if (!((HostnameVerifier) (obj1)).verify(getServiceName(), ((SSLSocket) (obj)).getSession()))
        {
            throw new CertificateException((new StringBuilder()).append("Hostname verification of certificate failed. Certificate does not authenticate ").append(getServiceName()).toString());
        } else
        {
            usingTLS = true;
            return;
        }
        Exception exception;
        exception;
          goto _L11
        exception;
          goto _L12
    }

    private void processHandledCount(long l)
        throws org.jivesoftware.smack.SmackException.NotConnectedException, org.jivesoftware.smack.sm.StreamManagementException.StreamManagementCounterError
    {
        final ArrayList ackedStanzas;
        boolean flag1;
        long l2 = SMUtils.calculateDelta(l, serverHandledStanzasCount);
        int i;
        if (l <= 0x7fffffffL)
        {
            i = (int)l;
        } else
        {
            i = 0x7fffffff;
        }
        ackedStanzas = new ArrayList(i);
        for (long l1 = 0L; l1 < l2; l1++)
        {
            Stanza stanza = (Stanza)unacknowledgedStanzas.poll();
            if (stanza == null)
            {
                throw new org.jivesoftware.smack.sm.StreamManagementException.StreamManagementCounterError(l, serverHandledStanzasCount, l2, ackedStanzas);
            }
            ackedStanzas.add(stanza);
        }

        flag1 = false;
        if (stanzaAcknowledgedListeners.isEmpty()) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        if (flag)
        {
            asyncGo(new Runnable() {

                final XMPPTCPConnection this$0;
                final List val$ackedStanzas;

                public void run()
                {
                    Iterator iterator1 = ackedStanzas.iterator();
                    do
                    {
                        if (!iterator1.hasNext())
                        {
                            break;
                        }
                        Stanza stanza1 = (Stanza)iterator1.next();
                        for (Iterator iterator2 = stanzaAcknowledgedListeners.iterator(); iterator2.hasNext();)
                        {
                            StanzaListener stanzalistener = (StanzaListener)iterator2.next();
                            try
                            {
                                stanzalistener.processPacket(stanza1);
                            }
                            catch (org.jivesoftware.smack.SmackException.NotConnectedException notconnectedexception1)
                            {
                                XMPPTCPConnection.LOGGER.log(Level.FINER, "Received not connected exception", notconnectedexception1);
                            }
                        }

                        Object obj = stanza1.getStanzaId();
                        if (!StringUtils.isNullOrEmpty(((CharSequence) (obj))))
                        {
                            obj = (StanzaListener)stanzaIdAcknowledgedListeners.remove(obj);
                            if (obj != null)
                            {
                                try
                                {
                                    ((StanzaListener) (obj)).processPacket(stanza1);
                                }
                                catch (org.jivesoftware.smack.SmackException.NotConnectedException notconnectedexception)
                                {
                                    XMPPTCPConnection.LOGGER.log(Level.FINER, "Received not connected exception", notconnectedexception);
                                }
                            }
                        }
                    } while (true);
                }

            
            {
                this$0 = XMPPTCPConnection.this;
                ackedStanzas = list;
                super();
            }
            });
        }
        serverHandledStanzasCount = l;
        return;
_L2:
        Iterator iterator = ackedStanzas.iterator();
        String s;
        do
        {
            flag = flag1;
            if (!iterator.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            s = ((Stanza)iterator.next()).getStanzaId();
        } while (s == null || !stanzaIdAcknowledgedListeners.containsKey(s));
        flag = true;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void requestSmAcknowledgementInternal()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        packetWriter.sendStreamElement(org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest.INSTANCE);
    }

    private void sendSmAcknowledgementInternal()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        packetWriter.sendStreamElement(new org.jivesoftware.smack.sm.packet.StreamManagement.AckAnswer(clientHandledStanzasCount));
    }

    public static void setDefaultBundleAndDeferCallback(BundleAndDeferCallback bundleanddefercallback)
    {
        defaultBundleAndDeferCallback = bundleanddefercallback;
    }

    public static void setUseStreamManagementDefault(boolean flag)
    {
        useSmDefault = flag;
    }

    public static void setUseStreamManagementResumptiodDefault(boolean flag)
    {
        if (flag)
        {
            setUseStreamManagementDefault(flag);
        }
        useSmResumptionDefault = flag;
    }

    private void shutdown(boolean flag)
    {
        if (disconnectedButResumeable)
        {
            return;
        }
        if (packetReader != null)
        {
            packetReader.shutdown();
        }
        if (packetWriter != null)
        {
            packetWriter.shutdown(flag);
        }
        socketClosed = true;
        try
        {
            socket.close();
        }
        catch (Exception exception)
        {
            LOGGER.log(Level.WARNING, "shutdown", exception);
        }
        setWasAuthenticated();
        if (isSmResumptionPossible() && flag)
        {
            disconnectedButResumeable = true;
        } else
        {
            disconnectedButResumeable = false;
            smSessionId = null;
        }
        authenticated = false;
        connected = false;
        usingTLS = false;
        reader = null;
        writer = null;
        maybeCompressFeaturesReceived.init();
        compressSyncPoint.init();
        smResumedSyncPoint.init();
        smEnabledSyncPoint.init();
        initalOpenStreamSend.init();
    }

    private void useCompression()
        throws org.jivesoftware.smack.SmackException.NotConnectedException, org.jivesoftware.smack.SmackException.NoResponseException, XMPPException
    {
        maybeCompressFeaturesReceived.checkIfSuccessOrWait();
        XMPPInputOutputStream xmppinputoutputstream = maybeGetCompressionHandler();
        compressionHandler = xmppinputoutputstream;
        if (xmppinputoutputstream != null)
        {
            compressSyncPoint.sendAndWaitForResponseOrThrow(new Compress(compressionHandler.getCompressionMethod()));
            return;
        } else
        {
            LOGGER.warning("Could not enable compression because no matching handler/method pair was found");
            return;
        }
    }

    public boolean addRequestAckPredicate(StanzaFilter stanzafilter)
    {
        boolean flag;
        synchronized (requestAckPredicates)
        {
            flag = requestAckPredicates.add(stanzafilter);
        }
        return flag;
        stanzafilter;
        set;
        JVM INSTR monitorexit ;
        throw stanzafilter;
    }

    public void addStanzaAcknowledgedListener(StanzaListener stanzalistener)
    {
        stanzaAcknowledgedListeners.add(stanzalistener);
    }

    public StanzaListener addStanzaIdAcknowledgedListener(final String id, StanzaListener stanzalistener)
        throws org.jivesoftware.smack.sm.StreamManagementException.StreamManagementNotEnabledException
    {
        if (!smWasEnabledAtLeastOnce)
        {
            throw new org.jivesoftware.smack.sm.StreamManagementException.StreamManagementNotEnabledException();
        } else
        {
            int i = Math.min(getMaxSmResumptionTime() + 60, 43200);
            schedule(new Runnable() {

                final XMPPTCPConnection this$0;
                final String val$id;

                public void run()
                {
                    stanzaIdAcknowledgedListeners.remove(id);
                }

            
            {
                this$0 = XMPPTCPConnection.this;
                id = s;
                super();
            }
            }, i, TimeUnit.SECONDS);
            return (StanzaListener)stanzaIdAcknowledgedListeners.put(id, stanzalistener);
        }
    }

    protected void afterFeaturesReceived()
        throws org.jivesoftware.smack.SmackException.SecurityRequiredException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        StartTls starttls = (StartTls)getFeature("starttls", "urn:ietf:params:xml:ns:xmpp-tls");
        if (starttls == null) goto _L2; else goto _L1
_L1:
        if (!starttls.required() || config.getSecurityMode() != org.jivesoftware.smack.ConnectionConfiguration.SecurityMode.disabled) goto _L4; else goto _L3
_L3:
        notifyConnectionError(new org.jivesoftware.smack.SmackException.SecurityRequiredByServerException());
_L6:
        return;
_L4:
        if (config.getSecurityMode() == org.jivesoftware.smack.ConnectionConfiguration.SecurityMode.disabled)
        {
            continue; /* Loop/switch isn't completed */
        }
        send(new StartTls());
_L2:
        if (!isSecureConnection() && starttls == null && getConfiguration().getSecurityMode() == org.jivesoftware.smack.ConnectionConfiguration.SecurityMode.required)
        {
            throw new org.jivesoftware.smack.SmackException.SecurityRequiredByClientException();
        }
        if (getSASLAuthentication().authenticationSuccessful())
        {
            maybeCompressFeaturesReceived.reportSuccess();
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void afterSuccessfulLogin(boolean flag)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        disconnectedButResumeable = false;
        super.afterSuccessfulLogin(flag);
    }

    protected void connectInternal()
        throws SmackException, IOException, XMPPException
    {
        connectUsingConfiguration();
        socketClosed = false;
        initConnection();
        saslFeatureReceived.checkIfSuccessOrWaitOrThrow();
        connected = true;
        callConnectionConnectedListener();
        if (wasAuthenticated)
        {
            login();
            notifyReconnection();
        }
    }

    public int getMaxSmResumptionTime()
    {
        int i;
        int j;
        if (smClientMaxResumptionTime > 0)
        {
            i = smClientMaxResumptionTime;
        } else
        {
            i = 0x7fffffff;
        }
        if (smServerMaxResumptimTime > 0)
        {
            j = smServerMaxResumptimTime;
        } else
        {
            j = 0x7fffffff;
        }
        return Math.min(i, j);
    }

    public void instantShutdown()
    {
        this;
        JVM INSTR monitorenter ;
        shutdown(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isDisconnectedButSmResumptionPossible()
    {
        return disconnectedButResumeable && isSmResumptionPossible();
    }

    public boolean isSecureConnection()
    {
        return usingTLS;
    }

    public boolean isSmAvailable()
    {
        return hasFeature("sm", "urn:xmpp:sm:3");
    }

    public boolean isSmEnabled()
    {
        return smEnabledSyncPoint.wasSuccessful();
    }

    public boolean isSmResumptionPossible()
    {
        if (smSessionId != null)
        {
            Long long1 = packetWriter.shutdownTimestamp;
            if (long1 == null)
            {
                return true;
            }
            long l = System.currentTimeMillis();
            long l1 = getMaxSmResumptionTime() * 1000;
            if (long1.longValue() + l1 <= l)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isSocketClosed()
    {
        return socketClosed;
    }

    public boolean isUsingCompression()
    {
        return compressionHandler != null && compressSyncPoint.wasSuccessful();
    }

    public void loginAnonymously()
        throws XMPPException, SmackException, IOException
    {
        this;
        JVM INSTR monitorenter ;
        saslFeatureReceived.checkIfSuccessOrWaitOrThrow();
        if (!saslAuthentication.hasAnonymousAuthentication())
        {
            break MISSING_BLOCK_LABEL_53;
        }
        saslAuthentication.authenticateAnonymously();
        if (config.isCompressionEnabled())
        {
            useCompression();
        }
        bindResourceAndEstablishSession(null);
        afterSuccessfulLogin(false);
        this;
        JVM INSTR monitorexit ;
        return;
        throw new SmackException("No anonymous SASL authentication mechanism available");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void loginNonAnonymously(String s, String s1, String s2)
        throws XMPPException, SmackException, IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (!saslAuthentication.hasNonAnonymousAuthentication()) goto _L2; else goto _L1
_L1:
        if (s1 == null) goto _L4; else goto _L3
_L3:
        saslAuthentication.authenticate(s, s1, s2);
_L9:
        if (config.isCompressionEnabled())
        {
            useCompression();
        }
        if (!isSmResumptionPossible()) goto _L6; else goto _L5
_L5:
        smResumedSyncPoint.sendAndWaitForResponse(new org.jivesoftware.smack.sm.packet.StreamManagement.Resume(clientHandledStanzasCount, smSessionId));
        if (!smResumedSyncPoint.wasSuccessful()) goto _L8; else goto _L7
_L7:
        afterSuccessfulLogin(true);
_L12:
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        saslAuthentication.authenticate(s2, config.getCallbackHandler());
          goto _L9
        s;
        throw s;
_L2:
        throw new SmackException("No non-anonymous SASL authentication mechanism available");
_L8:
        LOGGER.fine("Stream resumption failed, continuing with normal stream establishment process");
_L6:
        bindResourceAndEstablishSession(s2);
        s1 = new LinkedList();
        if (unacknowledgedStanzas != null)
        {
            unacknowledgedStanzas.drainTo(s1);
            unacknowledgedStanzas = null;
        }
        if (!isSmAvailable() || !useSm) goto _L11; else goto _L10
_L10:
        serverHandledStanzasCount = 0L;
        smEnabledSyncPoint.sendAndWaitForResponseOrThrow(new org.jivesoftware.smack.sm.packet.StreamManagement.Enable(useSmResumption, smClientMaxResumptionTime));
        synchronized (requestAckPredicates)
        {
            if (requestAckPredicates.isEmpty())
            {
                requestAckPredicates.add(Predicate.forMessagesOrAfter5Stanzas());
            }
        }
_L11:
        for (s = s1.iterator(); s.hasNext(); sendStanzaInternal((Stanza)s.next())) { }
        break MISSING_BLOCK_LABEL_278;
        s1;
        s;
        JVM INSTR monitorexit ;
        throw s1;
        afterSuccessfulLogin(false);
          goto _L12
    }

    void openStream()
        throws SmackException
    {
        String s1 = getServiceName();
        String s = null;
        CharSequence charsequence = config.getUsername();
        if (charsequence != null)
        {
            s = XmppStringUtils.completeJidFrom(charsequence, s1);
        }
        send(new StreamOpen(s1, s, getStreamId()));
        try
        {
            packetReader.parser = PacketParserUtils.newXmppParser(reader);
            return;
        }
        catch (XmlPullParserException xmlpullparserexception)
        {
            throw new SmackException(xmlpullparserexception);
        }
    }

    public void removeAllRequestAckPredicates()
    {
        synchronized (requestAckPredicates)
        {
            requestAckPredicates.clear();
        }
        return;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void removeAllStanzaAcknowledgedListeners()
    {
        stanzaAcknowledgedListeners.clear();
    }

    public void removeAllStanzaIdAcknowledgedListeners()
    {
        stanzaIdAcknowledgedListeners.clear();
    }

    public boolean removeRequestAckPredicate(StanzaFilter stanzafilter)
    {
        boolean flag;
        synchronized (requestAckPredicates)
        {
            flag = requestAckPredicates.remove(stanzafilter);
        }
        return flag;
        stanzafilter;
        set;
        JVM INSTR monitorexit ;
        throw stanzafilter;
    }

    public boolean removeStanzaAcknowledgedListener(StanzaListener stanzalistener)
    {
        return stanzaAcknowledgedListeners.remove(stanzalistener);
    }

    public StanzaListener removeStanzaIdAcknowledgedListener(String s)
    {
        return (StanzaListener)stanzaIdAcknowledgedListeners.remove(s);
    }

    public void requestSmAcknowledgement()
        throws org.jivesoftware.smack.sm.StreamManagementException.StreamManagementNotEnabledException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (!isSmEnabled())
        {
            throw new org.jivesoftware.smack.sm.StreamManagementException.StreamManagementNotEnabledException();
        } else
        {
            requestSmAcknowledgementInternal();
            return;
        }
    }

    public void send(PlainStreamElement plainstreamelement)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        packetWriter.sendStreamElement(plainstreamelement);
    }

    public void sendSmAcknowledgement()
        throws org.jivesoftware.smack.sm.StreamManagementException.StreamManagementNotEnabledException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (!isSmEnabled())
        {
            throw new org.jivesoftware.smack.sm.StreamManagementException.StreamManagementNotEnabledException();
        } else
        {
            sendSmAcknowledgementInternal();
            return;
        }
    }

    protected void sendStanzaInternal(Stanza stanza)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
label0:
        {
            packetWriter.sendStreamElement(stanza);
            if (!isSmEnabled())
            {
                break label0;
            }
            Iterator iterator = requestAckPredicates.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
            } while (!((StanzaFilter)iterator.next()).accept(stanza));
            requestSmAcknowledgementInternal();
        }
    }

    public void setBundleandDeferCallback(BundleAndDeferCallback bundleanddefercallback)
    {
        bundleAndDeferCallback = bundleanddefercallback;
    }

    public void setPreferredResumptionTime(int i)
    {
        smClientMaxResumptionTime = i;
    }

    public void setUseStreamManagement(boolean flag)
    {
        useSm = flag;
    }

    public void setUseStreamManagementResumption(boolean flag)
    {
        if (flag)
        {
            setUseStreamManagement(flag);
        }
        useSmResumption = flag;
    }

    protected void setWriter(Writer writer)
    {
        this.writer = writer;
    }

    protected void shutdown()
    {
        if (isSmEnabled())
        {
            try
            {
                sendSmAcknowledgementInternal();
            }
            catch (org.jivesoftware.smack.SmackException.NotConnectedException notconnectedexception)
            {
                LOGGER.log(Level.FINE, "Can not send final SM ack as connection is not connected", notconnectedexception);
            }
        }
        shutdown(false);
    }

    public boolean streamWasResumed()
    {
        return smResumedSyncPoint.wasSuccessful();
    }

    protected void throwAlreadyConnectedExceptionIfAppropriate()
        throws org.jivesoftware.smack.SmackException.AlreadyConnectedException
    {
        if (isConnected() && !disconnectedButResumeable)
        {
            throw new org.jivesoftware.smack.SmackException.AlreadyConnectedException();
        } else
        {
            return;
        }
    }

    protected void throwAlreadyLoggedInExceptionIfAppropriate()
        throws org.jivesoftware.smack.SmackException.AlreadyLoggedInException
    {
        if (isAuthenticated() && !disconnectedButResumeable)
        {
            throw new org.jivesoftware.smack.SmackException.AlreadyLoggedInException();
        } else
        {
            return;
        }
    }

    protected void throwNotConnectedExceptionIfAppropriate()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        if (packetWriter == null)
        {
            throw new org.jivesoftware.smack.SmackException.NotConnectedException();
        } else
        {
            packetWriter.throwNotConnectedExceptionIfDoneAndResumptionNotPossible();
            return;
        }
    }










/*
    static String access$1602(XMPPTCPConnection xmpptcpconnection, String s)
    {
        xmpptcpconnection.smSessionId = s;
        return s;
    }

*/



/*
    static int access$1802(XMPPTCPConnection xmpptcpconnection, int i)
    {
        xmpptcpconnection.smServerMaxResumptimTime = i;
        return i;
    }

*/


/*
    static boolean access$1902(XMPPTCPConnection xmpptcpconnection, boolean flag)
    {
        xmpptcpconnection.smWasEnabledAtLeastOnce = flag;
        return flag;
    }

*/







/*
    static BlockingQueue access$2402(XMPPTCPConnection xmpptcpconnection, BlockingQueue blockingqueue)
    {
        xmpptcpconnection.unacknowledgedStanzas = blockingqueue;
        return blockingqueue;
    }

*/




















/*
    static long access$502(XMPPTCPConnection xmpptcpconnection, long l)
    {
        xmpptcpconnection.clientHandledStanzasCount = l;
        return l;
    }

*/


/*
    static String access$602(XMPPTCPConnection xmpptcpconnection, String s)
    {
        xmpptcpconnection.streamId = s;
        return s;
    }

*/



}
