// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.dns;


// Referenced classes of package org.jivesoftware.smack.util.dns:
//            HostAddress

public class SRVRecord extends HostAddress
    implements Comparable
{

    private int priority;
    private int weight;

    public SRVRecord(String s, int i, int j, int k)
    {
        super(s, i);
        if (k < 0 || k > 65535)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("DNS SRV records weight must be a 16-bit unsiged integer (i.e. between 0-65535. Weight was: ").append(k).toString());
        }
        if (j < 0 || j > 65535)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("DNS SRV records priority must be a 16-bit unsiged integer (i.e. between 0-65535. Priority was: ").append(j).toString());
        } else
        {
            priority = j;
            weight = k;
            return;
        }
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((SRVRecord)obj);
    }

    public int compareTo(SRVRecord srvrecord)
    {
        int j = srvrecord.priority - priority;
        int i = j;
        if (j == 0)
        {
            i = weight - srvrecord.weight;
        }
        return i;
    }

    public int getPriority()
    {
        return priority;
    }

    public int getWeight()
    {
        return weight;
    }

    public String toString()
    {
        return (new StringBuilder()).append(super.toString()).append(" prio:").append(priority).append(":w:").append(weight).toString();
    }
}
