// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class XMPPInputOutputStream
{
    public static final class FlushMethod extends Enum
    {

        private static final FlushMethod $VALUES[];
        public static final FlushMethod FULL_FLUSH;
        public static final FlushMethod SYNC_FLUSH;

        public static FlushMethod valueOf(String s)
        {
            return (FlushMethod)Enum.valueOf(org/jivesoftware/smack/compression/XMPPInputOutputStream$FlushMethod, s);
        }

        public static FlushMethod[] values()
        {
            return (FlushMethod[])$VALUES.clone();
        }

        static 
        {
            FULL_FLUSH = new FlushMethod("FULL_FLUSH", 0);
            SYNC_FLUSH = new FlushMethod("SYNC_FLUSH", 1);
            $VALUES = (new FlushMethod[] {
                FULL_FLUSH, SYNC_FLUSH
            });
        }

        private FlushMethod(String s, int i)
        {
            super(s, i);
        }
    }


    protected static FlushMethod flushMethod;
    protected final String compressionMethod;

    protected XMPPInputOutputStream(String s)
    {
        compressionMethod = s;
    }

    public static void setFlushMethod(FlushMethod flushmethod)
    {
        flushMethod = flushmethod;
    }

    public String getCompressionMethod()
    {
        return compressionMethod;
    }

    public abstract InputStream getInputStream(InputStream inputstream)
        throws IOException;

    public abstract OutputStream getOutputStream(OutputStream outputstream)
        throws IOException;

    public abstract boolean isSupported();
}
