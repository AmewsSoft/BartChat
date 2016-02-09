// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;


public interface ParticipantStatusListener
{

    public abstract void adminGranted(String s);

    public abstract void adminRevoked(String s);

    public abstract void banned(String s, String s1, String s2);

    public abstract void joined(String s);

    public abstract void kicked(String s, String s1, String s2);

    public abstract void left(String s);

    public abstract void membershipGranted(String s);

    public abstract void membershipRevoked(String s);

    public abstract void moderatorGranted(String s);

    public abstract void moderatorRevoked(String s);

    public abstract void nicknameChanged(String s, String s1);

    public abstract void ownershipGranted(String s);

    public abstract void ownershipRevoked(String s);

    public abstract void voiceGranted(String s);

    public abstract void voiceRevoked(String s);
}
