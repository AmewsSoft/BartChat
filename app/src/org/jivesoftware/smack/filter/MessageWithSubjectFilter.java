// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smack.filter:
//            FlexibleStanzaTypeFilter, StanzaFilter

public class MessageWithSubjectFilter extends FlexibleStanzaTypeFilter
{

    public static final StanzaFilter INSTANCE = new MessageWithSubjectFilter();

    private MessageWithSubjectFilter()
    {
        super(org/jivesoftware/smack/packet/Message);
    }

    protected boolean acceptSpecific(Message message)
    {
        return message.getSubject() != null;
    }

    protected volatile boolean acceptSpecific(Stanza stanza)
    {
        return acceptSpecific((Message)stanza);
    }

    public String toString()
    {
        return getClass().getSimpleName();
    }

}
