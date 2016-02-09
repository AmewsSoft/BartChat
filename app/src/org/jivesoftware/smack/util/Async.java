// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;


public class Async
{

    public Async()
    {
    }

    public static Thread daemonThreadFrom(Runnable runnable)
    {
        runnable = new Thread(runnable);
        runnable.setDaemon(true);
        return runnable;
    }

    public static Thread go(Runnable runnable)
    {
        runnable = daemonThreadFrom(runnable);
        runnable.start();
        return runnable;
    }

    public static Thread go(Runnable runnable, String s)
    {
        runnable = daemonThreadFrom(runnable);
        runnable.setName(s);
        runnable.start();
        return runnable;
    }
}
