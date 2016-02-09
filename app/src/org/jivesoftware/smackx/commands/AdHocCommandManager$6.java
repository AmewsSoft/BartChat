// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommandManager, LocalCommand

class this._cls0
    implements Runnable
{

    final AdHocCommandManager this$0;

    public void run()
    {
        do
        {
            Iterator iterator = AdHocCommandManager.access$300(AdHocCommandManager.this).keySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                String s = (String)iterator.next();
                LocalCommand localcommand = (LocalCommand)AdHocCommandManager.access$300(AdHocCommandManager.this).get(s);
                if (localcommand != null)
                {
                    long l = localcommand.getCreationDate();
                    if (System.currentTimeMillis() - l > 0x3a980L)
                    {
                        AdHocCommandManager.access$300(AdHocCommandManager.this).remove(s);
                    }
                }
            } while (true);
            try
            {
                Thread.sleep(1000L);
            }
            catch (InterruptedException interruptedexception) { }
        } while (true);
    }

    ()
    {
        this$0 = AdHocCommandManager.this;
        super();
    }
}
