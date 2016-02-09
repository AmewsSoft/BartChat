// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.tcp;

import java.util.concurrent.atomic.AtomicBoolean;

public class BundleAndDefer
{

    private final AtomicBoolean isStopped;

    BundleAndDefer(AtomicBoolean atomicboolean)
    {
        isStopped = atomicboolean;
    }

    public void stopCurrentBundleAndDefer()
    {
label0:
        {
            synchronized (isStopped)
            {
                if (!isStopped.get())
                {
                    break label0;
                }
            }
            return;
        }
        isStopped.set(true);
        isStopped.notify();
        atomicboolean;
        JVM INSTR monitorexit ;
        return;
        exception;
        atomicboolean;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
