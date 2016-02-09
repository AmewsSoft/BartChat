// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.compression.Java7ZlibInputOutputStream;
import org.jivesoftware.smack.initializer.SmackInitializer;
import org.jivesoftware.smack.provider.BindIQProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.sasl.core.SASLXOauth2Mechanism;
import org.jivesoftware.smack.sasl.core.SCRAMSHA1Mechanism;
import org.jivesoftware.smack.util.FileUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

// Referenced classes of package org.jivesoftware.smack:
//            SmackConfiguration, SASLAuthentication

public final class SmackInitialization
{

    private static final String DEFAULT_CONFIG_FILE = "classpath:org.jivesoftware.smack/smack-config.xml";
    private static final Logger LOGGER;
    static final String SMACK_VERSION;

    public SmackInitialization()
    {
    }

    private static void loadSmackClass(String s, boolean flag, ClassLoader classloader)
        throws Exception
    {
label0:
        {
            try
            {
                classloader = Class.forName(s, true, classloader);
            }
            catch (ClassNotFoundException classnotfoundexception)
            {
                if (flag)
                {
                    classloader = Level.FINE;
                } else
                {
                    classloader = Level.WARNING;
                }
                LOGGER.log(classloader, (new StringBuilder()).append("A startup class '").append(s).append("' could not be loaded.").toString());
                if (!flag)
                {
                    throw classnotfoundexception;
                } else
                {
                    break label0;
                }
            }
            if (!org/jivesoftware/smack/initializer/SmackInitializer.isAssignableFrom(classloader))
            {
                break MISSING_BLOCK_LABEL_166;
            }
            classloader = ((SmackInitializer)classloader.newInstance()).initialize();
            if (classloader == null || classloader.size() == 0)
            {
                LOGGER.log(Level.FINE, (new StringBuilder()).append("Loaded SmackInitializer ").append(s).toString());
            } else
            {
                s = classloader.iterator();
                while (s.hasNext()) 
                {
                    classloader = (Exception)s.next();
                    LOGGER.log(Level.SEVERE, "Exception in loadSmackClass", classloader);
                }
            }
        }
        return;
        LOGGER.log(Level.FINE, (new StringBuilder()).append("Loaded ").append(s).toString());
        return;
    }

    private static void parseClassesToLoad(XmlPullParser xmlpullparser, boolean flag, Collection collection, ClassLoader classloader)
        throws XmlPullParserException, IOException, Exception
    {
        String s = xmlpullparser.getName();
        do
        {
            int i = xmlpullparser.next();
            String s1 = xmlpullparser.getName();
            if (i == 2 && "className".equals(s1))
            {
                String s2 = xmlpullparser.nextText();
                if (!SmackConfiguration.isDisabledSmackClass(s2))
                {
                    try
                    {
                        loadSmackClass(s2, flag, classloader);
                    }
                    catch (Exception exception)
                    {
                        if (collection != null)
                        {
                            collection.add(exception);
                        } else
                        {
                            throw exception;
                        }
                    }
                }
            }
        } while (i != 3 || !s.equals(s1));
    }

    public static void processConfigFile(InputStream inputstream, Collection collection)
        throws Exception
    {
        processConfigFile(inputstream, collection, org/jivesoftware/smack/SmackInitialization.getClassLoader());
    }

    public static void processConfigFile(InputStream inputstream, Collection collection, ClassLoader classloader)
        throws Exception
    {
        XmlPullParser xmlpullparser = XmlPullParserFactory.newInstance().newPullParser();
        xmlpullparser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        xmlpullparser.setInput(inputstream, "UTF-8");
        int i = xmlpullparser.getEventType();
        do
        {
            int j;
            if (i == 2)
            {
                if (xmlpullparser.getName().equals("startupClasses"))
                {
                    parseClassesToLoad(xmlpullparser, false, collection, classloader);
                } else
                if (xmlpullparser.getName().equals("optionalStartupClasses"))
                {
                    parseClassesToLoad(xmlpullparser, true, collection, classloader);
                }
            }
            j = xmlpullparser.next();
            i = j;
        } while (j != 1);
        try
        {
            inputstream.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            LOGGER.log(Level.SEVERE, "Error while closing config file input stream", inputstream);
        }
    }

    static 
    {
        LOGGER = Logger.getLogger(org/jivesoftware/smack/SmackInitialization.getName());
        Object obj;
        Object obj2;
        obj2 = new BufferedReader(new InputStreamReader(FileUtils.getStreamForUrl("classpath:org.jivesoftware.smack/version", null)));
        obj = ((BufferedReader) (obj2)).readLine();
        try
        {
            ((BufferedReader) (obj2)).close();
        }
        catch (IOException ioexception)
        {
            try
            {
                LOGGER.log(Level.WARNING, "IOException closing stream", ioexception);
            }
            catch (Exception exception)
            {
                LOGGER.log(Level.SEVERE, "Could not determine Smack version", exception);
                exception = "unkown";
            }
        }
        SMACK_VERSION = ((String) (obj));
        obj = System.getProperty("smack.disabledClasses");
        if (obj != null)
        {
            obj = ((String) (obj)).split(",");
            int k = obj.length;
            for (int i = 0; i < k; i++)
            {
                obj2 = obj[i];
                SmackConfiguration.disabledSmackClasses.add(obj2);
            }

        }
        String as[];
        int j;
        int l;
        try
        {
            FileUtils.addLines("classpath:org.jivesoftware.smack/disabledClasses", SmackConfiguration.disabledSmackClasses);
        }
        catch (Exception exception1)
        {
            throw new IllegalStateException(exception1);
        }
        as = (String[])(String[])Class.forName("org.jivesoftware.smack.CustomSmackConfiguration").getField("DISABLED_SMACK_CLASSES").get(null);
        if (as == null) goto _L2; else goto _L1
_L1:
        LOGGER.warning("Using CustomSmackConfig is deprecated and will be removed in a future release");
        l = as.length;
        j = 0;
_L3:
        if (j >= l)
        {
            break; /* Loop/switch isn't completed */
        }
        ioexception = as[j];
        SmackConfiguration.disabledSmackClasses.add(ioexception);
        j++;
        continue; /* Loop/switch isn't completed */
        Object obj1;
        obj1;
        break; /* Loop/switch isn't completed */
        if (true) goto _L3; else goto _L2
_L2:
        InputStream inputstream;
        try
        {
            inputstream = FileUtils.getStreamForUrl("classpath:org.jivesoftware.smack/smack-config.xml", null);
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            throw new IllegalStateException(inputstream);
        }
        try
        {
            processConfigFile(inputstream, null);
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            throw new IllegalStateException(inputstream);
        }
        SmackConfiguration.compressionHandlers.add(new Java7ZlibInputOutputStream());
        try
        {
            if (Boolean.getBoolean("smack.debugEnabled"))
            {
                SmackConfiguration.DEBUG = true;
            }
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream) { }
        SASLAuthentication.registerSASLMechanism(new SCRAMSHA1Mechanism());
        SASLAuthentication.registerSASLMechanism(new SASLXOauth2Mechanism());
        ProviderManager.addIQProvider("bind", "urn:ietf:params:xml:ns:xmpp-bind", new BindIQProvider());
        SmackConfiguration.smackInitialized = true;
        return;
        inputstream;
          goto _L2
        inputstream;
          goto _L2
        inputstream;
          goto _L2
        inputstream;
          goto _L2
    }
}
