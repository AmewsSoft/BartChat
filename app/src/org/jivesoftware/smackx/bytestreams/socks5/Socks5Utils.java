// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.DataInputStream;
import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.util.SHA1;

class Socks5Utils
{

    Socks5Utils()
    {
    }

    public static String createDigest(String s, String s1, String s2)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(s).append(s1).append(s2);
        return SHA1.hex(stringbuilder.toString());
    }

    public static byte[] receiveSocks5Message(DataInputStream datainputstream)
        throws IOException, SmackException
    {
        byte abyte0[] = new byte[5];
        datainputstream.readFully(abyte0, 0, 5);
        if (abyte0[3] != 3)
        {
            throw new SmackException("Unsupported SOCKS5 address type");
        } else
        {
            byte byte0 = abyte0[4];
            byte abyte1[] = new byte[byte0 + 7];
            System.arraycopy(abyte0, 0, abyte1, 0, abyte0.length);
            datainputstream.readFully(abyte1, abyte0.length, byte0 + 2);
            return abyte1;
        }
    }
}
