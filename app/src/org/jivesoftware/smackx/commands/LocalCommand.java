// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;

import org.jivesoftware.smackx.commands.packet.AdHocCommandData;

// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommand

public abstract class LocalCommand extends AdHocCommand
{

    private long creationDate;
    private int currenStage;
    private String ownerJID;
    private String sessionID;

    public LocalCommand()
    {
        creationDate = System.currentTimeMillis();
        currenStage = -1;
    }

    void decrementStage()
    {
        currenStage = currenStage - 1;
    }

    public long getCreationDate()
    {
        return creationDate;
    }

    public int getCurrentStage()
    {
        return currenStage;
    }

    public String getOwnerJID()
    {
        return ownerJID;
    }

    public String getSessionID()
    {
        return sessionID;
    }

    public abstract boolean hasPermission(String s);

    void incrementStage()
    {
        currenStage = currenStage + 1;
    }

    public abstract boolean isLastStage();

    void setData(AdHocCommandData adhoccommanddata)
    {
        adhoccommanddata.setSessionID(sessionID);
        super.setData(adhoccommanddata);
    }

    public void setOwnerJID(String s)
    {
        ownerJID = s;
    }

    public void setSessionID(String s)
    {
        sessionID = s;
        getData().setSessionID(s);
    }
}
