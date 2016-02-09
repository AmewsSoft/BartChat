// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.Objects;

// Referenced classes of package org.jivesoftware.smack.filter:
//            FlexibleStanzaTypeFilter, OrFilter, StanzaFilter

public class IQTypeFilter extends FlexibleStanzaTypeFilter
{

    public static final StanzaFilter ERROR;
    public static final StanzaFilter GET;
    public static final StanzaFilter GET_OR_SET;
    public static final StanzaFilter RESULT;
    public static final StanzaFilter SET;
    private final org.jivesoftware.smack.packet.IQ.Type type;

    private IQTypeFilter(org.jivesoftware.smack.packet.IQ.Type type1)
    {
        super(org/jivesoftware/smack/packet/IQ);
        type = (org.jivesoftware.smack.packet.IQ.Type)Objects.requireNonNull(type1, "Type must not be null");
    }

    protected boolean acceptSpecific(IQ iq)
    {
        return iq.getType() == type;
    }

    protected volatile boolean acceptSpecific(Stanza stanza)
    {
        return acceptSpecific((IQ)stanza);
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(": type=").append(type).toString();
    }

    static 
    {
        GET = new IQTypeFilter(org.jivesoftware.smack.packet.IQ.Type.get);
        SET = new IQTypeFilter(org.jivesoftware.smack.packet.IQ.Type.set);
        RESULT = new IQTypeFilter(org.jivesoftware.smack.packet.IQ.Type.result);
        ERROR = new IQTypeFilter(org.jivesoftware.smack.packet.IQ.Type.error);
        GET_OR_SET = new OrFilter(new StanzaFilter[] {
            GET, SET
        });
    }
}
