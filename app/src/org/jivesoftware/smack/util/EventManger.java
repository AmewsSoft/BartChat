// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventManger
{
    public static interface Callback
    {

        public abstract void action()
            throws Exception;
    }

    private static class Reference
    {

        volatile Object eventResult;

        private Reference()
        {
        }

    }


    private final Map events = new ConcurrentHashMap();

    public EventManger()
    {
    }

    public Object performActionAndWaitForEvent(Object obj, long l, Callback callback)
        throws InterruptedException, Exception
    {
        Reference reference;
        reference = new Reference();
        events.put(obj, reference);
        reference;
        JVM INSTR monitorenter ;
        callback.action();
        reference.wait(l);
        reference;
        JVM INSTR monitorexit ;
        callback = ((Callback) (reference.eventResult));
        events.remove(obj);
        return callback;
        callback;
        reference;
        JVM INSTR monitorexit ;
        throw callback;
        callback;
        events.remove(obj);
        throw callback;
    }

    public boolean signalEvent(Object obj, Object obj1)
    {
        obj = (Reference)events.get(obj);
        if (obj == null)
        {
            return false;
        }
        obj.eventResult = obj1;
        obj;
        JVM INSTR monitorenter ;
        obj.notifyAll();
        obj;
        JVM INSTR monitorexit ;
        return true;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }
}
