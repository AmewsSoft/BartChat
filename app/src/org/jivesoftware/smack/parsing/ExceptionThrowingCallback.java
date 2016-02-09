// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.parsing;


// Referenced classes of package org.jivesoftware.smack.parsing:
//            ParsingExceptionCallback, UnparsablePacket

public class ExceptionThrowingCallback extends ParsingExceptionCallback
{

    public ExceptionThrowingCallback()
    {
    }

    public void handleUnparsablePacket(UnparsablePacket unparsablepacket)
        throws Exception
    {
        throw unparsablepacket.getParsingException();
    }
}
