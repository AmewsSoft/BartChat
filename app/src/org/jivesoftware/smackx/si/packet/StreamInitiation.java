// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.si.packet;

import java.util.Date;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jxmpp.util.XmppDateTime;

public class StreamInitiation extends IQ
{
    public class Feature
        implements ExtensionElement
    {

        private final DataForm data;
        final StreamInitiation this$0;

        public DataForm getData()
        {
            return data;
        }

        public String getElementName()
        {
            return "feature";
        }

        public String getNamespace()
        {
            return "http://jabber.org/protocol/feature-neg";
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public String toXML()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("<feature xmlns=\"http://jabber.org/protocol/feature-neg\">");
            stringbuilder.append(data.toXML());
            stringbuilder.append("</feature>");
            return stringbuilder.toString();
        }

        public Feature(DataForm dataform)
        {
            this$0 = StreamInitiation.this;
            super();
            data = dataform;
        }
    }

    public static class File
        implements ExtensionElement
    {

        private Date date;
        private String desc;
        private String hash;
        private boolean isRanged;
        private final String name;
        private final long size;

        public Date getDate()
        {
            return date;
        }

        public String getDesc()
        {
            return desc;
        }

        public String getElementName()
        {
            return "file";
        }

        public String getHash()
        {
            return hash;
        }

        public String getName()
        {
            return name;
        }

        public String getNamespace()
        {
            return "http://jabber.org/protocol/si/profile/file-transfer";
        }

        public long getSize()
        {
            return size;
        }

        public boolean isRanged()
        {
            return isRanged;
        }

        public void setDate(Date date1)
        {
            date = date1;
        }

        public void setDesc(String s)
        {
            desc = s;
        }

        public void setHash(String s)
        {
            hash = s;
        }

        public void setRanged(boolean flag)
        {
            isRanged = flag;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public String toXML()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace()).append("\" ");
            if (getName() != null)
            {
                stringbuilder.append("name=\"").append(StringUtils.escapeForXML(getName())).append("\" ");
            }
            if (getSize() > 0L)
            {
                stringbuilder.append("size=\"").append(getSize()).append("\" ");
            }
            if (getDate() != null)
            {
                stringbuilder.append("date=\"").append(XmppDateTime.formatXEP0082Date(date)).append("\" ");
            }
            if (getHash() != null)
            {
                stringbuilder.append("hash=\"").append(getHash()).append("\" ");
            }
            if (desc != null && desc.length() > 0 || isRanged)
            {
                stringbuilder.append(">");
                if (getDesc() != null && desc.length() > 0)
                {
                    stringbuilder.append("<desc>").append(StringUtils.escapeForXML(getDesc())).append("</desc>");
                }
                if (isRanged())
                {
                    stringbuilder.append("<range/>");
                }
                stringbuilder.append("</").append(getElementName()).append(">");
            } else
            {
                stringbuilder.append("/>");
            }
            return stringbuilder.toString();
        }

        public File(String s, long l)
        {
            if (s == null)
            {
                throw new NullPointerException("name cannot be null");
            } else
            {
                name = s;
                size = l;
                return;
            }
        }
    }


    public static final String ELEMENT = "si";
    public static final String NAMESPACE = "http://jabber.org/protocol/si";
    private Feature featureNegotiation;
    private File file;
    private String id;
    private String mimeType;

    public StreamInitiation()
    {
        super("si", "http://jabber.org/protocol/si");
    }

    public DataForm getFeatureNegotiationForm()
    {
        return featureNegotiation.getData();
    }

    public File getFile()
    {
        return file;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type = new int[org.jivesoftware.smack.packet.IQ.Type.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[org.jivesoftware.smack.packet.IQ.Type.set.ordinal()] = 1;
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

        _cls1..SwitchMap.org.jivesoftware.smack.packet.IQ.Type[getType().ordinal()];
        JVM INSTR tableswitch 1 2: default 32
    //                   1 42
    //                   2 111;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalArgumentException("IQ Type not understood");
_L2:
        iqchildelementxmlstringbuilder.optAttribute("id", getSessionID());
        iqchildelementxmlstringbuilder.optAttribute("mime-type", getMimeType());
        iqchildelementxmlstringbuilder.attribute("profile", "http://jabber.org/protocol/si/profile/file-transfer");
        iqchildelementxmlstringbuilder.rightAngleBracket();
        iqchildelementxmlstringbuilder.optAppend(file.toXML());
_L5:
        if (featureNegotiation != null)
        {
            iqchildelementxmlstringbuilder.append(featureNegotiation.toXML());
        }
        return iqchildelementxmlstringbuilder;
_L3:
        iqchildelementxmlstringbuilder.rightAngleBracket();
        if (true) goto _L5; else goto _L4
_L4:
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public String getSessionID()
    {
        return id;
    }

    public void setFeatureNegotiationForm(DataForm dataform)
    {
        featureNegotiation = new Feature(dataform);
    }

    public void setFile(File file1)
    {
        file = file1;
    }

    public void setMimeType(String s)
    {
        mimeType = s;
    }

    public void setSessionID(String s)
    {
        id = s;
    }
}
