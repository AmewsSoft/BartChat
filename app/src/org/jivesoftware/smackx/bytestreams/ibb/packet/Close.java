// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.IQ;

public class Close extends IQ
{

    public static final String ELEMENT = "close";
    public static final String NAMESPACE = "http://jabber.org/protocol/ibb";
    private final String sessionID;

    public Close(String s)
    {
        super("close", "http://jabber.org/protocol/ibb");
        if (s == null || "".equals(s))
        {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        } else
        {
            sessionID = s;
            setType(org.jivesoftware.smack.packet.IQ.Type.set);
            return;
        }
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.attribute("sid", sessionID);
        iqchildelementxmlstringbuilder.setEmptyElement();
        return iqchildelementxmlstringbuilder;
    }

    public String getSessionID()
    {
        return sessionID;
    }
}
