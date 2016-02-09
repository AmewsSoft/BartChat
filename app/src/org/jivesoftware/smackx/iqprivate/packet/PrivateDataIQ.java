// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqprivate.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.iqprivate.packet:
//            PrivateData

public class PrivateDataIQ extends IQ
{

    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:private";
    private final String getElement;
    private final String getNamespace;
    private final PrivateData privateData;

    public PrivateDataIQ(String s, String s1)
    {
        this(null, s, s1);
        setType(org.jivesoftware.smack.packet.IQ.Type.get);
    }

    public PrivateDataIQ(PrivateData privatedata)
    {
        this(privatedata, null, null);
        setType(org.jivesoftware.smack.packet.IQ.Type.set);
    }

    private PrivateDataIQ(PrivateData privatedata, String s, String s1)
    {
        super("query", "jabber:iq:private");
        privateData = privatedata;
        getElement = s;
        getNamespace = s1;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        if (privateData != null)
        {
            iqchildelementxmlstringbuilder.append(privateData.toXML());
            return iqchildelementxmlstringbuilder;
        } else
        {
            iqchildelementxmlstringbuilder.halfOpenElement(getElement).xmlnsAttribute(getNamespace).closeEmptyElement();
            return iqchildelementxmlstringbuilder;
        }
    }

    public PrivateData getPrivateData()
    {
        return privateData;
    }
}
