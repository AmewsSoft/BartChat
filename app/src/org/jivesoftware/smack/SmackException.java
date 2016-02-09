// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.util.dns.HostAddress;

// Referenced classes of package org.jivesoftware.smack:
//            PacketCollector, XMPPConnection

public class SmackException extends Exception
{
    public static class AlreadyConnectedException extends SmackException
    {

        private static final long serialVersionUID = 0x458c2122a5b39a7fL;

        public AlreadyConnectedException()
        {
            super("Client is already connected");
        }
    }

    public static class AlreadyLoggedInException extends SmackException
    {

        private static final long serialVersionUID = 0x458c2122a5bfcf7fL;

        public AlreadyLoggedInException()
        {
            super("Client is already logged in");
        }
    }

    public static class ConnectionException extends SmackException
    {

        private static final long serialVersionUID = 0x17693ad0489b288cL;
        private final List failedAddresses;

        public static ConnectionException from(List list)
        {
            StringBuilder stringbuilder = new StringBuilder("The following addresses failed: ");
            for (Iterator iterator = list.iterator(); iterator.hasNext(); stringbuilder.append(", "))
            {
                stringbuilder.append(((HostAddress)iterator.next()).getErrorMessage());
            }

            stringbuilder.setLength(stringbuilder.length() - ", ".length());
            return new ConnectionException(stringbuilder.toString(), list);
        }

        public List getFailedAddresses()
        {
            return failedAddresses;
        }

        private ConnectionException(String s, List list)
        {
            super(s);
            failedAddresses = list;
        }

        public ConnectionException(Throwable throwable)
        {
            super(throwable);
            failedAddresses = new ArrayList(0);
        }
    }

    public static class FeatureNotSupportedException extends SmackException
    {

        private static final long serialVersionUID = 0x416960b1fedf2ef0L;
        private final String feature;
        private final String jid;

        public String getFeature()
        {
            return feature;
        }

        public String getJid()
        {
            return jid;
        }

        public FeatureNotSupportedException(String s)
        {
            this(s, null);
        }

        public FeatureNotSupportedException(String s, String s1)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append(s).append(" not supported");
            String s2;
            if (s1 == null)
            {
                s2 = "";
            } else
            {
                s2 = (new StringBuilder()).append(" by '").append(s1).append("'").toString();
            }
            super(stringbuilder.append(s2).toString());
            jid = s1;
            feature = s;
        }
    }

    public static class IllegalStateChangeException extends SmackException
    {

        private static final long serialVersionUID = 0xe77dd290515d5fe1L;

        public IllegalStateChangeException()
        {
        }
    }

    public static class NoResponseException extends SmackException
    {

        private static final long serialVersionUID = 0xa5785b3b891da66cL;
        private final StanzaFilter filter;

        public static NoResponseException newWith(XMPPConnection xmppconnection)
        {
            return newWith(xmppconnection, (StanzaFilter)null);
        }

        public static NoResponseException newWith(XMPPConnection xmppconnection, PacketCollector packetcollector)
        {
            return newWith(xmppconnection, packetcollector.getStanzaFilter());
        }

        public static NoResponseException newWith(XMPPConnection xmppconnection, StanzaFilter stanzafilter)
        {
            long l = xmppconnection.getPacketReplyTimeout();
            xmppconnection = new StringBuilder(256);
            xmppconnection.append((new StringBuilder()).append("No response received within reply timeout. Timeout was ").append(l).append("ms (~").append(l / 1000L).append("s). Used filter: ").toString());
            if (stanzafilter != null)
            {
                xmppconnection.append(stanzafilter.toString());
            } else
            {
                xmppconnection.append("No filter used or filter was 'null'");
            }
            xmppconnection.append('.');
            return new NoResponseException(xmppconnection.toString(), stanzafilter);
        }

        public StanzaFilter getFilter()
        {
            return filter;
        }

        private NoResponseException(String s, StanzaFilter stanzafilter)
        {
            super(s);
            filter = stanzafilter;
        }
    }

    public static class NotConnectedException extends SmackException
    {

        private static final long serialVersionUID = 0x7fa5ca7107423695L;

        public NotConnectedException()
        {
            super("Client is not, or no longer, connected");
        }
    }

    public static class NotLoggedInException extends SmackException
    {

        private static final long serialVersionUID = 0x2ca24c2cc72fb24eL;

        public NotLoggedInException()
        {
            super("Client is not logged in");
        }
    }

    public static class ResourceBindingNotOfferedException extends SmackException
    {

        private static final long serialVersionUID = 0x2091fc2a05d56a83L;

        public ResourceBindingNotOfferedException()
        {
            super("Resource binding was not offered by server");
        }
    }

    public static class SecurityNotPossibleException extends SmackException
    {

        private static final long serialVersionUID = 0xa12153920900b138L;

        public SecurityNotPossibleException(String s)
        {
            super(s);
        }
    }

    public static class SecurityRequiredByClientException extends SecurityRequiredException
    {

        private static final long serialVersionUID = 0x213de824b609eff7L;

        public SecurityRequiredByClientException()
        {
            super("SSL/TLS required by client but not supported by server");
        }
    }

    public static class SecurityRequiredByServerException extends SecurityRequiredException
    {

        private static final long serialVersionUID = 0x72be5d89fbf1ad4bL;

        public SecurityRequiredByServerException()
        {
            super("SSL/TLS required by server but disabled in client");
        }
    }

    public static abstract class SecurityRequiredException extends SmackException
    {

        private static final long serialVersionUID = 0x5554765a11440e9L;

        public SecurityRequiredException(String s)
        {
            super(s);
        }
    }


    private static final long serialVersionUID = 0x1999998fd20aa3b9L;

    protected SmackException()
    {
    }

    public SmackException(String s)
    {
        super(s);
    }

    public SmackException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public SmackException(Throwable throwable)
    {
        super(throwable);
    }
}
