// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.ItemsExtension;

public class ItemsProvider extends EmbeddedExtensionProvider
{

    public ItemsProvider()
    {
    }

    protected volatile ExtensionElement createReturnExtension(String s, String s1, Map map, List list)
    {
        return createReturnExtension(s, s1, map, list);
    }

    protected ItemsExtension createReturnExtension(String s, String s1, Map map, List list)
    {
        return new ItemsExtension(org.jivesoftware.smackx.pubsub.ItemsExtension.ItemsElementType.items, (String)map.get("node"), list);
    }
}
