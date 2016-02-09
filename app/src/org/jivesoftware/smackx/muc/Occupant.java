// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MUCAffiliation, MUCRole

public class Occupant
{

    private final MUCAffiliation affiliation;
    private final String jid;
    private final String nick;
    private final MUCRole role;

    Occupant(Presence presence)
    {
        MUCItem mucitem = ((MUCUser)presence.getExtension("x", "http://jabber.org/protocol/muc#user")).getItem();
        jid = mucitem.getJid();
        affiliation = mucitem.getAffiliation();
        role = mucitem.getRole();
        nick = XmppStringUtils.parseResource(presence.getFrom());
    }

    Occupant(MUCItem mucitem)
    {
        jid = mucitem.getJid();
        affiliation = mucitem.getAffiliation();
        role = mucitem.getRole();
        nick = mucitem.getNick();
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Occupant))
        {
            return false;
        } else
        {
            obj = (Occupant)obj;
            return jid.equals(((Occupant) (obj)).jid);
        }
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

    public int hashCode()
    {
        int j = affiliation.hashCode();
        int k = role.hashCode();
        int l = jid.hashCode();
        int i;
        if (nick != null)
        {
            i = nick.hashCode();
        } else
        {
            i = 0;
        }
        return ((j * 17 + k) * 17 + l) * 17 + i;
    }
}
