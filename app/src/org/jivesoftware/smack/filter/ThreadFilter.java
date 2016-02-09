// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;

// Referenced classes of package org.jivesoftware.smack.filter:
//            FlexibleStanzaTypeFilter, StanzaFilter

public class ThreadFilter extends FlexibleStanzaTypeFilter
    implements StanzaFilter
{

    private final String thread;

    public ThreadFilter(String s)
    {
        StringUtils.requireNotNullOrEmpty(s, "Thread must not be null or empty.");
        thread = s;
    }

    protected boolean acceptSpecific(Message message)
    {
        return thread.equals(message.getThread());
    }

    protected volatile boolean acceptSpecific(Stanza stanza)
    {
        return acceptSpecific((Message)stanza);
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(": thread=").append(thread).toString();
    }
}
