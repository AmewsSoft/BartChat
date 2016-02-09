// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;

// Referenced classes of package org.jivesoftware.smack:
//            XMPPException

public static class error extends XMPPException
{

    private static final long serialVersionUID = 0x2f3fbbb5b820744L;
    private final XMPPError error;

    public static void ifHasErrorThenThrow(Stanza stanza)
        throws error
    {
        stanza = stanza.getError();
        if (stanza != null)
        {
            throw new <init>(stanza);
        } else
        {
            return;
        }
    }

    public String getMessage()
    {
        String s = super.getMessage();
        if (s != null)
        {
            return s;
        } else
        {
            return error.toString();
        }
    }

    public XMPPError getXMPPError()
    {
        return error;
    }

    public (String s, XMPPError xmpperror)
    {
        super(s);
        error = xmpperror;
    }

    public error(String s, XMPPError xmpperror, Throwable throwable)
    {
        super(s, throwable);
        error = xmpperror;
    }

    public error(XMPPError xmpperror)
    {
        error = xmpperror;
    }
}
