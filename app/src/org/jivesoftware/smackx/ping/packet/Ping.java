// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.ping.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.SimpleIQ;

public class Ping extends SimpleIQ
{

    public static final String ELEMENT = "ping";
    public static final String NAMESPACE = "urn:xmpp:ping";

    public Ping()
    {
        super("ping", "urn:xmpp:ping");
    }

    public Ping(String s)
    {
        this();
        setTo(s);
        setType(org.jivesoftware.smack.packet.IQ.Type.get);
    }

    public IQ getPong()
    {
        return createResultIQ(this);
    }
}
