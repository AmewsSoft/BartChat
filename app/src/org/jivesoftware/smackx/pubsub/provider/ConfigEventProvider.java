// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.provider;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.ConfigurationEvent;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class ConfigEventProvider extends EmbeddedExtensionProvider
{

    public ConfigEventProvider()
    {
    }

    protected volatile ExtensionElement createReturnExtension(String s, String s1, Map map, List list)
    {
        return createReturnExtension(s, s1, map, list);
    }

    protected ConfigurationEvent createReturnExtension(String s, String s1, Map map, List list)
    {
        if (list.size() == 0)
        {
            return new ConfigurationEvent((String)map.get("node"));
        } else
        {
            return new ConfigurationEvent((String)map.get("node"), new ConfigureForm((DataForm)list.iterator().next()));
        }
    }
}
