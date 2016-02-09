// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.compression;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

// Referenced classes of package org.jivesoftware.smack.compression:
//            Java7ZlibInputOutputStream

class val.flushMethodInt extends DeflaterOutputStream
{

    final Java7ZlibInputOutputStream this$0;
    final int val$flushMethodInt;

    public void flush()
        throws IOException
    {
        if (!Java7ZlibInputOutputStream.access$000())
        {
            super.flush();
            return;
        }
_L1:
        int i;
        try
        {
            i = ((Integer)Java7ZlibInputOutputStream.access$100().invoke(def, new Object[] {
                buf, Integer.valueOf(0), Integer.valueOf(buf.length), Integer.valueOf(val$flushMethodInt)
            })).intValue();
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw new IOException("Can't flush");
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new IOException("Can't flush");
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new IOException("Can't flush");
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        out.write(buf, 0, i);
          goto _L1
        super.flush();
        return;
    }

    (Deflater deflater, int i)
    {
        this$0 = final_java7zlibinputoutputstream;
        val$flushMethodInt = i;
        super(OutputStream.this, deflater);
    }
}
