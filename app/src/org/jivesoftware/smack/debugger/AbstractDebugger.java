// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.ObservableReader;
import org.jivesoftware.smack.util.ObservableWriter;
import org.jivesoftware.smack.util.ReaderListener;
import org.jivesoftware.smack.util.WriterListener;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smack.debugger:
//            SmackDebugger

public abstract class AbstractDebugger
    implements SmackDebugger
{

    public static boolean printInterpreted = false;
    private final ConnectionListener connListener;
    private final XMPPConnection connection;
    private final StanzaListener listener;
    private ObservableReader reader;
    private final ReaderListener readerListener;
    private ObservableWriter writer;
    private final WriterListener writerListener;

    public AbstractDebugger(final XMPPConnection connection, Writer writer1, Reader reader1)
    {
        this.connection = connection;
        reader = new ObservableReader(reader1);
        readerListener = new ReaderListener() {

            final AbstractDebugger this$0;
            final XMPPConnection val$connection;

            public void read(String s)
            {
                log((new StringBuilder()).append("RECV (").append(connection.getConnectionCounter()).append("): ").append(s).toString());
            }

            
            {
                this$0 = AbstractDebugger.this;
                connection = xmppconnection;
                super();
            }
        };
        reader.addReaderListener(readerListener);
        writer = new ObservableWriter(writer1);
        writerListener = new WriterListener() {

            final AbstractDebugger this$0;
            final XMPPConnection val$connection;

            public void write(String s)
            {
                log((new StringBuilder()).append("SENT (").append(connection.getConnectionCounter()).append("): ").append(s).toString());
            }

            
            {
                this$0 = AbstractDebugger.this;
                connection = xmppconnection;
                super();
            }
        };
        writer.addWriterListener(writerListener);
        listener = new StanzaListener() {

            final AbstractDebugger this$0;
            final XMPPConnection val$connection;

            public void processPacket(Stanza stanza)
            {
                if (AbstractDebugger.printInterpreted)
                {
                    log((new StringBuilder()).append("RCV PKT (").append(connection.getConnectionCounter()).append("): ").append(stanza.toXML()).toString());
                }
            }

            
            {
                this$0 = AbstractDebugger.this;
                connection = xmppconnection;
                super();
            }
        };
        connListener = new ConnectionListener() {

            final AbstractDebugger this$0;
            final XMPPConnection val$connection;

            public void authenticated(XMPPConnection xmppconnection, boolean flag)
            {
                String s = (new StringBuilder()).append("XMPPConnection authenticated (").append(xmppconnection.getConnectionCounter()).append(")").toString();
                xmppconnection = s;
                if (flag)
                {
                    xmppconnection = (new StringBuilder()).append(s).append(" and resumed").toString();
                }
                log(xmppconnection);
            }

            public void connected(XMPPConnection xmppconnection)
            {
                log((new StringBuilder()).append("XMPPConnection connected (").append(xmppconnection.getConnectionCounter()).append(")").toString());
            }

            public void connectionClosed()
            {
                log((new StringBuilder()).append("XMPPConnection closed (").append(connection.getConnectionCounter()).append(")").toString());
            }

            public void connectionClosedOnError(Exception exception)
            {
                log((new StringBuilder()).append("XMPPConnection closed due to an exception (").append(connection.getConnectionCounter()).append(")").toString());
                exception.printStackTrace();
            }

            public void reconnectingIn(int i)
            {
                log((new StringBuilder()).append("XMPPConnection (").append(connection.getConnectionCounter()).append(") will reconnect in ").append(i).toString());
            }

            public void reconnectionFailed(Exception exception)
            {
                log((new StringBuilder()).append("Reconnection failed due to an exception (").append(connection.getConnectionCounter()).append(")").toString());
                exception.printStackTrace();
            }

            public void reconnectionSuccessful()
            {
                log((new StringBuilder()).append("XMPPConnection reconnected (").append(connection.getConnectionCounter()).append(")").toString());
            }

            
            {
                this$0 = AbstractDebugger.this;
                connection = xmppconnection;
                super();
            }
        };
    }

    public Reader getReader()
    {
        return reader;
    }

    public StanzaListener getReaderListener()
    {
        return listener;
    }

    public Writer getWriter()
    {
        return writer;
    }

    public StanzaListener getWriterListener()
    {
        return null;
    }

    protected abstract void log(String s);

    public Reader newConnectionReader(Reader reader1)
    {
        reader.removeReaderListener(readerListener);
        reader1 = new ObservableReader(reader1);
        reader1.addReaderListener(readerListener);
        reader = reader1;
        return reader;
    }

    public Writer newConnectionWriter(Writer writer1)
    {
        writer.removeWriterListener(writerListener);
        writer1 = new ObservableWriter(writer1);
        writer1.addWriterListener(writerListener);
        writer = writer1;
        return writer;
    }

    public void userHasLogged(String s)
    {
        String s1 = XmppStringUtils.parseLocalpart(s);
        boolean flag = "".equals(s1);
        StringBuilder stringbuilder = (new StringBuilder()).append("User logged (").append(connection.getConnectionCounter()).append("): ");
        if (flag)
        {
            s1 = "";
        }
        s1 = stringbuilder.append(s1).append("@").append(connection.getServiceName()).append(":").append(connection.getPort()).toString();
        log((new StringBuilder()).append(s1).append("/").append(XmppStringUtils.parseResource(s)).toString());
        connection.addConnectionListener(connListener);
    }

}
