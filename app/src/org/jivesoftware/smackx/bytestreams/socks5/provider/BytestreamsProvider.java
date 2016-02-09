// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class BytestreamsProvider extends IQProvider
{

    public BytestreamsProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public Bytestream parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        i = 0;
        Bytestream bytestream = new Bytestream();
        String s3 = xmlpullparser.getAttributeValue("", "sid");
        String s4 = xmlpullparser.getAttributeValue("", "mode");
        String s = null;
        String s1 = null;
        String s2 = null;
        do
        {
            if (i != 0)
            {
                break;
            }
            int j = xmlpullparser.next();
            String s5 = xmlpullparser.getName();
            if (j == 2)
            {
                if (s5.equals(org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost.ELEMENTNAME))
                {
                    s = xmlpullparser.getAttributeValue("", "jid");
                    s1 = xmlpullparser.getAttributeValue("", "host");
                    s2 = xmlpullparser.getAttributeValue("", "port");
                } else
                if (s5.equals(org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHostUsed.ELEMENTNAME))
                {
                    bytestream.setUsedHost(xmlpullparser.getAttributeValue("", "jid"));
                } else
                if (s5.equals(org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.Activate.ELEMENTNAME))
                {
                    bytestream.setToActivate(xmlpullparser.getAttributeValue("", "jid"));
                }
            } else
            if (j == 3)
            {
                if (s5.equals("streamhost"))
                {
                    if (s2 == null)
                    {
                        bytestream.addStreamHost(s, s1);
                    } else
                    {
                        bytestream.addStreamHost(s, s1, Integer.parseInt(s2));
                    }
                    s = null;
                    s1 = null;
                    s2 = null;
                } else
                if (s5.equals("query"))
                {
                    i = 1;
                }
            }
        } while (true);
        if (s4 == null)
        {
            bytestream.setMode(org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.Mode.tcp);
        } else
        {
            bytestream.setMode(org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.Mode.fromName(s4));
        }
        bytestream.setSessionID(s3);
        return bytestream;
    }
}
