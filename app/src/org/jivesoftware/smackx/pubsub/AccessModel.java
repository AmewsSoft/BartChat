// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


public final class AccessModel extends Enum
{

    private static final AccessModel $VALUES[];
    public static final AccessModel authorize;
    public static final AccessModel open;
    public static final AccessModel presence;
    public static final AccessModel roster;
    public static final AccessModel whitelist;

    private AccessModel(String s, int i)
    {
        super(s, i);
    }

    public static AccessModel valueOf(String s)
    {
        return (AccessModel)Enum.valueOf(org/jivesoftware/smackx/pubsub/AccessModel, s);
    }

    public static AccessModel[] values()
    {
        return (AccessModel[])$VALUES.clone();
    }

    static 
    {
        open = new AccessModel("open", 0);
        authorize = new AccessModel("authorize", 1);
        presence = new AccessModel("presence", 2);
        roster = new AccessModel("roster", 3);
        whitelist = new AccessModel("whitelist", 4);
        $VALUES = (new AccessModel[] {
            open, authorize, presence, roster, whitelist
        });
    }
}
