// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.rsm.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class RSMSet
    implements ExtensionElement
{
    public static final class PageDirection extends Enum
    {

        private static final PageDirection $VALUES[];
        public static final PageDirection after;
        public static final PageDirection before;

        public static PageDirection valueOf(String s)
        {
            return (PageDirection)Enum.valueOf(org/jivesoftware/smackx/rsm/packet/RSMSet$PageDirection, s);
        }

        public static PageDirection[] values()
        {
            return (PageDirection[])$VALUES.clone();
        }

        static 
        {
            before = new PageDirection("before", 0);
            after = new PageDirection("after", 1);
            $VALUES = (new PageDirection[] {
                before, after
            });
        }

        private PageDirection(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String ELEMENT = "set";
    public static final String NAMESPACE = "http://jabber.org/protocol/rsm";
    private final String after;
    private final String before;
    private final int count;
    private final int firstIndex;
    private final String firstString;
    private final int index;
    private final String last;
    private final int max;

    public RSMSet(int i)
    {
        this(i, -1);
    }

    public RSMSet(int i, int j)
    {
        this(null, null, -1, j, null, i, null, -1);
    }

    public RSMSet(int i, String s, PageDirection pagedirection)
    {
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smackx$rsm$packet$RSMSet$PageDirection[];

            static 
            {
                $SwitchMap$org$jivesoftware$smackx$rsm$packet$RSMSet$PageDirection = new int[PageDirection.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$rsm$packet$RSMSet$PageDirection[PageDirection.before.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$rsm$packet$RSMSet$PageDirection[PageDirection.after.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.org.jivesoftware.smackx.rsm.packet.RSMSet.PageDirection[pagedirection.ordinal()];
        JVM INSTR tableswitch 1 2: default 36
    //                   1 44
    //                   2 85;
           goto _L1 _L2 _L3
_L1:
        throw new AssertionError();
_L2:
        before = s;
        after = null;
_L5:
        count = -1;
        index = -1;
        last = null;
        max = i;
        firstString = null;
        firstIndex = -1;
        return;
_L3:
        before = null;
        after = s;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public RSMSet(String s, String s1, int i, int j, String s2, int k, String s3, 
            int l)
    {
        after = s;
        before = s1;
        count = i;
        index = j;
        last = s2;
        max = k;
        firstString = s3;
        firstIndex = l;
    }

    public RSMSet(String s, PageDirection pagedirection)
    {
        this(-1, s, pagedirection);
    }

    public static RSMSet from(Stanza stanza)
    {
        return (RSMSet)stanza.getExtension("set", "http://jabber.org/protocol/rsm");
    }

    public static RSMSet newAfter(String s)
    {
        return new RSMSet(s, PageDirection.after);
    }

    public static RSMSet newBefore(String s)
    {
        return new RSMSet(s, PageDirection.before);
    }

    public String getAfter()
    {
        return after;
    }

    public String getBefore()
    {
        return before;
    }

    public int getCount()
    {
        return count;
    }

    public String getElementName()
    {
        return "set";
    }

    public String getFirst()
    {
        return firstString;
    }

    public int getFirstIndex()
    {
        return firstIndex;
    }

    public int getIndex()
    {
        return index;
    }

    public String getLast()
    {
        return last;
    }

    public int getMax()
    {
        return max;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/rsm";
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optElement("after", after);
        xmlstringbuilder.optElement("before", before);
        xmlstringbuilder.optIntElement("count", count);
        if (firstString != null)
        {
            xmlstringbuilder.halfOpenElement("first");
            xmlstringbuilder.optIntAttribute("index", firstIndex);
            xmlstringbuilder.rightAngleBracket();
            xmlstringbuilder.append(firstString);
            xmlstringbuilder.closeElement("first");
        }
        xmlstringbuilder.optIntElement("index", index);
        xmlstringbuilder.optElement("last", last);
        xmlstringbuilder.optIntElement("max", max);
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
