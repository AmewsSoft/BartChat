// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.dns;

import java.util.List;

public interface DNSResolver
{

    public abstract List lookupSRVRecords(String s)
        throws Exception;
}
