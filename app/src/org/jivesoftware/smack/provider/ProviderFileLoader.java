// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.provider;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

// Referenced classes of package org.jivesoftware.smack.provider:
//            ProviderLoader, IQProvider, IQProviderInfo, ExtensionElementProvider, 
//            ExtensionProviderInfo, StreamFeatureProviderInfo

public class ProviderFileLoader
    implements ProviderLoader
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/provider/ProviderFileLoader.getName());
    private List exceptions;
    private final Collection extProviders;
    private final Collection iqProviders;
    private final Collection sfProviders;

    public ProviderFileLoader(InputStream inputstream)
    {
        this(inputstream, org/jivesoftware/smack/provider/ProviderFileLoader.getClassLoader());
    }

    public ProviderFileLoader(InputStream inputstream, ClassLoader classloader)
    {
        iqProviders = new LinkedList();
        extProviders = new LinkedList();
        sfProviders = new LinkedList();
        exceptions = new LinkedList();
        XmlPullParser xmlpullparser;
        int i;
        xmlpullparser = XmlPullParserFactory.newInstance().newPullParser();
        xmlpullparser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        xmlpullparser.setInput(inputstream, "UTF-8");
        i = xmlpullparser.getEventType();
_L9:
        if (i != 2) goto _L2; else goto _L1
_L1:
        String s = xmlpullparser.getName();
        if ("smackProviders".equals(s)) goto _L2; else goto _L3
_L3:
        Object obj;
        Object obj1;
        String s1;
        xmlpullparser.next();
        xmlpullparser.next();
        obj1 = xmlpullparser.nextText();
        xmlpullparser.next();
        xmlpullparser.next();
        s1 = xmlpullparser.nextText();
        xmlpullparser.next();
        xmlpullparser.next();
        obj = xmlpullparser.nextText();
        Class class1 = classloader.loadClass(((String) (obj)));
        i = -1;
        s.hashCode();
        JVM INSTR lookupswitch 3: default 688
    //                   -797518000: 285
    //                   80611175: 301
    //                   1834143545: 269;
           goto _L4 _L5 _L6 _L7
_L14:
        LOGGER.warning((new StringBuilder()).append("Unknown provider type: ").append(s).toString());
_L2:
        int j = xmlpullparser.next();
        i = j;
        if (j != 1) goto _L9; else goto _L8
_L8:
        try
        {
            inputstream.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            return;
        }
_L7:
        if (s.equals("iqProvider"))
        {
            i = 0;
        }
          goto _L4
_L5:
        if (s.equals("extensionProvider"))
        {
            i = 1;
        }
          goto _L4
_L6:
        if (s.equals("streamFeatureProvider"))
        {
            i = 2;
        }
          goto _L4
_L15:
        if (!org/jivesoftware/smack/provider/IQProvider.isAssignableFrom(class1)) goto _L11; else goto _L10
_L10:
        iqProviders.add(new IQProviderInfo(((String) (obj1)), s1, (IQProvider)class1.newInstance()));
          goto _L2
        obj;
        LOGGER.log(Level.SEVERE, "Could not find provider class", ((Throwable) (obj)));
        exceptions.add(obj);
          goto _L2
        obj;
        LOGGER.log(Level.SEVERE, (new StringBuilder()).append("Invalid provider type found [").append(s).append("] when expecting iqProvider or extensionProvider").toString(), ((Throwable) (obj)));
        exceptions.add(obj);
          goto _L2
        classloader;
        LOGGER.log(Level.SEVERE, "Unknown error occurred while parsing provider file", classloader);
        exceptions.add(classloader);
        try
        {
            inputstream.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            return;
        }
_L11:
        exceptions.add(new IllegalArgumentException((new StringBuilder()).append(((String) (obj))).append(" is not a IQProvider").toString()));
          goto _L2
        obj1;
        LOGGER.log(Level.SEVERE, (new StringBuilder()).append("Could not instanciate ").append(((String) (obj))).toString(), ((Throwable) (obj1)));
        exceptions.add(obj1);
          goto _L2
        classloader;
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream) { }
        throw classloader;
_L16:
        if (!org/jivesoftware/smack/provider/ExtensionElementProvider.isAssignableFrom(class1)) goto _L13; else goto _L12
_L12:
        extProviders.add(new ExtensionProviderInfo(((String) (obj1)), s1, (ExtensionElementProvider)class1.newInstance()));
          goto _L2
_L13:
        exceptions.add(new IllegalArgumentException((new StringBuilder()).append(((String) (obj))).append(" is not a PacketExtensionProvider").toString()));
          goto _L2
_L17:
        sfProviders.add(new StreamFeatureProviderInfo(((String) (obj1)), s1, (ExtensionElementProvider)class1.newInstance()));
          goto _L2
_L4:
        i;
        JVM INSTR tableswitch 0 2: default 716
    //                   0 317
    //                   1 568
    //                   2 650;
           goto _L14 _L15 _L16 _L17
    }

    public Collection getExtensionProviderInfo()
    {
        return extProviders;
    }

    public Collection getIQProviderInfo()
    {
        return iqProviders;
    }

    public List getLoadingExceptions()
    {
        return Collections.unmodifiableList(exceptions);
    }

    public Collection getStreamFeatureProviderInfo()
    {
        return sfProviders;
    }

}
