// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;


// Referenced classes of package org.jivesoftware.smack:
//            SmackException

public static class feature extends SmackException
{

    private static final long serialVersionUID = 0x416960b1fedf2ef0L;
    private final String feature;
    private final String jid;

    public String getFeature()
    {
        return feature;
    }

    public String getJid()
    {
        return jid;
    }

    public (String s)
    {
        this(s, null);
    }

    public <init>(String s, String s1)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(s).append(" not supported");
        String s2;
        if (s1 == null)
        {
            s2 = "";
        } else
        {
            s2 = (new StringBuilder()).append(" by '").append(s1).append("'").toString();
        }
        super(stringbuilder.append(s2).toString());
        jid = s1;
        feature = s;
    }
}
