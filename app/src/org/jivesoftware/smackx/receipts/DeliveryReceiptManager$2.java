// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.receipts;

import java.util.Iterator;
import java.util.Set;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smackx.receipts:
//            DeliveryReceiptManager, DeliveryReceipt, ReceiptReceivedListener

class this._cls0
    implements StanzaListener
{

    final DeliveryReceiptManager this$0;

    public void processPacket(Stanza stanza)
        throws org.jivesoftware.smack.edException
    {
        DeliveryReceipt deliveryreceipt = DeliveryReceipt.from((Message)stanza);
        for (Iterator iterator = DeliveryReceiptManager.access$000(DeliveryReceiptManager.this).iterator(); iterator.hasNext(); ((ReceiptReceivedListener)iterator.next()).onReceiptReceived(stanza.getFrom(), stanza.getTo(), deliveryreceipt.getId(), stanza)) { }
    }

    n()
    {
        this$0 = DeliveryReceiptManager.this;
        super();
    }
}
