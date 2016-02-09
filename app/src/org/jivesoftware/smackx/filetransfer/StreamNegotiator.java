// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.io.InputStream;
import java.io.OutputStream;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.EventManger;
import org.jivesoftware.smackx.si.packet.StreamInitiation;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public abstract class StreamNegotiator
{

    protected static final EventManger initationSetEvents = new EventManger();

    public StreamNegotiator()
    {
    }

    protected static StreamInitiation createInitiationAccept(StreamInitiation streaminitiation, String as[])
    {
        StreamInitiation streaminitiation1 = new StreamInitiation();
        streaminitiation1.setTo(streaminitiation.getFrom());
        streaminitiation1.setFrom(streaminitiation.getTo());
        streaminitiation1.setType(org.jivesoftware.smack.packet.IQ.Type.result);
        streaminitiation1.setStanzaId(streaminitiation.getStanzaId());
        streaminitiation = new DataForm(org.jivesoftware.smackx.xdata.packet.DataForm.Type.submit);
        FormField formfield = new FormField("stream-method");
        int j = as.length;
        for (int i = 0; i < j; i++)
        {
            formfield.addValue(as[i]);
        }

        streaminitiation.addField(formfield);
        streaminitiation1.setFeatureNegotiationForm(streaminitiation);
        return streaminitiation1;
    }

    public static void signal(String s, IQ iq)
    {
        initationSetEvents.signalEvent(s, iq);
    }

    public abstract InputStream createIncomingStream(StreamInitiation streaminitiation)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, InterruptedException, org.jivesoftware.smack.SmackException.NoResponseException, SmackException;

    public abstract OutputStream createOutgoingStream(String s, String s1, String s2)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException, SmackException, XMPPException;

    public abstract String[] getNamespaces();

    protected final IQ initiateIncomingStream(final XMPPConnection connection, StreamInitiation streaminitiation)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        final StreamInitiation response = createInitiationAccept(streaminitiation, getNamespaces());
        newStreamInitiation(streaminitiation.getFrom(), streaminitiation.getSessionID());
        streaminitiation = (new StringBuilder()).append(streaminitiation.getFrom().toString()).append('\t').append(streaminitiation.getSessionID()).toString();
        try
        {
            streaminitiation = (IQ)initationSetEvents.performActionAndWaitForEvent(streaminitiation, connection.getPacketReplyTimeout(), new org.jivesoftware.smack.util.EventManger.Callback() {

                final StreamNegotiator this$0;
                final XMPPConnection val$connection;
                final StreamInitiation val$response;

                public void action()
                    throws org.jivesoftware.smack.SmackException.NotConnectedException
                {
                    connection.sendStanza(response);
                }

            
            {
                this$0 = StreamNegotiator.this;
                connection = xmppconnection;
                response = streaminitiation;
                super();
            }
            });
        }
        // Misplaced declaration of an exception variable
        catch (final XMPPConnection connection)
        {
            throw new IllegalStateException(connection);
        }
        if (streaminitiation == null)
        {
            throw org.jivesoftware.smack.SmackException.NoResponseException.newWith(connection);
        } else
        {
            org.jivesoftware.smack.XMPPException.XMPPErrorException.ifHasErrorThenThrow(streaminitiation);
            return streaminitiation;
        }
    }

    abstract InputStream negotiateIncomingStream(Stanza stanza)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, InterruptedException, org.jivesoftware.smack.SmackException.NoResponseException, SmackException;

    protected abstract void newStreamInitiation(String s, String s1);

}
