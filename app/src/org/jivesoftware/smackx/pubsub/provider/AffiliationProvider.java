// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.Affiliation;

public class AffiliationProvider extends EmbeddedExtensionProvider
{

    public AffiliationProvider()
    {
    }

    protected volatile ExtensionElement createReturnExtension(String s, String s1, Map map, List list)
    {
        return createReturnExtension(s, s1, map, list);
    }

    protected Affiliation createReturnExtension(String s, String s1, Map map, List list)
    {
        return new Affiliation((String)map.get("node"), org.jivesoftware.smackx.pubsub.Affiliation.Type.valueOf((String)map.get("affiliation")));
    }
}
