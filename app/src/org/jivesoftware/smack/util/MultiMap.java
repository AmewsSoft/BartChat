// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiMap
{
    private static class SimpleMapEntry
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

        private SimpleMapEntry(Object obj, Object obj1)
        {
            key = obj;
            value = obj1;
        }

    }


    static final boolean $assertionsDisabled;
    public static final int DEFAULT_MAP_SIZE = 6;
    private static final int ENTRY_SET_SIZE = 3;
    private final Map map;

    public MultiMap()
    {
        this(6);
    }

    public MultiMap(int i)
    {
        map = new LinkedHashMap(i);
    }

    public void clear()
    {
        map.clear();
    }

    public boolean containsKey(Object obj)
    {
        return map.containsKey(obj);
    }

    public boolean containsValue(Object obj)
    {
        for (Iterator iterator = map.values().iterator(); iterator.hasNext();)
        {
            if (((Set)iterator.next()).contains(obj))
            {
                return true;
            }
        }

        return false;
    }

    public Set entrySet()
    {
        LinkedHashSet linkedhashset = new LinkedHashSet(size());
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            Object obj1 = (java.util.Map.Entry)iterator.next();
            Object obj = ((java.util.Map.Entry) (obj1)).getKey();
            obj1 = ((Set)((java.util.Map.Entry) (obj1)).getValue()).iterator();
            while (((Iterator) (obj1)).hasNext()) 
            {
                linkedhashset.add(new SimpleMapEntry(obj, ((Iterator) (obj1)).next()));
            }
        }

        return linkedhashset;
    }

    public Set getAll(Object obj)
    {
        Set set = (Set)map.get(obj);
        obj = set;
        if (set == null)
        {
            obj = Collections.emptySet();
        }
        return ((Set) (obj));
    }

    public Object getFirst(Object obj)
    {
        obj = getAll(obj);
        if (((Set) (obj)).isEmpty())
        {
            return null;
        } else
        {
            return ((Set) (obj)).iterator().next();
        }
    }

    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    public Set keySet()
    {
        return map.keySet();
    }

    public boolean put(Object obj, Object obj1)
    {
        Object obj2 = (Set)map.get(obj);
        if (obj2 == null)
        {
            obj2 = new LinkedHashSet(3);
            ((Set) (obj2)).add(obj1);
            map.put(obj, obj2);
            return false;
        } else
        {
            ((Set) (obj2)).add(obj1);
            return true;
        }
    }

    public void putAll(Map map1)
    {
        java.util.Map.Entry entry;
        for (map1 = map1.entrySet().iterator(); map1.hasNext(); put(entry.getKey(), entry.getValue()))
        {
            entry = (java.util.Map.Entry)map1.next();
        }

    }

    public Object remove(Object obj)
    {
        obj = (Set)map.remove(obj);
        if (obj == null)
        {
            return null;
        }
        if (!$assertionsDisabled && ((Set) (obj)).isEmpty())
        {
            throw new AssertionError();
        } else
        {
            return ((Set) (obj)).iterator().next();
        }
    }

    public boolean removeOne(Object obj, Object obj1)
    {
        Set set = (Set)map.get(obj);
        boolean flag;
        if (set == null)
        {
            flag = false;
        } else
        {
            boolean flag1 = set.remove(obj1);
            flag = flag1;
            if (set.isEmpty())
            {
                map.remove(obj);
                return flag1;
            }
        }
        return flag;
    }

    public int size()
    {
        int i = 0;
        for (Iterator iterator = map.values().iterator(); iterator.hasNext();)
        {
            i += ((Set)iterator.next()).size();
        }

        return i;
    }

    public List values()
    {
        ArrayList arraylist = new ArrayList(size());
        for (Iterator iterator = map.values().iterator(); iterator.hasNext(); arraylist.addAll((Set)iterator.next())) { }
        return arraylist;
    }

    static 
    {
        boolean flag;
        if (!org/jivesoftware/smack/util/MultiMap.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }
}
