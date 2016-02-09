// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdatavalidation.provider;

import java.io.IOException;
import java.util.logging.Logger;
import org.jivesoftware.smack.util.ParserUtils;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DataValidationProvider
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/xdatavalidation/provider/DataValidationProvider.getName());

    public DataValidationProvider()
    {
    }

    public static ValidateElement parse(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        Object obj;
        org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.ListRange listrange;
        String s;
        int i;
        i = xmlpullparser.getDepth();
        s = xmlpullparser.getAttributeValue("", "datatype");
        obj = null;
        listrange = null;
_L11:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 52
    //                   2 55
    //                   3 365;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s1;
        byte byte0;
        s1 = xmlpullparser.getName();
        byte0 = -1;
        s1.hashCode();
        JVM INSTR lookupswitch 5: default 120
    //                   -725250226: 235
    //                   3417674: 171
    //                   93508654: 187
    //                   108280125: 203
    //                   108392519: 219;
           goto _L4 _L5 _L6 _L7 _L8 _L9
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            obj = new org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.OpenValidateElement(s);
            break;

        case 1: // '\001'
            obj = new org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.BasicValidateElement(s);
            break;

        case 2: // '\002'
            obj = new org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.RangeValidateElement(s, xmlpullparser.getAttributeValue("", "min"), xmlpullparser.getAttributeValue("", "max"));
            break;

        case 3: // '\003'
            obj = new org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.RegexValidateElement(s, xmlpullparser.nextText());
            break;

        case 4: // '\004'
            Long long1 = ParserUtils.getLongAttribute(xmlpullparser, "min");
            Long long2 = ParserUtils.getLongAttribute(xmlpullparser, "max");
            if (long1 != null || long2 != null)
            {
                listrange = new org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.ListRange(long1, long2);
            } else
            {
                LOGGER.fine("Ignoring list-range element without min or max attribute");
            }
            break;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (s1.equals("open"))
        {
            byte0 = 0;
        }
          goto _L4
_L7:
        if (s1.equals("basic"))
        {
            byte0 = 1;
        }
          goto _L4
_L8:
        if (s1.equals("range"))
        {
            byte0 = 2;
        }
          goto _L4
_L9:
        if (s1.equals("regex"))
        {
            byte0 = 3;
        }
          goto _L4
_L5:
        if (s1.equals("list-range"))
        {
            byte0 = 4;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            xmlpullparser = ((XmlPullParser) (obj));
            if (obj == null)
            {
                xmlpullparser = new org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.BasicValidateElement(s);
            }
            xmlpullparser.setListRange(listrange);
            return xmlpullparser;
        }
        if (true) goto _L11; else goto _L10
_L10:
    }

}
