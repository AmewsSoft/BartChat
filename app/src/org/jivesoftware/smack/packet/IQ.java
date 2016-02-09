// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Locale;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            Stanza, ErrorIQ, EmptyResultIQ, XMPPError, 
//            ExtensionElement

public abstract class IQ extends Stanza
{
    public static class IQChildElementXmlStringBuilder extends XmlStringBuilder
    {

        private final String element;
        private boolean isEmptyElement;

        public void setEmptyElement()
        {
            isEmptyElement = true;
        }



        private IQChildElementXmlStringBuilder(String s, String s1)
        {
            prelude(s, s1);
            element = s;
        }

        public IQChildElementXmlStringBuilder(ExtensionElement extensionelement)
        {
            this(extensionelement.getElementName(), extensionelement.getNamespace());
        }

        private IQChildElementXmlStringBuilder(IQ iq)
        {
            this(iq.getChildElementName(), iq.getChildElementNamespace());
        }

    }

    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type error;
        public static final Type get;
        public static final Type result;
        public static final Type set;

        public static Type fromString(String s)
        {
            return valueOf(s.toLowerCase(Locale.US));
        }

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(org/jivesoftware/smack/packet/IQ$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            get = new Type("get", 0);
            set = new Type("set", 1);
            result = new Type("result", 2);
            error = new Type("error", 3);
            $VALUES = (new Type[] {
                get, set, result, error
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String IQ_ELEMENT = "iq";
    public static final String QUERY_ELEMENT = "query";
    private final String childElementName;
    private final String childElementNamespace;
    private Type type;

    protected IQ(String s)
    {
        this(s, null);
    }

    protected IQ(String s, String s1)
    {
        type = Type.get;
        childElementName = s;
        childElementNamespace = s1;
    }

    public IQ(IQ iq)
    {
        super(iq);
        type = Type.get;
        type = iq.getType();
        childElementName = iq.childElementName;
        childElementNamespace = iq.childElementNamespace;
    }

    public static ErrorIQ createErrorResponse(IQ iq, XMPPError xmpperror)
    {
        if (iq.getType() != Type.get && iq.getType() != Type.set)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("IQ must be of type 'set' or 'get'. Original IQ: ").append(iq.toXML()).toString());
        } else
        {
            xmpperror = new ErrorIQ(xmpperror);
            xmpperror.setStanzaId(iq.getStanzaId());
            xmpperror.setFrom(iq.getTo());
            xmpperror.setTo(iq.getFrom());
            return xmpperror;
        }
    }

    public static IQ createResultIQ(IQ iq)
    {
        return new EmptyResultIQ(iq);
    }

    public final String getChildElementName()
    {
        return childElementName;
    }

    public final String getChildElementNamespace()
    {
        return childElementNamespace;
    }

    public final XmlStringBuilder getChildElementXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        if (type == Type.error)
        {
            appendErrorIfExists(xmlstringbuilder);
        } else
        if (childElementName != null)
        {
            IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder = getIQChildElementBuilder(new IQChildElementXmlStringBuilder(this));
            if (iqchildelementxmlstringbuilder != null)
            {
                xmlstringbuilder.append(iqchildelementxmlstringbuilder);
                XmlStringBuilder xmlstringbuilder1 = getExtensionsXML();
                if (iqchildelementxmlstringbuilder.isEmptyElement)
                {
                    if (xmlstringbuilder1.length() == 0)
                    {
                        xmlstringbuilder.closeEmptyElement();
                        return xmlstringbuilder;
                    }
                    xmlstringbuilder.rightAngleBracket();
                }
                xmlstringbuilder.append(xmlstringbuilder1);
                xmlstringbuilder.closeElement(iqchildelementxmlstringbuilder.element);
                return xmlstringbuilder;
            }
        }
        return xmlstringbuilder;
    }

    protected abstract IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder);

    public Type getType()
    {
        return type;
    }

    public boolean isRequestIQ()
    {
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type = new int[Type.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[Type.get.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[Type.set.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.jivesoftware.smack.packet.IQ.Type[type.ordinal()])
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
            return true;
        }
    }

    public void setType(Type type1)
    {
        type = (Type)Objects.requireNonNull(type1, "type must not be null");
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public final XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("iq");
        addCommonAttributes(xmlstringbuilder);
        if (type == null)
        {
            xmlstringbuilder.attribute("type", "get");
        } else
        {
            xmlstringbuilder.attribute("type", type.toString());
        }
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.append(getChildElementXML());
        xmlstringbuilder.closeElement("iq");
        return xmlstringbuilder;
    }
}
