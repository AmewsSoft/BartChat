// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;


public final class ChildrenAssociationPolicy extends Enum
{

    private static final ChildrenAssociationPolicy $VALUES[];
    public static final ChildrenAssociationPolicy all;
    public static final ChildrenAssociationPolicy owners;
    public static final ChildrenAssociationPolicy whitelist;

    private ChildrenAssociationPolicy(String s, int i)
    {
        super(s, i);
    }

    public static ChildrenAssociationPolicy valueOf(String s)
    {
        return (ChildrenAssociationPolicy)Enum.valueOf(org/jivesoftware/smackx/pubsub/ChildrenAssociationPolicy, s);
    }

    public static ChildrenAssociationPolicy[] values()
    {
        return (ChildrenAssociationPolicy[])$VALUES.clone();
    }

    static 
    {
        all = new ChildrenAssociationPolicy("all", 0);
        owners = new ChildrenAssociationPolicy("owners", 1);
        whitelist = new ChildrenAssociationPolicy("whitelist", 2);
        $VALUES = (new ChildrenAssociationPolicy[] {
            all, owners, whitelist
        });
    }
}
