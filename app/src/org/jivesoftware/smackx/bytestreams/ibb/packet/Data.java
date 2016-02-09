// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.IQ;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb.packet:
//            DataPacketExtension

public class Data extends IQ
{

    private final DataPacketExtension dataPacketExtension;

    public Data(DataPacketExtension datapacketextension)
    {
        super("data", "http://jabber.org/protocol/ibb");
        if (datapacketextension == null)
        {
            throw new IllegalArgumentException("Data must not be null");
        } else
        {
            dataPacketExtension = datapacketextension;
            setType(org.jivesoftware.smack.packet.IQ.Type.set);
            return;
        }
    }

    public DataPacketExtension getDataPacketExtension()
    {
        return dataPacketExtension;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        return dataPacketExtension.getIQChildElementBuilder(iqchildelementxmlstringbuilder);
    }
}
