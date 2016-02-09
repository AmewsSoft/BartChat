// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sm;


// Referenced classes of package org.jivesoftware.smack.sm:
//            StreamManagementException

public static class  extends StreamManagementException
{

    private static final long serialVersionUID = 0x10878ae4b3cda005L;

    public (String s, String s1)
    {
        super((new StringBuilder()).append("Stream IDs do not match. Expected '").append(s).append("', but got '").append(s1).append("'").toString());
    }
}
