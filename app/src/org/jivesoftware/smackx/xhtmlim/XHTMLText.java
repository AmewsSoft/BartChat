// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xhtmlim;

import org.jivesoftware.smack.util.XmlStringBuilder;

public class XHTMLText
{

    public static final String A = "a";
    public static final String BLOCKQUOTE = "blockquote";
    public static final String BR = "br";
    public static final String CITE = "cite";
    public static final String CODE = "code";
    public static final String EM = "em";
    public static final String H = "h";
    public static final String HREF = "href";
    public static final String IMG = "img";
    public static final String LI = "li";
    public static final String NAMESPACE = "http://www.w3.org/1999/xhtml";
    public static final String OL = "ol";
    public static final String P = "p";
    public static final String Q = "q";
    public static final String SPAN = "span";
    public static final String STRONG = "strong";
    public static final String STYLE = "style";
    public static final String UL = "ul";
    private final XmlStringBuilder text = new XmlStringBuilder();

    public XHTMLText(String s, String s1)
    {
        appendOpenBodyTag(s, s1);
    }

    private XHTMLText appendOpenBodyTag(String s, String s1)
    {
        text.halfOpenElement("body");
        text.xmlnsAttribute("http://www.w3.org/1999/xhtml");
        text.optElement("style", s);
        text.xmllangAttribute(s1);
        text.rightAngleBracket();
        return this;
    }

    public XHTMLText append(String s)
    {
        text.escape(s);
        return this;
    }

    public XHTMLText appendBrTag()
    {
        text.emptyElement("br");
        return this;
    }

    public XHTMLText appendCloseAnchorTag()
    {
        text.closeElement("a");
        return this;
    }

    public XHTMLText appendCloseBlockQuoteTag()
    {
        text.closeElement("blockquote");
        return this;
    }

    public XHTMLText appendCloseBodyTag()
    {
        text.closeElement("body");
        return this;
    }

    public XHTMLText appendCloseCodeTag()
    {
        text.closeElement("code");
        return this;
    }

    public XHTMLText appendCloseEmTag()
    {
        text.closeElement("em");
        return this;
    }

    public XHTMLText appendCloseHeaderTag(int i)
    {
        if (i > 3 || i < 1)
        {
            throw new IllegalArgumentException("Level must be between 1 and 3");
        } else
        {
            text.closeElement((new StringBuilder()).append("h").append(Integer.toBinaryString(i)).toString());
            return this;
        }
    }

    public XHTMLText appendCloseInlinedQuoteTag()
    {
        text.closeElement("q");
        return this;
    }

    public XHTMLText appendCloseLineItemTag()
    {
        text.closeElement("li");
        return this;
    }

    public XHTMLText appendCloseOrderedListTag()
    {
        text.closeElement("ol");
        return this;
    }

    public XHTMLText appendCloseParagraphTag()
    {
        text.closeElement("p");
        return this;
    }

    public XHTMLText appendCloseSpanTag()
    {
        text.closeElement("span");
        return this;
    }

    public XHTMLText appendCloseStrongTag()
    {
        text.closeElement("strong");
        return this;
    }

    public XHTMLText appendCloseUnorderedListTag()
    {
        text.closeElement("ul");
        return this;
    }

    public XHTMLText appendImageTag(String s, String s1, String s2, String s3, String s4)
    {
        text.halfOpenElement("img");
        text.optAttribute("align", s);
        text.optAttribute("alt", s1);
        text.optAttribute("height", s2);
        text.optAttribute("src", s3);
        text.optAttribute("width", s4);
        text.rightAngleBracket();
        return this;
    }

    public XHTMLText appendLineItemTag(String s)
    {
        text.halfOpenElement("li");
        text.optAttribute("style", s);
        text.rightAngleBracket();
        return this;
    }

    public XHTMLText appendOpenAnchorTag(String s, String s1)
    {
        text.halfOpenElement("a");
        text.optAttribute("href", s);
        text.optAttribute("style", s1);
        text.rightAngleBracket();
        return this;
    }

    public XHTMLText appendOpenBlockQuoteTag(String s)
    {
        text.halfOpenElement("blockquote");
        text.optAttribute("style", s);
        text.rightAngleBracket();
        return this;
    }

    public XHTMLText appendOpenCiteTag()
    {
        text.openElement("cite");
        return this;
    }

    public XHTMLText appendOpenCodeTag()
    {
        text.openElement("code");
        return this;
    }

    public XHTMLText appendOpenEmTag()
    {
        text.openElement("em");
        return this;
    }

    public XHTMLText appendOpenHeaderTag(int i, String s)
    {
        if (i > 3 || i < 1)
        {
            throw new IllegalArgumentException("Level must be between 1 and 3");
        } else
        {
            text.halfOpenElement((new StringBuilder()).append("h").append(Integer.toString(i)).toString());
            text.optAttribute("style", s);
            text.rightAngleBracket();
            return this;
        }
    }

    public XHTMLText appendOpenInlinedQuoteTag(String s)
    {
        text.halfOpenElement("q");
        text.optAttribute("style", s);
        text.rightAngleBracket();
        return this;
    }

    public XHTMLText appendOpenOrderedListTag(String s)
    {
        text.halfOpenElement("ol");
        text.optAttribute("style", s);
        text.rightAngleBracket();
        return this;
    }

    public XHTMLText appendOpenParagraphTag(String s)
    {
        text.halfOpenElement("p");
        text.optAttribute("style", s);
        text.rightAngleBracket();
        return this;
    }

    public XHTMLText appendOpenSpanTag(String s)
    {
        text.halfOpenElement("span");
        text.optAttribute("style", s);
        text.rightAngleBracket();
        return this;
    }

    public XHTMLText appendOpenStrongTag()
    {
        text.openElement("strong");
        return this;
    }

    public XHTMLText appendOpenUnorderedListTag(String s)
    {
        text.halfOpenElement("ul");
        text.optAttribute("style", s);
        text.rightAngleBracket();
        return this;
    }

    public String toString()
    {
        return text.toString();
    }

    public XmlStringBuilder toXML()
    {
        return text;
    }
}
