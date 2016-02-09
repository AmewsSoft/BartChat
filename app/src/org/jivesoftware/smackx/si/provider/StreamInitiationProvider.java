// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.si.provider;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.si.packet.StreamInitiation;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jivesoftware.smackx.xdata.provider.DataFormProvider;
import org.jxmpp.util.XmppDateTime;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class StreamInitiationProvider extends IQProvider
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/si/provider/StreamInitiationProvider.getName());

    public StreamInitiationProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public StreamInitiation parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        i = 0;
        String s7 = xmlpullparser.getAttributeValue("", "id");
        String s8 = xmlpullparser.getAttributeValue("", "mime-type");
        StreamInitiation streaminitiation = new StreamInitiation();
        String s1 = null;
        String s2 = null;
        String s = null;
        String s4 = null;
        String s3 = null;
        boolean flag = false;
        DataForm dataform = null;
        DataFormProvider dataformprovider = new DataFormProvider();
        do
        {
            if (i == 0)
            {
                int j = xmlpullparser.next();
                String s5 = xmlpullparser.getName();
                String s6 = xmlpullparser.getNamespace();
                if (j == 2)
                {
                    if (s5.equals("file"))
                    {
                        s1 = xmlpullparser.getAttributeValue("", "name");
                        s2 = xmlpullparser.getAttributeValue("", "size");
                        s = xmlpullparser.getAttributeValue("", "hash");
                        s4 = xmlpullparser.getAttributeValue("", "date");
                    } else
                    if (s5.equals("desc"))
                    {
                        s3 = xmlpullparser.nextText();
                    } else
                    if (s5.equals("range"))
                    {
                        flag = true;
                    } else
                    if (s5.equals("x") && s6.equals("jabber:x:data"))
                    {
                        dataform = (DataForm)dataformprovider.parse(xmlpullparser);
                    }
                } else
                if (j == 3)
                {
                    if (s5.equals("si"))
                    {
                        i = 1;
                    } else
                    if (s5.equals("file"))
                    {
                        long l1 = 0L;
                        long l = l1;
                        Object obj;
                        Object obj1;
                        if (s2 != null)
                        {
                            l = l1;
                            if (s2.trim().length() != 0)
                            {
                                try
                                {
                                    l = Long.parseLong(s2);
                                }
                                catch (NumberFormatException numberformatexception)
                                {
                                    LOGGER.log(Level.SEVERE, (new StringBuilder()).append("Failed to parse file size from ").append(0L).toString(), numberformatexception);
                                    l = l1;
                                }
                            }
                        }
                        obj1 = new Date();
                        obj = obj1;
                        if (s4 != null)
                        {
                            try
                            {
                                obj = XmppDateTime.parseDate(s4);
                            }
                            catch (ParseException parseexception)
                            {
                                parseexception = ((ParseException) (obj1));
                            }
                        }
                        obj1 = new org.jivesoftware.smackx.si.packet.StreamInitiation.File(s1, l);
                        ((org.jivesoftware.smackx.si.packet.StreamInitiation.File) (obj1)).setHash(s);
                        ((org.jivesoftware.smackx.si.packet.StreamInitiation.File) (obj1)).setDate(((Date) (obj)));
                        ((org.jivesoftware.smackx.si.packet.StreamInitiation.File) (obj1)).setDesc(s3);
                        ((org.jivesoftware.smackx.si.packet.StreamInitiation.File) (obj1)).setRanged(flag);
                        streaminitiation.setFile(((org.jivesoftware.smackx.si.packet.StreamInitiation.File) (obj1)));
                    }
                }
            } else
            {
                streaminitiation.setSessionID(s7);
                streaminitiation.setMimeType(s8);
                streaminitiation.setFeatureNegotiationForm(dataform);
                return streaminitiation;
            }
        } while (true);
    }

}
