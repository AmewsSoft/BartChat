// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.debugger;

import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smack.debugger:
//            AbstractDebugger

public class ConsoleDebugger extends AbstractDebugger
{

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss aaa");

    public ConsoleDebugger(XMPPConnection xmppconnection, Writer writer, Reader reader)
    {
        super(xmppconnection, writer, reader);
    }

    protected void log(String s)
    {
        String s1;
        synchronized (dateFormatter)
        {
            s1 = dateFormatter.format(new Date());
        }
        System.out.println((new StringBuilder()).append(s1).append(' ').append(s).toString());
        return;
        s;
        simpledateformat;
        JVM INSTR monitorexit ;
        throw s;
    }
}
