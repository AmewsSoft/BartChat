// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.iqrequest;

import org.jivesoftware.smack.packet.IQ;

// Referenced classes of package org.jivesoftware.smack.iqrequest:
//            IQRequestHandler

public abstract class AbstractIqRequestHandler
    implements IQRequestHandler
{

    private final String element;
    private final IQRequestHandler.Mode mode;
    private final String namespace;
    private final org.jivesoftware.smack.packet.IQ.Type type;

    protected AbstractIqRequestHandler(String s, String s1, org.jivesoftware.smack.packet.IQ.Type type1, IQRequestHandler.Mode mode1)
    {
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type = new int[org.jivesoftware.smack.packet.IQ.Type.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[org.jivesoftware.smack.packet.IQ.Type.set.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[org.jivesoftware.smack.packet.IQ.Type.get.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.jivesoftware.smack.packet.IQ.Type[type1.ordinal()])
        {
        default:
            throw new IllegalArgumentException("Only get and set IQ type allowed");

        case 1: // '\001'
        case 2: // '\002'
            element = s;
            break;
        }
        namespace = s1;
        type = type1;
        mode = mode1;
    }

    public String getElement()
    {
        return element;
    }

    public IQRequestHandler.Mode getMode()
    {
        return mode;
    }

    public String getNamespace()
    {
        return namespace;
    }

    public org.jivesoftware.smack.packet.IQ.Type getType()
    {
        return type;
    }

    public abstract IQ handleIQRequest(IQ iq);
}
