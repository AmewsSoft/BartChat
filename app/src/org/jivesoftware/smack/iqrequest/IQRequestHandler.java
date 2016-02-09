// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.iqrequest;

import org.jivesoftware.smack.packet.IQ;

public interface IQRequestHandler
{
    public static final class Mode extends Enum
    {

        private static final Mode $VALUES[];
        public static final Mode async;
        public static final Mode sync;

        public static Mode valueOf(String s)
        {
            return (Mode)Enum.valueOf(org/jivesoftware/smack/iqrequest/IQRequestHandler$Mode, s);
        }

        public static Mode[] values()
        {
            return (Mode[])$VALUES.clone();
        }

        static 
        {
            sync = new Mode("sync", 0);
            async = new Mode("async", 1);
            $VALUES = (new Mode[] {
                sync, async
            });
        }

        private Mode(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract String getElement();

    public abstract Mode getMode();

    public abstract String getNamespace();

    public abstract org.jivesoftware.smack.packet.IQ.Type getType();

    public abstract IQ handleIQRequest(IQ iq);
}
