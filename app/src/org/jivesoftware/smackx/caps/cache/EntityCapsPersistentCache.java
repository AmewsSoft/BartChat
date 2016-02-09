// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps.cache;

import org.jivesoftware.smackx.disco.packet.DiscoverInfo;

public interface EntityCapsPersistentCache
{

    public abstract void addDiscoverInfoByNodePersistent(String s, DiscoverInfo discoverinfo);

    public abstract void emptyCache();

    public abstract DiscoverInfo lookup(String s);
}
