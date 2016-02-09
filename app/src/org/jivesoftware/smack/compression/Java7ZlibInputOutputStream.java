// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

// Referenced classes of package org.jivesoftware.smack.compression:
//            XMPPInputOutputStream

public class Java7ZlibInputOutputStream extends XMPPInputOutputStream
{

    private static final int FULL_FLUSH_INT = 3;
    private static final int SYNC_FLUSH_INT = 2;
    private static final int compressionLevel = -1;
    private static final Method method;
    private static final boolean supported;

    public Java7ZlibInputOutputStream()
    {
        super("zlib");
    }

    public InputStream getInputStream(InputStream inputstream)
    {
        return new InflaterInputStream(inputstream, new Inflater(), 512) {

            final Java7ZlibInputOutputStream this$0;

            public int available()
                throws IOException
            {
                if (inf.needsInput())
                {
                    return 0;
                } else
                {
                    return super.available();
                }
            }

            
            {
                this$0 = Java7ZlibInputOutputStream.this;
                super(inputstream, inflater, i);
            }
        };
    }

    public OutputStream getOutputStream(final OutputStream final_outputstream)
    {
        byte byte0;
        if (flushMethod == XMPPInputOutputStream.FlushMethod.SYNC_FLUSH)
        {
            byte0 = 2;
        } else
        {
            byte0 = 3;
        }
        return new DeflaterOutputStream(new Deflater(-1), byte0) {

            final Java7ZlibInputOutputStream this$0;
            final int val$flushMethodInt;

            public void flush()
                throws IOException
            {
                if (!Java7ZlibInputOutputStream.supported)
                {
                    super.flush();
                    return;
                }
_L1:
                int i;
                try
                {
                    i = ((Integer)Java7ZlibInputOutputStream.method.invoke(def, new Object[] {
                        buf, Integer.valueOf(0), Integer.valueOf(buf.length), Integer.valueOf(flushMethodInt)
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

            
            {
                this$0 = Java7ZlibInputOutputStream.this;
                flushMethodInt = i;
                super(final_outputstream, deflater);
            }
        };
    }

    public boolean isSupported()
    {
        return supported;
    }

    static 
    {
        Method method1;
        boolean flag;
        flag = true;
        method1 = null;
        Method method2 = java/util/zip/Deflater.getMethod("deflate", new Class[] {
            [B, Integer.TYPE, Integer.TYPE, Integer.TYPE
        });
        method1 = method2;
_L2:
        method = method1;
        if (method == null)
        {
            flag = false;
        }
        supported = flag;
        return;
        Object obj;
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }


}
