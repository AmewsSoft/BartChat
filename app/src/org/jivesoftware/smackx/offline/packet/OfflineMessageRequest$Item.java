// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.offline.packet;


// Referenced classes of package org.jivesoftware.smackx.offline.packet:
//            OfflineMessageRequest

public static class node
{

    private String action;
    private String jid;
    private String node;

    public String getAction()
    {
        return action;
    }

    public String getJid()
    {
        return jid;
    }

    public String getNode()
    {
        return node;
    }

    public void setAction(String s)
    {
        action = s;
    }

    public void setJid(String s)
    {
        jid = s;
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<item");
        if (getAction() != null)
        {
            stringbuilder.append(" action=\"").append(getAction()).append("\"");
        }
        if (getJid() != null)
        {
            stringbuilder.append(" jid=\"").append(getJid()).append("\"");
        }
        if (getNode() != null)
        {
            stringbuilder.append(" node=\"").append(getNode()).append("\"");
        }
        stringbuilder.append("/>");
        return stringbuilder.toString();
    }

    public (String s)
    {
        node = s;
    }
}
