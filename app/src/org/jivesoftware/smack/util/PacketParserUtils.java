// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.DefaultExtensionElement;
import org.jivesoftware.smack.packet.EmptyResultIQ;
import org.jivesoftware.smack.packet.ErrorIQ;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.StartTls;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.packet.UnparsedIQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

// Referenced classes of package org.jivesoftware.smack.util:
//            ParserUtils, XmlStringBuilder, StringUtils

public class PacketParserUtils
{

    static final boolean $assertionsDisabled;
    public static final String FEATURE_XML_ROUNDTRIP = "http://xmlpull.org/v1/doc/features.html#xml-roundtrip";
    private static final Logger LOGGER;
    private static final XmlPullParserFactory XML_PULL_PARSER_FACTORY;
    public static final boolean XML_PULL_PARSER_SUPPORTS_ROUNDTRIP;

    public PacketParserUtils()
    {
    }

    public static void addExtensionElement(Collection collection, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        addExtensionElement(collection, xmlpullparser, xmlpullparser.getName(), xmlpullparser.getNamespace());
    }

    public static void addExtensionElement(Collection collection, XmlPullParser xmlpullparser, String s, String s1)
        throws XmlPullParserException, IOException, SmackException
    {
        collection.add(parseExtensionElement(s, s1, xmlpullparser));
    }

    public static void addExtensionElement(Stanza stanza, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        ParserUtils.assertAtStartTag(xmlpullparser);
        addExtensionElement(stanza, xmlpullparser, xmlpullparser.getName(), xmlpullparser.getNamespace());
    }

    public static void addExtensionElement(Stanza stanza, XmlPullParser xmlpullparser, String s, String s1)
        throws XmlPullParserException, IOException, SmackException
    {
        stanza.addExtension(parseExtensionElement(s, s1, xmlpullparser));
    }

    public static void addPacketExtension(Collection collection, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        addExtensionElement(collection, xmlpullparser, xmlpullparser.getName(), xmlpullparser.getNamespace());
    }

    public static void addPacketExtension(Collection collection, XmlPullParser xmlpullparser, String s, String s1)
        throws XmlPullParserException, IOException, SmackException
    {
        addExtensionElement(collection, xmlpullparser, s, s1);
    }

    public static void addPacketExtension(Stanza stanza, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        addExtensionElement(stanza, xmlpullparser);
    }

    public static void addPacketExtension(Stanza stanza, XmlPullParser xmlpullparser, String s, String s1)
        throws XmlPullParserException, IOException, SmackException
    {
        addExtensionElement(stanza, xmlpullparser, s, s1);
    }

    private static String getLanguageAttribute(XmlPullParser xmlpullparser)
    {
        for (int i = 0; i < xmlpullparser.getAttributeCount(); i++)
        {
            String s = xmlpullparser.getAttributeName(i);
            if ("xml:lang".equals(s) || "lang".equals(s) && "xml".equals(xmlpullparser.getAttributePrefix(i)))
            {
                return xmlpullparser.getAttributeValue(i);
            }
        }

        return null;
    }

    public static XmlPullParser getParserFor(Reader reader)
        throws XmlPullParserException, IOException
    {
        reader = newXmppParser(reader);
        for (int i = reader.getEventType(); i != 2; i = reader.next())
        {
            if (i == 1)
            {
                throw new IllegalArgumentException("Document contains no start tag");
            }
        }

        return reader;
    }

    public static XmlPullParser getParserFor(String s)
        throws XmlPullParserException, IOException
    {
        return getParserFor(((Reader) (new StringReader(s))));
    }

    public static XmlPullParser getParserFor(String s, String s1)
        throws XmlPullParserException, IOException
    {
        XmlPullParser xmlpullparser = getParserFor(s);
        do
        {
            int i = xmlpullparser.getEventType();
            String s2 = xmlpullparser.getName();
            if (i == 2 && s2.equals(s1))
            {
                return xmlpullparser;
            }
            if (i == 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Could not find start tag '").append(s1).append("' in stanza: ").append(s).toString());
            }
            xmlpullparser.next();
        } while (true);
    }

