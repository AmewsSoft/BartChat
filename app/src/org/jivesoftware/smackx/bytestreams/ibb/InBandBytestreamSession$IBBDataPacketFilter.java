// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamSession

private class <init>
    implements StanzaFilter
{

    final InBandBytestreamSession this$0;

    public boolean accept(Stanza stanza)
    {
        if (stanza.getFrom().equalsIgnoreCase(InBandBytestreamSession.access$1000(InBandBytestreamSession.this))) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if (!(stanza instanceof Data))
        {
            break; /* Loop/switch isn't completed */
        }
        stanza = ((Data)stanza).getDataPacketExtension();
_L4:
        if (stanza.getSessionID().equals(InBandBytestreamSession.access$1100(InBandBytestreamSession.this).getSessionID()))
        {
            return true;
        }
        if (true) goto _L1; else goto _L3
_L3:
        DataPacketExtension datapacketextension = (DataPacketExtension)stanza.getExtension("data", "http://jabber.org/protocol/ibb");
        stanza = datapacketextension;
        if (datapacketextension == null)
        {
            return false;
        }
          goto _L4
        if (true) goto _L1; else goto _L5
_L5:
    }

    private Y()
    {
        this$0 = InBandBytestreamSession.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
