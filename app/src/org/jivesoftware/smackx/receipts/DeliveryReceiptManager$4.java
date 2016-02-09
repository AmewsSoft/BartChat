// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.receipts;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smackx.receipts:
//            DeliveryReceiptManager, DeliveryReceiptRequest

static final class n
    implements StanzaListener
{

    public void processPacket(Stanza stanza)
        throws org.jivesoftware.smack.edException
    {
        DeliveryReceiptRequest.addTo((Message)stanza);
    }

    n()
    {
    }
}
