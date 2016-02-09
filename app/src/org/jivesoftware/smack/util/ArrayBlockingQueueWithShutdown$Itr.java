// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

// Referenced classes of package org.jivesoftware.smack.util:
//            ArrayBlockingQueueWithShutdown

private class ss._cls100
    implements Iterator
{

    private int lastRet;
    private int nextIndex;
    private Object nextItem;
    final ArrayBlockingQueueWithShutdown this$0;

    private void checkNext()
    {
        if (nextIndex == ArrayBlockingQueueWithShutdown.access$300(ArrayBlockingQueueWithShutdown.this))
        {
            nextIndex = -1;
            nextItem = null;
        } else
        {
            nextItem = ArrayBlockingQueueWithShutdown.access$200(ArrayBlockingQueueWithShutdown.this)[nextIndex];
            if (nextItem == null)
            {
                nextIndex = -1;
                return;
            }
        }
    }

    public boolean hasNext()
    {
        return nextIndex >= 0;
    }

    public Object next()
    {
        ArrayBlockingQueueWithShutdown.access$400(ArrayBlockingQueueWithShutdown.this).lock();
        if (nextIndex < 0)
        {
            throw new NoSuchElementException();
        }
        break MISSING_BLOCK_LABEL_38;
        Exception exception;
        exception;
        ArrayBlockingQueueWithShutdown.access$400(ArrayBlockingQueueWithShutdown.this).unlock();
        throw exception;
        Object obj;
        lastRet = nextIndex;
        obj = nextItem;
        nextIndex = ArrayBlockingQueueWithShutdown.access$500(ArrayBlockingQueueWithShutdown.this, nextIndex);
        checkNext();
        ArrayBlockingQueueWithShutdown.access$400(ArrayBlockingQueueWithShutdown.this).unlock();
        return obj;
    }

    public void remove()
    {
        ArrayBlockingQueueWithShutdown.access$400(ArrayBlockingQueueWithShutdown.this).lock();
        int j = lastRet;
        if (j >= 0)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        throw new IllegalStateException();
        Exception exception;
        exception;
        ArrayBlockingQueueWithShutdown.access$400(ArrayBlockingQueueWithShutdown.this).unlock();
        throw exception;
        int k;
        lastRet = -1;
        k = ArrayBlockingQueueWithShutdown.access$100(ArrayBlockingQueueWithShutdown.this);
        ArrayBlockingQueueWithShutdown.access$600(ArrayBlockingQueueWithShutdown.this, j);
        int i;
        i = j;
        if (j != k)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        i = ArrayBlockingQueueWithShutdown.access$100(ArrayBlockingQueueWithShutdown.this);
        nextIndex = i;
        checkNext();
        ArrayBlockingQueueWithShutdown.access$400(ArrayBlockingQueueWithShutdown.this).unlock();
        return;
    }

    ()
    {
        this$0 = ArrayBlockingQueueWithShutdown.this;
        super();
        lastRet = -1;
        if (ArrayBlockingQueueWithShutdown.access$000(ArrayBlockingQueueWithShutdown.this) == 0)
        {
            nextIndex = -1;
            return;
        } else
        {
            nextIndex = ArrayBlockingQueueWithShutdown.access$100(ArrayBlockingQueueWithShutdown.this);
            nextItem = ArrayBlockingQueueWithShutdown.access$200(ArrayBlockingQueueWithShutdown.this)[ArrayBlockingQueueWithShutdown.access$100(ArrayBlockingQueueWithShutdown.this)];
            return;
        }
    }
}
