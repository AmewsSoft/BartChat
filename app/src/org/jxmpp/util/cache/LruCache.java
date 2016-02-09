// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.util.cache;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package org.jxmpp.util.cache:
//            Cache

public class LruCache extends LinkedHashMap
    implements Cache
{

    private static final int DEFAULT_INITIAL_SIZE = 50;
    private static final long serialVersionUID = 0xbae09c3cff79a8d1L;
    private final AtomicLong cacheHits = new AtomicLong();
    private final AtomicLong cacheMisses = new AtomicLong();
    private int maxCacheSize;

    public LruCache(int i)
    {
        int j = 50;
        if (i < 50)
        {
            j = i;
        }
        super(j, 0.75F, true);
        if (i == 0)
        {
            throw new IllegalArgumentException("Max cache size cannot be 0.");
        } else
        {
            maxCacheSize = i;
            return;
        }
    }

    public final void clear()
    {
        this;
        JVM INSTR monitorenter ;
        super.clear();
        this;
        JVM INSTR monitorexit ;
        cacheHits.set(0L);
        cacheMisses.set(0L);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final boolean containsKey(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = super.containsKey(obj);
        this;
        JVM INSTR monitorexit ;
        return flag;
        obj;
        throw obj;
    }

    public final boolean containsValue(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = super.containsValue(obj);
        this;
        JVM INSTR monitorexit ;
        return flag;
        obj;
        throw obj;
    }

    public final Set entrySet()
    {
        this;
        JVM INSTR monitorenter ;
        Set set = super.entrySet();
        this;
        JVM INSTR monitorexit ;
        return set;
        Exception exception;
        exception;
        throw exception;
    }

    public final Object get(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        obj = super.get(obj);
        this;
        JVM INSTR monitorexit ;
        if (obj == null)
        {
            cacheMisses.incrementAndGet();
            return null;
        } else
        {
            cacheHits.incrementAndGet();
            return obj;
        }
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public final long getCacheHits()
    {
        return cacheHits.longValue();
    }

    public final long getCacheMisses()
    {
        return cacheMisses.longValue();
    }

    public final int getMaxCacheSize()
    {
        return maxCacheSize;
    }

    public final boolean isEmpty()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = super.isEmpty();
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public final Set keySet()
    {
        this;
        JVM INSTR monitorenter ;
        Set set = super.keySet();
        this;
        JVM INSTR monitorexit ;
        return set;
        Exception exception;
        exception;
        throw exception;
    }

    public final Object put(Object obj, Object obj1)
    {
        this;
        JVM INSTR monitorenter ;
        obj = super.put(obj, obj1);
        this;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        throw obj;
    }

    public final void putAll(Map map)
    {
        this;
        JVM INSTR monitorenter ;
        super.putAll(map);
        this;
        JVM INSTR monitorexit ;
        return;
        map;
        throw map;
    }

    public final Object remove(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        obj = super.remove(obj);
        this;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        throw obj;
    }

    protected final boolean removeEldestEntry(java.util.Map.Entry entry)
    {
        return size() > maxCacheSize;
    }

    public final void setMaxCacheSize(int i)
    {
        maxCacheSize = i;
    }

    public final int size()
    {
        this;
        JVM INSTR monitorenter ;
        int i = super.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final Collection values()
    {
        this;
        JVM INSTR monitorenter ;
        Collection collection = super.values();
        this;
        JVM INSTR monitorexit ;
        return collection;
        Exception exception;
        exception;
        throw exception;
    }
}
