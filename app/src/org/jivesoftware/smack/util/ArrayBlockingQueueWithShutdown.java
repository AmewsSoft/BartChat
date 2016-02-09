// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueueWithShutdown extends AbstractQueue
    implements BlockingQueue
{
    private class Itr
        implements Iterator
    {

        private int lastRet;
        private int nextIndex;
        private Object nextItem;
        final ArrayBlockingQueueWithShutdown this$0;

        private void checkNext()
        {
            if (nextIndex == putIndex)
            {
                nextIndex = -1;
                nextItem = null;
            } else
            {
                nextItem = items[nextIndex];
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
            lock.lock();
            if (nextIndex < 0)
            {
                throw new NoSuchElementException();
            }
            break MISSING_BLOCK_LABEL_38;
            Exception exception;
            exception;
            lock.unlock();
            throw exception;
            Object obj;
            lastRet = nextIndex;
            obj = nextItem;
            nextIndex = inc(nextIndex);
            checkNext();
            lock.unlock();
            return obj;
        }

        public void remove()
        {
            lock.lock();
            int j = lastRet;
            if (j >= 0)
            {
                break MISSING_BLOCK_LABEL_40;
            }
            throw new IllegalStateException();
            Exception exception;
            exception;
            lock.unlock();
            throw exception;
            int k;
            lastRet = -1;
            k = takeIndex;
            removeAt(j);
            int i;
            i = j;
            if (j != k)
            {
                break MISSING_BLOCK_LABEL_78;
            }
            i = takeIndex;
            nextIndex = i;
            checkNext();
            lock.unlock();
            return;
        }

        Itr()
        {
            this$0 = ArrayBlockingQueueWithShutdown.this;
            super();
            lastRet = -1;
            if (count == 0)
            {
                nextIndex = -1;
                return;
            } else
            {
                nextIndex = takeIndex;
                nextItem = items[takeIndex];
                return;
            }
        }
    }


    private int count;
    private volatile boolean isShutdown;
    private final Object items[];
    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    private int putIndex;
    private int takeIndex;

    public ArrayBlockingQueueWithShutdown(int i)
    {
        this(i, false);
    }

    public ArrayBlockingQueueWithShutdown(int i, boolean flag)
    {
        isShutdown = false;
        if (i <= 0)
        {
            throw new IllegalArgumentException();
        } else
        {
            items = (Object[])new Object[i];
            lock = new ReentrantLock(flag);
            notEmpty = lock.newCondition();
            notFull = lock.newCondition();
            return;
        }
    }

    private static final void checkNotNull(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return;
        }
    }

    private final void checkNotShutdown()
        throws InterruptedException
    {
        if (isShutdown)
        {
            throw new InterruptedException();
        } else
        {
            return;
        }
    }

    private final Object extract()
    {
        Object obj = items[takeIndex];
        items[takeIndex] = null;
        takeIndex = inc(takeIndex);
        count = count - 1;
        notFull.signal();
        return obj;
    }

    private final boolean hasElements()
    {
        return !hasNoElements();
    }

    private final boolean hasNoElements()
    {
        return count == 0;
    }

    private final int inc(int i)
    {
        int j = i + 1;
        i = j;
        if (j == items.length)
        {
            i = 0;
        }
        return i;
    }

    private final void insert(Object obj)
    {
        items[putIndex] = obj;
        putIndex = inc(putIndex);
        count = count + 1;
        notEmpty.signal();
    }

    private final boolean isFull()
    {
        return count == items.length;
    }

    private final boolean isNotFull()
    {
        return !isFull();
    }

    private final void removeAt(int i)
    {
        int j = i;
        if (i == takeIndex)
        {
            items[takeIndex] = null;
            takeIndex = inc(takeIndex);
        } else
        {
            do
            {
                i = inc(j);
                if (i == putIndex)
                {
                    break;
                }
                items[j] = items[i];
                j = i;
            } while (true);
            items[j] = null;
            putIndex = j;
        }
        count = count - 1;
        notFull.signal();
    }

    public int drainTo(Collection collection)
    {
        checkNotNull(collection);
        if (collection == this)
        {
            throw new IllegalArgumentException();
        }
        lock.lock();
        int j = takeIndex;
        int i = 0;
_L2:
        if (i >= count)
        {
            break; /* Loop/switch isn't completed */
        }
        collection.add(items[j]);
        items[j] = null;
        j = inc(j);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_100;
        }
        count = 0;
        putIndex = 0;
        takeIndex = 0;
        notFull.signalAll();
        lock.unlock();
        return i;
        collection;
        lock.unlock();
        throw collection;
    }

    public int drainTo(Collection collection, int i)
    {
        checkNotNull(collection);
        if (collection == this)
        {
            throw new IllegalArgumentException();
        }
        if (i <= 0)
        {
            return 0;
        }
        lock.lock();
        int j = takeIndex;
        int k = 0;
        if (i >= count) goto _L2; else goto _L1
_L1:
        if (k >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        collection.add(items[j]);
        items[j] = null;
        j = inc(j);
        k++;
        continue; /* Loop/switch isn't completed */
_L2:
        i = count;
        if (true) goto _L1; else goto _L3
_L3:
        if (k <= 0)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        count = count - k;
        takeIndex = j;
        notFull.signalAll();
        lock.unlock();
        return k;
        collection;
        lock.unlock();
        throw collection;
    }

    public boolean isShutdown()
    {
        lock.lock();
        boolean flag = isShutdown;
        lock.unlock();
        return flag;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public Iterator iterator()
    {
        lock.lock();
        Itr itr = new Itr();
        lock.unlock();
        return itr;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public boolean offer(Object obj)
    {
        checkNotNull(obj);
        lock.lock();
        boolean flag;
        if (isFull())
        {
            break MISSING_BLOCK_LABEL_27;
        }
        flag = isShutdown;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        lock.unlock();
        return false;
        insert(obj);
        lock.unlock();
        return true;
        obj;
        lock.unlock();
        throw obj;
    }

    public boolean offer(Object obj, long l, TimeUnit timeunit)
        throws InterruptedException
    {
        checkNotNull(obj);
        l = timeunit.toNanos(l);
        lock.lockInterruptibly();
_L1:
        if (!isNotFull())
        {
            break MISSING_BLOCK_LABEL_39;
        }
        insert(obj);
        lock.unlock();
        return true;
        if (l <= 0L)
        {
            lock.unlock();
            return false;
        }
        l = notFull.awaitNanos(l);
        checkNotShutdown();
          goto _L1
        obj;
        notFull.signal();
        throw obj;
        obj;
        lock.unlock();
        throw obj;
    }

    public Object peek()
    {
        lock.lock();
        boolean flag = hasNoElements();
        if (!flag) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        lock.unlock();
        return obj;
_L2:
        obj = items[takeIndex];
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public Object poll()
    {
        lock.lock();
        boolean flag = hasNoElements();
        if (flag)
        {
            lock.unlock();
            return null;
        }
        Object obj = extract();
        lock.unlock();
        return obj;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public Object poll(long l, TimeUnit timeunit)
        throws InterruptedException
    {
        l = timeunit.toNanos(l);
        lock.lockInterruptibly();
        checkNotShutdown();
_L3:
        if (!hasElements()) goto _L2; else goto _L1
_L1:
        timeunit = ((TimeUnit) (extract()));
        lock.unlock();
        return timeunit;
_L2:
        if (l <= 0L)
        {
            lock.unlock();
            return null;
        }
        l = notEmpty.awaitNanos(l);
        checkNotShutdown();
          goto _L3
        timeunit;
        notEmpty.signal();
        throw timeunit;
        timeunit;
        lock.unlock();
        throw timeunit;
    }

    public void put(Object obj)
        throws InterruptedException
    {
        checkNotNull(obj);
        lock.lockInterruptibly();
_L1:
        boolean flag = isFull();
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        notFull.await();
        checkNotShutdown();
          goto _L1
        obj;
        notFull.signal();
        throw obj;
        obj;
        lock.unlock();
        throw obj;
        insert(obj);
        lock.unlock();
        return;
    }

    public int remainingCapacity()
    {
        lock.lock();
        int i;
        int j;
        i = items.length;
        j = count;
        lock.unlock();
        return i - j;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public void shutdown()
    {
        lock.lock();
        isShutdown = true;
        notEmpty.signalAll();
        notFull.signalAll();
        lock.unlock();
        return;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public int size()
    {
        lock.lock();
        int i = count;
        lock.unlock();
        return i;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public void start()
    {
        lock.lock();
        isShutdown = false;
        lock.unlock();
        return;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public Object take()
        throws InterruptedException
    {
        lock.lockInterruptibly();
        checkNotShutdown();
        for (; hasNoElements(); checkNotShutdown())
        {
            notEmpty.await();
        }

        break MISSING_BLOCK_LABEL_56;
        Object obj;
        obj;
        notEmpty.signal();
        throw obj;
        obj;
        lock.unlock();
        throw obj;
        Object obj1 = extract();
        lock.unlock();
        return obj1;
    }







}
