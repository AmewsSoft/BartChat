// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.bytestreams.BytestreamRequest;
import org.jivesoftware.smackx.bytestreams.BytestreamSession;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jxmpp.util.cache.Cache;
import org.jxmpp.util.cache.ExpirationCache;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            Socks5BytestreamManager, Socks5Utils, Socks5Client, Socks5BytestreamSession

public class Socks5BytestreamRequest
    implements BytestreamRequest
{

    private static final Cache ADDRESS_BLACKLIST = new ExpirationCache(100, 0x6ddd00L);
    private static final long BLACKLIST_LIFETIME = 0x6ddd00L;
    private static final int BLACKLIST_MAX_SIZE = 100;
    private static int CONNECTION_FAILURE_THRESHOLD = 2;
    private Bytestream bytestreamRequest;
    private Socks5BytestreamManager manager;
    private int minimumConnectTimeout;
    private int totalConnectTimeout;

    protected Socks5BytestreamRequest(Socks5BytestreamManager socks5bytestreammanager, Bytestream bytestream)
    {
        totalConnectTimeout = 10000;
        minimumConnectTimeout = 2000;
        manager = socks5bytestreammanager;
        bytestreamRequest = bytestream;
    }

    private void cancelRequest()
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        XMPPError xmpperror = XMPPError.from(org.jivesoftware.smack.packet.XMPPError.Condition.item_not_found, "Could not establish socket with any provided host");
        org.jivesoftware.smack.packet.ErrorIQ erroriq = IQ.createErrorResponse(bytestreamRequest, xmpperror);
        manager.getConnection().sendStanza(erroriq);
        throw new org.jivesoftware.smack.XMPPException.XMPPErrorException("Could not establish socket with any provided host", xmpperror);
    }

    private Bytestream createUsedHostResponse(org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost streamhost)
    {
        Bytestream bytestream = new Bytestream(bytestreamRequest.getSessionID());
        bytestream.setTo(bytestreamRequest.getFrom());
        bytestream.setType(org.jivesoftware.smack.packet.IQ.Type.result);
        bytestream.setStanzaId(bytestreamRequest.getStanzaId());
        bytestream.setUsedHost(streamhost.getJID());
        return bytestream;
    }

    public static int getConnectFailureThreshold()
    {
        return CONNECTION_FAILURE_THRESHOLD;
    }

    private int getConnectionFailures(String s)
    {
        s = (Integer)ADDRESS_BLACKLIST.get(s);
        if (s != null)
        {
            return s.intValue();
        } else
        {
            return 0;
        }
    }

    private void incrementConnectionFailures(String s)
    {
        Integer integer = (Integer)ADDRESS_BLACKLIST.get(s);
        Cache cache = ADDRESS_BLACKLIST;
        int i;
        if (integer == null)
        {
            i = 1;
        } else
        {
            i = integer.intValue() + 1;
        }
        cache.put(s, Integer.valueOf(i));
    }

    public static void setConnectFailureThreshold(int i)
    {
        CONNECTION_FAILURE_THRESHOLD = i;
    }

    public volatile BytestreamSession accept()
        throws InterruptedException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, SmackException
    {
        return accept();
    }

    public Socks5BytestreamSession accept()
        throws InterruptedException, org.jivesoftware.smack.XMPPException.XMPPErrorException, SmackException
    {
        Object obj1;
        Object obj2;
        String s;
        Iterator iterator;
        int i;
        java.util.List list = bytestreamRequest.getStreamHosts();
        if (list.size() == 0)
        {
            cancelRequest();
        }
        obj1 = null;
        obj2 = null;
        s = Socks5Utils.createDigest(bytestreamRequest.getSessionID(), bytestreamRequest.getFrom(), manager.getConnection().getUser());
        i = Math.max(getTotalConnectTimeout() / list.size(), getMinimumConnectTimeout());
        iterator = list.iterator();
_L2:
        java.net.Socket socket;
        org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost streamhost;
        String s1;
        streamhost = obj1;
        socket = obj2;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_180;
        }
        streamhost = (org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost)iterator.next();
        s1 = (new StringBuilder()).append(streamhost.getAddress()).append(":").append(streamhost.getPort()).toString();
        int j = getConnectionFailures(s1);
        if (CONNECTION_FAILURE_THRESHOLD > 0 && j >= CONNECTION_FAILURE_THRESHOLD)
        {
            continue; /* Loop/switch isn't completed */
        }
        socket = (new Socks5Client(streamhost, s)).getSocket(i);
        if (streamhost == null || socket == null)
        {
            cancelRequest();
        }
        Bytestream bytestream = createUsedHostResponse(streamhost);
        manager.getConnection().sendStanza(bytestream);
        return new Socks5BytestreamSession(socket, streamhost.getJID().equals(bytestreamRequest.getFrom()));
        Object obj;
        obj;
        incrementConnectionFailures(s1);
        continue; /* Loop/switch isn't completed */
        obj;
        incrementConnectionFailures(s1);
        continue; /* Loop/switch isn't completed */
        obj;
        incrementConnectionFailures(s1);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public String getFrom()
    {
        return bytestreamRequest.getFrom();
    }

    public int getMinimumConnectTimeout()
    {
        if (minimumConnectTimeout <= 0)
        {
            return 2000;
        } else
        {
            return minimumConnectTimeout;
        }
    }

    public String getSessionID()
    {
        return bytestreamRequest.getSessionID();
    }

    public int getTotalConnectTimeout()
    {
        if (totalConnectTimeout <= 0)
        {
            return 10000;
        } else
        {
            return totalConnectTimeout;
        }
    }

    public void reject()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        manager.replyRejectPacket(bytestreamRequest);
    }

    public void setMinimumConnectTimeout(int i)
    {
        minimumConnectTimeout = i;
    }

    public void setTotalConnectTimeout(int i)
    {
        totalConnectTimeout = i;
    }

}
