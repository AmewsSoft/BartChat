// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            ConfigureNodeFields, AccessModel, ChildrenAssociationPolicy, ItemReply, 
//            NodeType, PublishModel

public class ConfigureForm extends Form
{

    public ConfigureForm(Form form)
    {
        super(form.getDataFormToSend());
    }

    public ConfigureForm(org.jivesoftware.smackx.xdata.packet.DataForm.Type type)
    {
        super(type);
    }

    public ConfigureForm(DataForm dataform)
    {
        super(dataform);
    }

    private void addField(ConfigureNodeFields configurenodefields, org.jivesoftware.smackx.xdata.FormField.Type type)
    {
        configurenodefields = configurenodefields.getFieldName();
        if (getField(configurenodefields) == null)
        {
            configurenodefields = new FormField(configurenodefields);
            configurenodefields.setType(type);
            addField(((FormField) (configurenodefields)));
        }
    }

    private String getFieldValue(ConfigureNodeFields configurenodefields)
    {
        configurenodefields = getField(configurenodefields.getFieldName());
        if (configurenodefields.getValues().isEmpty())
        {
            return null;
        } else
        {
            return (String)configurenodefields.getValues().get(0);
        }
    }

    private List getFieldValues(ConfigureNodeFields configurenodefields)
    {
        return getField(configurenodefields.getFieldName()).getValues();
    }

    private List getListSingle(String s)
    {
        ArrayList arraylist = new ArrayList(1);
        arraylist.add(s);
        return arraylist;
    }

    private static boolean parseBoolean(String s)
    {
        return "1".equals(s) || "true".equals(s);
    }

    public AccessModel getAccessModel()
    {
        String s = getFieldValue(ConfigureNodeFields.access_model);
        if (s == null)
        {
            return null;
        } else
        {
            return AccessModel.valueOf(s);
        }
    }

    public String getBodyXSLT()
    {
        return getFieldValue(ConfigureNodeFields.body_xslt);
    }

    public List getChildren()
    {
        return getFieldValues(ConfigureNodeFields.children);
    }

    public ChildrenAssociationPolicy getChildrenAssociationPolicy()
    {
        String s = getFieldValue(ConfigureNodeFields.children_association_policy);
        if (s == null)
        {
            return null;
        } else
        {
            return ChildrenAssociationPolicy.valueOf(s);
        }
    }

    public List getChildrenAssociationWhitelist()
    {
        return getFieldValues(ConfigureNodeFields.children_association_whitelist);
    }

    public int getChildrenMax()
    {
        return Integer.parseInt(getFieldValue(ConfigureNodeFields.children_max));
    }

    public String getCollection()
    {
        return getFieldValue(ConfigureNodeFields.collection);
    }

    public String getDataType()
    {
        return getFieldValue(ConfigureNodeFields.type);
    }

    public String getDataformXSLT()
    {
        return getFieldValue(ConfigureNodeFields.dataform_xslt);
    }

    public ItemReply getItemReply()
    {
        String s = getFieldValue(ConfigureNodeFields.itemreply);
        if (s == null)
        {
            return null;
        } else
        {
            return ItemReply.valueOf(s);
        }
    }

    public int getMaxItems()
    {
        return Integer.parseInt(getFieldValue(ConfigureNodeFields.max_items));
    }

    public int getMaxPayloadSize()
    {
        return Integer.parseInt(getFieldValue(ConfigureNodeFields.max_payload_size));
    }

    public NodeType getNodeType()
    {
        String s = getFieldValue(ConfigureNodeFields.node_type);
        if (s == null)
        {
            return null;
        } else
        {
            return NodeType.valueOf(s);
        }
    }

    public PublishModel getPublishModel()
    {
        String s = getFieldValue(ConfigureNodeFields.publish_model);
        if (s == null)
        {
            return null;
        } else
        {
            return PublishModel.valueOf(s);
        }
    }

    public List getReplyRoom()
    {
        return getFieldValues(ConfigureNodeFields.replyroom);
    }

    public List getReplyTo()
    {
        return getFieldValues(ConfigureNodeFields.replyto);
    }

    public List getRosterGroupsAllowed()
    {
        return getFieldValues(ConfigureNodeFields.roster_groups_allowed);
    }

