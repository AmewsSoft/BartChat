// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqregister.provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.iqregister.packet.Registration;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RegistrationProvider extends IQProvider
{

    public RegistrationProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public Registration parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        String s1 = null;
        HashMap hashmap = new HashMap();
        LinkedList linkedlist = new LinkedList();
        do
        {
            do
            {
                i = xmlpullparser.next();
                if (i != 2)
                {
                    break;
                }
                if (xmlpullparser.getNamespace().equals("jabber:iq:register"))
                {
                    String s2 = xmlpullparser.getName();
                    String s = "";
                    if (xmlpullparser.next() == 4)
                    {
                        s = xmlpullparser.getText();
                    }
                    if (!s2.equals("instructions"))
                    {
                        hashmap.put(s2, s);
                    } else
                    {
                        s1 = s;
                    }
                } else
                {
                    PacketParserUtils.addExtensionElement(linkedlist, xmlpullparser);
                }
            } while (true);
        } while (i != 3 || !xmlpullparser.getName().equals("query"));
        xmlpullparser = new Registration(s1, hashmap);
        xmlpullparser.addExtensions(linkedlist);
        return xmlpullparser;
    }
}
