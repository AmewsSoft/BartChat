// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smack.debugger:
//            SmackDebugger

public interface SmackDebuggerFactory
{

    public abstract SmackDebugger create(XMPPConnection xmppconnection, Writer writer, Reader reader)
        throws IllegalArgumentException;
}
