// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.stringprep;

import org.jxmpp.stringprep.simple.SimpleXmppStringprep;
import org.jxmpp.util.cache.Cache;
import org.jxmpp.util.cache.LruCache;

// Referenced classes of package org.jxmpp.stringprep:
//            XmppStringprepException, XmppStringprep

public class XmppStringPrepUtil
{

    private static final Cache DOMAINPREP_CACHE = new LruCache(100);
    private static final Cache NODEPREP_CACHE = new LruCache(100);
    private static final Cache RESOURCEPREP_CACHE = new LruCache(100);
    private static XmppStringprep xmppStringprep;

    public XmppStringPrepUtil()
    {
    }

    public static String domainprep(String s)
        throws XmppStringprepException
    {
        String s1;
        if (xmppStringprep == null)
        {
            s1 = s;
        } else
        {
            throwIfEmptyString(s);
            String s3 = (String)DOMAINPREP_CACHE.get(s);
            s1 = s3;
            if (s3 == null)
            {
                String s2 = xmppStringprep.domainprep(s);
                DOMAINPREP_CACHE.put(s, s2);
                return s2;
            }
        }
        return s1;
    }

    public static String localprep(String s)
        throws XmppStringprepException
    {
        String s1;
        if (xmppStringprep == null)
        {
            s1 = s;
        } else
        {
            throwIfEmptyString(s);
            String s3 = (String)NODEPREP_CACHE.get(s);
            s1 = s3;
            if (s3 == null)
            {
                String s2 = xmppStringprep.localprep(s);
                NODEPREP_CACHE.put(s, s2);
                return s2;
            }
        }
        return s1;
    }

    public static String resourceprep(String s)
        throws XmppStringprepException
    {
        String s1;
        if (xmppStringprep == null)
        {
            s1 = s;
        } else
        {
            throwIfEmptyString(s);
            String s3 = (String)RESOURCEPREP_CACHE.get(s);
            s1 = s3;
            if (s3 == null)
            {
                String s2 = xmppStringprep.resourceprep(s);
                RESOURCEPREP_CACHE.put(s, s2);
                return s2;
            }
        }
        return s1;
    }

    public static void setMaxCacheSizes(int i)
    {
        NODEPREP_CACHE.setMaxCacheSize(i);
        DOMAINPREP_CACHE.setMaxCacheSize(i);
        RESOURCEPREP_CACHE.setMaxCacheSize(i);
    }

    public static void setXmppStringprep(XmppStringprep xmppstringprep)
    {
        xmppStringprep = xmppstringprep;
    }

    private static void throwIfEmptyString(String s)
        throws XmppStringprepException
    {
        if (s.length() == 0)
        {
            throw new XmppStringprepException(s, "Argument can't be the empty string");
        } else
        {
            return;
        }
    }

    static 
    {
        SimpleXmppStringprep.setup();
    }
}
