// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.initializer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackInitialization;
import org.jivesoftware.smack.provider.ProviderFileLoader;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.FileUtils;

// Referenced classes of package org.jivesoftware.smack.initializer:
//            SmackInitializer

public abstract class UrlInitializer
    implements SmackInitializer
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/initializer/UrlInitializer.getName());

    public UrlInitializer()
    {
    }

    protected String getConfigUrl()
    {
        return null;
    }

    protected String getProvidersUrl()
    {
        return null;
    }

    public List initialize()
    {
        LinkedList linkedlist;
        ClassLoader classloader;
        String s;
        classloader = getClass().getClassLoader();
        linkedlist = new LinkedList();
        s = getProvidersUrl();
        if (s == null) goto _L2; else goto _L1
_L1:
        Object obj = FileUtils.getStreamForUrl(s, classloader);
        if (obj == null) goto _L4; else goto _L3
_L3:
        try
        {
            LOGGER.log(Level.FINE, (new StringBuilder()).append("Loading providers for providerUrl [").append(s).append("]").toString());
            obj = new ProviderFileLoader(((java.io.InputStream) (obj)), classloader);
            ProviderManager.addLoader(((org.jivesoftware.smack.provider.ProviderLoader) (obj)));
            linkedlist.addAll(((ProviderFileLoader) (obj)).getLoadingExceptions());
        }
        catch (Exception exception1)
        {
            LOGGER.log(Level.SEVERE, (new StringBuilder()).append("Error trying to load provider file ").append(s).toString(), exception1);
            linkedlist.add(exception1);
        }
_L2:
        s = getConfigUrl();
        if (s != null)
        {
            try
            {
                SmackInitialization.processConfigFile(FileUtils.getStreamForUrl(s, classloader), linkedlist, classloader);
            }
            catch (Exception exception)
            {
                linkedlist.add(exception);
                return linkedlist;
            }
        }
        return linkedlist;
_L4:
        LOGGER.log(Level.WARNING, (new StringBuilder()).append("No input stream created for ").append(s).toString());
        linkedlist.add(new IOException((new StringBuilder()).append("No input stream created for ").append(s).toString()));
        if (true) goto _L2; else goto _L5
_L5:
    }

}
