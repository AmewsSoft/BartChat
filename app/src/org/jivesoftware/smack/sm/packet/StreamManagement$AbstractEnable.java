// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.packet;

import org.jivesoftware.smack.packet.FullStreamElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.sm.packet:
//            StreamManagement

private static abstract class <init> extends FullStreamElement
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

    private ()
    {
        max = -1;
        resume = false;
    }

    resume(resume resume1)
    {
        this();
    }
}
