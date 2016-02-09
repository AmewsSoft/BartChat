// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LazyStringBuilder
    implements Appendable, CharSequence
{

    static final boolean $assertionsDisabled;
    private String cache;
    private final List list = new ArrayList(20);

    public LazyStringBuilder()
    {
    }

    private void invalidateCache()
    {
        cache = null;
    }

    public volatile Appendable append(char c)
        throws IOException
    {
        return append(c);
    }

    public volatile Appendable append(CharSequence charsequence)
        throws IOException
    {
        return append(charsequence);
    }

    public volatile Appendable append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public LazyStringBuilder append(char c)
    {
        list.add(Character.toString(c));
        invalidateCache();
        return this;
    }

    public LazyStringBuilder append(CharSequence charsequence)
    {
        if (!$assertionsDisabled && charsequence == null)
        {
            throw new AssertionError();
        } else
        {
            list.add(charsequence);
            invalidateCache();
            return this;
        }
    }

    public LazyStringBuilder append(CharSequence charsequence, int i, int j)
    {
        charsequence = charsequence.subSequence(i, j);
        list.add(charsequence);
        invalidateCache();
        return this;
    }

    public LazyStringBuilder append(LazyStringBuilder lazystringbuilder)
    {
        list.addAll(lazystringbuilder.list);
        invalidateCache();
        return this;
    }

    public char charAt(int i)
    {
        if (cache != null)
        {
            return cache.charAt(i);
        }
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            CharSequence charsequence = (CharSequence)iterator.next();
            if (i < charsequence.length())
            {
                return charsequence.charAt(i);
            }
            i -= charsequence.length();
        }

        throw new IndexOutOfBoundsException();
    }

    public int length()
    {
        if (cache == null) goto _L2; else goto _L1
_L1:
        int j = cache.length();
_L4:
        return j;
_L2:
        int i = 0;
        Iterator iterator = list.iterator();
        do
        {
            j = i;
            if (!iterator.hasNext())
            {
                continue;
            }
            i += ((CharSequence)iterator.next()).length();
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public CharSequence subSequence(int i, int j)
    {
        return toString().subSequence(i, j);
    }

    public String toString()
    {
        if (cache == null)
        {
            StringBuilder stringbuilder = new StringBuilder(length());
            for (Iterator iterator = list.iterator(); iterator.hasNext(); stringbuilder.append((CharSequence)iterator.next())) { }
            cache = stringbuilder.toString();
        }
        return cache;
    }

    static 
    {
        boolean flag;
        if (!org/jivesoftware/smack/util/LazyStringBuilder.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }
}
