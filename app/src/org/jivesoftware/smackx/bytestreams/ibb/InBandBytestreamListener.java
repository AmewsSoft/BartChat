// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb;

import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.BytestreamRequest;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.ibb:
//            InBandBytestreamRequest

public abstract class InBandBytestreamListener
    implements BytestreamListener
{

    public InBandBytestreamListener()
    {
    }

    public void incomingBytestreamRequest(BytestreamRequest bytestreamrequest)
    {
        incomingBytestreamRequest((InBandBytestreamRequest)bytestreamrequest);
    }

    public abstract void incomingBytestreamRequest(InBandBytestreamRequest inbandbytestreamrequest);
}
