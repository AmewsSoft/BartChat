// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

// Referenced classes of package org.jivesoftware.smack.util:
//            SHA1

public class StringUtils
{

    public static final String AMP_ENCODE = "&amp;";
    public static final String APOS_ENCODE = "&apos;";
    public static final String GT_ENCODE = "&gt;";
    public static final char HEX_CHARS[] = "0123456789abcdef".toCharArray();
    public static final String LT_ENCODE = "&lt;";
    public static final String MD5 = "MD5";
    public static final String QUOTE_ENCODE = "&quot;";
    public static final String SHA1 = "SHA-1";
    public static final String USASCII = "US-ASCII";
    public static final String UTF8 = "UTF-8";
    private static char numbersAndLetters[] = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static Random randGen = new Random();

    public StringUtils()
    {
    }

    public static String collectionToString(Collection collection)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for (collection = collection.iterator(); collection.hasNext(); stringbuilder.append(" "))
        {
            stringbuilder.append((String)collection.next());
        }

        collection = stringbuilder.toString();
        return collection.substring(0, collection.length() - 1);
    }

    public static String encodeHex(byte abyte0[])
    {
        char ac[] = new char[abyte0.length * 2];
        for (int i = 0; i < abyte0.length; i++)
        {
            int j = abyte0[i] & 0xff;
            ac[i * 2] = HEX_CHARS[j >>> 4];
            ac[i * 2 + 1] = HEX_CHARS[j & 0xf];
        }

        return new String(ac);
    }

    public static CharSequence escapeForXML(String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        s = null;
_L11:
        return s;
_L2:
        char ac[];
        StringBuilder stringbuilder;
        int i;
        int j;
        int k;
        ac = s.toCharArray();
        k = ac.length;
        stringbuilder = new StringBuilder((int)((double)k * 1.3D));
        j = 0;
        i = 0;
_L9:
        Object obj;
        if (i >= k)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = null;
        ac[i];
        JVM INSTR lookupswitch 5: default 104
    //                   34: 165
    //                   38: 159
    //                   39: 171
    //                   60: 147
    //                   62: 153;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        if (obj != null)
        {
            if (i > j)
            {
                stringbuilder.append(ac, j, i - j);
            }
            stringbuilder.append(((CharSequence) (obj)));
            i++;
            j = i;
        } else
        {
            i++;
        }
        if (true) goto _L9; else goto _L7
_L7:
        obj = "&lt;";
          goto _L3
_L8:
        obj = "&gt;";
          goto _L3
_L5:
        obj = "&amp;";
          goto _L3
_L4:
        obj = "&quot;";
          goto _L3
_L6:
        obj = "&apos;";
          goto _L3
        if (j == 0) goto _L11; else goto _L10
_L10:
        if (i > j)
        {
            stringbuilder.append(ac, j, i - j);
        }
        return stringbuilder;
    }

    public static String hash(String s)
    {
        org/jivesoftware/smack/util/StringUtils;
        JVM INSTR monitorenter ;
        s = org.jivesoftware.smack.util.SHA1.hex(s);
        org/jivesoftware/smack/util/StringUtils;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public static boolean isEmpty(CharSequence charsequence)
    {
        return charsequence.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence charsequence)
    {
        return !isNullOrEmpty(charsequence);
    }

    public static boolean isNullOrEmpty(CharSequence charsequence)
    {
        return charsequence == null || isEmpty(charsequence);
    }

    public static String maybeToString(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            return null;
        } else
        {
            return charsequence.toString();
        }
    }

    public static int nullSafeCharSequenceComperator(CharSequence charsequence, CharSequence charsequence1)
    {
        boolean flag1 = true;
        byte byte0;
        boolean flag;
        if (charsequence == null)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        if (charsequence1 == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag ^ byte0)
        {
            byte0 = flag1;
            if (charsequence == null)
            {
                byte0 = -1;
            }
            return byte0;
        }
        if (charsequence == null && charsequence1 == null)
        {
            return 0;
        } else
        {
            return charsequence.toString().compareTo(charsequence1.toString());
        }
    }

    public static boolean nullSafeCharSequenceEquals(CharSequence charsequence, CharSequence charsequence1)
    {
        return nullSafeCharSequenceComperator(charsequence, charsequence1) == 0;
    }

    public static String randomString(int i)
    {
        if (i < 1)
        {
            return null;
        }
        char ac[] = new char[i];
        for (i = 0; i < ac.length; i++)
        {
            ac[i] = numbersAndLetters[randGen.nextInt(numbersAndLetters.length)];
        }

        return new String(ac);
    }

    public static CharSequence requireNotNullOrEmpty(CharSequence charsequence, String s)
    {
        if (isNullOrEmpty(charsequence))
        {
            throw new IllegalArgumentException(s);
        } else
        {
            return charsequence;
        }
    }

    public static String returnIfNotEmptyTrimmed(String s)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            String s1 = s.trim();
            s = s1;
            if (s1.length() <= 0)
            {
                return null;
            }
        }
        return s;
    }

    public static byte[] toBytes(String s)
    {
        try
        {
            s = s.getBytes("UTF-8");
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new IllegalStateException("UTF-8 encoding not supported by platform", s);
        }
        return s;
    }

}
