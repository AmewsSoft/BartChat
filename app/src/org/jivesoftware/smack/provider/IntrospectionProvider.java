// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.provider;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.ParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smack.provider:
//            IQProvider, ExtensionElementProvider

public class IntrospectionProvider
{
    public static abstract class IQIntrospectionProvider extends IQProvider
    {

        private final Class elementClass;

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public IQ parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            xmlpullparser = (IQ)IntrospectionProvider.parseWithIntrospection(elementClass, xmlpullparser, i);
            return xmlpullparser;
            xmlpullparser;
_L2:
            throw new SmackException(xmlpullparser);
            xmlpullparser;
            continue; /* Loop/switch isn't completed */
            xmlpullparser;
            continue; /* Loop/switch isn't completed */
            xmlpullparser;
            continue; /* Loop/switch isn't completed */
            xmlpullparser;
            continue; /* Loop/switch isn't completed */
            xmlpullparser;
            continue; /* Loop/switch isn't completed */
            xmlpullparser;
            if (true) goto _L2; else goto _L1
_L1:
        }

        protected IQIntrospectionProvider(Class class1)
        {
            elementClass = class1;
        }
    }

    public static abstract class PacketExtensionIntrospectionProvider extends ExtensionElementProvider
    {

        private final Class elementClass;

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public ExtensionElement parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            xmlpullparser = (ExtensionElement)IntrospectionProvider.parseWithIntrospection(elementClass, xmlpullparser, i);
            return xmlpullparser;
            xmlpullparser;
_L2:
            throw new SmackException(xmlpullparser);
            xmlpullparser;
            continue; /* Loop/switch isn't completed */
            xmlpullparser;
            continue; /* Loop/switch isn't completed */
            xmlpullparser;
            continue; /* Loop/switch isn't completed */
            xmlpullparser;
            continue; /* Loop/switch isn't completed */
            xmlpullparser;
            continue; /* Loop/switch isn't completed */
            xmlpullparser;
            if (true) goto _L2; else goto _L1
_L1:
        }

        protected PacketExtensionIntrospectionProvider(Class class1)
        {
            elementClass = class1;
        }
    }


    public IntrospectionProvider()
    {
    }

    private static Object decode(Class class1, String s)
        throws ClassNotFoundException
    {
        byte byte0;
        class1 = class1.getName();
        byte0 = -1;
        class1.hashCode();
        JVM INSTR lookupswitch 9: default 92
    //                   -1325958191: 218
    //                   -530663260: 262
    //                   104431: 176
    //                   3039496: 247
    //                   3327612: 190
    //                   64711720: 162
    //                   97526364: 204
    //                   109413500: 232
    //                   1195259493: 148;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_262;
_L11:
        class1 = s;
        switch (byte0)
        {
        default:
            class1 = null;
            // fall through

        case 0: // '\0'
            return class1;

        case 1: // '\001'
            return Boolean.valueOf(s);

        case 2: // '\002'
            return Integer.valueOf(s);

        case 3: // '\003'
            return Long.valueOf(s);

        case 4: // '\004'
            return Float.valueOf(s);

        case 5: // '\005'
            return Double.valueOf(s);

        case 6: // '\006'
            return Short.valueOf(s);

        case 7: // '\007'
            return Byte.valueOf(s);

        case 8: // '\b'
            return Class.forName(s);
        }
_L10:
        if (class1.equals("java.lang.String"))
        {
            byte0 = 0;
        }
          goto _L11
_L7:
        if (class1.equals("boolean"))
        {
            byte0 = 1;
        }
          goto _L11
_L4:
        if (class1.equals("int"))
        {
            byte0 = 2;
        }
          goto _L11
_L6:
        if (class1.equals("long"))
        {
            byte0 = 3;
        }
          goto _L11
_L8:
        if (class1.equals("float"))
        {
            byte0 = 4;
        }
          goto _L11
_L2:
        if (class1.equals("double"))
        {
            byte0 = 5;
        }
          goto _L11
_L9:
        if (class1.equals("short"))
        {
            byte0 = 6;
        }
          goto _L11
_L5:
        if (class1.equals("byte"))
        {
            byte0 = 7;
        }
          goto _L11
        if (class1.equals("java.lang.Class"))
        {
            byte0 = 8;
        }
          goto _L11
    }

    public static Object parseWithIntrospection(Class class1, XmlPullParser xmlpullparser, int i)
        throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, XmlPullParserException, IOException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException
    {
        ParserUtils.assertAtStartTag(xmlpullparser);
        class1 = ((Class) (class1.newInstance()));
        do
        {
label0:
            do
            {
                switch (xmlpullparser.next())
                {
                default:
                    break;

                case 3: // '\003'
                    break label0;

                case 2: // '\002'
                    String s = xmlpullparser.getName();
                    Object obj = xmlpullparser.nextText();
                    Class class2 = class1.getClass().getMethod((new StringBuilder()).append("get").append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString(), new Class[0]).getReturnType();
                    obj = decode(class2, ((String) (obj)));
                    class1.getClass().getMethod((new StringBuilder()).append("set").append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString(), new Class[] {
                        class2
                    }).invoke(class1, new Object[] {
                        obj
                    });
                    break;
                }
            } while (true);
        } while (xmlpullparser.getDepth() != i);
        ParserUtils.assertAtEndTag(xmlpullparser);
        return class1;
    }
}
