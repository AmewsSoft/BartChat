// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.StreamError;

// Referenced classes of package org.jivesoftware.smack:
//            XMPPException

public static class streamError extends XMPPException
{

    private static final long serialVersionUID = 0x2f31347428167f76L;
    private final StreamError streamError;

    public StreamError getStreamError()
    {
        return streamError;
    }

    public (StreamError streamerror)
    {
        super((new StringBuilder()).append(streamerror.getCondition().ini_19_()).append(" You can read more about the meaning of this stream error at http://xmpp.org/rfcs/rfc6120.html#streams-error-conditions\n").append(streamerror.toString()).toString());
        streamError = streamerror;
    }
}