    public static XmlPullParser newXmppParser()
        throws XmlPullParserException
    {
        XmlPullParser xmlpullparser = XmlPullParserFactory.newInstance().newPullParser();
        xmlpullparser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        if (XML_PULL_PARSER_SUPPORTS_ROUNDTRIP)
        {
            try
            {
                xmlpullparser.setFeature("http://xmlpull.org/v1/doc/features.html#xml-roundtrip", true);
            }
            catch (XmlPullParserException xmlpullparserexception)
            {
                LOGGER.log(Level.SEVERE, "XmlPullParser does not support XML_ROUNDTRIP, although it was first determined to be supported", xmlpullparserexception);
                return xmlpullparser;
            }
        }
        return xmlpullparser;
    }

    public static XmlPullParser newXmppParser(Reader reader)
        throws XmlPullParserException
    {
        XmlPullParser xmlpullparser = newXmppParser();
        xmlpullparser.setInput(reader);
        return xmlpullparser;
    }

    public static org.jivesoftware.smack.compress.packet.Compress.Feature parseCompressionFeature(XmlPullParser xmlpullparser)
        throws IOException, XmlPullParserException
    {
        LinkedList linkedlist;
        int i;
        if (!$assertionsDisabled && xmlpullparser.getEventType() != 2)
        {
            throw new AssertionError();
        }
        i = xmlpullparser.getDepth();
        linkedlist = new LinkedList();
_L14:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 68
    //                   2 71
    //                   3 153;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s = xmlpullparser.getName();
        s.hashCode();
        JVM INSTR tableswitch -1077554975 -1077554975: default 100
    //                   -1077554975 139;
           goto _L4 _L5
_L4:
        byte byte0 = -1;
_L7:
        switch (byte0)
        {
        case 0: // '\0'
            linkedlist.add(xmlpullparser.nextText());
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (!s.equals("method")) goto _L4; else goto _L6
_L6:
        byte0 = 0;
          goto _L7
          goto _L4
_L3:
        s = xmlpullparser.getName();
        s.hashCode();
        JVM INSTR tableswitch 1431984486 1431984486: default 184
    //                   1431984486 242;
           goto _L8 _L9
_L8:
        byte0 = -1;
_L11:
        switch (byte0)
        {
        default:
            continue; /* Loop/switch isn't completed */

        case 0: // '\0'
            break;
        }
        if (xmlpullparser.getDepth() != i)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!$assertionsDisabled && xmlpullparser.getEventType() != 3)
        {
            throw new AssertionError();
        }
        break; /* Loop/switch isn't completed */
_L9:
        if (!s.equals("compression"))
        {
            continue; /* Loop/switch isn't completed */
        }
        byte0 = 0;
        if (true) goto _L11; else goto _L10
_L10:
        if (true) goto _L8; else goto _L12
_L12:
        if (!$assertionsDisabled && xmlpullparser.getDepth() != i)
        {
            throw new AssertionError();
        } else
        {
            return new org.jivesoftware.smack.compress.packet.Compress.Feature(linkedlist);
        }
        if (true) goto _L14; else goto _L13
_L13:
    }

    public static CharSequence parseContent(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        if (!$assertionsDisabled && xmlpullparser.getEventType() != 2)
        {
            throw new AssertionError();
        }
        if (xmlpullparser.isEmptyElementTag())
        {
            return "";
        } else
        {
            xmlpullparser.next();
            return parseContentDepth(xmlpullparser, xmlpullparser.getDepth(), false);
        }
    }

    public static CharSequence parseContentDepth(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        return parseContentDepth(xmlpullparser, i, false);
    }

    public static CharSequence parseContentDepth(XmlPullParser xmlpullparser, int i, boolean flag)
        throws XmlPullParserException, IOException
    {
        if (xmlpullparser.getFeature("http://xmlpull.org/v1/doc/features.html#xml-roundtrip"))
        {
            return parseContentDepthWithRoundtrip(xmlpullparser, i, flag);
        } else
        {
            return parseContentDepthWithoutRoundtrip(xmlpullparser, i, flag);
        }
    }

    private static CharSequence parseContentDepthWithRoundtrip(XmlPullParser xmlpullparser, int i, boolean flag)
        throws XmlPullParserException, IOException
    {
        StringBuilder stringbuilder = new StringBuilder();
        int j = xmlpullparser.getEventType();
        do
        {
            if (j != 2 || !xmlpullparser.isEmptyElementTag())
            {
                stringbuilder.append(xmlpullparser.getText());
            }
            if (j == 3 && xmlpullparser.getDepth() <= i)
            {
                return stringbuilder;
            }
            j = xmlpullparser.next();
        } while (true);
    }

