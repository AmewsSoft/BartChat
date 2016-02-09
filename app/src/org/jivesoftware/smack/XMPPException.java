// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.packet.XMPPError;

public abstract class XMPPException extends Exception
{
    public static class StreamErrorException extends XMPPException
    {

        private static final long serialVersionUID = 0x2f31347428167f76L;
        private final StreamError streamError;

        public StreamError getStreamError()
        {
            return streamError;
        }

        public StreamErrorException(StreamError streamerror)
        {
            super((new StringBuilder()).append(streamerror.getCondition().toString()).append(" You can read more about the meaning of this stream error at http://xmpp.org/rfcs/rfc6120.html#streams-error-conditions\n").append(streamerror.toString()).toString());
            streamError = streamerror;
        }
    }

    public static class XMPPErrorException extends XMPPException
    {

        private static final long serialVersionUID = 0x2f3fbbb5b820744L;
        private final XMPPError error;

        public static void ifHasErrorThenThrow(Stanza stanza)
            throws XMPPErrorException
        {
            stanza = stanza.getError();
            if (stanza != null)
            {
                throw new XMPPErrorException(stanza);
            } else
            {
                return;
            }
        }

        public String getMessage()
        {
            String s = getMessage();
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

        public XMPPErrorException(String s, XMPPError xmpperror)
        {
            super(s);
            error = xmpperror;
        }

        public XMPPErrorException(String s, XMPPError xmpperror, Throwable throwable)
        {
            super(s, throwable);
            error = xmpperror;
        }

        public XMPPErrorException(XMPPError xmpperror)
        {
            error = xmpperror;
        }
    }


    private static final long serialVersionUID = 0x5f8089b3520ec431L;

    protected XMPPException()
    {
    }

    protected XMPPException(String s)
    {
        super(s);
    }

    protected XMPPException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
