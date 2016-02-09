// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.iqrequest.IQRequestHandler;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack:
//            StanzaListener, ConnectionListener, PacketCollector, ExceptionCallback

public interface XMPPConnection
{
    public static final class FromMode extends Enum
    {

        private static final FromMode $VALUES[];
        public static final FromMode OMITTED;
        public static final FromMode UNCHANGED;
        public static final FromMode USER;

        public static FromMode valueOf(String s)
        {
            return (FromMode)Enum.valueOf(org/jivesoftware/smack/XMPPConnection$FromMode, s);
        }

        public static FromMode[] values()
        {
            return (FromMode[])$VALUES.clone();
        }

        static 
        {
            UNCHANGED = new FromMode("UNCHANGED", 0);
            OMITTED = new FromMode("OMITTED", 1);
            USER = new FromMode("USER", 2);
            $VALUES = (new FromMode[] {
                UNCHANGED, OMITTED, USER
            });
        }

        private FromMode(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract void addAsyncStanzaListener(StanzaListener stanzalistener, StanzaFilter stanzafilter);

    public abstract void addConnectionListener(ConnectionListener connectionlistener);

    public abstract void addOneTimeSyncCallback(StanzaListener stanzalistener, StanzaFilter stanzafilter);

    public abstract void addPacketInterceptor(StanzaListener stanzalistener, StanzaFilter stanzafilter);

    public abstract void addPacketListener(StanzaListener stanzalistener, StanzaFilter stanzafilter);

    public abstract void addPacketSendingListener(StanzaListener stanzalistener, StanzaFilter stanzafilter);

    public abstract void addSyncStanzaListener(StanzaListener stanzalistener, StanzaFilter stanzafilter);

    public abstract PacketCollector createPacketCollector(PacketCollector.Configuration configuration);

    public abstract PacketCollector createPacketCollector(StanzaFilter stanzafilter);

    public abstract PacketCollector createPacketCollectorAndSend(StanzaFilter stanzafilter, Stanza stanza)
        throws SmackException.NotConnectedException;

    public abstract PacketCollector createPacketCollectorAndSend(IQ iq)
        throws SmackException.NotConnectedException;

    public abstract int getConnectionCounter();

    public abstract ExtensionElement getFeature(String s, String s1);

    public abstract FromMode getFromMode();

    public abstract String getHost();

    public abstract long getLastStanzaReceived();

    public abstract long getPacketReplyTimeout();

    public abstract int getPort();

    public abstract String getServiceName();

    public abstract String getStreamId();

    public abstract String getUser();

    public abstract boolean hasFeature(String s, String s1);

    public abstract boolean isAnonymous();

    public abstract boolean isAuthenticated();

    public abstract boolean isConnected();

    public abstract boolean isSecureConnection();

    public abstract boolean isUsingCompression();

    public abstract IQRequestHandler registerIQRequestHandler(IQRequestHandler iqrequesthandler);

    public abstract boolean removeAsyncStanzaListener(StanzaListener stanzalistener);

    public abstract void removeConnectionListener(ConnectionListener connectionlistener);

    public abstract void removePacketCollector(PacketCollector packetcollector);

    public abstract void removePacketInterceptor(StanzaListener stanzalistener);

    public abstract boolean removePacketListener(StanzaListener stanzalistener);

    public abstract void removePacketSendingListener(StanzaListener stanzalistener);

    public abstract boolean removeSyncStanzaListener(StanzaListener stanzalistener);

    public abstract void send(PlainStreamElement plainstreamelement)
        throws SmackException.NotConnectedException;

    public abstract void sendIqWithResponseCallback(IQ iq, StanzaListener stanzalistener)
        throws SmackException.NotConnectedException;

    public abstract void sendIqWithResponseCallback(IQ iq, StanzaListener stanzalistener, ExceptionCallback exceptioncallback)
        throws SmackException.NotConnectedException;

    public abstract void sendIqWithResponseCallback(IQ iq, StanzaListener stanzalistener, ExceptionCallback exceptioncallback, long l)
        throws SmackException.NotConnectedException;

    public abstract void sendPacket(Stanza stanza)
        throws SmackException.NotConnectedException;

    public abstract void sendStanza(Stanza stanza)
        throws SmackException.NotConnectedException;

    public abstract void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzafilter, StanzaListener stanzalistener)
        throws SmackException.NotConnectedException;

    public abstract void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzafilter, StanzaListener stanzalistener, ExceptionCallback exceptioncallback)
        throws SmackException.NotConnectedException;

    public abstract void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzafilter, StanzaListener stanzalistener, ExceptionCallback exceptioncallback, long l)
        throws SmackException.NotConnectedException;

    public abstract void setFromMode(FromMode frommode);

    public abstract void setPacketReplyTimeout(long l);

    public abstract IQRequestHandler unregisterIQRequestHandler(String s, String s1, org.jivesoftware.smack.packet.IQ.Type type);

    public abstract IQRequestHandler unregisterIQRequestHandler(IQRequestHandler iqrequesthandler);
}
