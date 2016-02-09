// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smackx.disco.AbstractNodeInformationProvider;

// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommandManager

class ovider extends AbstractNodeInformationProvider
{

    final AdHocCommandManager this$0;

    public List getNodeItems()
    {
        ArrayList arraylist = new ArrayList();
        org.jivesoftware.smackx.disco.packet.ommandInfo ommandinfo;
        for (Iterator iterator = AdHocCommandManager.access$000(AdHocCommandManager.this).iterator(); iterator.hasNext(); arraylist.add(ommandinfo))
        {
            HocCommandInfo hoccommandinfo = (HocCommandInfo)iterator.next();
            ommandinfo = new org.jivesoftware.smackx.disco.packet.it>(hoccommandinfo.getOwnerJID());
            ommandinfo.Name(hoccommandinfo.getName());
            ommandinfo.Node(hoccommandinfo.getNode());
        }

        return arraylist;
    }

    ()
    {
        this$0 = AdHocCommandManager.this;
        super();
    }
}
