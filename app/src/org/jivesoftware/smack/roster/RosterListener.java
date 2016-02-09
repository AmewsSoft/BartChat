// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster;

import java.util.Collection;
import org.jivesoftware.smack.packet.Presence;

public interface RosterListener
{

    public abstract void entriesAdded(Collection collection);

    public abstract void entriesDeleted(Collection collection);

    public abstract void entriesUpdated(Collection collection);

    public abstract void presenceChanged(Presence presence);
}
