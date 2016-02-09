// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster.rosterstore;

import java.util.Collection;

public interface RosterStore
{

    public abstract boolean addEntry(org.jivesoftware.smack.roster.packet.RosterPacket.Item item, String s);

    public abstract Collection getEntries();

    public abstract org.jivesoftware.smack.roster.packet.RosterPacket.Item getEntry(String s);

    public abstract String getRosterVersion();

    public abstract boolean removeEntry(String s, String s1);

    public abstract boolean resetEntries(Collection collection, String s);
}