    private static CharSequence parseContentDepthWithoutRoundtrip(XmlPullParser xmlpullparser, int i, boolean flag)
        throws XmlPullParserException, IOException
    {
        String s;
        XmlStringBuilder xmlstringbuilder;
        boolean flag1;
        int j;
        xmlstringbuilder = new XmlStringBuilder();
        j = xmlpullparser.getEventType();
        flag1 = false;
        s = null;
_L6:
        j;
        JVM INSTR tableswitch 2 4: default 52
    //                   2 63
    //                   3 205
    //                   4 270;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_270;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L7:
        j = xmlpullparser.next();
        if (true) goto _L6; else goto _L5
_L5:
        String s1;
label0:
        {
            xmlstringbuilder.halfOpenElement(xmlpullparser.getName());
            if (s != null)
            {
                s1 = s;
                if (!flag)
                {
                    break label0;
                }
            }
            String s3 = xmlpullparser.getNamespace();
            s1 = s;
            if (StringUtils.isNotEmpty(s3))
            {
                xmlstringbuilder.attribute("xmlns", s3);
                s1 = xmlpullparser.getName();
            }
        }
        for (int k = 0; k < xmlpullparser.getAttributeCount(); k++)
        {
            xmlstringbuilder.attribute(xmlpullparser.getAttributeName(k), xmlpullparser.getAttributeValue(k));
        }

        if (xmlpullparser.isEmptyElementTag())
        {
            xmlstringbuilder.closeEmptyElement();
            flag1 = true;
            s = s1;
        } else
        {
            xmlstringbuilder.rightAngleBracket();
            s = s1;
        }
          goto _L7
_L3:
        String s2;
        if (flag1)
        {
            flag1 = false;
        } else
        {
            xmlstringbuilder.closeElement(xmlpullparser.getName());
        }
        s2 = s;
        if (s != null)
        {
            s2 = s;
            if (s.equals(xmlpullparser.getName()))
            {
                s2 = null;
            }
        }
        s = s2;
        if (xmlpullparser.getDepth() <= i)
        {
            return xmlstringbuilder;
        }
          goto _L7
        xmlstringbuilder.append(xmlpullparser.getText());
          goto _L7
    }

    public static Map parseDescriptiveTexts(XmlPullParser xmlpullparser, Map map)
        throws XmlPullParserException, IOException
    {
        Object obj = map;
        if (map == null)
        {
            obj = new HashMap();
        }
        xmlpullparser = (String)((Map) (obj)).put(getLanguageAttribute(xmlpullparser), xmlpullparser.nextText());
        if (!$assertionsDisabled && xmlpullparser != null)
        {
            throw new AssertionError();
        } else
        {
            return ((Map) (obj));
        }
    }

    public static CharSequence parseElement(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        return parseElement(xmlpullparser, false);
    }

    public static CharSequence parseElement(XmlPullParser xmlpullparser, boolean flag)
        throws XmlPullParserException, IOException
    {
        if (!$assertionsDisabled && xmlpullparser.getEventType() != 2)
        {
            throw new AssertionError();
        } else
        {
            return parseContentDepth(xmlpullparser, xmlpullparser.getDepth(), flag);
        }
    }

    public static String parseElementText(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        if (!$assertionsDisabled && xmlpullparser.getEventType() != 2)
        {
            throw new AssertionError();
        }
        String s;
        if (xmlpullparser.isEmptyElementTag())
        {
            s = "";
        } else
        {
            int i = xmlpullparser.next();
            if (i != 4)
            {
                if (i == 3)
                {
                    return "";
                } else
                {
                    throw new XmlPullParserException("Non-empty element tag not followed by text, while Mixed Content (XML 3.2.2) is disallowed");
                }
            }
            s = xmlpullparser.getText();
            if (xmlpullparser.next() != 3)
            {
                throw new XmlPullParserException("Non-empty element tag contains child-elements, while Mixed Content (XML 3.2.2) is disallowed");
            }
        }
        return s;
    }

