// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.util.Date;

public class DiscussionHistory
{

    private int maxChars;
    private int maxStanzas;
    private int seconds;
    private Date since;

    public DiscussionHistory()
    {
        maxChars = -1;
        maxStanzas = -1;
        seconds = -1;
    }

    private boolean isConfigured()
    {
        return maxChars > -1 || maxStanzas > -1 || seconds > -1 || since != null;
    }

    org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History getMUCHistory()
    {
        org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History history;
        if (!isConfigured())
        {
            history = null;
        } else
        {
            org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History history1 = new org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History();
            if (maxChars > -1)
            {
                history1.setMaxChars(maxChars);
            }
            if (maxStanzas > -1)
            {
                history1.setMaxStanzas(maxStanzas);
            }
            if (seconds > -1)
            {
                history1.setSeconds(seconds);
            }
            history = history1;
            if (since != null)
            {
                history1.setSince(since);
                return history1;
            }
        }
        return history;
    }

    public int getMaxChars()
    {
        return maxChars;
    }

    public int getMaxStanzas()
    {
        return maxStanzas;
    }

    public int getSeconds()
    {
        return seconds;
    }

    public Date getSince()
    {
        return since;
    }

    public void setMaxChars(int i)
    {
        maxChars = i;
    }

    public void setMaxStanzas(int i)
    {
        maxStanzas = i;
    }

    public void setSeconds(int i)
    {
        seconds = i;
    }

    public void setSince(Date date)
    {
        since = date;
    }
}
