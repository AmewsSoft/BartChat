// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.dns.dnsjava;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.initializer.SmackInitializer;
import org.jivesoftware.smack.util.DNSUtil;
import org.jivesoftware.smack.util.dns.DNSResolver;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Name;
import org.xbill.DNS.SRVRecord;
import org.xbill.DNS.TextParseException;

public class DNSJavaResolver
    implements SmackInitializer, DNSResolver
{

    private static DNSJavaResolver instance = new DNSJavaResolver();

    public DNSJavaResolver()
    {
    }

    public static DNSResolver getInstance()
    {
        return instance;
    }

    public static void setup()
    {
        DNSUtil.setDNSResolver(getInstance());
    }

    public List initialize()
    {
        setup();
        return null;
    }

    public List lookupSRVRecords(String s)
        throws TextParseException
    {
        ArrayList arraylist = new ArrayList();
        s = (new Lookup(s, 33)).run();
        if (s != null)
        {
            int j = s.length;
            int i = 0;
            while (i < j) 
            {
                SRVRecord srvrecord = (SRVRecord)s[i];
                if (srvrecord != null && srvrecord.getTarget() != null)
                {
                    arraylist.add(new org.jivesoftware.smack.util.dns.SRVRecord(srvrecord.getTarget().toString(), srvrecord.getPort(), srvrecord.getPriority(), srvrecord.getWeight()));
                }
                i++;
            }
        }
        return arraylist;
    }

}
