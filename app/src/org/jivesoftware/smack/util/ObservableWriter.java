// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package org.jivesoftware.smack.util:
//            WriterListener

public class ObservableWriter extends Writer
{

    List listeners;
    Writer wrappedWriter;

    public ObservableWriter(Writer writer)
    {
        wrappedWriter = null;
        listeners = new ArrayList();
        wrappedWriter = writer;
    }

    private void notifyListeners(String s)
    {
        WriterListener awriterlistener[];
        synchronized (listeners)
        {
            awriterlistener = new WriterListener[listeners.size()];
            listeners.toArray(awriterlistener);
        }
        for (int i = 0; i < awriterlistener.length; i++)
        {
            awriterlistener[i].write(s);
        }

        break MISSING_BLOCK_LABEL_67;
        s;
        list;
        JVM INSTR monitorexit ;
        throw s;
    }

    public void addWriterListener(WriterListener writerlistener)
    {
        if (writerlistener == null)
        {
            return;
        }
        synchronized (listeners)
        {
            if (!listeners.contains(writerlistener))
            {
                listeners.add(writerlistener);
            }
        }
        return;
        writerlistener;
        list;
        JVM INSTR monitorexit ;
        throw writerlistener;
    }

    public void close()
        throws IOException
    {
        wrappedWriter.close();
    }

    public void flush()
        throws IOException
    {
        wrappedWriter.flush();
    }

    public void removeWriterListener(WriterListener writerlistener)
    {
        synchronized (listeners)
        {
            listeners.remove(writerlistener);
        }
        return;
        writerlistener;
        list;
        JVM INSTR monitorexit ;
        throw writerlistener;
    }

    public void write(int i)
        throws IOException
    {
        wrappedWriter.write(i);
    }

    public void write(String s)
        throws IOException
    {
        wrappedWriter.write(s);
        notifyListeners(s);
    }

    public void write(String s, int i, int j)
        throws IOException
    {
        wrappedWriter.write(s, i, j);
        notifyListeners(s.substring(i, i + j));
    }

    public void write(char ac[])
        throws IOException
    {
        wrappedWriter.write(ac);
        notifyListeners(new String(ac));
    }

    public void write(char ac[], int i, int j)
        throws IOException
    {
        wrappedWriter.write(ac, i, j);
        notifyListeners(new String(ac, i, j));
    }
}
