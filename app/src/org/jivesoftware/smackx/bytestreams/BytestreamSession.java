// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface BytestreamSession
{

    public abstract void close()
        throws IOException;

    public abstract InputStream getInputStream()
        throws IOException;

    public abstract OutputStream getOutputStream()
        throws IOException;

    public abstract int getReadTimeout()
        throws IOException;

    public abstract void setReadTimeout(int i)
        throws IOException;
}
