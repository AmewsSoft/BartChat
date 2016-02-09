// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet.id;

import java.util.concurrent.atomic.AtomicLong;
import org.jivesoftware.smack.util.StringUtils;

public class StanzaIdUtil
{

    private static final AtomicLong ID = new AtomicLong();
    private static final String PREFIX = (new StringBuilder()).append(StringUtils.randomString(5)).append("-").toString();

    public StanzaIdUtil()
    {
    }

    public static String newStanzaId()
    {
        return (new StringBuilder()).append(PREFIX).append(Long.toString(ID.incrementAndGet())).toString();
    }

}
