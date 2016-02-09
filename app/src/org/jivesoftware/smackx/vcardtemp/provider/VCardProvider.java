// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.vcardtemp.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VCardProvider extends IQProvider
{

    private static final String ADR[] = {
        "POSTAL", "PARCEL", "DOM", "INTL", "PREF", "POBOX", "EXTADR", "STREET", "LOCALITY", "REGION", 
        "PCODE", "CTRY", "FF"
    };
    private static final String TEL[] = {
        "VOICE", "FAX", "PAGER", "MSG", "CELL", "VIDEO", "BBS", "MODEM", "ISDN", "PCS", 
        "PREF"
    };

    public VCardProvider()
    {
    }

    private static void parseAddress(XmlPullParser xmlpullparser, VCard vcard)
        throws XmlPullParserException, IOException
    {
        int j = xmlpullparser.getDepth();
        boolean flag = true;
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
                    if ("HOME".equals(s))
                    {
                        flag = false;
                    } else
                    {
                        String as[] = ADR;
                        int k = as.length;
                        int i = 0;
                        while (i < k) 
                        {
                            if (as[i].equals(s))
                            {
                                if (flag)
                                {
                                    vcard.setAddressFieldWork(s, xmlpullparser.nextText());
                                } else
                                {
                                    vcard.setAddressFieldHome(s, xmlpullparser.nextText());
                                }
                            }
                            i++;
                        }
                    }
                    break;
                }
            } while (true);
        } while (xmlpullparser.getDepth() != j);
    }

    private static void parseEmail(XmlPullParser xmlpullparser, VCard vcard)
        throws XmlPullParserException, IOException
    {
        byte byte1;
        int i;
        i = xmlpullparser.getDepth();
        byte1 = 0;
_L1:
        byte byte0 = byte1;
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 44
    //                   2 47
    //                   3 115;
           goto _L1 _L2 _L3
_L2:
        String s;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 2: default 88
    //                   -1782700506: 144
    //                   2670353: 130;
           goto _L4 _L5 _L6
_L4:
        byte0;
        JVM INSTR tableswitch 0 1: default 112
    //                   0 158
    //                   1 163;
           goto _L7 _L8 _L9
_L7:
        byte0 = byte1;
_L3:
        byte1 = byte0;
        if (xmlpullparser.getDepth() == i)
        {
            return;
        }
          goto _L1
_L6:
        if (s.equals("WORK"))
        {
            byte0 = 0;
        }
          goto _L4
_L5:
        if (s.equals("USERID"))
        {
            byte0 = 1;
        }
          goto _L4
_L8:
        byte0 = 1;
          goto _L3
_L9:
        if (byte1 != 0)
        {
            vcard.setEmailWork(xmlpullparser.nextText());
            byte0 = byte1;
        } else
        {
            vcard.setEmailHome(xmlpullparser.nextText());
            byte0 = byte1;
        }
          goto _L3
    }

    private static void parseName(XmlPullParser xmlpullparser, VCard vcard)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
_L9:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 36
    //                   2 39
    //                   3 200;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 3: default 88
    //                   -2021012075: 160
    //                   67829597: 146
    //                   2066435940: 132;
           goto _L4 _L5 _L6 _L7
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            vcard.setLastName(xmlpullparser.nextText());
            break;

        case 1: // '\001'
            vcard.setFirstName(xmlpullparser.nextText());
            break;

        case 2: // '\002'
            vcard.setMiddleName(xmlpullparser.nextText());
            break;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (s.equals("FAMILY"))
        {
            byte0 = 0;
        }
          goto _L4
_L6:
        if (s.equals("GIVEN"))
        {
            byte0 = 1;
        }
          goto _L4
_L5:
        if (s.equals("MIDDLE"))
        {
            byte0 = 2;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L9; else goto _L8
_L8:
    }

    private static void parseOrg(XmlPullParser xmlpullparser, VCard vcard)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
_L8:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 36
    //                   2 39
    //                   3 161;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 2: default 80
    //                   -486104241: 120
    //                   -485883320: 134;
           goto _L4 _L5 _L6
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            vcard.setOrganization(xmlpullparser.nextText());
            break;

        case 1: // '\001'
            vcard.setOrganizationUnit(xmlpullparser.nextText());
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (s.equals("ORGNAME"))
        {
            byte0 = 0;
        }
          goto _L4
