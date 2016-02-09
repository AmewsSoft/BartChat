// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            FullStreamElement

public class StreamOpen extends FullStreamElement
{
    public static final class StreamContentNamespace extends Enum
    {

        private static final StreamContentNamespace $VALUES[];
        public static final StreamContentNamespace client;
        public static final StreamContentNamespace server;

        public static StreamContentNamespace valueOf(String s)
        {
            return (StreamContentNamespace)Enum.valueOf(org/jivesoftware/smack/packet/StreamOpen$StreamContentNamespace, s);
        }

        public static StreamContentNamespace[] values()
        {
            return (StreamContentNamespace[])$VALUES.clone();
        }

        static 
        {
            client = new StreamContentNamespace("client", 0);
            server = new StreamContentNamespace("server", 1);
            $VALUES = (new StreamContentNamespace[] {
                client, server
            });
        }

        private StreamContentNamespace(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String CLIENT_NAMESPACE = "jabber:client";
    public static final String ELEMENT = "stream:stream";
    public static final String SERVER_NAMESPACE = "jabber:server";
    public static final String VERSION = "1.0";
    private final String contentNamespace;
    private final String from;
    private final String id;
    private final String lang;
    private final String to;

    public StreamOpen(CharSequence charsequence)
    {
        this(charsequence, null, null, null, StreamContentNamespace.client);
    }

    public StreamOpen(CharSequence charsequence, CharSequence charsequence1, String s)
    {
        this(charsequence, charsequence1, s, "en", StreamContentNamespace.client);
    }

    public StreamOpen(CharSequence charsequence, CharSequence charsequence1, String s, String s1, StreamContentNamespace streamcontentnamespace)
    {
        to = StringUtils.maybeToString(charsequence);
        from = StringUtils.maybeToString(charsequence1);
        id = s;
        lang = s1;
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smack$packet$StreamOpen$StreamContentNamespace[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$packet$StreamOpen$StreamContentNamespace = new int[StreamContentNamespace.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$StreamOpen$StreamContentNamespace[StreamContentNamespace.client.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$StreamOpen$StreamContentNamespace[StreamContentNamespace.server.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.jivesoftware.smack.packet.StreamOpen.StreamContentNamespace[streamcontentnamespace.ordinal()])
        {
        default:
            throw new IllegalStateException();

        case 1: // '\001'
            contentNamespace = "jabber:client";
            return;

        case 2: // '\002'
            contentNamespace = "jabber:server";
            break;
        }
    }

    public String getElementName()
    {
        return "stream:stream";
    }

    public String getNamespace()
    {
        return contentNamespace;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("to", to);
        xmlstringbuilder.attribute("xmlns:stream", "http://etherx.jabber.org/streams");
        xmlstringbuilder.attribute("version", "1.0");
        xmlstringbuilder.optAttribute("from", from);
        xmlstringbuilder.optAttribute("id", id);
        xmlstringbuilder.xmllangAttribute(lang);
        xmlstringbuilder.rightAngleBracket();
        return xmlstringbuilder;
    }
}
