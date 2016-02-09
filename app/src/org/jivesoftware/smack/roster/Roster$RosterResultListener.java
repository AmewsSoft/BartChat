// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.jivesoftware.smack.roster.rosterstore.RosterStore;

// Referenced classes of package org.jivesoftware.smack.roster:
//            Roster, RosterEntry, RosterLoadedListener

private class <init>
    implements StanzaListener
{

    final Roster this$0;

    public void processPacket(Stanza stanza)
    {
        Object obj2 = Roster.access$1100(Roster.this);
        Roster.access$200().fine("RosterResultListener received stanza");
        Object obj = new ArrayList();
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        Object obj1 = new ArrayList();
        if (stanza instanceof RosterPacket)
        {
            RosterPacket rosterpacket = (RosterPacket)stanza;
            stanza = new ArrayList();
            Iterator iterator = rosterpacket.getRosterItems().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                org.jivesoftware.smack.roster.packet. 1 = (org.jivesoftware.smack.roster.packet.)iterator.next();
                if (Roster.access$1200(1))
                {
                    stanza.add(1);
                }
            } while (true);
            org.jivesoftware.smack.roster.packet. 2;
            RosterEntry rosterentry1;
            for (Iterator iterator1 = stanza.iterator(); iterator1.hasNext(); Roster.access$1300(Roster.this, ((Collection) (obj)), arraylist, ((Collection) (obj1)), 2, rosterentry1))
            {
                2 = (org.jivesoftware.smack.roster.packet.)iterator1.next();
                rosterentry1 = new RosterEntry(2.(), 2.(), 2.e(), 2.tus(), Roster.this, ((org.jivesoftware.smack.XMPPConnection) (obj2)));
            }

            obj2 = new HashSet();
            for (Iterator iterator2 = Roster.access$800(Roster.this).values().iterator(); iterator2.hasNext(); ((Set) (obj2)).add(((RosterEntry)iterator2.next()).getUser())) { }
            ((Set) (obj2)).removeAll(((Collection) (obj)));
            ((Set) (obj2)).removeAll(arraylist);
            ((Set) (obj2)).removeAll(((Collection) (obj1)));
            for (obj1 = ((Set) (obj2)).iterator(); ((Iterator) (obj1)).hasNext(); Roster.access$1400(Roster.this, arraylist1, (RosterEntry)Roster.access$800(Roster.this).get(obj2)))
            {
                obj2 = (String)((Iterator) (obj1)).next();
            }

            if (Roster.access$1500(Roster.this) != null)
            {
                obj1 = rosterpacket.getVersion();
                Roster.access$1500(Roster.this).resetEntries(stanza, ((String) (obj1)));
            }
            Roster.access$1600(Roster.this);
        } else
        {
            stanza = Roster.access$1500(Roster.this).getEntries().iterator();
            while (stanza.hasNext()) 
            {
                org.jivesoftware.smack.roster.packet.  = (org.jivesoftware.smack.roster.packet.s)stanza.next();
                RosterEntry rosterentry = new RosterEntry(.s(), .s(), .e(), .tus(), Roster.this, ((org.jivesoftware.smack.XMPPConnection) (obj2)));
                Roster.access$1300(Roster.this, ((Collection) (obj)), arraylist, ((Collection) (obj1)), , rosterentry);
            }
        }
        Roster.access$1702(Roster.this, true);
        synchronized (Roster.this)
        {
            notifyAll();
        }
        Roster.access$1800(Roster.this, ((Collection) (obj)), arraylist, arraylist1);
        stanza = Roster.access$1900(Roster.this);
        stanza;
        JVM INSTR monitorenter ;
        for (obj = Roster.access$1900(Roster.this).iterator(); ((Iterator) (obj)).hasNext(); ((RosterLoadedListener)((Iterator) (obj)).next()).onRosterLoaded(Roster.this)) { }
        break MISSING_BLOCK_LABEL_599;
        obj;
        stanza;
        JVM INSTR monitorexit ;
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch (Stanza stanza)
        {
            Roster.access$200().log(Level.WARNING, "RosterLoadedListener threw exception", stanza);
        }
        return;
        exception;
        stanza;
        JVM INSTR monitorexit ;
        throw exception;
        stanza;
        JVM INSTR monitorexit ;
    }

    private ()
    {
        this$0 = Roster.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
