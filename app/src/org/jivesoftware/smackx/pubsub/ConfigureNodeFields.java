// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


public final class ConfigureNodeFields extends Enum
{

    private static final ConfigureNodeFields $VALUES[];
    public static final ConfigureNodeFields access_model;
    public static final ConfigureNodeFields body_xslt;
    public static final ConfigureNodeFields children;
    public static final ConfigureNodeFields children_association_policy;
    public static final ConfigureNodeFields children_association_whitelist;
    public static final ConfigureNodeFields children_max;
    public static final ConfigureNodeFields collection;
    public static final ConfigureNodeFields dataform_xslt;
    public static final ConfigureNodeFields deliver_payloads;
    public static final ConfigureNodeFields itemreply;
    public static final ConfigureNodeFields max_items;
    public static final ConfigureNodeFields max_payload_size;
    public static final ConfigureNodeFields node_type;
    public static final ConfigureNodeFields notify_config;
    public static final ConfigureNodeFields notify_delete;
    public static final ConfigureNodeFields notify_retract;
    public static final ConfigureNodeFields persist_items;
    public static final ConfigureNodeFields presence_based_delivery;
    public static final ConfigureNodeFields publish_model;
    public static final ConfigureNodeFields replyroom;
    public static final ConfigureNodeFields replyto;
    public static final ConfigureNodeFields roster_groups_allowed;
    public static final ConfigureNodeFields subscribe;
    public static final ConfigureNodeFields title;
    public static final ConfigureNodeFields type;

    private ConfigureNodeFields(String s, int i)
    {
        super(s, i);
    }

    public static ConfigureNodeFields valueOf(String s)
    {
        return (ConfigureNodeFields)Enum.valueOf(org/jivesoftware/smackx/pubsub/ConfigureNodeFields, s);
    }

    public static ConfigureNodeFields[] values()
    {
        return (ConfigureNodeFields[])$VALUES.clone();
    }

    public String getFieldName()
    {
        return (new StringBuilder()).append("pubsub#").append(toString()).toString();
    }

    static 
    {
        access_model = new ConfigureNodeFields("access_model", 0);
        body_xslt = new ConfigureNodeFields("body_xslt", 1);
        collection = new ConfigureNodeFields("collection", 2);
        dataform_xslt = new ConfigureNodeFields("dataform_xslt", 3);
        deliver_payloads = new ConfigureNodeFields("deliver_payloads", 4);
        itemreply = new ConfigureNodeFields("itemreply", 5);
        children_association_policy = new ConfigureNodeFields("children_association_policy", 6);
        children_association_whitelist = new ConfigureNodeFields("children_association_whitelist", 7);
        children = new ConfigureNodeFields("children", 8);
        children_max = new ConfigureNodeFields("children_max", 9);
        max_items = new ConfigureNodeFields("max_items", 10);
        max_payload_size = new ConfigureNodeFields("max_payload_size", 11);
        node_type = new ConfigureNodeFields("node_type", 12);
        notify_config = new ConfigureNodeFields("notify_config", 13);
        notify_delete = new ConfigureNodeFields("notify_delete", 14);
        notify_retract = new ConfigureNodeFields("notify_retract", 15);
        persist_items = new ConfigureNodeFields("persist_items", 16);
        presence_based_delivery = new ConfigureNodeFields("presence_based_delivery", 17);
        publish_model = new ConfigureNodeFields("publish_model", 18);
        replyroom = new ConfigureNodeFields("replyroom", 19);
        replyto = new ConfigureNodeFields("replyto", 20);
        roster_groups_allowed = new ConfigureNodeFields("roster_groups_allowed", 21);
        subscribe = new ConfigureNodeFields("subscribe", 22);
        title = new ConfigureNodeFields("title", 23);
        type = new ConfigureNodeFields("type", 24);
        $VALUES = (new ConfigureNodeFields[] {
            access_model, body_xslt, collection, dataform_xslt, deliver_payloads, itemreply, children_association_policy, children_association_whitelist, children, children_max, 
            max_items, max_payload_size, node_type, notify_config, notify_delete, notify_retract, persist_items, presence_based_delivery, publish_model, replyroom, 
            replyto, roster_groups_allowed, subscribe, title, type
        });
    }
}
