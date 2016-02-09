// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

// Referenced classes of package org.jivesoftware.smackx.bytestreams:
//            BytestreamListener, BytestreamSession

public interface BytestreamManager
{

    public abstract void addIncomingBytestreamListener(BytestreamListener bytestreamlistener);

    public abstract void addIncomingBytestreamListener(BytestreamListener bytestreamlistener, String s);

    public abstract BytestreamSession establishSession(String s)
        throws XMPPException, IOException, InterruptedException, SmackException;

    public abstract BytestreamSession establishSession(String s, String s1)
        throws XMPPException, IOException, InterruptedException, SmackException;

    public abstract void removeIncomingBytestreamListener(String s);

    public abstract void removeIncomingBytestreamListener(BytestreamListener bytestreamlistener);
}
