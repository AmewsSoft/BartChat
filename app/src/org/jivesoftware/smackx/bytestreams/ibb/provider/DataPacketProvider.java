// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.ibb.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DataPacketProvider
{
    public static class IQProvider extends org.jivesoftware.smack.provider.IQProvider
    {

        private static final PacketExtensionProvider packetExtensionProvider = new PacketExtensionProvider();

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public Data parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return new Data((DataPacketExtension)packetExtensionProvider.parse(xmlpullparser));
        }


        public IQProvider()
        {
        }
    }

    public static class PacketExtensionProvider extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public DataPacketExtension parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException
        {
            return new DataPacketExtension(xmlpullparser.getAttributeValue("", "sid"), Long.parseLong(xmlpullparser.getAttributeValue("", "seq")), xmlpullparser.nextText());
        }

        public PacketExtensionProvider()
        {
        }
    }


    public DataPacketProvider()
    {
    }
}
