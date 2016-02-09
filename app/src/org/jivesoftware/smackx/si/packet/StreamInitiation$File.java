// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.si.packet;

import java.util.Date;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.StringUtils;
import org.jxmpp.util.XmppDateTime;

// Referenced classes of package org.jivesoftware.smackx.si.packet:
//            StreamInitiation

public static class size
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

    public (String s, long l)
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
