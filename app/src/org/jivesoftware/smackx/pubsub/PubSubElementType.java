// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Locale;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

public final class PubSubElementType extends Enum
{

    private static final PubSubElementType $VALUES[];
    public static final PubSubElementType AFFILIATIONS;
    public static final PubSubElementType CONFIGURATION;
    public static final PubSubElementType CONFIGURE;
    public static final PubSubElementType CONFIGURE_OWNER;
    public static final PubSubElementType CREATE;
    public static final PubSubElementType DEFAULT;
    public static final PubSubElementType DELETE;
    public static final PubSubElementType DELETE_EVENT;
    public static final PubSubElementType ITEM;
    public static final PubSubElementType ITEMS;
    public static final PubSubElementType ITEMS_EVENT;
    public static final PubSubElementType ITEM_EVENT;
    public static final PubSubElementType OPTIONS;
    public static final PubSubElementType PUBLISH;
    public static final PubSubElementType PUBLISH_OPTIONS;
    public static final PubSubElementType PURGE_EVENT;
    public static final PubSubElementType PURGE_OWNER;
    public static final PubSubElementType RETRACT;
    public static final PubSubElementType SUBSCRIBE;
    public static final PubSubElementType SUBSCRIPTION;
    public static final PubSubElementType SUBSCRIPTIONS;
    public static final PubSubElementType UNSUBSCRIBE;
    private String eName;
    private PubSubNamespace nSpace;

    private PubSubElementType(String s, int i, String s1, PubSubNamespace pubsubnamespace)
    {
        super(s, i);
        eName = s1;
        nSpace = pubsubnamespace;
    }

    public static PubSubElementType valueOf(String s)
    {
        return (PubSubElementType)Enum.valueOf(org/jivesoftware/smackx/pubsub/PubSubElementType, s);
    }

    public static PubSubElementType valueOfFromElemName(String s, String s1)
    {
        int i = s1.lastIndexOf('#');
        if (i == -1)
        {
            s1 = null;
        } else
        {
            s1 = s1.substring(i + 1);
        }
        if (s1 != null)
        {
            return valueOf((new StringBuilder()).append(s).append('_').append(s1).toString().toUpperCase(Locale.US));
        } else
        {
            return valueOf(s.toUpperCase(Locale.US).replace('-', '_'));
        }
    }

    public static PubSubElementType[] values()
    {
        return (PubSubElementType[])$VALUES.clone();
    }

    public String getElementName()
    {
        return eName;
    }

    public PubSubNamespace getNamespace()
    {
        return nSpace;
    }

    static 
    {
        CREATE = new PubSubElementType("CREATE", 0, "create", PubSubNamespace.BASIC);
        DELETE = new PubSubElementType("DELETE", 1, "delete", PubSubNamespace.OWNER);
        DELETE_EVENT = new PubSubElementType("DELETE_EVENT", 2, "delete", PubSubNamespace.EVENT);
        CONFIGURE = new PubSubElementType("CONFIGURE", 3, "configure", PubSubNamespace.BASIC);
        CONFIGURE_OWNER = new PubSubElementType("CONFIGURE_OWNER", 4, "configure", PubSubNamespace.OWNER);
        CONFIGURATION = new PubSubElementType("CONFIGURATION", 5, "configuration", PubSubNamespace.EVENT);
        OPTIONS = new PubSubElementType("OPTIONS", 6, "options", PubSubNamespace.BASIC);
        DEFAULT = new PubSubElementType("DEFAULT", 7, "default", PubSubNamespace.OWNER);
        ITEMS = new PubSubElementType("ITEMS", 8, "items", PubSubNamespace.BASIC);
        ITEMS_EVENT = new PubSubElementType("ITEMS_EVENT", 9, "items", PubSubNamespace.EVENT);
        ITEM = new PubSubElementType("ITEM", 10, "item", PubSubNamespace.BASIC);
        ITEM_EVENT = new PubSubElementType("ITEM_EVENT", 11, "item", PubSubNamespace.EVENT);
        PUBLISH = new PubSubElementType("PUBLISH", 12, "publish", PubSubNamespace.BASIC);
        PUBLISH_OPTIONS = new PubSubElementType("PUBLISH_OPTIONS", 13, "publish-options", PubSubNamespace.BASIC);
        PURGE_OWNER = new PubSubElementType("PURGE_OWNER", 14, "purge", PubSubNamespace.OWNER);
        PURGE_EVENT = new PubSubElementType("PURGE_EVENT", 15, "purge", PubSubNamespace.EVENT);
        RETRACT = new PubSubElementType("RETRACT", 16, "retract", PubSubNamespace.BASIC);
        AFFILIATIONS = new PubSubElementType("AFFILIATIONS", 17, "affiliations", PubSubNamespace.BASIC);
        SUBSCRIBE = new PubSubElementType("SUBSCRIBE", 18, "subscribe", PubSubNamespace.BASIC);
        SUBSCRIPTION = new PubSubElementType("SUBSCRIPTION", 19, "subscription", PubSubNamespace.BASIC);
        SUBSCRIPTIONS = new PubSubElementType("SUBSCRIPTIONS", 20, "subscriptions", PubSubNamespace.BASIC);
        UNSUBSCRIBE = new PubSubElementType("UNSUBSCRIBE", 21, "unsubscribe", PubSubNamespace.BASIC);
        $VALUES = (new PubSubElementType[] {
            CREATE, DELETE, DELETE_EVENT, CONFIGURE, CONFIGURE_OWNER, CONFIGURATION, OPTIONS, DEFAULT, ITEMS, ITEMS_EVENT, 
            ITEM, ITEM_EVENT, PUBLISH, PUBLISH_OPTIONS, PURGE_OWNER, PURGE_EVENT, RETRACT, AFFILIATIONS, SUBSCRIBE, SUBSCRIPTION, 
            SUBSCRIPTIONS, UNSUBSCRIBE
        });
    }
}
