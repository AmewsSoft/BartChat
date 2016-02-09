// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.util.Objects;

// Referenced classes of package org.jivesoftware.smack.packet:
//            SimpleIQ, XMPPError

public class ErrorIQ extends SimpleIQ
{

    public static final String ELEMENT = "error";

    public ErrorIQ(XMPPError xmpperror)
    {
        super("error", null);
        Objects.requireNonNull(xmpperror, "XMPPError must not be null");
        setType(IQ.Type.error);
        setError(xmpperror);
    }
}
