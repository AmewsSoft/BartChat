// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco.packet;

import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.TypedCloneable;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.disco.packet:
//            DiscoverInfo

public static class variable
    implements TypedCloneable
{

    private final String variable;

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public clone clone()
    {
        return new <init>(this);
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
                obj = (<init>)obj;
                return variable.equals(((variable) (obj)).variable);
            }
        }
        return false;
    }

    public String getVar()
    {
        return variable;
    }

    public int hashCode()
    {
        return variable.hashCode();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("feature");
        xmlstringbuilder.attribute("var", variable);
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }

    public (String s)
    {
        variable = (String)StringUtils.requireNotNullOrEmpty(s, "variable cannot be null");
    }

    public pty(pty pty)
    {
        variable = pty.variable;
    }
}
