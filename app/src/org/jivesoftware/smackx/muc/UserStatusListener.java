// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;


public interface UserStatusListener
{

    public abstract void adminGranted();

    public abstract void adminRevoked();

    public abstract void banned(String s, String s1);

    public abstract void kicked(String s, String s1);

    public abstract void membershipGranted();

    public abstract void membershipRevoked();

    public abstract void moderatorGranted();

    public abstract void moderatorRevoked();

    public abstract void ownershipGranted();

    public abstract void ownershipRevoked();

    public abstract void voiceGranted();

    public abstract void voiceRevoked();
}
