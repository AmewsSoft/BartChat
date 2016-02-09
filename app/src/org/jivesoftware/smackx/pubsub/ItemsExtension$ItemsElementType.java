// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            ItemsExtension, PubSubElementType

public static final class att extends Enum
{

    private static final retract $VALUES[];
    public static final retract items;
    public static final retract retract;
    private String att;
    private PubSubElementType elem;

    public static att valueOf(String s)
    {
        return (att)Enum.valueOf(org/jivesoftware/smackx/pubsub/ItemsExtension$ItemsElementType, s);
    }

    public static att[] values()
    {
        return (att[])$VALUES.clone();
    }

    public String getElementAttribute()
    {
        return att;
    }

    public PubSubElementType getNodeElement()
    {
        return elem;
    }

    static 
    {
        items = new <init>("items", 0, PubSubElementType.ITEMS, "max_items");
        retract = new <init>("retract", 1, PubSubElementType.RETRACT, "notify");
        $VALUES = (new .VALUES[] {
            items, retract
        });
    }

    private (String s, int i, PubSubElementType pubsubelementtype, String s1)
    {
        super(s, i);
        elem = pubsubelementtype;
        att = s1;
    }
}
