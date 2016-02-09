// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.shim.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.shim.packet.HeadersExtension;

public class HeadersProvider extends EmbeddedExtensionProvider
{

    public static final HeadersProvider INSTANCE = new HeadersProvider();

    public HeadersProvider()
    {
    }

    protected volatile ExtensionElement createReturnExtension(String s, String s1, Map map, List list)
    {
        return createReturnExtension(s, s1, map, list);
    }

    protected HeadersExtension createReturnExtension(String s, String s1, Map map, List list)
    {
        return new HeadersExtension(list);
    }

}
