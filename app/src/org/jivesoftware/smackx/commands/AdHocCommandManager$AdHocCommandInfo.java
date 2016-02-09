// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;


// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommandManager, LocalCommandFactory, LocalCommand

private static class factory
{

    private LocalCommandFactory factory;
    private String name;
    private String node;
    private String ownerJID;

    public LocalCommand getCommandInstance()
        throws InstantiationException, IllegalAccessException
    {
        return factory.getInstance();
    }

    public String getName()
    {
        return name;
    }

    public String getNode()
    {
        return node;
    }

    public String getOwnerJID()
    {
        return ownerJID;
    }

    public (String s, String s1, String s2, LocalCommandFactory localcommandfactory)
    {
        node = s;
        name = s1;
        ownerJID = s2;
        factory = localcommandfactory;
    }
}
