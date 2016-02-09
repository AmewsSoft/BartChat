// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import java.util.Locale;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            PubSubElementType

public final class FormNodeType extends Enum
{

    private static final FormNodeType $VALUES[];
    public static final FormNodeType CONFIGURE;
    public static final FormNodeType CONFIGURE_OWNER;
    public static final FormNodeType DEFAULT;
    public static final FormNodeType OPTIONS;

    private FormNodeType(String s, int i)
    {
        super(s, i);
    }

    public static FormNodeType valueOf(String s)
    {
        return (FormNodeType)Enum.valueOf(org/jivesoftware/smackx/pubsub/FormNodeType, s);
    }

    public static FormNodeType valueOfFromElementName(String s, String s1)
    {
        if ("configure".equals(s) && PubSubNamespace.OWNER.getXmlns().equals(s1))
        {
            return CONFIGURE_OWNER;
        } else
        {
            return valueOf(s.toUpperCase(Locale.US));
        }
    }

    public static FormNodeType[] values()
    {
        return (FormNodeType[])$VALUES.clone();
    }

    public PubSubElementType getNodeElement()
    {
        return PubSubElementType.valueOf(toString());
    }

    static 
    {
        CONFIGURE_OWNER = new FormNodeType("CONFIGURE_OWNER", 0);
        CONFIGURE = new FormNodeType("CONFIGURE", 1);
        OPTIONS = new FormNodeType("OPTIONS", 2);
        DEFAULT = new FormNodeType("DEFAULT", 3);
        $VALUES = (new FormNodeType[] {
            CONFIGURE_OWNER, CONFIGURE, OPTIONS, DEFAULT
        });
    }
}
