// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp.provider;

import java.io.IOException;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.amp.AMPDeliverCondition;
import org.jivesoftware.smackx.amp.AMPExpireAtCondition;
import org.jivesoftware.smackx.amp.AMPMatchResourceCondition;
import org.jivesoftware.smackx.amp.packet.AMPExtension;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AMPExtensionProvider extends ExtensionElementProvider
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/amp/provider/AMPExtensionProvider.getName());

    public AMPExtensionProvider()
    {
    }

    private org.jivesoftware.smackx.amp.packet.AMPExtension.Condition createCondition(String s, String s1)
    {
        if (s == null || s1 == null)
        {
            LOGGER.severe("Can't create rule condition from null name and/or value");
            return null;
        }
        if ("deliver".equals(s))
        {
            try
            {
                s = new AMPDeliverCondition(org.jivesoftware.smackx.amp.AMPDeliverCondition.Value.valueOf(s1));
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                LOGGER.severe((new StringBuilder()).append("Found invalid rule delivery condition value ").append(s1).toString());
                return null;
            }
            return s;
        }
        if ("expire-at".equals(s))
        {
            return new AMPExpireAtCondition(s1);
        }
        if ("match-resource".equals(s))
        {
            try
            {
                s = new AMPMatchResourceCondition(org.jivesoftware.smackx.amp.AMPMatchResourceCondition.Value.valueOf(s1));
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                LOGGER.severe((new StringBuilder()).append("Found invalid rule match-resource condition value ").append(s1).toString());
                return null;
            }
            return s;
        } else
        {
            LOGGER.severe((new StringBuilder()).append("Found unknown rule condition name ").append(s).toString());
            return null;
        }
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public AMPExtension parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException
    {
        Object obj2 = xmlpullparser.getAttributeValue(null, "from");
        String s = xmlpullparser.getAttributeValue(null, "to");
        String s1 = xmlpullparser.getAttributeValue(null, "status");
        Object obj1 = null;
        Object obj = obj1;
        if (s1 != null)
        {
            try
            {
                obj = org.jivesoftware.smackx.amp.packet.AMPExtension.Status.valueOf(s1);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                LOGGER.severe((new StringBuilder()).append("Found invalid amp status ").append(s1).toString());
                obj = obj1;
            }
        }
        obj2 = new AMPExtension(((String) (obj2)), s, ((org.jivesoftware.smackx.amp.packet.AMPExtension.Status) (obj)));
        obj = xmlpullparser.getAttributeValue(null, "per-hop");
        if (obj != null)
        {
            ((AMPExtension) (obj2)).setPerHop(Boolean.parseBoolean(((String) (obj))));
        }
        i = 0;
        do
        {
            if (i != 0)
            {
                break;
            }
            int j = xmlpullparser.next();
            if (j == 2)
            {
                if (xmlpullparser.getName().equals("rule"))
                {
                    s1 = xmlpullparser.getAttributeValue(null, "action");
                    org.jivesoftware.smackx.amp.packet.AMPExtension.Condition condition = createCondition(xmlpullparser.getAttributeValue(null, "condition"), xmlpullparser.getAttributeValue(null, "value"));
                    obj1 = null;
                    obj = obj1;
                    if (s1 != null)
                    {
                        try
                        {
                            obj = org.jivesoftware.smackx.amp.packet.AMPExtension.Action.valueOf(s1);
                        }
                        // Misplaced declaration of an exception variable
                        catch (Object obj)
                        {
                            LOGGER.severe((new StringBuilder()).append("Found invalid rule action value ").append(s1).toString());
                            obj = obj1;
                        }
                    }
                    if (obj == null || condition == null)
                    {
                        LOGGER.severe("Rule is skipped because either it's action or it's condition is invalid");
                    } else
                    {
                        ((AMPExtension) (obj2)).addRule(new org.jivesoftware.smackx.amp.packet.AMPExtension.Rule(((org.jivesoftware.smackx.amp.packet.AMPExtension.Action) (obj)), condition));
                    }
                }
            } else
            if (j == 3 && xmlpullparser.getName().equals("amp"))
            {
                i = 1;
            }
        } while (true);
        return ((AMPExtension) (obj2));
    }

}
