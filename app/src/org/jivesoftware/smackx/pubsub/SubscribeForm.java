// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UnknownFormatConversionException;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jxmpp.util.XmppDateTime;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            SubscribeOptionFields, PresenceState

public class SubscribeForm extends Form
{

    public SubscribeForm(Form form)
    {
        super(form.getDataFormToSend());
    }

    public SubscribeForm(org.jivesoftware.smackx.xdata.packet.DataForm.Type type)
    {
        super(type);
    }

    public SubscribeForm(DataForm dataform)
    {
        super(dataform);
    }

    private void addField(SubscribeOptionFields subscribeoptionfields, org.jivesoftware.smackx.xdata.FormField.Type type)
    {
        subscribeoptionfields = subscribeoptionfields.getFieldName();
        if (getField(subscribeoptionfields) == null)
        {
            subscribeoptionfields = new FormField(subscribeoptionfields);
            subscribeoptionfields.setType(type);
            addField(((FormField) (subscribeoptionfields)));
        }
    }

    private String getFieldValue(SubscribeOptionFields subscribeoptionfields)
    {
        return (String)getField(subscribeoptionfields.getFieldName()).getValues().get(0);
    }

    private List getFieldValues(SubscribeOptionFields subscribeoptionfields)
    {
        return getField(subscribeoptionfields.getFieldName()).getValues();
    }

    private static boolean parseBoolean(String s)
    {
        return "1".equals(s) || "true".equals(s);
    }

    public int getDigestFrequency()
    {
        return Integer.parseInt(getFieldValue(SubscribeOptionFields.digest_frequency));
    }

    public Date getExpiry()
    {
        Object obj = getFieldValue(SubscribeOptionFields.expire);
        Date date;
        try
        {
            date = XmppDateTime.parseDate(((String) (obj)));
        }
        catch (ParseException parseexception)
        {
            obj = new UnknownFormatConversionException(((String) (obj)));
            ((UnknownFormatConversionException) (obj)).initCause(parseexception);
            throw obj;
        }
        return date;
    }

    public List getShowValues()
    {
        ArrayList arraylist = new ArrayList(5);
        for (Iterator iterator = getFieldValues(SubscribeOptionFields.show_values).iterator(); iterator.hasNext(); arraylist.add(PresenceState.valueOf((String)iterator.next()))) { }
        return arraylist;
    }

    public boolean isDeliverOn()
    {
        return parseBoolean(getFieldValue(SubscribeOptionFields.deliver));
    }

    public boolean isDigestOn()
    {
        return parseBoolean(getFieldValue(SubscribeOptionFields.digest));
    }

    public boolean isIncludeBody()
    {
        return parseBoolean(getFieldValue(SubscribeOptionFields.include_body));
    }

    public void setDeliverOn(boolean flag)
    {
        addField(SubscribeOptionFields.deliver, org.jivesoftware.smackx.xdata.FormField.Type.bool);
        setAnswer(SubscribeOptionFields.deliver.getFieldName(), flag);
    }

    public void setDigestFrequency(int i)
    {
        addField(SubscribeOptionFields.digest_frequency, org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        setAnswer(SubscribeOptionFields.digest_frequency.getFieldName(), i);
    }

    public void setDigestOn(boolean flag)
    {
        addField(SubscribeOptionFields.deliver, org.jivesoftware.smackx.xdata.FormField.Type.bool);
        setAnswer(SubscribeOptionFields.deliver.getFieldName(), flag);
    }

    public void setExpiry(Date date)
    {
        addField(SubscribeOptionFields.expire, org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        setAnswer(SubscribeOptionFields.expire.getFieldName(), XmppDateTime.formatXEP0082Date(date));
    }

    public void setIncludeBody(boolean flag)
    {
        addField(SubscribeOptionFields.include_body, org.jivesoftware.smackx.xdata.FormField.Type.bool);
        setAnswer(SubscribeOptionFields.include_body.getFieldName(), flag);
    }

    public void setShowValues(Collection collection)
    {
        ArrayList arraylist = new ArrayList(collection.size());
        for (collection = collection.iterator(); collection.hasNext(); arraylist.add(((PresenceState)collection.next()).toString())) { }
        addField(SubscribeOptionFields.show_values, org.jivesoftware.smackx.xdata.FormField.Type.list_multi);
        setAnswer(SubscribeOptionFields.show_values.getFieldName(), arraylist);
    }
}
