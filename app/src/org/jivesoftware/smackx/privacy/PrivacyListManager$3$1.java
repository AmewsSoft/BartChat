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

class val.activeListName
    implements StanzaListener
{

    final val.activeListName this$1;
    final String val$activeListName;
    final boolean val$declinceActiveList;

    public void processPacket(Stanza stanza)
        throws org.jivesoftware.smack.ctedException
    {
        if (val$declinceActiveList)
        {
            PrivacyListManager.access$202(_fld0, null);
            return;
        } else
        {
            PrivacyListManager.access$202(_fld0, val$activeListName);
            return;
        }
    }

    is._cls0()
    {
        this$1 = final__pcls0;
        val$declinceActiveList = flag;
        val$activeListName = String.this;
        super();
    }

    // Unreferenced inner class org/jivesoftware/smackx/privacy/PrivacyListManager$3

/* anonymous class */
    class PrivacyListManager._cls3
        implements StanzaListener
    {

        final PrivacyListManager this$0;

        public void processPacket(Stanza stanza)
            throws org.jivesoftware.smack.SmackException.NotConnectedException
        {
            XMPPConnection xmppconnection = PrivacyListManager.access$100(PrivacyListManager.this);
            stanza = (Privacy)stanza;
            IQResultReplyFilter iqresultreplyfilter = new IQResultReplyFilter(stanza, xmppconnection);
            String s = stanza.getActiveName();
            xmppconnection.addOneTimeSyncCallback(s. new PrivacyListManager._cls3._cls1(), iqresultreplyfilter);
        }

            
            {
                this$0 = PrivacyListManager.this;
                super();
            }
    }

}
