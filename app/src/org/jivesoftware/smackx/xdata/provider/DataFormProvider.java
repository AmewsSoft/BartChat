// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdata.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.roster.provider.RosterPacketProvider;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jivesoftware.smackx.xdatalayout.provider.DataLayoutProvider;
import org.jivesoftware.smackx.xdatavalidation.provider.DataValidationProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DataFormProvider extends ExtensionElementProvider
{

    public DataFormProvider()
    {
    }

    private FormField parseField(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        Object obj;
        Object obj1;
        String s;
        byte byte0;
        int i = xmlpullparser.getDepth();
        obj = xmlpullparser.getAttributeValue("", "var");
        obj1 = org.jivesoftware.smackx.xdata.FormField.Type.fromString(xmlpullparser.getAttributeValue("", "type"));
        if (obj1 == org.jivesoftware.smackx.xdata.FormField.Type.fixed)
        {
            obj = new FormField();
        } else
        {
            obj = new FormField(((String) (obj)));
            ((FormField) (obj)).setType(((org.jivesoftware.smackx.xdata.FormField.Type) (obj1)));
        }
        ((FormField) (obj)).setLabel(xmlpullparser.getAttributeValue("", "label"));
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 92
    //                   2 95
    //                   3 204;
           goto _L1 _L2 _L3
_L1:
        break MISSING_BLOCK_LABEL_62;
_L2:
        obj1 = xmlpullparser.getName();
        s = xmlpullparser.getNamespace();
        byte0 = -1;
        ((String) (obj1)).hashCode();
        JVM INSTR lookupswitch 5: default 168
    //                   -1421272810: 294
    //                   -1010136971: 279
    //                   -393139297: 264
    //                   3079825: 234
    //                   111972721: 249;
           goto _L4 _L5 _L6 _L7 _L8 _L9
_L4:
        byte0;
        JVM INSTR tableswitch 0 4: default 204
    //                   0 309
    //                   1 322
    //                   2 335
    //                   3 343
    //                   4 355;
           goto _L3 _L10 _L11 _L12 _L13 _L14
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            return ((FormField) (obj));
        } else
        {
            break MISSING_BLOCK_LABEL_62;
        }
_L8:
        if (((String) (obj1)).equals("desc"))
        {
            byte0 = 0;
        }
          goto _L4
_L9:
        if (((String) (obj1)).equals("value"))
        {
            byte0 = 1;
        }
          goto _L4
_L7:
        if (((String) (obj1)).equals("required"))
        {
            byte0 = 2;
        }
          goto _L4
_L6:
        if (((String) (obj1)).equals("option"))
        {
            byte0 = 3;
        }
          goto _L4
_L5:
        if (((String) (obj1)).equals("validate"))
        {
            byte0 = 4;
        }
          goto _L4
_L10:
        ((FormField) (obj)).setDescription(xmlpullparser.nextText());
          goto _L3
_L11:
        ((FormField) (obj)).addValue(xmlpullparser.nextText());
          goto _L3
_L12:
        ((FormField) (obj)).setRequired(true);
          goto _L3
_L13:
        ((FormField) (obj)).addOption(parseOption(xmlpullparser));
          goto _L3
_L14:
        if (s.equals("http://jabber.org/protocol/xdata-validate"))
        {
            ((FormField) (obj)).setValidateElement(DataValidationProvider.parse(xmlpullparser));
        }
          goto _L3
    }

    private org.jivesoftware.smackx.xdata.packet.DataForm.Item parseItem(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        ArrayList arraylist;
        int i;
        i = xmlpullparser.getDepth();
        arraylist = new ArrayList();
_L7:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 44
    //                   2 47
    //                   3 133;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR tableswitch 97427706 97427706: default 80
    //                   97427706 118;
           goto _L4 _L5
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            arraylist.add(parseField(xmlpullparser));
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (s.equals("field"))
        {
            byte0 = 0;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            return new org.jivesoftware.smackx.xdata.packet.DataForm.Item(arraylist);
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    private org.jivesoftware.smackx.xdata.FormField.Option parseOption(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        org.jivesoftware.smackx.xdata.FormField.Option option;
        String s;
        int i;
        i = xmlpullparser.getDepth();
        option = null;
        s = xmlpullparser.getAttributeValue("", "label");
_L7:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 48
    //                   2 51
    //                   3 141;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s1;
        byte byte0;
        s1 = xmlpullparser.getName();
        byte0 = -1;
        s1.hashCode();
        JVM INSTR tableswitch 111972721 111972721: default 84
    //                   111972721 125;
           goto _L4 _L5
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            option = new org.jivesoftware.smackx.xdata.FormField.Option(s, xmlpullparser.nextText());
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (s1.equals("value"))
        {
            byte0 = 0;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            return option;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    private org.jivesoftware.smackx.xdata.packet.DataForm.ReportedData parseReported(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        ArrayList arraylist;
        int i;
        i = xmlpullparser.getDepth();
        arraylist = new ArrayList();
_L7:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 44
    //                   2 47
    //                   3 133;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR tableswitch 97427706 97427706: default 80
    //                   97427706 118;
           goto _L4 _L5
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            arraylist.add(parseField(xmlpullparser));
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (s.equals("field"))
        {
            byte0 = 0;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            return new org.jivesoftware.smackx.xdata.packet.DataForm.ReportedData(arraylist);
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public DataForm parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        DataForm dataform = new DataForm(org.jivesoftware.smackx.xdata.packet.DataForm.Type.fromString(xmlpullparser.getAttributeValue("", "type")));
_L13:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 48
    //                   2 51
    //                   3 407;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L3:
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        String s1;
        byte byte0;
        s = xmlpullparser.getName();
        s1 = xmlpullparser.getNamespace();
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 7: default 140
    //                   -427039533: 264
    //                   3242771: 248
    //                   3433103: 296
    //                   97427706: 232
    //                   107944136: 280
    //                   110371416: 216
    //                   757376421: 200;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            dataform.addInstruction(xmlpullparser.nextText());
            break;

        case 1: // '\001'
            dataform.setTitle(xmlpullparser.nextText());
            break;

        case 2: // '\002'
            dataform.addField(parseField(xmlpullparser));
            break;

        case 3: // '\003'
            dataform.addItem(parseItem(xmlpullparser));
            break;

        case 4: // '\004'
            dataform.setReportedData(parseReported(xmlpullparser));
            break;

        case 5: // '\005'
            if (s1.equals("jabber:iq:roster"))
            {
                dataform.addExtensionElement(RosterPacketProvider.INSTANCE.parse(xmlpullparser));
            }
            break;

        case 6: // '\006'
            if (s1.equals("http://jabber.org/protocol/xdata-layout"))
            {
                dataform.addExtensionElement(DataLayoutProvider.parse(xmlpullparser));
            }
            break;
        }
        continue; /* Loop/switch isn't completed */
_L11:
        if (s.equals("instructions"))
        {
            byte0 = 0;
        }
          goto _L4
_L10:
        if (s.equals("title"))
        {
            byte0 = 1;
        }
          goto _L4
_L8:
        if (s.equals("field"))
        {
            byte0 = 2;
        }
          goto _L4
_L6:
        if (s.equals("item"))
        {
            byte0 = 3;
        }
          goto _L4
_L5:
        if (s.equals("reported"))
        {
            byte0 = 4;
        }
          goto _L4
_L9:
        if (s.equals("query"))
        {
            byte0 = 5;
        }
          goto _L4
_L7:
        if (s.equals("page"))
        {
            byte0 = 6;
        }
          goto _L4
        if (true) goto _L13; else goto _L12
_L12:
        if (xmlpullparser.getDepth() != i) goto _L13; else goto _L14
_L14:
        return dataform;
    }
}
