// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.util.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package org.jxmpp.util.cache:
//            Cache, LruCache

public class ExpirationCache
    implements Cache, Map
{
    private static class EntryImpl
        implements java.util.Map.Entry
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

        public EntryImpl(Object obj, Object obj1)
        {
            key = obj;
            value = obj1;
        }
    }

    private static class ExpireElement
    {

        public final Object element;
        public final long expirationTimestamp;

        public boolean equals(Object obj)
        {
            if (!(obj instanceof ExpireElement))
            {
                return false;
            } else
            {
                obj = (ExpireElement)obj;
                return element.equals(((ExpireElement) (obj)).element);
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

        public ExpireElement(Object obj, long l)
        {
            element = obj;
            expirationTimestamp = System.currentTimeMillis() + l;
        }
    }


    private final LruCache cache;
    private long defaultExpirationTime;

    public ExpirationCache(int i, long l)
    {
        cache = new LruCache(i);
        setDefaultExpirationTime(l);
    }

    public void clear()
    {
        cache.clear();
    }

    public boolean containsKey(Object obj)
    {
        return cache.containsKey(obj);
    }

    public boolean containsValue(Object obj)
    {
        return cache.containsValue(obj);
    }

    public Set entrySet()
    {
        HashSet hashset = new HashSet();
        Iterator iterator = cache.entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                return hashset;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            hashset.add(new EntryImpl(entry.getKey(), ((ExpireElement)entry.getValue()).element));
        } while (true);
    }

    public Object get(Object obj)
    {
        ExpireElement expireelement = (ExpireElement)cache.get(obj);
        if (expireelement == null)
        {
            return null;
        }
        if (expireelement.isExpired())
        {
            remove(obj);
            return null;
        } else
        {
            return expireelement.element;
        }
    }

    public int getMaxCacheSize()
    {
        return cache.getMaxCacheSize();
    }

    public boolean isEmpty()
    {
        return cache.isEmpty();
    }

    public Set keySet()
    {
        return cache.keySet();
    }

    public Object put(Object obj, Object obj1)
    {
        return put(obj, obj1, defaultExpirationTime);
    }

    public Object put(Object obj, Object obj1, long l)
    {
        obj = (ExpireElement)cache.put(obj, new ExpireElement(obj1, l));
        if (obj == null)
        {
            return null;
        } else
        {
            return ((ExpireElement) (obj)).element;
        }
    }

    public void putAll(Map map)
    {
        map = map.entrySet().iterator();
        do
        {
            if (!map.hasNext())
            {
                return;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)map.next();
            put(entry.getKey(), entry.getValue());
        } while (true);
    }

    public Object remove(Object obj)
    {
        obj = (ExpireElement)cache.remove(obj);
        if (obj == null)
        {
            return null;
        } else
        {
            return ((ExpireElement) (obj)).element;
        }
    }

    public void setDefaultExpirationTime(long l)
    {
        if (l <= 0L)
        {
            throw new IllegalArgumentException();
        } else
        {
            defaultExpirationTime = l;
            return;
        }
    }

    public void setMaxCacheSize(int i)
    {
        cache.setMaxCacheSize(i);
    }

    public int size()
    {
        return cache.size();
    }

    public Collection values()
    {
        HashSet hashset = new HashSet();
        Iterator iterator = cache.values().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                return hashset;
            }
            hashset.add(((ExpireElement)iterator.next()).element);
        } while (true);
    }
}
