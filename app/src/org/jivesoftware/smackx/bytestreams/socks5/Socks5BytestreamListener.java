// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.BytestreamRequest;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            Socks5BytestreamRequest

public abstract class Socks5BytestreamListener
    implements BytestreamListener
{

    public Socks5BytestreamListener()
    {
    }

    public void incomingBytestreamRequest(BytestreamRequest bytestreamrequest)
    {
        incomingBytestreamRequest((Socks5BytestreamRequest)bytestreamrequest);
    }

    public abstract void incomingBytestreamRequest(Socks5BytestreamRequest socks5bytestreamrequest);
}
