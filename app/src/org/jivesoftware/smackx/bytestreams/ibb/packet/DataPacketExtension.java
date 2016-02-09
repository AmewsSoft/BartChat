// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smack.util.stringencoder.Base64;

public class DataPacketExtension
    implements ExtensionElement
{

    public static final String ELEMENT = "data";
    public static final String NAMESPACE = "http://jabber.org/protocol/ibb";
    private final String data;
    private byte decodedData[];
    private final long seq;
    private final String sessionID;

    public DataPacketExtension(String s, long l, String s1)
    {
        if (s == null || "".equals(s))
        {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        }
        if (l < 0L || l > 65535L)
        {
            throw new IllegalArgumentException("Sequence must not be between 0 and 65535");
        }
        if (s1 == null)
        {
            throw new IllegalArgumentException("Data must not be null");
        } else
        {
            sessionID = s;
            seq = l;
            data = s1;
            return;
        }
    }

    public String getData()
    {
        return data;
    }

    public byte[] getDecodedData()
    {
        if (decodedData != null)
        {
            return decodedData;
        }
        if (data.matches(".*={1,2}+.+"))
        {
            return null;
        } else
        {
            decodedData = Base64.decode(data);
            return decodedData;
        }
    }

    public String getElementName()
    {
        return "data";
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.attribute("seq", Long.toString(seq));
        iqchildelementxmlstringbuilder.attribute("sid", sessionID);
        iqchildelementxmlstringbuilder.rightAngleBracket();
        iqchildelementxmlstringbuilder.append(data);
        return iqchildelementxmlstringbuilder;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/ibb";
    }

    public long getSeq()
    {
        return seq;
    }

    public String getSessionID()
    {
        return sessionID;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder = getIQChildElementBuilder(new org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder(this));
        iqchildelementxmlstringbuilder.closeElement(this);
        return iqchildelementxmlstringbuilder;
    }
}
