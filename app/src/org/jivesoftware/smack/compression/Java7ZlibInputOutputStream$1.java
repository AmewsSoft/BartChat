// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.compression;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

// Referenced classes of package org.jivesoftware.smack.compression:
//            Java7ZlibInputOutputStream

class this._cls0 extends InflaterInputStream
{

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

    (InputStream inputstream, Inflater inflater, int i)
    {
        this$0 = Java7ZlibInputOutputStream.this;
        super(inputstream, inflater, i);
    }
}
