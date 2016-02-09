// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.stringencoder;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package org.jivesoftware.smack.util.stringencoder:
//            StringEncoder

public class Base32
{

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ2345678";
    private static final StringEncoder base32Stringencoder = new StringEncoder() {

        public String decode(String s)
        {
            return Base32.decode(s);
        }

        public String encode(String s)
        {
            return Base32.encode(s);
        }

    };

    public Base32()
    {
    }

    public static String decode(String s)
    {
        ByteArrayOutputStream bytearrayoutputstream;
        DataOutputStream dataoutputstream;
        int j;
        bytearrayoutputstream = new ByteArrayOutputStream();
        s = s.getBytes();
        for (int i = 0; i < s.length; i++)
        {
            char c = (char)s[i];
            if (!Character.isWhitespace(c))
            {
                bytearrayoutputstream.write((byte)Character.toUpperCase(c));
            }
        }

        for (; bytearrayoutputstream.size() % 8 != 0; bytearrayoutputstream.write(56)) { }
        s = bytearrayoutputstream.toByteArray();
        bytearrayoutputstream.reset();
        dataoutputstream = new DataOutputStream(bytearrayoutputstream);
        j = 0;
_L5:
        if (j >= s.length / 8) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        short word0;
        short word1;
        short word2;
        short word3;
        short word4;
        short word5;
        short word6;
        short word7;
        short word8;
        short word9;
        short word10;
        short word11;
        short aword0[] = new short[8];
        l = 8;
        k = 0;
        do
        {
            if (k >= 8 || (char)s[j * 8 + k] == '8')
            {
                l = paddingToLen(l);
                if (l < 0)
                {
                    return null;
                }
                break;
            }
            aword0[k] = (short)"ABCDEFGHIJKLMNOPQRSTUVWXYZ2345678".indexOf(s[j * 8 + k]);
            if (aword0[k] < 0)
            {
                return null;
            }
            l--;
            k++;
        } while (true);
        word0 = aword0[0];
        word1 = aword0[1];
        word2 = aword0[1];
        word3 = aword0[2];
        word4 = aword0[3];
        word5 = aword0[3];
        word6 = aword0[4];
        word7 = aword0[4];
        word8 = aword0[5];
        word9 = aword0[6];
        word10 = aword0[6];
        word11 = aword0[7];
        k = 0;
_L4:
        if (k >= l)
        {
            continue; /* Loop/switch isn't completed */
        }
        dataoutputstream.writeByte((byte)((new int[] {
            word0 << 3 | word1 >> 2, (word2 & 3) << 6 | word3 << 1 | word4 >> 4, (word5 & 0xf) << 4 | word6 >> 1 & 0xf, word7 << 7 | word8 << 2 | word9 >> 3, (word10 & 7) << 5 | word11
        })[k] & 0xff));
        k++;
        if (true) goto _L4; else goto _L3
_L3:
        IOException ioexception;
        ioexception;
        j++;
          goto _L5
_L2:
        return new String(bytearrayoutputstream.toByteArray());
    }

    public static String encode(String s)
    {
        s = s.getBytes();
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        for (int i = 0; i < (s.length + 4) / 5; i++)
        {
            short aword0[] = new short[5];
            int ai[] = new int[8];
            int l = 5;
            int j = 0;
            while (j < 5) 
            {
                if (i * 5 + j < s.length)
                {
                    aword0[j] = (short)(s[i * 5 + j] & 0xff);
                } else
                {
                    aword0[j] = 0;
                    l--;
                }
                j++;
            }
            l = lenToPadding(l);
            ai[0] = (byte)(aword0[0] >> 3 & 0x1f);
            ai[1] = (byte)((aword0[0] & 7) << 2 | aword0[1] >> 6 & 3);
            ai[2] = (byte)(aword0[1] >> 1 & 0x1f);
            ai[3] = (byte)((aword0[1] & 1) << 4 | aword0[2] >> 4 & 0xf);
            ai[4] = (byte)((aword0[2] & 0xf) << 1 | aword0[3] >> 7 & 1);
            ai[5] = (byte)(aword0[3] >> 2 & 0x1f);
            ai[6] = (byte)((aword0[3] & 3) << 3 | aword0[4] >> 5 & 7);
            ai[7] = (byte)(aword0[4] & 0x1f);
            for (int k = 0; k < ai.length - l; k++)
            {
                bytearrayoutputstream.write("ABCDEFGHIJKLMNOPQRSTUVWXYZ2345678".charAt(ai[k]));
            }

        }

        return new String(bytearrayoutputstream.toByteArray());
    }

    public static StringEncoder getStringEncoder()
    {
        return base32Stringencoder;
    }

    private static int lenToPadding(int i)
    {
        switch (i)
        {
        default:
            return -1;

        case 1: // '\001'
            return 6;

        case 2: // '\002'
            return 4;

        case 3: // '\003'
            return 3;

        case 4: // '\004'
            return 1;

        case 5: // '\005'
            return 0;
        }
    }

    private static int paddingToLen(int i)
    {
        switch (i)
        {
        case 2: // '\002'
        case 5: // '\005'
        default:
            return -1;

        case 6: // '\006'
            return 1;

        case 4: // '\004'
            return 2;

        case 3: // '\003'
            return 3;

        case 1: // '\001'
            return 4;

        case 0: // '\0'
            return 5;
        }
    }

}
