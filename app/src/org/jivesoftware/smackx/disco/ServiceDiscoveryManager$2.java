// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco;

import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;

// Referenced classes of package org.jivesoftware.smackx.disco:
//            ServiceDiscoveryManager, NodeInformationProvider

class r extends AbstractIqRequestHandler
{

    final ServiceDiscoveryManager this$0;

    public IQ handleIQRequest(IQ iq)
    {
        iq = (DiscoverItems)iq;
        DiscoverItems discoveritems = new DiscoverItems();
        discoveritems.setType(org.jivesoftware.smack.packet.pe);
        discoveritems.setTo(iq.getFrom());
        discoveritems.setStanzaId(iq.getStanzaId());
        discoveritems.setNode(iq.getNode());
        NodeInformationProvider nodeinformationprovider = ServiceDiscoveryManager.access$000(ServiceDiscoveryManager.this, iq.getNode());
        if (nodeinformationprovider != null)
        {
            discoveritems.addItems(nodeinformationprovider.getNodeItems());
            discoveritems.addExtensions(nodeinformationprovider.getNodePacketExtensions());
        } else
        if (iq.getNode() != null)
        {
            discoveritems.setType(org.jivesoftware.smack.packet.pe);
            discoveritems.setError(new XMPPError(org.jivesoftware.smack.packet.ot_found));
            return discoveritems;
        }
        return discoveritems;
    }

    r(String s, String s1, org.jivesoftware.smack.packet.r r, org.jivesoftware.smack.iqrequest. )
    {
        this$0 = ServiceDiscoveryManager.this;
        super(s, s1, r, );
    }
}
