// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.util;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormNode;
import org.jivesoftware.smackx.pubsub.PubSubElementType;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

public class NodeUtils
{

    public NodeUtils()
    {
    }

    public static ConfigureForm getFormFromPacket(Stanza stanza, PubSubElementType pubsubelementtype)
    {
        return new ConfigureForm(((FormNode)stanza.getExtension(pubsubelementtype.getElementName(), pubsubelementtype.getNamespace().getXmlns())).getForm());
    }
}
