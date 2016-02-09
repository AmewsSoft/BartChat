// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smackx.disco.AbstractNodeInformationProvider;

// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommandManager

class ovider extends AbstractNodeInformationProvider
{

    final AdHocCommandManager this$0;
    final String val$name;

    public List getNodeFeatures()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add("http://jabber.org/protocol/commands");
        arraylist.add("jabber:x:data");
        return arraylist;
    }

    public List getNodeIdentities()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new org.jivesoftware.smackx.disco.packet.<init>("automation", val$name, "command-node"));
        return arraylist;
    }

    ity()
    {
        this$0 = final_adhoccommandmanager;
        val$name = String.this;
        super();
    }
}
