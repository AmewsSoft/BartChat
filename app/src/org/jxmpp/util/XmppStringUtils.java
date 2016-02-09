// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.util;

import org.jxmpp.util.cache.LruCache;

public class XmppStringUtils
{

    private static final LruCache LOCALPART_ESACPE_CACHE = new LruCache(100);
    private static final LruCache LOCALPART_UNESCAPE_CACHE = new LruCache(100);

    public XmppStringUtils()
    {
    }

    public static String completeJidFrom(CharSequence charsequence, CharSequence charsequence1)
    {
        if (charsequence != null)
        {
            charsequence = charsequence.toString();
        } else
        {
            charsequence = null;
        }
        return completeJidFrom(((String) (charsequence)), charsequence1.toString());
    }

    public static String completeJidFrom(CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2)
    {
        Object obj = null;
        String s;
        if (charsequence != null)
        {
            charsequence = charsequence.toString();
        } else
        {
            charsequence = null;
        }
        s = charsequence1.toString();
        charsequence1 = obj;
        if (charsequence2 != null)
        {
            charsequence1 = charsequence2.toString();
        }
        return completeJidFrom(((String) (charsequence)), s, ((String) (charsequence1)));
    }

    public static String completeJidFrom(String s, String s1)
    {
        return completeJidFrom(s, s1, ((String) (null)));
    }

    public static String completeJidFrom(String s, String s1, String s2)
    {
        if (s1 == null)
        {
            throw new IllegalArgumentException("domainpart must not be null");
        }
        StringBuilder stringbuilder;
        int i;
        int j;
        int k;
        if (s != null)
        {
            i = s.length();
        } else
        {
            i = 0;
        }
        k = s1.length();
        if (s2 != null)
        {
            j = s2.length();
        } else
        {
            j = 0;
        }
        stringbuilder = new StringBuilder(i + k + j + 2);
        if (i > 0)
        {
            stringbuilder.append(s).append('@');
        }
        stringbuilder.append(s1);
        if (j > 0)
        {
            stringbuilder.append('/').append(s2);
        }
        return stringbuilder.toString();
    }

    public static String escapeLocalpart(String s)
    {
        String s1;
        if (s == null)
        {
            s1 = null;
        } else
        {
            String s2 = (String)LOCALPART_ESACPE_CACHE.get(s);
            s1 = s2;
            if (s2 == null)
            {
label0:
                {
label1:
                    {
label2:
                        {
label3:
                            {
label4:
                                {
label5:
                                    {
label6:
                                        {
label7:
                                            {
                                                Object obj = new StringBuilder(s.length() + 8);
                                                int i = 0;
                                                int j = s.length();
label8:
                                                do
                                                {
                                                    {
                                                        if (i >= j)
                                                        {
                                                            obj = ((StringBuilder) (obj)).toString();
                                                            LOCALPART_ESACPE_CACHE.put(s, obj);
                                                            return ((String) (obj));
                                                        }
                                                        char c = s.charAt(i);
                                                        switch (c)
                                                        {
                                                        default:
                                                            if (Character.isWhitespace(c))
                                                            {
                                                                ((StringBuilder) (obj)).append("\\20");
                                                            } else
                                                            {
                                                                ((StringBuilder) (obj)).append(c);
                                                            }
                                                            break;

                                                        case 34: // '"'
                                                            break label8;

                                                        case 38: // '&'
                                                            break label7;

                                                        case 39: // '\''
                                                            break label6;

                                                        case 47: // '/'
                                                            break label5;

                                                        case 58: // ':'
                                                            break label4;

                                                        case 60: // '<'
                                                            break label3;

                                                        case 62: // '>'
                                                            break label2;

                                                        case 64: // '@'
                                                            break label1;

                                                        case 92: // '\\'
                                                            break label0;
                                                        }
                                                    }
                                                    i++;
                                                } while (true);
                                                ((StringBuilder) (obj)).append("\\22");
                                                break MISSING_BLOCK_LABEL_178;
                                            }
                                            ((StringBuilder) (obj)).append("\\26");
                                            break MISSING_BLOCK_LABEL_178;
                                        }
                                        ((StringBuilder) (obj)).append("\\27");
                                        break MISSING_BLOCK_LABEL_178;
                                    }
                                    ((StringBuilder) (obj)).append("\\2f");
                                    break MISSING_BLOCK_LABEL_178;
                                }
                                ((StringBuilder) (obj)).append("\\3a");
                                break MISSING_BLOCK_LABEL_178;
                            }
                            ((StringBuilder) (obj)).append("\\3c");
                            break MISSING_BLOCK_LABEL_178;
                        }
                        ((StringBuilder) (obj)).append("\\3e");
                        break MISSING_BLOCK_LABEL_178;
                    }
                    ((StringBuilder) (obj)).append("\\40");
                    break MISSING_BLOCK_LABEL_178;
                }
                ((StringBuilder) (obj)).append("\\5c");
                break MISSING_BLOCK_LABEL_178;
            }
        }
        return s1;
    }

