// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.debugger.android;

import android.util.Log;
import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.debugger.AbstractDebugger;

public class AndroidDebugger extends AbstractDebugger
{

    public AndroidDebugger(XMPPConnection xmppconnection, Writer writer, Reader reader)
    {
        super(xmppconnection, writer, reader);
    }

    protected void log(String s)
    {
        Log.d("SMACK", s);
    }
}
