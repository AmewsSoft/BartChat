// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.util.StringUtils;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smack.provider:
//            ExtensionElementProvider, IQProvider, ProviderLoader, IQProviderInfo, 
//            ExtensionProviderInfo, StreamFeatureProviderInfo

public final class ProviderManager
{

    private static final Map extensionProviders = new ConcurrentHashMap();
    private static final Map iqProviders = new ConcurrentHashMap();
    private static final Map streamFeatureProviders = new ConcurrentHashMap();

    public ProviderManager()
    {
    }

    public static void addExtensionProvider(String s, String s1, Object obj)
    {
        validate(s, s1);
        s = removeExtensionProvider(s, s1);
        if (obj instanceof ExtensionElementProvider)
        {
            extensionProviders.put(s, (ExtensionElementProvider)obj);
            return;
        } else
        {
            throw new IllegalArgumentException("Provider must be a PacketExtensionProvider");
        }
    }

    public static void addIQProvider(String s, String s1, Object obj)
    {
        validate(s, s1);
        s = removeIQProvider(s, s1);
        if (obj instanceof IQProvider)
        {
            iqProviders.put(s, (IQProvider)obj);
            return;
        } else
        {
            throw new IllegalArgumentException("Provider must be an IQProvider");
        }
    }

    public static void addLoader(ProviderLoader providerloader)
    {
        if (providerloader.getIQProviderInfo() != null)
        {
            IQProviderInfo iqproviderinfo;
            for (Iterator iterator = providerloader.getIQProviderInfo().iterator(); iterator.hasNext(); addIQProvider(iqproviderinfo.getElementName(), iqproviderinfo.getNamespace(), iqproviderinfo.getProvider()))
            {
                iqproviderinfo = (IQProviderInfo)iterator.next();
            }

        }
        if (providerloader.getExtensionProviderInfo() != null)
        {
            ExtensionProviderInfo extensionproviderinfo;
            for (Iterator iterator1 = providerloader.getExtensionProviderInfo().iterator(); iterator1.hasNext(); addExtensionProvider(extensionproviderinfo.getElementName(), extensionproviderinfo.getNamespace(), extensionproviderinfo.getProvider()))
            {
                extensionproviderinfo = (ExtensionProviderInfo)iterator1.next();
            }

        }
        if (providerloader.getStreamFeatureProviderInfo() != null)
        {
            StreamFeatureProviderInfo streamfeatureproviderinfo;
            for (providerloader = providerloader.getStreamFeatureProviderInfo().iterator(); providerloader.hasNext(); addStreamFeatureProvider(streamfeatureproviderinfo.getElementName(), streamfeatureproviderinfo.getNamespace(), (ExtensionElementProvider)streamfeatureproviderinfo.getProvider()))
            {
                streamfeatureproviderinfo = (StreamFeatureProviderInfo)providerloader.next();
            }

        }
    }

    public static void addStreamFeatureProvider(String s, String s1, ExtensionElementProvider extensionelementprovider)
    {
        validate(s, s1);
        s = getKey(s, s1);
        streamFeatureProviders.put(s, extensionelementprovider);
    }

    public static ExtensionElementProvider getExtensionProvider(String s, String s1)
    {
        s = getKey(s, s1);
        return (ExtensionElementProvider)extensionProviders.get(s);
    }

    public static List getExtensionProviders()
    {
        ArrayList arraylist = new ArrayList(extensionProviders.size());
        arraylist.addAll(extensionProviders.values());
        return arraylist;
    }

    public static IQProvider getIQProvider(String s, String s1)
    {
        s = getKey(s, s1);
        return (IQProvider)iqProviders.get(s);
    }

    public static List getIQProviders()
    {
        ArrayList arraylist = new ArrayList(iqProviders.size());
        arraylist.addAll(iqProviders.values());
        return arraylist;
    }

    private static String getKey(String s, String s1)
    {
        return XmppStringUtils.generateKey(s, s1);
    }

    public static ExtensionElementProvider getStreamFeatureProvider(String s, String s1)
    {
        s = getKey(s, s1);
        return (ExtensionElementProvider)streamFeatureProviders.get(s);
    }

    public static String removeExtensionProvider(String s, String s1)
    {
        s = getKey(s, s1);
        extensionProviders.remove(s);
        return s;
    }

    public static String removeIQProvider(String s, String s1)
    {
        s = getKey(s, s1);
        iqProviders.remove(s);
        return s;
    }

    public static void removeStreamFeatureProvider(String s, String s1)
    {
        s = getKey(s, s1);
        streamFeatureProviders.remove(s);
    }

    private static void validate(String s, String s1)
    {
        if (StringUtils.isNullOrEmpty(s))
        {
            throw new IllegalArgumentException("elementName must not be null or empty");
        }
        if (StringUtils.isNullOrEmpty(s1))
        {
            throw new IllegalArgumentException("namespace must not be null or empty");
        } else
        {
            return;
        }
    }

    static 
    {
        SmackConfiguration.getVersion();
    }
}
