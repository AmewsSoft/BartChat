// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.provider;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.FormNode;
import org.jivesoftware.smackx.pubsub.FormNodeType;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class FormNodeProvider extends EmbeddedExtensionProvider
{

    public FormNodeProvider()
    {
    }

    protected volatile ExtensionElement createReturnExtension(String s, String s1, Map map, List list)
    {
        return createReturnExtension(s, s1, map, list);
    }

    protected FormNode createReturnExtension(String s, String s1, Map map, List list)
    {
        return new FormNode(FormNodeType.valueOfFromElementName(s, s1), (String)map.get("node"), new Form((DataForm)list.iterator().next()));
    }
}
