// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco;

import java.util.List;

// Referenced classes of package org.jivesoftware.smackx.disco:
//            NodeInformationProvider

public abstract class AbstractNodeInformationProvider
    implements NodeInformationProvider
{

    public AbstractNodeInformationProvider()
    {
    }

    public List getNodeFeatures()
    {
        return null;
    }

    public List getNodeIdentities()
    {
        return null;
    }

    public List getNodeItems()
    {
        return null;
    }

    public List getNodePacketExtensions()
    {
        return null;
    }
}
