// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;

// Referenced classes of package org.jivesoftware.smack.util:
//            LazyStringBuilder, StringUtils

public class XmlStringBuilder
    implements Appendable, CharSequence
{

    static final boolean $assertionsDisabled;
    public static final String RIGHT_ANGLE_BRACKET = Character.toString('>');
    private final LazyStringBuilder sb;

    public XmlStringBuilder()
    {
        sb = new LazyStringBuilder();
    }

    public XmlStringBuilder(ExtensionElement extensionelement)
    {
        this();
        prelude(extensionelement);
    }

    public XmlStringBuilder(NamedElement namedelement)
    {
        this();
        halfOpenElement(namedelement.getElementName());
    }

    public volatile Appendable append(char c)
        throws IOException
    {
        return append(c);
    }

    public volatile Appendable append(CharSequence charsequence)
        throws IOException
    {
        return append(charsequence);
    }

    public volatile Appendable append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public XmlStringBuilder append(char c)
    {
        sb.append(c);
        return this;
    }

    public XmlStringBuilder append(CharSequence charsequence)
    {
        if (!$assertionsDisabled && charsequence == null)
        {
            throw new AssertionError();
        } else
        {
            sb.append(charsequence);
            return this;
        }
    }

    public XmlStringBuilder append(CharSequence charsequence, int i, int j)
    {
        if (!$assertionsDisabled && charsequence == null)
        {
            throw new AssertionError();
        } else
        {
            sb.append(charsequence, i, j);
            return this;
        }
    }

    public XmlStringBuilder append(Collection collection)
    {
        for (collection = collection.iterator(); collection.hasNext(); append(((Element)collection.next()).toXML())) { }
        return this;
    }

    public XmlStringBuilder append(XmlStringBuilder xmlstringbuilder)
    {
        if (!$assertionsDisabled && xmlstringbuilder == null)
        {
            throw new AssertionError();
        } else
        {
            sb.append(xmlstringbuilder.sb);
            return this;
        }
    }

    public XmlStringBuilder attribute(String s, int i)
    {
        if (!$assertionsDisabled && s == null)
        {
            throw new AssertionError();
        } else
        {
            return attribute(s, String.valueOf(i));
        }
    }

    public XmlStringBuilder attribute(String s, Enum enum)
    {
        if (!$assertionsDisabled && enum == null)
        {
            throw new AssertionError();
        } else
        {
            attribute(s, enum.name());
            return this;
        }
    }

    public XmlStringBuilder attribute(String s, String s1)
    {
        if (!$assertionsDisabled && s1 == null)
        {
            throw new AssertionError();
        } else
        {
            sb.append(' ').append(s).append("='");
            escape(s1);
            sb.append('\'');
            return this;
        }
    }

    public char charAt(int i)
    {
        return sb.charAt(i);
    }

    public XmlStringBuilder closeElement(String s)
    {
        sb.append("</").append(s);
        rightAngleBracket();
        return this;
    }

    public XmlStringBuilder closeElement(NamedElement namedelement)
    {
        closeElement(namedelement.getElementName());
        return this;
    }

    public XmlStringBuilder closeEmptyElement()
    {
        sb.append("/>");
        return this;
    }

    public XmlStringBuilder condAttribute(boolean flag, String s, String s1)
    {
        if (flag)
        {
            attribute(s, s1);
        }
        return this;
    }

    public XmlStringBuilder condEmptyElement(boolean flag, String s)
    {
        if (flag)
        {
            emptyElement(s);
        }
        return this;
    }

    public XmlStringBuilder element(String s, Enum enum)
    {
        if (!$assertionsDisabled && enum == null)
        {
            throw new AssertionError();
        } else
        {
            element(s, enum.name());
            return this;
        }
    }

    public XmlStringBuilder element(String s, String s1)
    {
        if (!$assertionsDisabled && s1 == null)
        {
            throw new AssertionError();
        } else
        {
            openElement(s);
            escape(s1);
            closeElement(s);
            return this;
        }
    }

    public XmlStringBuilder element(Element element1)
    {
        if (!$assertionsDisabled && element1 == null)
        {
            throw new AssertionError();
        } else
        {
            return append(element1.toXML());
        }
    }

    public XmlStringBuilder emptyElement(Enum enum)
    {
        return emptyElement(enum.name());
    }

    public XmlStringBuilder emptyElement(String s)
    {
        halfOpenElement(s);
        return closeEmptyElement();
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof CharSequence))
        {
            return false;
        } else
        {
            obj = (CharSequence)obj;
            return toString().equals(((CharSequence) (obj)).toString());
        }
    }

    public XmlStringBuilder escape(String s)
    {
        if (!$assertionsDisabled && s == null)
        {
            throw new AssertionError();
        } else
        {
            sb.append(StringUtils.escapeForXML(s));
            return this;
        }
    }

    public XmlStringBuilder escapedElement(String s, String s1)
    {
        if (!$assertionsDisabled && s1 == null)
        {
            throw new AssertionError();
        } else
        {
            openElement(s);
            append(s1);
            closeElement(s);
            return this;
        }
    }

    public XmlStringBuilder halfOpenElement(String s)
    {
        if (!$assertionsDisabled && !StringUtils.isNotEmpty(s))
        {
            throw new AssertionError();
        } else
        {
            sb.append('<').append(s);
            return this;
        }
    }

    public XmlStringBuilder halfOpenElement(NamedElement namedelement)
    {
        return halfOpenElement(namedelement.getElementName());
    }

    public int hashCode()
    {
        return toString().hashCode();
    }

    public int length()
    {
        return sb.length();
    }

    public XmlStringBuilder openElement(String s)
    {
        halfOpenElement(s).rightAngleBracket();
        return this;
    }

    public XmlStringBuilder optAppend(CharSequence charsequence)
    {
        if (charsequence != null)
        {
            append(charsequence);
        }
        return this;
    }

    public XmlStringBuilder optAppend(Element element1)
    {
        if (element1 != null)
        {
            append(element1.toXML());
        }
        return this;
    }

    public XmlStringBuilder optAttribute(String s, Enum enum)
    {
        if (enum != null)
        {
            attribute(s, enum.toString());
        }
        return this;
    }

    public XmlStringBuilder optAttribute(String s, String s1)
    {
        if (s1 != null)
        {
            attribute(s, s1);
        }
        return this;
    }

    public XmlStringBuilder optBooleanAttribute(String s, boolean flag)
    {
        if (flag)
        {
            sb.append(' ').append(s).append("='true'");
        }
        return this;
    }

    public XmlStringBuilder optElement(String s, Enum enum)
    {
        if (enum != null)
        {
            element(s, enum);
        }
        return this;
    }

    public XmlStringBuilder optElement(String s, String s1)
    {
        if (s1 != null)
        {
            element(s, s1);
        }
        return this;
    }

    public XmlStringBuilder optElement(Element element1)
    {
        if (element1 != null)
        {
            append(element1.toXML());
        }
        return this;
    }

    public XmlStringBuilder optIntAttribute(String s, int i)
    {
        if (i >= 0)
        {
            attribute(s, Integer.toString(i));
        }
        return this;
    }

    public XmlStringBuilder optIntElement(String s, int i)
    {
        if (i >= 0)
        {
            element(s, String.valueOf(i));
        }
        return this;
    }

    public XmlStringBuilder optLongAttribute(String s, Long long1)
    {
        if (long1 != null && long1.longValue() >= 0L)
        {
            attribute(s, Long.toString(long1.longValue()));
        }
        return this;
    }

    public XmlStringBuilder prelude(String s, String s1)
    {
        halfOpenElement(s);
        xmlnsAttribute(s1);
        return this;
    }

    public XmlStringBuilder prelude(ExtensionElement extensionelement)
    {
        return prelude(extensionelement.getElementName(), extensionelement.getNamespace());
    }

    public XmlStringBuilder rightAngelBracket()
    {
        return rightAngleBracket();
    }

    public XmlStringBuilder rightAngleBracket()
    {
        sb.append(RIGHT_ANGLE_BRACKET);
        return this;
    }

    public CharSequence subSequence(int i, int j)
    {
        return sb.subSequence(i, j);
    }

    public String toString()
    {
        return sb.toString();
    }

    public XmlStringBuilder xmllangAttribute(String s)
    {
        optAttribute("xml:lang", s);
        return this;
    }

    public XmlStringBuilder xmlnsAttribute(String s)
    {
        optAttribute("xmlns", s);
        return this;
    }

    static 
    {
        boolean flag;
        if (!org/jivesoftware/smack/util/XmlStringBuilder.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }
}
