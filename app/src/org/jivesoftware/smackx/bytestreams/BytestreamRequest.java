// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams;

import org.jivesoftware.smack.SmackException;

// Referenced classes of package org.jivesoftware.smackx.bytestreams:
//            BytestreamSession

public interface BytestreamRequest
{

    public abstract BytestreamSession accept()
        throws InterruptedException, org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, SmackException;

    public abstract String getFrom();

    public abstract String getSessionID();

    public abstract void reject()
        throws org.jivesoftware.smack.SmackException.NotConnectedException;
}
