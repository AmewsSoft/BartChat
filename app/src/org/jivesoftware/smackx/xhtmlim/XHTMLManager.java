// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xhtmlim;

import java.util.List;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.xhtmlim.packet.XHTMLExtension;

// Referenced classes of package org.jivesoftware.smackx.xhtmlim:
//            XHTMLText

public class XHTMLManager
{

    public XHTMLManager()
    {
    }

    public static void addBody(Message message, XHTMLText xhtmltext)
    {
        XHTMLExtension xhtmlextension1 = XHTMLExtension.from(message);
        XHTMLExtension xhtmlextension = xhtmlextension1;
        if (xhtmlextension1 == null)
        {
            xhtmlextension = new XHTMLExtension();
            message.addExtension(xhtmlextension);
        }
        xhtmlextension.addBody(xhtmltext.toXML());
    }

    public static List getBodies(Message message)
    {
        message = XHTMLExtension.from(message);
        if (message != null)
        {
            return message.getBodies();
        } else
        {
            return null;
        }
    }

    public static boolean isServiceEnabled(XMPPConnection xmppconnection)
    {
        return ServiceDiscoveryManager.getInstanceFor(xmppconnection).includesFeature("http://jabber.org/protocol/xhtml-im");
    }

    public static boolean isServiceEnabled(XMPPConnection xmppconnection, String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(xmppconnection).supportsFeature(s, "http://jabber.org/protocol/xhtml-im");
    }

    public static boolean isXHTMLMessage(Message message)
    {
        return message.getExtension("html", "http://jabber.org/protocol/xhtml-im") != null;
    }

    public static void setServiceEnabled(XMPPConnection xmppconnection, boolean flag)
    {
        org/jivesoftware/smackx/xhtmlim/XHTMLManager;
        JVM INSTR monitorenter ;
        boolean flag1 = isServiceEnabled(xmppconnection);
        if (flag1 != flag) goto _L2; else goto _L1
_L1:
        org/jivesoftware/smackx/xhtmlim/XHTMLManager;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("http://jabber.org/protocol/xhtml-im");
          goto _L1
        xmppconnection;
        throw xmppconnection;
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).removeFeature("http://jabber.org/protocol/xhtml-im");
          goto _L1
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                XHTMLManager.setServiceEnabled(xmppconnection, true);
            }

        });
    }
}
