// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.packet;

import java.util.Locale;

public final class PubSubNamespace extends Enum
{

    private static final PubSubNamespace $VALUES[];
    public static final PubSubNamespace BASIC;
    public static final PubSubNamespace ERROR;
    public static final PubSubNamespace EVENT;
    public static final PubSubNamespace OWNER;
    private String fragment;

    private PubSubNamespace(String s, int i, String s1)
    {
        super(s, i);
        fragment = s1;
    }

    public static PubSubNamespace valueOf(String s)
    {
        return (PubSubNamespace)Enum.valueOf(org/jivesoftware/smackx/pubsub/packet/PubSubNamespace, s);
    }

    public static PubSubNamespace valueOfFromXmlns(String s)
    {
        if (s.lastIndexOf('#') != -1)
        {
            return valueOf(s.substring(s.lastIndexOf('#') + 1).toUpperCase(Locale.US));
        } else
        {
            return BASIC;
        }
    }

    public static PubSubNamespace[] values()
    {
        return (PubSubNamespace[])$VALUES.clone();
    }

    public String getFragment()
    {
        return fragment;
    }

    public String getXmlns()
    {
        String s = "http://jabber.org/protocol/pubsub";
        if (fragment != null)
        {
            s = (new StringBuilder()).append("http://jabber.org/protocol/pubsub").append('#').append(fragment).toString();
        }
        return s;
    }

    static 
    {
        BASIC = new PubSubNamespace("BASIC", 0, null);
        ERROR = new PubSubNamespace("ERROR", 1, "errors");
        EVENT = new PubSubNamespace("EVENT", 2, "event");
        OWNER = new PubSubNamespace("OWNER", 3, "owner");
        $VALUES = (new PubSubNamespace[] {
            BASIC, ERROR, EVENT, OWNER
        });
    }
}
