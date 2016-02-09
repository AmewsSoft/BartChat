// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import org.jivesoftware.smackx.muc.packet.MUCItem;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MUCAffiliation, MUCRole

public class Affiliate
{

    private final MUCAffiliation affiliation;
    private final String jid;
    private final String nick;
    private final MUCRole role;

    Affiliate(MUCItem mucitem)
    {
        jid = mucitem.getJid();
        affiliation = mucitem.getAffiliation();
        role = mucitem.getRole();
        nick = mucitem.getNick();
    }

    public MUCAffiliation getAffiliation()
    {
        return affiliation;
    }

    public String getJid()
    {
        return jid;
    }

    public String getNick()
    {
        return nick;
    }

    public MUCRole getRole()
    {
        return role;
    }
}
