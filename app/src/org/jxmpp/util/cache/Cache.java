// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.util.cache;


public interface Cache
{

    public abstract Object get(Object obj);

    public abstract int getMaxCacheSize();

    public abstract Object put(Object obj, Object obj1);

    public abstract void setMaxCacheSize(int i);
}
