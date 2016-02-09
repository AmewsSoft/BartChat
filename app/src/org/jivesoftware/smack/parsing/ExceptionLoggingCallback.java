// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.parsing;

import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package org.jivesoftware.smack.parsing:
//            ParsingExceptionCallback, UnparsablePacket

public class ExceptionLoggingCallback extends ParsingExceptionCallback
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/parsing/ExceptionLoggingCallback.getName());

    public ExceptionLoggingCallback()
    {
    }

    public void handleUnparsablePacket(UnparsablePacket unparsablepacket)
        throws Exception
    {
        LOGGER.log(Level.SEVERE, "Smack message parsing exception: ", unparsablepacket.getParsingException());
        LOGGER.severe((new StringBuilder()).append("Unparsed content: ").append(unparsablepacket.getContent()).toString());
    }

}
