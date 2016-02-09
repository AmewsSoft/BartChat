// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco;

import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;

// Referenced classes of package org.jivesoftware.smackx.disco:
//            ServiceDiscoveryManager, NodeInformationProvider

class r extends AbstractIqRequestHandler
{

    final ServiceDiscoveryManager this$0;

    public IQ handleIQRequest(IQ iq)
    {
        Object obj = (DiscoverInfo)iq;
        iq = new DiscoverInfo();
        iq.setType(org.jivesoftware.smack.packet.e);
        iq.setTo(((DiscoverInfo) (obj)).getFrom());
        iq.setStanzaId(((DiscoverInfo) (obj)).getStanzaId());
        iq.setNode(((DiscoverInfo) (obj)).getNode());
        if (((DiscoverInfo) (obj)).getNode() == null)
        {
            addDiscoverInfoTo(iq);
            return iq;
        }
        obj = ServiceDiscoveryManager.access$000(ServiceDiscoveryManager.this, ((DiscoverInfo) (obj)).getNode());
        if (obj != null)
        {
            iq.addFeatures(((NodeInformationProvider) (obj)).getNodeFeatures());
            iq.addIdentities(((NodeInformationProvider) (obj)).getNodeIdentities());
            iq.addExtensions(((NodeInformationProvider) (obj)).getNodePacketExtensions());
            return iq;
        } else
        {
            iq.setType(org.jivesoftware.smack.packet.e);
            iq.setError(new XMPPError(org.jivesoftware.smack.packet.ot_found));
            return iq;
        }
    }

    r(String s, String s1, org.jivesoftware.smack.packet.r r, org.jivesoftware.smack.iqrequest. )
    {
        this$0 = ServiceDiscoveryManager.this;
        super(s, s1, r, );
    }
}
