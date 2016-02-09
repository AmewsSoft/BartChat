// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.StanzaListener;

public interface SmackDebugger
{

    public abstract Reader getReader();

    public abstract StanzaListener getReaderListener();

    public abstract Writer getWriter();

    public abstract StanzaListener getWriterListener();

    public abstract Reader newConnectionReader(Reader reader);

    public abstract Writer newConnectionWriter(Writer writer);

    public abstract void userHasLogged(String s);
}
