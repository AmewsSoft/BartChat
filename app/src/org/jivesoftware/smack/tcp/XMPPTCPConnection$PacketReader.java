// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.tcp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SynchronizationPoint;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.sm.SMUtils;
import org.jivesoftware.smack.sm.provider.ParseStreamManagement;
import org.jivesoftware.smack.util.Async;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smack.util.StringUtils;
import org.xmlpull.v1.XmlPullParser;

// Referenced classes of package org.jivesoftware.smack.tcp:
//            XMPPTCPConnection, XMPPTCPConnectionConfiguration

protected class this._cls0
{

    static final boolean $assertionsDisabled;
    private volatile boolean done;
    XmlPullParser parser;
    final XMPPTCPConnection this$0;

    private void parsePackets()
    {
        int i;
        XMPPTCPConnection.access$300(XMPPTCPConnection.this).checkIfSuccessOrWait();
        i = parser.getEventType();
_L7:
        if (done) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 1 3: default 1452
    //                   1 1441
    //                   2 69
    //                   3 1414;
           goto _L3 _L4 _L5 _L6
_L3:
        i = parser.next();
          goto _L7
_L5:
        Object obj = parser.getName();
        i = -1;
        ((String) (obj)).hashCode();
        JVM INSTR lookupswitch 16: default 1455
    //                   -1867169789: 408
    //                   -1609594047: 438
    //                   -1281977283: 453
    //                   -1276666629: 307
    //                   -1086574198: 378
    //                   -891990144: 321
    //                   -369449087: 423
    //                   -309519186: 363
    //                   -290659267: 349
    //                   97: 483
    //                   114: 498
    //                   3368: 293
    //                   96784904: 335
    //                   954925063: 279
    //                   1097547223: 468
    //                   1402633315: 393;
           goto _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24
_L39:
        XMPPTCPConnection.access$2000().warning((new StringBuilder()).append("Unknown top level stream element: ").append(((String) (obj))).toString());
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
        XMPPTCPConnection.access$400(XMPPTCPConnection.this, parser);
        XMPPTCPConnection.access$502(XMPPTCPConnection.this, SMUtils.incrementHeight(XMPPTCPConnection.access$500(XMPPTCPConnection.this)));
          goto _L3
        Object obj1;
        obj1;
        XMPPTCPConnection.access$502(XMPPTCPConnection.this, SMUtils.incrementHeight(XMPPTCPConnection.access$500(XMPPTCPConnection.this)));
        throw obj1;
_L41:
        if (!"jabber:client".equals(parser.getNamespace(null))) goto _L3; else goto _L25
_L25:
        XMPPTCPConnection.access$602(XMPPTCPConnection.this, parser.getAttributeValue("", "id"));
        obj1 = parser.getAttributeValue("", "from");
        if ($assertionsDisabled || ((String) (obj1)).equals(XMPPTCPConnection.access$700(XMPPTCPConnection.this).getServiceName())) goto _L3; else goto _L26
_L26:
        throw new AssertionError();
_L42:
        throw new org.jivesoftware.smack.ion(PacketParserUtils.parseStreamError(parser));
_L43:
        XMPPTCPConnection.access$800(XMPPTCPConnection.this, parser);
          goto _L3
_L44:
        XMPPTCPConnection.access$900(XMPPTCPConnection.this);
        openStream();
          goto _L3
        obj1;
        XMPPTCPConnection.access$1000(XMPPTCPConnection.this).reportFailure(new SmackException(((Throwable) (obj1))));
        throw obj1;
_L45:
        obj1 = parser.getNamespace(null);
        i = -1;
        ((String) (obj1)).hashCode();
        JVM INSTR lookupswitch 3: default 1539
    //                   -1570142914: 811
    //                   919182852: 783
    //                   2117926358: 797;
           goto _L27 _L28 _L29 _L30
_L54:
        throw new org.jivesoftware.smack.n("TLS negotiation has failed", null);
_L29:
        if (((String) (obj1)).equals("urn:ietf:params:xml:ns:xmpp-tls"))
        {
            i = 0;
        }
          goto _L27
_L30:
        if (((String) (obj1)).equals("http://jabber.org/protocol/compress"))
        {
            i = 1;
        }
          goto _L27
_L28:
        if (((String) (obj1)).equals("urn:ietf:params:xml:ns:xmpp-sasl"))
        {
            i = 2;
        }
          goto _L27
_L55:
        XMPPTCPConnection.access$1100(XMPPTCPConnection.this).reportFailure(new org.jivesoftware.smack.n("Could not establish compression", null));
          goto _L3
_L56:
        obj1 = PacketParserUtils.parseSASLFailure(parser);
        XMPPTCPConnection.access$1200(XMPPTCPConnection.this).authenticationFailed(((org.jivesoftware.smack.sasl.packet.ceName) (obj1)));
          goto _L3
_L46:
        obj1 = parser.nextText();
        XMPPTCPConnection.access$1300(XMPPTCPConnection.this).challengeReceived(((String) (obj1)));
          goto _L3
_L47:
        obj1 = new org.jivesoftware.smack.sasl.packet.t>(parser.nextText());
        openStream();
        XMPPTCPConnection.access$1400(XMPPTCPConnection.this).authenticated(((org.jivesoftware.smack.sasl.packet.t>) (obj1)));
          goto _L3
_L48:
        XMPPTCPConnection.access$1500(XMPPTCPConnection.this);
        openStream();
        XMPPTCPConnection.access$1100(XMPPTCPConnection.this).reportSuccess();
          goto _L3
_L49:
        obj1 = ParseStreamManagement.enabled(parser);
        if (!((org.jivesoftware.smack.sm.packet.) (obj1)).meSet()) goto _L32; else goto _L31
_L31:
        XMPPTCPConnection.access$1602(XMPPTCPConnection.this, ((org.jivesoftware.smack.sm.packet.) (obj1)).());
        if (StringUtils.isNullOrEmpty(XMPPTCPConnection.access$1600(XMPPTCPConnection.this)))
        {
            obj1 = new org.jivesoftware.smack.n("Stream Management 'enabled' element with resume attribute but without session id received", new XMPPError(org.jivesoftware.smack.packet.));
            XMPPTCPConnection.access$1700(XMPPTCPConnection.this).reportFailure(((Exception) (obj1)));
            throw obj1;
        }
        XMPPTCPConnection.access$1802(XMPPTCPConnection.this, ((org.jivesoftware.smack.sm.packet.) (obj1)).ResumptionTime());
_L33:
        XMPPTCPConnection.access$502(XMPPTCPConnection.this, 0L);
        XMPPTCPConnection.access$1902(XMPPTCPConnection.this, true);
        XMPPTCPConnection.access$1700(XMPPTCPConnection.this).reportSuccess();
        XMPPTCPConnection.access$2000().fine("Stream Management (XEP-198): succesfully enabled");
          goto _L3
_L32:
        XMPPTCPConnection.access$1602(XMPPTCPConnection.this, null);
          goto _L33
_L50:
        obj1 = new org.jivesoftware.smack.n("Stream Management failed", new XMPPError(ParseStreamManagement.failed(parser).ErrorCondition()));
        if (!XMPPTCPConnection.access$2100(XMPPTCPConnection.this).requestSent()) goto _L35; else goto _L34
_L34:
        XMPPTCPConnection.access$2100(XMPPTCPConnection.this).reportFailure(((Exception) (obj1)));
          goto _L3
_L35:
        if (!XMPPTCPConnection.access$1700(XMPPTCPConnection.this).requestSent())
        {
            throw new IllegalStateException("Failed element received but SM was not previously enabled");
        }
        XMPPTCPConnection.access$1700(XMPPTCPConnection.this).reportFailure(((Exception) (obj1)));
        XMPPTCPConnection.access$2200(XMPPTCPConnection.this).reportSuccess();
          goto _L3
_L51:
        Object obj2 = ParseStreamManagement.resumed(parser);
        if (!XMPPTCPConnection.access$1600(XMPPTCPConnection.this).equals(((org.jivesoftware.smack.sm.packet.) (obj2)).vId()))
        {
            throw new org.jivesoftware.smack.sm.mIdDoesNotMatchException(XMPPTCPConnection.access$1600(XMPPTCPConnection.this), ((org.jivesoftware.smack.sm.packet.) (obj2)).vId());
        }
        XMPPTCPConnection.access$2300(XMPPTCPConnection.this, ((org.jivesoftware.smack.sm.packet.) (obj2)).dledCount());
        obj2 = new LinkedList();
        ((List) (obj2)).addAll(XMPPTCPConnection.access$2400(XMPPTCPConnection.this));
        Stanza stanza;
        for (obj2 = ((List) (obj2)).iterator(); ((Iterator) (obj2)).hasNext(); packetWriter.sendStreamElement(stanza))
        {
            stanza = (Stanza)((Iterator) (obj2)).next();
        }

        XMPPTCPConnection.access$2100(XMPPTCPConnection.this).reportSuccess();
        XMPPTCPConnection.access$1700(XMPPTCPConnection.this).reportSuccess();
        XMPPTCPConnection.access$2000().fine("Stream Management (XEP-198): Stream resumed");
          goto _L3
_L52:
        org.jivesoftware.smack.sm.packet.e e = ParseStreamManagement.ackAnswer(parser);
        XMPPTCPConnection.access$2300(XMPPTCPConnection.this, e.andledCount());
          goto _L3
_L53:
        ParseStreamManagement.ackRequest(parser);
        if (!XMPPTCPConnection.access$1700(XMPPTCPConnection.this).wasSuccessful()) goto _L37; else goto _L36
_L36:
        XMPPTCPConnection.access$2500(XMPPTCPConnection.this);
          goto _L3
_L37:
        XMPPTCPConnection.access$2000().warning("SM Ack Request received while SM is not enabled");
          goto _L3
_L6:
        if (!parser.getName().equals("stream")) goto _L3; else goto _L38
_L38:
        disconnect();
          goto _L3
_L4:
        try
        {
            throw new SmackException("Parser got END_DOCUMENT event. This could happen e.g. if the server closed the connection without sending a closing stream element");
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            if (!done && !isSocketClosed())
            {
                XMPPTCPConnection.access$2600(XMPPTCPConnection.this, ((Exception) (obj)));
            }
        }
          goto _L2
_L8:
        i;
        JVM INSTR tableswitch 0 15: default 1536
    //                   0 517
    //                   1 517
    //                   2 517
    //                   3 570
    //                   4 654
    //                   5 669
    //                   6 683
    //                   7 721
    //                   8 870
    //                   9 894
    //                   10 932
    //                   11 959
    //                   12 1097
    //                   13 1200
    //                   14 1349
    //                   15 1371;
           goto _L39 _L40 _L40 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53
_L27:
        i;
        JVM INSTR tableswitch 0 2: default 1568
    //                   0 772
    //                   1 825
    //                   2 848;
           goto _L3 _L54 _L55 _L56
    }

    void init()
    {
        done = false;
        Async.go(new Runnable() {

            final XMPPTCPConnection.PacketReader this$1;

            public void run()
            {
                parsePackets();
            }

            
            {
                this$1 = XMPPTCPConnection.PacketReader.this;
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



    protected _cls1.this._cls1()
    {
        this$0 = XMPPTCPConnection.this;
        super();
    }
}
