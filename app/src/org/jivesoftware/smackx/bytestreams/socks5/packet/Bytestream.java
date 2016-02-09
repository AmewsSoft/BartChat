// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class Bytestream extends IQ
{
    public static class Activate
        implements NamedElement
    {

        public static String ELEMENTNAME = "activate";
        private final String target;

        public String getElementName()
        {
            return ELEMENTNAME;
        }

        public String getTarget()
        {
            return target;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.rightAngleBracket();
            xmlstringbuilder.escape(getTarget());
            xmlstringbuilder.closeElement(this);
            return xmlstringbuilder;
        }


        public Activate(String s)
        {
            target = s;
        }
    }

    public static final class Mode extends Enum
    {

        private static final Mode $VALUES[];
        public static final Mode tcp;
        public static final Mode udp;

        public static Mode fromName(String s)
        {
            try
            {
                s = valueOf(s);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return tcp;
            }
            return s;
        }

        public static Mode valueOf(String s)
        {
            return (Mode)Enum.valueOf(org/jivesoftware/smackx/bytestreams/socks5/packet/Bytestream$Mode, s);
        }

        public static Mode[] values()
        {
            return (Mode[])$VALUES.clone();
        }

        static 
        {
            tcp = new Mode("tcp", 0);
            udp = new Mode("udp", 1);
            $VALUES = (new Mode[] {
                tcp, udp
            });
        }

        private Mode(String s, int i)
        {
            super(s, i);
        }
    }

    public static class StreamHost
        implements NamedElement
    {

        public static String ELEMENTNAME = "streamhost";
        private final String JID;
        private final String addy;
        private final int port;

        public String getAddress()
        {
            return addy;
        }

        public String getElementName()
        {
            return ELEMENTNAME;
        }

        public String getJID()
        {
            return JID;
        }

        public int getPort()
        {
            return port;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.attribute("jid", getJID());
            xmlstringbuilder.attribute("host", getAddress());
            if (getPort() != 0)
            {
                xmlstringbuilder.attribute("port", Integer.toString(getPort()));
            } else
            {
                xmlstringbuilder.attribute("zeroconf", "_jabber.bytestreams");
            }
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }


        public StreamHost(String s, String s1)
        {
            this(s, s1, 0);
        }

        public StreamHost(String s, String s1, int i)
        {
            JID = s;
            addy = s1;
            port = i;
        }
    }

    public static class StreamHostUsed
        implements NamedElement
    {

        public static String ELEMENTNAME = "streamhost-used";
        private final String JID;

        public String getElementName()
        {
            return ELEMENTNAME;
        }

        public String getJID()
        {
            return JID;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.attribute("jid", getJID());
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }


        public StreamHostUsed(String s)
        {
            JID = s;
        }
    }


    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "http://jabber.org/protocol/bytestreams";
    private Mode mode;
    private String sessionID;
    private final List streamHosts;
    private Activate toActivate;
    private StreamHostUsed usedHost;

    public Bytestream()
    {
        super("query", "http://jabber.org/protocol/bytestreams");
        mode = Mode.tcp;
        streamHosts = new ArrayList();
    }

    public Bytestream(String s)
    {
        this();
        setSessionID(s);
    }

    public StreamHost addStreamHost(String s, String s1)
    {
        return addStreamHost(s, s1, 0);
    }

    public StreamHost addStreamHost(String s, String s1, int i)
    {
        s = new StreamHost(s, s1, i);
        addStreamHost(((StreamHost) (s)));
        return s;
    }

    public void addStreamHost(StreamHost streamhost)
    {
        streamHosts.add(streamhost);
    }

    public int countStreamHosts()
    {
        return streamHosts.size();
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type = new int[org.jivesoftware.smack.packet.IQ.Type.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[org.jivesoftware.smack.packet.IQ.Type.set.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[org.jivesoftware.smack.packet.IQ.Type.result.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[org.jivesoftware.smack.packet.IQ.Type.get.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.org.jivesoftware.smack.packet.IQ.Type[getType().ordinal()];
        JVM INSTR tableswitch 1 3: default 36
    //                   1 44
    //                   2 131
    //                   3 184;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalStateException();
_L2:
        iqchildelementxmlstringbuilder.optAttribute("sid", getSessionID());
        iqchildelementxmlstringbuilder.optAttribute("mode", getMode());
        iqchildelementxmlstringbuilder.rightAngleBracket();
        if (getToActivate() == null)
        {
            for (Iterator iterator = getStreamHosts().iterator(); iterator.hasNext(); iqchildelementxmlstringbuilder.append(((StreamHost)iterator.next()).toXML())) { }
        } else
        {
            iqchildelementxmlstringbuilder.append(getToActivate().toXML());
        }
_L5:
        return iqchildelementxmlstringbuilder;
_L3:
        iqchildelementxmlstringbuilder.rightAngleBracket();
        iqchildelementxmlstringbuilder.optAppend(getUsedHost());
        Iterator iterator1 = streamHosts.iterator();
        while (iterator1.hasNext()) 
        {
            iqchildelementxmlstringbuilder.append(((StreamHost)iterator1.next()).toXML());
        }
        if (true) goto _L5; else goto _L4
_L4:
        iqchildelementxmlstringbuilder.setEmptyElement();
        return iqchildelementxmlstringbuilder;
    }

    public Mode getMode()
    {
        return mode;
    }

    public String getSessionID()
    {
        return sessionID;
    }

    public StreamHost getStreamHost(String s)
    {
        if (s == null)
        {
            return null;
        }
        for (Iterator iterator = streamHosts.iterator(); iterator.hasNext();)
        {
            StreamHost streamhost = (StreamHost)iterator.next();
            if (streamhost.getJID().equals(s))
            {
                return streamhost;
            }
        }

        return null;
    }

    public List getStreamHosts()
    {
        return Collections.unmodifiableList(streamHosts);
    }

    public Activate getToActivate()
    {
        return toActivate;
    }

    public StreamHostUsed getUsedHost()
    {
        return usedHost;
    }

    public void setMode(Mode mode1)
    {
        mode = mode1;
    }

    public void setSessionID(String s)
    {
        sessionID = s;
    }

    public void setToActivate(String s)
    {
        toActivate = new Activate(s);
    }

    public void setUsedHost(String s)
    {
        usedHost = new StreamHostUsed(s);
    }
}