    public String getTitle()
    {
        return getFieldValue(ConfigureNodeFields.title);
    }

    public boolean isDeliverPayloads()
    {
        return parseBoolean(getFieldValue(ConfigureNodeFields.deliver_payloads));
    }

    public boolean isNotifyConfig()
    {
        return parseBoolean(getFieldValue(ConfigureNodeFields.notify_config));
    }

    public boolean isNotifyDelete()
    {
        return parseBoolean(getFieldValue(ConfigureNodeFields.notify_delete));
    }

    public boolean isNotifyRetract()
    {
        return parseBoolean(getFieldValue(ConfigureNodeFields.notify_retract));
    }

    public boolean isPersistItems()
    {
        return parseBoolean(getFieldValue(ConfigureNodeFields.persist_items));
    }

    public boolean isPresenceBasedDelivery()
    {
        return parseBoolean(getFieldValue(ConfigureNodeFields.presence_based_delivery));
    }

    public boolean isSubscibe()
    {
        return isSubscribe();
    }

    public boolean isSubscribe()
    {
        return parseBoolean(getFieldValue(ConfigureNodeFields.subscribe));
    }

    public void setAccessModel(AccessModel accessmodel)
    {
        addField(ConfigureNodeFields.access_model, org.jivesoftware.smackx.xdata.FormField.Type.list_single);
        setAnswer(ConfigureNodeFields.access_model.getFieldName(), getListSingle(accessmodel.toString()));
    }

