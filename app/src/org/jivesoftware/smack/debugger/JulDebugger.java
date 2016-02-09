// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smack.debugger:
//            AbstractDebugger

public class JulDebugger extends AbstractDebugger
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/debugger/JulDebugger.getName());

    public JulDebugger(XMPPConnection xmppconnection, Writer writer, Reader reader)
    {
        super(xmppconnection, writer, reader);
    }

    protected void log(String s)
    {
        LOGGER.fine(s);
    }

}
