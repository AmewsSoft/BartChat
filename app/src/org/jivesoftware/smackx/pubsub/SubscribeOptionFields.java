// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


public final class SubscribeOptionFields extends Enum
{

    private static final SubscribeOptionFields $VALUES[];
    public static final SubscribeOptionFields deliver;
    public static final SubscribeOptionFields digest;
    public static final SubscribeOptionFields digest_frequency;
    public static final SubscribeOptionFields expire;
    public static final SubscribeOptionFields include_body;
    public static final SubscribeOptionFields show_values;
    public static final SubscribeOptionFields subscription_depth;
    public static final SubscribeOptionFields subscription_type;

    private SubscribeOptionFields(String s, int i)
    {
        super(s, i);
    }

    public static SubscribeOptionFields valueOf(String s)
    {
        return (SubscribeOptionFields)Enum.valueOf(org/jivesoftware/smackx/pubsub/SubscribeOptionFields, s);
    }

    public static SubscribeOptionFields valueOfFromElement(String s)
    {
        s = s.substring(s.lastIndexOf('$'));
        if ("show-values".equals(s))
        {
            return show_values;
        } else
        {
            return valueOf(s);
        }
    }

    public static SubscribeOptionFields[] values()
    {
        return (SubscribeOptionFields[])$VALUES.clone();
    }

    public String getFieldName()
    {
        if (this == show_values)
        {
            return (new StringBuilder()).append("pubsub#").append(toString().replace('_', '-')).toString();
        } else
        {
            return (new StringBuilder()).append("pubsub#").append(toString()).toString();
        }
    }

    static 
    {
        deliver = new SubscribeOptionFields("deliver", 0);
        digest = new SubscribeOptionFields("digest", 1);
        digest_frequency = new SubscribeOptionFields("digest_frequency", 2);
        expire = new SubscribeOptionFields("expire", 3);
        include_body = new SubscribeOptionFields("include_body", 4);
        show_values = new SubscribeOptionFields("show_values", 5);
        subscription_type = new SubscribeOptionFields("subscription_type", 6);
        subscription_depth = new SubscribeOptionFields("subscription_depth", 7);
        $VALUES = (new SubscribeOptionFields[] {
            deliver, digest, digest_frequency, expire, include_body, show_values, subscription_type, subscription_depth
        });
    }
}
