// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqlast;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.iqlast.packet.LastActivity;

public class LastActivityManager extends Manager
{

    private static boolean enabledPerDefault = true;
    private static final Map instances = new WeakHashMap();
    private boolean enabled;
    private volatile long lastMessageSent;

    private LastActivityManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        enabled = false;
        xmppconnection.addPacketSendingListener(new StanzaListener() {

            final LastActivityManager this$0;

            public void processPacket(Stanza stanza)
            {
                stanza = ((Presence)stanza).getMode();
                if (stanza == null)
                {
                    return;
                }
                static class _cls5
                {

                    static final int $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode[];

                    static 
                    {
                        $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode = new int[org.jivesoftware.smack.packet.Presence.Mode.values().length];
                        try
                        {
                            $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode[org.jivesoftware.smack.packet.Presence.Mode.available.ordinal()] = 1;
                        }
                        catch (NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode[org.jivesoftware.smack.packet.Presence.Mode.chat.ordinal()] = 2;
                        }
                        catch (NoSuchFieldError nosuchfielderror)
                        {
                            return;
                        }
                    }
                }

                switch (_cls5..SwitchMap.org.jivesoftware.smack.packet.Presence.Mode[stanza.ordinal()])
                {
                default:
                    return;

                case 1: // '\001'
                case 2: // '\002'
                    resetIdleTime();
                    break;
                }
            }

            
            {
                this$0 = LastActivityManager.this;
                super();
            }
        }, StanzaTypeFilter.PRESENCE);
        xmppconnection.addPacketSendingListener(new StanzaListener() {

            final LastActivityManager this$0;

            public void processPacket(Stanza stanza)
            {
                if (((Message)stanza).getType() == org.jivesoftware.smack.packet.Message.Type.error)
                {
                    return;
                } else
                {
                    resetIdleTime();
                    return;
                }
            }

            
            {
                this$0 = LastActivityManager.this;
                super();
            }
        }, StanzaTypeFilter.MESSAGE);
        xmppconnection.registerIQRequestHandler(new AbstractIqRequestHandler("query", "jabber:iq:last", org.jivesoftware.smack.packet.IQ.Type.get, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async) {

            final LastActivityManager this$0;

            public IQ handleIQRequest(IQ iq)
            {
                if (!enabled)
                {
                    return IQ.createErrorResponse(iq, new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.not_acceptable));
                } else
                {
                    LastActivity lastactivity = new LastActivity();
                    lastactivity.setType(org.jivesoftware.smack.packet.IQ.Type.result);
                    lastactivity.setTo(iq.getFrom());
                    lastactivity.setFrom(iq.getTo());
                    lastactivity.setStanzaId(iq.getStanzaId());
                    lastactivity.setLastActivity(getIdleTime());
                    return lastactivity;
                }
            }

            
            {
                this$0 = LastActivityManager.this;
                super(s, s1, type, mode);
            }
        });
        if (enabledPerDefault)
        {
            enable();
        }
        resetIdleTime();
        instances.put(xmppconnection, this);
    }

    private long getIdleTime()
    {
        long l = lastMessageSent;
        return (System.currentTimeMillis() - l) / 1000L;
    }

    public static LastActivityManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/iqlast/LastActivityManager;
        JVM INSTR monitorenter ;
        LastActivityManager lastactivitymanager1 = (LastActivityManager)instances.get(xmppconnection);
        LastActivityManager lastactivitymanager;
        lastactivitymanager = lastactivitymanager1;
        if (lastactivitymanager1 != null)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        lastactivitymanager = new LastActivityManager(xmppconnection);
        org/jivesoftware/smackx/iqlast/LastActivityManager;
        JVM INSTR monitorexit ;
        return lastactivitymanager;
        xmppconnection;
        throw xmppconnection;
    }

    private void resetIdleTime()
    {
        lastMessageSent = System.currentTimeMillis();
    }

    public static void setEnabledPerDefault(boolean flag)
    {
        enabledPerDefault = flag;
    }

    public void disable()
    {
        this;
        JVM INSTR monitorenter ;
        ServiceDiscoveryManager.getInstanceFor(connection()).removeFeature("jabber:iq:last");
        enabled = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void enable()
    {
        this;
        JVM INSTR monitorenter ;
        ServiceDiscoveryManager.getInstanceFor(connection()).addFeature("jabber:iq:last");
        enabled = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public LastActivity getLastActivity(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        s = new LastActivity(s);
        return (LastActivity)connection().createPacketCollectorAndSend(s).nextResultOrThrow();
    }

    public boolean isLastActivitySupported(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(s, "jabber:iq:last");
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                LastActivityManager.getInstanceFor(xmppconnection);
            }

        });
    }



}
