// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, EmbeddedPacketExtension, PubSubElementType, ConfigureForm

public class ConfigurationEvent extends NodeExtension
    implements EmbeddedPacketExtension
{

    private ConfigureForm form;

    public ConfigurationEvent(String s)
    {
        super(PubSubElementType.CONFIGURATION, s);
    }

    public ConfigurationEvent(String s, ConfigureForm configureform)
    {
        super(PubSubElementType.CONFIGURATION, s);
        form = configureform;
    }

    public ConfigureForm getConfiguration()
    {
        return form;
    }

    public List getExtensions()
    {
        if (getConfiguration() == null)
        {
            return Collections.emptyList();
        } else
        {
            return Arrays.asList(new ExtensionElement[] {
                getConfiguration().getDataFormToSend()
            });
        }
    }
}
