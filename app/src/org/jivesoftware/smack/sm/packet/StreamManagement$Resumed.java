// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm.packet;


// Referenced classes of package org.jivesoftware.smack.sm.packet:
//            StreamManagement

public static class Resume extends Resume
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

    public Resume(long l, String s)
    {
        super(l, s);
    }
}