    public static String generateKey(String s, String s1)
    {
        return (new StringBuilder(String.valueOf(s))).append('\t').append(s1).toString();
    }

    public static boolean isBareJid(String s)
    {
        return parseLocalpart(s).length() > 0 && parseDomain(s).length() > 0 && parseResource(s).length() == 0;
    }

    public static boolean isFullJID(String s)
    {
        return parseLocalpart(s).length() > 0 && parseDomain(s).length() > 0 && parseResource(s).length() > 0;
    }

    public static String parseBareAddress(String s)
    {
        return parseBareJid(s);
    }

    public static String parseBareJid(String s)
    {
        int i = s.indexOf('/');
        if (i < 0)
        {
            return s;
        }
        if (i == 0)
        {
            return "";
        } else
        {
            return s.substring(0, i);
        }
    }

    public static String parseDomain(String s)
    {
        int i = s.indexOf('@');
        if (i + 1 > s.length())
        {
            return "";
        }
        int j = s.indexOf('/');
        if (j > 0)
        {
            if (j > i)
            {
                return s.substring(i + 1, j);
            } else
            {
                return s.substring(0, j);
            }
        } else
        {
            return s.substring(i + 1);
        }
    }

    public static String parseLocalpart(String s)
    {
        int i = s.indexOf('@');
        if (i <= 0)
        {
            return "";
        }
        int j = s.indexOf('/');
        if (j >= 0 && j < i)
        {
            return "";
        } else
        {
            return s.substring(0, i);
        }
    }

    public static String parseResource(String s)
    {
        int i = s.indexOf('/');
        if (i + 1 > s.length() || i < 0)
        {
            return "";
        } else
        {
            return s.substring(i + 1);
        }
    }

    public static String unescapeLocalpart(String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        return ((String) (obj));
_L2:
        Object obj1;
        obj1 = (String)LOCALPART_UNESCAPE_CACHE.get(s);
        obj = obj1;
        if (obj1 != null) goto _L4; else goto _L3
_L3:
        int i;
        int j;
        obj = s.toCharArray();
        obj1 = new StringBuilder(obj.length);
        i = 0;
        j = obj.length;
_L11:
        char c;
        if (i >= j)
        {
            obj = ((StringBuilder) (obj1)).toString();
            LOCALPART_UNESCAPE_CACHE.put(s, obj);
            return ((String) (obj));
        }
        c = s.charAt(i);
        if (c != '\\' || i + 2 >= j) goto _L6; else goto _L5
_L5:
        char c1;
        char c2;
        c1 = obj[i + 1];
        c2 = obj[i + 2];
        c1;
        JVM INSTR tableswitch 50 53: default 140
    //                   50 155
    //                   51 291
    //                   52 379
    //                   53 402;
           goto _L6 _L7 _L8 _L9 _L10
_L6:
        ((StringBuilder) (obj1)).append(c);
_L17:
        i++;
          goto _L11
_L7:
        c2;
        JVM INSTR lookupswitch 5: default 208
    //                   48: 211
    //                   50: 227
    //                   54: 243
    //                   55: 259
    //                   102: 275;
           goto _L6 _L12 _L13 _L14 _L15 _L16
_L12:
        ((StringBuilder) (obj1)).append(' ');
        i += 2;
          goto _L17
_L13:
        ((StringBuilder) (obj1)).append('"');
        i += 2;
          goto _L17
_L14:
        ((StringBuilder) (obj1)).append('&');
        i += 2;
          goto _L17
_L15:
        ((StringBuilder) (obj1)).append('\'');
        i += 2;
          goto _L17
_L16:
        ((StringBuilder) (obj1)).append('/');
        i += 2;
          goto _L17
_L8:
        c2;
        JVM INSTR tableswitch 97 101: default 328
    //                   97 331
    //                   98 328
    //                   99 347
    //                   100 328
    //                   101 363;
           goto _L6 _L18 _L6 _L19 _L6 _L20
_L18:
        ((StringBuilder) (obj1)).append(':');
        i += 2;
          goto _L17
_L19:
        ((StringBuilder) (obj1)).append('<');
        i += 2;
          goto _L17
_L20:
        ((StringBuilder) (obj1)).append('>');
        i += 2;
          goto _L17
_L9:
        if (c2 != '0') goto _L6; else goto _L21
_L21:
        ((StringBuilder) (obj1)).append("@");
        i += 2;
          goto _L17
_L10:
        if (c2 != 'c') goto _L6; else goto _L22
_L22:
        ((StringBuilder) (obj1)).append("\\");
        i += 2;
          goto _L17
    }

}
