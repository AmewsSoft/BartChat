// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps;

import java.util.List;
import org.jivesoftware.smackx.disco.AbstractNodeInformationProvider;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

// Referenced classes of package org.jivesoftware.smackx.caps:
//            EntityCapsManager

class ager.getExtendedInfoAsList extends AbstractNodeInformationProvider
{

    List features;
    List packetExtensions;
    final EntityCapsManager this$0;
    final List val$identities;

    public List getNodeFeatures()
    {
        return features;
    }

    public List getNodeIdentities()
    {
        return val$identities;
    }

    public List getNodePacketExtensions()
    {
        return packetExtensions;
    }

    ager()
    {
        this$0 = final_entitycapsmanager;
        val$identities = List.this;
        super();
        features = EntityCapsManager.access$600(EntityCapsManager.this).getFeatures();
        packetExtensions = EntityCapsManager.access$600(EntityCapsManager.this).getExtendedInfoAsList();
    }
}
