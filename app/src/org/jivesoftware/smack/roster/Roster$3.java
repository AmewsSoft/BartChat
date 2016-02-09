// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.ExceptionCallback;

// Referenced classes of package org.jivesoftware.smack.roster:
//            Roster

class this._cls0
    implements ExceptionCallback
{

    final Roster this$0;

    public void processException(Exception exception)
    {
        Roster.access$200().log(Level.SEVERE, "Exception reloading roster", exception);
    }

    k()
    {
        this$0 = Roster.this;
        super();
    }
}