    public static XMPPError parseError(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        Map map;
        Object obj;
        String s;
        ArrayList arraylist;
        org.jivesoftware.smack.packet.XMPPError.Type type;
        String s1;
        int i;
        i = xmlpullparser.getDepth();
        map = null;
        obj = null;
        s = null;
        arraylist = new ArrayList();
        type = org.jivesoftware.smack.packet.XMPPError.Type.fromString(xmlpullparser.getAttributeValue("", "type"));
        s1 = xmlpullparser.getAttributeValue("", "by");
_L15:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 80
    //                   2 83
    //                   3 282;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L3:
        continue; /* Loop/switch isn't completed */
_L2:
        Object obj1;
        String s2;
        obj1 = xmlpullparser.getName();
        s2 = xmlpullparser.getNamespace();
        s2.hashCode();
        JVM INSTR tableswitch 888780199 888780199: default 124
    //                   888780199 161;
           goto _L4 _L5
_L4:
        byte byte0 = -1;
_L9:
        byte0;
        JVM INSTR tableswitch 0 0: default 148
    //                   0 178;
           goto _L6 _L7
_L6:
        addExtensionElement(arraylist, xmlpullparser, ((String) (obj1)), s2);
        continue; /* Loop/switch isn't completed */
_L5:
        if (!s2.equals("urn:ietf:params:xml:ns:xmpp-stanzas")) goto _L4; else goto _L8
_L8:
        byte0 = 0;
          goto _L9
          goto _L4
_L7:
        ((String) (obj1)).hashCode();
        JVM INSTR tableswitch 3556653 3556653: default 200
    //                   3556653 256;
           goto _L10 _L11
_L10:
        byte0 = -1;
_L13:
        switch (byte0)
        {
        default:
            obj1 = org.jivesoftware.smack.packet.XMPPError.Condition.fromString(((String) (obj1)));
            obj = obj1;
            if (!xmlpullparser.isEmptyElementTag())
            {
                s = xmlpullparser.nextText();
                obj = obj1;
            }
            break;

        case 0: // '\0'
            map = parseDescriptiveTexts(xmlpullparser, map);
            break;
        }
        continue; /* Loop/switch isn't completed */
_L11:
        if (!((String) (obj1)).equals("text")) goto _L10; else goto _L12
_L12:
        byte0 = 0;
          goto _L13
        if (true) goto _L15; else goto _L14
_L14:
        if (xmlpullparser.getDepth() != i) goto _L15; else goto _L16
_L16:
        return new XMPPError(((org.jivesoftware.smack.packet.XMPPError.Condition) (obj)), s, s1, type, map, arraylist);
    }

    public static ExtensionElement parseExtensionElement(String s, String s1, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        int i;
        ParserUtils.assertAtStartTag(xmlpullparser);
        ExtensionElementProvider extensionelementprovider = ProviderManager.getExtensionProvider(s, s1);
        if (extensionelementprovider != null)
        {
            return (ExtensionElement)extensionelementprovider.parse(xmlpullparser);
        }
        i = xmlpullparser.getDepth();
        s = new DefaultExtensionElement(s, s1);
_L5:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 68
    //                   2 71
    //                   3 121;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L3:
        continue; /* Loop/switch isn't completed */
_L2:
        s1 = xmlpullparser.getName();
        if (xmlpullparser.isEmptyElementTag())
        {
            s.setValue(s1, "");
        } else
        if (xmlpullparser.next() == 4)
        {
            s.setValue(s1, xmlpullparser.getText());
        }
        if (true) goto _L5; else goto _L4
_L4:
        if (xmlpullparser.getDepth() != i) goto _L5; else goto _L6
_L6:
        return s;
    }

