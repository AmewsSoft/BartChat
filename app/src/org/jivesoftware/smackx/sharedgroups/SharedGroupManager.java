// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.sharedgroups;

import java.util.List;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.sharedgroups.packet.SharedGroupsInfo;

public class SharedGroupManager
{

    public SharedGroupManager()
    {
    }

    public static List getSharedGroups(XMPPConnection xmppconnection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        SharedGroupsInfo sharedgroupsinfo = new SharedGroupsInfo();
        sharedgroupsinfo.setType(org.jivesoftware.smack.packet.IQ.Type.get);
        return ((SharedGroupsInfo)xmppconnection.createPacketCollectorAndSend(sharedgroupsinfo).nextResultOrThrow()).getGroups();
    }
}
