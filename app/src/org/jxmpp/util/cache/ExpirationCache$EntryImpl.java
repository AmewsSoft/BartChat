// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.util.cache;


// Referenced classes of package org.jxmpp.util.cache:
//            ExpirationCache

private static class value
    implements java.util..EntryImpl
{

    private final Object key;
    private Object value;

    public Object getKey()
    {
        return key;
    }

    public Object getValue()
    {
        return value;
    }

    public Object setValue(Object obj)
    {
        Object obj1 = value;
        value = obj;
        return obj1;
    }

    public (Object obj, Object obj1)
    {
        key = obj;
        value = obj1;
    }
}
