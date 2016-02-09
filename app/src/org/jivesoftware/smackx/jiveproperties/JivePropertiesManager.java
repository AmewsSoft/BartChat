// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.jiveproperties;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.jiveproperties.packet.JivePropertiesExtension;

public class JivePropertiesManager
{

    private static boolean javaObjectEnabled = false;

    public JivePropertiesManager()
    {
    }

    public static void addProperty(Stanza stanza, String s, Object obj)
    {
        JivePropertiesExtension jivepropertiesextension1 = (JivePropertiesExtension)stanza.getExtension("http://www.jivesoftware.com/xmlns/xmpp/properties");
        JivePropertiesExtension jivepropertiesextension = jivepropertiesextension1;
        if (jivepropertiesextension1 == null)
        {
            jivepropertiesextension = new JivePropertiesExtension();
            stanza.addExtension(jivepropertiesextension);
        }
        jivepropertiesextension.setProperty(s, obj);
    }

    public static Map getProperties(Stanza stanza)
    {
        stanza = (JivePropertiesExtension)stanza.getExtension("http://www.jivesoftware.com/xmlns/xmpp/properties");
        if (stanza == null)
        {
            return Collections.emptyMap();
        } else
        {
            return stanza.getProperties();
        }
    }

    public static Collection getPropertiesNames(Stanza stanza)
    {
        stanza = (JivePropertiesExtension)stanza.getExtension("http://www.jivesoftware.com/xmlns/xmpp/properties");
        if (stanza == null)
        {
            return Collections.emptyList();
        } else
        {
            return stanza.getPropertyNames();
        }
    }

    public static Object getProperty(Stanza stanza, String s)
    {
        Object obj = null;
        JivePropertiesExtension jivepropertiesextension = (JivePropertiesExtension)stanza.getExtension("http://www.jivesoftware.com/xmlns/xmpp/properties");
        stanza = obj;
        if (jivepropertiesextension != null)
        {
            stanza = ((Stanza) (jivepropertiesextension.getProperty(s)));
        }
        return stanza;
    }

    public static boolean isJavaObjectEnabled()
    {
        return javaObjectEnabled;
    }

    public static void setJavaObjectEnabled(boolean flag)
    {
        javaObjectEnabled = flag;
    }

}
