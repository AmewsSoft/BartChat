// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.util.concurrent.ThreadFactory;

public final class SmackExecutorThreadFactory
    implements ThreadFactory
{

    private final int connectionCounterValue;
    private int count;
    private final String name;

    public SmackExecutorThreadFactory(int i, String s)
    {
        count = 0;
        connectionCounterValue = i;
        name = s;
    }

    public Thread newThread(Runnable runnable)
    {
        runnable = new Thread(runnable);
        StringBuilder stringbuilder = (new StringBuilder()).append("Smack-").append(name).append(' ');
        int i = count;
        count = i + 1;
        runnable.setName(stringbuilder.append(i).append(" (").append(connectionCounterValue).append(")").toString());
        runnable.setDaemon(true);
        return runnable;
    }
}
