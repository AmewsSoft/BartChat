// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco.packet;

import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.TypedCloneable;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smackx.disco.packet:
//            DiscoverInfo

public static class lang
    implements Comparable, TypedCloneable
{

    private final String category;
    private final String key;
    private final String lang;
    private final String name;
    private final String type;

    private String getKey()
    {
        return key;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public clone clone()
    {
        return new <init>(this);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((compareTo)obj);
    }

    public int compareTo(compareTo compareto)
    {
        String s;
        String s1;
        String s2;
        String s3;
        if (compareto.lang == null)
        {
            s = "";
        } else
        {
            s = compareto.lang;
        }
        if (lang == null)
        {
            s1 = "";
        } else
        {
            s1 = lang;
        }
        if (compareto.type == null)
        {
            s2 = "";
        } else
        {
            s2 = compareto.type;
        }
        if (type == null)
        {
            s3 = "";
        } else
        {
            s3 = type;
        }
        if (category.equals(compareto.category))
        {
            if (s3.equals(s2))
            {
                if (s1.equals(s))
                {
                    return 0;
                } else
                {
                    return s1.compareTo(s);
                }
            } else
            {
                return s3.compareTo(s2);
            }
        } else
        {
            return category.compareTo(compareto.category);
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != null)
        {
            if (obj == this)
            {
                return true;
            }
            if (obj.getClass() == getClass())
            {
                category category1 = (category)obj;
                if (key.equals(category1.key))
                {
                    String s;
                    if (category1.lang == null)
                    {
                        obj = "";
                    } else
                    {
                        obj = category1.lang;
                    }
                    if (lang == null)
                    {
                        s = "";
                    } else
                    {
                        s = lang;
                    }
                    if (((String) (obj)).equals(s))
                    {
                        if (category1.name == null)
                        {
                            obj = "";
                        } else
                        {
                            obj = category1.name;
                        }
                        if (name == null)
                        {
                            s = "";
                        } else
                        {
                            s = category1.name;
                        }
                        if (s.equals(obj))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String getCategory()
    {
        return category;
    }

    public String getLanguage()
    {
        return lang;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public int hashCode()
    {
        int j = 0;
        int k = key.hashCode();
        int i;
        if (lang == null)
        {
            i = 0;
        } else
        {
            i = lang.hashCode();
        }
        if (name != null)
        {
            j = name.hashCode();
        }
        return ((k + 37) * 37 + i) * 37 + j;
    }

    public boolean isOfCategoryAndType(String s, String s1)
    {
        return category.equals(s) && type.equals(s1);
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("identity");
        xmlstringbuilder.xmllangAttribute(lang);
        xmlstringbuilder.attribute("category", category);
        xmlstringbuilder.optAttribute("name", name);
        xmlstringbuilder.optAttribute("type", type);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }


    public (String s, String s1)
    {
        this(s, s1, null, null);
    }

    public <init>(String s, String s1, String s2)
    {
        this(s, s2, s1, null);
    }

    public <init>(String s, String s1, String s2, String s3)
    {
        category = (String)StringUtils.requireNotNullOrEmpty(s, "category cannot be null");
        type = (String)StringUtils.requireNotNullOrEmpty(s1, "type cannot be null");
        key = XmppStringUtils.generateKey(s, s1);
        name = s2;
        lang = s3;
    }

    public lang(lang lang1)
    {
        category = lang1.category;
        type = lang1.type;
        key = lang1.type;
        name = lang1.name;
        lang = lang1.lang;
    }
}
