// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.util.dns.HostAddress;

// Referenced classes of package org.jivesoftware.smack:
//            SmackException

public static class failedAddresses extends SmackException
{

    private static final long serialVersionUID = 0x17693ad0489b288cL;
    private final List failedAddresses;

    public static failedAddresses from(List list)
    {
        StringBuilder stringbuilder = new StringBuilder("The following addresses failed: ");
        for (Iterator iterator = list.iterator(); iterator.hasNext(); stringbuilder.append(", "))
        {
            stringbuilder.append(((HostAddress)iterator.next()).getErrorMessage());
        }

        stringbuilder.setLength(stringbuilder.length() - ", ".length());
        return new <init>(stringbuilder.toString(), list);
    }

    public List getFailedAddresses()
    {
        return failedAddresses;
    }

    private (String s, List list)
    {
        super(s);
        failedAddresses = list;
    }

    public failedAddresses(Throwable throwable)
    {
        super(throwable);
        failedAddresses = new ArrayList(0);
    }
}