    public void setBodyXSLT(String s)
    {
        addField(ConfigureNodeFields.body_xslt, org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        setAnswer(ConfigureNodeFields.body_xslt.getFieldName(), s);
    }

    public void setChildren(List list)
    {
        addField(ConfigureNodeFields.children, org.jivesoftware.smackx.xdata.FormField.Type.text_multi);
        setAnswer(ConfigureNodeFields.children.getFieldName(), list);
    }

    public void setChildrenAssociationPolicy(ChildrenAssociationPolicy childrenassociationpolicy)
    {
        addField(ConfigureNodeFields.children_association_policy, org.jivesoftware.smackx.xdata.FormField.Type.list_single);
        ArrayList arraylist = new ArrayList(1);
        arraylist.add(childrenassociationpolicy.toString());
        setAnswer(ConfigureNodeFields.children_association_policy.getFieldName(), arraylist);
    }

    public void setChildrenAssociationWhitelist(List list)
    {
        addField(ConfigureNodeFields.children_association_whitelist, org.jivesoftware.smackx.xdata.FormField.Type.jid_multi);
        setAnswer(ConfigureNodeFields.children_association_whitelist.getFieldName(), list);
    }

    public void setChildrenMax(int i)
    {
        addField(ConfigureNodeFields.children_max, org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        setAnswer(ConfigureNodeFields.children_max.getFieldName(), i);
    }

    public void setCollection(String s)
    {
        addField(ConfigureNodeFields.collection, org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        setAnswer(ConfigureNodeFields.collection.getFieldName(), s);
    }

    public void setDataType(String s)
    {
        addField(ConfigureNodeFields.type, org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        setAnswer(ConfigureNodeFields.type.getFieldName(), s);
    }

    public void setDataformXSLT(String s)
    {
        addField(ConfigureNodeFields.dataform_xslt, org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        setAnswer(ConfigureNodeFields.dataform_xslt.getFieldName(), s);
    }

    public void setDeliverPayloads(boolean flag)
    {
        addField(ConfigureNodeFields.deliver_payloads, org.jivesoftware.smackx.xdata.FormField.Type.bool);
        setAnswer(ConfigureNodeFields.deliver_payloads.getFieldName(), flag);
    }

    public void setItemReply(ItemReply itemreply)
    {
        addField(ConfigureNodeFields.itemreply, org.jivesoftware.smackx.xdata.FormField.Type.list_single);
        setAnswer(ConfigureNodeFields.itemreply.getFieldName(), getListSingle(itemreply.toString()));
    }

    public void setMaxItems(int i)
    {
        addField(ConfigureNodeFields.max_items, org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        setAnswer(ConfigureNodeFields.max_items.getFieldName(), i);
    }

    public void setMaxPayloadSize(int i)
    {
        addField(ConfigureNodeFields.max_payload_size, org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        setAnswer(ConfigureNodeFields.max_payload_size.getFieldName(), i);
    }

    public void setNodeType(NodeType nodetype)
    {
        addField(ConfigureNodeFields.node_type, org.jivesoftware.smackx.xdata.FormField.Type.list_single);
        setAnswer(ConfigureNodeFields.node_type.getFieldName(), getListSingle(nodetype.toString()));
    }

    public void setNotifyConfig(boolean flag)
    {
        addField(ConfigureNodeFields.notify_config, org.jivesoftware.smackx.xdata.FormField.Type.bool);
        setAnswer(ConfigureNodeFields.notify_config.getFieldName(), flag);
    }

    public void setNotifyDelete(boolean flag)
    {
        addField(ConfigureNodeFields.notify_delete, org.jivesoftware.smackx.xdata.FormField.Type.bool);
        setAnswer(ConfigureNodeFields.notify_delete.getFieldName(), flag);
    }

    public void setNotifyRetract(boolean flag)
    {
        addField(ConfigureNodeFields.notify_retract, org.jivesoftware.smackx.xdata.FormField.Type.bool);
        setAnswer(ConfigureNodeFields.notify_retract.getFieldName(), flag);
    }

    public void setPersistentItems(boolean flag)
    {
        addField(ConfigureNodeFields.persist_items, org.jivesoftware.smackx.xdata.FormField.Type.bool);
        setAnswer(ConfigureNodeFields.persist_items.getFieldName(), flag);
    }

    public void setPresenceBasedDelivery(boolean flag)
    {
        addField(ConfigureNodeFields.presence_based_delivery, org.jivesoftware.smackx.xdata.FormField.Type.bool);
        setAnswer(ConfigureNodeFields.presence_based_delivery.getFieldName(), flag);
    }

    public void setPublishModel(PublishModel publishmodel)
    {
        addField(ConfigureNodeFields.publish_model, org.jivesoftware.smackx.xdata.FormField.Type.list_single);
        setAnswer(ConfigureNodeFields.publish_model.getFieldName(), getListSingle(publishmodel.toString()));
    }

    public void setReplyRoom(List list)
    {
        addField(ConfigureNodeFields.replyroom, org.jivesoftware.smackx.xdata.FormField.Type.list_multi);
        setAnswer(ConfigureNodeFields.replyroom.getFieldName(), list);
    }

    public void setReplyTo(List list)
    {
        addField(ConfigureNodeFields.replyto, org.jivesoftware.smackx.xdata.FormField.Type.list_multi);
        setAnswer(ConfigureNodeFields.replyto.getFieldName(), list);
    }

    public void setRosterGroupsAllowed(List list)
    {
        addField(ConfigureNodeFields.roster_groups_allowed, org.jivesoftware.smackx.xdata.FormField.Type.list_multi);
        setAnswer(ConfigureNodeFields.roster_groups_allowed.getFieldName(), list);
    }

    public void setSubscribe(boolean flag)
    {
        addField(ConfigureNodeFields.subscribe, org.jivesoftware.smackx.xdata.FormField.Type.bool);
        setAnswer(ConfigureNodeFields.subscribe.getFieldName(), flag);
    }

    public void setTitle(String s)
    {
        addField(ConfigureNodeFields.title, org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        setAnswer(ConfigureNodeFields.title.getFieldName(), s);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append(getClass().getName()).append(" Content [").toString());
        for (Iterator iterator = getFields().iterator(); iterator.hasNext(); stringbuilder.append(')'))
        {
            Object obj = (FormField)iterator.next();
            stringbuilder.append('(');
            stringbuilder.append(((FormField) (obj)).getVariable());
            stringbuilder.append(':');
            StringBuilder stringbuilder1 = new StringBuilder();
            String s;
            for (obj = ((FormField) (obj)).getValues().iterator(); ((Iterator) (obj)).hasNext(); stringbuilder1.append(s))
            {
                s = (String)((Iterator) (obj)).next();
                if (stringbuilder1.length() > 0)
                {
                    stringbuilder.append(',');
                }
            }

            if (stringbuilder1.length() == 0)
            {
                stringbuilder1.append("NOT SET");
            }
            stringbuilder.append(stringbuilder1);
        }

        stringbuilder.append(']');
        return stringbuilder.toString();
    }
}
