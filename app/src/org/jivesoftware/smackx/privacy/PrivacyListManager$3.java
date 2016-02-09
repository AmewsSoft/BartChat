// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.privacy;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.IQResultReplyFilter;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.privacy.packet.Privacy;

// Referenced classes of package org.jivesoftware.smackx.privacy:
//            PrivacyListManager

class this._cls0
    implements StanzaListener
{

    final PrivacyListManager this$0;

    public void processPacket(Stanza stanza)
        throws org.jivesoftware.smack.nectedException
    {
        XMPPConnection xmppconnection = PrivacyListManager.access$100(PrivacyListManager.this);
        stanza = (Privacy)stanza;
        IQResultReplyFilter iqresultreplyfilter = new IQResultReplyFilter(stanza, xmppconnection);
        String s = stanza.getActiveName();
        xmppconnection.addOneTimeSyncCallback(new StanzaListener() {

            final PrivacyListManager._cls3 this$1;
            final String val$activeListName;
            final boolean val$declinceActiveList;

            public void processPacket(Stanza stanza1)
                throws org.jivesoftware.smack.SmackException.NotConnectedException
            {
                if (declinceActiveList)
                {
                    PrivacyListManager.access$202(this$0, null);
                    return;
                } else
                {
                    PrivacyListManager.access$202(this$0, activeListName);
                    return;
                }
            }

            
            {
                this$1 = PrivacyListManager._cls3.this;
                declinceActiveList = flag;
                activeListName = s;
                super();
            }
        }, iqresultreplyfilter);
    }

    _cls1.val.activeListName()
    {
        this$0 = PrivacyListManager.this;
        super();
    }
}