_L6:
        if (s.equals("ORGUNIT"))
        {
            byte0 = 1;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    private static void parsePhoto(XmlPullParser xmlpullparser, VCard vcard)
        throws XmlPullParserException, IOException
    {
        String s;
        String s1;
        int i;
        i = xmlpullparser.getDepth();
        s1 = null;
        s = null;
_L8:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 40
    //                   2 43
    //                   3 163;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        String s2;
        byte byte0;
        s2 = xmlpullparser.getName();
        byte0 = -1;
        s2.hashCode();
        JVM INSTR lookupswitch 2: default 84
    //                   2590522: 137
    //                   1959349434: 121;
           goto _L4 _L5 _L6
_L4:
        switch (byte0)
        {
        case 0: // '\0'
            s1 = xmlpullparser.nextText();
            break;

        case 1: // '\001'
            s = xmlpullparser.nextText();
            break;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (s2.equals("BINVAL"))
        {
            byte0 = 0;
        }
          goto _L4
_L5:
        if (s2.equals("TYPE"))
        {
            byte0 = 1;
        }
          goto _L4
_L3:
        if (xmlpullparser.getDepth() != i)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (s1 == null || s == null)
        {
            return;
        }
        vcard.setAvatar(s1, s);
        break; /* Loop/switch isn't completed */
        if (true) goto _L8; else goto _L7
_L7:
    }

    private static void parseTel(XmlPullParser xmlpullparser, VCard vcard)
        throws XmlPullParserException, IOException
    {
        String s;
        boolean flag;
        int j;
        j = xmlpullparser.getDepth();
        flag = true;
        s = null;
_L5:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 3: default 40
    //                   2 43
    //                   3 167;
           goto _L1 _L2 _L3
_L1:
        continue; /* Loop/switch isn't completed */
_L3:
        continue; /* Loop/switch isn't completed */
_L2:
        String s3;
        s3 = xmlpullparser.getName();
        if ("HOME".equals(s3))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (s == null || !"NUMBER".equals(s3))
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag)
        {
            vcard.setPhoneWork(s, xmlpullparser.nextText());
        } else
        {
            vcard.setPhoneHome(s, xmlpullparser.nextText());
        }
        if (true) goto _L5; else goto _L4
_L4:
        String s2;
        String as[];
        int i;
        int k;
        as = TEL;
        k = as.length;
        i = 0;
        s2 = s;
_L7:
        s = s2;
        if (i >= k) goto _L5; else goto _L6
_L6:
        String s1 = s2;
        if (as[i].equals(s3))
        {
            s1 = s3;
        }
        i++;
        s2 = s1;
          goto _L7
        if (xmlpullparser.getDepth() != j) goto _L5; else goto _L8
_L8:
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public VCard parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        String s;
        VCard vcard;
        vcard = new VCard();
        s = null;
_L15:
        xmlpullparser.next();
        JVM INSTR tableswitch 2 4: default 44
    //                   2 47
    //                   3 418
    //                   4 391;
           goto _L1 _L2 _L3 _L4
_L1:
        continue; /* Loop/switch isn't completed */
_L2:
        byte byte0;
        s = xmlpullparser.getName();
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 8: default 136
    //                   -370243905: 286
    //                   78: 196
    //                   64655: 241
    //                   78532: 211
    //                   82939: 226
    //                   66081660: 256
    //                   76105234: 302
    //                   853317742: 271;
           goto _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L5:
        switch (byte0)
        {
        case 0: // '\0'
            parseName(xmlpullparser, vcard);
            break;

        case 1: // '\001'
            parseOrg(xmlpullparser, vcard);
            break;

        case 2: // '\002'
            parseTel(xmlpullparser, vcard);
            break;

        case 3: // '\003'
            parseAddress(xmlpullparser, vcard);
            break;

        case 4: // '\004'
            parseEmail(xmlpullparser, vcard);
            break;

        case 5: // '\005'
            vcard.setNickName(xmlpullparser.nextText());
            break;

        case 6: // '\006'
            vcard.setJabberId(xmlpullparser.nextText());
            break;

        case 7: // '\007'
            parsePhoto(xmlpullparser, vcard);
            break;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (s.equals("N"))
        {
            byte0 = 0;
        }
          goto _L5
_L9:
        if (s.equals("ORG"))
        {
            byte0 = 1;
        }
          goto _L5
_L10:
        if (s.equals("TEL"))
        {
            byte0 = 2;
        }
          goto _L5
_L8:
        if (s.equals("ADR"))
        {
            byte0 = 3;
        }
          goto _L5
_L11:
        if (s.equals("EMAIL"))
        {
            byte0 = 4;
        }
          goto _L5
_L13:
        if (s.equals("NICKNAME"))
        {
            byte0 = 5;
        }
          goto _L5
_L6:
        if (s.equals("JABBERID"))
        {
            byte0 = 6;
        }
          goto _L5
_L12:
        if (s.equals("PHOTO"))
        {
            byte0 = 7;
        }
          goto _L5
_L4:
        if (i + 1 == xmlpullparser.getDepth())
        {
            vcard.setField(s, xmlpullparser.getText());
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (xmlpullparser.getDepth() == i)
        {
            return vcard;
        }
        if (true) goto _L15; else goto _L14
_L14:
    }

}
