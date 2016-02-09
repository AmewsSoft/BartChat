// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


public final class PublishModel extends Enum
{

    private static final PublishModel $VALUES[];
    public static final PublishModel open;
    public static final PublishModel publishers;
    public static final PublishModel subscribers;

    private PublishModel(String s, int i)
    {
        super(s, i);
    }

    public static PublishModel valueOf(String s)
    {
        return (PublishModel)Enum.valueOf(org/jivesoftware/smackx/pubsub/PublishModel, s);
    }

    public static PublishModel[] values()
    {
        return (PublishModel[])$VALUES.clone();
    }

    static 
    {
        publishers = new PublishModel("publishers", 0);
        subscribers = new PublishModel("subscribers", 1);
        open = new PublishModel("open", 2);
        $VALUES = (new PublishModel[] {
            publishers, subscribers, open
        });
    }
}
