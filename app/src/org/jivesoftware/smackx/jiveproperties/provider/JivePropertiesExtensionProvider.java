// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.jiveproperties.provider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jivesoftware.smackx.jiveproperties.JivePropertiesManager;
import org.jivesoftware.smackx.jiveproperties.packet.JivePropertiesExtension;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class JivePropertiesExtensionProvider extends ExtensionElementProvider
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/jiveproperties/provider/JivePropertiesExtensionProvider.getName());

    public JivePropertiesExtensionProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public JivePropertiesExtension parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        HashMap hashmap = new HashMap();
label0:
        do
        {
            do
            {
                i = xmlpullparser.next();
                if (i != 2 || !xmlpullparser.getName().equals("property"))
                {
                    continue label0;
                }
                i = 0;
                String s3 = null;
                String s2 = null;
                String s1 = null;
                Integer integer = null;
                while (i == 0) 
                {
                    int j = xmlpullparser.next();
                    if (j == 2)
                    {
                        String s = xmlpullparser.getName();
                        if (s.equals("name"))
                        {
                            s3 = xmlpullparser.nextText();
                        } else
                        if (s.equals("value"))
                        {
                            s2 = xmlpullparser.getAttributeValue("", "type");
                            s1 = xmlpullparser.nextText();
                        }
                    } else
                    if (j == 3 && xmlpullparser.getName().equals("property"))
                    {
                        Object obj;
                        if ("integer".equals(s2))
                        {
                            obj = Integer.valueOf(s1);
                        } else
                        if ("long".equals(s2))
                        {
                            obj = Long.valueOf(s1);
                        } else
                        if ("float".equals(s2))
                        {
                            obj = Float.valueOf(s1);
                        } else
                        if ("double".equals(s2))
                        {
                            obj = Double.valueOf(s1);
                        } else
                        if ("boolean".equals(s2))
                        {
                            obj = Boolean.valueOf(s1);
                        } else
                        if ("string".equals(s2))
                        {
                            obj = s1;
                        } else
                        {
                            obj = integer;
                            if ("java-object".equals(s2))
                            {
                                if (JivePropertiesManager.isJavaObjectEnabled())
                                {
                                    try
                                    {
                                        obj = (new ObjectInputStream(new ByteArrayInputStream(Base64.decode(s1)))).readObject();
                                    }
                                    // Misplaced declaration of an exception variable
                                    catch (Object obj)
                                    {
                                        LOGGER.log(Level.SEVERE, "Error parsing java object", ((Throwable) (obj)));
                                        obj = integer;
                                    }
                                } else
                                {
                                    LOGGER.severe("JavaObject is not enabled. Enable with JivePropertiesManager.setJavaObjectEnabled(true)");
                                    obj = integer;
                                }
                            }
                        }
                        if (s3 != null && obj != null)
                        {
                            hashmap.put(s3, obj);
                        }
                        i = 1;
                        integer = ((Integer) (obj));
                    }
                }
            } while (true);
        } while (i != 3 || !xmlpullparser.getName().equals("properties"));
        return new JivePropertiesExtension(hashmap);
    }

}
