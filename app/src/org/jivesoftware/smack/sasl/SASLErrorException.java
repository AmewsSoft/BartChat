// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl;

import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.XMPPException;

public class SASLErrorException extends XMPPException
{

    private static final long serialVersionUID = 0x56b3d756c4ba5dc9L;
    private final String mechanism;
    private final org.jivesoftware.smack.sasl.packet.SaslStreamElements.SASLFailure saslFailure;
    private final Map texts;

    public SASLErrorException(String s, org.jivesoftware.smack.sasl.packet.SaslStreamElements.SASLFailure saslfailure)
    {
        this(s, saslfailure, ((Map) (new HashMap())));
    }

    public SASLErrorException(String s, org.jivesoftware.smack.sasl.packet.SaslStreamElements.SASLFailure saslfailure, Map map)
    {
        super((new StringBuilder()).append("SASLError using ").append(s).append(": ").append(saslfailure.getSASLErrorString()).toString());
        mechanism = s;
        saslFailure = saslfailure;
        texts = map;
    }

    public String getMechanism()
    {
        return mechanism;
    }

    public org.jivesoftware.smack.sasl.packet.SaslStreamElements.SASLFailure getSASLFailure()
    {
        return saslFailure;
    }

    public Map getTexts()
    {
        return texts;
    }
}