    public static IQ parseIQ(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        Object obj;
        XMPPError xmpperror;
        String s;
        String s1;
        String s2;
        org.jivesoftware.smack.packet.IQ.Type type;
        int i;
        ParserUtils.assertAtStartTag(xmlpullparser);
        i = xmlpullparser.getDepth();
        obj = null;
        xmpperror = null;
        s = xmlpullparser.getAttributeValue("", "id");
        s1 = xmlpullparser.getAttributeValue("", "to");
        s2 = xmlpullparser.getAttributeValue("", "from");
        type = org.jivesoftware.smack.packet.IQ.Type.fromString(xmlpullparser.getAttributeValue("", "type"));
_L14:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 100
    //                   2 103
    //                   3 232;
           goto _L1 _L2 _L3
_L2:
        String s3;
        String s4;
        byte byte0;
        s3 = xmlpullparser.getName();
        s4 = xmlpullparser.getNamespace();
        byte0 = -1;
        s3.hashCode();
        JVM INSTR tableswitch 96784904 96784904: default 144
    //                   96784904 188;
           goto _L4 _L5
_L4:
        switch (byte0)
        {
        default:
            obj = ProviderManager.getIQProvider(s3, s4);
            if (obj != null)
            {
                obj = (IQ)((IQProvider) (obj)).parse(xmlpullparser);
            } else
            {
                obj = new UnparsedIQ(s3, s4, parseElement(xmlpullparser));
            }
            break;

        case 0: // '\0'
            xmpperror = parseError(xmlpullparser);
            break;
        }
          goto _L1
_L5:
        if (s3.equals("error"))
        {
            byte0 = 0;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() != i) goto _L1; else goto _L6
_L6:
        xmlpullparser = ((XmlPullParser) (obj));
        if (obj != null) goto _L8; else goto _L7
_L7:
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type = new int[org.jivesoftware.smack.packet.IQ.Type.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[org.jivesoftware.smack.packet.IQ.Type.error.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[org.jivesoftware.smack.packet.IQ.Type.result.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.org.jivesoftware.smack.packet.IQ.Type[type.ordinal()];
        JVM INSTR tableswitch 1 2: default 280
    //                   1 312
    //                   2 324;
           goto _L9 _L10 _L11
_L9:
        xmlpullparser = ((XmlPullParser) (obj));
_L8:
        xmlpullparser.setStanzaId(s);
        xmlpullparser.setTo(s1);
        xmlpullparser.setFrom(s2);
        xmlpullparser.setType(type);
        xmlpullparser.setError(xmpperror);
        return xmlpullparser;
_L10:
        xmlpullparser = new ErrorIQ(xmpperror);
          goto _L12
_L11:
        xmlpullparser = new EmptyResultIQ();
_L12:
        if (true) goto _L8; else goto _L1
_L1:
        if (true) goto _L14; else goto _L13
_L13:
    }

    public static Collection parseMechanisms(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        ArrayList arraylist = new ArrayList();
        boolean flag = false;
        do
        {
            if (flag)
            {
                break;
            }
            int i = xmlpullparser.next();
            if (i == 2)
            {
                if (xmlpullparser.getName().equals("mechanism"))
                {
                    arraylist.add(xmlpullparser.nextText());
                }
            } else
            if (i == 3 && xmlpullparser.getName().equals("mechanisms"))
            {
                flag = true;
            }
        } while (true);
        return arraylist;
    }

    public static Message parseMessage(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        String s;
        String s1;
        String s2;
        Message message;
        byte byte0;
        int i;
        ParserUtils.assertAtStartTag(xmlpullparser);
        if (!$assertionsDisabled && !xmlpullparser.getName().equals("message"))
        {
            throw new AssertionError();
        }
        i = xmlpullparser.getDepth();
        message = new Message();
        message.setStanzaId(xmlpullparser.getAttributeValue("", "id"));
        message.setTo(xmlpullparser.getAttributeValue("", "to"));
        message.setFrom(xmlpullparser.getAttributeValue("", "from"));
        s = xmlpullparser.getAttributeValue("", "type");
        if (s != null)
        {
            message.setType(org.jivesoftware.smack.packet.Message.Type.fromString(s));
        }
        s = getLanguageAttribute(xmlpullparser);
        String s5;
        if (s != null && !"".equals(s.trim()))
        {
            message.setLanguage(s);
        } else
        {
            s = Stanza.getDefaultLanguage();
        }
        s1 = null;
_L10:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 180
    //                   2 183
    //                   3 475;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L3:
        continue; /* Loop/switch isn't completed */
_L2:
        s2 = xmlpullparser.getName();
        s5 = xmlpullparser.getNamespace();
        byte0 = -1;
        s2.hashCode();
        JVM INSTR lookupswitch 4: default 248
    //                   -1867885268: 299
    //                   -874443254: 331
    //                   3029410: 315
    //                   96784904: 347;
           goto _L4 _L5 _L6 _L7 _L8
_L4:
        switch (byte0)
        {
        default:
            addExtensionElement(message, xmlpullparser, s2, s5);
            break;

        case 0: // '\0'
            String s6 = getLanguageAttribute(xmlpullparser);
            String s3 = s6;
            if (s6 == null)
            {
                s3 = s;
            }
            s6 = parseElementText(xmlpullparser);
            if (message.getSubject(s3) == null)
            {
                message.addSubject(s3, s6);
            }
            break;

        case 1: // '\001'
            String s7 = getLanguageAttribute(xmlpullparser);
            String s4 = s7;
            if (s7 == null)
            {
                s4 = s;
            }
            s7 = parseElementText(xmlpullparser);
            if (message.getBody(s4) == null)
            {
                message.addBody(s4, s7);
            }
            break;

        case 2: // '\002'
            if (s1 == null)
            {
                s1 = xmlpullparser.nextText();
            }
            break;

        case 3: // '\003'
            message.setError(parseError(xmlpullparser));
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (s2.equals("subject"))
        {
            byte0 = 0;
        }
          goto _L4
_L7:
        if (s2.equals("body"))
        {
            byte0 = 1;
        }
          goto _L4
_L6:
        if (s2.equals("thread"))
        {
            byte0 = 2;
        }
          goto _L4
_L8:
        if (s2.equals("error"))
        {
            byte0 = 3;
        }
          goto _L4
        if (true) goto _L10; else goto _L9
_L9:
        if (xmlpullparser.getDepth() != i) goto _L10; else goto _L11
_L11:
        message.setThread(s1);
        return message;
    }

    public static ExtensionElement parsePacketExtension(String s, String s1, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        return parseExtensionElement(s, s1, xmlpullparser);
    }

    public static Presence parsePresence(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        Object obj;
        int i;
        ParserUtils.assertAtStartTag(xmlpullparser);
        i = xmlpullparser.getDepth();
        Object obj1 = org.jivesoftware.smack.packet.Presence.Type.available;
        String s2 = xmlpullparser.getAttributeValue("", "type");
        obj = obj1;
        if (s2 != null)
        {
            obj = obj1;
            if (!s2.equals(""))
            {
                obj = org.jivesoftware.smack.packet.Presence.Type.fromString(s2);
            }
        }
        obj = new Presence(((org.jivesoftware.smack.packet.Presence.Type) (obj)));
        ((Presence) (obj)).setTo(xmlpullparser.getAttributeValue("", "to"));
        ((Presence) (obj)).setFrom(xmlpullparser.getAttributeValue("", "from"));
        ((Presence) (obj)).setStanzaId(xmlpullparser.getAttributeValue("", "id"));
        obj1 = getLanguageAttribute(xmlpullparser);
        if (obj1 != null && !"".equals(((String) (obj1)).trim()))
        {
            ((Presence) (obj)).setLanguage(((String) (obj1)));
        }
_L1:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 160
    //                   2 163
    //                   3 267;
           goto _L1 _L2 _L3
_L2:
        String s;
        String s3;
        byte byte0;
        s = xmlpullparser.getName();
        s3 = xmlpullparser.getNamespace();
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 4: default 228
    //                   -1165461084: 296
    //                   -892481550: 280
    //                   3529469: 312
    //                   96784904: 328;
           goto _L4 _L5 _L6 _L7 _L8
_L4:
        byte0;
        JVM INSTR tableswitch 0 3: default 260
    //                   0 344
    //                   1 357
    //                   2 373
    //                   3 449;
           goto _L9 _L10 _L11 _L12 _L13
_L9:
        try
        {
            addExtensionElement(((Stanza) (obj)), xmlpullparser, s, s3);
        }
        catch (Exception exception)
        {
            LOGGER.log(Level.WARNING, "Failed to parse extension packet in Presence packet.", exception);
        }
_L3:
        if (xmlpullparser.getDepth() != i) goto _L1; else goto _L14
_L14:
        return ((Presence) (obj));
_L6:
        if (s.equals("status"))
        {
            byte0 = 0;
        }
          goto _L4
_L5:
        if (s.equals("priority"))
        {
            byte0 = 1;
        }
          goto _L4
_L7:
        if (s.equals("show"))
        {
            byte0 = 2;
        }
          goto _L4
_L8:
        if (s.equals("error"))
        {
            byte0 = 3;
        }
          goto _L4
_L10:
        ((Presence) (obj)).setStatus(xmlpullparser.nextText());
          goto _L3
_L11:
        ((Presence) (obj)).setPriority(Integer.parseInt(xmlpullparser.nextText()));
          goto _L3
_L12:
        String s1 = xmlpullparser.nextText();
        if (StringUtils.isNotEmpty(s1))
        {
            ((Presence) (obj)).setMode(org.jivesoftware.smack.packet.Presence.Mode.fromString(s1));
        } else
        {
            LOGGER.warning((new StringBuilder()).append("Empty or null mode text in presence show element form ").append(((Presence) (obj)).getFrom()).append(" with id '").append(((Presence) (obj)).getStanzaId()).append("' which is invalid according to RFC6121 4.7.2.1").toString());
        }
          goto _L3
_L13:
        ((Presence) (obj)).setError(parseError(xmlpullparser));
          goto _L3
    }

    public static org.jivesoftware.smack.sasl.packet.SaslStreamElements.SASLFailure parseSASLFailure(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
        String s = null;
        Map map = null;
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
                    if (xmlpullparser.getName().equals("text"))
                    {
                        map = parseDescriptiveTexts(xmlpullparser, map);
                    } else
                    {
                        if (!$assertionsDisabled && s != null)
                        {
                            throw new AssertionError();
                        }
                        s = xmlpullparser.getName();
                    }
                    break;
                }
            } while (true);
        } while (xmlpullparser.getDepth() != i);
        return new org.jivesoftware.smack.sasl.packet.SaslStreamElements.SASLFailure(s, map);
    }

    public static org.jivesoftware.smack.packet.Session.Feature parseSessionFeature(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i;
        boolean flag;
        boolean flag1;
        ParserUtils.assertAtStartTag(xmlpullparser);
        i = xmlpullparser.getDepth();
        flag = false;
        flag1 = false;
        if (xmlpullparser.isEmptyElementTag()) goto _L2; else goto _L1
_L1:
        flag = flag1;
_L10:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 60
    //                   2 63
    //                   3 140;
           goto _L3 _L4 _L5
_L3:
        continue; /* Loop/switch isn't completed */
_L4:
        String s;
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR tableswitch -79017120 -79017120: default 96
    //                   -79017120 125;
           goto _L6 _L7
_L6:
        switch (byte0)
        {
        case 0: // '\0'
            flag = true;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (s.equals("optional"))
        {
            byte0 = 0;
        }
        if (true) goto _L6; else goto _L8
_L8:
        break; /* Loop/switch isn't completed */
_L5:
        if (xmlpullparser.getDepth() != i)
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return new org.jivesoftware.smack.packet.Session.Feature(flag);
        if (true) goto _L10; else goto _L9
_L9:
    }

    public static Stanza parseStanza(String s)
        throws XmlPullParserException, IOException, SmackException
    {
        return parseStanza(getParserFor(s));
    }

    public static Stanza parseStanza(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        String s;
        byte byte0;
        ParserUtils.assertAtStartTag(xmlpullparser);
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 3: default 52
    //                   -1276666629: 138
    //                   3368: 123
    //                   954925063: 108;
           goto _L1 _L2 _L3 _L4
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break MISSING_BLOCK_LABEL_138;
_L5:
        switch (byte0)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Can only parse message, iq or presence, not ").append(s).toString());

        case 0: // '\0'
            return parseMessage(xmlpullparser);

        case 1: // '\001'
            return parseIQ(xmlpullparser);

        case 2: // '\002'
            return parsePresence(xmlpullparser);
        }
_L4:
        if (s.equals("message"))
        {
            byte0 = 0;
        }
          goto _L5
_L3:
        if (s.equals("iq"))
        {
            byte0 = 1;
        }
          goto _L5
        if (s.equals("presence"))
        {
            byte0 = 2;
        }
          goto _L5
    }

    public static StartTls parseStartTlsFeature(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i;
        boolean flag;
        if (!$assertionsDisabled && xmlpullparser.getEventType() != 2)
        {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && !xmlpullparser.getNamespace().equals("urn:ietf:params:xml:ns:xmpp-tls"))
        {
            throw new AssertionError();
        }
        i = xmlpullparser.getDepth();
        flag = false;
_L7:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 92
    //                   2 95
    //                   3 172;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR tableswitch -393139297 -393139297: default 128
    //                   -393139297 157;
           goto _L4 _L5
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            flag = true;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (s.equals("required"))
        {
            byte0 = 0;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            if (!$assertionsDisabled && xmlpullparser.getEventType() != 3)
            {
                throw new AssertionError();
            } else
            {
                return new StartTls(flag);
            }
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static StreamError parseStreamError(XmlPullParser xmlpullparser)
        throws IOException, XmlPullParserException, SmackException
    {
        Map map;
        Object obj;
        String s;
        ArrayList arraylist;
        int i;
        i = xmlpullparser.getDepth();
        arraylist = new ArrayList();
        map = null;
        obj = null;
        s = null;
_L15:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 52
    //                   2 55
    //                   3 254;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L3:
        continue; /* Loop/switch isn't completed */
_L2:
        Object obj1;
        String s1;
        obj1 = xmlpullparser.getName();
        s1 = xmlpullparser.getNamespace();
        s1.hashCode();
        JVM INSTR tableswitch 904188284 904188284: default 96
    //                   904188284 133;
           goto _L4 _L5
_L4:
        byte byte0 = -1;
_L9:
        byte0;
        JVM INSTR tableswitch 0 0: default 120
    //                   0 150;
           goto _L6 _L7
_L6:
        addExtensionElement(arraylist, xmlpullparser, ((String) (obj1)), s1);
        continue; /* Loop/switch isn't completed */
_L5:
        if (!s1.equals("urn:ietf:params:xml:ns:xmpp-streams")) goto _L4; else goto _L8
_L8:
        byte0 = 0;
          goto _L9
          goto _L4
_L7:
        ((String) (obj1)).hashCode();
        JVM INSTR tableswitch 3556653 3556653: default 172
    //                   3556653 228;
           goto _L10 _L11
_L10:
        byte0 = -1;
_L13:
        switch (byte0)
        {
        default:
            obj1 = org.jivesoftware.smack.packet.StreamError.Condition.fromString(((String) (obj1)));
            obj = obj1;
            if (!xmlpullparser.isEmptyElementTag())
            {
                s = xmlpullparser.nextText();
                obj = obj1;
            }
            break;

        case 0: // '\0'
            map = parseDescriptiveTexts(xmlpullparser, map);
            break;
        }
        continue; /* Loop/switch isn't completed */
_L11:
        if (!((String) (obj1)).equals("text")) goto _L10; else goto _L12
_L12:
        byte0 = 0;
          goto _L13
        if (true) goto _L15; else goto _L14
_L14:
        if (xmlpullparser.getDepth() != i) goto _L15; else goto _L16
_L16:
        return new StreamError(((org.jivesoftware.smack.packet.StreamError.Condition) (obj)), s, map, arraylist);
    }

    static 
    {
        boolean flag = true;
        XmlPullParser xmlpullparser;
        if (org/jivesoftware/smack/util/PacketParserUtils.desiredAssertionStatus())
        {
            flag = false;
        }
        $assertionsDisabled = flag;
        LOGGER = Logger.getLogger(org/jivesoftware/smack/util/PacketParserUtils.getName());
        flag = false;
        XmlPullParserException xmlpullparserexception;
        try
        {
            XML_PULL_PARSER_FACTORY = XmlPullParserFactory.newInstance();
            xmlpullparser = XML_PULL_PARSER_FACTORY.newPullParser();
        }
        catch (XmlPullParserException xmlpullparserexception1)
        {
            throw new AssertionError(xmlpullparserexception1);
        }
        xmlpullparser.setFeature("http://xmlpull.org/v1/doc/features.html#xml-roundtrip", true);
        flag = true;
_L1:
        XML_PULL_PARSER_SUPPORTS_ROUNDTRIP = flag;
        return;
        xmlpullparserexception;
        LOGGER.log(Level.FINEST, "XmlPullParser does not support XML_ROUNDTRIP", xmlpullparserexception);
          goto _L1
    }
}
