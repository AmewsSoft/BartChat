// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.stringprep.simple;

import java.util.Locale;
import org.jxmpp.stringprep.XmppStringPrepUtil;
import org.jxmpp.stringprep.XmppStringprep;
import org.jxmpp.stringprep.XmppStringprepException;

public class SimpleXmppStringprep
    implements XmppStringprep
{

    private static final char LOCALPART_FURTHER_EXCLUDED_CHARACTERS[] = {
        '"', '&', '\'', '/', ',', '<', '>', '@', ' '
    };
    private static SimpleXmppStringprep instance;

    private SimpleXmppStringprep()
    {
    }

    public static SimpleXmppStringprep getInstance()
    {
        if (instance == null)
        {
            instance = new SimpleXmppStringprep();
        }
        return instance;
    }

    public static void setup()
    {
        XmppStringPrepUtil.setXmppStringprep(getInstance());
    }

    private static String simpleStringprep(String s)
    {
        return s.toLowerCase(Locale.US);
    }

    public String domainprep(String s)
        throws XmppStringprepException
    {
        return simpleStringprep(s);
    }

    public String localprep(String s)
        throws XmppStringprepException
    {
        char ac[];
        int i;
        int k;
        s = simpleStringprep(s);
        ac = s.toCharArray();
        k = ac.length;
        i = 0;
_L2:
        if (i >= k)
        {
            return s;
        }
        char c1 = ac[i];
        char ac1[] = LOCALPART_FURTHER_EXCLUDED_CHARACTERS;
        int l = ac1.length;
        int j = 0;
        do
        {
label0:
            {
                if (j < l)
                {
                    break label0;
                }
                i++;
            }
            if (true)
            {
                continue;
            }
            char c = ac1[j];
            if (c1 == c)
            {
                throw new XmppStringprepException(s, (new StringBuilder("Localpart must not contain '")).append(c).append("'").toString());
            }
            j++;
        } while (true);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public String resourceprep(String s)
        throws XmppStringprepException
    {
        return s;
    }

}
