// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.jiveproperties.packet;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smack.util.stringencoder.Base64;

public class JivePropertiesExtension
    implements ExtensionElement
{

    public static final String ELEMENT = "properties";
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/jiveproperties/packet/JivePropertiesExtension.getName());
    public static final String NAMESPACE = "http://www.jivesoftware.com/xmlns/xmpp/properties";
    private final Map properties;

    public JivePropertiesExtension()
    {
        properties = new HashMap();
    }

    public JivePropertiesExtension(Map map)
    {
        properties = map;
    }

    public void deleteProperty(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Map map = properties;
        if (map != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        properties.remove(s);
        if (true) goto _L1; else goto _L3
_L3:
        s;
        throw s;
    }

    public String getElementName()
    {
        return "properties";
    }

    public String getNamespace()
    {
        return "http://www.jivesoftware.com/xmlns/xmpp/properties";
    }

    public Map getProperties()
    {
        this;
        JVM INSTR monitorenter ;
        if (properties != null) goto _L2; else goto _L1
_L1:
        Map map = Collections.emptyMap();
_L4:
        this;
        JVM INSTR monitorexit ;
        return map;
_L2:
        map = Collections.unmodifiableMap(new HashMap(properties));
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Object getProperty(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Map map = properties;
        if (map != null) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return s;
_L2:
        s = ((String) (properties.get(s)));
        if (true) goto _L4; else goto _L3
_L3:
        s;
        throw s;
    }

    public Collection getPropertyNames()
    {
        this;
        JVM INSTR monitorenter ;
        if (properties != null) goto _L2; else goto _L1
_L1:
        java.util.Set set = Collections.emptySet();
_L4:
        this;
        JVM INSTR monitorexit ;
        return set;
_L2:
        set = Collections.unmodifiableSet(new HashSet(properties.keySet()));
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void setProperty(String s, Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        if (!(obj instanceof Serializable))
        {
            throw new IllegalArgumentException("Value must be serialiazble");
        }
        break MISSING_BLOCK_LABEL_24;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        properties.put(s, obj);
        this;
        JVM INSTR monitorexit ;
    }

    public CharSequence toXML()
    {
        XmlStringBuilder xmlstringbuilder;
        Iterator iterator;
        xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        iterator = getPropertyNames().iterator();
_L2:
        Object obj;
        Object obj1;
        Object obj5;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (String)iterator.next();
        obj5 = getProperty(((String) (obj)));
        xmlstringbuilder.openElement("property");
        xmlstringbuilder.element("name", ((String) (obj)));
        xmlstringbuilder.halfOpenElement("value");
        if (obj5 instanceof Integer)
        {
            obj = "integer";
            obj1 = Integer.toString(((Integer)obj5).intValue());
        } else
        if (obj5 instanceof Long)
        {
            obj = "long";
            obj1 = Long.toString(((Long)obj5).longValue());
        } else
        if (obj5 instanceof Float)
        {
            obj = "float";
            obj1 = Float.toString(((Float)obj5).floatValue());
        } else
        if (obj5 instanceof Double)
        {
            obj = "double";
            obj1 = Double.toString(((Double)obj5).doubleValue());
        } else
        if (obj5 instanceof Boolean)
        {
            obj = "boolean";
            obj1 = Boolean.toString(((Boolean)obj5).booleanValue());
        } else
        {
label0:
            {
                if (!(obj5 instanceof String))
                {
                    break label0;
                }
                obj = "string";
                obj1 = (String)obj5;
            }
        }
_L4:
        xmlstringbuilder.attribute("type", ((String) (obj)));
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.escape(((String) (obj1)));
        xmlstringbuilder.closeElement("value");
        xmlstringbuilder.closeElement("property");
        if (true) goto _L2; else goto _L1
        Object obj3;
        Object obj4;
        String s;
        obj = null;
        s = null;
        obj1 = null;
        obj4 = null;
        obj3 = null;
        Object obj2 = new ByteArrayOutputStream();
        obj = new ObjectOutputStream(((java.io.OutputStream) (obj2)));
        ((ObjectOutputStream) (obj)).writeObject(obj5);
        obj4 = "java-object";
        obj3 = Base64.encodeToString(((ByteArrayOutputStream) (obj2)).toByteArray());
        if (obj != null)
        {
            try
            {
                ((ObjectOutputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        obj = obj4;
        obj1 = obj3;
        if (obj2 == null) goto _L4; else goto _L3
_L3:
        ((ByteArrayOutputStream) (obj2)).close();
        obj = obj4;
        obj1 = obj3;
          goto _L4
        obj;
        obj = obj4;
        obj1 = obj3;
          goto _L4
        obj4;
        obj2 = s;
_L8:
        obj = obj2;
        obj1 = obj3;
        LOGGER.log(Level.SEVERE, "Error encoding java object", ((Throwable) (obj4)));
        s = "java-object";
        obj = obj2;
        obj1 = obj3;
        obj4 = (new StringBuilder()).append("Serializing error: ").append(((Exception) (obj4)).getMessage()).toString();
        if (obj3 != null)
        {
            try
            {
                ((ObjectOutputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        obj = s;
        obj1 = obj4;
        if (obj2 == null) goto _L4; else goto _L5
_L5:
        ((ByteArrayOutputStream) (obj2)).close();
        obj = s;
        obj1 = obj4;
          goto _L4
        obj;
        obj = s;
        obj1 = obj4;
          goto _L4
        obj2;
_L7:
        if (obj1 != null)
        {
            try
            {
                ((ObjectOutputStream) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1) { }
        }
        if (obj != null)
        {
            try
            {
                ((ByteArrayOutputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        throw obj2;
_L1:
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
        Exception exception;
        exception;
        obj = obj2;
        obj1 = obj4;
        obj2 = exception;
        continue; /* Loop/switch isn't completed */
        exception;
        obj1 = obj;
        obj = obj2;
        obj2 = exception;
        if (true) goto _L7; else goto _L6
_L6:
        obj4;
          goto _L8
        obj4;
        exception = ((Exception) (obj));
          goto _L8
    }

}
