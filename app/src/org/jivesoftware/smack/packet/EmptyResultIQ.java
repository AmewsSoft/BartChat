// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;


// Referenced classes of package org.jivesoftware.smack.packet:
//            IQ

public class EmptyResultIQ extends IQ
{

    public EmptyResultIQ()
    {
        super(null, null);
        setType(IQ.Type.result);
    }

    public EmptyResultIQ(IQ iq)
    {
        this();
        if (iq.getType() != IQ.Type.get && iq.getType() != IQ.Type.set)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("IQ must be of type 'set' or 'get'. Original IQ: ").append(iq.toXML()).toString());
        } else
        {
            setStanzaId(iq.getStanzaId());
            setFrom(iq.getTo());
            setTo(iq.getFrom());
            return;
        }
    }

    protected IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        return null;
    }
}
