// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.disco.AbstractNodeInformationProvider;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChatManager

class Provider extends AbstractNodeInformationProvider
{

    final em this$0;
    final WeakReference val$weakRefConnection;

    public List getNodeItems()
    {
        Object obj = (XMPPConnection)val$weakRefConnection.get();
        if (obj != null) goto _L2; else goto _L1
_L1:
        obj = Collections.emptyList();
_L4:
        return ((List) (obj));
_L2:
        obj = MultiUserChatManager.getInstanceFor(((XMPPConnection) (obj))).getJoinedRooms();
        ArrayList arraylist = new ArrayList();
        Iterator iterator = ((Set) (obj)).iterator();
        do
        {
            obj = arraylist;
            if (!iterator.hasNext())
            {
                continue;
            }
            arraylist.add(new org.jivesoftware.smackx.disco.packet.((String)iterator.next()));
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    ()
    {
        this$0 = final_;
        val$weakRefConnection = WeakReference.this;
        super();
    }

    // Unreferenced inner class org/jivesoftware/smackx/muc/MultiUserChatManager$1

/* anonymous class */
    static final class MultiUserChatManager._cls1
        implements ConnectionCreationListener
    {

        public void connectionCreated(XMPPConnection xmppconnection)
        {
            ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("http://jabber.org/protocol/muc");
            WeakReference weakreference = new WeakReference(xmppconnection);
            ServiceDiscoveryManager.getInstanceFor(xmppconnection).setNodeInformationProvider("http://jabber.org/protocol/muc#rooms", weakreference. new MultiUserChatManager._cls1._cls1());
        }

    }

}
