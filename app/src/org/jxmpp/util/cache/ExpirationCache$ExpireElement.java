// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.util.cache;


// Referenced classes of package org.jxmpp.util.cache:
//            ExpirationCache

private static class expirationTimestamp
{

    public final Object element;
    public final long expirationTimestamp;

    public boolean equals(Object obj)
    {
        if (!(obj instanceof expirationTimestamp))
        {
            return false;
        } else
        {
            obj = (expirationTimestamp)obj;
            return element.equals(((element) (obj)).element);
        }
    }

    public int hashCode()
    {
        return element.hashCode();
    }

    public boolean isExpired()
    {
        return System.currentTimeMillis() > expirationTimestamp;
    }

    public (Object obj, long l)
    {
        element = obj;
        expirationTimestamp = System.currentTimeMillis() + l;
    }
}
