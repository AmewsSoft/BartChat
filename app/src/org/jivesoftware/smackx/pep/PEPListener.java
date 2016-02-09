// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pep;

import org.jivesoftware.smackx.pep.packet.PEPEvent;

public interface PEPListener
{

    public abstract void eventReceived(String s, PEPEvent pepevent);
}
