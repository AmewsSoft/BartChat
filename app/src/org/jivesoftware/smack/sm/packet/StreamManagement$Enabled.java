// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.sm.packet:
//            StreamManagement

public static class max extends Enable
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

    public Enable(String s, boolean flag)
    {
        this(s, flag, null, -1);
    }

    public <init>(String s, boolean flag, String s1, int i)
    {
        super(null);
        id = s;
        resume = flag;
        location = s1;
        max = i;
    }
}
