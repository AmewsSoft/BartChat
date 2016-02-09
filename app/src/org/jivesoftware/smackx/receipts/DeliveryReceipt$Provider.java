// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.receipts;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;

// Referenced classes of package org.jivesoftware.smackx.receipts:
//            DeliveryReceipt

public static class  extends EmbeddedExtensionProvider
{

    protected volatile ExtensionElement createReturnExtension(String s, String s1, Map map, List list)
    {
        return createReturnExtension(s, s1, map, list);
    }

    protected DeliveryReceipt createReturnExtension(String s, String s1, Map map, List list)
    {
        return new DeliveryReceipt((String)map.get("id"));
    }

    public ()
    {
    }
}
