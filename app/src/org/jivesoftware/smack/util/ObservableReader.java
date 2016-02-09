// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package org.jivesoftware.smack.util:
//            ReaderListener

public class ObservableReader extends Reader
{

    List listeners;
    Reader wrappedReader;

    public ObservableReader(Reader reader)
    {
        wrappedReader = null;
        listeners = new ArrayList();
        wrappedReader = reader;
    }

    public void addReaderListener(ReaderListener readerlistener)
    {
        if (readerlistener == null)
        {
            return;
        }
        synchronized (listeners)
        {
            if (!listeners.contains(readerlistener))
            {
                listeners.add(readerlistener);
            }
        }
        return;
        readerlistener;
        list;
        JVM INSTR monitorexit ;
        throw readerlistener;
    }

    public void close()
        throws IOException
    {
        wrappedReader.close();
    }

    public void mark(int i)
        throws IOException
    {
        wrappedReader.mark(i);
    }

    public boolean markSupported()
    {
        return wrappedReader.markSupported();
    }

    public int read()
        throws IOException
    {
        return wrappedReader.read();
    }

    public int read(char ac[])
        throws IOException
    {
        return wrappedReader.read(ac);
    }

    public int read(char ac[], int i, int j)
        throws IOException
    {
        j = wrappedReader.read(ac, i, j);
        if (j > 0)
        {
            String s = new String(ac, i, j);
            ReaderListener areaderlistener[];
            synchronized (listeners)
            {
                areaderlistener = new ReaderListener[listeners.size()];
                listeners.toArray(areaderlistener);
            }
            for (i = 0; i < areaderlistener.length; i++)
            {
                areaderlistener[i].read(s);
            }

        }
        break MISSING_BLOCK_LABEL_96;
        exception;
        ac;
        JVM INSTR monitorexit ;
        throw exception;
        return j;
    }

    public boolean ready()
        throws IOException
    {
        return wrappedReader.ready();
    }

    public void removeReaderListener(ReaderListener readerlistener)
    {
        synchronized (listeners)
        {
            listeners.remove(readerlistener);
        }
        return;
        readerlistener;
        list;
        JVM INSTR monitorexit ;
        throw readerlistener;
    }

    public void reset()
        throws IOException
    {
        wrappedReader.reset();
    }

    public long skip(long l)
        throws IOException
    {
        return wrappedReader.skip(l);
    }
}
