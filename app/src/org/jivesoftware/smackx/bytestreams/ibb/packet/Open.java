// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb.packet;

import java.util.Locale;
import org.jivesoftware.smack.packet.IQ;

public class Open extends IQ
{

    public static final String ELEMENT = "open";
    public static final String NAMESPACE = "http://jabber.org/protocol/ibb";
    private final int blockSize;
    private final String sessionID;
    private final org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType stanza;

    public Open(String s, int i)
    {
        this(s, i, org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType.IQ);
    }

    public Open(String s, int i, org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType stanzatype)
    {
        super("open", "http://jabber.org/protocol/ibb");
        if (s == null || "".equals(s))
        {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        }
        if (i <= 0)
        {
            throw new IllegalArgumentException("Block size must be greater than zero");
        } else
        {
            sessionID = s;
            blockSize = i;
            stanza = stanzatype;
            setType(org.jivesoftware.smack.packet.IQ.Type.set);
            return;
        }
    }

    public int getBlockSize()
    {
        return blockSize;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.attribute("block-size", Integer.toString(blockSize));
        iqchildelementxmlstringbuilder.attribute("sid", sessionID);
        iqchildelementxmlstringbuilder.attribute("stanza", stanza.toString().toLowerCase(Locale.US));
        iqchildelementxmlstringbuilder.setEmptyElement();
        return iqchildelementxmlstringbuilder;
    }

    public String getSessionID()
    {
        return sessionID;
    }

    public org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType getStanza()
    {
        return stanza;
    }
}
