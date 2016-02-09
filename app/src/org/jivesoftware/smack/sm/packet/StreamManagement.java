// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.FullStreamElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class StreamManagement
{
    private static abstract class AbstractEnable extends FullStreamElement
    {

        protected int max;
        protected boolean resume;

        public int getMaxResumptionTime()
        {
            return max;
        }

        public final String getNamespace()
        {
            return "urn:xmpp:sm:3";
        }

        public boolean isResumeSet()
        {
            return resume;
        }

        protected void maybeAddMaxAttributeTo(XmlStringBuilder xmlstringbuilder)
        {
            if (max > 0)
            {
                xmlstringbuilder.attribute("max", Integer.toString(max));
            }
        }

        protected void maybeAddResumeAttributeTo(XmlStringBuilder xmlstringbuilder)
        {
            if (resume)
            {
                xmlstringbuilder.attribute("resume", "true");
            }
        }

        private AbstractEnable()
        {
            max = -1;
            resume = false;
        }

    }

    private static abstract class AbstractResume extends FullStreamElement
    {

        private final long handledCount;
        private final String previd;

        public long getHandledCount()
        {
            return handledCount;
        }

        public final String getNamespace()
        {
            return "urn:xmpp:sm:3";
        }

        public String getPrevId()
        {
            return previd;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public final XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.attribute("h", Long.toString(handledCount));
            xmlstringbuilder.attribute("previd", previd);
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }

        public AbstractResume(long l, String s)
        {
            handledCount = l;
            previd = s;
        }
    }

    public static class AckAnswer extends FullStreamElement
    {

        public static final String ELEMENT = "a";
        private final long handledCount;

        public String getElementName()
        {
            return "a";
        }

        public long getHandledCount()
        {
            return handledCount;
        }

        public String getNamespace()
        {
            return "urn:xmpp:sm:3";
        }

        public CharSequence toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.attribute("h", Long.toString(handledCount));
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }

        public AckAnswer(long l)
        {
            handledCount = l;
        }
    }

    public static class AckRequest extends FullStreamElement
    {

        public static final String ELEMENT = "r";
        public static final AckRequest INSTANCE = new AckRequest();

        public String getElementName()
        {
            return "r";
        }

        public String getNamespace()
        {
            return "urn:xmpp:sm:3";
        }

        public CharSequence toXML()
        {
            return "<r xmlns='urn:xmpp:sm:3'/>";
        }


        private AckRequest()
        {
        }
    }

    public static class Enable extends AbstractEnable
    {

        public static final String ELEMENT = "enable";
        public static final Enable INSTANCE = new Enable();

        public String getElementName()
        {
            return "enable";
        }

        public volatile int getMaxResumptionTime()
        {
            return super.getMaxResumptionTime();
        }

        public volatile boolean isResumeSet()
        {
            return super.isResumeSet();
        }

        public CharSequence toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            maybeAddResumeAttributeTo(xmlstringbuilder);
            maybeAddMaxAttributeTo(xmlstringbuilder);
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }


        private Enable()
        {
        }

        public Enable(boolean flag)
        {
            resume = flag;
        }

        public Enable(boolean flag, int i)
        {
            this(flag);
            max = i;
        }
    }

    public static class Enabled extends AbstractEnable
    {

        public static final String ELEMENT = "enabled";
        private final String id;
        private final String location;

        public String getElementName()
        {
            return "enabled";
        }

        public String getId()
        {
            return id;
        }

        public String getLocation()
        {
            return location;
        }

        public volatile int getMaxResumptionTime()
        {
            return super.getMaxResumptionTime();
        }

        public volatile boolean isResumeSet()
        {
            return super.isResumeSet();
        }

        public CharSequence toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.optAttribute("id", id);
            maybeAddResumeAttributeTo(xmlstringbuilder);
            xmlstringbuilder.optAttribute("location", location);
            maybeAddMaxAttributeTo(xmlstringbuilder);
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }

        public Enabled(String s, boolean flag)
        {
            this(s, flag, null, -1);
        }

        public Enabled(String s, boolean flag, String s1, int i)
        {
            id = s;
            resume = flag;
            location = s1;
            max = i;
        }
    }

    public static class Failed extends FullStreamElement
    {

        public static final String ELEMENT = "failed";
        private org.jivesoftware.smack.packet.XMPPError.Condition condition;

        public String getElementName()
        {
            return "failed";
        }

        public String getNamespace()
        {
            return "urn:xmpp:sm:3";
        }

        public org.jivesoftware.smack.packet.XMPPError.Condition getXMPPErrorCondition()
        {
            return condition;
        }

        public CharSequence toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            if (condition != null)
            {
                xmlstringbuilder.rightAngleBracket();
                xmlstringbuilder.append(condition.toString());
                xmlstringbuilder.xmlnsAttribute("urn:ietf:params:xml:ns:xmpp-stanzas");
                xmlstringbuilder.closeElement("failed");
                return xmlstringbuilder;
            } else
            {
                xmlstringbuilder.closeEmptyElement();
                return xmlstringbuilder;
            }
        }

        public Failed()
        {
        }

        public Failed(org.jivesoftware.smack.packet.XMPPError.Condition condition1)
        {
            condition = condition1;
        }
    }

    public static class Resume extends AbstractResume
    {

        public static final String ELEMENT = "resume";

        public String getElementName()
        {
            return "resume";
        }

        public volatile long getHandledCount()
        {
            return super.getHandledCount();
        }

        public volatile String getPrevId()
        {
            return super.getPrevId();
        }

        public Resume(long l, String s)
        {
            super(l, s);
        }
    }

    public static class Resumed extends AbstractResume
    {

        public static final String ELEMENT = "resumed";

        public String getElementName()
        {
            return "resumed";
        }

        public volatile long getHandledCount()
        {
            return super.getHandledCount();
        }

        public volatile String getPrevId()
        {
            return super.getPrevId();
        }

        public Resumed(long l, String s)
        {
            super(l, s);
        }
    }

    public static class StreamManagementFeature
        implements ExtensionElement
    {

        public static final String ELEMENT = "sm";
        public static final StreamManagementFeature INSTANCE = new StreamManagementFeature();

        public String getElementName()
        {
            return "sm";
        }

        public String getNamespace()
        {
            return "urn:xmpp:sm:3";
        }

        public CharSequence toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }


        private StreamManagementFeature()
        {
        }
    }


    public static final String NAMESPACE = "urn:xmpp:sm:3";

    public StreamManagement()
    {
    }
}
