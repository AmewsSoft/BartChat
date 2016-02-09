// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.receipts;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.roster.Roster;

// Referenced classes of package org.jivesoftware.smackx.receipts:
//            DeliveryReceiptManager

class this._cls0
    implements StanzaListener
{

    final DeliveryReceiptManager this$0;

    public void processPacket(Stanza stanza)
        throws org.jivesoftware.smack.edException
    {
        String s;
        XMPPConnection xmppconnection;
        s = stanza.getFrom();
        xmppconnection = DeliveryReceiptManager.access$100(DeliveryReceiptManager.this);
        .SwitchMap.org.jivesoftware.smackx.receipts.DeliveryReceiptManager.AutoReceiptMode[DeliveryReceiptManager.access$200(DeliveryReceiptManager.this).ordinal()];
        JVM INSTR tableswitch 1 2: default 48
    //                   1 61
    //                   2 62;
           goto _L1 _L2 _L3
_L1:
        xmppconnection.sendStanza(DeliveryReceiptManager.receiptMessageFor((Message)stanza));
_L2:
        return;
_L3:
        if (!Roster.getInstanceFor(xmppconnection).isSubscribedToMyPresence(s))
        {
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    n()
    {
        this$0 = DeliveryReceiptManager.this;
        super();
    }
}
