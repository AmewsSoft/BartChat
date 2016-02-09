// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.util.Collection;
import java.util.Iterator;
import org.jivesoftware.smack.packet.ExtensionElement;

public class PacketUtil
{

    public PacketUtil()
    {
    }

    public static ExtensionElement extensionElementFrom(Collection collection, String s, String s1)
    {
        for (collection = collection.iterator(); collection.hasNext();)
        {
            ExtensionElement extensionelement = (ExtensionElement)collection.next();
            if ((s == null || extensionelement.getElementName().equals(s)) && extensionelement.getNamespace().equals(s1))
            {
                return extensionelement;
            }
        }

        return null;
    }

    public static ExtensionElement packetExtensionfromCollection(Collection collection, String s, String s1)
    {
        return extensionElementFrom(collection, s, s1);
    }
}
