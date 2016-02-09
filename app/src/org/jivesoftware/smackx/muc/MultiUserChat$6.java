// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChat

class val.subject
    implements StanzaFilter
{

    final MultiUserChat this$0;
    final String val$subject;

    public boolean accept(Stanza stanza)
    {
        stanza = (Message)stanza;
        return val$subject.equals(stanza.getSubject());
    }

    ()
    {
        this$0 = final_multiuserchat;
        val$subject = String.this;
        super();
    }
}
