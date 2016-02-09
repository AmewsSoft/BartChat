// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.util.dns.DNSResolver;
import org.jivesoftware.smack.util.dns.HostAddress;
import org.jivesoftware.smack.util.dns.SRVRecord;

// Referenced classes of package org.jivesoftware.smack.util:
//            StringTransformer

public class DNSUtil
{
    private static final class DomainType extends Enum
    {

        private static final DomainType $VALUES[];
        public static final DomainType Client;
        public static final DomainType Server;

        public static DomainType valueOf(String s)
        {
            return (DomainType)Enum.valueOf(org/jivesoftware/smack/util/DNSUtil$DomainType, s);
        }

        public static DomainType[] values()
        {
            return (DomainType[])$VALUES.clone();
        }

        static 
        {
            Server = new DomainType("Server", 0);
            Client = new DomainType("Client", 1);
            $VALUES = (new DomainType[] {
                Server, Client
            });
        }

        private DomainType(String s, int i)
        {
            super(s, i);
        }
    }


    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/util/DNSUtil.getName());
    private static DNSResolver dnsResolver = null;
    private static StringTransformer idnaTransformer = new StringTransformer() {

        public String transform(String s)
        {
            return s;
        }

    };

    public DNSUtil()
    {
    }

    private static int bisect(int ai[], double d)
    {
        int j = 0;
        int k = ai.length;
        int i = 0;
        do
        {
            if (i >= k || d < (double)ai[i])
            {
                return j;
            }
            j++;
            i++;
        } while (true);
    }

    public static DNSResolver getDNSResolver()
    {
        return dnsResolver;
    }

    private static List resolveDomain(String s, DomainType domaintype, List list)
    {
        ArrayList arraylist = new ArrayList();
        static class _cls2
        {

            static final int $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType = new int[DomainType.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType[DomainType.Server.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType[DomainType.Client.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls2..SwitchMap.org.jivesoftware.smack.util.DNSUtil.DomainType[domaintype.ordinal()];
        JVM INSTR tableswitch 1 2: default 40
    //                   1 48
    //                   2 175;
           goto _L1 _L2 _L3
_L1:
        throw new AssertionError();
_L2:
        domaintype = (new StringBuilder()).append("_xmpp-server._tcp.").append(s).toString();
_L6:
        String s1;
        List list1;
        try
        {
            list1 = dnsResolver.lookupSRVRecords(domaintype);
            if (!LOGGER.isLoggable(Level.FINE))
            {
                break MISSING_BLOCK_LABEL_205;
            }
            s1 = (new StringBuilder()).append("Resolved SRV RR for ").append(domaintype).append(":").toString();
            for (Iterator iterator = list1.iterator(); iterator.hasNext();)
            {
                SRVRecord srvrecord = (SRVRecord)iterator.next();
                s1 = (new StringBuilder()).append(s1).append(" ").append(srvrecord).toString();
            }

            break; /* Loop/switch isn't completed */
        }
        catch (Exception exception)
        {
            LOGGER.log(Level.WARNING, (new StringBuilder()).append("Exception while resovling SRV records for ").append(s).append(". Consider adding '_xmpp-(server|client)._tcp' DNS SRV Records").toString(), exception);
            if (list != null)
            {
                domaintype = new HostAddress(domaintype);
                domaintype.setException(exception);
                list.add(domaintype);
            }
        }
          goto _L4
_L3:
        domaintype = (new StringBuilder()).append("_xmpp-client._tcp.").append(s).toString();
        if (true) goto _L6; else goto _L5
_L5:
        LOGGER.fine(s1);
        arraylist.addAll(sortSRVRecords(list1));
_L8:
        arraylist.add(new HostAddress(s));
        return arraylist;
_L4:
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static List resolveXMPPDomain(String s, List list)
    {
        s = idnaTransformer.transform(s);
        if (dnsResolver == null)
        {
            LOGGER.warning("No DNS Resolver active in Smack, will be unable to perform DNS SRV lookups");
            list = new ArrayList(1);
            list.add(new HostAddress(s, 5222));
            return list;
        } else
        {
            return resolveDomain(s, DomainType.Client, list);
        }
    }

    public static List resolveXMPPServerDomain(String s, List list)
    {
        s = idnaTransformer.transform(s);
        if (dnsResolver == null)
        {
            LOGGER.warning("No DNS Resolver active in Smack, will be unable to perform DNS SRV lookups");
            list = new ArrayList(1);
            list.add(new HostAddress(s, 5269));
            return list;
        } else
        {
            return resolveDomain(s, DomainType.Server, list);
        }
    }

    public static void setDNSResolver(DNSResolver dnsresolver)
    {
        dnsResolver = dnsresolver;
    }

    public static void setIdnaTransformer(StringTransformer stringtransformer)
    {
        if (stringtransformer == null)
        {
            throw new NullPointerException();
        } else
        {
            idnaTransformer = stringtransformer;
            return;
        }
    }

    private static List sortSRVRecords(List list)
    {
        if (list.size() != 1 || !((SRVRecord)list.get(0)).getFQDN().equals(".")) goto _L2; else goto _L1
_L1:
        list = Collections.emptyList();
_L4:
        return list;
_L2:
        ArrayList arraylist;
        Iterator iterator;
        TreeMap treemap;
        Collections.sort(list);
        treemap = new TreeMap();
        Object obj;
        SRVRecord srvrecord;
        for (Iterator iterator1 = list.iterator(); iterator1.hasNext(); ((List) (obj)).add(srvrecord))
        {
            srvrecord = (SRVRecord)iterator1.next();
            Integer integer = Integer.valueOf(srvrecord.getPriority());
            List list1 = (List)treemap.get(integer);
            obj = list1;
            if (list1 == null)
            {
                obj = new LinkedList();
                treemap.put(integer, obj);
            }
        }

        arraylist = new ArrayList(list.size());
        iterator = treemap.keySet().iterator();
_L6:
        list = arraylist;
        if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        list = (List)treemap.get((Integer)iterator.next());
_L7:
        int l = list.size();
        if (l <= 0) goto _L6; else goto _L5
_L5:
        int ai[] = new int[list.size()];
        int k = 0;
        int j = 0;
        int i = 1;
        Iterator iterator2 = list.iterator();
        do
        {
            if (!iterator2.hasNext())
            {
                break;
            }
            if (((SRVRecord)iterator2.next()).getWeight() > 0)
            {
                i = 0;
            }
        } while (true);
        for (Iterator iterator3 = list.iterator(); iterator3.hasNext();)
        {
            k += ((SRVRecord)iterator3.next()).getWeight() + i;
            ai[j] = k;
            j++;
        }

        if (k == 0)
        {
            i = (int)(Math.random() * (double)l);
        } else
        {
            i = bisect(ai, Math.random() * (double)k);
        }
        arraylist.add((SRVRecord)list.remove(i));
          goto _L7
    }

}
