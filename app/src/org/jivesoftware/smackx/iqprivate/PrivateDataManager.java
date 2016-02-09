// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqprivate;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.iqprivate.packet.DefaultPrivateData;
import org.jivesoftware.smackx.iqprivate.packet.PrivateData;
import org.jivesoftware.smackx.iqprivate.packet.PrivateDataIQ;
import org.jivesoftware.smackx.iqprivate.provider.PrivateDataProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PrivateDataManager extends Manager
{
    public static class PrivateDataIQProvider extends IQProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public PrivateDataIQ parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            Object obj = null;
            i = 0;
label0:
            do
            {
                if (i != 0)
                {
                    break;
                }
                int j = xmlpullparser.next();
                if (j == 2)
                {
                    String s = xmlpullparser.getName();
                    obj = xmlpullparser.getNamespace();
                    PrivateDataProvider privatedataprovider = PrivateDataManager.getPrivateDataProvider(s, ((String) (obj)));
                    if (privatedataprovider != null)
                    {
                        obj = privatedataprovider.parsePrivateData(xmlpullparser);
                        continue;
                    }
                    obj = new DefaultPrivateData(s, ((String) (obj)));
                    j = 0;
                    do
                    {
                        if (j != 0)
                        {
                            continue label0;
                        }
                        int k = xmlpullparser.next();
                        if (k == 2)
                        {
                            String s1 = xmlpullparser.getName();
                            if (xmlpullparser.isEmptyElementTag())
                            {
                                ((DefaultPrivateData) (obj)).setValue(s1, "");
                            } else
                            if (xmlpullparser.next() == 4)
                            {
                                ((DefaultPrivateData) (obj)).setValue(s1, xmlpullparser.getText());
                            }
                        } else
                        if (k == 3 && xmlpullparser.getName().equals(s))
                        {
                            j = 1;
                        }
                    } while (true);
                }
                if (j == 3 && xmlpullparser.getName().equals("query"))
                {
                    i = 1;
                }
            } while (true);
            return new PrivateDataIQ(((PrivateData) (obj)));
        }

        public PrivateDataIQProvider()
        {
        }
    }


    private static final Map instances = new WeakHashMap();
    private static Map privateDataProviders = new Hashtable();

    private PrivateDataManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        instances.put(xmppconnection, this);
    }

    public static void addPrivateDataProvider(String s, String s1, PrivateDataProvider privatedataprovider)
    {
        s = getProviderKey(s, s1);
        privateDataProviders.put(s, privatedataprovider);
    }

    public static PrivateDataManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/iqprivate/PrivateDataManager;
        JVM INSTR monitorenter ;
        PrivateDataManager privatedatamanager1 = (PrivateDataManager)instances.get(xmppconnection);
        PrivateDataManager privatedatamanager;
        privatedatamanager = privatedatamanager1;
        if (privatedatamanager1 != null)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        privatedatamanager = new PrivateDataManager(xmppconnection);
        org/jivesoftware/smackx/iqprivate/PrivateDataManager;
        JVM INSTR monitorexit ;
        return privatedatamanager;
        xmppconnection;
        throw xmppconnection;
    }

    public static PrivateDataProvider getPrivateDataProvider(String s, String s1)
    {
        s = getProviderKey(s, s1);
        return (PrivateDataProvider)privateDataProviders.get(s);
    }

    private static String getProviderKey(String s, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<").append(s).append("/><").append(s1).append("/>");
        return stringbuilder.toString();
    }

    public static void removePrivateDataProvider(String s, String s1)
    {
        s = getProviderKey(s, s1);
        privateDataProviders.remove(s);
    }

    public PrivateData getPrivateData(String s, String s1)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        s = new PrivateDataIQ(s, s1);
        return ((PrivateDataIQ)connection().createPacketCollectorAndSend(s).nextResultOrThrow()).getPrivateData();
    }

    public void setPrivateData(PrivateData privatedata)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        privatedata = new PrivateDataIQ(privatedata);
        connection().createPacketCollectorAndSend(privatedata).nextResultOrThrow();
    }

}
